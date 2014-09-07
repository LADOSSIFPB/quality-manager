package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Edital;
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
	public Projeto getById(Integer id) throws QManagerSQLException {

		Projeto projeto = null;

		try {

			String sql = String.format("%s %d",
					"SELECT * FROM `tb_projeto` WHERE `id_projeto` =", id);

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Projeto> projetos = convertToList(rs);

			projeto = projetos.get(0);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return projeto;

	}

	@Override
	public int insert(Projeto projeto) throws QManagerSQLException {

		int chave = 0;

		try {

			// Define um insert com os atributos e cada valor é representado
			// por ?
			String sql = String
					.format("%s %s ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %s, %d)",
							"INSERT INTO `tb_projeto` (`nm_projeto`, `dt_inicio_projeto`, `dt_fim_projeto`, `ar_projeto_submetido`, `ar_relatorio_parcial`, `ar_relatorio_final`, `nr_processo`, `tp_projeto`, `vl_orcamento`, `edital_id`)",
							" VALUES", projeto.getNomeProjeto(),
							projeto.getInicioProjeto(),
							projeto.getFimProjeto(),
							projeto.getRelatorioSubmetido(),
							projeto.getRelatorioParcial(),
							projeto.getRelatorioFinal(), projeto.getProcesso(),
							projeto.getTipoProjeto(), projeto.getOrcamento(),
							projeto.getEdital().getIdEdital());

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
	public void update(Projeto projeto) throws QManagerSQLException {

		try {

			String sql = String.format(
					"%s %s %s %s %s %s %s %s %s %s %s %s %s %c %s %d %s %d",
					"UPDATE `tb_projeto` SET `nm_projeto`=", projeto
							.getNomeProjeto(), ", `dt_inicio_projeto`=",
					projeto.getInicioProjeto().toString(),
					", `dt_fim_projeto`=", projeto.getFimProjeto().toString(),
					", `relatorio_parcial`=", projeto.getRelatorioParcial(),
					", `relatorio_final`=", projeto.getRelatorioFinal(),
					", `nr_processo`=", projeto.getProcesso(),
					", `tp_projeto`=", projeto.getTipoProjeto(),
					", `edital_id`=", projeto.getEdital().getIdEdital(),
					"WHERE `id_projeto`=", projeto.getIdProjeto());

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
			String sql = "DELETE FROM `tb_projeto` WHERE `id_projeto`=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, id);

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public List<Projeto> findAll() throws QManagerSQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Projeto> convertToList(ResultSet rs)
			throws QManagerSQLException {
		List<Projeto> projetos = new ArrayList<Projeto>();

		Projeto projeto = new Projeto();
		EditalDAO editalDAO = new EditalDAO(banco);

		try {

			while (rs.next()) {
				projeto.setNomeProjeto(rs.getString("nm_projeto"));
				projeto.setInicioProjetoSQL(rs.getDate("dt_inicio_projeto"));
				projeto.setFimProjetoSQL(rs.getDate("dt_fim_projeto"));
				projeto.setRelatorioParcial(rs.getString("relatorio_parcial"));
				projeto.setRelatorioFinal(rs.getString("relatorio_final"));
				projeto.setProcesso(rs.getString("nr_processo"));
				projeto.setTipoProjeto(rs.getString("tp_projeto").charAt(0));
				Edital edital = editalDAO.getById(rs.getInt("edital_id"));
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