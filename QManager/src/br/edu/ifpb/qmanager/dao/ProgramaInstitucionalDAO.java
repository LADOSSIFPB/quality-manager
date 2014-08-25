package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import principal.Banco;
import br.edu.ifpb.qmanager.entidade.EntidadeIF;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.excecao.ClasseInvalidaException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/*
 TABLE `programa_institucional`
 `id_programa_institucional` INT NOT NULL AUTO_INCREMENT,
 `nm_programa_institucional` VARCHAR(45) NOT NULL,
 `nm_sigla` VARCHAR(10)
 */

/*
 TABLE `instituicao_has_programa_institucional` (
 `instituicao_id` INT NOT NULL,
 `programa_institucional_id` INT NOT NULL
 */
//TODO: implements DAO
public class ProgramaInstitucionalDAO {

	// a conexão com o banco de dados
	public Connection connection;

	public ProgramaInstitucionalDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}

	public void insert(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof ProgramaInstitucional) {

			ProgramaInstitucional programaInstitucional = (ProgramaInstitucional) entidade;

			try {

				// Define um insert com os atributos e cada valor é representado
				// por ?
				String sql = String
						.format("%s %s('%s', '%s')",
								"INSERT INTO `programa_institucional` (`nm_programa_institucional`, `nm_sigla`)",
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

				int chave = 0;

				// recuperar a chave como inteiro
				if (rs.next()) {
					chave = rs.getInt(1);
				}

				stmt.close();

				sql = "INSERT INTO `instituicao_has_programa_institucional` (`instituicao_id`, `programa_institucional_id`)"
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
				throw new RuntimeException(sqle);
			}

		} else {
			throw new ClasseInvalidaException();
		}

	}

	public void readById(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof ProgramaInstitucional) {

			ProgramaInstitucional programaInstitucional = (ProgramaInstitucional) entidade;

			try {

				String sql = String
						.format("%s %d",
								"SELECT * FROM `programa_institucional` WHERE `id_programa_institucional` =",
								programaInstitucional
										.getIdProgramaInstitucional());

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					programaInstitucional.setNomeProgramaInstitucional(rs
							.getString("nm_programa_institucional"));
					programaInstitucional.setSigla(rs.getString("nm_sigla"));
				}

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		} else {
			throw new ClasseInvalidaException();
		}

	}

	public void update(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof ProgramaInstitucional) {

			ProgramaInstitucional programaInstitucional = (ProgramaInstitucional) entidade;

			try {

				// Define update setando cada atributo e cada valor é
				// representado por ?
				String sql = "UPDATE `programa_institucional` SET `nm_programa_institucional`=?, `nm_sigla`=?"
						+ "WHERE `id_programa_institucional`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setString(1,
						programaInstitucional.getNomeProgramaInstitucional());
				stmt.setString(2, programaInstitucional.getSigla());
				stmt.setInt(3,
						programaInstitucional.getIdProgramaInstitucional());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

				sql = "UPDATE `instituicao_has_programa_institucional` SET `instituicao_id`=? "
						+ "WHERE `id_programa_institucional`=?";

				stmt = (PreparedStatement) connection.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, programaInstitucional.getInstituicaoId());
				stmt.setInt(2,
						programaInstitucional.getIdProgramaInstitucional());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		} else {
			throw new ClasseInvalidaException();
		}

	}

	public void delete(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof ProgramaInstitucional) {

			ProgramaInstitucional programaInstitucional = (ProgramaInstitucional) entidade;

			try {

				// Deleta uma tupla setando o atributo de identificação com
				// valor representado por ?
				String sql = "DELETE FROM `instituicao_has_programa_institucional` WHERE `programa_institucional_id`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1,
						programaInstitucional.getIdProgramaInstitucional());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

				sql = "DELETE FROM `programa_institucional` WHERE `id_programa_institucional`=?";

				stmt = (PreparedStatement) connection.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1,
						programaInstitucional.getIdProgramaInstitucional());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}