package dao;

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

public class DiscenteDAO {

	// a conexão com o banco de dados
	public Connection connection;

	private PessoaDAO pessoaDAO;

	public DiscenteDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
		pessoaDAO = new PessoaDAO(banco);
	}

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

	public void read(EntidadeIF entidade) throws ClasseInvalidaException {

	}

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