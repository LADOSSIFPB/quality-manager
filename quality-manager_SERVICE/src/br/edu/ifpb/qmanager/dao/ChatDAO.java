package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.chat.Chat;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class ChatDAO implements GenericDAO<Integer, Chat> {

	static DBPool banco;

	private static ChatDAO instance;

	public Connection connection;

	public static ChatDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new ChatDAO(banco);
		return instance;
	}

	public ChatDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Chat chat) throws SQLExceptionQManager {

		int idCurso = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {

			String sql = String.format("%s %s ('%s')",
					"INSERT INTO tb_chat (nm_nome) ", "VALUES", 
					chat.getNome());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idCurso = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, this.connection);
		}

		return idCurso;
	}

	@Override
	public void update(Chat chat) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_chat"
					+ " SET nm_nome = ? "
					+ " WHERE id_chat = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, chat.getNome());
			stmt.setInt(2, chat.getIdChat());

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

			String sql = "DELETE FROM tb_chat" + " WHERE id_chat = ?";

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
	public List<Chat> getAll() throws SQLExceptionQManager {

		List<Chat> chat = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s",
					"SELECT "
						+ " chat.id_chat, "
						+ " chat.nm_nome,"
						+ " chat.dt_registro" 
						+ " FROM tb_chat AS chat");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			chat = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return chat;

	}

	@Override
	public Chat getById(Integer id) throws SQLExceptionQManager {

		Chat chat = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d", 
							"SELECT "
								+ " chat.id_chat,"
								+ " chat.nm_nome," 
								+ " chat.dt_registro"
								+ " FROM tb_chat AS chat"
								+ " WHERE chat.id_chat =", id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Chat> chats = convertToList(rs);

			if (!chats.isEmpty())
				chat = chats.get(0);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return chat;
	}

	public List<Chat> find(Chat chat) throws SQLExceptionQManager {

		List<Chat> chats;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %%%s%%", 
					"SELECT "
						+ "chat.id_chat,"
						+ " chat.nm_nome, "
						+ " chat.dt_registro"
						+ " FROM tb_chat AS chat"
						+ " WHERE chat.nm_nome LIKE ", 
						chat.getNome());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			chats = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return chats;

	}

	@Override
	public List<Chat> convertToList(ResultSet rs) throws SQLExceptionQManager {

		List<Chat> chats = new LinkedList<Chat>();

		try {
			while (rs.next()) {
				Chat chat = new Chat();
				chat.setIdChat(rs.getInt("chat.id_chat"));
				chat.setNome(rs.getString("chat.nm_nome"));
				chat.setRegistro(rs.getDate("chat.dt_registro"));

				chats.add(chat);
			}

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return chats;
	}
}