package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.TipoParticipacao;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class ParticipacaoDAO implements GenericDAO<Integer, Participacao> {

	static DBPool banco;
	
	private static ParticipacaoDAO instance;
	
	public Connection connection;

	public static ParticipacaoDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new ParticipacaoDAO(banco);
		return instance;
	}

	public ParticipacaoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Participacao participacao) throws SQLExceptionQManager {

		int idParticipacao = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {

			String sql = String.format("%s %s (%d, %d, '%s', '%s', '%s')",
					"INSERT INTO tb_participacao (pessoa_id," 
							+ " projeto_id,"
							+ " dt_inicio,"
							+ " vl_bolsa,"
							+ " tipo_participacao)", "VALUES", participacao
							.getPessoa().getPessoaId(), participacao
							.getProjeto().getIdProjeto(), new Date(participacao
							.getInicioParticipacao().getTime()), participacao
							.getValorBolsa(), participacao
							.getTipoParticipacao().toString());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idParticipacao = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}

		return idParticipacao;
	}

	@Override
	public void update(Participacao participacao) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_participacao"
					+ " SET pessoa_id=?,"
					+ " projeto_id=?,"
					+ " dt_inicio=?,"
					+ " dt_fim=?,"
					+ " fl_bolsista=?,"
					+ " tipo_participacao=?"
					+ " WHERE id_participacao=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, participacao.getPessoa().getPessoaId());
			stmt.setInt(2, participacao.getProjeto().getIdProjeto());
			stmt.setDate(3, new Date(participacao.getInicioParticipacao()
					.getTime()));
			stmt.setDate(4, new Date(participacao.getFimParticipacao()
					.getTime()));
			stmt.setDouble(5, participacao.getValorBolsa());
			stmt.setString(6, participacao.getTipoParticipacao().toString());
			stmt.setInt(7, participacao.getIdParticipacao());

			stmt.execute();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, this.connection);
		}
	}

	@Override
	public void delete(Integer id) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "DELETE FROM tb_participacao WHERE id_participacao=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}
	}

	@Override
	public List<Participacao> getAll() throws SQLExceptionQManager {

		List<Participacao> participacoes;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s",
					"SELECT participacao.id_participacao, "
							+ "participacao.pessoa_id, "
							+ "participacao.projeto_id, "
							+ "participacao.dt_inicio, "
							+ "participacao.dt_fim, "
							+ "participacao.vl_bolsa, "
							+ "participacao.tipo_participacao, "
							+ "participacao.dt_registro "
							+ "FROM tb_participacao participacao");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			participacoes = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return participacoes;
	}

	@Override
	public Participacao getById(Integer id) throws SQLExceptionQManager {

		Participacao participacao = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d",
					"SELECT participacao.id_participacao, "
							+ "participacao.pessoa_id, "
							+ "participacao.projeto_id, "
							+ "participacao.dt_inicio, "
							+ "participacao.dt_fim, "
							+ "participacao.vl_bolsa, "
							+ "participacao.tipo_participacao, "
							+ "participacao.dt_registro "
							+ "FROM tb_participacao participacao "
							+ "WHERE participacao.id_participacao =", id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Participacao> participacoes = convertToList(rs);

			if (!participacoes.isEmpty())
				participacao = participacoes.get(0);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return participacao;

	}

	public List<Participacao> getByProjeto(Projeto projeto)
			throws SQLExceptionQManager {

		List<Participacao> participacoes;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d",
					"SELECT participacao.id_participacao, "
							+ "participacao.pessoa_id, "
							+ "participacao.projeto_id, "
							+ "participacao.dt_inicio, "
							+ "participacao.dt_fim, "
							+ "participacao.vl_bolsa, "
							+ "participacao.tipo_participacao, "
							+ "participacao.dt_registro "
							+ "FROM tb_participacao participacao "
							+ "WHERE participacao.projeto_id =",
					projeto.getIdProjeto());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			participacoes = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return participacoes;
	}

	@Override
	public List<Participacao> find(Participacao entity)
			throws SQLExceptionQManager {
		return null;
	}

	@Override
	public List<Participacao> convertToList(ResultSet rs)
			throws SQLExceptionQManager {

		List<Participacao> participacoes = new LinkedList<Participacao>();

		try {

			while (rs.next()) {
				Participacao participacao = new Participacao();

				participacao.setTipoParticipacao(TipoParticipacao.valueOf(rs
						.getString("participacao.tipo_participacao")));
				participacao.getPessoa().setPessoaId(
						rs.getInt("participacao.pessoa_id"));
				participacao.getProjeto().setIdProjeto(
						rs.getInt("participacao.projeto_id"));

				participacao.setIdParticipacao(rs.getInt(
						"participacao.id_participacao"));
				participacao.setInicioParticipacao(rs.getDate(
						"participacao.dt_inicio"));
				participacao.setFimParticipacao(rs.getDate(
						"participacao.dt_fim"));
				participacao.setValorBolsa(rs.getInt(
						"participacao.vl_bolsa"));
				participacao.setRegistro(rs.getDate(
								"participacao.dt_registro"));

				double valorBolsa = participacao.getValorBolsa();
				if (valorBolsa > 0.0)
					participacao.setBolsista(true);
				else
					participacao.setBolsista(false);

				participacoes.add(participacao);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return participacoes;
	}
}
