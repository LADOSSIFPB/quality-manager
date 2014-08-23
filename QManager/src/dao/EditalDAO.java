package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import principal.Banco;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import entidades.Edital;
import entidades.EntidadeIF;
import excecoes.ClasseInvalidaException;

/*
 TABLE `edital`
 `id_edital` INT NOT NULL AUTO_INCREMENT,
 `numero_ano` VARCHAR(6) NOT NULL,
 `dt_inicio_inscricoes` DATE NOT NULL,
 `dt_fim_inscricoes` DATE NOT NULL,
 `dt_relatorio_parcial` DATE NOT NULL,
 `dt_relatorio_final` DATE NOT NULL,
 `nr_vagas` INT NOT NULL,
 `vl_bolsa_discente` DOUBLE NOT NULL,
 `vl_bolsa_docente` DOUBLE NOT NULL,
 `tp_edital` CHAR NOT NULL,
 `programa_institucional_id`
 */
//TODO: implements DAO
public class EditalDAO  {

	// a conexão com o banco de dados
	public Connection connection;

	public EditalDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}

	public void insert(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Edital) {

			Edital edital = (Edital) entidade;

			try {

				// Define um insert com os atributos e cada valor é representado
				// por ?
				String sql = "INSERT INTO `edital` (`numero_ano`, `dt_inicio_inscricoes`, `dt_fim_inscricoes`, `dt_relatorio_parcial`, `dt_relatorio_final`, `nr_vagas`, `vl_bolsa_discente`, `vl_bolsa_docente`, `tp_edital`, `programa_institucional_id`)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setString(1, edital.getNumeroAno());
				stmt.setDate(2, edital.getInicioInscricoes());
				stmt.setDate(3, edital.getFimInscricoes());
				stmt.setDate(4, edital.getRelatorioParcial());
				stmt.setDate(5, edital.getRelatorioFinal());
				stmt.setInt(6, edital.getVagas());
				stmt.setDouble(7, edital.getBolsaDiscente());
				stmt.setDouble(8, edital.getBolsaDocente());
				stmt.setString(9, edital.getTipoEdital());
				stmt.setInt(10, edital.getProgramaInstitucionalId());

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

		if (entidade instanceof Edital) {

			Edital edital = (Edital) entidade;

			try {

				String sql = String.format("%s %d",
						"SELECT * FROM `edital` WHERE `id_edital` =",
						edital.getIdEdital());

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					edital.setNumeroAno(rs.getString("numero_ano"));
					edital.setInicioInscricoes(rs
							.getDate("dt_inicio_inscricoes"));
					edital.setFimInscricoes(rs.getDate("dt_fim_inscricoes"));
					edital.setRelatorioParcial(rs
							.getDate("dt_relatorio_parcial"));
					edital.setRelatorioFinal(rs.getDate("dt_relatorio_final"));
					edital.setVagas(rs.getInt("dt_relatorio_final"));
					edital.setBolsaDiscente(rs.getDouble("vl_bolsa_discente"));
					edital.setBolsaDocente(rs.getDouble("vl_bolsa_docente"));
					edital.setTipoEdital(rs.getString("tp_edital"));
					edital.setProgramaInstitucionalId(rs
							.getInt("programa_institucional_id"));
				}

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		} else {
			throw new ClasseInvalidaException();
		}

	}

	public void update(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Edital) {

			Edital edital = (Edital) entidade;

			try {

				// Define update setando cada atributo e cada valor é
				// representado por ?
				String sql = "UPDATE `edital` SET `numero_ano`=?, `dt_inicio_inscricoes`=?, `dt_fim_inscricoes`=?, `dt_relatorio_parcial`=?, `dt_relatorio_final`=?, `nr_vagas`=?, `vl_bolsa_discente`=?, `vl_bolsa_docente`=?, `tp_edital`=?, `programa_institucional_id`=? "
						+ "WHERE `id_edital`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setString(1, edital.getNumeroAno());
				stmt.setDate(2, edital.getInicioInscricoes());
				stmt.setDate(3, edital.getFimInscricoes());
				stmt.setDate(4, edital.getRelatorioParcial());
				stmt.setDate(5, edital.getRelatorioFinal());
				stmt.setInt(6, edital.getVagas());
				stmt.setDouble(7, edital.getBolsaDiscente());
				stmt.setDouble(8, edital.getBolsaDocente());
				stmt.setString(9, edital.getTipoEdital());
				stmt.setInt(10, edital.getProgramaInstitucionalId());
				stmt.setInt(11, edital.getIdEdital());

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

		if (entidade instanceof Edital) {

			Edital edital = (Edital) entidade;

			try {

				// Deleta uma tupla setando o atributo de identificação com
				// valor representado por ?
				String sql = "DELETE FROM `edital` WHERE `id_edital`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);
				// seta os valores
				stmt.setInt(1, edital.getIdEdital());
				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}