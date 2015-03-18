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

	public Connection connection;
	
	public static InstituicaoBancariaDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new InstituicaoBancariaDAO(banco);
		return instance;
	}	

	public InstituicaoBancariaDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(InstituicaoBancaria instituicaoBancaria)
			throws SQLExceptionQManager {

		int chave = 0;

		PreparedStatement stmt = null;

		try {

			String sql = String.format("%s %s ('%s', '%s')",
					"INSERT INTO tb_instituicao_bancaria (nm_banco, nr_cnpj)",
					"VALUES", instituicaoBancaria.getNomeBanco(),
					instituicaoBancaria.getCnpj());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			chave = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}

		return chave;
	}

	@Override
	public void update(InstituicaoBancaria instituicaoBancaria)
			throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_instituicao_bancaria"
					+ " SET nm_banco=?,"
					+ " nr_cnpj=?"
					+ " WHERE id_instituicao_bancaria=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, instituicaoBancaria.getNomeBanco());
			stmt.setString(2, instituicaoBancaria.getCnpj());
			stmt.setInt(3, instituicaoBancaria.getIdInstituicaoBancaria());

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

			String sql = "DELETE FROM tb_instituicao_bancaria WHERE id_instituicao_bancaria=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}
	}

	@Override
	public List<InstituicaoBancaria> getAll() throws SQLExceptionQManager {
		List<InstituicaoBancaria> instituicoesBancarias;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s",
							"SELECT instituicao_bancaria.id_instituicao_bancaria, "
									+ "instituicao_bancaria.nm_banco,"
									+ " instituicao_bancaria.nr_cnpj,"
									+ " instituicao_bancaria.dt_registro"
									+ " FROM tb_instituicao_bancaria instituicao_bancaria");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			instituicoesBancarias = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return instituicoesBancarias;

	}

	@Override
	public InstituicaoBancaria getById(Integer id) throws SQLExceptionQManager {

		InstituicaoBancaria instituicaoBancaria = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT instituicao_bancaria.id_instituicao_bancaria, "
									+ "instituicao_bancaria.nm_banco, instituicao_bancaria.nr_cnpj, "
									+ "instituicao_bancaria.dt_registro "
									+ "FROM tb_instituicao_bancaria instituicao_bancaria "
									+ "WHERE instituicao_bancaria.id_instituicao_bancaria =",
							id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<InstituicaoBancaria> instituicoesBancarias = convertToList(rs);

			if (!instituicoesBancarias.isEmpty())
				instituicaoBancaria = instituicoesBancarias.remove(0);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return instituicaoBancaria;

	}

	@Override
	public List<InstituicaoBancaria> find(
			InstituicaoBancaria instituicaoBancaria)
			throws SQLExceptionQManager {

		List<InstituicaoBancaria> instituicoesBancarias = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s '%%%s%%'",
							"SELECT instituicao_bancaria.id_instituicao_bancaria, "
									+ "instituicao_bancaria.nm_banco, instituicao_bancaria.nr_cnpj, "
									+ "instituicao_bancaria.dt_registro "
									+ "FROM tb_instituicao_bancaria instituicao_bancaria "
									+ "WHERE instituicao_bancaria.nm_banco LIKE",
							instituicaoBancaria.getNomeBanco());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			instituicoesBancarias = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return instituicoesBancarias;
	}

	@Override
	public List<InstituicaoBancaria> convertToList(ResultSet rs)
			throws SQLExceptionQManager {

		List<InstituicaoBancaria> instituicoesBancarias = new LinkedList<InstituicaoBancaria>();

		try {

			while (rs.next()) {
				InstituicaoBancaria instituicaoBancaria = new InstituicaoBancaria();
				instituicaoBancaria
						.setIdInstituicaoBancaria(rs
								.getInt("instituicao_bancaria.id_instituicao_bancaria"));
				instituicaoBancaria.setNomeBanco(rs
						.getString("instituicao_bancaria.nm_banco"));
				instituicaoBancaria.setCnpj(rs
						.getString("instituicao_bancaria.nr_cnpj"));
				instituicaoBancaria.setRegistro(rs
						.getDate("instituicao_bancaria.dt_registro"));

				instituicoesBancarias.add(instituicaoBancaria);

			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return instituicoesBancarias;
	}
}
