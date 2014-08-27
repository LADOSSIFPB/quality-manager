package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import principal.Banco;
import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/*
 TABLE `tb_dados_bancarios`
 `pessoa_id` INT NOT NULL,
 `instituicao_bancaria_id` INT NOT NULL,
 `nr_operacao` VARCHAR(3),
 `nr_conta` VARCHAR(10) NOT NULL,
 `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
 */

public class DadosBancariosDAO implements GenericDAO<Integer, DadosBancarios> {

	// a conexão com o banco de dados
	public Connection connection;

	public DadosBancariosDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}

	@Override
	public DadosBancarios getById(Integer id) throws QManagerSQLException {

		DadosBancarios dadosBancarios = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT * FROM `tb_dados_bancarios` WHERE `id_dados_bancarios` =",
							id);

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<DadosBancarios> listaDadosBancarios = convertToList(rs);

			dadosBancarios = listaDadosBancarios.get(0);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return dadosBancarios;

	}

	@Override
	public int insert(DadosBancarios dadosBancarios)
			throws QManagerSQLException {

		int chave = 0;

		try {

			String sql = String
					.format("%s %s (%d, '%s', '%s')",
							"INSERT INTO `tb_dados_bancarios` (`instituicao_bancaria_id`, `nr_operacao`, `nr_conta`)",
							"VALUES", dadosBancarios.getInstituicaoBancaria()
									.getIdInstituicaoBancaria(), dadosBancarios
									.getOperacao(), dadosBancarios.getConta());

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// envia para o Banco e fecha o objeto
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			chave = BancoUtil.getGenerateKey(stmt);

			stmt.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return chave;

	}

	@Override
	public void update(DadosBancarios dadosBancarios)
			throws QManagerSQLException {
		try {

			// Define um update com os atributos e cada valor é representado por
			// ?
			String sql = "UPDATE `tb_dados_bancarios` SET `instituicao_bancaria_id`=?, `nr_operacao`=?, `nr_conta`=? "
					+ "WHERE pessoa_id`= ?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, dadosBancarios.getInstituicaoBancaria()
					.getIdInstituicaoBancaria());
			stmt.setString(2, dadosBancarios.getOperacao());
			stmt.setString(3, dadosBancarios.getConta());
			stmt.setInt(4, dadosBancarios.getPessoaId());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}
	}

	@Override
	public void delete(DadosBancarios dadosBancarios) throws QManagerSQLException {

		try {

			String sql = "DELETE FROM `tb_dados_bancarios` WHERE `pessoa_id`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, dadosBancarios.getPessoaId());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	@Override
	public List<DadosBancarios> findAll() throws QManagerSQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DadosBancarios> convertToList(ResultSet rs) {

		List<DadosBancarios> listaDadosBancarios = new ArrayList<DadosBancarios>();

		DadosBancarios dadosBancarios = new DadosBancarios();

		try {

			while (rs.next()) {
				/*
				 * é necessário fazer uma busca pela Instituição Bancária e
				 * setála aqui ;)
				 * dadosBancarios.setInstituicaoBancaria(rs.getInt
				 * ("instituicao_bancaria_id");
				 */
				dadosBancarios.setOperacao(rs.getString("nr_operacao"));
				dadosBancarios.setConta(rs.getString("nr_conta"));
				dadosBancarios.setRegistro(rs.getDate("dt_registro"));

				listaDadosBancarios.add(dadosBancarios);

			}

		} catch (SQLException e) {
			Logger.getLogger(InstituicaoDAO.class.getName()).log(Level.SEVERE,
					null, e);
		}

		return listaDadosBancarios;

	}

}
