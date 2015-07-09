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
					.format("%s %s (%d, %d, '%s', '%s', '%s', " // essenciais
							+ " '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', " // datas
							+ " %d, %d, %d, %s, %d, %s, " // sobre participação
							+ " %d, %d)", // chaves estrangeiras
							"INSERT INTO tb_edital ("
									// essenciais
									+ " nr_edital,"
									+ " nr_ano,"
									+ " nm_numero_ano, "	
									+ " nm_titulo, "
									+ " nm_descricao,"
									// datas
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
									// sobre participação
									+ " nr_projetos_aprovados, "
									+ " nr_vagas_discentes_bolsistas,"
									+ " nr_vagas_voluntarios,"
									+ " vl_bolsa_discente,"
									+ " nr_vagas_docentes_bolsistas,"
									+ " vl_bolsa_docente,"
									// chaves estrangeiras
									+ " pessoa_id, "
									+ " programa_institucional_id)",
							"VALUES",
							// essenciais
							edital.getNumero(),
							edital.getAno(),
							edital.getNumAno(),
							edital.getTitulo(),
							edital.getDescricao(),
							// datas
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
							// sobre participação
							edital.getQuantidadeProjetosAprovados(),
							edital.getVagasBolsistasDiscentePorProjeto(),
							edital.getVagasVoluntariosPorProjeto(),
							edital.getBolsaDiscente(),
							edital.getVagasBolsistasDocentePorProjeto(),
							edital.getBolsaDocente(),
							// chaves estrangeiras
							edital.getGestor().getPessoaId(),
							edital.getProgramaInstitucional().getIdProgramaInstitucional());

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
								// essenciais
								+ " nr_edital=?,"
								+ " nr_ano=?,"
								+ " nm_numero_ano=?, "
								+ " nm_titulo=?, "
								+ " nm_descricao=?,"
								// datas
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
								// sobre participação
								+ " nr_projetos_aprovados=?, "
								+ " nr_vagas_discentes_bolsistas=?,"
								+ " nr_vagas_voluntarios=?,"
								+ " vl_bolsa_discente=?,"
								+ " nr_vagas_docentes_bolsistas=?,"
								+ " vl_bolsa_docente=?,"
								// chaves estrangeiras
								+ " pessoa_id=?,"
								+ " programa_institucional_id=? "
								+ " WHERE id_edital=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			// essenciais
			stmt.setInt(1, edital.getNumero());
			stmt.setInt(2, edital.getAno());
			stmt.setString(3, edital.getNumAno());
			stmt.setString(4, edital.getTitulo());
			stmt.setString(5, edital.getDescricao());
			// datas
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
			// sobre participação
			stmt.setInt(16, edital.getQuantidadeProjetosAprovados());
			stmt.setInt(17, edital.getVagasBolsistasDiscentePorProjeto());
			stmt.setInt(18, edital.getVagasVoluntariosPorProjeto());
			stmt.setDouble(19, edital.getBolsaDiscente());
			stmt.setInt(20, edital.getVagasBolsistasDocentePorProjeto());
			stmt.setDouble(21, edital.getBolsaDocente());
			// chaves estrangeiras
			stmt.setInt(22, edital.getGestor().getPessoaId());
			stmt.setInt(23, edital.getProgramaInstitucional().getIdProgramaInstitucional());
			stmt.setInt(24, edital.getIdEdital());
			stmt.execute();

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}

	}

	// TODO: Rever essa função, pois se excluir o edital tem que excluir os
	// projetos associados a ele. Trigger pra resolver isso.
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
							"SELECT "
								// essenciais
								+ " edital.id_edital,"
								+ " edital.nr_edital,"
								+ " edital.nr_ano,"
								+ " edital.nm_numero_ano, "	
								+ " edital.nm_titulo, "
								+ " edital.nm_descricao,"
								// datas
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
								// sobre participação
								+ " edital.nr_projetos_aprovados, "
								+ " edital.nr_vagas_discentes_bolsistas,"
								+ " edital.nr_vagas_voluntarios,"
								+ " edital.vl_bolsa_discente,"
								+ " edital.nr_vagas_docentes_bolsistas,"
								+ " edital.vl_bolsa_docente,"
								// chaves estrangeiras
								+ " edital.pessoa_id, "
								+ " edital.programa_institucional_id,"
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
							"SELECT "
								// essenciais
								+ " edital.id_edital,"
								+ " edital.nr_edital,"
								+ " edital.nr_ano,"
								+ " edital.nm_numero_ano, "	
								+ " edital.nm_titulo, "
								+ " edital.nm_descricao,"
								// datas
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
								// sobre participação
								+ " edital.nr_projetos_aprovados, "
								+ " edital.nr_vagas_discentes_bolsistas,"
								+ " edital.nr_vagas_voluntarios,"
								+ " edital.vl_bolsa_discente,"
								+ " edital.nr_vagas_docentes_bolsistas,"
								+ " edital.vl_bolsa_docente,"
								// chaves estrangeiras
								+ " edital.pessoa_id, "
								+ " edital.programa_institucional_id,"
								+ " edital.dt_registro "
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
							"SELECT "
								// essenciais
								+ " edital.id_edital,"
								+ " edital.nr_edital,"
								+ " edital.nr_ano,"
								+ " edital.nm_numero_ano, "	
								+ " edital.nm_titulo, "
								+ " edital.nm_descricao,"
								// datas
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
								// sobre participação
								+ " edital.nr_projetos_aprovados, "
								+ " edital.nr_vagas_discentes_bolsistas,"
								+ " edital.nr_vagas_voluntarios,"
								+ " edital.vl_bolsa_discente,"
								+ " edital.nr_vagas_docentes_bolsistas,"
								+ " edital.vl_bolsa_docente,"
								// chaves estrangeiras
								+ " edital.pessoa_id, "
								+ " edital.programa_institucional_id,"
								+ " edital.dt_registro "
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
					.format("%s %s",
							"SELECT "
								// essenciais
								+ " edital.id_edital,"
								+ " edital.nr_edital,"
								+ " edital.nr_ano,"
								+ " edital.nm_numero_ano, "	
								+ " edital.nm_titulo, "
								+ " edital.nm_descricao,"
								// datas
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
								// sobre participação
								+ " edital.nr_projetos_aprovados, "
								+ " edital.nr_vagas_discentes_bolsistas,"
								+ " edital.nr_vagas_voluntarios,"
								+ " edital.vl_bolsa_discente,"
								+ " edital.nr_vagas_docentes_bolsistas,"
								+ " edital.vl_bolsa_docente,"
								// chaves estrangeiras
								+ " edital.pessoa_id, "
								+ " edital.programa_institucional_id,"
								+ " edital.dt_registro "
								+ " FROM tb_edital edital "
								+ " WHERE edital.nm_numero_ano =", edital.getNumAno());

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
					"SELECT "
						// essenciais
						+ " edital.id_edital,"
						+ " edital.nr_edital,"
						+ " edital.nr_ano,"
						+ " edital.nm_numero_ano, "	
						+ " edital.nm_titulo, "
						+ " edital.nm_descricao,"
						// datas
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
						// sobre participação
						+ " edital.nr_projetos_aprovados, "
						+ " edital.nr_vagas_discentes_bolsistas,"
						+ " edital.nr_vagas_voluntarios,"
						+ " edital.vl_bolsa_discente,"
						+ " edital.nr_vagas_docentes_bolsistas,"
						+ " edital.vl_bolsa_docente,"
						// chaves estrangeiras
						+ " edital.pessoa_id, "
						+ " edital.programa_institucional_id,"
						+ " edital.dt_registro "
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
	
	public int getProximoNumero(int ano) throws SQLExceptionQManager {

		int proximoNumero = 1;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT sequencia_nr_edital.nr_sequencia_edital" 
					+ " FROM tb_sequencia_nr_edital sequencia_nr_edital"
					+ " WHERE sequencia_nr_edital.nr_ano = " + ano;

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int i = rs.getInt("sequencia_nr_edital.nr_sequencia_edital");
				proximoNumero += i;
			}

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return proximoNumero;
	}
	
	@Override
	public List<Edital> convertToList(ResultSet rs) throws SQLExceptionQManager {

		List<Edital> editais = new ArrayList<Edital>();

		try {

			while (rs.next()) {

				Edital edital = new Edital();
				
				// chaves estrangeiras
				edital.getProgramaInstitucional().setIdProgramaInstitucional(
						rs.getInt("edital.programa_institucional_id"));

				edital.getGestor().setPessoaId(rs.getInt("edital.pessoa_id"));

				// essenciais
				edital.setIdEdital(rs.getInt("edital.id_edital"));
				edital.setNumero(rs.getInt("edital.nr_edital"));
				edital.setAno(rs.getInt("edital.nr_ano"));
				edital.setTitulo(rs.getString("edital.nm_titulo"));
				edital.setDescricao(rs.getString("edital.nm_descricao"));
				// datas
				edital.setInicioInscricoes(rs.getDate("edital.dt_inicio_inscricoes"));
				edital.setFimInscricoes(rs.getDate("edital.dt_fim_inscricoes"));
				edital.setInicioAvaliacao(rs.getDate("edital.dt_inicio_avaliacao"));
				edital.setFimAvaliacao(rs.getDate("edital.dt_fim_avaliacao"));
				edital.setResultadoPreliminar(rs.getDate("edital.dt_resultado_preliminar"));
				edital.setReceberRecursos(rs.getDate("edital.dt_receber_recursos"));
				edital.setResultadoFinal(rs.getDate("edital.dt_resultado_final"));
				edital.setInicioAtividades(rs.getDate("edital.dt_inicio_atividades"));
				edital.setRelatorioParcial(rs.getDate("edital.dt_relatorio_parcial"));
				edital.setRelatorioFinal(rs.getDate("edital.dt_relatorio_final"));
				// sobre participação
				edital.setQuantidadeProjetosAprovados(rs.getInt("edital.nr_projetos_aprovados"));
				edital.setVagasBolsistasDiscentePorProjeto(rs.getInt("edital.nr_vagas_discentes_bolsistas"));
				edital.setVagasVoluntariosPorProjeto(rs.getInt("edital.nr_vagas_voluntarios"));
				edital.setBolsaDiscente(rs.getDouble("edital.vl_bolsa_discente"));
				edital.setVagasBolsistasDocentePorProjeto(rs.getInt("edital.nr_vagas_docentes_bolsistas"));
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