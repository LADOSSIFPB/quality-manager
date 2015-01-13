package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class DadosBancariosDAO implements GenericDAO<Integer, Pessoa> {

	static DBPool banco;
	private static DadosBancariosDAO instance;

	public static DadosBancariosDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new DadosBancariosDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public DadosBancariosDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Pessoa pessoa) throws SQLExceptionQManager {

		int chave = 0;

		try {

			String sql = String
					.format("%s %s (%d, %d, '%s', '%s')",
							"INSERT INTO tb_dados_bancarios (pessoa_id, instituicao_bancaria_id, nr_operacao, nr_conta)",
							"VALUES", pessoa.getPessoaId(), pessoa
									.getDadosBancarios()
									.getInstituicaoBancaria()
									.getIdInstituicaoBancaria(), pessoa
									.getDadosBancarios().getOperacao(), pessoa
									.getDadosBancarios().getConta());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			chave = BancoUtil.getGenerateKey(stmt);

			stmt.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return chave;

	}

	@Override
	public void update(Pessoa pessoa) throws SQLExceptionQManager {

		try {

			String sql = "UPDATE tb_dados_bancarios SET instituicao_bancaria_id=?, nr_operacao=?, nr_conta=? "
					+ "WHERE pessoa_id= ?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, pessoa.getDadosBancarios().getInstituicaoBancaria()
					.getIdInstituicaoBancaria());
			stmt.setString(2, pessoa.getDadosBancarios().getOperacao());
			stmt.setString(3, pessoa.getDadosBancarios().getConta());
			stmt.setInt(4, pessoa.getPessoaId());

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}
	}

	@Override
	public void delete(Integer id) throws SQLExceptionQManager {

		try {

			String sql = "DELETE FROM tb_dados_bancarios WHERE pessoa_id=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}
	}

	@Override
	public List<Pessoa> getAll() throws SQLExceptionQManager {
		return null;
	}

	public List<DadosBancarios> getAllDadosBancarios()
			throws SQLExceptionQManager {
		List<DadosBancarios> dadosBancarios;

		try {

			String sql = String
					.format("%s",
							"SELECT dados_bancarios.pessoa_id, dados_bancarios.instituicao_bancaria_id,"
									+ "dados_bancarios.nr_operacao, dados_bancarios.nr_conta, "
									+ "dados_bancarios.dt_registro FROM tb_dados_bancarios dados_bancarios"
									+ "ORDER BY dados_bancarios.dt_registro DESC");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			dadosBancarios = convertToListDadosBancarios(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return dadosBancarios;
	}

	@Override
	public Pessoa getById(Integer id) throws SQLExceptionQManager {
		return null;
	}

	public DadosBancarios getByIdDadosBancarios(Integer id)
			throws SQLExceptionQManager {

		DadosBancarios dadosBancarios = null;

		try {

			// seleciona dados bancários em ordem de inserção decrescente
			String sql = String
					.format("%s %d %s",
							"SELECT dados_bancarios.pessoa_id, dados_bancarios.instituicao_bancaria_id,"
									+ "dados_bancarios.nr_operacao, dados_bancarios.nr_conta, "
									+ "dados_bancarios.dt_registro FROM tb_dados_bancarios dados_bancarios "
									+ "WHERE dados_bancarios.pessoa_id =", id,
							"ORDER BY dados_bancarios.dt_registro DESC");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<DadosBancarios> listaDadosBancarios = convertToListDadosBancarios(rs);

			if (!listaDadosBancarios.isEmpty())
				dadosBancarios = listaDadosBancarios.get(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return dadosBancarios;

	}

	@Override
	public List<Pessoa> convertToList(ResultSet rs) throws SQLExceptionQManager {
		return null;
	}

	public List<DadosBancarios> convertToListDadosBancarios(ResultSet rs)
			throws SQLExceptionQManager {

		List<DadosBancarios> listaDadosBancarios = new LinkedList<DadosBancarios>();

		try {

			while (rs.next()) {
				DadosBancarios dadosBancarios = new DadosBancarios();

				InstituicaoBancaria instituicaoBancaria = InstituicaoBancariaDAO
						.getInstance()
						.getById(
								rs.getInt("dados_bancarios.instituicao_bancaria_id"));

				String nomeBanco = instituicaoBancaria.getNomeBanco();

				dadosBancarios.getInstituicaoBancaria().setNomeBanco(nomeBanco);

				dadosBancarios
						.getInstituicaoBancaria()
						.setIdInstituicaoBancaria(
								rs.getInt("dados_bancarios.instituicao_bancaria_id"));
				dadosBancarios.setOperacao(rs
						.getString("dados_bancarios.nr_operacao"));
				dadosBancarios.setConta(rs
						.getString("dados_bancarios.nr_conta"));
				dadosBancarios.setRegistro(rs
						.getDate("dados_bancarios.dt_registro"));

				listaDadosBancarios.add(dadosBancarios);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return listaDadosBancarios;

	}
}
