package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DiscenteDAO implements GenericDAO<Integer, Discente> {

	// a conexão com o banco de dados
	public DatabaseConnection banco;
	public Connection connection;

	private PessoaDAO pessoaDAO;

	public DiscenteDAO(DatabaseConnection banco) {
		this.banco = banco;
		this.connection = (Connection) banco.getConnection();
		pessoaDAO = new PessoaDAO(banco);
	}

	@Override
	public int insert(Discente discente) throws QManagerSQLException {

		TipoPessoa tipoPessoa = new TipoPessoa();
		tipoPessoa.setIdTipoPessoa(3);
		discente.setTipoPessoa(tipoPessoa);

		// inserir Pessoa
		int idPessoa = pessoaDAO.insert(discente);

		try {
			String sql = String.format("%s %s ('%s', '%s')",
					"INSERT INTO `tb_discente` (`pessoa_id`, `turma_id`)",
					"VALUES", idPessoa, discente.getTurma().getIdTurma());

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

		pessoaDAO.update(discente);

		try {

			String sql = "UPDATE `tb_discente` SET `turma_id`=?"
					+ " WHERE `pessoa_id`=?";

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

			String sql = "DELETE FROM `tb_discente` WHERE `pessoa_id`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();

			pessoaDAO.delete(id);

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
							"SELECT * FROM `tb_discente` D"
									+ " INNER JOIN `tb_pessoa` P ON D.`pessoa_id` = P.`id_pessoa`");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			discentes = convertToList(rs);

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
							"SELECT * FROM `tb_discente` D"
									+ " INNER JOIN `tb_pessoa` P ON D.`pessoa_id` = P.`id_pessoa`"
									+ " WHERE D.`pessoa_id`=", id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Discente> discentes = convertToList(rs);

			discente = discentes.get(0);

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
							"SELECT P.id_pessoa, P.nm_pessoa, P.nr_cpf, P.nr_matricula, P.nm_endereco, P.nm_cep, P.nm_telefone, P.nm_email, P.nm_senha, D.turma_id, P.tipo_pessoa_id, P.dt_registro"
									+ " FROM `tb_discente` D, `tb_participacao` PA, `tb_pessoa` P"
									+ " WHERE PA.`pessoa_id` = D.`pessoa_id`"
									+ " AND PA.`pessoa_id` = P.`id_pessoa`"
									+ " AND PA.`projeto_id` =",
							projeto.getIdProjeto(), "GROUP BY PA.`pessoa_id`");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			discentes = convertToList(rs);

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
		DadosBancariosDAO dadosBancariosDAO = new DadosBancariosDAO(banco);
		TurmaDAO turmaDAO = new TurmaDAO(banco);
		TipoPessoaDAO tipoPessoaDAO = new TipoPessoaDAO(banco);

		try {

			while (rs.next()) {
				Discente discente = new Discente();
				Turma turma = new Turma();
				DadosBancarios dadosBancarios = new DadosBancarios();
				TipoPessoa tipoPessoa = new TipoPessoa();
				// tabela pessoa
				discente.setPessoaId(rs.getInt("P.id_pessoa"));
				discente.setNomePessoa(rs.getString("P.nm_pessoa"));
				discente.setCpf(rs.getString("P.nr_cpf"));
				discente.setMatricula(rs.getString("P.nr_matricula"));
				discente.setEndereco(rs.getString("P.nm_endereco"));
				discente.setCep(rs.getString("P.nm_cep"));
				discente.setTelefone(rs.getString("P.nm_telefone"));
				discente.setEmail(rs.getString("P.nm_email"));
				discente.setSenha(rs.getString("P.nm_senha"));
				discente.setRegistro(rs.getDate("P.dt_registro"));
				dadosBancarios = dadosBancariosDAO.getByIdDadosBancarios(rs
						.getInt("P.id_pessoa"));
				discente.setDadosBancarios(dadosBancarios);
				tipoPessoa = tipoPessoaDAO.getById(rs
						.getInt("P.tipo_pessoa_id"));
				discente.setTipoPessoa(tipoPessoa);

				// tabela discente
				turma = turmaDAO.getById(rs.getInt("D.turma_id"));
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