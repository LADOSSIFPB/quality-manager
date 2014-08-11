package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import principal.Banco;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import entidades.Discente;
import entidades.EntidadeIF;
import excecoes.ClasseInvalidaException;

/*
 TABLE `discente`
 `pessoa_id` INT NOT NULL,
 `turma_id` INT NOT NULL
 */

public class DiscenteDAO implements DAO {

	// a conexão com o banco de dados
	public Connection connection;

	private PessoaDAO pessoaDAO;

	public DiscenteDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
		pessoaDAO = new PessoaDAO(banco);
	}

	@Override
	public void creat(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Discente) {

			Discente discente = (Discente) entidade;

			int chave = pessoaDAO.creat(discente);

			if (chave != 0) {

				try {

					String sql = "INSERT INTO `discente` (`pessoa_id`, `turma_id`)"
							+ " VALUES (?, ?)";

					// prepared statement para inserção
					PreparedStatement stmt = (PreparedStatement) connection
							.prepareStatement(sql);

					// seta os valores
					stmt.setInt(1, chave);
					stmt.setInt(2, discente.getTurmaId());

					// envia para o Banco e fecha o objeto
					stmt.execute();
					stmt.close();

				} catch (SQLException sqle) {
					throw new RuntimeException(sqle);
				}

			} else
				System.err.println("Não foi possível inserir pessoa!");

		} else {
			throw new ClasseInvalidaException();
		}

	}

	@Override
	public void readById(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Discente) {

			Discente discente = (Discente) entidade;

			try {

				String sql = String
						.format("%s %d",
								"SELECT * FROM `discente` D "
										+ "INNER JOIN `pessoa` P ON D.`pessoa_id` = P.`id_pessoa` "
										+ "INNER JOIN `dados_bancarios` DB ON DB.`pessoa_id` = P.`id_pessoa` "
										+ "WHERE D.`pessoa_id`=",
								discente.getPessoaId());

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					// tabela pessoa
					discente.setNomePessoa(rs.getString("nm_pessoa"));
					discente.setCpf(rs.getString("nr_cpf"));
					discente.setMatricula(rs.getString("nr_matricula"));
					discente.setEndereco(rs.getString("nm_endereco"));
					discente.setCep(rs.getString("nm_cep"));
					discente.setTelefone(rs.getString("nm_telefone"));
					discente.setEmail(rs.getString("nm_email"));

					// tabela dados_bancarios
					discente.setInstituicaoBancariaId(rs
							.getInt("instituicao_bancaria_id"));
					discente.setOperacao(rs.getString("nr_operacao"));
					discente.setConta(rs.getString("nr_conta"));

					// discente
					discente.setTurmaId(rs.getInt("turma_id"));
				}

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		} else {
			throw new ClasseInvalidaException();
		}

	}

	@Override
	public void update(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Discente) {

			Discente discente = (Discente) entidade;

			pessoaDAO.update(discente);

			try {

				String sql = "UPDATE `discente` SET `turma_id`=?"
						+ " WHERE `pessoa_id`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);
				// seta os valores
				stmt.setInt(1, discente.getTurmaId());
				stmt.setInt(2, discente.getPessoaId());

				// envia para o Banco e fecha o objeto
				stmt.execute();

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		} else {
			throw new ClasseInvalidaException();
		}

	}

	@Override
	public void delete(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Discente) {

			Discente discente = (Discente) entidade;

			try {

				// Deleta uma tupla setando o atributo de identificação com
				// valor representado por ?
				String sql = "DELETE FROM `discente` WHERE `pessoa_id`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, discente.getPessoaId());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

				pessoaDAO.delete(discente);

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}