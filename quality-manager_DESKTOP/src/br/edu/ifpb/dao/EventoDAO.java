package br.edu.ifpb.dao;

import java.sql.SQLException;

import br.edu.ifpb.Banco;
import br.edu.ifpb.entidades.Evento;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

//						Campos da tabela
//id_evento, Nome, Descricao, Localidade, Ano, Inicio_Evento, Fim_Evento, Area_de_Atuacao

public class EventoDAO {
	
	public Connection connection;
	
	public EventoDAO(Banco banco) {
		
		this.connection = (Connection) banco.getConnection();	
		
	}
	
	// Adicinando um novo Evento ao banco
			public void adiciona(Evento evento) {

				// Cria um insert, com os atributos, e os valores sem definição, apenas
				// com a quantidade de valores a ser inseridos (representado por "?").
				String sql = "INSERT INTO evento (Nome, Descricao, Localidade,"
						+ " Ano, Inicio_Evento, Fim_Evento, Area_de_Atuacao)"
						+ " values (?,?,?,?,?,?,?)";

				try {
					// prepared statement para inserção
					PreparedStatement stmt = (PreparedStatement) connection
							.prepareStatement(sql);
					// seta os valores
					stmt.setString(1, evento.getNome());
					stmt.setString(2, evento.getDescricao());
					stmt.setString(3, evento.getLocalidade());
					stmt.setString(4, evento.getAno());
					stmt.setDate(5, evento.getInicio_Evento());
					stmt.setDate(6, evento.getFim_Evento());
					stmt.setString(7, evento.getArea_de_Atuacao());
					
					// executa
					stmt.execute();
					stmt.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}

			// Alterar dados do discente, a partir do CPF(chave primária).
			public void alterar(Evento evento) {

				String sql = "UPDATE evento SET Nome=?, Descricao=?, Localidade=?, Ano=?,"
						+ " Inicio_Evento=?, Fim_Evento=?, Area_de_Atuacao=? WHERE id_evento=?";

				try {
					PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
					stmt.setString(1, evento.getNome());
					stmt.setString(2, evento.getDescricao());
					stmt.setString(3, evento.getLocalidade());
					stmt.setString(4, evento.getAno());
					stmt.setDate(5, evento.getInicio_Evento());
					stmt.setDate(6, evento.getFim_Evento());
					stmt.setString(7, evento.getArea_de_Atuacao());
					stmt.setInt(7, evento.getId_evento());
					
					stmt.execute();
					stmt.close();

				} catch (SQLException e) {
					throw new RuntimeException(e);
				}

			}
			
			// Deletar discente a partir do CPF(chave primária)
			public void deletar(Evento evento) {
				String sql = "DELETE FROM evento WHERE id_evento=?";
				PreparedStatement stmt;
				try {
					stmt = (PreparedStatement) connection.prepareStatement(sql);
					stmt.setInt(1, evento.getId_evento());
					stmt.execute();
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	

}
