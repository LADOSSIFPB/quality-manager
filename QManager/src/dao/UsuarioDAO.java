package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAO implements GenericDAO<Integer, UsuarioDAO> {

	public UsuarioDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UsuarioDAO getById(Integer pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(UsuarioDAO entity) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(UsuarioDAO entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UsuarioDAO entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UsuarioDAO> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsuarioDAO> convertToList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
}
