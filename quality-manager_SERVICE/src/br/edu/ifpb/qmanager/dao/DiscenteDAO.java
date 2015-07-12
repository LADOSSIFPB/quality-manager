package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class DiscenteDAO implements GenericDAO<Integer, Discente> {

	static DBPool banco;
	
	private static DiscenteDAO instance;
	
	public Connection connection;
	
	public static DiscenteDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new DiscenteDAO(banco);
		return instance;
	}	

	public DiscenteDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Discente discente) throws SQLExceptionQManager {

		TipoPessoa tipoPessoa = new TipoPessoa();
		tipoPessoa.setIdTipoPessoa(3);
		discente.setTipoPessoa(tipoPessoa);

		int idPessoa = PessoaDAO.getInstance().insert(discente);

		PreparedStatement stmt = null;

		try {
			String sql = String.format("%s %s ('%s', '%s')",
					"INSERT INTO tb_discente (pessoa_id, turma_id)", "VALUES",
					idPessoa, discente.getTurma().getIdTurma());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}

		return idPessoa;
	}

	@Override
	public void update(Discente discente) throws SQLExceptionQManager {

		PessoaDAO.getInstance().update(discente);

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_discente" + " SET turma_id = ?"
					+ " WHERE pessoa_id = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, discente.getTurma().getIdTurma());
			stmt.setInt(2, discente.getPessoaId());

			stmt.execute();

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, this.connection);
		}

	}

	@Override
	public void delete(Integer id) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "DELETE FROM tb_discente" + " WHERE pessoa_id = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();

			PessoaDAO.getInstance().delete(id);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, this.connection);
		}
	}

	@Override
	public List<Discente> getAll() throws SQLExceptionQManager {

		List<Discente> discentes;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s", "SELECT pessoa.id_pessoa,"
					+ " pessoa.nm_pessoa," 
					+ " pessoa.nr_cpf,"
					+ " pessoa.nr_matricula," 
					+ " pessoa.nm_endereco,"
					+ " pessoa.nm_cep," 
					+ " pessoa.nm_telefone,"
					+ " pessoa.nm_email," 
					+ " pessoa.tipo_pessoa_id,"
					+ " pessoa.local_id," 
					+ " pessoa.dt_registro,"
					+ " discente.turma_id" 
					+ " FROM tb_discente discente"
					+ " INNER JOIN tb_pessoa pessoa"
					+ " ON discente.pessoa_id = pessoa.id_pessoa");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			discentes = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return discentes;
	}

	@Override
	public Discente getById(Integer id) throws SQLExceptionQManager {

		Discente discente = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d", "SELECT pessoa.id_pessoa,"
					+ " pessoa.nm_pessoa," 
					+ " pessoa.nr_cpf,"
					+ " pessoa.nr_matricula," 
					+ " pessoa.nm_endereco,"
					+ " pessoa.nm_cep," 
					+ " pessoa.nm_telefone,"
					+ " pessoa.nm_email," 
					+ " pessoa.tipo_pessoa_id, "
					+ " pessoa.local_id," 
					+ " pessoa.dt_registro,"
					+ " discente.turma_id" 
					+ " FROM tb_discente discente"
					+ " INNER JOIN tb_pessoa pessoa ON"
					+ " discente.pessoa_id = pessoa.id_pessoa"
					+ " WHERE discente.pessoa_id=", id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Discente> discentes = convertToList(rs);

			if (!discentes.isEmpty())
				discente = discentes.get(0);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return discente;

	}

	public List<Discente> getByProjeto(Projeto projeto)
			throws SQLExceptionQManager {
		List<Discente> discentes;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d %s",
							"SELECT pessoa.id_pessoa,"
									+ " pessoa.nm_pessoa,"
									+ " pessoa.nr_cpf,"
									+ " pessoa.nr_matricula,"
									+ " pessoa.nm_endereco,"
									+ " pessoa.nm_cep,"
									+ " pessoa.nm_telefone,"
									+ " pessoa.nm_email,"
									+ " pessoa.tipo_pessoa_id,"
									+ " pessoa.local_id,"
									+ " pessoa.dt_registro,"
									+ " discente.turma_id"
									+ " FROM tb_discente discente, tb_participacao participacao,"
									+ " tb_pessoa pessoa "
									+ " WHERE participacao.pessoa_id = discente.pessoa_id"
									+ " AND participacao.pessoa_id = participacao.id_pessoa"
									+ " AND participacao.projeto_id = ",
							projeto.getIdProjeto(),
							"GROUP BY participacao.pessoa_id");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			discentes = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return discentes;
	}

	@Override
	public List<Discente> find(Discente discente) throws SQLExceptionQManager {
		List<Discente> discentes;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s '%%%s%%'",
					"SELECT pessoa.id_pessoa," + " pessoa.nm_pessoa,"
							+ " pessoa.nr_cpf," + " pessoa.nr_matricula,"
							+ " pessoa.nm_endereco," + " pessoa.nm_cep,"
							+ " pessoa.nm_telefone," + " pessoa.nm_email,"
							+ " pessoa.tipo_pessoa_id," + " pessoa.local_id,"
							+ " pessoa.dt_registro," + " discente.turma_id"
							+ " FROM tb_discente discente"
							+ " INNER JOIN tb_pessoa pessoa"
							+ " ON discente.pessoa_id = pessoa.id_pessoa"
							+ " WHERE pessoa.nm_pessoa LIKE",
					discente.getNomePessoa());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			discentes = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return discentes;
	}

	@Override
	public List<Discente> convertToList(ResultSet rs)
			throws SQLExceptionQManager {

		List<Discente> discentes = new LinkedList<Discente>();

		try {

			while (rs.next()) {
				Discente discente = new Discente();

				// Pessoa
				discente.setPessoaId(rs.getInt("pessoa.id_pessoa"));
				discente.setNomePessoa(rs.getString("pessoa.nm_pessoa"));
				discente.setCpf(rs.getString("pessoa.nr_cpf"));
				discente.setMatricula(rs.getString("pessoa.nr_matricula"));
				discente.setEndereco(rs.getString("pessoa.nm_endereco"));
				discente.setCep(rs.getString("pessoa.nm_cep"));
				discente.setTelefone(rs.getString("pessoa.nm_telefone"));
				discente.setEmail(rs.getString("pessoa.nm_email"));
				discente.setRegistro(rs.getDate("pessoa.dt_registro"));

				// Dados bancários
				DadosBancarios dadosBancarios = new DadosBancarios();
				dadosBancarios = DadosBancariosDAO.getInstance()
						.getByIdDadosBancarios(rs.getInt("pessoa.id_pessoa"));
				discente.setDadosBancarios(dadosBancarios);

				// TipoPessoa
				TipoPessoa tipoPessoa = new TipoPessoa();
				tipoPessoa = TipoPessoaDAO.getInstance().getById(
						rs.getInt("pessoa.tipo_pessoa_id"));
				discente.setTipoPessoa(tipoPessoa);

				// Campus
				Campus campus = CampusDAO.getInstance().getById(
						rs.getInt("pessoa.local_id"));
				discente.setCampus(campus);

				// Discente
				Turma turma = new Turma();
				turma = TurmaDAO.getInstance().getById(
						rs.getInt("discente.turma_id"));
				discente.setTurma(turma);

				discentes.add(discente);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return discentes;
	}
}