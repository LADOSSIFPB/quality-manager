package br.edu.ifpb.qmanager.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.MembroProjeto;
import br.edu.ifpb.qmanager.entidade.Partipacao;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class ParticipacaoDAO implements GenericDAO<Integer, Partipacao> {

	// a conexão com o banco de dados
	public Connection connection;
	private DatabaseConnection banco;

	public ParticipacaoDAO(DatabaseConnection banco) {
		this.connection = (Connection) banco.getConnection();
		this.banco = banco;
	}

	@Override
	public int insert(Partipacao participacao) throws QManagerSQLException {

		int chave = 0;

		try {

			String sql = String
					.format("%s %s ('%d', '%d', '%s', '%s')",
							"INSERT INTO `tb_participacao` (`pessoa_id`, `projeto_id`, `dt_inicio`, `vl_bolsa`)",
							"VALUES", participacao.getMembroProjeto()
									.getPessoaId(), participacao.getProjeto()
									.getIdProjeto(), new Date(participacao
									.getInicioParticipacao().getTime()),
							participacao.getValorBolsa());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			chave = BancoUtil.getGenerateKey(stmt);

			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return chave;

	}

	@Override
	public void update(Partipacao participacao) throws QManagerSQLException {

		try {

			String sql = "UPDATE `tb_participacao` SET `pessoa_id`=?, `projeto_id`=?, `dt_inicio`=?, `dt_fim`=?, `fl_bolsista`=? "
					+ "WHERE `id_participacao`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, participacao.getMembroProjeto().getPessoaId());
			stmt.setInt(2, participacao.getProjeto().getIdProjeto());
			stmt.setDate(3, new Date(participacao.getInicioParticipacao()
					.getTime()));
			stmt.setDate(4, new Date(participacao.getFimParticipacao()
					.getTime()));
			stmt.setDouble(5, participacao.getValorBolsa());
			stmt.setInt(6, participacao.getIdParticipacao());

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public void delete(Integer id) throws QManagerSQLException {

		try {

			String sql = "DELETE FROM `tb_participacao` WHERE `id_participacao`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public List<Partipacao> getAll() throws QManagerSQLException {
		List<Partipacao> participacoes;

		try {

			String sql = String.format("%s", "SELECT * FROM `tb_participacao`");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			participacoes = convertToList(rs);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return participacoes;
	}

	@Override
	public Partipacao getById(Integer id) throws QManagerSQLException {

		Partipacao participacao = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT * FROM `tb_participacao` WHERE `id_participacao` =",
							id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Partipacao> participacoes = convertToList(rs);

			if (!participacoes.isEmpty())
				participacao = participacoes.get(0);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return participacao;

	}

	@Override
	public List<Partipacao> convertToList(ResultSet rs)
			throws QManagerSQLException {

		List<Partipacao> participacoes = new LinkedList<Partipacao>();

		PessoaDAO pessoaDAO = new PessoaDAO(banco);
		ProjetoDAO projetoDAO = new ProjetoDAO(banco);

		try {

			while (rs.next()) {
				Partipacao participacao = new Partipacao();
				MembroProjeto membroProjeto = new MembroProjeto();
				Projeto projeto = new Projeto();
				participacao.setIdParticipacao(rs.getInt("id_participacao"));
				membroProjeto = (MembroProjeto) pessoaDAO.getById(rs
						.getInt("pessoa_id"));
				participacao.setMembroProjeto(membroProjeto);
				projeto = projetoDAO.getById(rs.getInt("projeto_id"));
				participacao.setProjeto(projeto);
				participacao.setInicioParticipacao(rs.getDate("dt_inicio"));
				participacao.setFimParticipacao(rs.getDate("dt_fim"));
				participacao.setValorBolsa(rs.getInt("vl_bolsa"));
				participacao.setRegistro(rs.getDate("dt_registro"));

				participacoes.add(participacao);

			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return participacoes;

	}

}
