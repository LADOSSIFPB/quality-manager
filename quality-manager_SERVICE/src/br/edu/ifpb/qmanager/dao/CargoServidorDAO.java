package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class CargoServidorDAO implements GenericDAO<Integer, CargoServidor> {

	static DBPool banco;
	private static CargoServidorDAO instance;

	public static CargoServidorDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new CargoServidorDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public CargoServidorDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(CargoServidor cargoServidor) throws SQLExceptionQManager {

		int idCargoServidor = 0;

		PreparedStatement stmt = null;

		try {

			String sql = String.format("%s %s ('%s')",
					"INSERT INTO tb_cargo_servidor (nm_cargo_servidor)",
					"VALUES", cargoServidor.getNomeCargoServidor());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idCargoServidor = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.closeQuery(stmt);
		}

		return idCargoServidor;

	}

	@Override
	public void update(CargoServidor cargoServidor) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_cargo_servidor SET nm_cargo_servidor=? "
					+ "WHERE id_cargo_servidor=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, cargoServidor.getNomeCargoServidor());
			stmt.setInt(2, cargoServidor.getIdCargoServidor());

			stmt.execute();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.closeQuery(stmt);
		}

	}

	@Override
	public void delete(Integer id) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "DELETE FROM tb_cargo_servidor WHERE id_cargo_servidor=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.closeQuery(stmt);
		}

	}

	@Override
	public List<CargoServidor> getAll() throws SQLExceptionQManager {

		List<CargoServidor> cargosServidor;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s",
					"SELECT cargo_servidor.id_cargo_servidor, cargo_servidor.nm_cargo_servidor, "
							+ "cargo_servidor.dt_registro "
							+ "FROM tb_cargo_servidor cargo_servidor");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			cargosServidor = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.closeQuery(stmt, rs);
		}

		return cargosServidor;

	}

	@Override
	public CargoServidor getById(Integer id) throws SQLExceptionQManager {
		CargoServidor cargoServidor = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d",
					"SELECT cargo_servidor.id_cargo_servidor, cargo_servidor.nm_cargo_servidor, "
							+ "cargo_servidor.dt_registro "
							+ "FROM tb_cargo_servidor cargo_servidor "
							+ "WHERE cargo_servidor.id_cargo_servidor =", id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<CargoServidor> cargosServidor = convertToList(rs);

			if (!cargosServidor.isEmpty())
				cargoServidor = cargosServidor.get(0);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.closeQuery(stmt, rs);
		}

		return cargoServidor;
	}

	@Override
	public List<CargoServidor> find(CargoServidor cargoServidor)
			throws SQLExceptionQManager {
		List<CargoServidor> cargosServidor = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %s",
					"SELECT cargo_servidor.id_cargo_servidor, cargo_servidor.nm_cargo_servidor, "
							+ "cargo_servidor.dt_registro "
							+ "FROM tb_cargo_servidor cargo_servidor "
							+ "WHERE cargo_servidor.nm_cargo_servidor =",
					cargoServidor.getNomeCargoServidor());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			cargosServidor = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.closeQuery(stmt, rs);
		}

		return cargosServidor;

	}

	@Override
	public List<CargoServidor> convertToList(ResultSet rs)
			throws SQLExceptionQManager {
		List<CargoServidor> cargosServidor = new LinkedList<CargoServidor>();

		try {
			while (rs.next()) {
				CargoServidor cargoServidor = new CargoServidor();
				cargoServidor.setIdCargoServidor(rs
						.getInt("cargo_servidor.id_cargo_servidor"));
				cargoServidor.setNomeCargoServidor(rs
						.getString("cargo_servidor.nm_cargo_servidor"));
				cargoServidor.setRegistro(rs
						.getDate("cargo_servidor.dt_registro"));

				cargosServidor.add(cargoServidor);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return cargosServidor;

	}

}
