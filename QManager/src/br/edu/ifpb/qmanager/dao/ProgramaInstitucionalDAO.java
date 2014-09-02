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

	public ProgramaInstitucionalDAO(DatabaseConnection banco) {
		this.connection = (Connection) banco.getConnection();
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

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<ProgramaInstitucional> programasInstitucionais = convertToList(rs);

			programaInstitucional = programasInstitucionais.get(0);

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

			// Define um insert com os atributos e cada valor é representado
			// por ?
			String sql = String
					.format("%s %s('%s', '%s')",
							"INSERT INTO `tb_programa_institucional` (`nm_programa_institucional`, `nm_sigla`)",
							"VALUES", programaInstitucional
									.getNomeProgramaInstitucional(),
							programaInstitucional.getSigla());

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// envia para o Banco e fecha o objeto
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			// recuperar a chave
			ResultSet rs = stmt.getGeneratedKeys();

			// recuperar a chave como inteiro
			if (rs.next()) {
				chave = rs.getInt(1);
			}

			stmt.close();

			sql = "INSERT INTO `tb_instituicao_has_programa_institucional` (`instituicao_id`, `programa_institucional_id`)"
					+ "VALUES (?, ?)";

			// prepared statement para inserção
			stmt = (PreparedStatement) connection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, programaInstitucional.getInstituicaoId());
			stmt.setInt(2, chave);

			// envia para o Banco e fecha o objeto
			stmt.execute();
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

			// Define update setando cada atributo e cada valor é
			// representado por ?
			String sql = "UPDATE `tb_programa_institucional` SET `nm_programa_institucional`=?, `nm_sigla`=?"
					+ "WHERE `id_programa_institucional`=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setString(1,
					programaInstitucional.getNomeProgramaInstitucional());
			stmt.setString(2, programaInstitucional.getSigla());
			stmt.setInt(3, programaInstitucional.getIdProgramaInstitucional());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

			sql = "UPDATE `tb_instituicao_has_programa_institucional` SET `instituicao_id`=? "
					+ "WHERE `id_programa_institucional`=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, programaInstitucional.getInstituicaoId());
			stmt.setInt(2, programaInstitucional.getIdProgramaInstitucional());

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
			String sql = "DELETE FROM `tb_instituicao_has_programa_institucional` WHERE `programa_institucional_id`=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, id);

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

			sql = "DELETE FROM `tb_programa_institucional` WHERE `id_programa_institucional`=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

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
	public List<ProgramaInstitucional> findAll() throws QManagerSQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProgramaInstitucional> convertToList(ResultSet rs)
			throws QManagerSQLException {

		List<ProgramaInstitucional> programasInstitucionais = new ArrayList<ProgramaInstitucional>();

		ProgramaInstitucional programaInstitucional = new ProgramaInstitucional();

		try {

			while (rs.next()) {
				programaInstitucional.setNomeProgramaInstitucional(rs
						.getString("nm_programa_institucional"));
				programaInstitucional.setSigla(rs.getString("nm_sigla"));

				programasInstitucionais.add(programaInstitucional);

			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return programasInstitucionais;

	}

}