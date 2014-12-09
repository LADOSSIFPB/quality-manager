package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

public class TurmaDAO implements GenericDAO<Integer, Turma> {

	static DBPool banco;
	private static TurmaDAO instance;

	public static TurmaDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new TurmaDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public TurmaDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Turma turma) throws QManagerSQLException {

		int idTurma = 0;

		try {

			// TODO: Verificar se o código do curso já está cadastrado. Caso não
			// esteja inserir Curso antes da Turma. Precisa disso mesmo?
			int idCurso = turma.getCurso().getIdCurso();
			if (idCurso == Constantes.ID_VAZIO) {
				CursoDAO cursoDAO = new CursoDAO(this.banco);
				turma.getCurso().setIdCurso(cursoDAO.insert(turma.getCurso()));
			}

			String sql = String
					.format("%s %s ('%s', '%s', '%s')",
							"INSERT INTO `tb_turma` (`nr_periodo_letivo`, `nm_turno`, `curso_id`)",
							"VALUES", turma.getPeriodoLetivo(),
							turma.getTurno(), turma.getCurso().getIdCurso());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idTurma = BancoUtil.getGenerateKey(stmt);

			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return idTurma;
	}

	@Override
	public void update(Turma turma) throws QManagerSQLException {

		try {

			String sql = "UPDATE `tb_turma` SET `nr_periodo_letivo`=?, `nm_turno`=?, `curso_id`=?, "
					+ "WHERE `id_turma`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, turma.getPeriodoLetivo());
			stmt.setString(2, String.valueOf(turma.getTurno()));
			stmt.setInt(3, turma.getCurso().getIdCurso());
			stmt.setInt(4, turma.getIdTurma());

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

			String sql = "DELETE FROM `tb_turma` WHERE `id_turma`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public List<Turma> getAll() throws QManagerSQLException {
		List<Turma> turmas;

		try {

			String sql = String
					.format("%s",
							"SELECT turma.id_turma, turma.nr_periodo_letivo, turma.nm_turno, "
									+ "turma.dt_registro, turma.curso_id FROM `tb_turma` turma");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			turmas = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return turmas;
	}

	@Override
	public Turma getById(Integer id) throws QManagerSQLException {

		Turma turma = null;

		try {

			String sql = String.format("%s %d",
					"SELECT turma.id_turma, turma.nr_periodo_letivo, turma.nm_turno, "
							+ "turma.dt_registro, turma.curso_id "
							+ "FROM `tb_turma` turma WHERE turma.`id_turma` =",
					id);

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Turma> turmas = convertToList(rs);

			if (!turmas.isEmpty())
				turma = turmas.get(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return turma;

	}

	public List<Turma> getByCoordenador(int id) throws QManagerSQLException {
		List<Turma> turmas;

		try {

			String sql = String.format("%s %d",
					"SELECT turma.id_turma, turma.nr_periodo_letivo, turma.nm_turno, "
							+ "turma.dt_registro, turma.curso_id "
							+ "FROM `tb_turma` turma, tb_curso curso, "
							+ "tb_pessoa coordenador "
							+ "WHERE turma.curso_id = curso.id_curso"
							+ "AND curso.pessoa_id = coordenador.id_pessoa"
							+ "AND coordenador.id_pessoa =", id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			turmas = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return turmas;
	}

	@Override
	public List<Turma> convertToList(ResultSet rs) throws QManagerSQLException {

		List<Turma> turmas = new LinkedList<Turma>();

		try {

			while (rs.next()) {
				Turma turma = new Turma();

				// Curso curso = new Curso();
				// curso = CursoDAO.getInstance().getById(rs.getInt("turma.curso_id"));
				// turma.setCurso(curso);

				turma.setIdTurma(rs.getInt("turma.id_turma"));
				turma.setPeriodoLetivo(rs.getInt("turma.nr_periodo_letivo"));
				turma.setTurno(rs.getString("turma.nm_turno").charAt(0));
				turma.setRegistro(rs.getDate("turma.dt_registro"));

				turmas.add(turma);
			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return turmas;
	}

}