package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import principal.Banco;
import br.edu.ifpb.qmanager.entidade.Instituicao;
import br.edu.ifpb.qmanager.excecao.ClasseInvalidaException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/*
 TABLE `instituicao` 
 `id_instituicao` INT NOT NULL AUTO_INCREMENT,
 `nr_cnpj` CHAR(14) NOT NULL,
 `nm_instituicao` VARCHAR(45) NOT NULL,
 `nm_sigla` VARCHAR(10) NOT NULL,
 `vl_orcamento` DOUBLE NOT NULL
 */

public class InstituicaoDAO implements GenericDAO<Integer, Instituicao> {

	// a conexão com o banco de dados
	public Connection connection;

	public InstituicaoDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}

	@Override
	public int insert(Instituicao instituicao) throws ClasseInvalidaException {

		int chave = 0;
		
		try {

			// Define um insert com os atributos e cada valor é representado
			// por ?		
			String sql = String
					.format("%s %s ('%s', '%s', '%s', '%s')",
							"INSERT INTO `tb_instituicao` (`nr_cnpj`, `nm_instituicao`, `nm_sigla`, `vl_orcamento`)",
							"VALUES", instituicao.getCnpj(),
							instituicao.getNomeInstituicao(),
							instituicao.getSigla(),
							instituicao.getOrcamento());			

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);			

			// envia para o Banco e fecha o objeto
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			
			chave = BancoUtil.getGenerateKey(stmt);
			
			stmt.close();

		} catch (SQLException sqle) {
			
			Logger.getLogger(InstituicaoDAO.class.getName()).log(Level.SEVERE,
					null, sqle);
		} 
		
		return chave;
	}

	@Override
	public Instituicao getById(Integer id) throws ClasseInvalidaException {

		Instituicao instituicao = null;
		try {

			String sql = String.format("%s %d",
					"SELECT * FROM `tb_instituicao` WHERE `id_instituicao` =", id);

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Instituicao> instituicoes = convertToList(rs);
			
			instituicao = instituicoes.get(0);

		} catch (SQLException sqle) {
			Logger.getLogger(InstituicaoDAO.class.getName()).log(Level.SEVERE,
					null, sqle);
		}
		
		return instituicao;

	}

	@Override
	public void update(Instituicao instituicao) throws ClasseInvalidaException {

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
			Logger.getLogger(InstituicaoDAO.class.getName()).log(Level.SEVERE,
					null, sqle);
		}
	}

	@Override
	public void delete(Instituicao instituicao) throws ClasseInvalidaException {

		try {

			// Deleta uma tupla setando o atributo de identificação com
			// valor representado por ?
			String sql = "DELETE FROM `tb_instituicao` WHERE `id_instituicao`=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, instituicao.getIdInstituicao());

			// envia para o Banco e fecha o objeto
			stmt.execute();
			stmt.close();
		} catch (SQLException sqle) {
			Logger.getLogger(InstituicaoDAO.class.getName()).log(Level.SEVERE,
					null, sqle);
		}
	}

	@Override
	public List<Instituicao> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Instituicao> convertToList(ResultSet rs) {
		
		List<Instituicao> instituicoes = new ArrayList<Instituicao>();
		
		Instituicao instituicao = new Instituicao();
		
		try {
			
			while (rs.next()) {
				instituicao.setCnpj(rs.getString("nr_cnpj"));
				instituicao.setNomeInstituicao(rs.getString("nm_instituicao"));
				instituicao.setSigla(rs.getString("nm_sigla"));
				instituicao.setOrcamento(rs.getDouble("vl_orcamento"));
				instituicao.setRegistro(rs.getDate("dt_registro"));				
			}
			
			instituicoes.add(instituicao);
			
		} catch (SQLException e) {
			Logger.getLogger(InstituicaoDAO.class.getName()).log(Level.SEVERE,
					null, e);
		}

		return instituicoes;
	}
}
