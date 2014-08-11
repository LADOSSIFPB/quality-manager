package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import principal.Banco;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import entidades.Pessoa;
import excecoes.ClasseInvalidaException;

/*
 TABLE `pessoa` (
 `id_pessoa` INT NOT NULL AUTO_INCREMENT,
 `nm_pessoa` VARCHAR(45) NOT NULL,
 `nr_cpf` CHAR(11) NOT NULL,
 `nr_matricula` VARCHAR(20) NOT NULL,
 `nm_endereco` VARCHAR(45) NOT NULL,
 `nm_cep` CHAR(8) NOT NULL,
 `nm_telefone` VARCHAR(15) NOT NULL,
 `nm_email` VARCHAR(45) NOT NULL
 */

/*
 TABLE `dados_bancarios` (
 `pessoa_id` INT NOT NULL,
 `instituicao_bancaria_id` INT NOT NULL,
 `nr_operacao` INT,
 `nr_conta` INT NOT NULL
 */

/* serve de fatoração comum de código para Discente e Docente */
public class PessoaDAO {

	// a conexão com o banco de dados
	public Connection connection;

	public PessoaDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}

	int chave;

	public int creat(Pessoa pessoa) {
			
		chave = 0;

		try {

			// Define um insert com os atributos e cada valor é representado por
			// ?
			String sql = String
					.format("%s %s ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
							"INSERT INTO `pessoa` (`nm_pessoa`, `nr_cpf`, `nr_matricula`, `nm_endereco`, `nm_cep`, `nm_telefone`, `nm_email`)",
							"VALUES", pessoa.getNomePessoa(), pessoa.getCpf(),
							pessoa.getMatricula(), pessoa.getEndereco(),
							pessoa.getCep(), pessoa.getTelefone(),
							pessoa.getEmail());

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// envia para o Banco e fecha o objeto
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			// recuperar a chave
			ResultSet rs = stmt.getGeneratedKeys();

			// recuperar a chave como inteiro
			if (rs.next()) {
				chave = rs.getInt(1);
			}

			stmt.close();

			sql = "INSERT INTO `dados_bancarios` (`pessoa_id`, `instituicao_bancaria_id`, `nr_operacao`, `nr_conta`)"
					+ " VALUES (?, ?, ?, ?)";

			// prepared statement para inserção
			stmt = (PreparedStatement) connection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, chave);
			stmt.setInt(2, pessoa.getInstituicaoBancariaId());
			stmt.setString(3, pessoa.getOperacao());
			stmt.setString(4, pessoa.getConta());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

		return chave;

	}

	public void readById(Pessoa pessoa) {

	}

	public void update(Pessoa pessoa) throws ClasseInvalidaException {

		try {

			// Define um update com os atributos e cada valor é representado por
			// ?
			String sql = "UPDATE `pessoa` SET `nm_pessoa`=?, `nr_cpf`=?, `nr_matricula`=?, `nm_endereco`=?, `nm_cep`=?, `nm_telefone`=?, `nm_email`=?"
					+ " WHERE `id_pessoa`=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, pessoa.getNomePessoa());
			stmt.setString(2, pessoa.getCpf());
			stmt.setString(3, pessoa.getMatricula());
			stmt.setString(4, pessoa.getEndereco());
			stmt.setString(5, pessoa.getCep());
			stmt.setString(6, pessoa.getTelefone());
			stmt.setString(7, pessoa.getEmail());
			stmt.setInt(8, pessoa.getPessoaId());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

			// Define um update com os atributos e cada valor é representado por
			// ?
			sql = "UPDATE `dados_bancarios` SET `instituicao_bancaria_id`=?, `nr_operacao`=?, `nr_conta`=? "
					+ "WHERE pessoa_id`=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, pessoa.getInstituicaoBancariaId());
			stmt.setString(2, pessoa.getOperacao());
			stmt.setString(3, pessoa.getConta());
			stmt.setInt(4, pessoa.getPessoaId());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}

	}

	public void delete(Pessoa pessoa) throws ClasseInvalidaException {

		try {

			String sql = "DELETE FROM `dados_bancarios` WHERE `id_pessoa`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, pessoa.getPessoaId());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

			sql = "DELETE FROM `pessoa` WHERE `id_pessoa`=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, pessoa.getPessoaId());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

}
