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
	public Orientador getById(Integer id) throws QManagerSQLException {

		Orientador docente = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT P.id_pessoa, P.nm_pessoa, P.nr_cpf, P.nr_matricula, P.nm_endereco, P.nm_cep, P.nm_telefone, P.nm_email,"
									+ " D.nm_titulacao, D.nm_cargo, D.nm_local_trabalho, P.dt_registro"
									+ " FROM `tb_docente` D"
									+ " INNER JOIN `tb_pessoa` P ON D.`pessoa_id` = P.`id_pessoa`"
									+ " WHERE D.`pessoa_id`=", id);

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Orientador> docentes = convertToList(rs);

			docente = docentes.get(0);

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

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// envia para o Banco e fecha o objeto
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

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, docente.getTitulacao());
			stmt.setString(2, docente.getCargo());
			stmt.setString(3, docente.getLocalTrabalho());
			stmt.setInt(4, docente.getPessoaId());

			// envia para o Banco e fecha o objeto
			stmt.execute();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

	}

	@Override
	public void delete(Integer id) throws QManagerSQLException {

		try {

			// Deleta uma tupla setando o atributo de identificação com
			// valor representado por ?
			String sql = "DELETE FROM `tb_docente` WHERE `pessoa_id`=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, id);

			// envia para o Banco e fecha o objeto
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orientador> convertToList(ResultSet rs)
			throws QManagerSQLException {

		List<Orientador> docentes = new ArrayList<Orientador>();

		Orientador docente = new Orientador();

		try {

			while (rs.next()) {
				// tabela pessoa
				docente.setPessoaId(rs.getInt("P.id_pessoa"));
				docente.setNomePessoa(rs.getString("P.nm_pessoa"));
				docente.setCpf(rs.getString("P.nr_cpf"));
				docente.setMatricula(rs.getString("P.nr_matricula"));
				docente.setEndereco(rs.getString("P.nm_endereco"));
				docente.setCep(rs.getString("P.nm_cep"));
				docente.setTelefone(rs.getString("P.nm_telefone"));
				docente.setEmail(rs.getString("P.nm_email"));

				// docente
				docente.setTitulacao(rs.getString("D.nm_titulacao"));
				docente.setCargo(rs.getString("D.nm_cargo"));
				docente.setLocalTrabalho(rs.getString("D.nm_local_trabalho"));
				docente.setRegistro(rs.getDate("P.dt_registro"));

				docentes.add(docente);

			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return docentes;
	}
}