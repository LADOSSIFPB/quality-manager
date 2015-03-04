package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.entidade.Titulacao;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class ServidorDAO implements GenericDAO<Integer, Servidor> {

	static DBPool banco;
	
	private static ServidorDAO instance;

	public static ServidorDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new ServidorDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public ServidorDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Servidor servidor) throws SQLExceptionQManager {

		TipoPessoa tipoPessoa = new TipoPessoa();
		tipoPessoa.setIdTipoPessoa(TipoPessoa.TIPO_SERVIDOR);
		servidor.setTipoPessoa(tipoPessoa);

		int idPessoa = PessoaDAO.getInstance().insert(servidor);

		PreparedStatement stmt = null;
		
		try {

			String sql = String.format("%s %s (%d, '%s', %d)",
					"INSERT INTO tb_servidor ("
					+ " pessoa_id,"
					+ " id_titulacao,"
					+ " cargo_servidor_id)",
					" VALUES",
					idPessoa,
					servidor.getTitulacao().getIdTitulacao(), 
					servidor.getCargoServidor().getIdCargoServidor());

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {
			banco.closeQuery(stmt);
		}

		return idPessoa;
	}

	@Override
	public void update(Servidor servidor) throws SQLExceptionQManager {

		PessoaDAO.getInstance().update(servidor);
		
		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_servidor"
					+ " SET id_titulacao = ?,"
					+ " nm_local_trabalho = ?,"
					+ " cargo_servidor_id = ?"
					+ " WHERE pessoa_id = ?";

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, servidor.getTitulacao().getIdTitulacao());
			stmt.setInt(2, servidor.getCargoServidor().getIdCargoServidor());
			stmt.setInt(3, servidor.getPessoaId());

			stmt.execute();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {
			banco.closeQuery(stmt);
		}
	}

	@Override
	public void delete(Integer id) throws SQLExceptionQManager {

		PreparedStatement stmt = null;
		
		try {

			String sql = "DELETE FROM tb_servidor WHERE pessoa_id=?";

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();

			PessoaDAO.getInstance().delete(id);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {
			banco.closeQuery(stmt);
		}
	}

	@Override
	public List<Servidor> getAll() throws SQLExceptionQManager {
		
		List<Servidor> servidores = null;
		
		PreparedStatement stmt = null;
		
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s",
							"SELECT pessoa.id_pessoa,"
							+ " pessoa.nm_pessoa,"
							+ " pessoa.nr_cpf,"
							+ " pessoa.nr_matricula,"
							+ " pessoa.nm_endereco,"
							+ " pessoa.nm_telefone,"
							+ " pessoa.nm_cep,"
							+ " pessoa.nm_email,"
							+ " pessoa.dt_registro, "
							+ " pessoa.tipo_pessoa_id,"
							+ " pessoa.local_id, "
							+ " servidor.id_titulacao,"
							+ " titulacao.nm_titulacao,"
							+ " servidor.cargo_servidor_id"
							+ " FROM tb_servidor servidor"
							+ " INNER JOIN tb_pessoa pessoa"
								+ " ON servidor.pessoa_id = pessoa.id_pessoa"
							+ " INNER JOIN tb_titulacao titulacao"
								+ " ON servidor.id_titulacao = titulacao.id_titulacao");

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			servidores = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {
			banco.closeQuery(stmt, rs);
		}

		return servidores;
	}

	@Override
	public Servidor getById(Integer id) throws SQLExceptionQManager {

		Servidor servidor = null;

		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {

			String sql = String
					.format("%s %d",
							"SELECT pessoa.id_pessoa,"
							+ " pessoa.nm_pessoa,"
							+ " pessoa.nr_cpf,"
							+ " pessoa.nr_matricula,"
							+ " pessoa.nm_endereco,"
							+ " pessoa.nm_telefone,"
							+ " pessoa.nm_cep,"
							+ " pessoa.nm_email,"
							+ " pessoa.dt_registro,"
							+ " pessoa.tipo_pessoa_id,"
							+ " pessoa.local_id,"
							+ " servidor.id_titulacao,"
							+ " titulacao.nm_titulacao,"
							+ " servidor.cargo_servidor_id"
							+ " FROM tb_servidor servidor"
							+ " INNER JOIN tb_pessoa pessoa"
								+ " ON servidor.pessoa_id = pessoa.id_pessoa"
							+ " INNER JOIN tb_titulacao titulacao"
								+ " ON servidor.id_titulacao = titulacao.id_titulacao"
							+ " WHERE pessoa.id_pessoa=",
							id);

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Servidor> servidores = convertToList(rs);

			if (!servidores.isEmpty())
				servidor = servidores.get(0);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {
			banco.closeQuery(stmt, rs);
		}

		return servidor;
	}

	public List<Servidor> getByProjeto(Projeto projeto)
			throws SQLExceptionQManager {

		List<Servidor> servidores = null;

		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {

			String sql = String
					.format("%s %d",
							"SELECT pessoa.id_pessoa,"
							+ " pessoa.nm_pessoa,"
							+ " pessoa.nr_cpf,"
							+ " pessoa.nr_matricula,"
							+ " pessoa.nm_endereco,"
							+ " pessoa.nm_telefone,"
							+ " pessoa.nm_cep,"
							+ " pessoa.nm_email,"
							+ " pessoa.dt_registro,"
							+ " pessoa.tipo_pessoa_id,"
							+ " pessoa.local_id,"
							+ " servidor.id_titulacao,"
							+ " titulacao.nm_titulacao,"
							+ " servidor.cargo_servidor_id"
							+ " FROM tb_servidor servidor"
							+ " INNER JOIN tb_pessoa pessoa"
								+ " ON pessoa.id_pessoa = servidor.pessoa_id"
							+ " INNER JOIN tb_participacao participacao"
								+ " ON participacao.pessoa_id = pessoa.id_pessoa"
							+ " INNER JOIN tb_titulacao titulacao"
								+ " ON servidor.id_titulacao = titulacao.id_titulacao"
							+ " WHERE participacao.projeto_id =",
							projeto.getIdProjeto());

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			servidores = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {
			banco.closeQuery(stmt, rs);
		}

		return servidores;
	}

	public List<Servidor> getServidoresPesquisa() throws SQLExceptionQManager {

		List<Servidor> servidores = null;

		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {

			String sql = String
					.format("%s %d %s",
							"SELECT pessoa.id_pessoa,"
							+ " pessoa.nm_pessoa,"
							+ " pessoa.nr_cpf,"
							+ "pessoa.nr_matricula,"
							+ " pessoa.nm_endereco,"
							+ " pessoa.nm_telefone,"
							+ " pessoa.nm_cep,"
							+ " pessoa.nm_email,"
							+ " pessoa.dt_registro,"
							+ " pessoa.tipo_pessoa_id,"
							+ " pessoa.local_id,"
							+ " servidor.id_titulacao,"
							+ " titulacao.nm_titulacao,"
							+ " servidor.cargo_servidor_id"
							+ " FROM tb_servidor as servidor"
							+ " INNER JOIN tb_pessoa pessoa"
								+ " ON pessoa.id_pessoa = servidor.pessoa_id"
							+ " INNER JOIN tb_participacao participacao"
								+ " ON participacao.pessoa_id = pessoa.id_pessoa"
							+ " INNER JOIN tb_projeto projeto"
								+ " ON projeto.id_projeto = participacao.projeto_id"
							+ " INNER JOIN tb_titulacao titulacao"
								+ " ON servidor.id_titulacao = titulacao.id_titulacao"
							+ " WHERE projeto.tp_projeto = 'P'");

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			servidores = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {
			banco.closeQuery(stmt, rs);
		}

		return servidores;
	}

	public List<Servidor> getServidoresExtensao() throws SQLExceptionQManager {

		List<Servidor> servidores = null;

		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {

			String sql = String
					.format("%s",
							"SELECT pessoa.id_pessoa,"
							+ " pessoa.nm_pessoa,"
							+ " pessoa.nr_cpf,"
							+ " pessoa.nr_matricula,"
							+ " pessoa.nm_endereco,"
							+ " pessoa.nm_telefone,"
							+ " pessoa.nm_cep,"
							+ " pessoa.nm_email,"
							+ " pessoa.dt_registro,"
							+ " pessoa.tipo_pessoa_id,"
							+ " pessoa.local_id,"
							+ " servidor.id_titulacao,"
							+ " titulacao.nm_titulacao,"
							+ " servidor.cargo_servidor_id"
							+ " FROM tb_servidor as servidor"
							+ " INNER JOIN tb_pessoa pessoa"
								+ " ON pessoa.id_pessoa = servidor.pessoa_id"
							+ " INNER JOIN tb_participacao participacao"
								+ " ON participacao.pessoa_id = pessoa.id_pessoa"
							+ " INNER JOIN tb_projeto projeto"
								+ " ON projeto.id_projeto = participacao.projeto_id"
							+ " INNER JOIN tb_titulacao titulacao"
								+ " ON servidor.id_titulacao = titulacao.id_titulacao"
							+ " WHERE projeto.tp_projeto = 'E'");

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			servidores = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {
			banco.closeQuery(stmt, rs);
		}

		return servidores;
	}

	public List<Servidor> getServidoresPesquisa(int ano)
			throws SQLExceptionQManager {

		List<Servidor> servidores = null;

		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {

			String sql = String
					.format("%s %d %s %d %s",
							"SELECT pessoa.id_pessoa,"
							+ " pessoa.nm_pessoa,"
							+ " pessoa.nr_cpf,"
							+ " pessoa.nr_matricula,"
							+ " pessoa.nm_endereco,"
							+ " pessoa.nm_telefone,"
							+ " pessoa.nm_cep,"
							+ " pessoa.nm_email,"
							+ " pessoa.dt_registro,"
							+ " pessoa.tipo_pessoa_id,"
							+ " pessoa.local_id,"
							+ " servidor.id_titulacao,"
							+ " titulacao.nm_titulacao,"
							+ " servidor.cargo_servidor_id"
							+ " FROM tb_servidor as servidor"
							+ " INNER JOIN tb_pessoa pessoa"
								+ " ON pessoa.id_pessoa = servidor.pessoa_id"
							+ " INNER JOIN tb_participacao participacao"
								+ " ON participacao.pessoa_id = pessoa.id_pessoa"
							+ " INNER JOIN tb_projeto projeto"
								+ " ON projeto.id_projeto = participacao.projeto_id"
							+ " INNER JOIN tb_titulacao titulacao"
								+ " ON servidor.id_titulacao = titulacao.id_titulacao"
							+ " WHERE projeto.tp_projeto = 'P' "
							+ " AND (YEAR (participacao.dt_inicio) =",
							ano,
							" OR YEAR (participacao.dt_inicio) = ",
							ano,
							")");

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			servidores = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {
			banco.closeQuery(stmt, rs);
		}

		return servidores;
	}

	public List<Servidor> getServidoresExtensao(int ano)
			throws SQLExceptionQManager {

		List<Servidor> servidores = null;
		
		PreparedStatement stmt = null;
		
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d %s %d %s",
							"SELECT pessoa.id_pessoa,"
							+ " pessoa.nm_pessoa,"
							+ " pessoa.nr_cpf,"
							+ " pessoa.nr_matricula,"
							+ " pessoa.nm_endereco,"
							+ " pessoa.nm_telefone,"
							+ " pessoa.nm_cep,"
							+ " pessoa.nm_email,"
							+ " pessoa.dt_registro,"
							+ " pessoa.tipo_pessoa_id,"
							+ " pessoa.local_id,"
							+ " servidor.id_titulacao,"
							+ " titulacao.nm_titulacao,"
							+ " servidor.cargo_servidor_id"
							+ " FROM tb_servidor as servidor"
							+ " INNER JOIN tb_pessoa pessoa"
								+ " ON pessoa.id_pessoa = servidor.pessoa_id"
							+ " INNER JOIN tb_participacao participacao"
								+ " ON participacao.pessoa_id = pessoa.id_pessoa"
							+ " INNER JOIN tb_projeto projeto"
								+ " ON projeto.id_projeto = participacao.projeto_id"
							+ " INNER JOIN tb_titulacao titulacao"
								+ " ON servidor.id_titulacao = titulacao.id_titulacao"
							+ " WHERE projeto.tp_projeto = 'E'"
							+ " AND (YEAR (participacao.dt_inicio) = ",
							ano,
							" OR YEAR (participacao.dt_inicio) = ",
							ano,
							")");

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			servidores = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {
			banco.closeQuery(stmt, rs);
		}

		return servidores;
	}

	public List<Servidor> getAllCoordenadores() throws SQLExceptionQManager {

		List<Servidor> coordenadores = null;

		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {

			String sql = String
					.format("%s %d",
							"SELECT pessoa.id_pessoa,"
							+ " pessoa.nm_pessoa,"
							+ " pessoa.nr_cpf,"
							+ " pessoa.nr_matricula,"
							+ " pessoa.nm_endereco,"
							+ " pessoa.nm_telefone,"
							+ " pessoa.nm_cep,"
							+ " pessoa.nm_email,"
							+ " pessoa.dt_registro,"
							+ " pessoa.id_pessoa,"
							+ " pessoa.tipo_pessoa_id,"
							+ " pessoa.local_id,"
							+ " servidor.id_titulacao,"
							+ " titulacao.nm_titulacao,"
							+ " servidor.cargo_servidor_id"
							+ " FROM tb_pessoa AS pessoa"
							+ " INNER JOIN tb_servidor servidor"
								+ " ON servidor.pessoa_id = pessoa.id_pessoa"
							+ " INNER JOIN tb_titulacao titulacao"
								+ " ON servidor.id_titulacao = titulacao.id_titulacao"
							+ " WHERE servidor.cargo_servidor_id = ",
							CargoServidor.COORDENADOR);

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			coordenadores = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {
			banco.closeQuery(stmt, rs);
		}

		return coordenadores;
	}

	public Servidor getCoordenadorById(int id) throws SQLExceptionQManager {

		Servidor coordenador = null;

		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {

			String sql = String
					.format("%s %d %s %d",
							"SELECT pessoa.id_pessoa,"
							+ " pessoa.nm_pessoa,"
							+ " pessoa.nr_cpf,"
							+ " pessoa.nr_matricula,"
							+ " pessoa.nm_endereco,"
							+ " pessoa.nm_telefone,"
							+ " pessoa.nm_cep,"
							+ " pessoa.nm_email,"
							+ " pessoa.dt_registro,"
							+ " pessoa.id_pessoa,"
							+ " pessoa.tipo_pessoa_id,"
							+ " pessoa.local_id,"
							+ " servidor.id_titulacao,"
							+ " titulacao.nm_titulacao,"
							+ " servidor.cargo_servidor_id"
							+ " FROM tb_pessoa as pessoa"
							+ " INNER JOIN tb_servidor servidor"
								+ " ON servidor.pessoa_id = pessoa.id_pessoa"
							+ " INNER JOIN tb_titulacao titulacao"
								+ " ON servidor.id_titulacao = titulacao.id_titulacao"
							+ " WHERE servidor.cargo_servidor_id = ",
							CargoServidor.COORDENADOR,
							" AND servidor.pessoa_id =",
							id);

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Servidor> coordenadores = convertToList(rs);
			if (!coordenadores.isEmpty()) {
				coordenador = coordenadores.get(0);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {
			banco.closeQuery(stmt, rs);
		}

		return coordenador;
	}

	public List<Servidor> getAllGestores() throws SQLExceptionQManager {

		List<Servidor> gestores = null;

		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		
		try {

			String sql = String
					.format("%s %d",
							"SELECT pessoa.id_pessoa,"
							+ " pessoa.nm_pessoa,"
							+ " pessoa.nr_cpf,"
							+ " pessoa.nr_matricula,"
							+ " pessoa.nm_endereco,"
							+ " pessoa.nm_telefone,"
							+ " pessoa.nm_cep,"
							+ " pessoa.nm_email,"
							+ " pessoa.dt_registro,"
							+ " pessoa.id_pessoa,"
							+ " pessoa.tipo_pessoa_id,"
							+ " pessoa.local_id,"
							+ " servidor.id_titulacao,"
							+ " titulacao.nm_titulacao,"
							+ " servidor.cargo_servidor_id"
							+ " FROM tb_pessoa as pessoa"
							+ " INNER JOIN tb_servidor servidor"
								+ " ON servidor.pessoa_id = pessoa.id_pessoa"
							+ " INNER JOIN tb_titulacao titulacao"
								+ " ON servidor.id_titulacao = titulacao.id_titulacao"
							+ " WHERE servidor.cargo_servidor_id = ",
							CargoServidor.GESTOR);

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			gestores = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {
			banco.closeQuery(stmt, rs);
		}

		return gestores;
	}

	public Servidor getGestorById(int id) throws SQLExceptionQManager {

		Servidor gestor = null;
		
		PreparedStatement stmt = null;
		
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d %s %d",
							"SELECT pessoa.id_pessoa,"
							+ " pessoa.nm_pessoa,"
							+ " pessoa.nr_cpf,"
							+ " pessoa.nr_matricula,"
							+ " pessoa.nm_endereco,"
							+ " pessoa.nm_telefone,"
							+ " pessoa.nm_cep,"
							+ " pessoa.nm_email,"
							+ " pessoa.dt_registro,"
							+ " pessoa.id_pessoa,"
							+ " pessoa.tipo_pessoa_id,"
							+ " pessoa.local_id,"
							+ " servidor.id_titulacao,"
							+ " titulacao.nm_titulacao,"
							+ " servidor.cargo_servidor_id"
							+ " FROM tb_pessoa as pessoa"
							+ " INNER JOIN tb_servidor servidor"
								+ " ON servidor.pessoa_id = pessoa.id_pessoa"
							+ " INNER JOIN tb_titulacao titulacao"
								+ " ON servidor.id_titulacao = titulacao.id_titulacao"
							+ " WHERE servidor.cargo_servidor_id = ",
							CargoServidor.GESTOR, 
							" AND servidor.pessoa_id = ",
							id);

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Servidor> gestores = convertToList(rs);
			if (!gestores.isEmpty()) {
				gestor = gestores.get(0);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {
			banco.closeQuery(stmt, rs);
		}

		return gestor;
	}

	@Override
	public List<Servidor> find(Servidor servidor) throws SQLExceptionQManager {

		List<Servidor> servidores = null;
		
		PreparedStatement stmt = null;
		
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s '%%%s%%'",
							"SELECT pessoa.id_pessoa,"
							+ " pessoa.nm_pessoa,"
							+ " pessoa.nr_cpf,"
							+ " pessoa.nr_matricula,"
							+ " pessoa.nm_endereco,"
							+ " pessoa.nm_telefone,"
							+ " pessoa.nm_cep,"
							+ " pessoa.nm_email,"
							+ " pessoa.dt_registro,"
							+ " pessoa.id_pessoa,"
							+ " pessoa.tipo_pessoa_id,"
							+ " pessoa.local_id,"
							+ " servidor.id_titulacao,"
							+ " titulacao.nm_titulacao,"
							+ " servidor.cargo_servidor_id"
							+ " FROM tb_servidor as servidor"
							+ " INNER JOIN tb_pessoa pessoa"
								+ " ON servidor.pessoa_id = pessoa.id_pessoa"
							+ " INNER JOIN tb_titulacao titulacao"
								+ " ON servidor.id_titulacao = titulacao.id_titulacao"
							+ " WHERE pessoa.nm_pessoa LIKE ",
							servidor.getNomePessoa());

			stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			servidores = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {
			banco.closeQuery(stmt, rs);
		}

		return servidores;
	}

	@Override
	public List<Servidor> convertToList(ResultSet rs)
			throws SQLExceptionQManager {

		List<Servidor> servidores = new ArrayList<Servidor>();

		try {

			while (rs.next()) {

				Servidor servidor = new Servidor();			
				
				// tabela pessoa
				servidor.setPessoaId(rs.getInt("pessoa.id_pessoa"));
				servidor.setNomePessoa(rs.getString("pessoa.nm_pessoa"));
				servidor.setCpf(rs.getString("pessoa.nr_cpf"));
				servidor.setMatricula(rs.getString("pessoa.nr_matricula"));
				servidor.setEndereco(rs.getString("pessoa.nm_endereco"));
				servidor.setCep(rs.getString("pessoa.nm_cep"));
				servidor.setTelefone(rs.getString("pessoa.nm_telefone"));
				servidor.setEmail(rs.getString("pessoa.nm_email"));
				
				// Dados bancários
				DadosBancarios dadosBancarios = new DadosBancarios();
				dadosBancarios = DadosBancariosDAO.getInstance()
						.getByIdDadosBancarios(rs.getInt("pessoa.id_pessoa"));
				servidor.setDadosBancarios(dadosBancarios);
				
				// TipoPessoa
				TipoPessoa tipoPessoa = new TipoPessoa();
				tipoPessoa = TipoPessoaDAO.getInstance().getById(
						rs.getInt("pessoa.tipo_pessoa_id"));
				servidor.setTipoPessoa(tipoPessoa);
				
				// Campus
				Campus campus = CampusDAO.getInstance().getById(
						rs.getInt("pessoa.local_id"));
				servidor.setCampus(campus);

				// Titulação
				Titulacao titulacao = new Titulacao();
				titulacao.setIdTitulacao(rs.getInt("servidor.id_titulacao"));
				titulacao.setNome(rs.getString("titulacao.nm_titulacao"));
				servidor.setTitulacao(titulacao);
				
				// Cargo
				CargoServidor cargoServidor = new CargoServidor();
				cargoServidor = CargoServidorDAO.getInstance().getById(
						rs.getInt("servidor.cargo_servidor_id"));
				servidor.setCargoServidor(cargoServidor);
				servidor.setRegistro(rs.getDate("pessoa.dt_registro"));

				servidores.add(servidor);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return servidores;
	}

}