package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class InstituicaoBancariaDAO implements
		GenericDAO<Integer, InstituicaoBancaria> {

	// a conexão com o banco de dados
	public Connection connection;

	public InstituicaoBancariaDAO(DatabaseConnection banco) {
		this.connection = (Connection) banco.getConnection();
	}

	@Override
	public InstituicaoBancaria getById(Integer id) throws QManagerSQLException {

		InstituicaoBancaria instituicaoBancaria = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT * FROM `tb_instituicao_bancaria` WHERE `id_instituicao_bancaria` =",
							id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<InstituicaoBancaria> instituicoesBancarias = convertToList(rs);

			if (instituicoesBancarias.size() != 0) {
				instituicaoBancaria = instituicoesBancarias.get(0);
			} else {
				throw new QManagerSQLException(777, "'id_instituicao_bancaria= " + id + "'");
			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return instituicaoBancaria;

	}

	@Override
	public int insert(InstituicaoBancaria instituicaoBancaria)
			throws QManagerSQLException {

		int chave = 0;

		try {

			// Define um insert com os atributos e cada valor é representado
			// por ?
			String sql = String.format("%s %s ('%s')",
					"INSERT INTO `tb_instituicao_bancaria` (`nm_banco`)",
					"VALUES", instituicaoBancaria.getNomeBanco());

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
	public void update(InstituicaoBancaria instituicaoBancaria)
			throws QManagerSQLException {

		try {

			// Define update setando cada atributo e cada valor é
			// representado por ?
			String sql = "UPDATE `tb_instituicao_bancaria` SET `nm_banco`=?"
					+ " WHERE `id_instituicao_bancaria`=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, instituicaoBancaria.getNomeBanco());
			stmt.setInt(2, instituicaoBancaria.getIdInstituicaoBancaria());

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
			String sql = "DELETE FROM `tb_instituicao_bancaria` WHERE `id_instituicao_bancaria`=?";

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
	public List<InstituicaoBancaria> findAll() throws QManagerSQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InstituicaoBancaria> convertToList(ResultSet rs)
			throws QManagerSQLException {

		List<InstituicaoBancaria> instituicoesBancarias = new ArrayList<InstituicaoBancaria>();

		InstituicaoBancaria instituicaoBancaria = new InstituicaoBancaria();

		try {

			while (rs.next()) {
				instituicaoBancaria.setIdInstituicaoBancaria(rs
						.getInt("id_instituicao_bancaria"));
				instituicaoBancaria.setNomeBanco(rs.getString("nm_banco"));
				instituicaoBancaria.setRegistro(rs.getDate("dt_registro"));
				
				instituicoesBancarias.add(instituicaoBancaria);

			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return instituicoesBancarias;

	}

}
