package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.GrandeArea;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.util.StringUtil;

public class GrandeAreaDAO implements GenericDAO<Integer, GrandeArea> {

	static DBPool banco;
	
	public Connection connection;
	
	private static GrandeAreaDAO instance;

	public static GrandeAreaDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new GrandeAreaDAO(banco);
		return instance;
	}
	
	public GrandeAreaDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}
	
	@Override
	public List<GrandeArea> getAll() throws SQLExceptionQManager {
		
		List<GrandeArea> grandesAreas = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {

			String sql = String
					.format("%s",
							"SELECT grandearea.id_grande_area,"
							+ " grandearea.cd_grande_area,"
							+ " grandearea.nm_grande_area"
							+ " FROM tb_grande_area AS grandearea");

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			rs = stmt.executeQuery(sql);
			
			grandesAreas = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {
			
			banco.close(stmt, rs, this.connection);
		}

		return grandesAreas;
		
	}
	
	@Override
	public List<GrandeArea> convertToList(ResultSet rs)
			throws SQLExceptionQManager {
		
		List<GrandeArea> grandesAreas = new ArrayList<GrandeArea>();

		try {
			while (rs.next()) {
				
				GrandeArea grandeArea = new GrandeArea();
				grandeArea.setIdGrandeArea(rs.getInt("grandearea.id_grande_area"));
				grandeArea.setCodigo(rs.getString("grandearea.cd_grande_area"));
				grandeArea.setNome(StringUtil.upperCaseNomeCompleto(
						rs.getString("grandearea.nm_grande_area")));

				grandesAreas.add(grandeArea);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return grandesAreas;
	}
	
	@Override
	public int insert(GrandeArea entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(GrandeArea entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GrandeArea getById(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GrandeArea> find(GrandeArea entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}	
}
