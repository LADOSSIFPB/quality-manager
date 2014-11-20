package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.Gestor;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

public class GestorDAO implements GenericDAO<Integer, Gestor> {

	static DBPool banco;
	private static GestorDAO instance;

	public static GestorDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new GestorDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public GestorDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Gestor gestor) throws QManagerSQLException {

		TipoPessoa tipoPessoa = new TipoPessoa();
		tipoPessoa.setIdTipoPessoa(4);
		gestor.setTipoPessoa(tipoPessoa);

		int idPessoa = PessoaDAO.getInstance().insert(gestor);

		return idPessoa;

	}

	@Override
	public void update(Gestor gestor) throws QManagerSQLException {

		PessoaDAO.getInstance().update(gestor);

	}

	@Override
	public void delete(Integer id) throws QManagerSQLException {

		PessoaDAO.getInstance().delete(id);

	}

	@Override
	public List<Gestor> getAll() throws QManagerSQLException {

		List<Gestor> gestor;

		try {

			String sql = String
					.format("%s %d",
							"SELECT pessoa.id_pessoa, pessoa.nm_pessoa, pessoa.nr_cpf, "
									+ "pessoa.nr_matricula, pessoa.nm_endereco, pessoa.nm_telefone, "
									+ "pessoa.nm_cep, pessoa.nm_email, "
									+ "pessoa.nm_senha, pessoa.dt_registro, pessoa.id_pessoa, pessoa.tipo_pessoa_id "
									+ "FROM `tb_pessoa` pessoa "
									+ "WHERE pessoa.`tipo_pessoa_id` =",
							TipoPessoa.TIPO_GESTOR);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			gestor = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return gestor;

	}

	@Override
	public Gestor getById(Integer id) throws QManagerSQLException {

		Gestor gestor = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT pessoa.id_pessoa, pessoa.nm_pessoa, pessoa.nr_cpf, "
									+ "pessoa.nr_matricula, pessoa.nm_endereco, pessoa.nm_telefone, "
									+ "pessoa.nm_cep, pessoa.nm_email, "
									+ "pessoa.nm_senha, pessoa.dt_registro, pessoa.id_pessoa, "
									+ "pessoa.tipo_pessoa_id "
									+ "FROM `tb_pessoa` pessoa "
									+ "WHERE pessoa.`tipo_pessoa_id` =", id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Gestor> gestores = convertToList(rs);

			if (!gestores.isEmpty())
				gestor = gestores.get(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return gestor;

	}

	@Override
	public List<Gestor> convertToList(ResultSet rs) throws QManagerSQLException {

		List<Gestor> gestores = new LinkedList<Gestor>();

		try {

			while (rs.next()) {
				Gestor gestor = new Gestor();
				DadosBancarios dadosBancarios = new DadosBancarios();
				TipoPessoa tipoPessoa = new TipoPessoa();
				// tabela pessoa
				gestor.setPessoaId(rs.getInt("pessoa.id_pessoa"));
				gestor.setNomePessoa(rs.getString("pessoa.nm_pessoa"));
				gestor.setCpf(rs.getString("pessoa.nr_cpf"));
				gestor.setMatricula(rs.getString("pessoa.nr_matricula"));
				gestor.setEndereco(rs.getString("pessoa.nm_endereco"));
				gestor.setCep(rs.getString("pessoa.nm_cep"));
				gestor.setTelefone(rs.getString("pessoa.nm_telefone"));
				gestor.setEmail(rs.getString("pessoa.nm_email"));
				gestor.setSenha(rs.getString("pessoa.nm_senha"));
				gestor.setRegistro(rs.getDate("pessoa.dt_registro"));
				dadosBancarios = DadosBancariosDAO.getInstance()
						.getByIdDadosBancarios(rs.getInt("pessoa.id_pessoa"));
				gestor.setDadosBancarios(dadosBancarios);
				tipoPessoa = TipoPessoaDAO.getInstance().getById(
						rs.getInt("pessoa.tipo_pessoa_id"));
				gestor.setTipoPessoa(tipoPessoa);

				gestores.add(gestor);
			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return gestores;
	}

}
