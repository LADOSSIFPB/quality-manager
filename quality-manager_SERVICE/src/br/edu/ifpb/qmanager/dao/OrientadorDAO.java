package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class OrientadorDAO implements GenericDAO<Integer, Orientador> {

	// a conexão com o banco de dados
	public Connection connection;
	private DatabaseConnection banco;

	private PessoaDAO pessoaDAO;

	public OrientadorDAO(DatabaseConnection banco) {
		this.connection = (Connection) banco.getConnection();
		pessoaDAO = new PessoaDAO(banco);
		this.banco = banco;
	}

	@Override
	public int insert(Orientador docente) throws QManagerSQLException {

		TipoPessoa tipoPessoa = new TipoPessoa();
		tipoPessoa.setIdTipoPessoa(2);
		docente.setTipoPessoa(tipoPessoa);

		int idPessoa = pessoaDAO.insert(docente);

		try {

			String sql = String
					.format("%s %s ('%s', '%s', '%s', '%s')",
							"INSERT INTO `tb_orientador` (`pessoa_id`, `nm_titulacao`, `nm_cargo`, `nm_local_trabalho`)",
							"VALUES", idPessoa, docente.getTitulacao(),
							docente.getCargo(), docente.getLocalTrabalho());

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
	public void update(Orientador orientador) throws QManagerSQLException {

		pessoaDAO.update(orientador);

		try {

			String sql = "UPDATE `tb_docente` SET `nm_titulacao`=?, `nm_cargo`=?, `nm_local_trabalho`=?"
					+ " WHERE `pessoa_id`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1, orientador.getTitulacao());
			stmt.setString(2, orientador.getCargo());
			stmt.setString(3, orientador.getLocalTrabalho());
			stmt.setInt(4, orientador.getPessoaId());

			stmt.execute();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public void delete(Integer id) throws QManagerSQLException {

		try {

			String sql = "DELETE FROM `tb_docente` WHERE `pessoa_id`=?";

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
	public List<Orientador> getAll() throws QManagerSQLException {
		List<Orientador> orientadores;

		try {

			String sql = String
					.format("%s",
							"SELECT * FROM `tb_orientador` O"
									+ " INNER JOIN `tb_pessoa` P ON O.`pessoa_id` = P.`id_pessoa`");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			orientadores = convertToList(rs);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return orientadores;
	}

	@Override
	public Orientador getById(Integer id) throws QManagerSQLException {

		Orientador docente = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT * FROM `tb_orientador` O"
									+ " INNER JOIN `tb_pessoa` P ON O.`pessoa_id` = P.`id_pessoa`"
									+ " WHERE O.`pessoa_id`=", id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Orientador> docentes = convertToList(rs);

			if (!docentes.isEmpty())
				docente = docentes.get(0);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return docente;

	}

	public List<Orientador> getByProjeto(Projeto projeto)
			throws QManagerSQLException {
		List<Orientador> orientador;

		try {

			String sql = String
					.format("%s %d %s",
							"SELECT P.id_pessoa, P.nm_pessoa, P.nr_cpf, P.nr_matricula, P.nm_endereco, P.nm_cep, P.nm_telefone, P.nm_email, P.nm_senha, "
									+ "O.nm_titulacao, O.nm_cargo, O.nm_local_trabalho, P.tipo_pessoa_id, P.dt_registro "
									+ "FROM `tb_orientador` O, `tb_participacao` PA, `tb_pessoa` P"
									+ " WHERE PA.`pessoa_id` = O.`pessoa_id`"
									+ " AND PA.`pessoa_id` = P.`id_pessoa`"
									+ " AND PA.`projeto_id` =",
							projeto.getIdProjeto(), "GROUP BY PA.`pessoa_id`");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			orientador = convertToList(rs);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return orientador;
	}

	@Override
	public List<Orientador> convertToList(ResultSet rs)
			throws QManagerSQLException {

		List<Orientador> orientadores = new LinkedList<Orientador>();
		DadosBancariosDAO dadosBancariosDAO = new DadosBancariosDAO(banco);
		TipoPessoaDAO tipoPessoaDAO = new TipoPessoaDAO(banco);

		try {

			while (rs.next()) {
				Orientador orientador = new Orientador();
				DadosBancarios dadosBancarios = new DadosBancarios();
				TipoPessoa tipoPessoa = new TipoPessoa();
				// tabela pessoa
				orientador.setPessoaId(rs.getInt("P.id_pessoa"));
				orientador.setNomePessoa(rs.getString("P.nm_pessoa"));
				orientador.setCpf(rs.getString("P.nr_cpf"));
				orientador.setMatricula(rs.getString("P.nr_matricula"));
				orientador.setEndereco(rs.getString("P.nm_endereco"));
				orientador.setCep(rs.getString("P.nm_cep"));
				orientador.setTelefone(rs.getString("P.nm_telefone"));
				orientador.setEmail(rs.getString("P.nm_email"));
				orientador.setSenha(rs.getString("P.nm_senha"));
				dadosBancarios = dadosBancariosDAO.getByIdDadosBancarios(rs
						.getInt("P.id_pessoa"));
				orientador.setDadosBancarios(dadosBancarios);
				tipoPessoa = tipoPessoaDAO.getById(rs
						.getInt("P.tipo_pessoa_id"));
				orientador.setTipoPessoa(tipoPessoa);

				// docente
				orientador.setTitulacao(rs.getString("O.nm_titulacao"));
				orientador.setCargo(rs.getString("O.nm_cargo"));
				orientador
						.setLocalTrabalho(rs.getString("O.nm_local_trabalho"));
				orientador.setRegistro(rs.getDate("P.dt_registro"));

				orientadores.add(orientador);

			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return orientadores;

	}
}