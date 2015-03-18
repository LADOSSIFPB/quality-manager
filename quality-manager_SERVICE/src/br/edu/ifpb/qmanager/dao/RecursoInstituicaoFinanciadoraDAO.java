package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.RecursoInstituicaoFinanciadora;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class RecursoInstituicaoFinanciadoraDAO implements
		GenericDAO<Integer, RecursoInstituicaoFinanciadora> {

	static DBPool banco;
	
	private static RecursoInstituicaoFinanciadoraDAO instance;
	
	public Connection connection;

	public static RecursoInstituicaoFinanciadoraDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new RecursoInstituicaoFinanciadoraDAO(banco);
		return instance;
	}	

	public RecursoInstituicaoFinanciadoraDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(RecursoInstituicaoFinanciadora recurso)
			throws SQLExceptionQManager {

		int chave = 0;

		PreparedStatement stmt = null;

		try {

			String sql = String
					.format("%s %s (%d, %s, '%s', '%s')",
							"INSERT INTO tb_recurso_instituicao_financiadora (instituicao_financiadora_id, vl_orcamento, dt_validade_inicial, dt_validade_final) ",
							"VALUES", recurso.getInstituicaoFinanciadora()
									.getIdInstituicaoFinanciadora(), recurso
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

			banco.close(stmt, this.connection);
		}

		return chave;

	}

	@Override
	public void update(RecursoInstituicaoFinanciadora recurso)
			throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_recurso_instituicao_financiadora SET instituicao_financiadora_id=?, vl_orcamento=?, dt_validade_inicial=?, dt_validade_final=? "
					+ "WHERE id_recurso_if=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, recurso.getInstituicaoFinanciadora()
					.getIdInstituicaoFinanciadora());
			stmt.setDouble(2, recurso.getOrcamento());
			stmt.setDate(3, new java.sql.Date(recurso.getValidadeInicial()
					.getTime()));
			stmt.setDate(4, new java.sql.Date(recurso.getValidadeFinal()
					.getTime()));
			stmt.setInt(5, recurso.getIdRecursoIF());

			stmt.execute();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, this.connection);
		}

	}

	@Override
	public void delete(Integer id) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "DELETE FROM tb_recurso_instituicao_financiadora WHERE id_recurso_if=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, this.connection);
		}
	}

	@Override
	public List<RecursoInstituicaoFinanciadora> getAll()
			throws SQLExceptionQManager {

		List<RecursoInstituicaoFinanciadora> recursosIF = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s",
							"SELECT recurso_instituicao_financiadora.id_recurso_if, "
									+ "recurso_instituicao_financiadora.instituicao_financiadora_id, "
									+ "recurso_instituicao_financiadora.vl_orcamento, "
									+ "recurso_instituicao_financiadora.dt_validade_inicial, "
									+ "recurso_instituicao_financiadora.dt_validade_final,"
									+ "recurso_instituicao_financiadora.dt_registro "
									+ "FROM tb_recurso_instituicao_financiadora recurso_instituicao_financiadora");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			recursosIF = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return recursosIF;
	}

	@Override
	public RecursoInstituicaoFinanciadora getById(Integer id)
			throws SQLExceptionQManager {

		RecursoInstituicaoFinanciadora recursoIF = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT recurso_instituicao_financiadora.id_recurso_if, "
									+ "recurso_instituicao_financiadora.instituicao_financiadora_id, "
									+ "recurso_instituicao_financiadora.vl_orcamento, "
									+ "recurso_instituicao_financiadora.dt_validade_inicial, "
									+ "recurso_instituicao_financiadora.dt_validade_final,"
									+ "recurso_instituicao_financiadora.dt_registro "
									+ "FROM tb_recurso_instituicao_financiadora recurso_instituicao_financiadora "
									+ "WHERE id_recurso_if=", id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<RecursoInstituicaoFinanciadora> recursosIF = convertToList(rs);

			if (!recursosIF.isEmpty())
				recursoIF = recursosIF.get(0);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return recursoIF;

	}

	@Override
	public List<RecursoInstituicaoFinanciadora> find(
			RecursoInstituicaoFinanciadora instituicaoFinanciadora)
			throws SQLExceptionQManager {
		return null;
	}

	@Override
	public List<RecursoInstituicaoFinanciadora> convertToList(ResultSet rs)
			throws SQLExceptionQManager {

		List<RecursoInstituicaoFinanciadora> recursosInstituicaoFinanciadora = new LinkedList<RecursoInstituicaoFinanciadora>();

		try {

			while (rs.next()) {

				RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora = new RecursoInstituicaoFinanciadora();

				recursoInstituicaoFinanciadora.setIdRecursoIF(rs.getInt(
								"recurso_instituicao_financiadora.id_recurso_if"));
				recursoInstituicaoFinanciadora.getInstituicaoFinanciadora()
						.setIdInstituicaoFinanciadora(rs.getInt(
								"recurso_instituicao_financiadora.instituicao_financiadora_id"));
				recursoInstituicaoFinanciadora.setOrcamento(rs.getDouble(
						"recurso_instituicao_financiadora.vl_orcamento"));
				recursoInstituicaoFinanciadora
						.setValidadeInicial(rs.getDate(
								"recurso_instituicao_financiadora.dt_validade_inicial"));
				recursoInstituicaoFinanciadora.setValidadeFinal(rs.getDate(
						"recurso_instituicao_financiadora.dt_validade_final"));
				recursoInstituicaoFinanciadora.setRegistro(rs.getDate(
						"recurso_instituicao_financiadora.dt_registro"));

				recursosInstituicaoFinanciadora
						.add(recursoInstituicaoFinanciadora);
			}

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		}

		return recursosInstituicaoFinanciadora;
	}
}
