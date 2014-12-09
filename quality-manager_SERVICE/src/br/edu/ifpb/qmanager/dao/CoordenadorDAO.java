package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Coordenador;
import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

public class CoordenadorDAO implements GenericDAO<Integer, Coordenador> {

	static DBPool banco;
	private static CoordenadorDAO instance;

	public static CoordenadorDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new CoordenadorDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public CoordenadorDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Coordenador coordenador) throws QManagerSQLException {

		TipoPessoa tipoPessoa = new TipoPessoa();
		tipoPessoa.setIdTipoPessoa(TipoPessoa.TIPO_COORDENADOR);
		coordenador.setTipoPessoa(tipoPessoa);

		int idPessoa = PessoaDAO.getInstance().insert(coordenador);

		return idPessoa;

	}

	@Override
	public void update(Coordenador coordenador) throws QManagerSQLException {

		PessoaDAO.getInstance().update(coordenador);

	}

	@Override
	public void delete(Integer id) throws QManagerSQLException {

		PessoaDAO.getInstance().delete(id);

	}

	@Override
	public List<Coordenador> getAll() throws QManagerSQLException {

		List<Coordenador> coordenadores;

		try {

			String sql = String
					.format("%s %d",
							"SELECT pessoa.id_pessoa, pessoa.nm_pessoa, pessoa.nr_cpf, "
									+ "pessoa.nr_matricula, pessoa.nm_endereco, pessoa.nm_telefone, "
									+ "pessoa.nm_cep, pessoa.nm_email, pessoa.dt_registro, "
									+ "pessoa.id_pessoa, pessoa.tipo_pessoa_id "
									+ "FROM tb_pessoa pessoa WHERE pessoa.tipo_pessoa_id =",
							TipoPessoa.TIPO_COORDENADOR);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			coordenadores = convertToList(rs);

			stmt.close();
			rs.close();

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

			String sql = String
					.format("%s %d",
							"SELECT pessoa.id_pessoa, pessoa.nm_pessoa, pessoa.nr_cpf, "
									+ "pessoa.nr_matricula, pessoa.nm_endereco, pessoa.nm_telefone, "
									+ "pessoa.nm_cep, pessoa.nm_email, pessoa.dt_registro, "
									+ "pessoa.id_pessoa, pessoa.tipo_pessoa_id "
									+ "FROM tb_pessoa pessoa"
									+ "WHERE pessoa.tipo_pessoa_id =", id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Coordenador> coordenadores = convertToList(rs);

			if (!coordenadores.isEmpty())
				coordenador = coordenadores.get(0);

			stmt.close();
			rs.close();

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

		try {

			while (rs.next()) {
				Coordenador coordenador = new Coordenador();
				DadosBancarios dadosBancarios = new DadosBancarios();
				TipoPessoa tipoPessoa = new TipoPessoa();
				// tabela pessoa
				coordenador.setPessoaId(rs.getInt("pessoa.id_pessoa"));
				coordenador.setNomePessoa(rs.getString("pessoa.nm_pessoa"));
				coordenador.setCpf(rs.getString("pessoa.nr_cpf"));
				coordenador.setMatricula(rs.getString("pessoa.nr_matricula"));
				coordenador.setEndereco(rs.getString("pessoa.nm_endereco"));
				coordenador.setCep(rs.getString("pessoa.nm_cep"));
				coordenador.setTelefone(rs.getString("pessoa.nm_telefone"));
				coordenador.setEmail(rs.getString("pessoa.nm_email"));
				coordenador.setRegistro(rs.getDate("pessoa.dt_registro"));
				dadosBancarios = DadosBancariosDAO.getInstance()
						.getByIdDadosBancarios(rs.getInt("pessoa.id_pessoa"));
				coordenador.setDadosBancarios(dadosBancarios);
				tipoPessoa = TipoPessoaDAO.getInstance().getById(
						rs.getInt("pessoa.tipo_pessoa_id"));
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
