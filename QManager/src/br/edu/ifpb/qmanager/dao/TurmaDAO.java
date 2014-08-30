package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import principal.Banco;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;
import br.edu.ifpb.qmanager.util.Constantes;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class TurmaDAO implements GenericDAO<Integer, Turma> {

	// a conexão com o banco de dados
	public Banco banco;
	public Connection connection;

	public TurmaDAO(Banco banco) {
		this.banco = banco;
		this.connection = (Connection) banco.getConnection();
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

			turma = turmas.get(0);

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

			// Define um insert com os atributos e cada valor é representado
			// por ?
			String sql = String
					.format("%s %s ('%s', '%s', '%s')",
							"INSERT INTO `tb_turma` (`nr_ano`, `nm_turno`, `curso_id`)",
							"VALUES", turma.getAno(), turma.getTurno(), turma
									.getCurso().getIdCurso());

			// prepared statement para inserção
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

			// Define update setando cada atributo e cada valor é
			// representado por ?
			String sql = "UPDATE `tb_turma` SET `nr_ano`=?, `nm_turno`=?, `curso_id`=? "
					+ "WHERE `id_turma`=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, turma.getAno());
			stmt.setString(2, turma.getTurno());
			stmt.setInt(3, turma.getCurso().getIdCurso());
			stmt.setInt(4, turma.getIdTurma());

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
			String sql = "DELETE FROM `tb_turma` WHERE `id_turma`=?";

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
	public List<Turma> findAll() throws QManagerSQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Turma> convertToList(ResultSet rs) throws QManagerSQLException {

		List<Turma> turmas = new ArrayList<Turma>();

		Turma turma = new Turma();
		Curso curso = new Curso();
		CursoDAO cursoDAO = new CursoDAO(banco);

		try {

			while (rs.next()) {
				turma.setIdTurma(rs.getInt("id_turma"));
				turma.setAno(rs.getInt("nr_ano"));
				turma.setTurno(rs.getString("nm_turno"));

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