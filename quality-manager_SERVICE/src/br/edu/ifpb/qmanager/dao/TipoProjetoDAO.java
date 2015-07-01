package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.TipoProjeto;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class TipoProjetoDAO implements GenericDAO<Integer, TipoProjeto> {

	static DBPool banco;

	private static TipoProjetoDAO instance;

	public Connection connection;

	public static TipoProjetoDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new TipoProjetoDAO(banco);
		return instance;
	}

	public TipoProjetoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(TipoProjeto tipoProjeto) throws SQLExceptionQManager {

		int idTipoProjeto = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {

			String sql = String.format("%s ('%s')",
					"INSERT INTO tb_tipo_projeto (nm_tipo_projeto) VALUES",
					tipoProjeto.getNomeProjeto());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idTipoProjeto = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, this.connection);
		}

		return idTipoProjeto;
	}

	@Override
	public void update(TipoProjeto tipoProjeto) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_tipo_projeto SET "
					+ " nm_curso = ? "
					+ " WHERE id_tipo_projeto = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, tipoProjeto.getNomeProjeto());
			stmt.setInt(2, tipoProjeto.getIdTipoProjeto());

			stmt.execute();

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, this.connection);
		}
	}

	@Override
	public void delete(Integer id) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "DELETE FROM tb_tipo_projeto" + " WHERE id_tipo_projeto = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, id);

			stmt.execute();

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, this.connection);
		}
	}

	@Override
	public List<TipoProjeto> getAll() throws SQLExceptionQManager {

		List<TipoProjeto> tipoProjeto = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s",
					"SELECT tipo_projeto.id_tipo_projeto,"
					+ " tipo_projeto.nm_tipo_projeto"
					+ " FROM tb_tipo_projeto AS tipo_projeto");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			tipoProjeto = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return tipoProjeto;

	}

	@Override
	public TipoProjeto getById(Integer id) throws SQLExceptionQManager {

		TipoProjeto tipoProjeto = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d", "SELECT tipo_projeto.id_tipo_projeto, "
							+ " tipo_projeto.nm_tipo_projeto, "
							+ " tipo_projeto.dt_registro "
							+ " FROM tb_tipo_projeto AS tipo_projeto "
							+ " WHERE tipo_projeto.id_tipo_projeto = ", id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<TipoProjeto> tiposProjeto = convertToList(rs);

			if (!tiposProjeto.isEmpty())
				tipoProjeto = tiposProjeto.get(0);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return tipoProjeto;
	}

	public List<TipoProjeto> find(TipoProjeto tipoProjeto) throws SQLExceptionQManager {

		List<TipoProjeto> tiposProjeto;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s", 
					"SELECT tipo_projeto.id_tipo_projeto, "
					+ " tipo_projeto.nm_tipo_projeto, "
					+ " tipo_projeto.dt_registro"
					+ " FROM tb_tipo_projeto AS tipo_projeto"
					+ " WHERE tipo_projeto.nm_tipo_projeto LIKE '%" 
					+ tipoProjeto.getNomeProjeto()
					+ "%'");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			tiposProjeto = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return tiposProjeto;

	}

	@Override
	public List<TipoProjeto> convertToList(ResultSet rs) throws SQLExceptionQManager {

		List<TipoProjeto> cursos = new ArrayList<TipoProjeto>();

		try {
			while (rs.next()) {
				TipoProjeto tipoProjeto = new TipoProjeto();
				tipoProjeto.setIdTipoProjeto(rs.getInt("tipo_projeto.id_tipo_projeto"));
				tipoProjeto.setNomeProjeto(rs.getString("tipo_projeto.nm_tipo_projeto"));

				cursos.add(tipoProjeto);
			}

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return cursos;
	}
}