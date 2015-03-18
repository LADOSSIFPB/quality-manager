package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.TipoParticipacao;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class TipoParticipacaoDAO implements
		GenericDAO<Integer, TipoParticipacao> {

	static DBPool banco;
	
	private static TipoParticipacaoDAO instance;
	
	public Connection connection;

	public static TipoParticipacaoDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new TipoParticipacaoDAO(banco);
		return instance;
	}	

	public TipoParticipacaoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(TipoParticipacao tipoParticipacao)
			throws SQLExceptionQManager {

		int chave = 0;

		PreparedStatement stmt = null;

		try {

			String sql = String.format("%s %s('%s')",
					"INSERT INTO tb_tipo_pessoa(nm_tipo)", "VALUES",
					tipoParticipacao.getNomeTipoParticipacao());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			chave = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {
			
			banco.close(stmt, this.connection);
		}

		return chave;
	}

	@Override
	public void update(TipoParticipacao tipoParticipacao)
			throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_tipo_participacao SET nm_tipo_participacao=?"
					+ " WHERE id_tipo_participacao=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, tipoParticipacao.getNomeTipoParticipacao());
			stmt.setInt(2, tipoParticipacao.getIdTipoParticipacao());

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

			String sql = "DELETE FROM tb_tipo_participacao WHERE id_tipo_participacao=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {
			
			banco.close(stmt, this.connection);
		}
	}

	@Override
	public List<TipoParticipacao> getAll() throws SQLExceptionQManager {

		List<TipoParticipacao> tiposParticipacao;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s",
					"SELECT id_tipo_participacao, nm_tipo_participacao "
							+ "FROM tb_tipo_participacao");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			tiposParticipacao = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {
			
			banco.close(stmt, rs, this.connection);
		}

		return tiposParticipacao;
	}

	@Override
	public TipoParticipacao getById(Integer id) throws SQLExceptionQManager {

		TipoParticipacao tipoParticipacao = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT id_tipo_participacao, nm_tipo_participacao "
									+ "FROM tb_tipo_participacao WHERE id_tipo_participacao =",
							id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<TipoParticipacao> tiposParticipacao = convertToList(rs);

			if (!tiposParticipacao.isEmpty())
				tipoParticipacao = tiposParticipacao.get(0);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {
			
			banco.close(stmt, rs, this.connection);
		}

		return tipoParticipacao;
	}

	@Override
	public List<TipoParticipacao> find(TipoParticipacao tipoParticipacao)
			throws SQLExceptionQManager {

		List<TipoParticipacao> tiposParticipacoes = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s '%%%s%%'",
							"SELECT id_tipo_participacao, nm_tipo_participacao "
									+ "FROM tb_tipo_participacao WHERE nm_tipo_participacao LIKE",
							tipoParticipacao.getNomeTipoParticipacao());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<TipoParticipacao> tiposParticipacao = convertToList(rs);

			if (!tiposParticipacao.isEmpty())
				tipoParticipacao = tiposParticipacao.get(0);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {
			
			banco.close(stmt, rs, this.connection);
		}

		return tiposParticipacoes;
	}

	@Override
	public List<TipoParticipacao> convertToList(ResultSet rs)
			throws SQLExceptionQManager {
		List<TipoParticipacao> tiposParticipacao = new LinkedList<TipoParticipacao>();

		try {

			while (rs.next()) {
				TipoParticipacao tipoParticipacao = new TipoParticipacao();
				tipoParticipacao.setIdTipoParticipacao(rs
						.getInt("id_tipo_participacao"));
				tipoParticipacao.setNomeTipoParticipacao(rs
						.getString("nm_tipo_participacao"));

				tiposParticipacao.add(tipoParticipacao);
			}

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		}

		return tiposParticipacao;
	}
}
