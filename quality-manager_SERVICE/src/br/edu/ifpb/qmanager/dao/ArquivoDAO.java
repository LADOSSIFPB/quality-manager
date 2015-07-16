package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Arquivo;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class ArquivoDAO implements GenericDAO<Integer, Arquivo> {

	static DBPool banco;
	
	private static ArquivoDAO instance;
	
	public Connection connection;

	public static ArquivoDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new ArquivoDAO(banco);
		return instance;
	}

	public ArquivoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Arquivo arquivo) throws SQLExceptionQManager {
		
		int idArquivo = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {

			String sql = String
					.format("%s %s ('%s', '%s', '%s', %d, '%s')",
							"INSERT INTO tb_arquivo("
							+ " nm_real_arquivo,"
							+ " nm_sistema_arquivo,"
							+ " nm_extensao_arquivo,"
							+ " tp_arquivo,"
							+ "	pessoa_id,"
							+ " )",
							" VALUES",
							arquivo.getNomeRealArquivo(),
							arquivo.getNomeSistemaArquivo(),
							arquivo.getExtensaoArquivo(),
							arquivo.getCadastradorArquivo().getPessoaId(),
							arquivo.getTipoArquivo().getId());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idArquivo = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}

		return idArquivo;
	}

	@Override
	public void update(Arquivo entity) throws SQLExceptionQManager {
	}

	@Override
	public void delete(Integer pk) throws SQLExceptionQManager {
	}

	@Override
	public List<Arquivo> getAll() throws SQLExceptionQManager {
		return null;
	}

	@Override
	public Arquivo getById(Integer pk) throws SQLExceptionQManager {
		return null;
	}

	@Override
	public List<Arquivo> find(Arquivo entity)
			throws SQLExceptionQManager {
		return null;
	}

	@Override
	public List<Arquivo> convertToList(ResultSet rs)
			throws SQLExceptionQManager {
		return null;
	}
}
