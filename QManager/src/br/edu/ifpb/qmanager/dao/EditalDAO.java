package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import principal.Banco;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

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
public class EditalDAO implements GenericDAO<Integer, Edital> {

	// a conexão com o banco de dados
	public Connection connection;

	public EditalDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}

	@Override
	public Edital getById(Integer id) throws QManagerSQLException {

		Edital edital = null;

		try {

			String sql = String.format("%s %d",
					"SELECT * FROM `edital` WHERE `id_edital` =", id);

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Edital> editais = convertToList(rs);

			edital = editais.get(0);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode());
		}

		return edital;

	}

	@Override
	public int insert(Edital edital) throws QManagerSQLException {

		int chave = 0;

		try {

			// Define um insert com os atributos e cada valor é representado
			// por ?
			String sql = String
					.format("%s %s ('%s', '%s', '%s', '%s', '%s', %d, %lf, %lf, '%s', '%d')",
							"INSERT INTO `edital` (`numero_ano`, `dt_inicio_inscricoes`, `dt_fim_inscricoes`, `dt_relatorio_parcial`, `dt_relatorio_final`, `nr_vagas`, `vl_bolsa_discente`, `vl_bolsa_docente`, `tp_edital`, `programa_institucional_id`)",
							"VALUES", edital.getNumeroAno(),
							edital.getInicioInscricoes(),
							edital.getFimInscricoes(),
							edital.getRelatorioParcial(),
							edital.getRelatorioFinal(), edital.getVagas(),
							edital.getBolsaDiscente(),
							edital.getBolsaDocente(), edital.getTipoEdital(),
							edital.getProgramaInstitucionalId());

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// envia para o Banco e fecha o objeto
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			chave = BancoUtil.getGenerateKey(stmt);

			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode());
		}

		return chave;

	}

	@Override
	public void update(Edital edital) throws QManagerSQLException {

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
			throw new QManagerSQLException(sqle.getErrorCode());
		}

	}

	@Override
	public void delete(Edital edital) throws QManagerSQLException {

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

	@Override
	public List<Edital> findAll() throws QManagerSQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Edital> convertToList(ResultSet rs) throws QManagerSQLException {

		List<Edital> editais = new ArrayList<Edital>();

		Edital edital = new Edital();

		try {

			while (rs.next()) {
				edital.setNumeroAno(rs.getString("numero_ano"));
				edital.setInicioInscricoes(rs.getDate("dt_inicio_inscricoes"));
				edital.setFimInscricoes(rs.getDate("dt_fim_inscricoes"));
				edital.setRelatorioParcial(rs.getDate("dt_relatorio_parcial"));
				edital.setRelatorioFinal(rs.getDate("dt_relatorio_final"));
				edital.setVagas(rs.getInt("dt_relatorio_final"));
				edital.setBolsaDiscente(rs.getDouble("vl_bolsa_discente"));
				edital.setBolsaDocente(rs.getDouble("vl_bolsa_docente"));
				edital.setTipoEdital(rs.getString("tp_edital"));
				edital.setProgramaInstitucionalId(rs
						.getInt("programa_institucional_id"));

				editais.add(edital);
				
			}


		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode());
		}

		return editais;
	}

}