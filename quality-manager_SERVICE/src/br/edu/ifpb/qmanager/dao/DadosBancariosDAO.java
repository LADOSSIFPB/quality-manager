package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DadosBancariosDAO implements GenericDAO<Integer, Pessoa> {

	// a conexão com o banco de dados
	public Connection connection;
	public DatabaseConnection banco;

	public DadosBancariosDAO(DatabaseConnection banco) {
		this.connection = (Connection) banco.getConnection();
		this.banco = banco;
	}

	@Override
	public int insert(Pessoa pessoa) throws QManagerSQLException {

		int chave = 0;

		try {

			String sql = String
					.format("%s %s (%d, %d, '%s', '%s')",
							"INSERT INTO `tb_dados_bancarios` (`pessoa_id`, `instituicao_bancaria_id`, `nr_operacao`, `nr_conta`)",
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
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return chave;

	}

	@Override
	public void update(Pessoa pessoa) throws QManagerSQLException {

		try {

			String sql = "UPDATE `tb_dados_bancarios` SET `instituicao_bancaria_id`=?, `nr_operacao`=?, `nr_conta`=? "
					+ "WHERE pessoa_id`= ?";

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
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}
	}

	@Override
	public void delete(Integer id) throws QManagerSQLException {

		try {

			String sql = "DELETE FROM `tb_dados_bancarios` WHERE `pessoa_id`=?";

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
	public List<Pessoa> getAll() throws QManagerSQLException {
		return null;
	}

	public List<DadosBancarios> getAllDadosBancarios()
			throws QManagerSQLException {
		List<DadosBancarios> dadosBancarios;

		try {

			String sql = String
					.format("%s",
							"SELECT * FROM `tb_dados_bancarios` ORDER BY `dt_registro` DESC");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			dadosBancarios = convertToListDadosBancarios(rs);

			if (dadosBancarios.size() == 0) {
				throw new QManagerSQLException(777, "");
			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return dadosBancarios;
	}

	@Override
	public Pessoa getById(Integer id) throws QManagerSQLException {
		return null;
	}

	public DadosBancarios getByIdDadosBancarios(Integer id)
			throws QManagerSQLException {

		DadosBancarios dadosBancarios = null;

		try {

			// seleciona dados bancários em ordem de inserção decrescente
			String sql = String.format("%s %d %s",
					"SELECT * FROM `tb_dados_bancarios` WHERE `pessoa_id` =",
					id, "ORDER BY `dt_registro` DESC");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<DadosBancarios> listaDadosBancarios = convertToListDadosBancarios(rs);

			// recupera o dado bancário atual
			dadosBancarios = listaDadosBancarios.get(0);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return dadosBancarios;

	}

	@Override
	public List<Pessoa> convertToList(ResultSet rs) throws QManagerSQLException {
		return null;
	}

	public List<DadosBancarios> convertToListDadosBancarios(ResultSet rs)
			throws QManagerSQLException {

		List<DadosBancarios> listaDadosBancarios = new ArrayList<DadosBancarios>();

		InstituicaoBancariaDAO instituicaoBancariaDAO = new InstituicaoBancariaDAO(
				banco);

		try {

			while (rs.next()) {
				DadosBancarios dadosBancarios = new DadosBancarios();
				InstituicaoBancaria instituicaoBancaria = new InstituicaoBancaria();
				instituicaoBancaria = instituicaoBancariaDAO.getById(rs
						.getInt("instituicao_bancaria_id"));
				dadosBancarios.setInstituicaoBancaria(instituicaoBancaria);
				dadosBancarios.setOperacao(rs.getString("nr_operacao"));
				dadosBancarios.setConta(rs.getString("nr_conta"));
				dadosBancarios.setRegistro(rs.getDate("dt_registro"));

				listaDadosBancarios.add(dadosBancarios);
			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return listaDadosBancarios;

	}
}
