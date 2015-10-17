package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Arquivo;
import br.edu.ifpb.qmanager.entidade.ArquivoParticipacao;
import br.edu.ifpb.qmanager.entidade.ArquivoProjeto;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.tipo.TipoArquivoParticipacao;

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
	public int delete(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return BancoUtil.NOROWSUPDATED;
	}
	
	public int deleteByParticipacaoId(Integer idParticipacao) throws SQLExceptionQManager {
PreparedStatement stmt = null;
		
		int rowsUpdated = BancoUtil.NOROWSUPDATED;

		try {

			String sql = "UPDATE tb_arquivo_participacao" 
					+ " SET fl_removido = ?,"
					+ " dt_removido = ?"
					+ " WHERE participacao_id = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setBoolean(1, true);
			stmt.setTimestamp(2, BancoUtil.getCurrenteTimeStamp());
			stmt.setInt(3, idParticipacao);

			rowsUpdated = stmt.executeUpdate();
			
			//TODO: Remover os arquivos.
			if (rowsUpdated != BancoUtil.NOROWSUPDATED) {
				
			}

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}
		
		return rowsUpdated;
	}
	
	public List<ArquivoParticipacao> getByParticipacaoId(Integer idParticipacao) throws SQLExceptionQManager {
		
		List<ArquivoParticipacao> arquivosParticipacao;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d",
					"SELECT arqparticipacao.id_arquivo_participacao,"
						+ " arqparticipacao.participacao_id,"
						+ " arqparticipacao.arquivo_id,"
						+ " arqparticipacao.tp_arquivo_participacao"
						+ " FROM tb_arquivo_participacao AS arqparticipacao"
						+ " WHERE participacao_id = ",
						idParticipacao);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			arquivosParticipacao = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return arquivosParticipacao;
	}
	
	public ArquivoProjeto getArquivoByParticipacao(Integer idParticipacao) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
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
		
		List<ArquivoParticipacao> arquivosParticipacao = new ArrayList<ArquivoParticipacao>();

		try {

			while (rs.next()) {
				
				ArquivoParticipacao arquivoParticipacao = new ArquivoParticipacao();

				arquivoParticipacao.setIdArquivoParticipacao(rs.getInt(""));
				
				Participacao participacao = new Participacao();
				participacao.setIdParticipacao(rs.getInt(""));
				arquivoParticipacao.setParticipacao(participacao);
				
				Arquivo arquivo = new Arquivo();
				arquivo.setIdArquivo(rs.getInt(""));
				arquivoParticipacao.setArquivo(arquivo);
				
				//arquivoParticipacao.setTipoArquivoParticipacao(rs.getInt(""));

				arquivosParticipacao.add(arquivoParticipacao);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return arquivosParticipacao;
	}
}
