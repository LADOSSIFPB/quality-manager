package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.TipoProgramaInstitucional;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class ProjetoDAO implements GenericDAO<Integer, Projeto> {

	static DBPool banco;
	
	private static ProjetoDAO instance;
	
	public Connection connection;

	public static ProjetoDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new ProjetoDAO(banco);
		return instance;
	}

	public ProjetoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Projeto projeto) throws SQLExceptionQManager {

		int idProjeto = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {
			
			String sql = String
					.format("%s %s ('%s', '%s', '%s', '%s', '%s', %s, %d, %d)",
							"INSERT INTO tb_projeto (" 
									+ " nm_projeto,"
									+ " nm_resumo,"
									+ " dt_inicio_projeto,"
									+ " dt_fim_projeto," 
									+ " nr_processo,"
									+ " vl_orcamento,"
									+ " edital_id," 
									+ " local_id)", 
									" VALUES",
							projeto.getNomeProjeto(),
							projeto.getResumoProjeto(),
							new Date(projeto.getInicioProjeto().getTime()),
							new Date(projeto.getFimProjeto().getTime()),
							projeto.getProcesso(), 
							projeto.getOrcamento(),
							projeto.getEdital().getIdEdital(), 
							projeto.getCampus().getIdCampusInstitucional());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idProjeto = BancoUtil.getGenerateKey(stmt);

			projeto.setIdProjeto(idProjeto);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}

		return idProjeto;
	}

	@Override
	public void update(Projeto projeto) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_projeto" 
					+ " SET nm_projeto = ?,"
					+ " nm_resumo=?, "
					+ " dt_inicio_projeto = ?, "
					+ " dt_fim_projeto = ?,"
					+ " nr_processo = ?," 
					+ " vl_orcamento = ?,"
					+ " edital_id = ?,"
					+ " local_id = ?"
					+ " WHERE id_projeto = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, projeto.getNomeProjeto());
			stmt.setString(2, projeto.getResumoProjeto());
			stmt.setDate(3, new Date(projeto.getInicioProjeto().getTime()));
			stmt.setDate(4, new Date(projeto.getFimProjeto().getTime()));
			stmt.setString(5, projeto.getProcesso());
			stmt.setDouble(6, projeto.getOrcamento());
			stmt.setInt(7, projeto.getEdital().getIdEdital());
			stmt.setInt(8, projeto.getCampus().getIdCampusInstitucional());
			stmt.setInt(9, projeto.getIdProjeto());

			stmt.execute();

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}
	}

	// TODO: só é possível deletar o projeto se suas Participações forem
	// deletadas
	@Override
	public void delete(Integer id) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "DELETE FROM tb_projeto"
					+ " WHERE id_projeto = ?";

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
	public List<Projeto> getAll() throws SQLExceptionQManager {
		List<Projeto> projetos;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s",
							"SELECT projeto.id_projeto,"
									+ " projeto.nm_projeto, "
									+ " projeto.nm_resumo, "
									+ " projeto.dt_inicio_projeto,"
									+ " projeto.dt_fim_projeto,"
									+ " projeto.nr_processo,"
									+ " projeto.vl_orcamento,"
									+ " projeto.dt_registro,"
									+ " projeto.edital_id,"
									+ " projeto.local_id"
									+ " FROM tb_projeto projeto");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			projetos = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return projetos;
	}

	@Override
	public Projeto getById(Integer id) throws SQLExceptionQManager {

		Projeto projeto = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d", 
					"SELECT projeto.id_projeto,"
					+ " projeto.nm_projeto," 
					+ " projeto.nm_resumo, "
					+ " projeto.dt_inicio_projeto,"
					+ " projeto.dt_fim_projeto,"
					+ " projeto.nr_processo,"
					+ " projeto.vl_orcamento,"
					+ " projeto.dt_registro," 
					+ " projeto.edital_id,"
					+ " projeto.local_id"
					+ " FROM tb_projeto projeto"
					+ " WHERE projeto.id_projeto = ",
					id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Projeto> projetos = convertToList(rs);

			if (!projetos.isEmpty()) {
				projeto = projetos.get(0);
			}
			
		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return projeto;
	}

	public List<Projeto> getByEdital(Edital edital) throws SQLExceptionQManager {
		List<Projeto> projetos;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT projeto.id_projeto,"
									+ " projeto.nm_projeto,"
									+ " projeto.nm_resumo, "
									+ " projeto.dt_inicio_projeto,"
									+ " projeto.dt_fim_projeto,"
									+ " projeto.nr_processo,"
									+ " projeto.vl_orcamento,"
									+ " projeto.dt_registro,"
									+ " projeto.edital_id,"
									+ " projeto.local_id"
									+ " FROM tb_projeto projeto"
									+ " INNER JOIN tb_edital edital ON projeto.edital_id = edital.id_edital"
									+ " WHERE edital.id_edital = ",
							edital.getIdEdital());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			projetos = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return projetos;
	}

	public List<Projeto> getByProgramaInstitucional(
			ProgramaInstitucional programaInstitucional)
			throws SQLExceptionQManager {

		List<Projeto> projetos = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT projeto.id_projeto,"
									+ " projeto.nm_projeto,"
									+ " projeto.nm_resumo, "
									+ " projeto.dt_inicio_projeto,"
									+ " projeto.dt_fim_projeto,"
									+ " projeto.nr_processo,"
									+ " projeto.vl_orcamento,"
									+ " projeto.dt_registro, projeto.edital_id,"
									+ " projeto.local_id"
									+ " FROM tb_projeto projeto"
									+ " INNER JOIN tb_edital edital"
									+ " ON projeto.edital_id = edital.id_edital"
									+ " INNER JOIN tb_programa_institucional programa_institucional"
									+ " ON edital.programa_institucional_id = programa_institucional.id_programa_institucional"
									+ " WHERE programa_institucional.id_programa_institucional = ",
							programaInstitucional.getIdProgramaInstitucional());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			projetos = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return projetos;
	}

	public List<Projeto> getByPessoa(Pessoa pessoa) throws SQLExceptionQManager {
		List<Projeto> projetos;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d", "SELECT projeto.id_projeto,"
					+ " projeto.nm_projeto," 
					+ " projeto.nm_resumo, "
					+ " projeto.dt_inicio_projeto,"
					+ " projeto.dt_fim_projeto,"
					+ " projeto.nr_processo,"
					+ " projeto.vl_orcamento,"
					+ " projeto.dt_registro," 
					+ " projeto.edital_id,"
					+ " projeto.local_id" 
					+ " FROM tb_projeto projeto"
					+ " INNER JOIN tb_participacao participacao "
					+ " ON projeto.id_projeto = participacao.projeto_id"
					+ " INNER JOIN tb_pessoa pessoa "
					+ " ON participacao.pessoa_id = pessoa.id_pessoa"
					+ " WHERE pessoa.id_pessoa =", 
					pessoa.getPessoaId());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			projetos = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return projetos;
	}

	@Override
	public List<Projeto> find(Projeto projeto) throws SQLExceptionQManager {

		List<Projeto> projetos = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s '%%%s%%'",
					"SELECT projeto.id_projeto," 
							+ " projeto.nm_projeto,"
							+ " projeto.nm_resumo, "
							+ " projeto.dt_inicio_projeto,"
							+ " projeto.dt_fim_projeto,"
							+ " projeto.nr_processo,"
							+ " projeto.vl_orcamento,"
							+ " projeto.dt_registro," 
							+ " projeto.edital_id,"
							+ " projeto.local_id" 
							+ " FROM tb_projeto projeto"
							+ " WHERE projeto.nm_projeto LIKE ",
					projeto.getNomeProjeto());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			projetos = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return projetos;
	}
	
	public int getQuantidadeProjetosDePesquisa() throws SQLExceptionQManager {

		int quantidade = 0;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT COUNT(*) AS quantidade_projetos"
					+ " FROM tb_projeto projeto"
					+ " INNER JOIN tb_edital edital "
					+ "   ON projeto.edital_id = edital.id_edital "
					+ " INNER JOIN tb_programa_institucional pi "
					+ "   ON edital.programa_institucional_id = pi.id_programa_institucional "
					+ " WHERE pi.tipo_programa_institucional_id = "
					+ TipoProgramaInstitucional.PESQUISA;

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				quantidade = rs.getInt("quantidade_projetos");
			}

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return quantidade;
	}

	public int getQuantidadeProjetosDeExtensao() throws SQLExceptionQManager {

		int quantidade = 0;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT COUNT(*) AS quantidade_projetos "
					+ " FROM tb_projeto projeto"
					+ " INNER JOIN tb_edital edital "
					+ "   ON projeto.edital_id = edital.id_edital "
					+ " INNER JOIN tb_programa_institucional pi "
					+ "   ON edital.programa_institucional_id = pi.id_programa_institucional "
					+ " WHERE pi.tipo_programa_institucional_id = "
					+ TipoProgramaInstitucional.EXTENSAO;

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				quantidade = rs.getInt("quantidade_projetos");
			}

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return quantidade;
	}
	
	public int getQuantidadeProjetosDePesquisaPorCampus(int idCampus)
			throws SQLExceptionQManager {

		int quantidade = 0;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT COUNT(*) AS quantidade_projetos "
					+ " FROM tb_projeto projeto"
					+ " INNER JOIN tb_edital edital "
					+ "   ON projeto.edital_id = edital.id_edital "
					+ " INNER JOIN tb_programa_institucional pi "
					+ "   ON edital.programa_institucional_id = pi.id_programa_institucional "
					+ " WHERE pi.tipo_programa_institucional_id = "
					+ TipoProgramaInstitucional.PESQUISA
					+ "   AND projeto.local_id = " + idCampus;

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				quantidade = rs.getInt("quantidade_projetos");
			}

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return quantidade;
	}

	public int getQuantidadeProjetosDeExtensaoPorCampus(int idCampus)
			throws SQLExceptionQManager {

		int quantidade = 0;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT COUNT(*) AS quantidade_projetos "
					+ " FROM tb_projeto projeto"
					+ " INNER JOIN tb_edital edital "
					+ "   ON projeto.edital_id = edital.id_edital "
					+ " INNER JOIN tb_programa_institucional pi "
					+ "   ON edital.programa_institucional_id = pi.id_programa_institucional "
					+ " WHERE pi.tipo_programa_institucional_id = "
					+ TipoProgramaInstitucional.EXTENSAO
					+ "   AND projeto.local_id = " + idCampus;

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				quantidade = rs.getInt("quantidade_projetos");
			}

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return quantidade;
	}
	
	@Override
	public List<Projeto> convertToList(ResultSet rs)
			throws SQLExceptionQManager {
		List<Projeto> projetos = new LinkedList<Projeto>();

		try {

			while (rs.next()) {
				Projeto projeto = new Projeto();
				projeto.setIdProjeto(rs.getInt("projeto.id_projeto"));
				projeto.setNomeProjeto(rs.getString("projeto.nm_projeto"));
				projeto.setResumoProjeto(rs.getString("projeto.nm_resumo"));
				projeto.setInicioProjeto(rs.getDate("projeto.dt_inicio_projeto"));
				projeto.setFimProjeto(rs.getDate("projeto.dt_fim_projeto"));
				projeto.setProcesso(rs.getString("projeto.nr_processo"));
				projeto.setOrcamento(rs.getDouble("projeto.vl_orcamento"));

				// Edital
				Edital edital = EditalDAO.getInstance().getById(
						rs.getInt("projeto.edital_id"));
				projeto.setEdital(edital);

				// Campus
				Campus campus = CampusDAO.getInstance().getById(
						rs.getInt("projeto.local_id"));
				projeto.setCampus(campus);

				projeto.setRegistro(rs.getDate("projeto.dt_registro"));

				projetos.add(projeto);
			}

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return projetos;
	}

}