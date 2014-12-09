package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

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
	public int insert(Servidor servidor) throws QManagerSQLException {

		TipoPessoa tipoPessoa = new TipoPessoa();
		tipoPessoa.setIdTipoPessoa(TipoPessoa.TIPO_ORIENTADOR);
		servidor.setTipoPessoa(tipoPessoa);

		int idPessoa = PessoaDAO.getInstance().insert(servidor);

		try {

			String sql = String.format("%s %s ('%s', '%s', '%s', %d)",
					"INSERT INTO tb_servidor (pessoa_id, nm_titulacao, nm_local_trabalho, "
							+ "cargo_servidor_id)", "VALUES", idPessoa,
					servidor.getTitulacao(), servidor.getLocalTrabalho(),
					servidor.getCargoServidor().getIdCargoServidor());

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
	public void update(Servidor servidor) throws QManagerSQLException {

		PessoaDAO.getInstance().update(servidor);

		try {

			String sql = "UPDATE tb_servidor SET nm_titulacao=?, nm_local_trabalho=?, cargo_servidor_id=?"
					+ " WHERE pessoa_id=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1, servidor.getTitulacao());
			stmt.setString(2, servidor.getLocalTrabalho());
			stmt.setInt(3, servidor.getCargoServidor().getIdCargoServidor());
			stmt.setInt(4, servidor.getPessoaId());

			stmt.execute();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public void delete(Integer id) throws QManagerSQLException {

		try {

			String sql = "DELETE FROM tb_docente WHERE pessoa_id=?";

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
	public List<Servidor> getAll() throws QManagerSQLException {
		List<Servidor> servidores;

		try {

			String sql = String
					.format("%s",
							"SELECT pessoa.id_pessoa, pessoa.nm_pessoa, pessoa.nr_cpf, "
									+ "pessoa.nr_matricula, pessoa.nm_endereco, pessoa.nm_telefone, "
									+ "pessoa.nm_cep, pessoa.nm_email, pessoa.dt_registro, "
									+ "pessoa.tipo_pessoa_id, "
									+ "servidor.nm_titulacao, servidor.nm_local_trabalho, "
									+ "servidor.cargo_servidor_id "
									+ "FROM tb_servidor servidor "
									+ "INNER JOIN tb_pessoa pessoa ON servidor.pessoa_id = pessoa.id_pessoa");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			servidores = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return servidores;
	}

	@Override
	public Servidor getById(Integer id) throws QManagerSQLException {

		Servidor servidor = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT pessoa.id_pessoa, pessoa.nm_pessoa, pessoa.nr_cpf, "
									+ "pessoa.nr_matricula, pessoa.nm_endereco, pessoa.nm_telefone, "
									+ "pessoa.nm_cep, pessoa.nm_email, pessoa.dt_registro, "
									+ "pessoa.tipo_pessoa_id, "
									+ "servidor.nm_titulacao, servidor.nm_local_trabalho, "
									+ "servidor.cargo_servidor_id "
									+ "FROM tb_servidor servidor "
									+ "INNER JOIN tb_pessoa pessoa ON servidor.pessoa_id = pessoa.id_pessoa "
									+ "WHERE pessoa.id_pessoa=", id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Servidor> servidores = convertToList(rs);

			if (!servidores.isEmpty())
				servidor = servidores.get(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return servidor;

	}

	public List<Servidor> getByProjeto(Projeto projeto)
			throws QManagerSQLException {
		List<Servidor> servidores;

		try {

			String sql = String
					.format("%s %d %s",
							"SELECT pessoa.id_pessoa, pessoa.nm_pessoa, pessoa.nr_cpf, "
									+ "pessoa.nr_matricula, pessoa.nm_endereco, pessoa.nm_telefone, "
									+ "pessoa.nm_cep, pessoa.nm_email, pessoa.dt_registro, "
									+ "pessoa.tipo_pessoa_id, "
									+ "servidor.nm_titulacao, servidor.nm_local_trabalho, "
									+ "servidor.cargo_servidor_id "
									+ "FROM tb_servidor servidor "
									+ "INNER JOIN tb_pessoa pessoa ON pessoa.id_pessoa = servidor.pessoa_id "
									+ "INNER JOIN tb_participacao participacao ON "
									+ "participacao.pessoa_id = pessoa.id_pessoa "
									+ "WHERE participacao.projeto_id =",
							projeto.getIdProjeto());
			// "GROUP BY participacao.pessoa_id");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			servidores = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return servidores;
	}

	@Override
	public List<Servidor> convertToList(ResultSet rs)
			throws QManagerSQLException {

		List<Servidor> servidores = new LinkedList<Servidor>();

		try {

			while (rs.next()) {

				Servidor servidor = new Servidor();
				DadosBancarios dadosBancarios = new DadosBancarios();
				TipoPessoa tipoPessoa = new TipoPessoa();
				CargoServidor cargoServidor = new CargoServidor();
				// tabela pessoa
				servidor.setPessoaId(rs.getInt("pessoa.id_pessoa"));
				servidor.setNomePessoa(rs.getString("pessoa.nm_pessoa"));
				servidor.setCpf(rs.getString("pessoa.nr_cpf"));
				servidor.setMatricula(rs.getString("pessoa.nr_matricula"));
				servidor.setEndereco(rs.getString("pessoa.nm_endereco"));
				servidor.setCep(rs.getString("pessoa.nm_cep"));
				servidor.setTelefone(rs.getString("pessoa.nm_telefone"));
				servidor.setEmail(rs.getString("pessoa.nm_email"));
				//servidor.setSenha(rs.getString("pessoa.nm_senha"));
				dadosBancarios = DadosBancariosDAO.getInstance()
						.getByIdDadosBancarios(rs.getInt("pessoa.id_pessoa"));
				servidor.setDadosBancarios(dadosBancarios);
				tipoPessoa = TipoPessoaDAO.getInstance().getById(
						rs.getInt("pessoa.tipo_pessoa_id"));
				servidor.setTipoPessoa(tipoPessoa);

				// docente
				servidor.setTitulacao(rs.getString("servidor.nm_titulacao"));
				servidor.setLocalTrabalho(rs
						.getString("servidor.nm_local_trabalho"));
				cargoServidor = CargoServidorDAO.getInstance().getById(
						rs.getInt("servidor.cargo_servidor_id"));
				servidor.setCargoServidor(cargoServidor);
				servidor.setRegistro(rs.getDate("pessoa.dt_registro"));

				servidores.add(servidor);

			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return servidores;

	}

}