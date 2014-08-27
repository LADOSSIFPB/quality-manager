package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.util.List;

import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

public class UsuarioDAO implements GenericDAO<Integer, UsuarioDAO> {

	public UsuarioDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UsuarioDAO getById(Integer pk) throws QManagerSQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(UsuarioDAO entity) throws QManagerSQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(UsuarioDAO entity) throws QManagerSQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(UsuarioDAO entity) throws QManagerSQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UsuarioDAO> findAll() throws QManagerSQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsuarioDAO> convertToList(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
}
