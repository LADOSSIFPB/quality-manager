package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import br.edu.ifpb.qmanager.entidade.Orientacao;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

import java.sql.Statement;


public class OrientacaoDAO implements GenericDAO<Integer, Orientacao> {
	

	static DBPool banco;
	
	private static OrientacaoDAO instance;
	
	public Connection connection;

	public static OrientacaoDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new OrientacaoDAO(banco);
		return instance;
	}

	public OrientacaoDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Orientacao orientacao) throws SQLExceptionQManager {

		int idOrientacao = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {
			
			String sql = "INSERT INTO tb_orientacao (" 
						+ " tipo_orientacao,"
						+ " dt_inicio,"
						+ " dt_fim," 
						+ " servidor_id,"
						+ " discente_id)"
						+ " VALUES (?, ?, ?, ?, ?)";
							
			stmt = (PreparedStatement) connection.prepareStatement(sql, 
					Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, orientacao.getTipoOrientacao());
			stmt.setDate(2, orientacao.getDtInicio() != null ? 
					new Date(orientacao.getDtInicio().getTime()): null);
			stmt.setDate(3, orientacao.getDtFim() != null ?
					new Date(orientacao.getDtInicio().getTime()): null);
			stmt.setInt(4, orientacao.getServidor().getPessoaId());
			stmt.setInt(5, orientacao.getDiscente().getPessoaId());
			
			stmt.executeUpdate();			

			idOrientacao = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}

		return idOrientacao;
	}

	@Override
	public void update(Orientacao orientacao) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_orientacao" 
					+ " SET tipo_orientacao = ?,"
					+ " dt_inicio = ?, "
					+ " dt_fim = ?,"
					+ " servidor_id = ?,"
					+ " discente_id = ?"
					+ " WHERE id_orientacao = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, orientacao.getTipoOrientacao());
			stmt.setDate(2, orientacao.getDtInicio()!= null ? 
					new Date(orientacao.getDtInicio().getTime()): null);
			stmt.setDate(3, orientacao.getDtFim()!= null ? 
					new Date(orientacao.getDtFim().getTime()): null);
			stmt.setInt(4, orientacao.getServidor().getPessoaId());
			stmt.setInt(5, orientacao.getDiscente().getPessoaId());
			stmt.setInt(6, orientacao.getIdOrientacao());

			stmt.execute();

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, this.connection);
		}
	}

	@Override
	public int delete(Integer idOrientacao)throws SQLExceptionQManager{
		
		PreparedStatement stmt = null;
		
		try {

			String sql = "DELETE FROM tb_orientacao WHERE id_orientacao =?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, idOrientacao);

			stmt.execute();

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {
			
			banco.close(stmt, this.connection);
		}
		
		return BancoUtil.NOROWSUPDATED;
	}
		
	@Override
	public List<Orientacao> getAll() throws SQLExceptionQManager{
		List<Orientacao> orientacoes;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s","SELECT  orientacao.tipo_orientacao,"
							+ " orientacao.dt_inicio, "
							+ " orientacao.dt_fim,"
							+ " servidor.nm_pessoa,"
							+ " discente.nm_pessoa"
							+ "from tb_orientacao orientacao"
							+ "INNER JOIN tb_pessoa servidor"
							+ "ON orientacao.servidor_id = servidor.id_pessoa"
							+ "INNER JOIN tb_pessoa discente "
							+ "ON orientacao.discente_id = discente.id_pessoa"
							);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery();

			orientacoes = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return orientacoes;
	}


	@Override
	public Orientacao getById (Integer idOrientacao) throws SQLExceptionQManager {

		Orientacao orientacao = null;
		List<Orientacao> orientacoes = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT orientacao.tipo_orientacao,"
					+ " orientacao.dt_inicio," 
					+ " orientacao.dt_fim,"
					+ " servidor.nm_pessoa,"
					+ " discente.nm_pessoa"
					+ " FROM tb_orientacao orientacao"
					+ " INNER JOIN tb_pessoa servidor"
					+ " ON orientacao.servidor_id = servidor.id_pessoa" 
					+ " INNER JOIN tb_pessoa discente"
					+ " ON orientacao.discente_id = discente.id_pessoa"
					+ " WHERE id_orientacao = ?" ;
			
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			
			stmt.setInt(1, idOrientacao);

			rs = stmt.executeQuery();

			orientacoes = convertToList(rs);

			if (!orientacoes.isEmpty()) {
				orientacao = orientacoes.get(0);
			}
			
		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return orientacao;
	}

	
	public List<Orientacao> getByDiscente(Integer idDiscente) throws SQLExceptionQManager {
		
		List<Orientacao> orientacoes = null;

		PreparedStatement stmt = null;
		
		ResultSet rs = null;

		try {

			String sql = "SELECT orientacao.tipo_orientacao,"
					+ "orientacao.dt_inicio,"
					+ "orientacao.dt_fim,"
					+ "servidor.nm_pessoa,"
					+ "discente.nm_pessoa"
					+ "FROM tb_orientacao orientacao "
					+ "INNER JOIN tb_pessoa servidor"
					+ " ON orientacao.servidor_id = servidor.id_pessoa "
					+ "INNER JOIN tb_pessoa discente "
					+ "ON orientacao.discente_id = discente.id_pessoa"
					+ "where orientacao.discente_id = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);
			
			stmt.setInt(1, idDiscente);
			
			rs = stmt.executeQuery();

			orientacoes = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return orientacoes;
	}
	
	
	
	public List<Orientacao> getByServidor(Integer idServidor) throws SQLExceptionQManager {

		List<Orientacao> orientacoes = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = "SELECT orientacao.tipo_orientacao,"
					+ "orientacao.dt_inicio,"
					+ "orientacao.dt_fim,"
					+ "servidor.nm_pessoa,"
					+ "discente.nm_pessoa" 
					+ "FROM tb_orientacao orientacao" 
					+ "INNER JOIN tb_pessoa servidor"
					+ "ON orientacao.servidor_id = servidor.id_pessoa"
					+ "INNER JOIN tb_pessoa discente "
					+ "ON orientacao.discente_id = discente.id_pessoa"
					+ "where orientacao.servidor_id = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);
            
			stmt.setInt(1, idServidor);
			
			rs = stmt.executeQuery(sql);

			orientacoes = convertToList(rs);

		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
			
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return orientacoes;
	}
	

	@Override
	public List<Orientacao> find(Orientacao orientacao) throws SQLExceptionQManager {
		
		List<Orientacao> orientacoes = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s '%%%s%%'",
							"SELECT  orientacao.tipo_orientacao, "
									+ " orientacao.dt_inicio, "
									+ " orientacao.dt_fim,"
									+ " servidor.nm_pessoa,"
									+ " discente.nm_pessoa"
									+ "from tb_orientacao orientacao"
									+ "INNER JOIN tb_pessoa servidor"
									+ "ON orientacao.servidor_id = servidor.id_pessoa"
									+ "INNER JOIN tb_pessoa discente "
									+ "ON orientacao.discente_id = discente.id_pessoa"
									+ " WHERE servidor.nm_pessoa LIKE",
							            orientacao.getServidor().getNomePessoa());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			orientacoes = convertToList(rs);

		} catch (SQLException sqle) {
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return orientacoes;
		
	}


	@Override
	public List<Orientacao> convertToList(ResultSet rs)
			throws SQLExceptionQManager {
		List<Orientacao> orientacoes = new LinkedList<Orientacao>();
	
		try {
	
			while (rs.next()) {
				
				Orientacao orientacao = new Orientacao();
				orientacao.setIdOrientacao(rs.getInt("orientacao.id_orientacao"));
			    orientacao.setTipoOrientacao(rs.getString("orientacao.tipo_orientacao"));
				orientacao.setDtInicio(rs.getDate("orientacao.dt_inicio"));
				orientacao.setDtFim(rs.getDate("orientacao.dt_fim"));
				orientacao.setDtRegistro(rs.getDate("orientacao.dt_registro"));
	
				
				// Servidor
				int idServidor = rs.getInt("orientacao.servidor_id");				
				orientacao.setServidor(ServidorDAO.getInstance().getById(
						idServidor));
				
				// Discente
				int idDiscente = rs.getInt("orientacao.discente_id");
				orientacao.setDiscente(DiscenteDAO.getInstance().getById(idDiscente));
	
				orientacoes.add(orientacao);
			}
	
		} catch (SQLException sqle) {
			
			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}
	
		return orientacoes;
	}
	





}


