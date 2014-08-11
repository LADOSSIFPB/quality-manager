package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import principal.Banco;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import entidades.EntidadeIF;
import entidades.Turma;
import excecoes.ClasseInvalidaException;

/*
 TABLE `turma`
 `id_turma` INT NOT NULL AUTO_INCREMENT,
 `nr_ano` INT NOT NULL,
 `nm_turno` CHAR NOT NULL,
 `curso_id` INT NOT NULL
 */

public class TurmaDAO implements DAO {

	// a conexão com o banco de dados
	public Connection connection;

	public TurmaDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}

	public void creat(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Turma) {

			Turma turma = (Turma) entidade;

			try {

				// Define um insert com os atributos e cada valor é representado
				// por ?
				String sql = "INSERT INTO `turma` (`nr_ano`, `nm_turno`, `curso_id`)"
						+ " VALUES (?, ?, ?)";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, turma.getAno());
				stmt.setString(2, turma.getTurno());
				stmt.setInt(3, turma.getCursoId());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();
			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		} else {
			throw new ClasseInvalidaException();
		}

	}

	public void readById(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Turma) {

			Turma turma = (Turma) entidade;

			try {

				String sql = String.format("%s %d",
						"SELECT * FROM `turma` WHERE `id_turma` =",
						turma.getIdTurma());

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					turma.setAno(rs.getInt("nr_ano"));
					turma.setTurno(rs.getString("nm_turno"));
					turma.setCursoId(rs.getInt("curso_id"));
				}

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		} else {
			throw new ClasseInvalidaException();
		}

	}

	public void update(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Turma) {

			Turma turma = (Turma) entidade;

			try {

				// Define update setando cada atributo e cada valor é
				// representado por ?
				String sql = "UPDATE `turma` SET `nr_ano`=?, `nm_turno`=?, `curso_id`=? "
						+ "WHERE `id_turma`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, turma.getAno());
				stmt.setString(2, turma.getTurno());
				stmt.setInt(3, turma.getCursoId());
				stmt.setInt(4, turma.getIdTurma());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		} else {
			throw new ClasseInvalidaException();
		}

	}

	public void delete(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Turma) {

			Turma turma = (Turma) entidade;

			try {

				// Deleta uma tupla setando o atributo de identificação com
				// valor representado por ?
				String sql = "DELETE FROM `turma` WHERE `id_turma`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, turma.getIdTurma());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}

		}
	}

}