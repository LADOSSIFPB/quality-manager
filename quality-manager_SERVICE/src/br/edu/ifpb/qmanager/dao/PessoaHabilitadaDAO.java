package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.Titulacao;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class PessoaHabilitadaDAO implements GenericDAO<Integer, Servidor> {

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
	
	public Servidor getServidorByMatricula(Integer siape) throws SQLExceptionQManager {
		
		Servidor servidor = null;
		
		PreparedStatement stmt = null;

		ResultSet rs = null;
		
		try {

			String sql = String
					.format("%s %d",
							"SELECT pessoahabilitada.id_pessoa_habilitada,"
								+ " pessoahabilitada.nm_pessoa_habilitada,"
								+ " pessoahabilitada.nr_siape,"
								+ " pessoahabilitada.nm_email,"
								+ " pessoahabilitada.id_titulacao,"
								+ " titulacao.nm_titulacao,"
								+ " pessoahabilitada.id_departamento,"
								+ " departamento.nm_departamento,"
								+ " pessoahabilitada.id_campus_institucional,"
								+ " campus.nm_campus_institucional"
								+ " FROM tb_pessoa_habilitada AS pessoahabilitada"
								+ " INNER JOIN tb_titulacao titulacao"
									+ " ON pessoahabilitada.id_titulacao = titulacao.id_titulacao"
								+ " INNER JOIN tb_departamento departamento"
									+ " ON pessoahabilitada.id_departamento = departamento.id_departamento"
								+ " INNER JOIN tb_campus_institucional campus"
									+ " ON pessoahabilitada.id_campus_institucional = campus.id_campus_institucional"
								+ " WHERE pessoahabilitada.nr_siape =", siape);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Servidor> servidores = convertToList(rs);

			if (!servidores.isEmpty())
				servidor = servidores.get(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {
			banco.closeQuery(stmt, rs);
		}

		return servidor;
	}

	@Override
	public List<Servidor> convertToList(ResultSet rs) throws SQLExceptionQManager {
		
		List<Servidor> servidores = new LinkedList<Servidor>();

		try {

			while (rs.next()) {

				Servidor servidor = new Servidor();				
				Titulacao titulacao = new Titulacao();
				
				// tabela pessoa
				servidor.setPessoaId(rs.getInt("pessoahabilitada.id_pessoa_habilitada"));
				servidor.setNomePessoa(rs.getString("pessoahabilitada.nm_pessoa_habilitada"));

				// servidor
				titulacao.setIdTitulacao(rs.getInt("pessoahabilitada.id_titulacao"));
				titulacao.setNome(rs.getString("titulacao.nm_titulacao"));
				servidor.setTitulacao(titulacao);
				
				servidores.add(servidor);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return servidores;
	}

	@Override
	public int insert(Servidor entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Servidor entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Servidor> getAll() throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Servidor getById(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Servidor> find(Servidor entity) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}
}
