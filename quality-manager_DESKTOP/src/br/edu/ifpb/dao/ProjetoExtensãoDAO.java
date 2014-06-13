package br.edu.ifpb.dao;

import java.sql.SQLException;

import br.edu.ifpb.Banco;
import br.edu.ifpb.entidades.Entidade;
import br.edu.ifpb.entidades.ProjetoExtensão;
import br.edu.ifpb.exceções.ClasseInválidaException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

// Campos da tabela
// ID_projeto, nome_projeto, data_início, data_término, ano_projeto, relatório_final, relatório_parcial, processo, registro, ano_N

public class ProjetoExtensãoDAO implements DAO {

	public Connection connection;

	public ProjetoExtensãoDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}

	@Override
	public void creat(Entidade entidade) throws ClasseInválidaException {
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.ProjetoExtensão")) {
			ProjetoExtensão projeto = (ProjetoExtensão) entidade;
			
			// Cria um insert, com os atributos, e os valores sem definição, apenas
			// com a quantidade de valores a ser inseridos (representado por "?").
			String sql = "INSERT INTO projeto_extensão (nome_projeto, data_início, data_término, ano_projeto,"
					+ " processo, registro, ano_N) values (?,?,?,?,?,?,?,?,?,?)";
			
			try {
				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);
				// seta os valores
				stmt.setString(1, projeto.getNomeProjeto());
				stmt.setDate(2, projeto.getDataInício());
				stmt.setDate(3, projeto.getDataTérmino());
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
		else {
			throw new ClasseInválidaException();
		}
	}

	@Override
	public void read(Entidade entidade) throws ClasseInválidaException {
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.ProjetoExtensão")) {
			
		}
		else {
			throw new ClasseInválidaException();
		}
	}

	@Override
	public void update(Entidade entidade) throws ClasseInválidaException {
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.ProjetoExtensão")) {
			ProjetoExtensão projeto = (ProjetoExtensão) entidade;
			
			String sql = "UPDATE projeto_extensão SET nome_projeto=?, data_início=?, data_término=?)"
					+ "ano_projeto=?, relatório_parcial=?, relatório_final=?,"
					+ "processo=?, registro=?, ano_N WHERE id_projeto=?";

			try {
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.setString(1, projeto.getNomeProjeto());
				stmt.setDate(2, projeto.getDataInício());
				stmt.setDate(3, projeto.getDataTérmino());
				stmt.setString(4, projeto.getAnoProjeto());
				stmt.setString(5, projeto.getRelatórioParcial());
				stmt.setString(6, projeto.getRelatórioFinal());
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
		else {
			throw new ClasseInválidaException();
		}
	}

	@Override
	public void delete(Entidade entidade) throws ClasseInválidaException {
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.ProjetoExtensão")) {
			ProjetoExtensão projeto = (ProjetoExtensão) entidade;
			
			String sql = "DELETE FROM projeto_extensão WHERE id_projeto=?";
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
		else {
			throw new ClasseInválidaException();
		}
	}

}
