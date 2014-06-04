package br.edu.ifpb.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.edu.ifpb.Banco;
import br.edu.ifpb.entidades.Instituição;

import com.mysql.jdbc.PreparedStatement;

public class InstituiçãoDAO {

	// a conexão com o banco de dados
	public Connection connection;
	
	public InstituiçãoDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}
	
	
	
	// Adicinando uma nova instituição ao banco
		public void adiciona(Instituição instituição) {

			// Cria um insert, com os atributos, e os valores sem definição, apenas
			// com a quantidade de valores a ser inseridos (representado por "?").
			String sql = "INSERT INTO instituição (nome_instituição, sigla, orçamento)"
					+ " values (?,?,?)";

			try {
				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);
				// seta os valores
				stmt.setString(1, instituição.getNomeInstituição());
				stmt.setString(2, instituição.getSigla());
				stmt.setDouble(3, instituição.getOrçamento());
				
				// executa
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		// Alterar dados da instituição, a partir do id_instituição(chave primária).
		public void alterar(Instituição instituição) {

			String sql = "UPDATE instituição SET nome_instituição=?, sigla=?, orçamento=?"
					+ " WHERE ID_instituição=?";

			try {
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.setString(1, instituição.getNomeInstituição());
				stmt.setString(2, instituição.getSigla());
				stmt.setDouble(3, instituição.getOrçamento());
				stmt.setInt(4, instituição.getIDInstituição());

				stmt.execute();
				stmt.close();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}
		
		// Deletar Instituição a partir do ID_instituição(chave primária)
		public void deletar(Instituição instituição) {
			String sql = "DELETE FROM instituição WHERE ID_instituição=?";
			PreparedStatement stmt;
			try {
				stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.setInt(1, instituição.getIDInstituição());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

}
