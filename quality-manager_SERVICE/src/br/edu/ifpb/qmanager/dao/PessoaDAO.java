package br.edu.ifpb.qmanager.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.DadosBancarios;
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
					.format("%s %s ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d, %d)",
							"INSERT INTO tb_pessoa (" 
									+ " nm_pessoa,"
									+ " nr_cpf," 
									+ " nr_matricula,"
									+ " nm_endereco," 
									+ " nm_cep,"
									+ " nm_telefone," 
									+ " nm_email,"
									+ " nm_url_lattes,"
									+ " nm_senha," 	
									+ " tipo_pessoa_id,"
									+ " campus_institucional_id)",
									" VALUES",
									pessoa.getNomePessoa(),
									pessoa.getCpf(),
									pessoa.getMatricula(),
									pessoa.getEndereco() != null ? pessoa.getEndereco() : "",
									pessoa.getCep() != null ? pessoa.getCep() : "",
									pessoa.getTelefone() != null ? pessoa.getTelefone() : "",
									pessoa.getEmail(),
									pessoa.getUrlLattes(),
									StringUtil.criptografarSha256(pessoa.getSenha()),
									pessoa.getTipoPessoa().getIdTipoPessoa(),
									pessoa.getCampus().getIdCampusInstitucional());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			// Cadastra e recuperar identificação da Pessoa.
			idPessoa = BancoUtil.getGenerateKey(stmt);

			pessoa.setPessoaId(idPessoa);

			DadosBancarios dadosBancarios = pessoa.getDadosBancarios();
			
			// Caso a Pessoa não possua Conta bancária não cadastrar.
			if (dadosBancarios != null) {				
				DadosBancariosDAO.getInstance().insert(pessoa);
			}
		} catch (SQLException sqleException) {
			
			throw new SQLExceptionQManager(sqleException.getErrorCode(),
					sqleException.getLocalizedMessage());
			
		} catch (NoSuchAlgorithmException 
				| UnsupportedEncodingException criptException) {
			
			//TODO: Lançar exceção da criptografia.
			logger.error("Problema ao criptografar os dados do usuário.");
			
		} finally {

			banco.close(stmt, this.connection);
		}

		return idPessoa;
	}

	public int insertAuthorizationKey(Pessoa pessoa) throws SQLExceptionQManager {

		int idAuthorizationKey = BancoUtil.IDVAZIO;
		
		long timeMilis = Calendar.getInstance().getTimeInMillis();
		
		PreparedStatement stmt = null;

		try {

			String sql = String
					.format("%s %s (%d, '%s')",
							"INSERT INTO tb_authorization_key (" 
									+ " pessoa_id,"
									+ " nm_key)",
									" VALUES",
									pessoa.getPessoaId(),
									StringUtil.criptografarBase64(
											String.valueOf(timeMilis)));

			
			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			// Cadastra e recuperar identificação.
			idAuthorizationKey = BancoUtil.getGenerateKey(stmt);
			
		} catch (SQLException sqleException) {
			
			throw new SQLExceptionQManager(sqleException.getErrorCode(),
					sqleException.getLocalizedMessage());
			
		} catch (UnsupportedEncodingException criptException) {
			
			//TODO: Lançar exceção da criptografia.
			logger.error("Problema ao criptografar os dados do usuário.");
			
		} finally {

			banco.close(stmt, this.connection);
		}

		return idAuthorizationKey;
	}
	
	@Override
	public void update(Pessoa pessoa) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_pessoa"
					+ " SET nm_pessoa = ?,"
					+ " nr_cpf = ?,"
					+ " nr_matricula = ?,"
					+ " nm_endereco = ?,"
					+ " nm_cep = ?,"
					+ " nm_telefone = ?,"
					+ " nm_email = ?,"
					+ " nm_senha = ?,"
					+ " tipo_pessoa_id = ?,"
					+ " campus_institucional_id = ?"
					+ " WHERE id_pessoa = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, pessoa.getNomePessoa());
			stmt.setString(2, pessoa.getCpf());
			stmt.setString(3, pessoa.getMatricula());
			stmt.setString(4, pessoa.getEndereco());
			stmt.setString(5, pessoa.getCep());
			stmt.setString(6, pessoa.getTelefone());
			stmt.setString(7, pessoa.getEmail());
			stmt.setString(8, StringUtil.criptografarSha256(pessoa.getSenha()));
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
	public int delete(Integer id) throws SQLExceptionQManager {

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
		
		return BancoUtil.NOROWSUPDATED;
	}

	@Override
	public List<Pessoa> getAll() throws SQLExceptionQManager {
		return null;
	}

	/**
	 * Retornar se o usuário tem permissão de Login.
	 * 
	 * @param login
	 * @return isAuthorized
	 * @throws SQLExceptionQManager
	 */
	public int getIsAuthorized(Login login) throws SQLExceptionQManager {

		int idPessoa = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sql = String
					.format("%s '%s' %s '%s'",
							"SELECT pessoa.id_pessoa,"
									+ " pessoa.nm_senha"
									+ " FROM tb_pessoa pessoa"
									+ " WHERE pessoa.nr_matricula =",
							login.getIdentificador(), 
							"OR pessoa.nm_email =",
							login.getIdentificador());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				//TODO: a senha deve vir criptografada do Cliente
				String senhaBanco = rs.getString("pessoa.nm_senha");
				String senhaLogin = StringUtil.criptografarSha256(login.getSenha());

				if (senhaLogin.equals(senhaBanco)) {
					
					idPessoa = rs.getInt("pessoa.id_pessoa");
					
				} else {

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
		
		return idPessoa;
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
									+ " pessoa.campus_institucional_id"
									+ " FROM tb_pessoa pessoa INNER JOIN tb_tipo_pessoa tipo_pessoa"
									+ " ON pessoa.tipo_pessoa_id = tipo_pessoa.id_tipo_pessoa"
									+ " WHERE pessoa.id_pessoa = ", 
									id);

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
							login.getIdentificador(),
							"OR pessoa.nm_email =",
							login.getIdentificador());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				//TODO: a senha deve vir criptografada do Cliente
				String senhaBanco = rs.getString("pessoa.nm_senha");
				String senhaLogin = StringUtil.criptografarSha256(login.getSenha());

				if (senhaLogin.equals(senhaBanco)) {

					pessoa = new Pessoa();
					TipoPessoa tipoPessoa = new TipoPessoa();
					tipoPessoa.setIdTipoPessoa(rs
							.getInt("tipo_pessoa.id_tipo_pessoa"));
					pessoa.setTipoPessoa(tipoPessoa);
					pessoa.setPessoaId(rs.getInt("pessoa.id_pessoa"));

				} else {
					
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
		
		if (pessoa == null) // NÃO TIRAR ESSA VERIFICAÇÃO
			throw new SQLExceptionQManager(100, "Email inválido!");
		
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
									+ " pessoa.campus_institucional_id,"
									+ " tipo_pessoa.id_tipo_pessoa,"
									+ " tipo_pessoa.nm_tipo_pessoa"
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
	
	public boolean isCPFCadastrado(String cpf) throws SQLExceptionQManager {
		
		boolean isCPFCadastrado = false;

		PreparedStatement stmt = null;
		
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s '%s'",
							"SELECT count(pessoa.id_pessoa) AS quant_pessoas "
								+ " FROM tb_pessoa pessoa"
								+ " WHERE pessoa.nr_cpf =",
							cpf);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);
			
			int rowCount = rs.last() ? rs.getInt("quant_pessoas") : 0; 
			
			isCPFCadastrado = (rowCount != 0);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}
		
		return isCPFCadastrado;
	}
	
	public String getAuthorizationKeyById(int idAuthorizationKey) 
			throws SQLExceptionQManager {
		
		String authorizationKey = StringUtil.STRING_VAZIO;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT autorizacao.nm_key"
									+ " FROM tb_authorization_key autorizacao"
									+ " WHERE autorizacao.id_authorization_key =",
							idAuthorizationKey);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				authorizationKey = rs.getString("autorizacao.nm_key");				
			}

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}
		
		return authorizationKey;
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
						rs.getInt("pessoa.campus_institucional_id"));
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
