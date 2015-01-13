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

	public static TipoParticipacaoDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new TipoParticipacaoDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public TipoParticipacaoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(TipoParticipacao tipoParticipacao)
			throws SQLExceptionQManager {

		int chave = 0;

		String sql = String.format("%s %s('%s')",
				"INSERT INTO tb_tipo_pessoa(nm_tipo)", "VALUES",
				tipoParticipacao.getNomeTipoParticipacao());

		try {

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			chave = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return chave;
	}

	@Override
	public void update(TipoParticipacao tipoParticipacao)
			throws SQLExceptionQManager {

		try {

			String sql = "UPDATE tb_tipo_participacao SET nm_tipo_participacao=?"
					+ " WHERE id_tipo_participacao=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1, tipoParticipacao.getNomeTipoParticipacao());
			stmt.setInt(2, tipoParticipacao.getIdTipoParticipacao());

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public void delete(Integer id) throws SQLExceptionQManager {

		try {

			String sql = "DELETE FROM tb_tipo_participacao WHERE id_tipo_participacao=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public List<TipoParticipacao> getAll() throws SQLExceptionQManager {

		List<TipoParticipacao> tiposParticipacao;

		try {

			String sql = String.format("%s",
					"SELECT id_tipo_participacao, nm_tipo_participacao "
							+ "FROM tb_tipo_participacao");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			tiposParticipacao = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return tiposParticipacao;

	}

	@Override
	public TipoParticipacao getById(Integer id) throws SQLExceptionQManager {
		TipoParticipacao tipoParticipacao = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT id_tipo_participacao, nm_tipo_participacao "
									+ "FROM tb_tipo_participacao WHERE id_tipo_participacao =",
							id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<TipoParticipacao> tiposParticipacao = convertToList(rs);

			if (!tiposParticipacao.isEmpty())
				tipoParticipacao = tiposParticipacao.get(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return tipoParticipacao;

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
