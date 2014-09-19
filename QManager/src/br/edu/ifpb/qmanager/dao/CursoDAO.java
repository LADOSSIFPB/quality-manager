package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class CursoDAO implements GenericDAO<Integer, Curso> {

	// a conexão com o banco de dados
	public Connection connection;

	public CursoDAO(DatabaseConnection banco) {
		this.connection = (Connection) banco.getConnection();
	}

	@Override
	public List<Curso> getAll() throws QManagerSQLException {

		List<Curso> cursos;

		try {

			String sql = String.format("%s", "SELECT * FROM `tb_curso`");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			cursos = convertToList(rs);

			if (cursos.size() == 0) {
				throw new QManagerSQLException(777, "");
			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return cursos;
	}

	@Override
	public Curso getById(Integer id) throws QManagerSQLException {

		Curso curso = null;

		try {

			String sql = String.format("%s %d", "SELECT * FROM `tb_curso`"
					+ " WHERE `id_curso` =", id);

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Curso> cursos = convertToList(rs);

			if (cursos.size() != 0) {
				curso = cursos.get(0);
			} else {
				throw new QManagerSQLException(777, "'id_curso= " + id + "'");
			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return curso;
	}

	@Override
	public int insert(Curso curso) throws QManagerSQLException {

		int idCurso = 0;

		try {

			// Define um insert com os atributos e cada valor é representado
			// por ?
			String sql = String.format("%s %s ('%s')",
					"INSERT INTO `tb_curso` (`nm_curso`)", "VALUES",
					curso.getNomeCurso());

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idCurso = BancoUtil.getGenerateKey(stmt);

			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return idCurso;
	}

	@Override
	public void update(Curso curso) throws QManagerSQLException {

		try {

			// Define update setando cada atributo e cada valor é
			// representado por ?
			String sql = "UPDATE `tb_curso` SET `nm_curso`=? "
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
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}
	}

	@Override
	public void delete(Integer id) throws QManagerSQLException {

		try {

			// Deleta uma tupla setando o atributo de identificação com
			// valor representado por ?
			String sql = "DELETE FROM `tb_curso` WHERE `id_curso`=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, id);

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}
	}

	@Override
	public List<Curso> findAll() throws QManagerSQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Curso> convertToList(ResultSet rs) throws QManagerSQLException {

		List<Curso> cursos = new ArrayList<Curso>();

		try {
			while (rs.next()) {
				Curso curso = new Curso();
				curso.setIdCurso(rs.getInt("id_curso"));
				curso.setNomeCurso(rs.getString("nm_curso"));
				curso.setRegistro(rs.getDate("dt_registro"));

				cursos.add(curso);

			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return cursos;
	}

}