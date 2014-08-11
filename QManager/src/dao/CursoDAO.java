package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import principal.Banco;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import entidades.Curso;
import entidades.EntidadeIF;
import excecoes.ClasseInvalidaException;

/*
 TABLE `curso`
 `id_curso` INT NOT NULL,
 `nm_curso` VARCHAR(45) NOT NULL,
 */

public class CursoDAO implements DAO {

	// a conexão com o banco de dados
	public Connection connection;

	public CursoDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}

	@Override
	public void creat(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Curso) {

			Curso curso = (Curso) entidade;

			try {

				// Define um insert com os atributos e cada valor é representado
				// por ?
				String sql = "INSERT INTO `curso` (`nm_curso`)" + " VALUES (?)";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setString(1, curso.getNomeCurso());

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

	@Override
	public void readById(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Curso) {

			Curso curso = (Curso) entidade;

			try {

				String sql = String.format("%s %d",
						"SELECT * FROM `curso` WHERE `id_curso` =",
						curso.getIdCurso());

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					curso.setNomeCurso(rs.getString("nm_curso"));
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

		if (entidade instanceof Curso) {

			Curso curso = (Curso) entidade;

			try {

				// Define update setando cada atributo e cada valor é
				// representado por ?
				String sql = "UPDATE `curso` SET `nm_curso`=? "
						+ "WHERE `id_curso`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setString(1, curso.getNomeCurso());
				stmt.setInt(2, curso.getIdCurso());

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

	@Override
	public void delete(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Curso) {

			Curso curso = (Curso) entidade;

			try {

				// Deleta uma tupla setando o atributo de identificação com
				// valor representado por ?
				String sql = "DELETE FROM `curso` WHERE `id_curso`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, curso.getIdCurso());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}