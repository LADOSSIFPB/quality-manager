package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Arquivo;
import br.edu.ifpb.qmanager.entidade.ArquivoEdital;
import br.edu.ifpb.qmanager.entidade.ArquivoProjeto;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class ArquivoEditalDAO implements GenericDAO<Integer, ArquivoEdital> {

	static DBPool banco;
	
	private static ArquivoEditalDAO instance;
	
	public Connection connection;

	public static ArquivoEditalDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new ArquivoEditalDAO(banco);
		return instance;
	}

	public ArquivoEditalDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(ArquivoEdital arquivoEdital) throws SQLExceptionQManager {
		int idArquivoEdital = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {

			// Arquivo
			Arquivo arquivo = arquivoEdital.getArquivo();
			
			ArquivoDAO arquivoDAO = ArquivoDAO.getInstance();			
			int idArquivo = arquivoDAO.insert(arquivo);
			
			arquivo.setIdArquivo(idArquivo);
			
			// Arquivo Projeto
			String sql = String
					.format("%s %s (%d, %d, %d)",
							"INSERT INTO tb_arquivo_projeto (edital_id,"
							+ " arquivo_id,"
							+ " tp_arquivo_edital)",
							" VALUES",
							arquivoEdital.getEdital().getIdEdital(),
							arquivo.getIdArquivo(),
							arquivoEdital.getTipoArquivoEdital().getId());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idArquivoEdital = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}

		return idArquivoEdital;
	}

	@Override
	public void update(ArquivoEdital entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ArquivoEdital> getAll() throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArquivoEdital getById(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArquivoEdital> find(ArquivoEdital entity)
			throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArquivoEdital> convertToList(ResultSet rs)
			throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}
}
