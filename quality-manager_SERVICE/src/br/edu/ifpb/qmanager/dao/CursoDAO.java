package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

public class CursoDAO implements GenericDAO<Integer, Curso> {

	static DBPool banco;
	private static CursoDAO instance;

	public static CursoDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new CursoDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public CursoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Curso curso) throws QManagerSQLException {

		int idCurso = 0;

		try {

			// Define um insert com os atributos e cada valor é representado
			// por ?
			String sql = String
					.format("%s %s ('%s', %d, %d)",
							"INSERT INTO tb_curso (nm_curso, coordenador_id, pessoa_id)",
							"VALUES", curso.getNomeCurso(), curso
									.getCoordenador().getPessoaId(), curso
									.getGestor().getPessoaId());

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

			String sql = "UPDATE tb_curso SET nm_curso=? coordenador_id=? WHERE id_curso=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1, curso.getNomeCurso());
			stmt.setInt(2, curso.getCoordenador().getPessoaId());
			stmt.setInt(3, curso.getIdCurso());

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
			String sql = "DELETE FROM tb_curso WHERE id_curso=?";

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
	public List<Curso> getAll() throws QManagerSQLException {

		List<Curso> cursos;

		try {

			String sql = String
					.format("%s",
							"SELECT curso.id_curso, curso.nm_curso, curso.coordenador_id, curso.pessoa_id, curso.dt_registro FROM tb_curso curso");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			cursos = convertToList(rs);

			stmt.close();
			rs.close();

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

			String sql = String
					.format("%s %d",
							"SELECT curso.id_curso, curso.nm_curso, curso.coordenador_id, curso.pessoa_id, curso.dt_registro FROM tb_curso curso"
									+ " WHERE curso.id_curso =", id);

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Curso> cursos = convertToList(rs);

			if (!cursos.isEmpty())
				curso = cursos.get(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return curso;
	}

	@Override
	public List<Curso> convertToList(ResultSet rs) throws QManagerSQLException {

		List<Curso> cursos = new LinkedList<Curso>();

		try {
			while (rs.next()) {
				Curso curso = new Curso();
				curso.setIdCurso(rs.getInt("curso.id_curso"));
				curso.setNomeCurso(rs.getString("curso.nm_curso"));
				Servidor coordenador = ServidorDAO.getInstance().getById(
						rs.getInt("curso.coordenador_id"));
				curso.setCoordenador(coordenador);
				curso.getGestor().setPessoaId(rs.getInt("curso.pessoa_id"));
				curso.setRegistro(rs.getDate("curso.dt_registro"));

				cursos.add(curso);
			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return cursos;
	}
}