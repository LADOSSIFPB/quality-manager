package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.PreparedStatement;

public class DatabaseConnection {

	private Connection connection = null;
	private PreparedStatement stmt = null;

	/**
	 * Estabelecer conexão com o banco de dados
	 * 
	 * @author Felipe Nascimento
	 * @param usuario
	 * @param senha
	 * @throws QManagerSQLException 
	 */
	public void iniciarConexao() throws QManagerSQLException {

		Logger.getLogger(DatabaseConnection.class.getName()).log(Level.INFO,
				"Estabelecendo conexão...");

		try {

			Class.forName("com.mysql.jdbc.Driver");  
			
			Logger.getLogger(DatabaseConnection.class.getName()).log(
					Level.INFO, "MySQL JDBC Driver Registrado!");

			// Tenta estabelecer conexão com o banco de dados
			this.connection = DriverManager.getConnection(
					Constantes.URL_DATABASE, Constantes.USER_DATABASE, Constantes.PASS_DATABASE);
			
			Logger.getLogger(DatabaseConnection.class.getName()).log(
					Level.INFO,
					"Conexão estabelecida com o banco de dados SQL!");
		
		} catch (SQLException e) {
			Logger.getLogger(DatabaseConnection.class.getName()).log(
					Level.SEVERE,
					"Não foi possivel estabelecer conexão com banco de dados "
							+ Constantes.URL_DATABASE);
			throw new QManagerSQLException(e.getErrorCode(), e.getMessage());
			
		} catch (ClassNotFoundException e) {
			Logger.getLogger(DatabaseConnection.class.getName()).log(
					Level.SEVERE,
					"Não foi possivel estabelecer conexão com banco de dados "
							+ Constantes.URL_DATABASE);
			throw new QManagerSQLException(0, e.getMessage());
		}
	}

	/**
	 * Encerrar conexão com banco de dados
	 * 
	 * @author Felipe Nascimento
	 */
	public void encerrarConexao() {
		
		try {
			
			if (this.stmt != null)
				this.stmt.close();
			if (this.connection != null)
				this.connection.close();

			Logger.getLogger(DatabaseConnection.class.getName()).log(
					Level.INFO, "Conexão encerrada!");

		} catch (SQLException sqle) {
			Logger.getLogger(DatabaseConnection.class.getName()).log(
					Level.SEVERE, "Erro ao encerrar conexão!");
		}
	}

	public Connection getConnection() {
		return connection;
	}
}
