package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.ArquivoProjeto;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class ArquivoProjetoDAO implements GenericDAO<Integer, ArquivoProjeto> {

	static DBPool banco;
	
	private static ArquivoProjetoDAO instance;
	
	public Connection connection;

	public static ArquivoProjetoDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new ArquivoProjetoDAO(banco);
		return instance;
	}

	public ArquivoProjetoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(ArquivoProjeto arquivoProjeto) throws SQLExceptionQManager {
		
		int idArquivoProjeto = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {

			String sql = String
					.format("%s %s (%d, '%s', '%s', '%s', %d, %d)",
							"INSERT INTO tb_arquivo_projeto (projeto_id,"
							+ " nm_real_arquivo,"
							+ " nm_sistema_arquivo,"
							+ " nm_extensao_arquivo,"
							+ " tp_arquivo_projeto,"
							+ "	pessoa_id)",
							" VALUES",
							arquivoProjeto.getProjeto().getIdProjeto(),
							arquivoProjeto.getNomeRealArquivo(),
							arquivoProjeto.getNomeSistemaArquivo(),
							arquivoProjeto.getExtensaoArquivo(),
							1, // Tipo arquivo: (1) Projeto inicial, (2) Projeto corrigido, (3) Relatório parcial e (4) Relatório final (5) .
							arquivoProjeto.getPessoaUploader().getPessoaId());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idArquivoProjeto = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}

		return idArquivoProjeto;
	}

	@Override
	public void update(ArquivoProjeto entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub		
	}

	@Override
	public void delete(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub		
	}

	@Override
	public List<ArquivoProjeto> getAll() throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArquivoProjeto getById(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArquivoProjeto> find(ArquivoProjeto entity)
			throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArquivoProjeto> convertToList(ResultSet rs)
			throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}
}
