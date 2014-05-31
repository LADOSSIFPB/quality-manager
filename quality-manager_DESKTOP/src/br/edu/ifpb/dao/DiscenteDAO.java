package br.edu.ifpb.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.edu.ifpb.Banco;
import br.edu.ifpb.entidades.Discente;

import com.mysql.jdbc.PreparedStatement;

//					CAMPOS DA TABELA
//CPF, Matricula, Nome, Curso, Endereco, CEP, Telefone, E_mail

public class DiscenteDAO{
	// a conexão com o banco de dados
	public Connection connection;

	public DiscenteDAO(Banco b) {
		this.connection = (Connection) b.getConnection();
	}

	// Recupera todo os discentes
	// public List<Discente> getList() {
	//
	// try {
	// List<Discente> contatos = new ArrayList<Discente>();
	// PreparedStatement stmt = (PreparedStatement)
	// this.connection.prepareStatement("select * from discente where ????");
	// ResultSet rs = stmt.executeQuery();
	//
	// while (rs.next()) {
	// // criando o objeto Contato
	// Discente discente = new Discente();
	// discente.setNome(rs.getString("nome"));
	// discente.setEmail(rs.getString("email"));
	// discente.setEndereco(rs.getString("endereco"));
	//
	// // adicionando o objeto à lista
	// contatos.add(discente);
	// }
	// rs.close();
	// stmt.close();
	// return contatos;
	// } catch (SQLException e) {
	// throw new RuntimeException(e);
	// }
	// }

	// Adicinando um novo discente ao banco
	public void adiciona(Discente discente) {

		// Cria um insert, com os atributos, e os valores sem definição, apenas
		// com a quantidade de valores a ser inseridos (representado por "?").
		String sql = "INSERT INTO discente (CPF, Matricula, Nome, Curso, Endereco, CEP, Telefone, E_mail) values (?,?,?,?,?,?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, discente.getCpf());
			stmt.setString(2, discente.getMatricula());
			stmt.setString(3, discente.getNome());
			stmt.setString(4, discente.getCurso());
			stmt.setString(5, discente.getEndereco());
			stmt.setString(6, discente.getCep());
			stmt.setString(7, discente.getTelefone());
			stmt.setString(8, discente.getEmail());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Alterar dados do discente, a partir do CPF(chave primária).
	public void alterar(Discente discente) {

		String sql = "UPDATE discente set Matricula=?, Nome=?, Curso=?, Endereco=?, CEP=?, Telefone=?, E_mail=? WHERE CPF=?";

		try {
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			stmt.setString(1, discente.getMatricula());
			stmt.setString(2, discente.getNome());
			stmt.setString(3, discente.getCurso());
			stmt.setString(4, discente.getEndereco());
			stmt.setString(5, discente.getCep());
			stmt.setString(6, discente.getTelefone());
			stmt.setString(7, discente.getEmail());
			stmt.setString(8, discente.getCpf());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	// Deletar discente a partir do CPF(chave primária)
	public void deletar(Discente discente) {
		String sql = "DELETE FROM discente WHERE CPF=?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, discente.getCpf());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}