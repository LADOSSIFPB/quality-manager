package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.RecursoProgramaInstitucional;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class RecursoProgramaInstitucionalDAO implements
		GenericDAO<Integer, RecursoProgramaInstitucional> {

	static DBPool banco;
	private static RecursoProgramaInstitucionalDAO instance;

	public static RecursoProgramaInstitucionalDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new RecursoProgramaInstitucionalDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public RecursoProgramaInstitucionalDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(RecursoProgramaInstitucional recurso)
			throws SQLExceptionQManager {

		int chave = 0;

		PreparedStatement stmt = null;

		try {

			String sql = String
					.format("%s %s (%d, %s, '%s', '%s')",
							"INSERT INTO tb_recurso_programa_institucional (programa_institucional_id, vl_orcamento, dt_validade_inicial, dt_validade_final) ",
							"VALUES", recurso.getProgramaInstitucional()
									.getIdProgramaInstitucional(), recurso
									.getOrcamento(), recurso
									.getValidadeInicial(), recurso
									.getValidadeFinal());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			chave = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.closeQuery(stmt);
		}

		return chave;

	}

	@Override
	public void update(RecursoProgramaInstitucional recurso)
			throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_recurso_programa_institucional SET programa_institucional_id=?, vl_orcamento=?, dt_validade_inicial=?, dt_validade_final=? "
					+ "WHERE id_recurso_pi=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, recurso.getProgramaInstitucional()
					.getIdProgramaInstitucional());
			stmt.setDouble(2, recurso.getOrcamento());
			stmt.setDate(3, new java.sql.Date(recurso.getValidadeInicial()
					.getTime()));
			stmt.setDate(4, new java.sql.Date(recurso.getValidadeFinal()
					.getTime()));
			stmt.setInt(5, recurso.getIdRecursoPI());

			stmt.execute();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.closeQuery(stmt);
		}

	}

	@Override
	public void delete(Integer id) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "DELETE FROM tb_recurso_programa_institucional WHERE id_recurso_pi=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.closeQuery(stmt);
		}

	}

	@Override
	public List<RecursoProgramaInstitucional> getAll()
			throws SQLExceptionQManager {

		List<RecursoProgramaInstitucional> recursosIF;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s",
							"SELECT recurso_programa_institucional.id_recurso_pi, "
									+ "recurso_programa_institucional.programa_institucional_id, "
									+ "recurso_programa_institucional.vl_orcamento, "
									+ "recurso_programa_institucional.dt_validade_inicial, "
									+ "recurso_programa_institucional.dt_validade_final,"
									+ "programa_institucional.dt_registro "
									+ "FROM tb_recurso_programa_institucional recurso_programa_institucional");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			recursosIF = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.closeQuery(stmt, rs);
		}

		return recursosIF;
	}

	@Override
	public RecursoProgramaInstitucional getById(Integer id)
			throws SQLExceptionQManager {

		RecursoProgramaInstitucional recursoPI = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT recurso_programa_institucional.id_recurso_pi, "
									+ "recurso_programa_institucional.programa_institucional_id, "
									+ "recurso_programa_institucional.vl_orcamento, "
									+ "recurso_programa_institucional.dt_validade_inicial, "
									+ "recurso_programa_institucional.dt_validade_final,"
									+ "programa_institucional.dt_registro "
									+ "FROM tb_recurso_programa_institucional recurso_programa_institucional "
									+ "WHERE id_recurso_pi=", id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<RecursoProgramaInstitucional> recursosPI = convertToList(rs);

			if (!recursosPI.isEmpty())
				recursoPI = recursosPI.get(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.closeQuery(stmt, rs);
		}

		return recursoPI;

	}

	@Override
	public List<RecursoProgramaInstitucional> find(
			RecursoProgramaInstitucional programaInstitucional)
			throws SQLExceptionQManager {
		return null;
	}

	@Override
	public List<RecursoProgramaInstitucional> convertToList(ResultSet rs)
			throws SQLExceptionQManager {

		List<RecursoProgramaInstitucional> recursosProgramaInstitucional = new LinkedList<RecursoProgramaInstitucional>();

		try {

			while (rs.next()) {

				RecursoProgramaInstitucional recursoProgramaInstitucional = new RecursoProgramaInstitucional();

				recursoProgramaInstitucional
						.setIdRecursoPI(rs
								.getInt("recurso_programa_institucional.id_recurso_pi"));
				recursoProgramaInstitucional
						.getProgramaInstitucional()
						.setIdProgramaInstitucional(
								rs.getInt("recurso_programa_institucional.programa_institucional_id"));
				recursoProgramaInstitucional
						.setOrcamento(rs
								.getDouble("recurso_programa_institucional.vl_orcamento"));
				recursoProgramaInstitucional
						.setValidadeInicial(rs
								.getDate("recurso_programa_institucional.dt_validade_inicial"));
				recursoProgramaInstitucional
						.setValidadeFinal(rs
								.getDate("recurso_programa_institucional.dt_validade_final"));
				recursoProgramaInstitucional.setRegistro(rs
						.getDate("programa_institucional.dt_registro"));

				recursosProgramaInstitucional.add(recursoProgramaInstitucional);

			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return recursosProgramaInstitucional;

	}

}
