package br.edu.ifpb.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.edu.ifpb.Banco;
import br.edu.ifpb.entidades.Entidade;
import br.edu.ifpb.entidades.Instituição;
import br.edu.ifpb.exceções.ClasseInválidaException;

import com.mysql.jdbc.PreparedStatement;

// Campos da tabela
// ID_instituição, nome_instituição, sigla, orçamento

public class InstituiçãoDAO implements DAO {

	// a conexão com o banco de dados
	public Connection connection;
	
	public InstituiçãoDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}

	@Override
	public void creat(Entidade entidade) throws ClasseInválidaException {
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.Instituição")) {
			Instituição instituição = (Instituição) entidade;
			
			// Cria um insert com os atributos e os valores sem definição, apenas
			// com a quantidade de valores a ser inseridos (representado por "?").
			String sql = "INSERT INTO instituição (nome_instituição, sigla, orçamento)"
					+ " values (?,?,?)";

			try {
				// prepara para inserção
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
		else {
			throw new ClasseInválidaException();
		}
	}

	@Override
	public void read(Entidade entidade) throws ClasseInválidaException {
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.Instituição")) {
			
		}
		else {
			throw new ClasseInválidaException();
		}
	}

	@Override
	public void update(Entidade entidade) throws ClasseInválidaException {
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.Instituição")) {
			Instituição instituição = (Instituição) entidade;
			
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
		else {
			throw new ClasseInválidaException();
		}
	}

	@Override
	public void delete(Entidade entidade) throws ClasseInválidaException {
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.Instituição")) {
			Instituição instituição = (Instituição) entidade;
			
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
		else {
			throw new ClasseInválidaException();
		}
	}

}
