package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class TurmaDAO implements GenericDAO<Integer, Turma> {

	static DBPool banco;
	
	private static TurmaDAO instance;
	
	public Connection connection;

	public static TurmaDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new TurmaDAO(banco);
		return instance;
	}	

	public TurmaDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Turma turma) throws SQLExceptionQManager {

		int idTurma = 0;

		PreparedStatement stmt = null;

		try {

			String sql = String
					.format("%s %s ('%s', '%s', %d)",
							"INSERT INTO tb_turma (nr_periodo_letivo, nm_turno, curso_id)",
							"VALUES",
							turma.getPeriodoLetivo(),
							turma.getTurno(),
							turma.getCurso().getIdCurso());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idTurma = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}

		return idTurma;
	}

	@Override
	public void update(Turma turma) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_turma"
					+ " SET nr_periodo_letivo=?,"
					+ " nm_turno=?,"
					+ " curso_id=?, "
					+ "WHERE id_turma=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, turma.getPeriodoLetivo());
			stmt.setString(2, String.valueOf(turma.getTurno()));
			stmt.setInt(3, turma.getCurso().getIdCurso());
			stmt.setInt(4, turma.getIdTurma());

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

			String sql = "DELETE FROM tb_turma WHERE id_turma=?";

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
	public List<Turma> getAll() throws SQLExceptionQManager {

		List<Turma> turmas = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s",
							"SELECT turma.id_turma,"
							+ " turma.nr_periodo_letivo,"
							+ " turma.nm_turno,"
							+ " turma.dt_registro,"
							+ " turma.curso_id"
							+ " FROM tb_turma AS turma");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			turmas = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return turmas;
	}

	@Override
	public Turma getById(Integer id) throws SQLExceptionQManager {

		Turma turma = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d",
					"SELECT turma.id_turma, turma.nr_periodo_letivo, turma.nm_turno, "
							+ "turma.dt_registro, turma.curso_id "
							+ "FROM tb_turma turma WHERE turma.id_turma =",
					id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Turma> turmas = convertToList(rs);

			if (!turmas.isEmpty())
				turma = turmas.get(0);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return turma;

	}

	public List<Turma> getByCoordenador(int id) throws SQLExceptionQManager {

		List<Turma> turmas = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d",
					"SELECT turma.id_turma,"
						+ " turma.nr_periodo_letivo,"
						+ " turma.nm_turno,"
						+ " turma.dt_registro,"
						+ " turma.curso_id"
						+ " FROM tb_turma AS turma,"
						+ " tb_curso AS curso, "
						+ " tb_pessoa AS coordenador "
						+ " WHERE turma.curso_id = curso.id_curso"
						+ " AND curso.pessoa_id = coordenador.id_pessoa"
						+ " AND coordenador.id_pessoa =",
					id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			turmas = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return turmas;
	}

	@Override
	public List<Turma> find(Turma turma) throws SQLExceptionQManager {
		return null;
	}

	@Override
	public List<Turma> convertToList(ResultSet rs) throws SQLExceptionQManager {

		List<Turma> turmas = new LinkedList<Turma>();

		try {

			while (rs.next()) {
				Turma turma = new Turma();

				turma.setIdTurma(rs.getInt("turma.id_turma"));
				turma.setPeriodoLetivo(rs.getInt("turma.nr_periodo_letivo"));
				turma.setTurno(rs.getString("turma.nm_turno").charAt(0));
				turma.setRegistro(rs.getDate("turma.dt_registro"));

				turmas.add(turma);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return turmas;
	}
}