package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class CursoDAO implements GenericDAO<Integer, Curso> {

	static DBPool banco;
	
	private static CursoDAO instance;
	
	public Connection connection;
	
	public static CursoDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new CursoDAO(banco);
		return instance;
	}	

	public CursoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Curso curso) throws SQLExceptionQManager {

		int idCurso = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {

			String sql = String.format("%s %s ('%s', %d, %d)",
					"INSERT INTO tb_curso ("
						+ " nm_curso, "
						+ " coordenador_id,"
						+ " pessoa_id)", 
					"VALUES",
					curso.getNomeCurso(),
					curso.getCoordenador().getPessoaId(),
					curso.getCadastrador().getPessoaId());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idCurso = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}

		return idCurso;
	}

	@Override
	public void update(Curso curso) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_curso"
					+ " SET nm_curso = ?, "
					+ " coordenador_id = ?"
					+ " WHERE id_curso = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, curso.getNomeCurso());
			stmt.setInt(2, curso.getCoordenador().getPessoaId());
			stmt.setInt(3, curso.getIdCurso());

			stmt.execute();

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}
	}

	@Override
	public int delete(Integer id) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "DELETE FROM tb_curso WHERE id_curso = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, this.connection);
		}
		
		return BancoUtil.NOROWSUPDATED;
	}

	@Override
	public List<Curso> getAll() throws SQLExceptionQManager {

		List<Curso> cursos = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s",
					"SELECT "
						+ " curso.id_curso, "
						+ " curso.nm_curso,"
						+ " curso.coordenador_id,"
						+ " curso.pessoa_id,"
						+ " curso.dt_registro" 
						+ " FROM tb_curso AS curso");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			cursos = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return cursos;

	}

	@Override
	public Curso getById(Integer id) throws SQLExceptionQManager {

		Curso curso = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d",
					"SELECT curso.id_curso,"
							+ " curso.nm_curso,"
							+ " curso.coordenador_id,"
							+ " curso.pessoa_id,"
							+ " curso.dt_registro" 
							+ " FROM tb_curso AS curso"
							+ " WHERE curso.id_curso =", 
					id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Curso> cursos = convertToList(rs);

			if (!cursos.isEmpty())
				curso = cursos.get(0);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return curso;
	}

	public List<Curso> find(Curso curso) throws SQLExceptionQManager {

		List<Curso> cursos;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format(
					"%s",
					"SELECT curso.id_curso,"
							+ " curso.nm_curso,"
							+ " curso.coordenador_id,"
							+ " curso.pessoa_id,"
							+ " curso.dt_registro" 
							+ " FROM tb_curso AS curso"
							+ " WHERE curso.nm_curso LIKE '%"
							+ curso.getNomeCurso() + "%'");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			cursos = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return cursos;

	}

	@Override
	public List<Curso> convertToList(ResultSet rs) throws SQLExceptionQManager {

		List<Curso> cursos = new ArrayList<Curso>();

		try {
			while (rs.next()) {
				Curso curso = new Curso();
				curso.setIdCurso(rs.getInt("curso.id_curso"));
				curso.setNomeCurso(rs.getString("curso.nm_curso"));
				Servidor coordenador = ServidorDAO.getInstance().getById(
						rs.getInt("curso.coordenador_id"));
				curso.setCoordenador(coordenador);
				curso.getCadastrador().setPessoaId(rs.getInt("curso.pessoa_id"));
				curso.setRegistro(rs.getDate("curso.dt_registro"));

				cursos.add(curso);
			}

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return cursos;
	}
}