package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.EditalCampusSubmissao;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class EditalCampusSubmissaoDAO 
	implements GenericDAO<Integer, EditalCampusSubmissao>{

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

	/**
	 * Recupera os campi autorizados a submeter para o Edital.
	 *  
	 * @param edital
	 * @return
	 * @throws SQLExceptionQManager
	 */
	public List<Campus> getCampiSubmissaoByEdital(Edital edital) throws SQLExceptionQManager {

		List<Campus> campiSubmissao = new ArrayList<Campus>();

		PreparedStatement stmt = null;
		
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d",
				"SELECT"
					+ " edital_campus_submissao.campus_institucional_id"
					+ " FROM tb_edital_campus_submissao AS edital_campus_submissao"
					+ " WHERE edital_campus_submissao.edital_id = ",
					edital.getIdEdital());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				int idCampus = rs.getInt("edital_campus_submissao.campus_institucional_id");
				
				Campus campus = CampusDAO.getInstance().getById(idCampus);
				
				campiSubmissao.add(campus);				
			}

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return campiSubmissao;
	}

	@Override
	public int insert(EditalCampusSubmissao editalCampusSubmissao) throws SQLExceptionQManager {

		int idEditalCampusSubmissao = BancoUtil.IDVAZIO;
		
		PreparedStatement stmt = null;

		try {

			String sql = String.format("%s %s (%d, %d, %d)",
					"INSERT INTO tb_edital_campus_submissao ("
						+ " edital_id, "
						+ " campus_institucional_id,"
						+ " nr_quantidade_projetos)", 
					"VALUES",
					editalCampusSubmissao.getEdital().getIdEdital(),
					editalCampusSubmissao.getCampus().getIdCampusInstitucional(),
					editalCampusSubmissao.getQuantidadeProjeto());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idEditalCampusSubmissao = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, this.connection);
		}
		
		return idEditalCampusSubmissao;
	}

	@Override
	public void update(EditalCampusSubmissao editalCampusSubmissao)
			throws SQLExceptionQManager {
		
		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_edital_campus_submissao SET "
					+ " nr_quantidade_projetos = ?"
					+ " WHERE edital_id = ?"
					+ "	 AND campus_institucional_id = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, editalCampusSubmissao.getQuantidadeProjeto());
			stmt.setInt(2, editalCampusSubmissao.getEdital().getIdEdital());
			stmt.setInt(3, editalCampusSubmissao.getCampus()
					.getIdCampusInstitucional());

			stmt.execute();

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}		
	}
	
	@Override
	public void delete(Integer idEditalCampusSubmissao) throws SQLExceptionQManager {
		PreparedStatement stmt = null;

		try {

			String sql = "DELETE FROM tb_edital_campus_submissao WHERE id_curso = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, idEditalCampusSubmissao);

			stmt.execute();

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, this.connection);
		}		
	}

	@Override
	public List<EditalCampusSubmissao> getAll() throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EditalCampusSubmissao getById(Integer pk)
			throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EditalCampusSubmissao> find(EditalCampusSubmissao entity)
			throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EditalCampusSubmissao> convertToList(ResultSet rs)
			throws SQLExceptionQManager {
		// TODO Auto-generated method stub
		return null;
	}
}
