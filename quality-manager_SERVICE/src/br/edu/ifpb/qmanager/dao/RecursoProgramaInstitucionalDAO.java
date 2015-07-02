package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.RecursoProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class RecursoProgramaInstitucionalDAO implements
		GenericDAO<Integer, RecursoProgramaInstitucional> {

	static DBPool banco;
	
	private static RecursoProgramaInstitucionalDAO instance;
	
	public Connection connection;

	public static RecursoProgramaInstitucionalDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new RecursoProgramaInstitucionalDAO(banco);
		return instance;
	}

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
					.format("%s %s (%d, %s, '%s', '%s', %d, %d)",
							"INSERT INTO tb_recurso_programa_institucional ("
							+ " programa_institucional_id, "
							+ " vl_orcamento, "
							+ " dt_validade_inicial, "
							+ " dt_validade_final, "
							+ " recurso_instituicao_financiadora_id, "
							+ " pessoa_id) ",
							"VALUES", 
							recurso.getProgramaInstitucional().getIdProgramaInstitucional(), 
							recurso.getOrcamento(),
							new java.sql.Date(recurso.getValidadeInicial().getTime()), 
							new java.sql.Date(recurso.getValidadeFinal().getTime()),
							recurso.getRecursoInstituicaoFinanciadora().getIdRecursoIF(),
							recurso.getServidor().getPessoaId()
					);

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
	public void update(RecursoProgramaInstitucional recurso)
			throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_recurso_programa_institucional"
					+ " SET programa_institucional_id=?,"
					+ " vl_orcamento=?, "
					+ " dt_validade_inicial=?, "
					+ " dt_validade_final=?, "
					+ " fl_recurso_valido=?,"
					+ " pessoa_id=? "
					+ " WHERE id_recurso_pi=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, recurso.getProgramaInstitucional().getIdProgramaInstitucional());
			stmt.setDouble(2, recurso.getOrcamento());
			stmt.setDate(3, new java.sql.Date(recurso.getValidadeInicial().getTime()));
			stmt.setDate(4, new java.sql.Date(recurso.getValidadeFinal().getTime()));
			stmt.setBoolean(5, recurso.isRecursoValido());
			stmt.setInt(6, recurso.getServidor().getPessoaId());
			stmt.setInt(7, recurso.getIdRecursoPI());

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

			String sql = "DELETE FROM tb_recurso_programa_institucional"
					+ " WHERE id_recurso_pi=?";

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
	public List<RecursoProgramaInstitucional> getAll()
			throws SQLExceptionQManager {

		List<RecursoProgramaInstitucional> recursosIF;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s",
							"SELECT recurso_programa_institucional.id_recurso_pi, "
									+ " recurso_programa_institucional.programa_institucional_id, "
									+ " recurso_programa_institucional.vl_orcamento, "
									+ " recurso_programa_institucional.dt_validade_inicial, "
									+ " recurso_programa_institucional.dt_validade_final, "
									+ " recurso_programa_institucional.fl_recurso_valido, "
									+ " recurso_programa_institucional.pessoa_id, "
									+ " recurso_programa_institucional.dt_registro "
									+ " FROM tb_recurso_programa_institucional recurso_programa_institucional");

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
	public RecursoProgramaInstitucional getById(Integer id)
			throws SQLExceptionQManager {

		RecursoProgramaInstitucional recursoPI = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT recurso_programa_institucional.id_recurso_pi, "
									+ " recurso_programa_institucional.programa_institucional_id, "
									+ " recurso_programa_institucional.vl_orcamento, "
									+ " recurso_programa_institucional.dt_validade_inicial, "
									+ " recurso_programa_institucional.dt_validade_final, "
									+ " recurso_programa_institucional.fl_recurso_valido, "
									+ " recurso_programa_institucional.pessoa_id, "
									+ " recurso_programa_institucional.dt_registro "
									+ " FROM tb_recurso_programa_institucional recurso_programa_institucional "
									+ " WHERE id_recurso_pi=", id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<RecursoProgramaInstitucional> recursosPI = convertToList(rs);

			if (!recursosPI.isEmpty())
				recursoPI = recursosPI.get(0);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return recursoPI;
	}

	@Override
	public List<RecursoProgramaInstitucional> find(
			RecursoProgramaInstitucional programaInstitucional)
			throws SQLExceptionQManager {
		return null;
	}

	public List<RecursoProgramaInstitucional> getByOrcamentoValido()
			throws SQLExceptionQManager {

		List<RecursoProgramaInstitucional> recursosIF;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s",
							"SELECT recurso_programa_institucional.id_recurso_pi, "
									+ " recurso_programa_institucional.programa_institucional_id, "
									+ " recurso_programa_institucional.vl_orcamento, "
									+ " recurso_programa_institucional.dt_validade_inicial, "
									+ " recurso_programa_institucional.dt_validade_final, "
									+ " recurso_programa_institucional.fl_recurso_valido, "
									+ " recurso_programa_institucional.pessoa_id, "
									+ " recurso_programa_institucional.dt_registro "
									+ " FROM tb_recurso_programa_institucional recurso_programa_institucional "
									+ " WHERE recurso_programa_institucional.fl_recurso_valido = TRUE "
										+ " AND recurso_programa_institucional.dt_validade_final >= CURDATE()");

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
	
	public List<RecursoProgramaInstitucional> getAllByProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) throws SQLExceptionQManager {
		List<RecursoProgramaInstitucional> recursosIP;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT recurso_programa_institucional.id_recurso_pi, "
									+ " recurso_programa_institucional.programa_institucional_id, "
									+ " recurso_programa_institucional.vl_orcamento, "
									+ " recurso_programa_institucional.dt_validade_inicial, "
									+ " recurso_programa_institucional.dt_validade_final, "
									+ " recurso_programa_institucional.fl_recurso_valido, "
									+ " recurso_programa_institucional.pessoa_id, "
									+ " recurso_programa_institucional.dt_registro "
									+ " FROM tb_recurso_programa_institucional recurso_programa_institucional"
									+ " WHERE recurso_programa_institucional.programa_institucional_id=",
										programaInstitucional.getIdProgramaInstitucional());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);
			
			recursosIP = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}
		
		return recursosIP;
	}
	
	@Override
	public List<RecursoProgramaInstitucional> convertToList(ResultSet rs)
			throws SQLExceptionQManager {

		List<RecursoProgramaInstitucional> recursosProgramaInstitucional = new LinkedList<RecursoProgramaInstitucional>();

		try {

			while (rs.next()) {

				RecursoProgramaInstitucional recursoProgramaInstitucional = new RecursoProgramaInstitucional();
				ProgramaInstitucional programaInstitucional = new ProgramaInstitucional();
				Servidor servidor = new Servidor();

				recursoProgramaInstitucional.setIdRecursoPI(rs.getInt("recurso_programa_institucional.id_recurso_pi"));
				
				servidor.setPessoaId(rs
						.getInt("recurso_programa_institucional.pessoa_id"));
				recursoProgramaInstitucional.setServidor(servidor);
				
				programaInstitucional.setIdProgramaInstitucional(
								rs.getInt("recurso_programa_institucional.programa_institucional_id"));
				recursoProgramaInstitucional.setProgramaInstitucional(programaInstitucional);
				
				recursoProgramaInstitucional.setOrcamento(rs.getDouble("recurso_programa_institucional.vl_orcamento"));
				recursoProgramaInstitucional.setValidadeInicial(rs.getDate("recurso_programa_institucional.dt_validade_inicial"));
				recursoProgramaInstitucional.setValidadeFinal(rs.getDate("recurso_programa_institucional.dt_validade_final"));
				recursoProgramaInstitucional.setRecursoValido(rs.getBoolean("recurso_programa_institucional.fl_recurso_valido"));
				recursoProgramaInstitucional.setRegistro(rs.getDate("recurso_programa_institucional.dt_registro"));

				recursosProgramaInstitucional.add(recursoProgramaInstitucional);

			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return recursosProgramaInstitucional;
	}

}
