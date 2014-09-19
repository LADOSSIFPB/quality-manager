package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class OrientadorDAO implements GenericDAO<Integer, Orientador> {

	// a conexão com o banco de dados
	public Connection connection;

	private PessoaDAO pessoaDAO;

	public OrientadorDAO(DatabaseConnection banco) {
		this.connection = (Connection) banco.getConnection();
		pessoaDAO = new PessoaDAO(banco);
	}

	@Override
	public List<Orientador> getAll() throws QManagerSQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Orientador getById(Integer id) throws QManagerSQLException {

		Orientador docente = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT P.id_pessoa, P.nm_pessoa, P.nr_cpf, P.nr_matricula, P.nm_endereco, P.nm_cep, P.nm_telefone, P.nm_email, P.nm_senha,"
									+ " O.nm_titulacao, O.nm_cargo, O.nm_local_trabalho, P.dt_registro"
									+ " FROM `tb_orientador` O"
									+ " INNER JOIN `tb_pessoa` P ON O.`pessoa_id` = P.`id_pessoa`"
									+ " WHERE O.`pessoa_id`=", id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Orientador> docentes = convertToList(rs);

			if (docentes.size() != 0) {
				docente = docentes.get(0);
			} else {
				throw new QManagerSQLException(777, "'pessoa_id= " + id + "'");
			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return docente;

	}

	@Override
	public int insert(Orientador docente) throws QManagerSQLException {

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
	public void update(Orientador entidade) throws QManagerSQLException {

		Orientador docente = (Orientador) entidade;

		pessoaDAO.update(docente);

		try {

			String sql = "UPDATE `tb_docente` SET `nm_titulacao`=?, `nm_cargo`=?, `nm_local_trabalho`=?"
					+ " WHERE `pessoa_id`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1, docente.getTitulacao());
			stmt.setString(2, docente.getCargo());
			stmt.setString(3, docente.getLocalTrabalho());
			stmt.setInt(4, docente.getPessoaId());

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
	public List<Orientador> findAll() throws QManagerSQLException {
		return null;
	}

	@Override
	public List<Orientador> convertToList(ResultSet rs)
			throws QManagerSQLException {

		List<Orientador> orientadores = new ArrayList<Orientador>();

		Orientador orientador = new Orientador();

		try {

			while (rs.next()) {
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