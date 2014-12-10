package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.MembroProjeto;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoParticipacao;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

public class ProjetoDAO implements GenericDAO<Integer, Projeto> {

	static DBPool banco;
	private static ProjetoDAO instance;

	public static ProjetoDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new ProjetoDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public ProjetoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Projeto projeto) throws QManagerSQLException {

		int chave = 0;

		try {

			String sql = String
					.format("%s %s ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %s, %d)",
							"INSERT INTO tb_projeto (nm_projeto, dt_inicio_projeto, "
									+ "dt_fim_projeto, ar_projeto_submetido, ar_relatorio_parcial, "
									+ "ar_relatorio_final, nr_processo, tp_projeto, vl_orcamento, "
									+ "edital_id)", " VALUES", projeto
									.getNomeProjeto(), new Date(projeto
									.getInicioProjeto().getTime()), new Date(
									projeto.getFimProjeto().getTime()),
							"tem_que_ter_um_arquivo_aqui", projeto
									.getRelatorioParcial(), projeto
									.getRelatorioFinal(),
							projeto.getProcesso(), projeto.getTipoProjeto(),
							projeto.getOrcamento(), projeto.getEdital()
									.getIdEdital());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			chave = BancoUtil.getGenerateKey(stmt);

			projeto.setIdProjeto(chave);

			// TODO: Melhorar a composição da entre Projeto, Participação e
			// Membro de Projeto

			Participacao participacaoOrientador = new Participacao();

			Servidor servidor = projeto.getOrientador();

			// povoando a participação
			participacaoOrientador.setMembroProjeto(servidor);
			participacaoOrientador.setProjeto(projeto);
			participacaoOrientador.setInicioParticipacao(projeto
					.getInicioProjeto());
			participacaoOrientador.setValorBolsa(0.0);
			participacaoOrientador.setTipoParticipacao(new TipoParticipacao(
					TipoParticipacao.TIPO_ORIENTADOR));

			ParticipacaoDAO.getInstance().insert(participacaoOrientador);

			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return chave;

	}

	@Override
	public void update(Projeto projeto) throws QManagerSQLException {

		try {

			String sql = "UPDATE tb_projeto SET nm_projeto=?, dt_inicio_projeto=?, "
					+ "dt_fim_projeto=?, ar_projeto_submetido=?, ar_relatorio_parcial=?, "
					+ "ar_relatorio_final=?, nr_processo=?, vl_orcamento=?, "
					+ "edital_id=? WHERE id_projeto=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1, projeto.getNomeProjeto());
			stmt.setDate(2, new Date(projeto.getInicioProjeto().getTime()));
			stmt.setDate(3, new Date(projeto.getFimProjeto().getTime()));
			stmt.setString(4, projeto.getProjetoSubmetido());
			stmt.setString(5, projeto.getRelatorioParcial());
			stmt.setString(6, projeto.getRelatorioFinal());
			stmt.setString(7, projeto.getProcesso());
			// stmt.setString(8, String.valueOf(projeto.getTipoProjeto()));
			stmt.setDouble(8, projeto.getOrcamento());
			stmt.setInt(9, projeto.getEdital().getIdEdital());
			stmt.setInt(10, projeto.getIdProjeto());

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

			// TODO: Deletar as participações para esse projeto ;)

			String sql = "DELETE FROM tb_projeto WHERE id_projeto=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public List<Projeto> getAll() throws QManagerSQLException {
		List<Projeto> projetos;

		try {

			String sql = String
					.format("%s",
							"SELECT projeto.id_projeto, projeto.nm_projeto, "
									+ "projeto.dt_inicio_projeto, projeto.dt_fim_projeto, "
									+ "projeto.ar_projeto_submetido, projeto.ar_relatorio_parcial, "
									+ "projeto.ar_relatorio_final, projeto.nr_processo, projeto.tp_projeto, "
									+ "projeto.vl_orcamento, projeto.dt_registro, projeto.edital_id "
									+ "FROM tb_projeto projeto");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			projetos = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return projetos;
	}

	@Override
	public Projeto getById(Integer id) throws QManagerSQLException {

		Projeto projeto = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT projeto.id_projeto, projeto.nm_projeto, "
									+ "projeto.dt_inicio_projeto, projeto.dt_fim_projeto, "
									+ "projeto.ar_projeto_submetido, projeto.ar_relatorio_parcial, "
									+ "projeto.ar_relatorio_final, projeto.nr_processo, projeto.tp_projeto, "
									+ "projeto.vl_orcamento, projeto.dt_registro, projeto.edital_id "
									+ "FROM tb_projeto projeto WHERE projeto.id_projeto =",
							id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Projeto> projetos = convertToList(rs);

			if (!projetos.isEmpty())
				projeto = projetos.get(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return projeto;

	}

	public List<Projeto> getByEdital(Edital edital) throws QManagerSQLException {
		List<Projeto> projetos;

		try {

			String sql = String
					.format("%s %d",
							"SELECT projeto.id_projeto, projeto.nm_projeto, "
									+ "projeto.dt_inicio_projeto, projeto.dt_fim_projeto, "
									+ "projeto.ar_projeto_submetido, projeto.ar_relatorio_parcial, "
									+ "projeto.ar_relatorio_final, projeto.nr_processo, projeto.tp_projeto, "
									+ "projeto.vl_orcamento, projeto.dt_registro, projeto.edital_id "
									+ "FROM tb_projeto projeto "
									+ "INNER JOIN tb_edital edital ON projeto.edital_id = edital.id_edital "
									+ "WHERE edital.id_edital =",
							edital.getIdEdital());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			projetos = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return projetos;
	}

	public List<Projeto> getByProgramaInstitucional(
			ProgramaInstitucional programaInstitucional)
			throws QManagerSQLException {
		List<Projeto> projetos;

		try {

			String sql = String
					.format("%s %d",
							"SELECT projeto.id_projeto, projeto.nm_projeto, "
									+ "projeto.dt_inicio_projeto, projeto.dt_fim_projeto, "
									+ "projeto.ar_projeto_submetido, projeto.ar_relatorio_parcial, "
									+ "projeto.ar_relatorio_final, projeto.nr_processo, projeto.tp_projeto, "
									+ "projeto.vl_orcamento, projeto.dt_registro, projeto.edital_id "
									+ "FROM tb_projeto projeto "
									+ "INNER JOIN tb_edital edital ON projeto.edital_id = edital.id_edital "
									+ "INNER JOIN tb_programa_institucional programa_institucional "
									+ "ON edital.programa_institucional_id = "
									+ "programa_institucional.id_programa_institucional "
									+ "WHERE programa_institucional.id_programa_institucional =",
							programaInstitucional.getIdProgramaInstitucional());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			projetos = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return projetos;
	}

	public List<Projeto> getByMembroProjeto(MembroProjeto membroProjeto)
			throws QManagerSQLException {
		List<Projeto> projetos;

		try {

			String sql = String
					.format("%s %d",
							"SELECT projeto.id_projeto, projeto.nm_projeto, "
									+ "projeto.dt_inicio_projeto, projeto.dt_fim_projeto, "
									+ "projeto.ar_projeto_submetido, projeto.ar_relatorio_parcial, "
									+ "projeto.ar_relatorio_final, projeto.nr_processo, projeto.tp_projeto, "
									+ "projeto.vl_orcamento, projeto.dt_registro, projeto.edital_id "
									+ "FROM tb_projeto projeto "
									+ "INNER JOIN tb_participacao participacao "
									+ "ON projeto.id_projeto = participacao.projeto_id "
									+ "INNER JOIN tb_pessoa pessoa "
									+ "ON participacao.pessoa_id = pessoa.id_pessoa "
									+ "WHERE pessoa.id_pessoa =",
							membroProjeto.getPessoaId());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			projetos = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return projetos;
	}

	@Override
	public List<Projeto> convertToList(ResultSet rs)
			throws QManagerSQLException {
		List<Projeto> projetos = new LinkedList<Projeto>();

		try {

			while (rs.next()) {
				Projeto projeto = new Projeto();

				// Edital edital = new Edital();
				// edital =
				// EditalDAO.getInstance().getById(rs.getInt("projeto.edital_id"));
				// projeto.setEdital(edital);

				projeto.getEdital().setIdEdital(rs.getInt("projeto.edital_id"));

				projeto.setIdProjeto(rs.getInt("projeto.id_projeto"));
				projeto.setNomeProjeto(rs.getString("projeto.nm_projeto"));
				projeto.setInicioProjeto(rs
						.getDate("projeto.dt_inicio_projeto"));
				projeto.setFimProjeto(rs.getDate("projeto.dt_fim_projeto"));
				projeto.setProjetoSubmetido(rs
						.getString("projeto.ar_projeto_submetido"));
				projeto.setRelatorioParcial(rs
						.getString("projeto.ar_relatorio_parcial"));
				projeto.setRelatorioFinal(rs
						.getString("projeto.ar_relatorio_final"));
				projeto.setProcesso(rs.getString("projeto.nr_processo"));
				projeto.setTipoProjeto(rs.getString("projeto.tp_projeto")
						.charAt(0));
				projeto.setOrcamento(rs.getDouble("projeto.vl_orcamento"));
				projeto.setRegistro(rs.getDate("projeto.dt_registro"));

				projetos.add(projeto);
			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return projetos;
	}

}