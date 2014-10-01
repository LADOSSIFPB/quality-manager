package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.util.List;

import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

public interface GenericDAO<PK, T> {

	// public void insert(T entity) throws SQLException;

	public int insert(T entity) throws QManagerSQLException;

	public void update(T entity) throws QManagerSQLException;

	public void delete(PK pk) throws QManagerSQLException;

	public List<T> getAll() throws QManagerSQLException;
	
	public T getById(PK pk) throws QManagerSQLException;

	public List<T> convertToList(ResultSet rs) throws QManagerSQLException;

}