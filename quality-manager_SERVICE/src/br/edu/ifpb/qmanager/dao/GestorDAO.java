package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.Gestor;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class GestorDAO implements GenericDAO<Integer, Gestor> {
	
	// a conexão com o banco de dados
		public DatabaseConnection banco;
		public Connection connection;

		private PessoaDAO pessoaDAO;

		public GestorDAO(DatabaseConnection banco) {
			this.banco = banco;
			this.connection = (Connection) banco.getConnection();
			pessoaDAO = new PessoaDAO(banco);
		}

		@Override
		public int insert(Gestor gestor) throws QManagerSQLException {

			TipoPessoa tipoPessoa = new TipoPessoa();
			tipoPessoa.setIdTipoPessoa(4);
			gestor.setTipoPessoa(tipoPessoa);

			int idPessoa = pessoaDAO.insert(gestor);

			return idPessoa;

		}

		@Override
		public void update(Gestor gestor) throws QManagerSQLException {

			pessoaDAO.update(gestor);

		}

		@Override
		public void delete(Integer id) throws QManagerSQLException {

			pessoaDAO.delete(id);

		}

		@Override
		public List<Gestor> getAll() throws QManagerSQLException {

			List<Gestor> gestor;

			try {

				String sql = String.format("%s", "SELECT P.id_pessoa, P.nm_pessoa, P.nr_cpf,"
						+ " P.nr_matricula, P.nm_endereco, P.nm_telefone, P.nm_cep, P.nm_email,"
						+ " P.nm_senha, P.dt_registro, P.id_pessoa, P.tipo_pessoa_id"
						+ " FROM `tb_pessoa` P"
						+ " WHERE P.`tipo_pessoa_id` = 4");

				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(sql);

				gestor = convertToList(rs);

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

				String sql = String.format("%s %d", "SELECT P.id_pessoa, P.nm_pessoa, P.nr_cpf,"
						+ " P.nr_matricula, P.nm_endereco, P.nm_telefone, P.nm_cep, P.nm_email,"
						+ " P.nm_senha, P.dt_registro, P.id_pessoa, P.tipo_pessoa_id"
						+ " FROM `tb_pessoa` P"
						+ " WHERE P.`tipo_pessoa_id` =", id);

				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(sql);

				List<Gestor> gestores = convertToList(rs);

				if (!gestores.isEmpty())
					gestor = gestores.get(0);

			} catch (SQLException sqle) {
				throw new QManagerSQLException(sqle.getErrorCode(),
						sqle.getLocalizedMessage());
			}

			return gestor;

		}

		@Override
		public List<Gestor> convertToList(ResultSet rs)
				throws QManagerSQLException {

			List<Gestor> gestores = new LinkedList<Gestor>();

			DadosBancariosDAO dadosBancariosDAO = new DadosBancariosDAO(banco);
			TipoPessoaDAO tipoPessoaDAO = new TipoPessoaDAO(banco);

			try {

				while (rs.next()) {
					Gestor gestor = new Gestor();
					DadosBancarios dadosBancarios = new DadosBancarios();
					TipoPessoa tipoPessoa = new TipoPessoa();
					// tabela pessoa
					gestor.setPessoaId(rs.getInt("P.id_pessoa"));
					gestor.setNomePessoa(rs.getString("P.nm_pessoa"));
					gestor.setCpf(rs.getString("P.nr_cpf"));
					gestor.setMatricula(rs.getString("P.nr_matricula"));
					gestor.setEndereco(rs.getString("P.nm_endereco"));
					gestor.setCep(rs.getString("P.nm_cep"));
					gestor.setTelefone(rs.getString("P.nm_telefone"));
					gestor.setEmail(rs.getString("P.nm_email"));
					gestor.setSenha(rs.getString("P.nm_senha"));
					gestor.setRegistro(rs.getDate("P.dt_registro"));
					dadosBancarios = dadosBancariosDAO.getByIdDadosBancarios(rs
							.getInt("P.id_pessoa"));
					gestor.setDadosBancarios(dadosBancarios);
					tipoPessoa = tipoPessoaDAO.getById(rs
							.getInt("P.tipo_pessoa_id"));
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
