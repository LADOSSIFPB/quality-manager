package br.edu.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDBPool {

	private DBPool dbPool;
	private Connection connection;
	private Statement statement;

	public TestDBPool() {
		try {
			// Get Connection and Statement
			dbPool = DBPool.getInstance();
			connection = dbPool.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void consultar() {
		
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM tb_usuario";
            resultSet = statement.executeQuery(query);
            ResultSetMetaData rsmetadata = resultSet.getMetaData();
            
            while(resultSet.next()) {
            	System.out.println("Id: " + resultSet.getInt("idUsuario"));
            	System.out.println("Nome: " + resultSet.getString("nome_Usuario"));
            	System.out.println(" ---------------------------------- ");
            }
            
            resultSet.close();
            statement.close();
            connection.close();
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TestDBPool teste = new TestDBPool();
		teste.consultar();
	}
}
