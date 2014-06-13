package br.edu.ifpb.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.edu.ifpb.Banco;
import br.edu.ifpb.entidades.Docente;
import br.edu.ifpb.entidades.Entidade;
import br.edu.ifpb.exceções.ClasseInválidaException;

import com.mysql.jdbc.PreparedStatement;

// Campos da tabela
// CPF, matrícula, nome, cargo, endereço, CEP, telefone, e_mail, titulação, local_trabalho

public class DocenteDAO implements DAO {
	// a conexão com o banco de dados
	public Connection connection;

	public DocenteDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}

	@Override
	public void creat(Entidade entidade) throws ClasseInválidaException {
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.Docente")) {
			Docente docente = (Docente) entidade;
			
			// Cria um insert com os atributos e os valores sem definição, apenas
			// com a quantidade de valores a ser inseridos (representado por "?").
			String sql = "INSERT INTO docente (CPF, matrícula, nome, cargo, endereço, CEP,"
					+ " telefone, e_mail, titulação, local_trabalho) values (?,?,?,?,?,?,?,?,?,?)";

			try {
				// prepara para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);
				// seta os valores
				stmt.setString(1, docente.getCPF());
				stmt.setString(2, docente.getMatrícula());
				stmt.setString(3, docente.getNome());
				stmt.setString(4, docente.getCargo());
				stmt.setString(5, docente.getEndereço());
				stmt.setString(6, docente.getCEP());
				stmt.setString(7, docente.getTelefone());
				stmt.setString(8, docente.getEmail());
				stmt.setString(9, docente.getTitulação());
				stmt.setString(10, docente.getLocalTrabalho());
				
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
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.Docente")) {
			
		}
		else {
			throw new ClasseInválidaException();
		}
	}

	@Override
	public void update(Entidade entidade) throws ClasseInválidaException {
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.Docente")) {
			Docente docente = (Docente) entidade;
			
			String sql = "UPDATE docente SET matrícula=?, nome=?, Cargo=?, endereço=?,"
					+ " CEP=?, telefone=?, e_mail=?, titulação=?, local_trabalho=?"
					+ " WHERE CPF=?";

			try {
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.setString(1, docente.getMatrícula());
				stmt.setString(2, docente.getNome());
				stmt.setString(3, docente.getCargo());
				stmt.setString(4, docente.getEndereço());
				stmt.setString(5, docente.getCEP());
				stmt.setString(6, docente.getTelefone());
				stmt.setString(7, docente.getEmail());
				stmt.setString(8, docente.getTitulação());
				stmt.setString(9, docente.getLocalTrabalho());
				stmt.setString(10, docente.getCPF());

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
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.Docente")) {
			Docente docente = (Docente) entidade;
			
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
		else {
			throw new ClasseInválidaException();
		}
	}

}
