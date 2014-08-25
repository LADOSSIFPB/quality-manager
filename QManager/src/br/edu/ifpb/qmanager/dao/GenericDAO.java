package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<PK, T> {
 
    public T getById(PK pk);
 
    //public void insert(T entity) throws SQLException;
    
    public int insert(T entity) throws SQLException;
 
    public void update(T entity) throws SQLException;
 
    public void delete(T entity) throws SQLException;
 
    public List<T> findAll() throws SQLException;
    
    public List<T> convertToList(ResultSet rs);
}