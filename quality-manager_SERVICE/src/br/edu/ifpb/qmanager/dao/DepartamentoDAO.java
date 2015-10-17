package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Departamento;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class DepartamentoDAO implements GenericDAO<Integer, Departamento> {

	static DBPool banco;
	
	public Connection connection;
	
	private static DepartamentoDAO instance;

	public static DepartamentoDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new DepartamentoDAO(banco);
		return instance;
	}
	
	@Override
	public List<Departamento> getAll() throws SQLExceptionQManager {
		
		List<Departamento> departamentos = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {

			String sql = String
					.format("%s",
							"SELECT departamento.id_departamento,"
							+ " departamento.nm_departamento"
							+ " FROM tb_departamento AS departamento");

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			rs = stmt.executeQuery(sql);
			
			departamentos = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {
			
			banco.close(stmt, rs, this.connection);
		}

		return departamentos;
		
	}
	
	@Override
	public List<Departamento> convertToList(ResultSet rs)
			throws SQLExceptionQManager {
		
		List<Departamento> departamentos = new ArrayList<Departamento>();

		try {
			while (rs.next()) {
				
				Departamento departamento = new Departamento();
				departamento.setIdDepartamento(rs.getInt("departamento.id_departamento"));
				departamento.setNome(rs.getString("departamento.nm_departamento"));

				departamentos.add(departamento);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return departamentos;
	}

	public DepartamentoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}
	
	@Override
	public int insert(Departamento entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Departamento entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int delete(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return BancoUtil.NOROWSUPDATED;
	}

	@Override
	public Departamento getById(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Departamento> find(Departamento entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}	
}
