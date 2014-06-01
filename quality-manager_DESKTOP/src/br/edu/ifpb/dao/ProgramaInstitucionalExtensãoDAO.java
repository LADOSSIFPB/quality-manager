package br.edu.ifpb.dao;

import java.sql.SQLException;

import br.edu.ifpb.Banco;
import br.edu.ifpb.entidades.ProgramaInstitucionalExtensão;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

//Campos da tabela
//Id_programa, SiglaPIP, NomePIP, Instituição_idInstituição

//PIP = Programa Institucional de Pesquisa
public class ProgramaInstitucionalExtensãoDAO {
	
	public Connection connection;

	public ProgramaInstitucionalExtensãoDAO(Banco banco) {
		
		this.connection = (Connection) banco.getConnection();	
		
	}
	
	// Adicinando um novo Evento ao banco
	public void adiciona(ProgramaInstitucionalExtensão pie) {

		// Cria um insert, com os atributos, e os valores sem definição, apenas
		// com a quantidade de valores a ser inseridos (representado por "?").
		String sql = "INSERT INTO pie (siglaPIP, nomePIP, instituição_ID,"
				+ " values (?,?,?,?,?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, pie.getSiglaPIE());
			stmt.setString(2, pie.getNomePIE());
			stmt.setInt(3, pie.getInstituição_ID());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Alterar dados do discente, a partir do CPF(chave primária).
	public void alterar(ProgramaInstitucionalExtensão pie) {

		String sql = "UPDATE pie SET siglaPIP=?, nomePIP=?, instituição_ID=?)"
				+ " WHERE ID_PIE=?";

		try {
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, pie.getSiglaPIE());
			stmt.setString(2, pie.getNomePIE());
			stmt.setInt(3, pie.getInstituição_ID());
			stmt.setInt(4, pie.getID_PIE());
			
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	// Deletar discente a partir do CPF(chave primária)
	public void deletar(ProgramaInstitucionalExtensão pie) {
		String sql = "DELETE FROM pie WHERE ID_PIE=?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, pie.getID_PIE());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
