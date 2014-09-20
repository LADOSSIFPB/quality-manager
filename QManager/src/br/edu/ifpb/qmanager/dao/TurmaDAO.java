package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;
import br.edu.ifpb.qmanager.util.Constantes;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class TurmaDAO implements GenericDAO<Integer, Turma> {

	public DatabaseConnection banco;
	public Connection connection;

	public TurmaDAO(DatabaseConnection banco) {
		this.banco = banco;
		this.connection = (Connection) banco.getConnection();
	}
	
	@Override
	public List<Turma> getAll() throws QManagerSQLException {
		List<Turma> turmas;

		try {

			String sql = String.format("%s", "SELECT * FROM `tb_turma`");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			turmas = convertToList(rs);

			if (turmas.size() == 0) {
				throw new QManagerSQLException(777, "");
			}

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
					"SELECT * FROM `tb_turma` WHERE `id_turma` =", id);

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Turma> turmas = convertToList(rs);

			if (turmas.size() != 0) {
				turma = turmas.get(0);
			} else {
				throw new QManagerSQLException(777, "'id_turma= " + id + "'");
			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return turma;

	}

	@Override
	public int insert(Turma turma) throws QManagerSQLException {

		int idTurma = 0;

		try {

			// TODO: Verificar se o código do curso já está cadastrado. Caso não
			// esteja inserir Curso antes da Turma.
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
	public void update(Turma entidade) throws QManagerSQLException {

		Turma turma = (Turma) entidade;

		try {

			String sql = String.format("%s %d %s %c %s %d %s %d",
					"UPDATE `tb_turma` SET `nr_periodo_letivo`=", turma
							.getPeriodoLetivo(), ", `nm_turno`=", turma
							.getTurno(), ", `curso_id`=", turma.getCurso()
							.getIdCurso(), "WHERE `id_turma`=", turma
							.getIdTurma());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

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
	public List<Turma> findAll() throws QManagerSQLException {
		return null;
	}

	@Override
	public List<Turma> convertToList(ResultSet rs) throws QManagerSQLException {

		List<Turma> turmas = new ArrayList<Turma>();

		CursoDAO cursoDAO = new CursoDAO(banco);

		try {

			while (rs.next()) {
				Turma turma = new Turma();
				Curso curso = new Curso();
				turma.setIdTurma(rs.getInt("id_turma"));
				turma.setPeriodoLetivo(rs.getInt("nr_periodo_letivo"));
				turma.setTurno(rs.getString("nm_turno").charAt(0));

				curso = cursoDAO.getById(rs.getInt("curso_id"));

				turma.setRegistro(rs.getDate("dt_registro"));
				turma.setCurso(curso);

				turmas.add(turma);

			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return turmas;
	}

}