package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class PessoaHabilitadaDAO implements GenericDAO<Integer, Pessoa> {

private static DBPool banco;
	
	private static PessoaHabilitadaDAO instance;

	private static Logger logger = LogManager.getLogger(PessoaDAO.class);

	public PessoaHabilitadaDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	public static PessoaHabilitadaDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new PessoaHabilitadaDAO(banco);
		}
		return instance;
	}

	public Connection connection;
	
	@Override
	public int insert(Pessoa entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Pessoa entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Pessoa> getAll() throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pessoa getById(Integer id) throws SQLExceptionQManager {
		return null;		
	}
	
	public Pessoa getByMatricula(Integer siape) throws SQLExceptionQManager {
		
		Pessoa pessoa = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT pessoaHabilitada.id_pessoa_habilitada,"
								+ " pessoaHabilitada.nm_pessoa_habilitada,"
								+ " pessoaHabilitada.nr_cpf,"
								+ " FROM tb_pessoa_habilitada pessoa AS pessoaHabilitada"
								+ " WHERE pessoaHabilitada.nr_siape =", siape);

			PreparedStatement stmt;
			stmt = (PreparedStatement) connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Pessoa> pessoas = convertToList(rs);

			if (!pessoas.isEmpty())
				pessoa = pessoas.get(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return pessoa;
	}

	@Override
	public List<Pessoa> find(Pessoa entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pessoa> convertToList(ResultSet rs) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

}
