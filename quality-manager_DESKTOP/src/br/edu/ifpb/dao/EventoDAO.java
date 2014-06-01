package br.edu.ifpb.dao;

import java.sql.SQLException;

import br.edu.ifpb.Banco;
import br.edu.ifpb.entidades.Evento;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

// Campos da tabela
// ID_evento, nome, descrição, localidade, ano, início_evento, fim_evento, área_atuação

public class EventoDAO {
	
	public Connection connection;
	
	public EventoDAO(Banco banco) {
		
		this.connection = (Connection) banco.getConnection();	
		
	}
	
	// Adicinando um novo Evento ao banco
	public void adiciona(Evento evento) {

		// Cria um insert, com os atributos, e os valores sem definição, apenas
		// com a quantidade de valores a ser inseridos (representado por "?").
		String sql = "INSERT INTO evento (nome, descrição, localidade,"
				+ " ano, início_evento, fim_evento, área_atuação)"
				+ " values (?,?,?,?,?,?,?)";
	
		try {
			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, evento.getNome());
			stmt.setString(2, evento.getDescrição());
			stmt.setString(3, evento.getLocalidade());
			stmt.setString(4, evento.getAno());
			stmt.setDate(5, evento.getInícioEvento());
			stmt.setDate(6, evento.getFimEvento());
			stmt.setString(7, evento.getÁreaAtuação());
			
			// executa
			stmt.execute();
			stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	// Alterar dados do discente, a partir do CPF(chave primária).
	public void alterar(Evento evento) {
	
		String sql = "UPDATE evento SET nome=?, descrição=?, localidade=?, ano=?,"
				+ " início_evento=?, fim_evento=?, área_atuacao=? WHERE id_evento=?";
	
		try {
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, evento.getNome());
			stmt.setString(2, evento.getDescrição());
			stmt.setString(3, evento.getLocalidade());
			stmt.setString(4, evento.getAno());
			stmt.setDate(5, evento.getInícioEvento());
			stmt.setDate(6, evento.getFimEvento());
			stmt.setString(7, evento.getÁreaAtuação());
			stmt.setInt(7, evento.getIDEvento());
			
			stmt.execute();
			stmt.close();
	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	}

	// Deletar Evento a partir do ID_evento(chave primária)
	public void deletar(Evento evento) {
		String sql = "DELETE FROM evento WHERE ID_evento=?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, evento.getIDEvento());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

}
