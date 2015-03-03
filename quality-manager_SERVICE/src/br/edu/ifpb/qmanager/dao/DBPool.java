package br.edu.ifpb.qmanager.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import snaq.db.ConnectionPoolManager;

/**
 * DBPool class file will be used to get new Database connection with the help
 * of ConnectionPoolManager.
 * 
 * It will fetch all the DB Connection properties from DBPool.properties file
 * and it will create new connection.
 */
public class DBPool {

	protected Connection conn;
	protected ConnectionPoolManager connManager;
	private static DBPool dbPool;

	private Logger logger = LogManager.getLogger(DBPool.class);

	// Name of the database connection name from DBPool.properties file.
	// static final String databaseName = "pool-mysql";
	static final String databaseName = "pool-qmanager";

	/**
	 * Class constructor creates ConnectionPoolManager object
	 * 
	 * @exception properties
	 *                file not found.
	 */
	public DBPool() {
		try {

			connManager = ConnectionPoolManager
					.getInstance("DBPool.properties");

			ConnectionPoolManager.registerGlobalShutdownHook();
			
			logger.info("Connection Manager: " + connManager.getName());

		} catch (IOException ex) {
			logger.error("Error While Connecting with DBPool Properties file :=> "
					+ ex.toString());
		}
	}

	/**
	 * Creates/Provides the instance of the Pool.
	 * 
	 * @return DBPool
	 */
	public static DBPool getInstance() {
		if (dbPool == null)
			dbPool = new DBPool();
		return dbPool;
	}

	/**
	 * Sets the connection object.
	 * 
	 * @exception cannot
	 *                get connection.
	 */
	public Connection getConn() {

		Connection con = null;

		try {
			con = connManager.getConnection(databaseName);
			logger.info("Connection Created: " + con.toString());

			if (con != null) {
				this.conn = con;
				logger.info("Connection Released: " + this.conn.getCatalog() +" Timeout: "+ this.conn.getNetworkTimeout());
			}

		} catch (SQLException ex) {
			logger.error("Error While Creating Connection: " + ex.toString());
		}

		return con;
	}
	
	public void closeQuery(PreparedStatement stmt, ResultSet rs) {
		
		try {
			// Fechar o statement
			if (stmt != null) {
				stmt.close();
			}
			
			// Fechar o resultset
			if (rs != null) {
				rs.close();
			}			
		} catch (SQLException e) {
			logger.error("Problema ao fechar a consulta: statement e resultset.");
			e.printStackTrace();
		}		
	}
	
	public void closeQuery(PreparedStatement stmt) {
		
		try {
			if (stmt != null) {
				stmt.close();
			}			
		} catch (SQLException e) {
			logger.error("Problema ao fechar a consulta: statement.");
		}		
	}
}