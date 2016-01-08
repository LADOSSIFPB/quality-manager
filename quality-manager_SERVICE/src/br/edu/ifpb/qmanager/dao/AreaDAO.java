package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Area;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.util.StringUtil;

public class AreaDAO implements GenericDAO<Integer, Area> {

	static DBPool banco;
	
	public Connection connection;
	
	private static AreaDAO instance;

	public static AreaDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new AreaDAO(banco);
		return instance;
	}
	
	public AreaDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}
	
	public List<Area> getAreaByGrandeArea(int idGrandeArea) throws SQLExceptionQManager {
		
		List<Area> areas = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {

			String sql = String.format("%s %d",
					"SELECT area.id_area,"
					+ " area.grande_area_id,"
					+ " area.cd_area,"
					+ " area.nm_area"
					+ " FROM tb_area as area"
					+ " WHERE area.grande_area_id = ", 
					idGrandeArea);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);
			
			areas = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {
			
			banco.close(stmt, rs, this.connection);
		}

		return areas;
		
	}
	
	@Override
	public List<Area> convertToList(ResultSet rs)
			throws SQLExceptionQManager {
		
		List<Area> areas = new ArrayList<Area>();

		try {
			while (rs.next()) {
				
				Area area = new Area();
				area.setIdArea(rs.getInt("area.id_area"));
				area.setCodigo(rs.getString("area.cd_area"));
				area.setNome(StringUtil.upperCaseNomeCompleto(
						rs.getString("area.nm_area")));

				areas.add(area);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return areas;
	}

	@Override
	public int insert(Area entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Area entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int delete(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return BancoUtil.NOROWSUPDATED;
	}

	@Override
	public List<Area> getAll() throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Area getById(Integer id) throws SQLExceptionQManager {
		
		Area area = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT "
								+ " area.id_area,"
								+ " area.cd_area,"
								+ " area.nm_area"
								+ " FROM tb_area AS area "
								+ " WHERE area.id_area = ", id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Area> areas = convertToList(rs);

			if (!areas.isEmpty())
				area = areas.get(0);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return area;
	}

	@Override
	public List<Area> find(Area entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}
}
