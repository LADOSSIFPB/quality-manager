package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.MembroProjeto;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

public class ParticipacaoDAO implements GenericDAO<Integer, Participacao> {

	static DBPool banco;
	private static ParticipacaoDAO instance;

	public static ParticipacaoDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new ParticipacaoDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public ParticipacaoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Participacao participacao) throws QManagerSQLException {

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
	public void update(Participacao participacao) throws QManagerSQLException {

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
	public List<Participacao> getAll() throws QManagerSQLException {
		List<Participacao> participacoes;

		try {

			String sql = String.format("%s", "SELECT * FROM `tb_participacao`");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			participacoes = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return participacoes;
	}

	@Override
	public Participacao getById(Integer id) throws QManagerSQLException {

		Participacao participacao = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT * FROM `tb_participacao` WHERE `id_participacao` =",
							id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Participacao> participacoes = convertToList(rs);

			if (!participacoes.isEmpty())
				participacao = participacoes.get(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return participacao;

	}

	@Override
	public List<Participacao> convertToList(ResultSet rs)
			throws QManagerSQLException {

		List<Participacao> participacoes = new LinkedList<Participacao>();

		try {

			while (rs.next()) {
				Participacao participacao = new Participacao();
				MembroProjeto membroProjeto = new MembroProjeto();
				Projeto projeto = new Projeto();
				participacao.setIdParticipacao(rs.getInt("id_participacao"));
				membroProjeto = (MembroProjeto) PessoaDAO.getInstance()
						.getById(rs.getInt("pessoa_id"));
				participacao.setMembroProjeto(membroProjeto);
				projeto = ProjetoDAO.getInstance().getById(
						rs.getInt("projeto_id"));
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
