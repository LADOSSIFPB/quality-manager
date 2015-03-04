package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Titulacao;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class TitulacaoDAO implements GenericDAO<Integer, Titulacao> {

	static DBPool banco;
	
	public Connection connection;
	
	private static TitulacaoDAO instance;

	public static TitulacaoDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new TitulacaoDAO(banco);
		}
		return instance;
	}
	
	@Override
	public List<Titulacao> getAll() throws SQLExceptionQManager {
		
		List<Titulacao> titulacoes = null;

		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {

			String sql = String
					.format("%s",
							"SELECT titulacao.id_titulacao,"
							+ " titulacao.nm_titulacao"
							+ " FROM tb_titulacao AS titulacao");

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			rs = stmt.executeQuery(sql);
			
			titulacoes = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {
			
			banco.closeQuery(stmt, rs);
		}

		return titulacoes;
	}

	public TitulacaoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}
	
	@Override
	public int insert(Titulacao entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Titulacao entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Titulacao getById(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Titulacao> find(Titulacao entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Titulacao> convertToList(ResultSet rs)
			throws SQLExceptionQManager {
		
		List<Titulacao> titulacoes = new ArrayList<Titulacao>();

		try {
			while (rs.next()) {
				
				Titulacao titulacao = new Titulacao();
				titulacao.setIdTitulacao(rs.getInt("titulacao.id_titulacao"));
				titulacao.setNome(rs.getString("titulacao.nm_titulacao"));

				titulacoes.add(titulacao);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return titulacoes;
	}
}
