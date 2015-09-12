package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.TipoProgramaInstitucional;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class ParticipacaoDAO implements GenericDAO<Integer, Participacao> {

	static DBPool banco;
	
	private static ParticipacaoDAO instance;
	
	public Connection connection;

	public static ParticipacaoDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new ParticipacaoDAO(banco);
		return instance;
	}

	public ParticipacaoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Participacao participacao) throws SQLExceptionQManager {

		int idParticipacao = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {
			
			String sql = "INSERT INTO tb_participacao ("
							+ "pessoa_id," 
							+ " projeto_id,"
							+ " dt_inicio,"
							+ " dt_fim,"
							+ " fl_bolsista,"
							+ " tipo_participacao_id)" 
							+ " VALUES"
							+ " (?, ?, ?, ?, ?, ?)";
			
			stmt = (PreparedStatement) connection.prepareStatement(sql, 
					Statement.RETURN_GENERATED_KEYS);
			
			stmt.setInt(1, participacao.getPessoa().getPessoaId());
			stmt.setInt(2, participacao.getProjeto().getIdProjeto());
			stmt.setDate(3, participacao.getInicioParticipacao() != null ? 
					new Date(participacao.getInicioParticipacao().getTime()):
						null);
			stmt.setDate(4, participacao.getFimParticipacao() != null ? 
					new Date(participacao.getFimParticipacao().getTime()): 
						null);
			stmt.setBoolean(5, participacao.isBolsista());
			stmt.setInt(6, participacao.getTipoParticipacao().getIdTipoParticipacao());

			stmt.executeUpdate();

			idParticipacao = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}

		return idParticipacao;
	}

	@Override
	public void update(Participacao participacao) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_participacao"
					+ " SET pessoa_id = ?,"
					+ " projeto_id = ?,"
					+ " dt_inicio = ?,"
					+ " dt_fim = ?,"
					+ " fl_bolsista = ?,"
					+ " tipo_participacao_id = ?"
					+ " WHERE id_participacao = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, participacao.getPessoa().getPessoaId());
			stmt.setInt(2, participacao.getProjeto().getIdProjeto());
			stmt.setDate(3, new Date(participacao.getInicioParticipacao().getTime()));
			stmt.setDate(4, new Date(participacao.getFimParticipacao().getTime()));
			stmt.setInt(5, participacao.isBolsista() ? 1 : 0);
			stmt.setInt(6, participacao.getTipoParticipacao().getIdTipoParticipacao());
			stmt.setInt(7, participacao.getIdParticipacao());

			stmt.execute();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, this.connection);
		}
	}

	@Override
	public void delete(Integer id) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "DELETE FROM tb_participacao WHERE id_participacao=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}
	}

	@Override
	public List<Participacao> getAll() throws SQLExceptionQManager {

		List<Participacao> participacoes;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s",
					"SELECT participacao.id_participacao, "
							+ " participacao.pessoa_id, "
							+ " participacao.projeto_id, "
							+ " participacao.dt_inicio, "
							+ " participacao.dt_fim, "
							+ " participacao.fl_bolsista,"
							+ " participacao.tipo_participacao_id, "
							+ " participacao.dt_registro "
							+ "FROM tb_participacao participacao");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			participacoes = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return participacoes;
	}

	@Override
	public Participacao getById(Integer id) throws SQLExceptionQManager {

		Participacao participacao = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d",
					"SELECT participacao.id_participacao, "
							+ " participacao.pessoa_id, "
							+ " participacao.projeto_id, "
							+ " participacao.dt_inicio, "
							+ " participacao.dt_fim, "
							+ " participacao.fl_bolsista,"
							+ " participacao.tipo_participacao_id, "
							+ " participacao.dt_registro "
							+ " FROM tb_participacao participacao "
							+ " WHERE participacao.id_participacao =", id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Participacao> participacoes = convertToList(rs);

			if (!participacoes.isEmpty())
				participacao = participacoes.get(0);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return participacao;

	}

	public List<Participacao> getByProjeto(Projeto projeto)
			throws SQLExceptionQManager {

		List<Participacao> participacoes;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d",
					"SELECT participacao.id_participacao, "
						+ " participacao.pessoa_id, "
						+ " participacao.projeto_id, "
						+ " participacao.dt_inicio, "
						+ " participacao.dt_fim, "
						+ " participacao.fl_bolsista,"
						+ " participacao.tipo_participacao_id, "
						+ " participacao.dt_registro "
						+ " FROM tb_participacao participacao "
						+ " WHERE participacao.projeto_id =",
					projeto.getIdProjeto());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			participacoes = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return participacoes;
	}

	@Override
	public List<Participacao> find(Participacao entity)
			throws SQLExceptionQManager {
		return null;
	}

	public int getQuantidadeDocentesPesquisa() throws SQLException {
		
		int quantidade = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d %s %d %s",
						"SELECT COUNT(*) AS quantidade"
							+ " FROM tb_servidor as servidor"
							+ " INNER JOIN tb_pessoa pessoa"
							+ "   ON pessoa.id_pessoa = servidor.pessoa_id"
							+ " INNER JOIN tb_titulacao titulacao"
							+ "   ON servidor.id_titulacao = titulacao.id_titulacao"
							+ " INNER JOIN tb_participacao participacao"
							+ "   ON participacao.pessoa_id = pessoa.id_pessoa"
							+ " INNER JOIN tb_projeto projeto"
							+ "   ON projeto.id_projeto = participacao.projeto_id"
							+ " INNER JOIN tb_edital edital"
							+ "   ON edital.id_edital = projeto.edital_id"
							+ " INNER JOIN tb_programa_institucional programa_institucional"
							+ "   ON programa_institucional.id_programa_institucional = "
							+ "      edital.programa_institucional_id"
							+ " INNER JOIN tb_tipo_programa_institucional tipo_programa_institucional"
							+ "   ON tipo_programa_institucional.id_tipo_programa_institucional = "
							+ "      programa_institucional.tipo_programa_institucional_id"
							+ " WHERE tipo_programa_institucional.id_tipo_programa_institucional =",
							TipoProgramaInstitucional.PESQUISA,
							  "   AND servidor.cargo_servidor_id = ", CargoServidor.PROFESSOR,
							" GROUP BY pessoa.id_pessoa");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				quantidade = rs.getInt("quantidade");
			}
		} catch (SQLException sqle) {

			throw sqle;
		} finally {

			banco.close(stmt, rs, this.connection);
		}
		
		return quantidade;
	}
	
	@Override
	public List<Participacao> convertToList(ResultSet rs)
			throws SQLExceptionQManager {

		List<Participacao> participacoes = new LinkedList<Participacao>();

		try {

			while (rs.next()) {
				Participacao participacao = new Participacao();

				participacao.getTipoParticipacao().setIdTipoParticipacao(rs.getInt("participacao.tipo_participacao_id"));
				participacao.getPessoa().setPessoaId(rs.getInt("participacao.pessoa_id"));
				participacao.getProjeto().setIdProjeto(rs.getInt("participacao.projeto_id"));

				participacao.setIdParticipacao(rs.getInt("participacao.id_participacao"));
				participacao.setInicioParticipacao(rs.getDate("participacao.dt_inicio"));
				participacao.setFimParticipacao(rs.getDate("participacao.dt_fim"));
				participacao.setRegistro(rs.getDate("participacao.dt_registro"));
				participacao.setBolsista(rs.getInt("participacao.fl_bolsista") == 1 ? true : false);

				participacoes.add(participacao);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return participacoes;
	}

}
