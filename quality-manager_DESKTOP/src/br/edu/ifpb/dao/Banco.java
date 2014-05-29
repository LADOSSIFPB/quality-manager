/**
 * 
 */
package br.edu.ifpb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class Banco {
	
	private Connection connection = null;
	private PreparedStatement stm = null;
	
	
	//Estabelecer conexão com o banco de dados	
	public void iniciarConexao(String usuario, String senha){
		
		System.out.println("Estabelecendo conexão...");
		
		//
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found !!");
			return;
		}
		
		System.out.println("MySQL JDBC Driver Registrado!");
		
		//Tenta esabelecer conexão com o banco específico
		try {
			this.connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/controle_projetos", usuario, senha);
			System.out.println("Conexão estabelecida com o banco de dados SQL!");

		} catch (SQLException e) {
			System.out.println("Não foi possivel estabelecer conexão.");
			return;
		}
		
	}

	//Encerrar conexão com banco de dados
	public void encerrarConexao(){
		
		try {
			if(this.stm!= null)
				this.stm.close();
			if (this.connection != null)
				this.connection.close();
				System.out.println("Conexão encerrada!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	
	
	
}
