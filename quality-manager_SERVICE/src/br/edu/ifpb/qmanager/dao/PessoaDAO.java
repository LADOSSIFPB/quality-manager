package br.edu.ifpb.qmanager.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;
import br.edu.ifpb.qmanager.util.PalavraUtil;
import br.edu.ifpb.qmanager.util.StringUtil;

/* serve de fatoração comum de código para Gestor, Coordenador, Servidor, Discente */
public class PessoaDAO implements GenericDAO<Integer, Pessoa> {

	static DBPool banco;
	private static PessoaDAO instance;

	public static PessoaDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new PessoaDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public PessoaDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Pessoa pessoa) throws QManagerSQLException {

		int chave = 0;

		try {

			String sql = String
					.format("%s %s ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d)",
							"INSERT INTO tb_pessoa (nm_pessoa, nr_cpf, nr_matricula,"
									+ " nm_endereco, nm_cep, nm_telefone, nm_email,"
									+ " nm_senha, tipo_pessoa_id)", "VALUES",
							pessoa.getNomePessoa(), pessoa.getCpf(), pessoa
									.getMatricula(), pessoa.getEndereco(),
							pessoa.getCep(), pessoa.getTelefone(), pessoa
									.getEmail(), StringUtil.criptografar(pessoa
									.getSenha()), pessoa.getTipoPessoa()
									.getIdTipoPessoa());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			chave = BancoUtil.getGenerateKey(stmt);

			pessoa.setPessoaId(chave);

			DadosBancariosDAO.getInstance().insert(pessoa);

			stmt.close();

		} catch (SQLException sqleException) {
			throw new QManagerSQLException(sqleException.getErrorCode(),
					sqleException.getLocalizedMessage());
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException criptException) {
			criptException.printStackTrace();
		}

		return chave;

	}

	@Override
	public void update(Pessoa pessoa) throws QManagerSQLException {

		try {

			String sql = "UPDATE tb_pessoa SET nm_pessoa = ?,"
					+ " nr_cpf = ?, nr_matricula = ?, nm_endereco = ?,"
					+ " nm_cep = ?, nm_telefone = ?, nm_email = ?, nm_senha = ?,"
					+ " tipo_pessoa_id = ?" + " WHERE id_pessoa = ?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1, pessoa.getNomePessoa());
			stmt.setString(2, pessoa.getCpf());
			stmt.setString(3, pessoa.getMatricula());
			stmt.setString(4, pessoa.getEndereco());
			stmt.setString(5, pessoa.getCep());
			stmt.setString(6, pessoa.getTelefone());
			stmt.setString(7, pessoa.getEmail());
			stmt.setString(8, StringUtil.criptografar(pessoa.getSenha()));
			stmt.setInt(9, pessoa.getTipoPessoa().getIdTipoPessoa());
			stmt.setInt(10, pessoa.getPessoaId());

			stmt.execute();
			stmt.close();

			DadosBancariosDAO.getInstance().update(pessoa);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException criptException) {
			criptException.printStackTrace();
		}

	}

	@Override
	public void delete(Integer id) throws QManagerSQLException {

		try {

			DadosBancariosDAO.getInstance().delete(id);

			String sql = "DELETE FROM tb_pessoa WHERE id_pessoa=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public List<Pessoa> getAll() throws QManagerSQLException {
		return null;
	}

	@Override
	public Pessoa getById(Integer id) throws QManagerSQLException {
		return null;
	}

	/**
	 * Retornar o usuário do Login.
	 * 
	 * @param login
	 * @return usuario
	 * @throws QManagerSQLException
	 */
	public Pessoa getByLogin(Login login) throws QManagerSQLException {

		Pessoa pessoa = null;

		String sql = String
				.format("%s '%s' %s '%s'",
						"SELECT pessoa.id_pessoa, pessoa.nm_pessoa, pessoa.nr_cpf, pessoa.nr_matricula, "
								+ "pessoa.nm_endereco, pessoa.nm_cep, pessoa.nm_telefone, "
								+ "pessoa.nm_email, tipo_pessoa.id_tipo_pessoa, pessoa.nm_senha "
								+ "FROM tb_pessoa pessoa INNER JOIN tb_tipo_pessoa tipo_pessoa "
								+ "ON pessoa.tipo_pessoa_id = tipo_pessoa.id_tipo_pessoa "
								+ "WHERE pessoa.nr_matricula =",
						login.getIdentificador(), "OR pessoa.nm_email =",
						login.getIdentificador());

		try {

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			// recuperar o Usuário do banco
			while (rs.next()) {

				String senhaBanco = rs.getString("pessoa.nm_senha");
				String senhaCriptografada = StringUtil.criptografar(login
						.getSenha());
				if (senhaCriptografada.equals(senhaBanco)) {
					List<Pessoa> pessoas = convertToList(rs);
					if (!pessoas.isEmpty())
						pessoa = pessoas.get(0);
				} else {
					throw new QManagerSQLException(101, "Senha inválida!");
				}
			}

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Tratar.
		}

		if (pessoa == null)
			throw new QManagerSQLException(100, "Usuário não existe no sistema");

		return pessoa;
	}

	public List<Pessoa> getByPalavra(PalavraUtil palavraUtil)
			throws QManagerSQLException {

		List<Pessoa> pessoas = null;

		try {

			String sql = String
					.format("%s '%%%s%%'",
							"SELECT pessoa.id_pessoa, pessoa.nm_pessoa, pessoa.nr_cpf, pessoa.nr_matricula, "
									+ "pessoa.nm_endereco, pessoa.nm_cep, pessoa.nm_telefone, "
									+ "pessoa.nm_email, tipo_pessoa.id_tipo_pessoa, pessoa.nm_senha "
									+ "FROM tb_pessoa pessoa INNER JOIN tb_tipo_pessoa tipo_pessoa "
									+ "ON pessoa.tipo_pessoa_id = tipo_pessoa.id_tipo_pessoa "
									+ "WHERE pessoa.nm_pessoa LIKE",
							palavraUtil.getPalavra());

			PreparedStatement stmt;
			stmt = (PreparedStatement) connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			pessoas = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return pessoas;

	}

	@Override
	public List<Pessoa> convertToList(ResultSet rs) throws QManagerSQLException {

		List<Pessoa> pessoas = new LinkedList<Pessoa>();

		try {

			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				int idTipoPessoa = rs.getInt("tipo_pessoa.id_tipo_pessoa");
				TipoPessoa tipoPessoa = new TipoPessoa();
				tipoPessoa.setIdTipoPessoa(idTipoPessoa);
				pessoa.setTipoPessoa(tipoPessoa);
				pessoa = new Pessoa();
				int idPessoa = rs.getInt("pessoa.id_pessoa");
				pessoa.setPessoaId(idPessoa);
				pessoa.setNomePessoa(rs.getString("pessoa.nm_pessoa"));
				pessoa.setCpf(rs.getString("pessoa.nr_cpf"));
				pessoa.setMatricula(rs.getString("pessoa.nr_matricula"));
				pessoa.setCep(rs.getString("pessoa.nm_cep"));
				pessoa.setEndereco(rs.getString("pessoa.nm_endereco"));
				pessoa.setTelefone(rs.getString("pessoa.nm_telefone"));

				pessoas.add(pessoa);
			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return pessoas;

	}
}
