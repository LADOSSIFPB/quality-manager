package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Coordenador;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.entidade.Usuario;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/* serve de fatoração comum de código para Discente e Docente */
public class PessoaDAO implements GenericDAO<Integer, Pessoa> {

	// a conexão com o banco de dados
	public DatabaseConnection banco;
	public Connection connection;
	private DadosBancariosDAO dadosBancariosDAO;

	public PessoaDAO(DatabaseConnection banco) {
		this.banco = banco;
		this.connection = (Connection) banco.getConnection();
		this.dadosBancariosDAO = new DadosBancariosDAO(banco);
	}

	@Override
	public int insert(Pessoa pessoa) throws QManagerSQLException {

		int chave = 0;

		try {

			String sql = String
					.format("%s %s ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d)",
							"INSERT INTO `tb_pessoa` (`nm_pessoa`, `nr_cpf`, `nr_matricula`, `nm_endereco`, `nm_cep`, `nm_telefone`, `nm_email`, `nm_senha`, `tipo_pessoa_id`)",
							"VALUES", pessoa.getNomePessoa(), pessoa.getCpf(),
							pessoa.getMatricula(), pessoa.getEndereco(), pessoa
									.getCep(), pessoa.getTelefone(), pessoa
									.getEmail(), pessoa.getSenha(), pessoa
									.getTipoPessoa().getIdTipoPessoa());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			chave = BancoUtil.getGenerateKey(stmt);

			pessoa.setPessoaId(chave);

			this.dadosBancariosDAO.insert(pessoa);

			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return chave;

	}

	@Override
	public void update(Pessoa pessoa) throws QManagerSQLException {

		try {

			String sql = "UPDATE `tb_pessoa` SET `nm_pessoa`=?, `nr_cpf`=?, `nr_matricula`=?, `nm_endereco`=?, `nm_cep`=?, `nm_telefone`=?, `nm_email`=?"
					+ " WHERE `id_pessoa`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1, pessoa.getNomePessoa());
			stmt.setString(2, pessoa.getCpf());
			stmt.setString(3, pessoa.getMatricula());
			stmt.setString(4, pessoa.getEndereco());
			stmt.setString(5, pessoa.getCep());
			stmt.setString(6, pessoa.getTelefone());
			stmt.setString(7, pessoa.getEmail());
			stmt.setInt(8, pessoa.getPessoaId());

			stmt.execute();
			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public void delete(Integer id) throws QManagerSQLException {

		try {

			dadosBancariosDAO.delete(id);

			String sql = "DELETE FROM `tb_pessoa` WHERE `id_pessoa`=?";

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
	public Usuario getByLogin(Login login) throws QManagerSQLException {

		Usuario usuario = null;

		String sql = String
				.format("%s %s (%s '%s' %s '%s')",
						"SELECT P.id_pessoa, TP.id_tipo_pessoa, P.nm_senha FROM tb_pessoa P "
								+ "INNER JOIN `tb_tipo_pessoa` TP ON P.`tipo_pessoa_id` = TP.`id_tipo_pessoa`",
						"WHERE", "P.nr_matricula =", login.getIdentificador(),
						"OR P.nm_email =", login.getIdentificador());

		try {

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			// recuperar o Usuário do banco
			while (rs.next()) {

				usuario = new Usuario();
				int tipoPessoa = rs.getInt("TP.id_tipo_pessoa");
				String senha = rs.getString(rs.getInt("P.nm_senha"));
				int id = rs.getInt("P.id_pessoa");

				if (login.getSenha().equals(senha)) {

					if (tipoPessoa == TipoPessoa.TIPO_ORIENTADOR) {
						OrientadorDAO orientadorDAO = new OrientadorDAO(banco);
						Orientador orientador = orientadorDAO.getById(id);
						usuario.setUsuario(orientador);
					} else if (tipoPessoa == TipoPessoa.TIPO_DISCENTE) {
						DiscenteDAO discenteDAO = new DiscenteDAO(banco);
						Discente discente = discenteDAO.getById(id);
						usuario.setUsuario(discente);
					} else {
						CoordenadorDAO coordenadorDAO = new CoordenadorDAO(
								banco);
						Coordenador coordenador = coordenadorDAO.getById(id);
						usuario.setUsuario(coordenador);
					}
				} else {
					throw new QManagerSQLException(101, "Senha inválida!");
				}
			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		if (usuario == null)
			throw new QManagerSQLException(100, "Usuário não existe no sistema");

		return usuario;
	}

	@Override
	public List<Pessoa> convertToList(ResultSet rs) throws QManagerSQLException {
		return null;
	}

}
