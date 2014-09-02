package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Usuario;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/*
 TABLE `tb_usuario`
 `id_usuario` INT NOT NULL AUTO_INCREMENT,
 `nm_login` VARCHAR(95),
 `nm_password` VARCHAR(25),
 `dt_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `pessoa_id` INT(11) NOT NULL
 */

public class UsuarioDAO implements GenericDAO<Integer, Usuario> {

	// a conexão com o banco de dados
	public Connection connection;

	public UsuarioDAO(DatabaseConnection banco) {
		this.connection = (Connection) banco.getConnection();
	}

	@Override
	public Usuario getById(Integer id) throws QManagerSQLException {

		Usuario usuario = null;

		try {

			String sql = String.format("%s %d",
					"SELECT * FROM `tb_usuario` WHERE `id_usuario` =", id);

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Usuario> usuarios = convertToList(rs);

			usuario = usuarios.get(0);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return usuario;

	}

	@Override
	public int insert(Usuario usuario) throws QManagerSQLException {

		int chave = 0;

		try {

			String sql = String
					.format("%s %s ('%s', '%s', %d)",
							"INSERT INTO `tb_usuario` (`nm_login`, `nm_password`, `pessoa_id`)",
							"VALUES", usuario.getLogin(), usuario.getSenha(),
							usuario.getPessoaId());

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
	public void update(Usuario usuario) throws QManagerSQLException {

		try {

			// Define update setando cada atributo e cada valor é
			// representado por ?
			String sql = "UPDATE `tb_usuario` SET `nm_login`=?, `nm_password`=?, `pessoa_id`=? "
					+ "WHERE `id_usuario`=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			stmt.setDouble(3, usuario.getPessoaId());
			stmt.setInt(4, usuario.getIdUsuario());

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
			String sql = "DELETE FROM `tb_usuario` WHERE `id_usuario`=?";

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
	public List<Usuario> findAll() throws QManagerSQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> convertToList(ResultSet rs)
			throws QManagerSQLException {

		List<Usuario> usuarios = new ArrayList<Usuario>();

		Usuario usuario = new Usuario();

		try {

			while (rs.next()) {
				usuario.setPessoaId(rs.getInt("pessoa_id"));
				usuario.setLogin(rs.getString("nm_login"));
				usuario.setSenha(rs.getString("nm_password"));
				usuario.setRegistro(rs.getDate("dt_registro"));

				usuarios.add(usuario);

			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return usuarios;

	}
}
