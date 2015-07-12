package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.TipoProgramaInstitucional;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class TipoProgramaInstitucionalDAO implements
		GenericDAO<Integer, TipoProgramaInstitucional> {

	static DBPool banco;

	private static TipoProgramaInstitucionalDAO instance;

	public Connection connection;

	public static TipoProgramaInstitucionalDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new TipoProgramaInstitucionalDAO(banco);
		return instance;
	}

	public TipoProgramaInstitucionalDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(TipoProgramaInstitucional tipoProgramaInstitucional)
			throws SQLExceptionQManager {

		int idTipoProgramaInstitucional = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {

			String sql = String
					.format("%s ('%s')",
							"INSERT INTO tb_tipo_programa_institucional "
							+ " (nm_tipo_programa_institucional) VALUES",
							tipoProgramaInstitucional
									.getNomeTipoProgramaInstitucional());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idTipoProgramaInstitucional = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, this.connection);
		}

		return idTipoProgramaInstitucional;
	}

	@Override
	public void update(TipoProgramaInstitucional tipoProgramaInstitucional)
			throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_tipo_programa_institucional SET "
					+ " nm_tipo_programa_institucional = ? "
					+ " WHERE id_tipo_programa_institucional = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, tipoProgramaInstitucional.getNomeTipoProgramaInstitucional());
			stmt.setInt(2, tipoProgramaInstitucional.getIdTipoProgramaInstitucional());

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

			String sql = "DELETE FROM tb_tipo_programa_institucional" 
			+ " WHERE id_tipo_programa_institucional = ?";

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
	public List<TipoProgramaInstitucional> getAll() throws SQLExceptionQManager {

		List<TipoProgramaInstitucional> tiposProgramaInstitucional = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s",
					"SELECT tipo_programa_institucional.id_tipo_programa_institucional,"
					+ " tipo_programa_institucional.nm_tipo_programa_institucional"
					+ " FROM tb_tipo_programa_institucional AS tipo_programa_institucional");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			tiposProgramaInstitucional = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return tiposProgramaInstitucional;

	}

	@Override
	public TipoProgramaInstitucional getById(Integer id) throws SQLExceptionQManager {

		TipoProgramaInstitucional tipoProgramaInstitucional = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d", "SELECT "
							+ " tipo_programa_institucional.id_tipo_programa_institucional, "
							+ " tipo_programa_institucional.nm_tipo_programa_institucional, "
							+ " tipo_programa_institucional.dt_registro "
							+ " FROM tb_tipo_programa_institucional AS tipo_programa_institucional "
							+ " WHERE tipo_programa_institucional.id_tipo_programa_institucional = ", 
							id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<TipoProgramaInstitucional> tiposProgramaInstitucional = convertToList(rs);

			if (!tiposProgramaInstitucional.isEmpty())
				tipoProgramaInstitucional = tiposProgramaInstitucional.get(0);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return tipoProgramaInstitucional;
	}

	public List<TipoProgramaInstitucional> find(
			TipoProgramaInstitucional tipoProgramaInstitucional)
			throws SQLExceptionQManager {

		List<TipoProgramaInstitucional> tiposProgramaInstitucional;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s", 
					"SELECT tipo_programa_institucional.id_tipo_programa_institucional, "
					+ " tipo_programa_institucional.nm_tipo_programa_institucional, "
					+ " tipo_programa_institucional.dt_registro"
					+ " FROM tb_tipo_programa_institucional AS tipo_programa_institucional"
					+ " WHERE tipo_programa_institucional.nm_tipo_programa_institucional LIKE '%" 
					+ tipoProgramaInstitucional.getNomeTipoProgramaInstitucional()
					+ "%'");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			tiposProgramaInstitucional = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return tiposProgramaInstitucional;

	}

	@Override
	public List<TipoProgramaInstitucional> convertToList(ResultSet rs) throws SQLExceptionQManager {

		List<TipoProgramaInstitucional> tiposProgramaInstitucional = new ArrayList<TipoProgramaInstitucional>();

		try {
			while (rs.next()) {
				TipoProgramaInstitucional tipoProgramaInstitucional = new TipoProgramaInstitucional();
				tipoProgramaInstitucional
						.setIdTipoProgramaInstitucional(rs
								.getInt("tipo_programa_institucional.id_tipo_programa_institucional"));
				tipoProgramaInstitucional
						.setNomeTipoProgramaInstitucional(rs
								.getString("tipo_programa_institucional.nm_tipo_programa_institucional"));

				tiposProgramaInstitucional.add(tipoProgramaInstitucional);
			}

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return tiposProgramaInstitucional;
	}
}