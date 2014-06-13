package br.edu.ifpb.dao;

import java.sql.SQLException;

import br.edu.ifpb.Banco;
import br.edu.ifpb.entidades.Entidade;
import br.edu.ifpb.entidades.ProgramaInstitucionalExtensão;
import br.edu.ifpb.exceções.ClasseInválidaException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

// Campos da tabela
// ID_PIE, sigla_PIE, nome_PIE, instituição_ID

// PIE = Programa Institucional de Extensão

public class ProgramaInstitucionalExtensãoDAO implements DAO {
	
	public Connection connection;

	public ProgramaInstitucionalExtensãoDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();			
	}

	@Override
	public void creat(Entidade entidade) throws ClasseInválidaException {
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.ProgramaInstitucionalExtensão")) {
			ProgramaInstitucionalExtensão pie = (ProgramaInstitucionalExtensão) entidade;
			
			// Cria um insert com os atributos e os valores sem definição, apenas
			// com a quantidade de valores a ser inseridos (representado por "?").
			String sql = "INSERT INTO pie (sigla_PIE, nome_PIE, instituição_ID)"
					+ " values (?,?,?)";

			try {
				// prepara para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);
				// seta os valores
				stmt.setString(1, pie.getSiglaPIE());
				stmt.setString(2, pie.getNomePIE());
				stmt.setInt(3, pie.getInstituiçãoID());
				
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
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.ProgramaInstitucionalExtensão")) {
			
		}
		else {
			throw new ClasseInválidaException();
		}
	}

	@Override
	public void update(Entidade entidade) throws ClasseInválidaException {
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.ProgramaInstitucionalExtensão")) {
			ProgramaInstitucionalExtensão pie = (ProgramaInstitucionalExtensão) entidade;
			
			String sql = "UPDATE pie SET sigla_PIP=?, nome_PIP=?, instituição_ID=?)"
					+ " WHERE ID_PIE=?";

			try {
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.setString(1, pie.getSiglaPIE());
				stmt.setString(2, pie.getNomePIE());
				stmt.setInt(3, pie.getInstituiçãoID());
				stmt.setInt(4, pie.getID_PIE());
				
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
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.ProgramaInstitucionalExtensão")) {
			ProgramaInstitucionalExtensão pie = (ProgramaInstitucionalExtensão) entidade;
			
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
		else {
			throw new ClasseInválidaException();
		}
	}	
}
