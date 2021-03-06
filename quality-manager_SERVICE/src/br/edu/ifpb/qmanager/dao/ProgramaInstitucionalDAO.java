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
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoProgramaInstitucional;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class ProgramaInstitucionalDAO implements
		GenericDAO<Integer, ProgramaInstitucional> {

	static DBPool banco;
	
	private static ProgramaInstitucionalDAO instance;
	
	public Connection connection;
	
	public static ProgramaInstitucionalDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new ProgramaInstitucionalDAO(banco);
		return instance;
	}

	public ProgramaInstitucionalDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(ProgramaInstitucional programaInstitucional)
			throws SQLExceptionQManager {

		int chave = 0;

		PreparedStatement stmt = null;

		try {

			String sql = String.format("%s %s('%s', '%s', %d, %d, %d)",
					"INSERT INTO tb_programa_institucional ("
					+ " nm_programa_institucional,"
					+ " nm_sigla, "
					+ "instituicao_id,"
					+ " pessoa_id,"
					+ " tipo_programa_institucional_id)",
					"VALUES",
					programaInstitucional.getNomeProgramaInstitucional(),
					programaInstitucional.getSigla(), programaInstitucional
							.getInstituicaoFinanciadora()
							.getIdInstituicaoFinanciadora(),
					programaInstitucional.getCadastrador().getPessoaId(),
					programaInstitucional.getTipoProgramaInstitucional().
						getIdTipoProgramaInstitucional());

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
	public void update(ProgramaInstitucional programaInstitucional)
			throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_programa_institucional SET "
					+ " nm_programa_institucional=?, "
					+ " nm_sigla=?, "
					+ " instituicao_id=?, "
					+ " tipo_programa_institucional_id=? "
					+ " WHERE id_programa_institucional=? ";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, programaInstitucional.getNomeProgramaInstitucional());
			stmt.setString(2, programaInstitucional.getSigla());
			stmt.setInt(3, programaInstitucional.getInstituicaoFinanciadora().getIdInstituicaoFinanciadora());
			stmt.setInt(4, programaInstitucional.getTipoProgramaInstitucional().getIdTipoProgramaInstitucional());
			stmt.setInt(5, programaInstitucional.getIdProgramaInstitucional());

			stmt.execute();

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(), sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}
	}

	@Override
	public int delete(Integer id) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "DELETE FROM tb_programa_institucional"
					+ " WHERE id_programa_institucional=?";

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
	public List<ProgramaInstitucional> getAll() throws SQLExceptionQManager {
		List<ProgramaInstitucional> programasInstitucionais;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s",
							"SELECT programa_institucional.id_programa_institucional, "
									+ " programa_institucional.nm_programa_institucional, "
									+ " programa_institucional.nm_sigla, "
									+ " programa_institucional.pessoa_id, "
									+ " programa_institucional.instituicao_id, "
									+ " programa_institucional.tipo_programa_institucional_id, "
									+ " programa_institucional.dt_registro "
									+ "FROM tb_programa_institucional programa_institucional");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			programasInstitucionais = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return programasInstitucionais;
	}

	@Override
	public ProgramaInstitucional getById(Integer id)
			throws SQLExceptionQManager {

		ProgramaInstitucional programaInstitucional = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT programa_institucional.id_programa_institucional, "
									+ " programa_institucional.nm_programa_institucional, "
									+ " programa_institucional.nm_sigla, "
									+ " programa_institucional.pessoa_id, "
									+ " programa_institucional.instituicao_id, "
									+ " programa_institucional.tipo_programa_institucional_id, "
									+ " programa_institucional.dt_registro "
									+ "FROM tb_programa_institucional programa_institucional "
									+ "WHERE programa_institucional.id_programa_institucional =",
							id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<ProgramaInstitucional> programasInstitucionais = convertToList(rs);

			if (!programasInstitucionais.isEmpty())
				programaInstitucional = programasInstitucionais.get(0);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return programaInstitucional;
	}

	@Override
	public List<ProgramaInstitucional> find(
			ProgramaInstitucional programaInstitucional)
			throws SQLExceptionQManager {

		List<ProgramaInstitucional> programasInstitucionais = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s '%%%s%%'",
							"SELECT programa_institucional.id_programa_institucional, "
									+ " programa_institucional.nm_programa_institucional, "
									+ " programa_institucional.nm_sigla, "
									+ " programa_institucional.pessoa_id, "
									+ " programa_institucional.instituicao_id, "
									+ " programa_institucional.tipo_programa_institucional_id, "
									+ " programa_institucional.dt_registro "
									+ "FROM tb_programa_institucional programa_institucional "
									+ "WHERE programa_institucional.nm_programa_institucional LIKE",
							programaInstitucional
									.getNomeProgramaInstitucional());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			programasInstitucionais = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
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

				// Responsável pelo registro.
				Servidor cadastrador = new Servidor();
				cadastrador.setPessoaId(rs.getInt("programa_institucional.pessoa_id"));
				programaInstitucional.setCadastrador(cadastrador);

				// Instituição Financiadora
				int idInstFinanciadora = rs.getInt("programa_institucional.instituicao_id");
				InstituicaoFinanciadora instFinanciadora = 
						InstituicaoFinanciadoraDAO.getInstance().getById(
								idInstFinanciadora);
				programaInstitucional.setInstituicaoFinanciadora(instFinanciadora);

				// Tipo do Programa Institucional
				int idTipoProgInst = rs.getInt("programa_institucional.tipo_programa_institucional_id");
				TipoProgramaInstitucional tipoProgInst = 
						TipoProgramaInstitucionalDAO.getInstance().getById(
								idTipoProgInst);				
				programaInstitucional.setTipoProgramaInstitucional(tipoProgInst);

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