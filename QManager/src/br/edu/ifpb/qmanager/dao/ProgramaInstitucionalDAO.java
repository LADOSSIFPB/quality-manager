package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class ProgramaInstitucionalDAO implements
		GenericDAO<Integer, ProgramaInstitucional> {

	// a conexão com o banco de dados
	public Connection connection;
	private DatabaseConnection banco;

	public ProgramaInstitucionalDAO(DatabaseConnection banco) {
		this.connection = (Connection) banco.getConnection();
		this.banco = banco;
	}
	
	@Override
	public List<ProgramaInstitucional> getAll() throws QManagerSQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProgramaInstitucional getById(Integer id)
			throws QManagerSQLException {

		ProgramaInstitucional programaInstitucional = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT * FROM `tb_programa_institucional` WHERE `id_programa_institucional` =",
							id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<ProgramaInstitucional> programasInstitucionais = convertToList(rs);

			if (programasInstitucionais.size() != 0) {
				programaInstitucional = programasInstitucionais.get(0);
			} else {
				throw new QManagerSQLException(777,
						"'id_programa_institucional= " + id + "'");
			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return programaInstitucional;

	}

	@Override
	public int insert(ProgramaInstitucional programaInstitucional)
			throws QManagerSQLException {

		int chave = 0;

		try {

			String sql = String
					.format("%s %s('%s', '%s', '%s', '%s')",
							"INSERT INTO `tb_programa_institucional` (`nm_programa_institucional`, `nm_sigla`, `vl_orcamento`, `instituicao_id`)",
							"VALUES", programaInstitucional
									.getNomeProgramaInstitucional(),
							programaInstitucional.getSigla(),
							programaInstitucional.getOrcamento(),
							programaInstitucional.getInstituicaoFinanciadora()
									.getIdInstituicaoFinanciadora());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			chave = BancoUtil.getGenerateKey(stmt);

			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return chave;

	}

	@Override
	public void update(ProgramaInstitucional programaInstitucional)
			throws QManagerSQLException {

		try {

			String sql = "UPDATE `tb_programa_institucional` SET `nm_programa_institucional`=?, `nm_sigla`=?"
					+ "WHERE `id_programa_institucional`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1,
					programaInstitucional.getNomeProgramaInstitucional());
			stmt.setString(2, programaInstitucional.getSigla());
			stmt.setInt(3, programaInstitucional.getIdProgramaInstitucional());

			stmt.execute();
			stmt.close();

			sql = "UPDATE `tb_instituicao_has_programa_institucional` SET `instituicao_id`=? "
					+ "WHERE `id_programa_institucional`=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, programaInstitucional.getInstituicaoFinanciadora()
					.getIdInstituicaoFinanciadora());
			stmt.setInt(2, programaInstitucional.getIdProgramaInstitucional());

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

			String sql = "DELETE FROM `tb_instituicao_has_programa_institucional` WHERE `programa_institucional_id`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();

			sql = "DELETE FROM `tb_programa_institucional` WHERE `id_programa_institucional`=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public List<ProgramaInstitucional> findAll() throws QManagerSQLException {
		return null;
	}

	@Override
	public List<ProgramaInstitucional> convertToList(ResultSet rs)
			throws QManagerSQLException {

		List<ProgramaInstitucional> programasInstitucionais = new ArrayList<ProgramaInstitucional>();

		InstituicaoFinanciadoraDAO instituicaoFinanciadoraDAO = new InstituicaoFinanciadoraDAO(
				banco);

		ProgramaInstitucional programaInstitucional = new ProgramaInstitucional();

		try {

			while (rs.next()) {
				programaInstitucional.setIdProgramaInstitucional(rs
						.getInt("id_programa_institucional"));
				programaInstitucional.setNomeProgramaInstitucional(rs
						.getString("nm_programa_institucional"));
				programaInstitucional.setSigla(rs.getString("nm_sigla"));
				programaInstitucional
						.setOrcamento(rs.getDouble("vl_orcamento"));
				programaInstitucional
						.setInstituicaoFinanciadora(instituicaoFinanciadoraDAO
								.getById(rs.getInt("instituicao_id")));
				programaInstitucional.setRegistro(rs.getDate("dt_registro"));

				programasInstitucionais.add(programaInstitucional);

			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return programasInstitucionais;

	}

}