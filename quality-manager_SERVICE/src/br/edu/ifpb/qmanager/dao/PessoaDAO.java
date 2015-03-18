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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.util.StringUtil;

/**
 * Superclasse de fatoração para as entidades Servidor e Discente.
 * 
 * @author Rhavy
 *
 */
public class PessoaDAO implements GenericDAO<Integer, Pessoa> {

	private static DBPool banco;

	private static PessoaDAO instance;
	
	public Connection connection;

	private static Logger logger = LogManager.getLogger(PessoaDAO.class);

	public static PessoaDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new PessoaDAO(banco);
		return instance;
	}

	public PessoaDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Pessoa pessoa) throws SQLExceptionQManager {

		int idPessoa = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {

			String sql = String
					.format("%s %s ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d, %d)",
							"INSERT INTO tb_pessoa (" 
									+ " nm_pessoa,"
									+ " nr_cpf," 
									+ " nr_matricula,"
									+ " nm_endereco," 
									+ " nm_cep,"
									+ " nm_telefone," 
									+ " nm_email,"
									+ " nm_senha," 	
									+ " tipo_pessoa_id,"
									+ " local_id)", 
									" VALUES",
									pessoa.getNomePessoa(), 
									pessoa.getCpf(), 
									pessoa.getMatricula(), 
									pessoa.getEndereco(), 
									pessoa.getCep(), 
									pessoa.getTelefone(),
									pessoa.getEmail(), 
									StringUtil.criptografar(pessoa.getSenha()),
									pessoa.getTipoPessoa().getIdTipoPessoa(),
									pessoa.getCampus().getIdCampusInstitucional());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idPessoa = BancoUtil.getGenerateKey(stmt);

			pessoa.setPessoaId(idPessoa);

			DadosBancariosDAO.getInstance().insert(pessoa);

		} catch (SQLException sqleException) {
			
			throw new SQLExceptionQManager(sqleException.getErrorCode(),
					sqleException.getLocalizedMessage());
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException criptException) {
			
			logger.error("Problema ao criptografar os dados do usuário.");
			
		} finally {

			banco.close(stmt, this.connection);
		}

		return idPessoa;
	}

	@Override
	public void update(Pessoa pessoa) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_pessoa SET nm_pessoa = ?,"
					+ " nr_cpf = ?, nr_matricula = ?, nm_endereco = ?,"
					+ " nm_cep = ?, nm_telefone = ?, nm_email = ?, nm_senha = ?,"
					+ " tipo_pessoa_id = ? local_id = ?"
					+ " WHERE id_pessoa = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, pessoa.getNomePessoa());
			stmt.setString(2, pessoa.getCpf());
			stmt.setString(3, pessoa.getMatricula());
			stmt.setString(4, pessoa.getEndereco());
			stmt.setString(5, pessoa.getCep());
			stmt.setString(6, pessoa.getTelefone());
			stmt.setString(7, pessoa.getEmail());
			stmt.setString(8, StringUtil.criptografar(pessoa.getSenha()));
			stmt.setInt(9, pessoa.getTipoPessoa().getIdTipoPessoa());
			stmt.setInt(10, pessoa.getCampus().getIdCampusInstitucional());
			stmt.setInt(11, pessoa.getPessoaId());

			stmt.execute();

			DadosBancariosDAO.getInstance().update(pessoa);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException criptException) {
			
			logger.error("Problema ao criptografar os dados do usuário.");
			
		} finally {

			banco.close(stmt, this.connection);
		}
	}

	@Override
	public void delete(Integer id) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			DadosBancariosDAO.getInstance().delete(id);

			String sql = "DELETE FROM tb_pessoa WHERE id_pessoa=?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}
	}

	@Override
	public List<Pessoa> getAll() throws SQLExceptionQManager {
		return null;
	}

	@Override
	public Pessoa getById(Integer id) throws SQLExceptionQManager {

		Pessoa pessoa = null;

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
									+ " pessoa.nm_cep,"
									+ " pessoa.nm_telefone,"
									+ " pessoa.nm_email,"
									+ " tipo_pessoa.id_tipo_pessoa,"
									+ " tipo_pessoa.nm_tipo_pessoa,"
									+ " pessoa.local_id"
									+ " FROM tb_pessoa pessoa INNER JOIN tb_tipo_pessoa tipo_pessoa"
									+ " ON pessoa.tipo_pessoa_id = tipo_pessoa.id_tipo_pessoa"
									+ " WHERE pessoa.id_pessoa = ", id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Pessoa> pessoas = convertToList(rs);

			if (!pessoas.isEmpty())
				pessoa = pessoas.get(0);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return pessoa;

	}

	/**
	 * Retornar o usuário do Login.
	 * 
	 * @param login
	 * @return usuario
	 * @throws SQLExceptionQManager
	 */
	public Pessoa getByLogin(Login login) throws SQLExceptionQManager {

		Pessoa pessoa = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s '%s' %s '%s'",
							"SELECT pessoa.id_pessoa,"
									+ " tipo_pessoa.id_tipo_pessoa,"
									+ " pessoa.nm_senha"
									+ " FROM tb_pessoa pessoa INNER JOIN tb_tipo_pessoa tipo_pessoa "
									+ " ON pessoa.tipo_pessoa_id = tipo_pessoa.id_tipo_pessoa "
									+ " WHERE pessoa.nr_matricula =",
							login.getIdentificador(), "OR pessoa.nm_email =",
							login.getIdentificador());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				String senhaBanco = rs.getString("pessoa.nm_senha");
				String senhaLogin = StringUtil.criptografar(login.getSenha());

				if (senhaLogin.equals(senhaBanco)) {

					pessoa = new Pessoa();
					TipoPessoa tipoPessoa = new TipoPessoa();
					tipoPessoa.setIdTipoPessoa(rs
							.getInt("tipo_pessoa.id_tipo_pessoa"));
					pessoa.setTipoPessoa(tipoPessoa);
					pessoa.setPessoaId(rs.getInt("pessoa.id_pessoa"));

				} else {
					banco.close(stmt, rs, this.connection);
					throw new SQLExceptionQManager(101, "Senha inválida!");
				}
			}

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

			logger.error("Problema ao criptografar os dados do usuário.");

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return pessoa;
	}

	public List<Pessoa> find(Pessoa pessoa) throws SQLExceptionQManager {

		List<Pessoa> pessoas = null;

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
									+ " pessoa.nm_cep,"
									+ " pessoa.nm_telefone,"
									+ " pessoa.nm_email,"
									+ " pessoa.local_id,"
									+ " tipo_pessoa.id_tipo_pessoa,"
									+ " tipo_pessoa.nm_tipo_pessoa,"
									+ " pessoa.local_id"
									+ " FROM tb_pessoa pessoa INNER JOIN tb_tipo_pessoa tipo_pessoa"
									+ " ON pessoa.tipo_pessoa_id = tipo_pessoa.id_tipo_pessoa"
									+ " WHERE pessoa.nm_pessoa LIKE",
							pessoa.getNomePessoa());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			pessoas = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return pessoas;

	}

	@Override
	public List<Pessoa> convertToList(ResultSet rs) throws SQLExceptionQManager {

		List<Pessoa> pessoas = new LinkedList<Pessoa>();

		try {

			while (rs.next()) {

				// Pessoa
				Pessoa pessoa = new Pessoa();

				// TipoPessoa
				TipoPessoa tipoPessoa = new TipoPessoa();
				tipoPessoa.setIdTipoPessoa(rs.getInt(
						"tipo_pessoa.id_tipo_pessoa"));
				tipoPessoa.setNomeTipoPessoa(rs.getString(
						"tipo_pessoa.nm_tipo_pessoa"));
				pessoa.setTipoPessoa(tipoPessoa);

				pessoa.setPessoaId(rs.getInt("pessoa.id_pessoa"));
				pessoa.setNomePessoa(rs.getString("pessoa.nm_pessoa"));
				pessoa.setCpf(rs.getString("pessoa.nr_cpf"));
				pessoa.setMatricula(rs.getString("pessoa.nr_matricula"));
				pessoa.setCep(rs.getString("pessoa.nm_cep"));
				pessoa.setEndereco(rs.getString("pessoa.nm_endereco"));
				pessoa.setTelefone(rs.getString("pessoa.nm_telefone"));
				pessoa.setEmail(rs.getString("pessoa.nm_email"));

				// Campus
				Campus campus = CampusDAO.getInstance().getById(
						rs.getInt("pessoa.local_id"));
				pessoa.setCampus(campus);

				pessoas.add(pessoa);
			}

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return pessoas;
	}
}
