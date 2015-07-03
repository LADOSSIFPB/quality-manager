package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class EditalDAO implements GenericDAO<Integer, Edital> {

	static DBPool banco;
	
	private static EditalDAO instance;
	
	public Connection connection;

	public static EditalDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new EditalDAO(banco);
		return instance;
	}

	public EditalDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Edital edital) throws SQLExceptionQManager {

		int idEdital = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {
			
			String sql = String
					.format("%s %s ('lembre_do_arquivo', %d, %d, '%s', '%s', '%s', '%s', "
							+ " '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d, %s, '%s',"
							+ " %d, %d, %d)",
							"INSERT INTO tb_edital (ar_edital,"
									+ " nr_edital,"
									+ " nr_ano,"
									+ " nm_titulo, "
									+ " nm_descricao,"
									+ " dt_inicio_inscricoes,"
									+ " dt_fim_inscricoes,"
									+ " dt_inicio_avaliacao,"
									+ " dt_fim_avaliacao,"
									+ " dt_resultado_preliminar,"
									+ " dt_receber_recursos,"
									+ " dt_resultado_final,"
									+ " dt_inicio_atividades,"
									+ " dt_relatorio_parcial,"
									+ " dt_relatorio_final,"
									+ " nr_vagas, "
									+ " vl_bolsa_discente,"
									+ " vl_bolsa_docente,"
									+ " pessoa_id, "
									+ " tipo_edital_id,"
									+ " programa_institucional_id)",
							"VALUES",
							edital.getNumero(),
							edital.getAno(),
							edital.getTitulo(),
							edital.getDescricao(),
							new Date(edital.getInicioInscricoes().getTime()),
							new Date(edital.getFimInscricoes().getTime()),
							new Date(edital.getInicioAvaliacao().getTime()),
							new Date(edital.getFimAvaliacao().getTime()),
							new Date(edital.getResultadoPreliminar().getTime()),
							new Date(edital.getReceberRecursos().getTime()),
							new Date(edital.getResultadoFinal().getTime()),
							new Date(edital.getInicioAtividades().getTime()),
							new Date(edital.getRelatorioParcial().getTime()),
							new Date(edital.getRelatorioFinal().getTime()),
							edital.getVagas(),
							edital.getBolsaDiscente(),
							edital.getBolsaDocente(),
							edital.getGestor().getPessoaId(),
							edital.getProgramaInstitucional().getIdProgramaInstitucional(),
							edital.getTipoEdital().getIdTipoEdital());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idEdital = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}

		return idEdital;
	}

	@Override
	public void update(Edital edital) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_edital SET "
								+ " ar_edital=?,"
								+ " nr_edital=?,"
								+ " nr_ano=?,"
								+ " nm_titulo=?, "
								+ " nm_descricao=?,"
								+ " dt_inicio_inscricoes=?,"
								+ " dt_fim_inscricoes=?,"
								+ " dt_inicio_avaliacao=?,"
								+ " dt_fim_avaliacao=?,"
								+ " dt_resultado_preliminar=?,"
								+ " dt_receber_recursos=?,"
								+ " dt_resultado_final=?,"
								+ " dt_inicio_atividades=?,"
								+ " dt_relatorio_parcial=?,"
								+ " dt_relatorio_final=?,"
								+ " nr_vagas=?, "
								+ " vl_bolsa_discente=?,"
								+ " vl_bolsa_docente=?,"
								+ " pessoa_id=?,"
								+ " tipo_edital_id=?, "
								+ " programa_institucional_id=? "
								+ " WHERE id_edital=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			// TODO: Submeter o arquivo.
			stmt.setString(1, "lembre_do_aqrquivo");
			stmt.setInt(2, edital.getNumero());
			stmt.setInt(3, edital.getAno());
			stmt.setString(4, edital.getTitulo());
			stmt.setString(5, edital.getDescricao());
			stmt.setDate(6, new Date(edital.getInicioInscricoes().getTime()));
			stmt.setDate(7, new Date(edital.getFimInscricoes().getTime()));
			stmt.setDate(8, new Date(edital.getInicioAvaliacao().getTime()));
			stmt.setDate(9, new Date(edital.getFimAvaliacao().getTime()));
			stmt.setDate(10, new Date(edital.getResultadoPreliminar().getTime()));
			stmt.setDate(11, new Date(edital.getReceberRecursos().getTime()));
			stmt.setDate(12, new Date(edital.getResultadoFinal().getTime()));
			stmt.setDate(13, new Date(edital.getInicioAtividades().getTime()));
			stmt.setDate(14, new Date(edital.getRelatorioParcial().getTime()));
			stmt.setDate(15, new Date(edital.getRelatorioFinal().getTime()));
			stmt.setInt(16, edital.getVagas());
			stmt.setDouble(17, edital.getBolsaDiscente());
			stmt.setDouble(18, edital.getBolsaDocente());
			stmt.setInt(19, edital.getGestor().getPessoaId());
			stmt.setInt(20, edital.getTipoEdital().getIdTipoEdital());
			stmt.setInt(21, edital.getProgramaInstitucional().getIdProgramaInstitucional());
			stmt.setInt(22, edital.getIdEdital());
			stmt.execute();

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}

	}

	// TODO: Rever essa função, pois se excluir o edital tem que excluir os
	// projetos associados a ele
	@Override
	public void delete(Integer id) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "DELETE FROM tb_edital WHERE id_edital=?";

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
	public List<Edital> getAll() throws SQLExceptionQManager {
		List<Edital> editais;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s",
							"SELECT edital.id_edital,"
								+ " edital.ar_edital,"
								+ " edital.nr_edital,"
								+ " edital.nr_ano,"
								+ " edital.nm_titulo, "
								+ " edital.nm_descricao,"
								+ " edital.dt_inicio_inscricoes,"
								+ " edital.dt_fim_inscricoes,"
								+ " edital.dt_inicio_avaliacao,"
								+ " edital.dt_fim_avaliacao,"
								+ " edital.dt_resultado_preliminar,"
								+ " edital.dt_receber_recursos,"
								+ " edital.dt_resultado_final,"
								+ " edital.dt_inicio_atividades,"
								+ " edital.dt_relatorio_parcial,"
								+ " edital.dt_relatorio_final,"
								+ " edital.nr_vagas,"
								+ " edital.vl_bolsa_discente,"
								+ " edital.vl_bolsa_docente,"
								+ " edital.programa_institucional_id,"
								+ " edital.pessoa_id,"
								+ " edital.tipo_edital_id,"
								+ " edital.dt_registro "
								+ " FROM tb_edital edital");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			editais = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return editais;
	}

	@Override
	public Edital getById(Integer id) throws SQLExceptionQManager {

		Edital edital = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT edital.id_edital,"
								+ " edital.ar_edital,"
								+ " edital.nr_edital,"
								+ " edital.nr_ano,"
								+ " edital.nm_titulo, "
								+ " edital.nm_descricao,"
								+ " edital.dt_inicio_inscricoes,"
								+ " edital.dt_fim_inscricoes,"
								+ " edital.dt_inicio_avaliacao,"
								+ " edital.dt_fim_avaliacao,"
								+ " edital.dt_resultado_preliminar,"
								+ " edital.dt_receber_recursos,"
								+ " edital.dt_resultado_final,"
								+ " edital.dt_inicio_atividades,"
								+ " edital.dt_relatorio_parcial,"
								+ " edital.dt_relatorio_final,"
								+ " edital.nr_vagas,"
								+ " edital.vl_bolsa_discente,"
								+ " edital.vl_bolsa_docente,"
								+ " edital.programa_institucional_id,"
								+ " edital.pessoa_id,"
								+ " edital.tipo_edital_id,"
								+ " edital.dt_registro"
								+ " FROM tb_edital edital "
								+ " WHERE edital.id_edital =", id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Edital> editais = convertToList(rs);

			if (!editais.isEmpty())
				edital = editais.get(0);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return edital;
	}

	public List<Edital> getByProgramaInstitucional(
			ProgramaInstitucional programaInstitucional)
			throws SQLExceptionQManager {
		List<Edital> editais = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT edital.id_edital,"
								+ " edital.ar_edital,"
								+ " edital.nr_edital,"
								+ " edital.nr_ano,"
								+ " edital.nm_titulo, "
								+ " edital.nm_descricao,"
								+ " edital.dt_inicio_inscricoes,"
								+ " edital.dt_fim_inscricoes,"
								+ " edital.dt_inicio_avaliacao,"
								+ " edital.dt_fim_avaliacao,"
								+ " edital.dt_resultado_preliminar,"
								+ " edital.dt_receber_recursos,"
								+ " edital.dt_resultado_final,"
								+ " edital.dt_inicio_atividades,"
								+ " edital.dt_relatorio_parcial,"
								+ " edital.dt_relatorio_final,"
								+ " edital.nr_vagas,"
								+ " edital.vl_bolsa_discente,"
								+ " edital.vl_bolsa_docente,"
								+ " edital.programa_institucional_id,"
								+ " edital.pessoa_id,"
								+ " edital.tipo_edital_id,"
								+ " edital.dt_registro"
								+ " FROM tb_edital edital,"
								+ " INNER JOIN tb_programa_institucional programa_institucional"
								+ " ON edital.programa_institucional_id = programa_institucional.id_programa_institucional"
								+ " WHERE edital.programa_institucional_id =",
							programaInstitucional.getIdProgramaInstitucional());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			editais = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return editais;
	}

	@Override
	public List<Edital> find(Edital edital) throws SQLExceptionQManager {
		List<Edital> editais = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d %s %d",
							"SELECT edital.id_edital,"
								+ " edital.ar_edital,"
								+ " edital.nr_edital,"
								+ " edital.nr_ano,"
								+ " edital.nm_titulo, "
								+ " edital.nm_descricao,"
								+ " edital.dt_inicio_inscricoes,"
								+ " edital.dt_fim_inscricoes,"
								+ " edital.dt_inicio_avaliacao,"
								+ " edital.dt_fim_avaliacao,"
								+ " edital.dt_resultado_preliminar,"
								+ " edital.dt_receber_recursos,"
								+ " edital.dt_resultado_final,"
								+ " edital.dt_inicio_atividades,"
								+ " edital.dt_relatorio_parcial,"
								+ " edital.dt_relatorio_final,"
								+ " edital.nr_vagas,"
								+ " edital.vl_bolsa_discente,"
								+ " edital.vl_bolsa_docente,"
								+ " edital.programa_institucional_id,"
								+ " edital.pessoa_id,"
								+ " edital.tipo_edital_id,"
								+ " edital.dt_registro"
								+ " FROM tb_edital edital "
								+ "WHERE edital.nr_ano =", edital.getAno(),
							"OR edital.nr_edital =", edital.getNumero());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			editais = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return editais;
	}

	public List<Integer> getAnosEditais() throws SQLExceptionQManager {

		List<Integer> anosEditais = new ArrayList<Integer>();

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT edital.nr_ano" 
					+ " FROM tb_edital edital"
					+ " GROUP BY edital.nr_ano";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int i = rs.getInt("edital.nr_ano");
				anosEditais.add(i);
			}

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return anosEditais;
	}

	public List<Edital> getByAno(int ano) throws SQLExceptionQManager {

		List<Edital> editais;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d", 
					"SELECT edital.id_edital,"
							+ " edital.ar_edital,"
							+ " edital.nr_edital,"
							+ " edital.nr_ano,"
							+ " edital.nm_titulo, "
							+ " edital.nm_descricao,"
							+ " edital.dt_inicio_inscricoes,"
							+ " edital.dt_fim_inscricoes,"
							+ " edital.dt_inicio_avaliacao,"
							+ " edital.dt_fim_avaliacao,"
							+ " edital.dt_resultado_preliminar,"
							+ " edital.dt_receber_recursos,"
							+ " edital.dt_resultado_final,"
							+ " edital.dt_inicio_atividades,"
							+ " edital.dt_relatorio_parcial,"
							+ " edital.dt_relatorio_final,"
							+ " edital.nr_vagas,"
							+ " edital.vl_bolsa_discente,"
							+ " edital.vl_bolsa_docente,"
							+ " edital.programa_institucional_id,"
							+ " edital.pessoa_id,"
							+ " edital.tipo_edital_id,"
							+ " edital.dt_registro"
							+ " FROM tb_edital edital"
							+ " WHERE edital.nr_ano = ", ano);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			editais = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return editais;
	}

	@Override
	public List<Edital> convertToList(ResultSet rs) throws SQLExceptionQManager {

		List<Edital> editais = new ArrayList<Edital>();

		try {

			while (rs.next()) {

				Edital edital = new Edital();

				edital.getProgramaInstitucional().setIdProgramaInstitucional(
						rs.getInt("edital.programa_institucional_id"));

				edital.setTipoEdital(TipoEditalDAO.getInstance()
						.getById(rs.getInt("edital.tipo_edital_id")));

				edital.getGestor().setPessoaId(rs.getInt("edital.pessoa_id"));

				edital.setIdEdital(rs.getInt("edital.id_edital"));
				edital.setArquivo(rs.getString("edital.ar_edital"));
				edital.setNumero(rs.getInt("edital.nr_edital"));
				edital.setAno(rs.getInt("edital.nr_ano"));
				edital.setTitulo(rs.getString("edital.nm_titulo"));
				edital.setDescricao(rs.getString("edital.nm_descricao"));
				edital.setInicioInscricoes(rs.getDate("edital.dt_inicio_inscricoes"));
				edital.setFimInscricoes(rs.getDate("edital.dt_fim_inscricoes"));
				edital.setFimInscricoes(rs.getDate("edital.dt_inicio_avaliacao"));
				edital.setFimInscricoes(rs.getDate("edital.dt_fim_avaliacao"));
				edital.setFimInscricoes(rs.getDate("edital.dt_resultado_preliminar"));
				edital.setFimInscricoes(rs.getDate("edital.dt_receber_recursos"));
				edital.setFimInscricoes(rs.getDate("edital.dt_resultado_final"));
				edital.setFimInscricoes(rs.getDate("edital.dt_inicio_atividades"));
				edital.setRelatorioParcial(rs.getDate("edital.dt_relatorio_parcial"));
				edital.setRelatorioFinal(rs.getDate("edital.dt_relatorio_final"));
				edital.setVagas(rs.getInt("edital.nr_vagas"));
				edital.setBolsaDiscente(rs.getDouble("edital.vl_bolsa_discente"));
				edital.setBolsaDocente(rs.getDouble("edital.vl_bolsa_docente"));
				edital.setRegistro(rs.getDate("edital.dt_registro"));

				editais.add(edital);
			}

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return editais;
	}
}