package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class ProgramaInstitucionalDAO implements
		GenericDAO<Integer, ProgramaInstitucional> {

	static DBPool banco;
	private static ProgramaInstitucionalDAO instance;

	public static ProgramaInstitucionalDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new ProgramaInstitucionalDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public ProgramaInstitucionalDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	/*
	 * private boolean orcamentoValido(double orcamentoAtual, int idInstituicao)
	 * throws SQLExceptionQManager {
	 * 
	 * try {
	 * 
	 * String sql = "SELECT SUM(programa_institucional.vl_orcamento) AS soma " +
	 * "FROM tb_programa_institucional programa_institucional " +
	 * "WHERE programa_institucional.instituicao_id = " + idInstituicao;
	 * 
	 * PreparedStatement stmt = (PreparedStatement) connection
	 * .prepareStatement(sql);
	 * 
	 * ResultSet rs = stmt.executeQuery(sql);
	 * 
	 * double soma = -1;
	 * 
	 * while (rs.next()) { soma = rs.getDouble("soma"); }
	 * 
	 * InstituicaoFinanciadora instituicaoFinanciadora =
	 * InstituicaoFinanciadoraDAO .getInstance().getById(idInstituicao);
	 * 
	 * double orcamentoInstituicao = instituicaoFinanciadora .getOrcamento();
	 * 
	 * if ((soma == -1) || ((orcamentoInstituicao - soma) < orcamentoAtual))
	 * throw new SQLExceptionQManager(102, "Erro: Orçamento insuficiente!");
	 * 
	 * stmt.close(); rs.close();
	 * 
	 * } catch (SQLException sqle) { throw new
	 * SQLExceptionQManager(sqle.getErrorCode(), sqle.getLocalizedMessage()); }
	 * 
	 * return true; }
	 */

	@Override
	public int insert(ProgramaInstitucional programaInstitucional)
			throws SQLExceptionQManager {

		int chave = 0;

		/*
		 * if (orcamentoValido(programaInstitucional.getOrcamento(),
		 * programaInstitucional.getInstituicaoFinanciadora()
		 * .getIdInstituicaoFinanciadora())) {
		 */

		try {

			String sql = String.format("%s %s('%s', '%s', '%s', %d, %d)",
					"INSERT INTO tb_programa_institucional (nm_programa_institucional, nm_sigla, "
							+ "vl_orcamento, instituicao_id, pessoa_id)",
					"VALUES", programaInstitucional
							.getNomeProgramaInstitucional(),
					programaInstitucional.getSigla(), programaInstitucional
							.getInstituicaoFinanciadora()
							.getIdInstituicaoFinanciadora(),
					programaInstitucional.getGestor().getPessoaId());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			chave = BancoUtil.getGenerateKey(stmt);

			stmt.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return chave;

	}

	@Override
	public void update(ProgramaInstitucional programaInstitucional)
			throws SQLExceptionQManager {

		try {

			String sql = "UPDATE tb_programa_institucional SET nm_programa_institucional=?, nm_sigla=?, instituicao_id=? "
					+ "WHERE id_programa_institucional=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1,
					programaInstitucional.getNomeProgramaInstitucional());
			stmt.setString(2, programaInstitucional.getSigla());
			stmt.setInt(3, programaInstitucional.getInstituicaoFinanciadora()
					.getIdInstituicaoFinanciadora());
			stmt.setInt(4, programaInstitucional.getIdProgramaInstitucional());

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public void delete(Integer id) throws SQLExceptionQManager {

		try {

			String sql = "DELETE FROM tb_programa_institucional WHERE id_programa_institucional=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public List<ProgramaInstitucional> getAll() throws SQLExceptionQManager {
		List<ProgramaInstitucional> programasInstitucionais;

		try {

			String sql = String
					.format("%s",
							"SELECT programa_institucional.id_programa_institucional, "
									+ "programa_institucional.nm_programa_institucional, "
									+ "programa_institucional.nm_sigla, programa_institucional.vl_orcamento, "
									+ "programa_institucional.pessoa_id, "
									+ "programa_institucional.instituicao_id, "
									+ "programa_institucional.dt_registro "
									+ "FROM tb_programa_institucional programa_institucional");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			programasInstitucionais = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return programasInstitucionais;
	}

	@Override
	public ProgramaInstitucional getById(Integer id)
			throws SQLExceptionQManager {

		ProgramaInstitucional programaInstitucional = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT programa_institucional.id_programa_institucional, "
									+ "programa_institucional.nm_programa_institucional, "
									+ "programa_institucional.nm_sigla, programa_institucional.vl_orcamento, "
									+ "programa_institucional.pessoa_id, "
									+ "programa_institucional.instituicao_id, "
									+ "programa_institucional.dt_registro "
									+ "FROM tb_programa_institucional programa_institucional "
									+ "WHERE programa_institucional.id_programa_institucional =",
							id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<ProgramaInstitucional> programasInstitucionais = convertToList(rs);

			if (!programasInstitucionais.isEmpty())
				programaInstitucional = programasInstitucionais.get(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return programaInstitucional;

	}

	@Override
	public List<ProgramaInstitucional> find(
			ProgramaInstitucional programaInstitucional)
			throws SQLExceptionQManager {

		List<ProgramaInstitucional> programasInstitucionais = null;

		try {

			String sql = String
					.format("%s '%%%s%%'",
							"SELECT programa_institucional.id_programa_institucional, "
									+ "programa_institucional.nm_programa_institucional, "
									+ "programa_institucional.nm_sigla, programa_institucional.vl_orcamento, "
									+ "programa_institucional.pessoa_id, "
									+ "programa_institucional.instituicao_id, "
									+ "programa_institucional.dt_registro "
									+ "FROM tb_programa_institucional programa_institucional "
									+ "WHERE programa_institucional.nm_programa_institucional LIKE",
							programaInstitucional
									.getNomeProgramaInstitucional());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			programasInstitucionais = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return programasInstitucionais;

	}

	@Override
	public List<ProgramaInstitucional> convertToList(ResultSet rs)
			throws SQLExceptionQManager {

		List<ProgramaInstitucional> programasInstitucionais = new LinkedList<ProgramaInstitucional>();

		try {

			while (rs.next()) {
				ProgramaInstitucional programaInstitucional = new ProgramaInstitucional();

				// Gestor gestor = new Gestor();
				// gestor =
				// GestorDAO.getInstance().getById(rs.getInt("programa_institucional.pessoa_id"));
				// programaInstitucional.setGestor(gestor);
				// InstituicaoFinanciadora instituicaoFinanciadora =
				// InstituicaoFinanciadoraDAO.getInstance().getById(rs.getInt("programa_institucional.instituicao_id"));
				// programaInstitucional.setInstituicaoFinanciadora(instituicaoFinanciadora);

				programaInstitucional.getGestor().setPessoaId(
						rs.getInt("programa_institucional.pessoa_id"));

				programaInstitucional
						.getInstituicaoFinanciadora()
						.setIdInstituicaoFinanciadora(
								rs.getInt("programa_institucional.instituicao_id"));

				programaInstitucional
						.setIdProgramaInstitucional(rs
								.getInt("programa_institucional.id_programa_institucional"));
				programaInstitucional
						.setNomeProgramaInstitucional(rs
								.getString("programa_institucional.nm_programa_institucional"));
				programaInstitucional.setSigla(rs
						.getString("programa_institucional.nm_sigla"));
				programaInstitucional.setRegistro(rs
						.getDate("programa_institucional.dt_registro"));

				programasInstitucionais.add(programaInstitucional);

			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return programasInstitucionais;

	}

}