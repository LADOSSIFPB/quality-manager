package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Instituicao;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class InstituicaoDAO implements GenericDAO<Integer, Instituicao> {

	// a conexão com o banco de dados
	public Connection connection;

	public InstituicaoDAO(DatabaseConnection banco) {
		this.connection = (Connection) banco.getConnection();
	}

	@Override
	public Instituicao getById(Integer id) throws QManagerSQLException {

		Instituicao instituicao = null;

		try {

			String sql = String.format("%s %d",
					"SELECT * FROM `tb_instituicao` WHERE `id_instituicao` =",
					id);

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Instituicao> instituicoes = convertToList(rs);

			instituicao = instituicoes.get(0);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return instituicao;

	}

	@Override
	public int insert(Instituicao instituicao) throws QManagerSQLException {

		int chave = 0;

		try {

			String sql = String
					.format("%s %s ('%s', '%s', '%s', '%s')",
							"INSERT INTO `tb_instituicao` (`nr_cnpj`, `nm_instituicao`, `nm_sigla`, `vl_orcamento`)",
							"VALUES", instituicao.getCnpj(),
							instituicao.getNomeInstituicao(),
							instituicao.getSigla(), instituicao.getOrcamento());

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// envia para o Banco e fecha o objeto
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
	public void update(Instituicao instituicao) throws QManagerSQLException {

		try {

			// Define update setando cada atributo e cada valor é
			// representado por ?
			String sql = "UPDATE `tb_instituicao` SET `nr_cnpj`=?, `nm_instituicao`=?, "
					+ "`nm_sigla`=?, `vl_orcamento`=? "
					+ "WHERE `id_instituicao`=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, instituicao.getNomeInstituicao());
			stmt.setString(2, instituicao.getSigla());
			stmt.setDouble(3, instituicao.getOrcamento());
			stmt.setInt(4, instituicao.getIdInstituicao());

			// envia para o Banco e fecha o objeto
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

			// Deleta uma tupla setando o atributo de identificação com
			// valor representado por ?
			String sql = "DELETE FROM `tb_instituicao` WHERE `id_instituicao`=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, id);

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();
		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}
	}

	@Override
	public List<Instituicao> findAll() throws QManagerSQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Instituicao> convertToList(ResultSet rs)
			throws QManagerSQLException {

		List<Instituicao> instituicoes = new ArrayList<Instituicao>();

		Instituicao instituicao = new Instituicao();

		try {

			while (rs.next()) {
				instituicao.setCnpj(rs.getString("nr_cnpj"));
				instituicao.setNomeInstituicao(rs.getString("nm_instituicao"));
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
