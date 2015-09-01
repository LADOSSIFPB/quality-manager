package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

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
	public Role getById(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
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
