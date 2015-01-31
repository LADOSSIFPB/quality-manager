package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.util.List;

import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public interface GenericDAO<PK, T> {

	// public void insert(T entity) throws SQLException;

	public int insert(T entity) throws SQLExceptionQManager;

	public void update(T entity) throws SQLExceptionQManager;

	public void delete(PK pk) throws SQLExceptionQManager;

	public List<T> getAll() throws SQLExceptionQManager;

	public T getById(PK pk) throws SQLExceptionQManager;

	public List<T> find(T entity) throws SQLExceptionQManager;

	public List<T> convertToList(ResultSet rs) throws SQLExceptionQManager;

}