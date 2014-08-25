package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import principal.Banco;
import br.edu.ifpb.qmanager.entidade.ContaBancaria;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ContaBancariaDAO implements GenericDAO<Integer, ContaBancaria> {

	// a conexão com o banco de dados
	public Connection connection;

	public ContaBancariaDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}

	@Override
	public ContaBancaria getById(Integer pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ContaBancaria entity) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(ContaBancaria contaBancaria) throws SQLException {
		try {

			// Define um update com os atributos e cada valor é representado por
			// ?
			String sql = "UPDATE `tb_dados_bancarios` SET `instituicao_bancaria_id`=?, `nr_operacao`=?, `nr_conta`=? "
					+ "WHERE pessoa_id`= ?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, contaBancaria.getInstituicaoBancaria()
					.getIdInstituicaoBancaria());
			stmt.setString(2, contaBancaria.getOperacao());
			stmt.setString(3, contaBancaria.getConta());
			stmt.setInt(4, contaBancaria.getPessoaId());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}
	}

	@Override
	public void delete(ContaBancaria contaBancaria) throws SQLException {

		String sql = "DELETE FROM `tb_dados_bancarios` WHERE `pessoa_id`=?";

		PreparedStatement stmt = (PreparedStatement) connection
				.prepareStatement(sql);

		// seta os valores
		stmt.setInt(1, contaBancaria.getPessoaId());

		// envia para o Banco e fecha o objeto
		stmt.execute();
		stmt.close();
	}

	@Override
	public List<ContaBancaria> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContaBancaria> convertToList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}
