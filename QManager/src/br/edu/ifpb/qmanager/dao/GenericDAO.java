package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.util.List;

import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

public interface GenericDAO<PK, T> {

	public T getById(PK pk) throws QManagerSQLException;

	// public void insert(T entity) throws SQLException;

	public int insert(T entity) throws QManagerSQLException;

	public void update(T entity) throws QManagerSQLException;

	public void delete(T entity) throws QManagerSQLException;

	public List<T> findAll() throws QManagerSQLException;

	public List<T> convertToList(ResultSet rs) throws QManagerSQLException;
	
}