package br.edu.ifpb.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.edu.ifpb.entidades.Docente;

import com.mysql.jdbc.PreparedStatement;

// CAMPOS DA TABELA
// CPF, Matricula, Nome, Cargo, Endereco, CEP, Telefone, E_mail, Titulação, Local_de_Trabalho

public class DocenteDAO {
	// a conexão com o banco de dados
	public Connection connection;

	public DocenteDAO(Banco b) {
		this.connection = (Connection) b.getConnection();
	}

	// Adicinando um novo docente ao banco
	public void adiciona(Docente docente) {

		// Cria um insert, com os atributos, e os valores sem definição, apenas
		// com a quantidade de valores a ser inseridos (representado por "?").
		String sql = "INSERT INTO docente (CPF, Matricula, Nome, Cargo, Endereco, CEP, Telefone, E_mail, Titulação, Local_de_Trabalho) values (?,?,?,?,?,?,?,?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, docente.getCPF());
			stmt.setString(2, docente.getMatricula());
			stmt.setString(3, docente.getNome());
			stmt.setString(4, docente.getCargo());
			stmt.setString(5, docente.getEndereco());
			stmt.setString(6, docente.getCEP());
			stmt.setString(7, docente.getTelefone());
			stmt.setString(8, docente.getE_mail());
			stmt.setString(9, docente.getTitulação());
			stmt.setString(10, docente.getLocal_de_Trabalho());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Alterar dados do discente, a partir do CPF(chave primária).
	public void alterar(Docente docente) {

		String sql = "UPDATE docente SET Matricula=?, Nome=?, Cargo=?, Endereco=?, CEP=?, Telefone=?, E_mail=?, Titulação=?, Local_de_Trabalho=? WHERE CPF=?";

		try {
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, docente.getMatricula());
			stmt.setString(2, docente.getNome());
			stmt.setString(3, docente.getCargo());
			stmt.setString(4, docente.getEndereco());
			stmt.setString(5, docente.getCEP());
			stmt.setString(6, docente.getTelefone());
			stmt.setString(7, docente.getE_mail());
			stmt.setString(8, docente.getTitulação());
			stmt.setString(9, docente.getLocal_de_Trabalho());
			stmt.setString(10, docente.getCPF());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	// Deletar discente a partir do CPF(chave primária)
	public void deletar(Docente docente) {
		String sql = "DELETE FROM docente WHERE CPF=?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, docente.getCPF());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
