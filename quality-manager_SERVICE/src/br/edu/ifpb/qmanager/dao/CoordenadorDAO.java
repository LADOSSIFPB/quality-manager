package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Coordenador;
import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class CoordenadorDAO implements GenericDAO<Integer, Coordenador> {

	// a conexão com o banco de dados
	public DatabaseConnection banco;
	public Connection connection;

	private PessoaDAO pessoaDAO;

	public CoordenadorDAO(DatabaseConnection banco) {
		this.banco = banco;
		this.connection = (Connection) banco.getConnection();
		pessoaDAO = new PessoaDAO(banco);
	}

	@Override
	public int insert(Coordenador coordenador) throws QManagerSQLException {

		TipoPessoa tipoPessoa = new TipoPessoa();
		tipoPessoa.setIdTipoPessoa(1);
		coordenador.setTipoPessoa(tipoPessoa);

		int idPessoa = pessoaDAO.insert(coordenador);

		return idPessoa;

	}

	@Override
	public void update(Coordenador coordenador) throws QManagerSQLException {

		pessoaDAO.update(coordenador);

	}

	@Override
	public void delete(Integer id) throws QManagerSQLException {

		pessoaDAO.delete(id);

	}

	@Override
	public List<Coordenador> getAll() throws QManagerSQLException {

		List<Coordenador> coordenadores;

		try {

			String sql = String.format("%s", "SELECT P.id_pessoa, P.nm_pessoa, P.nr_cpf,"
					+ " P.nr_matricula, P.nm_endereco, P.nm_telefone, P.nm_cep, P.nm_email,"
					+ " P.nm_senha, P.dt_registro, P.id_pessoa, P.tipo_pessoa_id"
					+ " FROM `tb_pessoa` P"
					+ " WHERE P.`tipo_pessoa_id` = 1");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			coordenadores = convertToList(rs);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return coordenadores;

	}

	@Override
	public Coordenador getById(Integer id) throws QManagerSQLException {

		Coordenador coordenador = null;

		try {

			String sql = String.format("%s %d", "SELECT P.id_pessoa, P.nm_pessoa, P.nr_cpf,"
					+ " P.nr_matricula, P.nm_endereco, P.nm_telefone, P.nm_cep, P.nm_email,"
					+ " P.nm_senha, P.dt_registro, P.id_pessoa, P.tipo_pessoa_id"
					+ " FROM `tb_pessoa` P"
					+ " WHERE P.`tipo_pessoa_id` =", id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Coordenador> coordenadores = convertToList(rs);

			if (!coordenadores.isEmpty())
				coordenador = coordenadores.get(0);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return coordenador;

	}

	@Override
	public List<Coordenador> convertToList(ResultSet rs)
			throws QManagerSQLException {

		List<Coordenador> coordenadores = new LinkedList<Coordenador>();

		DadosBancariosDAO dadosBancariosDAO = new DadosBancariosDAO(banco);
		TipoPessoaDAO tipoPessoaDAO = new TipoPessoaDAO(banco);

		try {

			while (rs.next()) {
				Coordenador coordenador = new Coordenador();
				DadosBancarios dadosBancarios = new DadosBancarios();
				TipoPessoa tipoPessoa = new TipoPessoa();
				// tabela pessoa
				coordenador.setPessoaId(rs.getInt("P.id_pessoa"));
				coordenador.setNomePessoa(rs.getString("P.nm_pessoa"));
				coordenador.setCpf(rs.getString("P.nr_cpf"));
				coordenador.setMatricula(rs.getString("P.nr_matricula"));
				coordenador.setEndereco(rs.getString("P.nm_endereco"));
				coordenador.setCep(rs.getString("P.nm_cep"));
				coordenador.setTelefone(rs.getString("P.nm_telefone"));
				coordenador.setEmail(rs.getString("P.nm_email"));
				coordenador.setSenha(rs.getString("P.nm_senha"));
				coordenador.setRegistro(rs.getDate("P.dt_registro"));
				dadosBancarios = dadosBancariosDAO.getByIdDadosBancarios(rs
						.getInt("P.id_pessoa"));
				coordenador.setDadosBancarios(dadosBancarios);
				tipoPessoa = tipoPessoaDAO.getById(rs
						.getInt("P.tipo_pessoa_id"));
				coordenador.setTipoPessoa(tipoPessoa);

				coordenadores.add(coordenador);
			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return coordenadores;
	}

}
