package dao;

import java.sql.SQLException;

import principal.Banco;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import entidades.Docente;
import entidades.EntidadeIF;
import excecoes.ClasseInvalidaException;

/*
 TABLE `docente`
 `pessoa_id` INT NOT NULL,
 `nm_titulacao` VARCHAR(45) NOT NULL,
 `nm_cargo` VARCHAR(45) NOT NULL,
 `nm_local_trabalho` VARCHAR(45) NOT NULL
 */

public class DocenteDAO implements DAO {

	// a conexão com o banco de dados
	public Connection connection;

	private PessoaDAO pessoaDAO;

	public DocenteDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
		pessoaDAO = new PessoaDAO(banco);
	}

	public void creat(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Docente) {

			Docente docente = (Docente) entidade;

			int chave = pessoaDAO.creat(docente);

			if (chave != 0) {

				try {

					String sql = "INSERT INTO `docente` (`pessoa_id`, `nm_titulacao`, `nm_cargo`, `nm_local_trabalho`)"
							+ " VALUES (?, ?, ?, ?)";

					// prepared statement para inserção
					PreparedStatement stmt = (PreparedStatement) connection
							.prepareStatement(sql);

					// seta os valores
					stmt.setInt(1, chave);
					stmt.setString(2, docente.getTitulacao());
					stmt.setString(3, docente.getCargo());
					stmt.setString(4, docente.getLocalTrabalho());

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

		if (entidade instanceof Docente) {

			Docente docente = (Docente) entidade;

			pessoaDAO.update(docente);

			try {

				String sql = "UPDATE `docente` SET `nm_titulacao`=?, `nm_cargo`=?, `nm_local_trabalho`=?"
						+ " WHERE `pessoa_id`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setString(1, docente.getTitulacao());
				stmt.setString(2, docente.getCargo());
				stmt.setString(3, docente.getLocalTrabalho());
				stmt.setInt(4, docente.getPessoaId());

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

		if (entidade instanceof Docente) {

			Docente docente = (Docente) entidade;

			try {

				// Deleta uma tupla setando o atributo de identificação com
				// valor representado por ?
				String sql = "DELETE FROM `docente` WHERE `pessoa_id`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, docente.getPessoaId());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

				pessoaDAO.delete(docente);

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}