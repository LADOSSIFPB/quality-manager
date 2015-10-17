package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.entidade.Departamento;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.Titulacao;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.util.StringUtil;

public class PessoaHabilitadaDAO implements GenericDAO<Integer, Servidor> {

	private static DBPool banco;

	private static PessoaHabilitadaDAO instance;

	public Connection connection;

	private static Logger logger = LogManager.getLogger(PessoaDAO.class);

	public PessoaHabilitadaDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	public static PessoaHabilitadaDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new PessoaHabilitadaDAO(banco);
		return instance;
	}

	public void setPessoaHabilitada(int siape) throws SQLExceptionQManager {
		
		PreparedStatement stmt = null;
		
		boolean isHabilitado = true;

		try {

			String sql = "UPDATE tb_pessoa_habilitada"
					+ " SET fl_habilitada = ?"
					+ " WHERE nr_siape = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setBoolean(1, isHabilitado);
			stmt.setInt(2, siape);
			
			stmt.execute();

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}
	}
	
	public Servidor getServidorByMatricula(Integer siape)
			throws SQLExceptionQManager {

		Servidor servidor = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			logger.info("Recuperando servidor pelo siape: " + siape);
			
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
								+ " pessoahabilitada.id_cargo_servidor,"
								+ " cargoservidor.nm_cargo_servidor,"
								+ " pessoahabilitada.id_campus_institucional,"
								+ " campus.nm_campus_institucional,"
								+ " pessoahabilitada.fl_habilitada"
								+ " FROM tb_pessoa_habilitada AS pessoahabilitada"
								+ " INNER JOIN tb_titulacao titulacao"
								+ " ON pessoahabilitada.id_titulacao = titulacao.id_titulacao"
								+ " INNER JOIN tb_departamento departamento"
								+ " ON pessoahabilitada.id_departamento = departamento.id_departamento"
								+ " INNER JOIN tb_cargo_servidor cargoservidor"
								+ " ON pessoahabilitada.id_cargo_servidor = cargoservidor.id_cargo_servidor"
								+ " INNER JOIN tb_campus_institucional campus"
								+ " ON pessoahabilitada.id_campus_institucional = campus.id_campus_institucional"
								+ " WHERE pessoahabilitada.nr_siape =",
							siape);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Servidor> servidores = convertToList(rs);

			if (!servidores.isEmpty()) {
				servidor = servidores.get(0);
			}

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return servidor;
	}

	@Override
	public List<Servidor> find(Servidor servidor) throws SQLExceptionQManager {

		List<Servidor> servidores;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s",
							"SELECT pessoahabilitada.id_pessoa_habilitada,"
								+ " pessoahabilitada.nm_pessoa_habilitada,"
								+ " pessoahabilitada.nr_siape,"
								+ " pessoahabilitada.nm_email,"
								+ " pessoahabilitada.id_titulacao,"
								+ " titulacao.nm_titulacao,"
								+ " pessoahabilitada.id_departamento,"
								+ " departamento.nm_departamento,"
								+ " pessoahabilitada.id_cargo_servidor,"
								+ " cargoservidor.nm_cargo_servidor,"
								+ " pessoahabilitada.id_campus_institucional,"
								+ " campus.nm_campus_institucional,"
								+ " pessoahabilitada.fl_habilitada"
								+ " FROM tb_pessoa_habilitada AS pessoahabilitada"
								+ " INNER JOIN tb_titulacao titulacao"
								+ " ON pessoahabilitada.id_titulacao = titulacao.id_titulacao"
								+ " INNER JOIN tb_departamento departamento"
								+ " ON pessoahabilitada.id_departamento = departamento.id_departamento"
								+ " INNER JOIN tb_cargo_servidor cargoservidor"
								+ " ON pessoahabilitada.id_cargo_servidor = cargoservidor.id_cargo_servidor"
								+ " INNER JOIN tb_campus_institucional campus"
								+ " ON pessoahabilitada.id_campus_institucional = campus.id_campus_institucional"
								+ " WHERE pessoahabilitada.nm_pessoa_habilitada"
								+ " LIKE '%" + servidor.getNomePessoa() + "%'"
								+ " AND pessoahabilitada.fl_habilitada IS FALSE");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			servidores = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return servidores;
	}

	@Override
	public List<Servidor> convertToList(ResultSet rs)
			throws SQLExceptionQManager {

		List<Servidor> servidores = new LinkedList<Servidor>();

		try {

			while (rs.next()) {

				Servidor servidor = new Servidor();
				Titulacao titulacao = new Titulacao();

				// Pessoa
				servidor.setPessoaId(rs.getInt(
						"pessoahabilitada.id_pessoa_habilitada"));
				servidor.setNomePessoa(StringUtil.upperCaseNomeCompleto(rs.getString(
						"pessoahabilitada.nm_pessoa_habilitada")));
				servidor.setMatricula(Integer.toString(rs.getInt(
						"pessoahabilitada.nr_siape")));
				servidor.setEmail(rs.getString("pessoahabilitada.nm_email"));

				// Titulação
				titulacao.setIdTitulacao(rs
						.getInt("pessoahabilitada.id_titulacao"));
				titulacao.setNome(rs.getString("titulacao.nm_titulacao"));
				servidor.setTitulacao(titulacao);

				// Campus
				Campus campus = new Campus();
				campus.setIdCampusInstitucional(rs.getInt(
						"pessoahabilitada.id_campus_institucional"));
				campus.setNome(rs.getString("campus.nm_campus_institucional"));
				servidor.setCampus(campus);

				// Departamento
				Departamento departamento = new Departamento();
				departamento.setIdDepartamento(rs.getInt(
						"pessoahabilitada.id_departamento"));
				departamento.setNome(rs.getString(
						"departamento.nm_departamento"));
				servidor.setDepartamento(departamento);

				// Cargo
				CargoServidor cargoServidor = new CargoServidor();
				cargoServidor.setIdCargoServidor(rs.getInt(
						"pessoahabilitada.id_cargo_servidor"));
				cargoServidor.setNomeCargoServidor(rs.getString(
						"cargoservidor.nm_cargo_servidor"));
				servidor.setCargoServidor(cargoServidor);
				
				servidor.setHabilitada(rs.getBoolean(
						"pessoahabilitada.fl_habilitada"));

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
	public int delete(Integer pk) throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return BancoUtil.NOROWSUPDATED;
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
}
