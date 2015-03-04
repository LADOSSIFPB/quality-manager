package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class CampusDAO implements GenericDAO<Integer, Campus> {

	static DBPool banco;
	
	public Connection connection;
	
	private static CampusDAO instance;	

	public static CampusDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new CampusDAO(banco);
		}
		return instance;
	}

	public CampusDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Campus campus) throws SQLExceptionQManager {

		int idCampus = BancoUtil.IDVAZIO;

		try {

			String sql = String.format("%s ('%s')",
					"INSERT INTO tb_campus_institucional (nm_campus_institucional) VALUES",
					campus.getNome());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idCampus = BancoUtil.getGenerateKey(stmt);

			stmt.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return idCampus;
	}

	@Override
	public void update(Campus campus) throws SQLExceptionQManager {

		try {

			String sql = "UPDATE tb_campus_institucional"
					+ " SET nm_campus_institucional = ?"
					+ " WHERE id_campus_institucional = ?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1, campus.getNome());
			stmt.setInt(2, campus.getIdCampusInstitucional());

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

			String sql = "DELETE FROM tb_campus_institucional"
					+ " WHERE id_campus_institucional = ?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public List<Campus> getAll() throws SQLExceptionQManager {

		List<Campus> campi = null;

		try {

			String sql = String
					.format("%s",
							"SELECT campus.id_campus_institucional,"
							+ " campus.nm_campus_institucional,"
							+ " campus.dt_registro"
							+ " FROM tb_campus_institucional campus");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			campi = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return campi;
	}

	@Override
	public Campus getById(Integer id) throws SQLExceptionQManager {

		Campus campus = null;

		try {

			String sql = String.format("%s %d",
					"SELECT campus.id_campus_institucional,"
					+ " campus.nm_campus_institucional,"
					+ " campus.dt_registro"
					+ " FROM tb_campus_institucional campus"
					+ " WHERE campus.id_campus_institucional = ", id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Campus> campi = convertToList(rs);

			if (!campi.isEmpty())
				campus = campi.get(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return campus;
	}

	@Override
	public List<Campus> find(Campus campus) throws SQLExceptionQManager {

		List<Campus> campi = null;

		try {

			String sql = String.format("%s '%%%s%%'",
					"SELECT campus.id_campus_institucional,"
					+ " campus.nm_campus_institucional,"
					+ " campus.dt_registro"
					+ " FROM tb_campus_institucional campus"
					+ " WHERE campus.nm_campus_institucional LIKE",
					campus.getNome());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			campi = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return campi;
	}

	@Override
	public List<Campus> convertToList(ResultSet rs) throws SQLExceptionQManager {

		List<Campus> campi = new ArrayList<Campus>();

		try {
			while (rs.next()) {
				Campus campus = new Campus();
				campus.setIdCampusInstitucional(rs.getInt("campus.id_campus_institucional"));
				campus.setNome(rs.getString("campus.nm_campus_institucional"));
				campus.setRegistro(rs.getDate("campus.dt_registro"));

				campi.add(campus);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return campi;
	}
}