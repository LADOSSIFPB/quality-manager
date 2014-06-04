package br.edu.ifpb.dao;

import java.sql.SQLException;

import br.edu.ifpb.Banco;
import br.edu.ifpb.entidades.ProgramaInstitucionalPesquisa;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

//Campos da tabela
//ID_PIP, sigla_PIP, nome_PIP, instituição_ID

//PIP = Programa Institucional de Pesquisa

public class ProgramaInstitucionalPesquisaDAO {
	
	public Connection connection;

	public ProgramaInstitucionalPesquisaDAO(Banco banco) {
		
		this.connection = (Connection) banco.getConnection();	
		
	}
	
	
	// Adicinando um novo ProgramaInstitucionalPesquisa ao banco
		public void adiciona(ProgramaInstitucionalPesquisa pip) {

			// Cria um insert, com os atributos, e os valores sem definição, apenas
			// com a quantidade de valores a ser inseridos (representado por "?").
			String sql = "INSERT INTO pip (sigla_PIP, nome_PIP, instituição_ID)"
					+ " values (?,?,?)";

			try {
				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);
				// seta os valores
				stmt.setString(1, pip.getSiglaPIP());
				stmt.setString(2, pip.getNomePIP());
				stmt.setInt(3, pip.getInstituiçãoID());
				
				// executa
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		// Alterar dados do ProgramaInstitucionalPesquisa, a partir do ID_PIP(chave primária).
		public void alterar(ProgramaInstitucionalPesquisa pip) {

			String sql = "UPDATE pip SET sigla_PIP=?, nome_PIP=?, instituição_ID=?)"
					+ " WHERE ID_PIP=?";

			try {
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.setString(1, pip.getSiglaPIP());
				stmt.setString(2, pip.getNomePIP());
				stmt.setInt(3, pip.getInstituiçãoID());
				stmt.setInt(4, pip.getID_PIP());
				
				stmt.execute();
				stmt.close();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}
		
		// Deletar ProgramaInstitucionalPesquisa a partir do ID_PIP(chave primária)
		public void deletar(ProgramaInstitucionalPesquisa pip) {
			String sql = "DELETE FROM pip WHERE ID_PIP=?";
			PreparedStatement stmt;
			try {
				stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.setInt(1, pip.getID_PIP());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

}
