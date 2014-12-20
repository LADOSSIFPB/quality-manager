package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

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
	public int insert(CargoServidor cargoServidor) throws QManagerSQLException {

		int idCargoServidor = 0;

		try {

			// Define um insert com os atributos e cada valor é representado
			// por ?
			String sql = String.format("%s %s ('%s')",
					"INSERT INTO tb_cargo_servidor (nm_cargo_servidor)",
					"VALUES", cargoServidor.getCargoServidor());

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idCargoServidor = BancoUtil.getGenerateKey(stmt);

			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return idCargoServidor;
	}

	@Override
	public void update(CargoServidor cargoServidor) throws QManagerSQLException {

		try {

			String sql = "UPDATE tb_cargo_servidor SET nm_cargo_servidor=? "
					+ "WHERE id_cargo_servidor=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1, cargoServidor.getCargoServidor());
			stmt.setInt(2, cargoServidor.getIdCargoServidor());

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

			// Deleta uma tupla setando o atributo de identificação com
			// valor representado por ?
			String sql = "DELETE FROM tb_cargo_servidor WHERE id_cargo_servidor=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, id);

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public List<CargoServidor> getAll() throws QManagerSQLException {

		List<CargoServidor> cargosServidor;

		try {

			String sql = String.format("%s",
					"SELECT cargo_servidor.id_cargo_servidor, cargo_servidor.nm_cargo_servidor "
							+ "FROM tb_cargo_servidor cargo_servidor");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			cargosServidor = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return cargosServidor;

	}

	@Override
	public CargoServidor getById(Integer id) throws QManagerSQLException {
		CargoServidor cargoServidor = null;

		try {

			String sql = String.format("%s %d",
					"SELECT cargo_servidor.id_cargo_servidor, cargo_servidor.nm_cargo_servidor "
							+ "FROM tb_cargo_servidor cargo_servidor "
							+ "WHERE cargo_servidor.id_cargo_servidor =", id);

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<CargoServidor> cargosServidor = convertToList(rs);

			if (!cargosServidor.isEmpty())
				cargoServidor = cargosServidor.get(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return cargoServidor;
	}

	@Override
	public List<CargoServidor> convertToList(ResultSet rs)
			throws QManagerSQLException {
		List<CargoServidor> cargosServidor = new LinkedList<CargoServidor>();

		try {
			while (rs.next()) {
				CargoServidor cargoServidor = new CargoServidor();
				cargoServidor.setIdCargoServidor(rs
						.getInt("cargo_servidor.id_cargo_servidor"));
				cargoServidor.setCargoServidor(rs
						.getString("cargo_servidor.nm_cargo_servidor"));

				cargosServidor.add(cargoServidor);
			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return cargosServidor;
	}

}
