package br.edu.ifpb.qmanager.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Gestor;
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
	public int insert(Edital edital) throws QManagerSQLException {

		int chave = 0;

		try {

			// TODO: rever o causo do arquivo
			String sql = String
					.format("%s %s ('lembre_do_aqrquivo', %d, %d, '%s', '%s', '%s', '%s', %d, %s, %s, '%c', '%d', '%d')",
							"INSERT INTO `tb_edital` (`ar_edital`, `nr_edital`, `nr_ano`, "
									+ "`dt_inicio_inscricoes`, `dt_fim_inscricoes`, `dt_relatorio_parcial`, "
									+ "`dt_relatorio_final`, `nr_vagas`, `vl_bolsa_discente`, "
									+ "`vl_bolsa_docente`, `tp_edital`, `pessoa_id`, `programa_institucional_id`)",
							"VALUES", edital.getNumero(),
							edital.getAno(), new Date(edital
									.getInicioInscricoes().getTime()),
							new Date(edital.getFimInscricoes().getTime()),
							new Date(edital.getRelatorioParcial().getTime()),
							new Date(edital.getRelatorioFinal().getTime()),
							edital.getVagas(), edital.getBolsaDiscente(),
							edital.getBolsaDocente(), edital.getTipoEdital(),
							edital.getGestor().getPessoaId(), edital
									.getProgramaInstitucional()
									.getIdProgramaInstitucional());

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
	public void update(Edital edital) throws QManagerSQLException {

		try {

			String sql = "UPDATE `tb_edital` SET `nr_edital`=?, `nr_ano`=?, "
					+ "`dt_inicio_inscricoes`=?, `dt_fim_inscricoes`=?, `dt_relatorio_parcial`=?, "
					+ "`dt_relatorio_final`=?, `nr_vagas`=?, `vl_bolsa_discente`=?, "
					+ "`vl_bolsa_docente`=?, `tp_edital`=?, `programa_institucional_id`=? "
					+ "WHERE `id_edital`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, edital.getNumero());
			stmt.setInt(2, edital.getAno());
			stmt.setDate(3, new Date(edital.getInicioInscricoes().getTime()));
			stmt.setDate(4, new Date(edital.getFimInscricoes().getTime()));
			stmt.setDate(5, new Date(edital.getRelatorioParcial().getTime()));
			stmt.setDate(6, new Date(edital.getRelatorioFinal().getTime()));
			stmt.setInt(7, edital.getVagas());
			stmt.setDouble(8, edital.getBolsaDiscente());
			stmt.setDouble(9, edital.getBolsaDocente());
			stmt.setString(10, String.valueOf(edital.getTipoEdital()));
			stmt.setInt(11, edital.getProgramaInstitucional()
					.getIdProgramaInstitucional());
			stmt.setInt(12, edital.getIdEdital());

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

			String sql = "DELETE FROM `tb_edital` WHERE `id_edital`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

	@Override
	public List<Edital> getAll() throws QManagerSQLException {
		List<Edital> editais;

		try {

			String sql = String.format("%s", "SELECT * FROM `tb_edital`");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			editais = convertToList(rs);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return editais;
	}

	@Override
	public Edital getById(Integer id) throws QManagerSQLException {

		Edital edital = null;

		try {

			String sql = String.format("%s %d",
					"SELECT * FROM `tb_edital` WHERE `id_edital` =", id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Edital> editais = convertToList(rs);

			if (!editais.isEmpty())
				edital = editais.get(0);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return edital;

	}

	public List<Edital> getByProgramaInstitucional(
			ProgramaInstitucional programaInstitucional)
			throws QManagerSQLException {
		List<Edital> editais;

		try {

			String sql = String
					.format("%s %d",
							"SELECT * FROM `tb_edital`, `tb_programa_institucional` "
									+ "WHERE programa_institucional_id = id_programa_institucional "
									+ "AND id_programa_institucional =",
							programaInstitucional.getIdProgramaInstitucional());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			editais = convertToList(rs);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return editais;
	}

	@Override
	public List<Edital> convertToList(ResultSet rs) throws QManagerSQLException {

		List<Edital> editais = new LinkedList<Edital>();

		ProgramaInstitucionalDAO programaInstitucionalDAO = new ProgramaInstitucionalDAO(
				banco);
		GestorDAO gestorDAO = new GestorDAO(banco);

		try {

			while (rs.next()) {
				Edital edital = new Edital();
				ProgramaInstitucional programaInstitucional = new ProgramaInstitucional();
				Gestor gestor = new Gestor();
				edital.setIdEdital(rs.getInt("id_edital"));
				edital.setArquivo(rs.getString("ar_edital"));
				edital.setNumero(rs.getInt("nr_edital"));
				edital.setAno(rs.getInt("nr_ano"));
				edital.setInicioInscricoes(rs.getDate("dt_inicio_inscricoes"));
				edital.setFimInscricoes(rs.getDate("dt_fim_inscricoes"));
				edital.setRelatorioParcial(rs.getDate("dt_relatorio_parcial"));
				edital.setRelatorioFinal(rs.getDate("dt_relatorio_final"));
				edital.setVagas(rs.getInt("nr_vagas"));
				edital.setBolsaDiscente(rs.getDouble("vl_bolsa_discente"));
				edital.setBolsaDocente(rs.getDouble("vl_bolsa_docente"));
				edital.setTipoEdital(rs.getString("tp_edital").charAt(0));
				edital.setRegistro(rs.getDate("dt_registro"));
				programaInstitucional = programaInstitucionalDAO.getById(rs
						.getInt("programa_institucional_id"));
				edital.setProgramaInstitucional(programaInstitucional);
				gestor = gestorDAO.getById(rs.getInt("pessoa_id"));
				edital.setGestor(gestor);
				programaInstitucional.setRegistro(rs.getDate("dt_registro"));

				editais.add(edital);

			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return editais;
	}

}