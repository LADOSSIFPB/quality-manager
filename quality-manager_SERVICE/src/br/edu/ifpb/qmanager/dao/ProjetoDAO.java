package br.edu.ifpb.qmanager.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class ProjetoDAO implements GenericDAO<Integer, Projeto> {

	// a conexão com o banco de dados
	public Connection connection;
	private DatabaseConnection banco;

	public ProjetoDAO(DatabaseConnection banco) {
		this.connection = (Connection) banco.getConnection();
		this.banco = banco;
	}

	@Override
	public int insert(Projeto projeto) throws QManagerSQLException {

		int chave = 0;

		try {

			String sql = String
					.format("%s %s ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %s, %d)",
							"INSERT INTO `tb_projeto` (`nm_projeto`, `dt_inicio_projeto`, "
									+ "`dt_fim_projeto`, `ar_projeto_submetido`, `ar_relatorio_parcial`, "
									+ "`ar_relatorio_final`, `nr_processo`, `tp_projeto`, `vl_orcamento`, "
									+ "`edital_id`)", " VALUES", projeto
									.getNomeProjeto(), new Date(projeto
									.getInicioProjeto().getTime()), new Date(
									projeto.getFimProjeto().getTime()), projeto
									.getProjetoSubmetido(), projeto
									.getRelatorioParcial(), projeto
									.getRelatorioFinal(),
							projeto.getProcesso(), projeto.getTipoProjeto(),
							projeto.getOrcamento(), projeto.getEdital()
									.getIdEdital());

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
	public void update(Projeto projeto) throws QManagerSQLException {

		try {

			String sql = "UPDATE `tb_projeto` SET `nm_projeto`=?, `dt_inicio_projeto`=?, "
					+ "`dt_fim_projeto`=?, `ar_projeto_submetido`=?, `ar_relatorio_parcial`=?, "
					+ "`ar_relatorio_final`=?, `nr_processo`=?, `tp_projeto`=?, `vl_orcamento`=?, "
					+ "`edital_id`=? WHERE `id_projeto`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1, projeto.getNomeProjeto());
			stmt.setDate(2, new Date(projeto.getInicioProjeto().getTime()));
			stmt.setDate(3, new Date(projeto.getFimProjeto().getTime()));
			stmt.setString(4, projeto.getProjetoSubmetido());
			stmt.setString(5, projeto.getRelatorioParcial());
			stmt.setString(6, projeto.getRelatorioFinal());
			stmt.setString(7, projeto.getProcesso());
			stmt.setString(8, String.valueOf(projeto.getTipoProjeto()));
			stmt.setDouble(9, projeto.getOrcamento());
			stmt.setInt(10, projeto.getEdital().getIdEdital());
			stmt.setInt(11, projeto.getIdProjeto());

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

			String sql = "DELETE FROM `tb_projeto` WHERE `id_projeto`=?";

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

			String sql = String.format("%s", "SELECT * FROM `tb_projeto`");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			projetos = convertToList(rs);

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

			String sql = String.format("%s %d",
					"SELECT * FROM `tb_projeto` WHERE `id_projeto` =", id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Projeto> projetos = convertToList(rs);

			if (!projetos.isEmpty())
				projeto = projetos.get(0);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return projeto;

	}

	public List<Projeto> getByProgramaInstitucional(
			ProgramaInstitucional programaInstitucional)
			throws QManagerSQLException {
		List<Projeto> projetos;

		try {

			String sql = String
					.format("%s %d",
							"SELECT * FROM `tb_projeto`, `tb_edital`, `tb_programa_institucional` "
									+ "WHERE edital_id = id_edital "
									+ "AND programa_institucional_id = id_programa_institucional "
									+ "AND id_programa_institucional =",
							programaInstitucional.getIdProgramaInstitucional());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			projetos = convertToList(rs);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return projetos;
	}

	public List<Projeto> getByEdital(Edital edital) throws QManagerSQLException {
		List<Projeto> projetos;

		try {

			String sql = String.format("%s %d",
					"SELECT * FROM `tb_projeto`, `tb_edital` "
							+ "WHERE edital_id = id_edital "
							+ "AND id_edital =", edital.getIdEdital());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			projetos = convertToList(rs);

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

		EditalDAO editalDAO = new EditalDAO(banco);

		try {

			while (rs.next()) {
				Projeto projeto = new Projeto();
				Edital edital = new Edital();
				projeto.setIdProjeto(rs.getInt("id_projeto"));
				projeto.setNomeProjeto(rs.getString("nm_projeto"));
				projeto.setInicioProjeto(rs.getDate("dt_inicio_projeto"));
				projeto.setFimProjeto(rs.getDate("dt_fim_projeto"));
				projeto.setProjetoSubmetido(rs
						.getString("ar_projeto_submetido"));
				projeto.setRelatorioParcial(rs
						.getString("ar_relatorio_parcial"));
				projeto.setRelatorioFinal(rs.getString("ar_relatorio_final"));
				projeto.setProcesso(rs.getString("nr_processo"));
				projeto.setTipoProjeto(rs.getString("tp_projeto").charAt(0));
				projeto.setOrcamento(rs.getDouble("vl_orcamento"));
				projeto.setRegistro(rs.getDate("dt_registro"));
				edital = editalDAO.getById(rs.getInt("edital_id"));
				projeto.setEdital(edital);

				projetos.add(projeto);

			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return projetos;
	}

}