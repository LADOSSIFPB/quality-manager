package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class ReleaseDAO implements GenericDAO<Integer, String> {

	static DBPool banco;

	private static ReleaseDAO instance;

	public Connection connection;

	public static ReleaseDAO getInstance() {		
		banco = DBPool.getInstance();
		instance = new ReleaseDAO(banco);		
		return instance;
	}

	public ReleaseDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	public int count() throws SQLException {
		
		int count = 0;
		
		PreparedStatement stmt = null;
		
		ResultSet rs = null;

		try {

			String sql = String.format("%s", "SELECT count(id_tipo_pessoa) as result"
					+ " FROM tb_tipo_pessoa");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {				
				count = rs.getInt("result");
			}

		} finally {

			banco.close(stmt, rs, this.connection);
		}
		
		return count;
	}

	@Override
	public int insert(String entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(String entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int delete(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return BancoUtil.NOROWSUPDATED;
	}

	@Override
	public List<String> getAll() throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getById(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> find(String entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> convertToList(ResultSet rs) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}
}
