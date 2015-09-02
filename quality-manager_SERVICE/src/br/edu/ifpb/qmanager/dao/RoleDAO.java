package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Role;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class RoleDAO implements GenericDAO<Integer, Role> {

	static DBPool banco;
	
	public Connection connection;
	
	private static RoleDAO instance;

	public static RoleDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new RoleDAO(banco);
		return instance;
	}
	
	public RoleDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Role entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Role entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Role> getAll() throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role getById(Integer id) throws SQLExceptionQManager {
		
		Role role = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d",
					"SELECT curso.id_curso,"
							+ " curso.nm_curso,"
							+ " curso.coordenador_id,"
							+ " curso.pessoa_id,"
							+ " curso.dt_registro" 
							+ " FROM tb_curso AS curso"
							+ " WHERE curso.id_curso =", 
					id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Role> roles = convertToList(rs);

			if (!roles.isEmpty())
				role = roles.get(0);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return role;
	}

	@Override
	public List<Role> find(Role entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> convertToList(ResultSet rs) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}
}
