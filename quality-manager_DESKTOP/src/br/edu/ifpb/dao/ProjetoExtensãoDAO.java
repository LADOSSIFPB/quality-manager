package br.edu.ifpb.dao;

import java.sql.SQLException;

import br.edu.ifpb.Banco;
import br.edu.ifpb.entidades.ProjetoExtens�o;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ProjetoExtens�oDAO {

	public Connection connection;

	public ProjetoExtens�oDAO(Banco banco) {
		
		this.connection = (Connection) banco.getConnection();	
		
	}
	
	// Adicinando um novo ProjetoExtens�o ao banco
	public void adiciona(ProjetoExtens�o projeto) {

		// Cria um insert, com os atributos, e os valores sem defini��o, apenas
		// com a quantidade de valores a ser inseridos (representado por "?").
		String sql = "INSERT INTO projeto_extens�o (nome_projeto, data_in�cio, data_t�rmino, ano_projeto,"
				+ " processo, registro, ano_N) values (?,?,?,?,?,?,?,?,?,?)";

		try {
			// prepared statement para inser��o
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, projeto.getNomeProjeto());
			stmt.setDate(2, projeto.getDataIn�cio());
			stmt.setDate(3, projeto.getDataT�rmino());
			stmt.setString(4, projeto.getAnoProjeto());
			stmt.setString(5, projeto.getProcesso());
			stmt.setString(6, projeto.getRegistro());
			stmt.setString(7, projeto.getAnoN());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Alterar dados do ProjetoExtens�o, a partir do id_projeto(chave prim�ria).
	public void alterar(ProjetoExtens�o projeto) {

		String sql = "UPDATE projeto_extens�o SET nome_projeto=?, data_in�cio=?, data_t�rmino=?)"
				+ "ano_projeto=?, relat�rio_parcial=?, relat�rio_final=?,"
				+ "processo=?, registro=?, ano_N WHERE id_projeto=?";

		try {
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, projeto.getNomeProjeto());
			stmt.setDate(2, projeto.getDataIn�cio());
			stmt.setDate(3, projeto.getDataT�rmino());
			stmt.setString(4, projeto.getAnoProjeto());
			stmt.setString(5, projeto.getRelat�rioParcial());
			stmt.setString(6, projeto.getRelat�rioFinal());
			stmt.setString(7, projeto.getProcesso());
			stmt.setString(8, projeto.getRegistro());
			stmt.setString(9, projeto.getAnoN());
			stmt.setString(10, projeto.getID_projeto());
			
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	// Deletar ProjetoExtens�o a partir do id_projeto(chave prim�ria)
	public void deletar(ProjetoExtens�o projeto) {
		String sql = "DELETE FROM projeto_extens�o WHERE id_projeto=?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, projeto.getID_projeto());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
