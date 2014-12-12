package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;
import br.edu.ifpb.qmanager.util.PalavraUtil;

public class DiscenteDAO implements GenericDAO<Integer, Discente> {

	static DBPool banco;
	private static DiscenteDAO instance;

	public static DiscenteDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new DiscenteDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public DiscenteDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Discente discente) throws QManagerSQLException {

		TipoPessoa tipoPessoa = new TipoPessoa();
		tipoPessoa.setIdTipoPessoa(3);
		discente.setTipoPessoa(tipoPessoa);

		// inserir Pessoa
		int idPessoa = PessoaDAO.getInstance().insert(discente);

		try {
			String sql = String.format("%s %s ('%s', '%s')",
					"INSERT INTO tb_discente (pessoa_id, turma_id)", "VALUES",
					idPessoa, discente.getTurma().getIdTurma());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return idPessoa;

	}

	@Override
	public void update(Discente discente) throws QManagerSQLException {

		PessoaDAO.getInstance().update(discente);

		try {

			String sql = "UPDATE tb_discente SET turma_id=?"
					+ " WHERE pessoa_id=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, discente.getTurma().getIdTurma());
			stmt.setInt(2, discente.getPessoaId());

			stmt.execute();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public void delete(Integer id) throws QManagerSQLException {

		try {

			String sql = "DELETE FROM tb_discente WHERE pessoa_id=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();

			PessoaDAO.getInstance().delete(id);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}
	}

	@Override
	public List<Discente> getAll() throws QManagerSQLException {
		List<Discente> discentes;

		try {

			String sql = String
					.format("%s",
							"SELECT pessoa.id_pessoa, pessoa.nm_pessoa, pessoa.nr_cpf, "
									+ "pessoa.nr_matricula, pessoa.nm_endereco, pessoa.nm_cep, "
									+ "pessoa.nm_telefone, pessoa.nm_email, pessoa.tipo_pessoa_id, "
									+ "pessoa.dt_registro, discente.turma_id "
									+ "FROM tb_discente discente "
									+ "INNER JOIN tb_pessoa pessoa ON "
									+ "discente.pessoa_id = pessoa.id_pessoa");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			discentes = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return discentes;
	}

	@Override
	public Discente getById(Integer id) throws QManagerSQLException {

		Discente discente = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT pessoa.id_pessoa, pessoa.nm_pessoa, pessoa.nr_cpf, "
									+ "pessoa.nr_matricula, pessoa.nm_endereco, pessoa.nm_cep, "
									+ "pessoa.nm_telefone, pessoa.nm_email, pessoa.tipo_pessoa_id, "
									+ "pessoa.dt_registro, discente.turma_id "
									+ "FROM tb_discente discente "
									+ "INNER JOIN tb_pessoa pessoa ON "
									+ "discente.pessoa_id = pessoa.id_pessoa "
									+ "WHERE discente.pessoa_id=", id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Discente> discentes = convertToList(rs);

			if (!discentes.isEmpty())
				discente = discentes.get(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return discente;
	}

	public List<Discente> getByProjeto(Projeto projeto)
			throws QManagerSQLException {
		List<Discente> discentes;

		try {

			String sql = String
					.format("%s %d %s",
							"SELECT pessoa.id_pessoa, pessoa.nm_pessoa, pessoa.nr_cpf, "
									+ "pessoa.nr_matricula, pessoa.nm_endereco, pessoa.nm_cep, "
									+ "pessoa.nm_telefone, pessoa.nm_email, pessoa.tipo_pessoa_id, "
									+ "pessoa.dt_registro, discente.turma_id "
									+ "FROM tb_discente discente, tb_participacao participacao, "
									+ "tb_pessoa pessoa "
									+ "WHERE participacao.pessoa_id = discente.pessoa_id"
									+ "  AND participacao.pessoa_id = participacao.id_pessoa"
									+ "  AND participacao.projeto_id =",
							projeto.getIdProjeto(),
							"GROUP BY participacao.pessoa_id");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			discentes = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return discentes;
	}

	public List<Discente> getByPalavra(PalavraUtil palavraUtil)
			throws QManagerSQLException {
		List<Discente> discentes;

		try {

			String sql = String
					.format("%s '%%%s%%'",
							"SELECT pessoa.id_pessoa, pessoa.nm_pessoa, pessoa.nr_cpf, "
									+ "pessoa.nr_matricula, pessoa.nm_endereco, pessoa.nm_cep, "
									+ "pessoa.nm_telefone, pessoa.nm_email, pessoa.tipo_pessoa_id, "
									+ "pessoa.dt_registro, discente.turma_id "
									+ "FROM tb_discente discente "
									+ "INNER JOIN tb_pessoa pessoa "
									+ "ON discente.pessoa_id = pessoa.id_pessoa "
									+ "WHERE pessoa.nm_pessoa LIKE ",
							palavraUtil.getPalavra());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			discentes = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return discentes;
	}

	@Override
	public List<Discente> convertToList(ResultSet rs)
			throws QManagerSQLException {

		List<Discente> discentes = new LinkedList<Discente>();

		try {

			while (rs.next()) {
				Discente discente = new Discente();
				Turma turma = new Turma();
				DadosBancarios dadosBancarios = new DadosBancarios();
				TipoPessoa tipoPessoa = new TipoPessoa();
				// tabela pessoa
				discente.setPessoaId(rs.getInt("pessoa.id_pessoa"));
				discente.setNomePessoa(rs.getString("pessoa.nm_pessoa"));
				discente.setCpf(rs.getString("pessoa.nr_cpf"));
				discente.setMatricula(rs.getString("pessoa.nr_matricula"));
				discente.setEndereco(rs.getString("pessoa.nm_endereco"));
				discente.setCep(rs.getString("pessoa.nm_cep"));
				discente.setTelefone(rs.getString("pessoa.nm_telefone"));
				discente.setEmail(rs.getString("pessoa.nm_email"));
				discente.setRegistro(rs.getDate("pessoa.dt_registro"));
				dadosBancarios = DadosBancariosDAO.getInstance()
						.getByIdDadosBancarios(rs.getInt("pessoa.id_pessoa"));
				discente.setDadosBancarios(dadosBancarios);
				tipoPessoa = TipoPessoaDAO.getInstance().getById(
						rs.getInt("pessoa.tipo_pessoa_id"));
				discente.setTipoPessoa(tipoPessoa);

				// tabela discente
				turma = TurmaDAO.getInstance().getById(
						rs.getInt("discente.turma_id"));
				discente.setTurma(turma);

				discentes.add(discente);

			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return discentes;
	}

}