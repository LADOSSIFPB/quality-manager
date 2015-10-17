package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class InstituicaoFinanciadoraDAO implements
		GenericDAO<Integer, InstituicaoFinanciadora> {

	static DBPool banco;

	private static InstituicaoFinanciadoraDAO instance;

	public Connection connection;

	public static InstituicaoFinanciadoraDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new InstituicaoFinanciadoraDAO(banco);
		return instance;
	}

	public InstituicaoFinanciadoraDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(InstituicaoFinanciadora instituicao)
			throws SQLExceptionQManager {

		int chave = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {

			String sql = String.format("%s %s ('%s', '%s', '%s', %d)",
					"INSERT INTO tb_instituicao_financiadora ("
							+ "nr_cnpj, nm_instituicao, nm_sigla, pessoa_id)",
					"VALUES", instituicao.getCnpj(), instituicao
							.getNomeInstituicaoFinanciadora(), instituicao
							.getSigla(), instituicao.getCadastrador().getPessoaId());

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
	public void update(InstituicaoFinanciadora instituicao)
			throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_instituicao_financiadora SET "
						+ " nr_cnpj=?, " 
						+ " nm_instituicao=?, " 
						+ " nm_sigla=? "
						+ " WHERE id_instituicao=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, instituicao.getCnpj());
			stmt.setString(2, instituicao.getNomeInstituicaoFinanciadora());
			stmt.setString(3, instituicao.getSigla());
			stmt.setInt(4, instituicao.getIdInstituicaoFinanciadora());

			stmt.execute();

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, this.connection);
		}
	}

	@Override
	public int delete(Integer id) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "DELETE FROM tb_instituicao_financiadora WHERE id_instituicao=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, this.connection);
		}
		
		return BancoUtil.NOROWSUPDATED;
	}

	@Override
	public List<InstituicaoFinanciadora> getAll() throws SQLExceptionQManager {

		List<InstituicaoFinanciadora> instituicoes = new ArrayList<InstituicaoFinanciadora>();

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s",
							"SELECT instituicao_financiadora.id_instituicao,"
									+ " instituicao_financiadora.nr_cnpj,"
									+ " instituicao_financiadora.nm_instituicao,"
									+ " instituicao_financiadora.nm_sigla,"
									+ " instituicao_financiadora.pessoa_id,"
									+ " instituicao_financiadora.dt_registro"
									+ " FROM tb_instituicao_financiadora AS instituicao_financiadora");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			instituicoes = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return instituicoes;
	}

	@Override
	public InstituicaoFinanciadora getById(Integer id)
			throws SQLExceptionQManager {

		InstituicaoFinanciadora instituicao = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT instituicao_financiadora.id_instituicao,"
									+ " instituicao_financiadora.nr_cnpj,"
									+ " instituicao_financiadora.nm_instituicao,"
									+ " instituicao_financiadora.nm_sigla,"
									+ " instituicao_financiadora.pessoa_id,"
									+ " instituicao_financiadora.dt_registro"
									+ " FROM tb_instituicao_financiadora instituicao_financiadora"
									+ " WHERE instituicao_financiadora.id_instituicao =",
							id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<InstituicaoFinanciadora> instituicoes = convertToList(rs);

			if (!instituicoes.isEmpty())
				instituicao = instituicoes.get(0);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return instituicao;
	}

	@Override
	public List<InstituicaoFinanciadora> find(
			InstituicaoFinanciadora instituicaoFinanciadora)
			throws SQLExceptionQManager {

		List<InstituicaoFinanciadora> instituicoes = new ArrayList<InstituicaoFinanciadora>();

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s '%%%s%%'",
							"SELECT instituicao_financiadora.id_instituicao,"
									+ " instituicao_financiadora.nr_cnpj,"
									+ " instituicao_financiadora.nm_instituicao,"
									+ " instituicao_financiadora.nm_sigla,"
									+ " instituicao_financiadora.pessoa_id,"
									+ " instituicao_financiadora.dt_registro"
									+ " FROM tb_instituicao_financiadora instituicao_financiadora"
									+ " WHERE instituicao_financiadora.nm_instituicao LIKE",
							instituicaoFinanciadora
									.getNomeInstituicaoFinanciadora());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			instituicoes = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return instituicoes;
	}
	
	public boolean isCNPJCadastrado(String cnpj) throws SQLExceptionQManager {
		
		boolean isCNPJCadastrado = false;

		PreparedStatement stmt = null;
		
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s '%s'",
							"SELECT count(instituicao.nr_cnpj) AS quant_IF "
								+ " FROM tb_instituicao_financiadora as instituicao"
								+ " WHERE instituicao.nr_cnpj =",
							cnpj);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);
			
			int rowCount = rs.last() ? rs.getInt("quant_IF") : 0;  
			
			isCNPJCadastrado = (rowCount != 0);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}
		
		return isCNPJCadastrado;
	}

	@Override
	public List<InstituicaoFinanciadora> convertToList(ResultSet rs)
			throws SQLExceptionQManager {

		List<InstituicaoFinanciadora> instituicoes = new ArrayList<InstituicaoFinanciadora>();

		try {

			while (rs.next()) {

				InstituicaoFinanciadora instituicao = new InstituicaoFinanciadora();

				Servidor servidor = new Servidor();
				servidor = ServidorDAO.getInstance().getById(
						rs.getInt("instituicao_financiadora.pessoa_id"));
				instituicao.setCadastrador(servidor);

				instituicao.setIdInstituicaoFinanciadora(rs.getInt(
						"instituicao_financiadora.id_instituicao"));
				instituicao.setCnpj(rs.getString(
						"instituicao_financiadora.nr_cnpj"));
				instituicao.setNomeInstituicaoFinanciadora(rs.getString(
						"instituicao_financiadora.nm_instituicao"));
				instituicao.setSigla(rs.getString(
						"instituicao_financiadora.nm_sigla"));
				instituicao.setRegistro(rs.getDate(
						"instituicao_financiadora.dt_registro"));

				instituicoes.add(instituicao);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return instituicoes;
	}
}
