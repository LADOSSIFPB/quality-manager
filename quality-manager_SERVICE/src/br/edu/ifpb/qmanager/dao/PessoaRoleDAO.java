package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.PessoaRole;
import br.edu.ifpb.qmanager.entidade.Role;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class PessoaRoleDAO implements GenericDAO<Integer, PessoaRole> {

	static DBPool banco;
	
	public Connection connection;
	
	private static PessoaRoleDAO instance;

	public static PessoaRoleDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new PessoaRoleDAO(banco);
		return instance;
	}
	
	public PessoaRoleDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}
	
	public List<Role> getRoleByIdPessoa(Integer idPessoa) throws SQLExceptionQManager {
		
		List<Role> roles = new ArrayList<Role>();

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d",
					"SELECT pessoa_role.role_id" 
						+ " FROM tb_pessoa_role AS pessoa_role"
						+ " WHERE pessoa_role.pessoa_id =", 
						idPessoa);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				int idRole = rs.getInt("pessoa_role.role_id");
				Role role = RoleDAO.getInstance().getById(idRole);
				
				roles.add(role);
			}

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return roles;
	}

	@Override
	public int insert(PessoaRole pessoaRole) throws SQLExceptionQManager {
		
		int chave = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		List<Role> roles = pessoaRole.getRoles();
		
		try {
			
			for (Role role: roles) {
				
				try {
	
					String sql = String.format("%s %s (%d, %d)",
							"INSERT INTO  tb_pessoa_role ("
							+ " pessoa_id, "
							+ " role_id)",
							"VALUES", 
							pessoaRole.getPessoa().getPessoaId(),
							role.getIdRole());
	
					stmt = (PreparedStatement) connection.prepareStatement(sql);
	
					stmt.executeUpdate(sql);
	
				} catch (SQLException sqle) {
					
					throw new SQLExceptionQManager(sqle.getErrorCode(),
							sqle.getLocalizedMessage());
					
				} finally {
					
					banco.close(stmt, null);
				}
			}
		
		} finally {
			
			banco.close(stmt, this.connection);
		}

		return chave;
	}

	@Override
	public void update(PessoaRole entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int delete(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return BancoUtil.NOROWSUPDATED;
	}

	@Override
	public List<PessoaRole> getAll() throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PessoaRole getById(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaRole> find(PessoaRole entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoaRole> convertToList(ResultSet rs)
			throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}
}
