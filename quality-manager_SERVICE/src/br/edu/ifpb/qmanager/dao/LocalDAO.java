package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Local;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class LocalDAO implements GenericDAO<Integer, Local> {

	static DBPool banco;
	private static LocalDAO instance;

	public static LocalDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new LocalDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public LocalDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Local local) throws SQLExceptionQManager {

		int idCurso = 0;

		try {

			String sql = String.format("%s ('%s')",
					"INSERT INTO tb_local (nm_local) VALUES",
					local.getNomeLocal());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idCurso = BancoUtil.getGenerateKey(stmt);

			stmt.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return idCurso;
	}

	@Override
	public void update(Local local) throws SQLExceptionQManager {

		try {

			String sql = "UPDATE tb_local SET nm_local=? WHERE id_local=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1, local.getNomeLocal());
			stmt.setInt(2, local.getIdLocal());

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

			String sql = "DELETE FROM tb_local WHERE id_local=?";

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
	public List<Local> getAll() throws SQLExceptionQManager {

		List<Local> locais = null;

		try {

			String sql = String
					.format("%s",
							"SELECT local.id_local, local.nm_local, local.dt_registro FROM tb_local local");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			locais = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return locais;

	}

	@Override
	public Local getById(Integer id) throws SQLExceptionQManager {

		Local local = null;

		try {

			String sql = String.format("%s %d",
					"SELECT local.id_local, local.nm_local, local.dt_registro FROM tb_local local"
							+ " WHERE local.id_local =", id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Local> locais = convertToList(rs);

			if (!locais.isEmpty())
				local = locais.get(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return local;

	}

	@Override
	public List<Local> find(Local local) throws SQLExceptionQManager {

		List<Local> locais = null;

		try {

			String sql = String.format("%s '%%%s%%'",
					"SELECT local.id_local, local.nm_local, local.dt_registro FROM tb_local local"
							+ " WHERE local.nm_local LIKE",
					local.getNomeLocal());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			locais = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return locais;

	}

	@Override
	public List<Local> convertToList(ResultSet rs) throws SQLExceptionQManager {

		List<Local> locais = new LinkedList<Local>();

		try {
			while (rs.next()) {
				Local local = new Local();
				local.setIdLocal(rs.getInt("local.id_local"));
				local.setNomeLocal(rs.getString("local.nm_local"));
				local.setRegistro(rs.getDate("local.dt_registro"));

				locais.add(local);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return locais;
	}

}