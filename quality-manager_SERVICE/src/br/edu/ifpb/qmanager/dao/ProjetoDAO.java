package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Area;
import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.GrandeArea;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoParticipacao;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.util.StringUtil;

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
			
			String sql = "INSERT INTO tb_projeto (" 
						+ " nm_projeto,"
						+ " nm_resumo,"
						+ " nm_palavras_chave,"
						+ " dt_inicio_projeto,"
						+ " dt_fim_projeto," 
						+ " nr_processo,"
						+ " vl_orcamento,"
						+ " edital_id," 
						+ " campus_institucional_id,"
						+ " grande_area_id,"
						+ " area_id,"
						+ " cadastrador_id)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
							
			stmt = (PreparedStatement) connection.prepareStatement(sql, 
					Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, projeto.getNomeProjeto());
			stmt.setString(2, projeto.getResumoProjeto());
			
			// Palavras-chave
			String palavrasChaves = getPalavrasChaves(
					projeto.getPalavrasChave());
			stmt.setString(3, palavrasChaves);
			
			stmt.setDate(4, projeto.getInicioProjeto() != null ? 
					new Date(projeto.getInicioProjeto().getTime()): null);
			stmt.setDate(5, projeto.getFimProjeto() != null ? 
					new Date(projeto.getFimProjeto().getTime()): null);
			stmt.setString(6, projeto.getProcesso());
			stmt.setDouble(7, projeto.getOrcamento());
			stmt.setInt(8, projeto.getEdital().getIdEdital());
			stmt.setInt(9, projeto.getCampus().getIdCampusInstitucional());
			stmt.setInt(10, projeto.getGrandeArea().getIdGrandeArea());
			stmt.setInt(11, projeto.getArea().getIdArea());
			stmt.setInt(12, projeto.getCadastrador().getPessoaId());
			
			stmt.executeUpdate();			

			idProjeto = BancoUtil.getGenerateKey(stmt);

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
					+ " nm_palavras_chave=?,"
					+ " dt_inicio_projeto = ?, "
					+ " dt_fim_projeto = ?,"
					+ " nr_processo = ?," 
					+ " vl_orcamento = ?,"
					+ " edital_id = ?,"
					+ " campus_institucional_id = ?,"
					+ " grande_area_id = ?,"
					+ " area_id = ?, "
					+ " WHERE id_projeto = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, projeto.getNomeProjeto());
			stmt.setString(2, projeto.getResumoProjeto());
			stmt.setString(3, getPalavrasChaves(projeto.getPalavrasChave()));
			stmt.setDate(4, new Date(projeto.getInicioProjeto().getTime()));
			stmt.setDate(5, new Date(projeto.getFimProjeto().getTime()));
			stmt.setString(6, projeto.getProcesso());
			stmt.setDouble(7, projeto.getOrcamento());
			stmt.setInt(8, projeto.getEdital().getIdEdital());
			stmt.setInt(9, projeto.getCampus().getIdCampusInstitucional());
			stmt.setInt(10, projeto.getGrandeArea().getIdGrandeArea());
			stmt.setInt(11, projeto.getArea().getIdArea());
			stmt.setInt(12, projeto.getIdProjeto());

			stmt.execute();

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}
	}

	private String getPalavrasChaves(String[] palavrasChave) {
		
		String palavrasChavesComVirgula = StringUtil.STRING_VAZIO;
		
		for (String palavraChave: palavrasChave) {
			
			palavrasChavesComVirgula += palavraChave + ",";			
		}
		
		return StringUtil.replaceLastToEmptySpace(palavrasChavesComVirgula, 
				",");
	}

	@Override
	public int delete(Integer idProjeto) throws SQLExceptionQManager {

		PreparedStatement stmt = null;
		
		int rowsUpdated = BancoUtil.NOROWSUPDATED;

		try {

			String sql = "UPDATE tb_projeto" 
					+ " SET fl_removido = ?,"
					+ " dt_removido = ?"
					+ " WHERE id_projeto = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setBoolean(1, true);
			stmt.setTimestamp(2, BancoUtil.getCurrenteTimeStamp());
			stmt.setInt(3, idProjeto);

			rowsUpdated = stmt.executeUpdate();
			
			//TODO: Remover as ligações do projeto: participações, arquivos do projeto...
			if (rowsUpdated != BancoUtil.NOROWSUPDATED) {
				
				ParticipacaoDAO.getInstance().deleteByProjetoId(idProjeto);
			}		

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}
		
		return rowsUpdated;
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
									+ " projeto.campus_institucional_id,"
									+ " projeto.grande_area_id,"
									+ " projeto.area_id,"
									+ " projeto.cadastrador_id"
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
					+ " projeto.campus_institucional_id,"
					+ " projeto.grande_area_id,"
					+ " projeto.area_id,"
					+ " projeto.cadastrador_id,"
					+ " participacao.pessoa_id as orientador"
					+ " FROM tb_projeto projeto"
					+ " INNER JOIN tb_participacao participacao "
					+ "   ON projeto.id_projeto = participacao.projeto_id"
					+ " WHERE participacao.tipo_participacao_id = " 
						+ TipoParticipacao.TIPO_ORIENTADOR
					+ " AND projeto.id_projeto = ",
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
									+ " projeto.campus_institucional_id,"
									+ " projeto.grande_area_id,"
									+ " projeto.area_id,"
									+ " projeto.cadastrador_id,"
									+ " participacao.pessoa_id as orientador"
									+ " FROM tb_projeto projeto"
									+ " INNER JOIN tb_edital edital "
									+ "   ON projeto.edital_id = edital.id_edital"
									+ " INNER JOIN tb_participacao participacao "
									+ "   ON projeto.id_projeto = participacao.projeto_id"
									+ " WHERE participacao.tipo_participacao_id = " 
										+ TipoParticipacao.TIPO_ORIENTADOR
									+ " AND edital.id_edital = ",
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
									+ " projeto.dt_registro,"
									+ " projeto.edital_id,"
									+ " projeto.campus_institucional_id,"
									+ " projeto.grande_area_id,"
									+ " projeto.area_id,"
									+ " projeto.cadastrador_id,"
									+ " participacao.pessoa_id as orientador"
									+ " FROM tb_projeto projeto"
									+ " INNER JOIN tb_edital edital"
									+ "   ON projeto.edital_id = edital.id_edital"
									+ " INNER JOIN tb_programa_institucional programa_institucional"
									+ "   ON edital.programa_institucional_id = programa_institucional.id_programa_institucional"
									+ " INNER JOIN tb_participacao participacao "
									+ "   ON projeto.id_projeto = participacao.projeto_id"
									+ " WHERE participacao.tipo_participacao_id = " 
										+ TipoParticipacao.TIPO_ORIENTADOR
									+ " AND programa_institucional.id_programa_institucional = ",
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
					+ " projeto.campus_institucional_id,"
					+ " projeto.grande_area_id,"
					+ " projeto.area_id,"
					+ " projeto.cadastrador_id,"
					+ " participacao.pessoa_id as orientador"
					+ " FROM tb_projeto projeto"
					+ " INNER JOIN tb_participacao participacao "
					+ "   ON projeto.id_projeto = participacao.projeto_id"
					+ " INNER JOIN tb_pessoa pessoa "
					+ "   ON participacao.pessoa_id = pessoa.id_pessoa"
					+ " WHERE participacao.tipo_participacao_id = " 
						+ TipoParticipacao.TIPO_ORIENTADOR
					+ " AND pessoa.id_pessoa =", 
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
	
	public List<Projeto> getByPessoas(List<Pessoa> pessoas) throws SQLExceptionQManager {
		
		List<Projeto> projetos = new ArrayList<Projeto>();
		
		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {
			
			int quantidadePessoas = pessoas.size();
			
			String inValues = buildInValues(pessoas);
			
			String sql = String.format("%s %s %s",
					"SELECT projeto.id_projeto,"
					+ " projeto.nm_projeto," 
					+ " projeto.nm_resumo, "
					+ " projeto.dt_inicio_projeto,"
					+ " projeto.dt_fim_projeto,"
					+ " projeto.nr_processo,"
					+ " projeto.vl_orcamento,"
					+ " projeto.dt_registro," 
					+ " projeto.edital_id,"
					+ " projeto.campus_institucional_id,"
					+ " projeto.grande_area_id,"
					+ " projeto.area_id,"
					+ " projeto.cadastrador_id,"
					+ " participacao.pessoa_id as orientador"
					+ " FROM tb_projeto as projeto"
					+ " INNER JOIN tb_participacao participacao "
					+ "   ON projeto.id_projeto = participacao.projeto_id"
					+ " WHERE participacao.tipo_participacao_id = " 
						+ TipoParticipacao.TIPO_ORIENTADOR
					+ " AND projeto.id_projeto IN ("
					+ " 	SELECT participacao.projeto_id"
					+ "		FROM tb_participacao AS participacao"
					+ "		WHERE participacao.pessoa_id IN",
					" (" + inValues + ")",
					"		GROUP BY participacao.projeto_id"
					+ "		HAVING COUNT(participacao.pessoa_id) >= " + quantidadePessoas
					+ "	)");
			
			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			projetos = convertToList(rs);
			
		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}
		
		return projetos;
	}

	private String buildInValues(List<Pessoa> pessoas) {
		
		StringBuilder inValues = new StringBuilder();
		
		for (Pessoa pessoa: pessoas) {			
			inValues.append(pessoa.getPessoaId());
			inValues.append(",");			
		}
		
		inValues.replace(inValues.lastIndexOf(","), inValues.lastIndexOf(",") + 1, 
				StringUtil.STRING_VAZIO);
		
		return inValues.toString();
	}

	public List<Projeto> getByNomeProjeto(String nomeProjeto) throws SQLExceptionQManager {

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
							+ " projeto.campus_institucional_id,"
							+ " projeto.grande_area_id,"
							+ " projeto.area_id,"
							+ " projeto.cadastrador_id,"
							+ " participacao.pessoa_id as orientador"
							+ " FROM tb_projeto projeto"
							+ " INNER JOIN tb_participacao participacao "
							+ "   ON projeto.id_projeto = participacao.projeto_id"
							+ " WHERE participacao.tipo_participacao_id = " 
								+ TipoParticipacao.TIPO_ORIENTADOR
							+ " AND projeto.nm_projeto LIKE ",
					nomeProjeto);

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
	
	public int getQuantidadeProjetos(int idTipoProgramaInstitucional) throws SQLExceptionQManager {

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
					+ " WHERE pi.tipo_programa_institucional_id = " + idTipoProgramaInstitucional;

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
	
	public int getQuantidadeProjetos(int idTipoProgramaInstitucional,
			int idCampusInstitucional) throws SQLExceptionQManager {

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
						+ idTipoProgramaInstitucional
					+ " AND projeto.campus_institucional_id = " 
						+ idCampusInstitucional;

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
				projeto.setRegistro(rs.getDate("projeto.dt_registro"));

				// Orientador
				int idOrientador = rs.getInt("orientador");				
				projeto.setOrientador(ServidorDAO.getInstance().getById(
						idOrientador));
				
				// Edital
				Edital edital = EditalDAO.getInstance().getById(
						rs.getInt("projeto.edital_id"));
				projeto.setEdital(edital);

				// Campus
				Campus campus = CampusDAO.getInstance().getById(
						rs.getInt("projeto.campus_institucional_id"));
				projeto.setCampus(campus);
				
				// Grande Area
				GrandeArea grandeArea = GrandeAreaDAO.getInstance().getById(
						rs.getInt("projeto.grande_area_id"));
				projeto.setGrandeArea(grandeArea);

				// Area
				Area area = AreaDAO.getInstance().getById(
						rs.getInt("projeto.area_id"));
				projeto.setArea(area);

				// Cadastrador
				Servidor cadastrador = ServidorDAO.getInstance().getById(
						rs.getInt("projeto.cadastrador_id"));
				projeto.setCadastrador(cadastrador);

				projetos.add(projeto);
			}

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return projetos;
	}

	@Override
	public List<Projeto> find(Projeto projeto) throws SQLExceptionQManager {
		return null;
	}
}