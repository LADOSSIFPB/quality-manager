package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class InstituicaoBancariaDAO implements
		GenericDAO<Integer, InstituicaoBancaria> {

	static DBPool banco;
	private static InstituicaoBancariaDAO instance;

	public static InstituicaoBancariaDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new InstituicaoBancariaDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public InstituicaoBancariaDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(InstituicaoBancaria instituicaoBancaria)
			throws SQLExceptionQManager {

		int chave = 0;

		try {

			// Define um insert com os atributos e cada valor é representado
			// por ?
			String sql = String.format("%s %s ('%s', '%s')",
					"INSERT INTO tb_instituicao_bancaria (nm_banco, nr_cnpj)",
					"VALUES", instituicaoBancaria.getNomeBanco(),
					instituicaoBancaria.getCnpj());

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// envia para o Banco e fecha o objeto
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
	public void update(InstituicaoBancaria instituicaoBancaria)
			throws SQLExceptionQManager {

		try {

			// Define update setando cada atributo e cada valor é
			// representado por ?
			String sql = "UPDATE tb_instituicao_bancaria SET nm_banco=?, nr_cnpj=?"
					+ " WHERE id_instituicao_bancaria=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, instituicaoBancaria.getNomeBanco());
			stmt.setString(2, instituicaoBancaria.getCnpj());
			stmt.setInt(3, instituicaoBancaria.getIdInstituicaoBancaria());

			// envia para o Banco e fecha o objeto
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

			// Deleta uma tupla setando o atributo de identificação com
			// valor representado por ?
			String sql = "DELETE FROM tb_instituicao_bancaria WHERE id_instituicao_bancaria=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, id);

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public List<InstituicaoBancaria> getAll() throws SQLExceptionQManager {
		List<InstituicaoBancaria> instituicoesBancarias;

		try {

			String sql = String
					.format("%s",
							"SELECT instituicao_bancaria.id_instituicao_bancaria, "
									+ "instituicao_bancaria.nm_banco, instituicao_bancaria.nr_cnpj, "
									+ "instituicao_bancaria.dt_registro "
									+ "FROM tb_instituicao_bancaria instituicao_bancaria");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			instituicoesBancarias = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return instituicoesBancarias;
	}

	@Override
	public InstituicaoBancaria getById(Integer id) throws SQLExceptionQManager {

		InstituicaoBancaria instituicaoBancaria = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT instituicao_bancaria.id_instituicao_bancaria, "
									+ "instituicao_bancaria.nm_banco, instituicao_bancaria.nr_cnpj, "
									+ "instituicao_bancaria.dt_registro "
									+ "FROM tb_instituicao_bancaria instituicao_bancaria "
									+ "WHERE instituicao_bancaria.id_instituicao_bancaria =",
							id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<InstituicaoBancaria> instituicoesBancarias = convertToList(rs);

			if (!instituicoesBancarias.isEmpty())
				instituicaoBancaria = instituicoesBancarias.remove(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return instituicaoBancaria;

	}

	@Override
	public List<InstituicaoBancaria> convertToList(ResultSet rs)
			throws SQLExceptionQManager {

		List<InstituicaoBancaria> instituicoesBancarias = new LinkedList<InstituicaoBancaria>();

		try {

			while (rs.next()) {
				InstituicaoBancaria instituicaoBancaria = new InstituicaoBancaria();
				instituicaoBancaria.setIdInstituicaoBancaria(rs
						.getInt("instituicao_bancaria.id_instituicao_bancaria"));
				instituicaoBancaria.setNomeBanco(rs.getString("instituicao_bancaria.nm_banco"));
				instituicaoBancaria.setCnpj(rs.getString("instituicao_bancaria.nr_cnpj"));
				instituicaoBancaria.setRegistro(rs.getDate("instituicao_bancaria.dt_registro"));

				instituicoesBancarias.add(instituicaoBancaria);

			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return instituicoesBancarias;

	}

}
