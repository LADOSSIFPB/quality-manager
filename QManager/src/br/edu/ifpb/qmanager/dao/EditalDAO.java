package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class EditalDAO implements GenericDAO<Integer, Edital> {

	// a conexão com o banco de dados
	public Connection connection;
	private DatabaseConnection banco;

	public EditalDAO(DatabaseConnection banco) {
		this.banco = banco;
		this.connection = (Connection) banco.getConnection();
	}

	@Override
	public Edital getById(Integer id) throws QManagerSQLException {

		Edital edital = null;

		try {

			String sql = String.format("%s %d",
					"SELECT * FROM `tb_edital` WHERE `id_edital` =", id);

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Edital> editais = convertToList(rs);

			edital = editais.get(0);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
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
					.format("%s %s ('%s', %d, %d, '%s', '%s', '%s', '%s', %d, %s, %s, '%c', '%d')",
							"INSERT INTO `tb_edital` (`ar_edital`, `nr_edital`, `nr_ano`, `dt_inicio_inscricoes`, `dt_fim_inscricoes`, `dt_relatorio_parcial`, `dt_relatorio_final`, `nr_vagas`, `vl_bolsa_discente`, `vl_bolsa_docente`, `tp_edital`, `programa_institucional_id`)",
							"VALUES", edital.getArquivo(), edital.getNumero(), edital.getAno(),
							edital.getInicioInscricoes(), edital
									.getFimInscricoes(), edital
									.getRelatorioParcial(), edital
									.getRelatorioFinal(), edital.getVagas(),
							edital.getBolsaDiscente(),
							edital.getBolsaDocente(), edital.getTipoEdital(),
							edital.getProgramaInstitucional()
									.getIdProgramaInstitucional());

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// envia para o Banco e fecha o objeto
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
	public void update(Edital edital) throws QManagerSQLException {

		try {

			// Define update setando cada atributo e cada valor é
			// representado por ?
			String sql = String.format("%s %d %s %d %s %s %s %s %s %s %s %s %s %d %s %lf %s %lf %s %s %s %d %s %d",
					"UPDATE `tb_edital` SET `nr_edital`=", edital.getNumero(),
					", `nr_ano`=", edital.getAno(),
					", `dt_inicio_inscricoes`=", edital.getInicioInscricoes()
							.toString(), ", `dt_fim_inscricoes`=", edital
							.getFimInscricoes().toString(),
					", `dt_relatorio_parcial`=", edital.getRelatorioParcial()
							.toString(), ", `dt_relatorio_final`=", edital
							.getRelatorioFinal().toString(), ", `nr_vagas`=",
					edital.getVagas(), ", `vl_bolsa_discente`=", edital
							.getBolsaDiscente(), ", `vl_bolsa_docente`=",
					edital.getBolsaDocente(), ", `tp_edital`=", edital
							.getTipoEdital(), ", `programa_institucional_id`=",
					edital.getProgramaInstitucional()
							.getIdProgramaInstitucional(),
					"WHERE `id_edital`=", edital.getIdEdital());

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

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
			String sql = "DELETE FROM `tb_edital` WHERE `id_edital`=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			// seta os valores
			stmt.setInt(1, id);
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
		ProgramaInstitucional programaInstitucional = new ProgramaInstitucional();
		ProgramaInstitucionalDAO programaInstitucionalDAO = new ProgramaInstitucionalDAO(banco);

		try {

			while (rs.next()) {
				edital.setIdEdital(rs.getInt("id_edital"));
				edital.setNumero(rs.getInt("nr_edital"));
				edital.setAno(rs.getInt("nr_ano"));
				edital.setInicioInscricoesSQL(rs.getDate("dt_inicio_inscricoes"));
				edital.setFimInscricoesSQL(rs.getDate("dt_fim_inscricoes"));
				edital.setRelatorioParcialSQL(rs.getDate("dt_relatorio_parcial"));
				edital.setRelatorioFinalSQL(rs.getDate("dt_relatorio_final"));
				edital.setVagas(rs.getInt("dt_relatorio_final"));
				edital.setBolsaDiscente(rs.getDouble("vl_bolsa_discente"));
				edital.setBolsaDocente(rs.getDouble("vl_bolsa_docente"));
				edital.setTipoEdital(rs.getString("tp_edital").charAt(0));
				
				programaInstitucional = programaInstitucionalDAO.getById(rs.getInt("programa_institucional_id"));
				edital.setProgramaInstitucional(programaInstitucional);

				editais.add(edital);

			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return editais;
	}

}