package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Arquivo;
import br.edu.ifpb.qmanager.entidade.ArquivoParticipacao;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class ArquivoParticipacaoDAO implements GenericDAO<Integer, ArquivoParticipacao> {

	static DBPool banco;
	
	private static ArquivoParticipacaoDAO instance;
	
	public Connection connection;

	public static ArquivoParticipacaoDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new ArquivoParticipacaoDAO(banco);
		return instance;
	}

	public ArquivoParticipacaoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(ArquivoParticipacao arquivoParticipacao) throws SQLExceptionQManager {
		int idArquivoEdital = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {

			// Arquivo
			Arquivo arquivo = arquivoParticipacao.getArquivo();
			
			ArquivoDAO arquivoDAO = ArquivoDAO.getInstance();			
			int idArquivo = arquivoDAO.insert(arquivo);
			
			arquivo.setIdArquivo(idArquivo);
			
			// Arquivo Projeto
			String sql = String
					.format("%s %s (%d, %d, %d)",
							"INSERT INTO tb_arquivo_participacao (participacao_id,"
							+ " arquivo_id,"
							+ " tp_arquivo_participacao)",
							" VALUES",
							arquivoParticipacao.getParticipacao().getIdParticipacao(),
							arquivo.getIdArquivo(),
							arquivoParticipacao.getTipoArquivoParticipacao().getId());

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
	public void update(ArquivoParticipacao entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ArquivoParticipacao> getAll() throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArquivoParticipacao getById(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArquivoParticipacao> find(ArquivoParticipacao entity)
			throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArquivoParticipacao> convertToList(ResultSet rs)
			throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}
}
