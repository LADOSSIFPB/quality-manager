package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.TipoEdital;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class TipoEditalDAO implements GenericDAO<Integer, TipoEdital> {

	static DBPool banco;

	private static TipoEditalDAO instance;

	public Connection connection;

	public static TipoEditalDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new TipoEditalDAO(banco);
		return instance;
	}

	public TipoEditalDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(TipoEdital tipoEdital) throws SQLExceptionQManager {

		int idTipoEdital = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {

			String sql = String.format("%s ('%s')",
					"INSERT INTO tb_tipo_edital (nm_tipo_edital) VALUES",
					tipoEdital.getNomeTipoEdital());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idTipoEdital = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, this.connection);
		}

		return idTipoEdital;
	}

	@Override
	public void update(TipoEdital tipoEdital) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_tipo_edital SET "
					+ " nm_tipo_edital = ? "
					+ " WHERE id_tipo_edital = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, tipoEdital.getNomeTipoEdital());
			stmt.setInt(2, tipoEdital.getIdTipoEdital());

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

			String sql = "DELETE FROM tb_tipo_edital" + " WHERE id_tipo_edital = ?";

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
	public List<TipoEdital> getAll() throws SQLExceptionQManager {

		List<TipoEdital> tipoProjeto = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s",
					"SELECT tipo_edital.id_tipo_edital,"
					+ " tipo_edital.nm_tipo_edital"
					+ " FROM tb_tipo_edital AS tipo_edital");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			tipoProjeto = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return tipoProjeto;

	}

	@Override
	public TipoEdital getById(Integer id) throws SQLExceptionQManager {

		TipoEdital tipoProjeto = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d", "SELECT tipo_edital.id_tipo_edital, "
							+ " tipo_edital.nm_tipo_edital, "
							+ " tipo_edital.dt_registro "
							+ " FROM tb_tipo_edital AS tipo_edital "
							+ " WHERE tipo_edital.id_tipo_edital = ", id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<TipoEdital> tiposProjeto = convertToList(rs);

			if (!tiposProjeto.isEmpty())
				tipoProjeto = tiposProjeto.get(0);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return tipoProjeto;
	}

	public List<TipoEdital> find(TipoEdital tipoProjeto) throws SQLExceptionQManager {

		List<TipoEdital> tiposProjeto;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s", 
					"SELECT tipo_edital.id_tipo_edital, "
					+ " tipo_edital.nm_tipo_edital, "
					+ " tipo_edital.dt_registro"
					+ " FROM tb_tipo_edital AS tipo_edital"
					+ " WHERE tipo_edital.nm_tipo_edital LIKE '%" 
					+ tipoProjeto.getNomeTipoEdital()
					+ "%'");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			tiposProjeto = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return tiposProjeto;

	}

	@Override
	public List<TipoEdital> convertToList(ResultSet rs) throws SQLExceptionQManager {

		List<TipoEdital> tiposEdital = new ArrayList<TipoEdital>();

		try {
			while (rs.next()) {
				TipoEdital tipoEdital = new TipoEdital();
				tipoEdital.setIdTipoEdital(rs.getInt("tipo_edital.id_tipo_edital"));
				tipoEdital.setNomeTipoEdital(rs.getString("tipo_edital.nm_tipo_edital"));

				tiposEdital.add(tipoEdital);
			}

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return tiposEdital;
	}
}