package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

public class InstituicaoFinanciadoraDAO implements
		GenericDAO<Integer, InstituicaoFinanciadora> {

	static DBPool banco;
	private static InstituicaoFinanciadoraDAO instance;

	public static InstituicaoFinanciadoraDAO getInstance() {
		if (instance == null) {
			banco = DBPool.getInstance();
			instance = new InstituicaoFinanciadoraDAO(banco);
		}
		return instance;
	}

	public Connection connection;

	public InstituicaoFinanciadoraDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(InstituicaoFinanciadora instituicao)
			throws QManagerSQLException {

		int chave = 0;

		try {

			String sql = String
					.format("%s %s ('%s', '%s', '%s', %s, %d)",
							"INSERT INTO tb_instituicao_financiadora (nr_cnpj, nm_instituicao, nm_sigla, vl_orcamento, pessoa_id)",
							"VALUES", instituicao.getCnpj(),
							instituicao.getNomeInstituicaoFinanciadora(),
							instituicao.getSigla(), instituicao.getOrcamento(),
							instituicao.getGestor().getPessoaId());

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			chave = BancoUtil.getGenerateKey(stmt);

			stmt.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return chave;
	}

	@Override
	public void update(InstituicaoFinanciadora instituicao)
			throws QManagerSQLException {

		try {

			String sql = "UPDATE tb_instituicao_financiadora SET nr_cnpj=?, nm_instituicao=?, "
					+ "nm_sigla=?, vl_orcamento=?, pessoa_id=?"
					+ "WHERE id_instituicao=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1, instituicao.getNomeInstituicaoFinanciadora());
			stmt.setString(2, instituicao.getSigla());
			stmt.setDouble(3, instituicao.getOrcamento());
			stmt.setInt(4, instituicao.getGestor().getPessoaId());
			stmt.setInt(5, instituicao.getIdInstituicaoFinanciadora());
			stmt.setInt(6, instituicao.getIdInstituicaoFinanciadora());

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

			String sql = "DELETE FROM tb_instituicao_financiadora WHERE id_instituicao=?";

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
	public List<InstituicaoFinanciadora> getAll() throws QManagerSQLException {
		List<InstituicaoFinanciadora> instituicoes;

		try {

			String sql = String
					.format("%s",
							"SELECT instituicao_financiadora.id_instituicao, "
									+ "instituicao_financiadora.nr_cnpj, "
									+ "instituicao_financiadora.nm_instituicao, "
									+ "instituicao_financiadora.nm_sigla, "
									+ "instituicao_financiadora.vl_orcamento, "
									+ "instituicao_financiadora.pessoa_id, "
									+ "instituicao_financiadora.dt_registro "
									+ "FROM tb_instituicao_financiadora instituicao_financiadora");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			instituicoes = convertToList(rs);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return instituicoes;
	}

	@Override
	public InstituicaoFinanciadora getById(Integer id)
			throws QManagerSQLException {

		InstituicaoFinanciadora instituicao = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT instituicao_financiadora.id_instituicao, "
									+ "instituicao_financiadora.nr_cnpj, "
									+ "instituicao_financiadora.nm_instituicao, "
									+ "instituicao_financiadora.nm_sigla, "
									+ "instituicao_financiadora.vl_orcamento, "
									+ "instituicao_financiadora.pessoa_id, "
									+ "instituicao_financiadora.dt_registro "
									+ "FROM tb_instituicao_financiadora instituicao_financiadora "
									+ "WHERE instituicao_financiadora.id_instituicao =",
							id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<InstituicaoFinanciadora> instituicoes = convertToList(rs);

			if (!instituicoes.isEmpty())
				instituicao = instituicoes.get(0);

			stmt.close();
			rs.close();

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return instituicao;

	}

	@Override
	public List<InstituicaoFinanciadora> convertToList(ResultSet rs)
			throws QManagerSQLException {

		List<InstituicaoFinanciadora> instituicoes = new LinkedList<InstituicaoFinanciadora>();

		try {

			while (rs.next()) {
				InstituicaoFinanciadora instituicao = new InstituicaoFinanciadora();

				// Gestor gestor = new Gestor();
				// gestor =
				// GestorDAO.getInstance().getById(rs.getInt("instituicao_financiadora.pessoa_id"));
				// instituicao.setGestor(gestor);

				instituicao.getGestor().setPessoaId(
						rs.getInt("instituicao_financiadora.pessoa_id"));

				instituicao.setIdInstituicaoFinanciadora(rs
						.getInt("instituicao_financiadora.id_instituicao"));
				instituicao.setCnpj(rs
						.getString("instituicao_financiadora.nr_cnpj"));
				instituicao.setNomeInstituicaoFinanciadora(rs
						.getString("instituicao_financiadora.nm_instituicao"));
				instituicao.setSigla(rs
						.getString("instituicao_financiadora.nm_sigla"));
				instituicao.setOrcamento(rs
						.getDouble("instituicao_financiadora.vl_orcamento"));
				instituicao.setRegistro(rs
						.getDate("instituicao_financiadora.dt_registro"));

				instituicoes.add(instituicao);

			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return instituicoes;

	}
}
