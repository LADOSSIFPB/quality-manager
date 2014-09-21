package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class InstituicaoFinanciadoraDAO implements
		GenericDAO<Integer, InstituicaoFinanciadora> {

	// a conexão com o banco de dados
	public Connection connection;

	public InstituicaoFinanciadoraDAO(DatabaseConnection banco) {
		this.connection = (Connection) banco.getConnection();
	}

	@Override
	public int insert(InstituicaoFinanciadora instituicao)
			throws QManagerSQLException {

		int chave = 0;

		try {

			String sql = String
					.format("%s %s ('%s', '%s', '%s', %s)",
							"INSERT INTO `tb_instituicao_financiadora` (`nr_cnpj`, `nm_instituicao`, `nm_sigla`, `vl_orcamento`)",
							"VALUES", instituicao.getCnpj(),
							instituicao.getNomeInstituicaoFinanciadora(),
							instituicao.getSigla(), instituicao.getOrcamento());

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

			String sql = "UPDATE `tb_instituicao_financiadora` SET `nr_cnpj`=?, `nm_instituicao`=?, "
					+ "`nm_sigla`=?, `vl_orcamento`=? "
					+ "WHERE `id_instituicao`=?";

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			stmt.setString(1, instituicao.getNomeInstituicaoFinanciadora());
			stmt.setString(2, instituicao.getSigla());
			stmt.setDouble(3, instituicao.getOrcamento());
			stmt.setInt(4, instituicao.getIdInstituicaoFinanciadora());

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

			String sql = "DELETE FROM `tb_instituicao_financiadora` WHERE `id_instituicao`=?";

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

			String sql = String.format("%s",
					"SELECT * FROM `tb_instituicao_financiadora`");

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			instituicoes = convertToList(rs);

			if (instituicoes.size() == 0) {
				throw new QManagerSQLException(777, "");
			}

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
							"SELECT * FROM `tb_instituicao_financiadora` WHERE `id_instituicao` =",
							id);

			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<InstituicaoFinanciadora> instituicoes = convertToList(rs);

			if (instituicoes.size() != 0) {
				instituicao = instituicoes.get(0);
			} else {
				throw new QManagerSQLException(777,
						"'id_instituicao_financiadora= " + id + "'");
			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return instituicao;

	}

	@Override
	public List<InstituicaoFinanciadora> convertToList(ResultSet rs)
			throws QManagerSQLException {

		List<InstituicaoFinanciadora> instituicoes = new ArrayList<InstituicaoFinanciadora>();

		try {

			while (rs.next()) {
				InstituicaoFinanciadora instituicao = new InstituicaoFinanciadora();
				instituicao.setIdInstituicaoFinanciadora(rs
						.getInt("id_instituicao"));
				instituicao.setCnpj(rs.getString("nr_cnpj"));
				instituicao.setNomeInstituicaoFinanciadora(rs
						.getString("nm_instituicao"));
				instituicao.setSigla(rs.getString("nm_sigla"));
				instituicao.setOrcamento(rs.getDouble("vl_orcamento"));
				instituicao.setRegistro(rs.getDate("dt_registro"));

				instituicoes.add(instituicao);

			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return instituicoes;

	}
}
