package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class EditalCampusSubmissaoDAO {

	static DBPool banco;

	private static EditalCampusSubmissaoDAO instance;

	public Connection connection;

	public static EditalCampusSubmissaoDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new EditalCampusSubmissaoDAO(banco);
		return instance;
	}	

	public EditalCampusSubmissaoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	public void insert(
			Campus campusSubmissao, 
			int quantidadeProjetos, 
			Edital edital) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = String.format("%s %s (%d, %d, %d)",
					"INSERT INTO tb_edital_campus_submissao ("
						+ " edital_id, "
						+ " campus_institucional_id,"
						+ " nr_quantidade_projetos)", 
					"VALUES",
					edital.getIdEdital(),
					campusSubmissao.getIdCampusInstitucional(),
					quantidadeProjetos);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			// int = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, this.connection);
		}
	}

	public void update(
			Campus campusSubmissao, 
			int quantidadeProjetos, 
			Edital edital) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_edital_campus_submissao SET "
					+ " nr_quantidade_projetos = ?"
					+ " WHERE edital_id = ? AND campus_institucional_id = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, quantidadeProjetos);
			stmt.setInt(2, edital.getIdEdital());
			stmt.setInt(3, campusSubmissao.getIdCampusInstitucional());

			stmt.execute();

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, this.connection);
		}
	}

	public void delete(Integer id) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "DELETE FROM tb_edital_campus_submissao WHERE id_curso = ?";

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

	public Map<Campus, Integer> getCampiSubmissao(Edital edital) throws SQLExceptionQManager {

		Map<Campus, Integer> campiSubmissao = new HashMap<>();

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d",
				"SELECT "
					+ " edital_campus_submissao.campus_institucional_id,"
					+ " edital_campus_submissao.nr_quantidade_projetos"
					+ " FROM tb_edital_campus_submissao AS edital_campus_submissao"
					+ " WHERE edital_campus_submissao.edital_id = ",
					edital.getIdEdital());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			int quantErros = 0;
			while (rs.next()) {
				int idCurso = rs.getInt("edital_campus_submissao.campus_institucional_id");
				int quantidadeProjetos = rs.getInt("edital_campus_submissao.nr_quantidade_projetos");
				try {
					Campus campusSubmissao = CampusDAO.getInstance().getById(idCurso);
					campiSubmissao.put(campusSubmissao, quantidadeProjetos);
				} catch (SQLException sqle) {
					// TODO: verificar caso de erro.
					quantErros++;
				}
			}
		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return campiSubmissao;
	}

}
