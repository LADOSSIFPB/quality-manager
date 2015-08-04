package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.chat.Chat;
import br.edu.ifpb.qmanager.chat.ChatLine;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class ChatLineDAO implements GenericDAO<Integer, ChatLine> {

	static DBPool banco;

	private static ChatLineDAO instance;

	public Connection connection;

	public static ChatLineDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new ChatLineDAO(banco);
		return instance;
	}

	public ChatLineDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(ChatLine chatLine) throws SQLExceptionQManager {

		int idChatLine = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {

			String sql = String.format("%s %s (%d, %d, %s)",
					"INSERT INTO tb_chat_line_line ("
						+ " chat_id, "
						+ " pessoa_id, "
						+ " nm_mensagem) ", 
						"VALUES", 
					chatLine.getChat().getIdChat(),
					chatLine.getRemetente().getPessoaId(),
					chatLine.getMensagem());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idChatLine = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, this.connection);
		}

		return idChatLine;
	}

	@Override
	public void update(ChatLine chatLine) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_chat_line_line SET "
					+ " chat_id = ?, "
					+ " pessoa_id = ?, "
					+ " nm_mensagem = ? "
					+ " WHERE id_chat_line = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, chatLine.getChat().getIdChat());
			stmt.setInt(2, chatLine.getRemetente().getPessoaId());
			stmt.setString(3, chatLine.getMensagem());
			stmt.setInt(4, chatLine.getIdChatLine());

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

			String sql = "DELETE FROM tb_chat_line_line WHERE id_chat_line = ?";

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
	public List<ChatLine> getAll() throws SQLExceptionQManager {

		List<ChatLine> chatLine = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s",
					"SELECT "
						+ " chat_line.id_chat_line, "
						+ " chat_line.chat_id,"
						+ " chat_line.pessoa_id, "
						+ " chat_line.nm_mensagem, "
						+ " chat_line.dt_registro "
						+ " FROM tb_chat_line AS chat_line");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			chatLine = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return chatLine;

	}

	@Override
	public ChatLine getById(Integer id) throws SQLExceptionQManager {

		ChatLine chatLine = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d", 
							"SELECT "
								+ " chat_line.id_chat_line, "
								+ " chat_line.chat_id,"
								+ " chat_line.pessoa_id, "
								+ " chat_line.nm_mensagem, "
								+ " chat_line.dt_registro "
								+ " FROM tb_chat_line AS chat_line "
								+ " WHERE chat_line.id_chat_line =", id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<ChatLine> chatLines = convertToList(rs);

			if (!chatLines.isEmpty())
				chatLine = chatLines.get(0);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return chatLine;
	}

	public List<ChatLine> find(ChatLine chatLine) throws SQLExceptionQManager {

		List<ChatLine> chatLines;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %%%s%%", 
					"SELECT "
						+ " chat_line.id_chat_line, "
						+ " chat_line.chat_id,"
						+ " chat_line.pessoa_id, "
						+ " chat_line.nm_mensagem, "
						+ " chat_line.dt_registro "
						+ " FROM tb_chat_line AS chat_line"
						+ " WHERE chat_line.nm_mensagem LIKE ", 
						chatLine.getMensagem());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			chatLines = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return chatLines;

	}

	@Override
	public List<ChatLine> convertToList(ResultSet rs) throws SQLExceptionQManager {

		List<ChatLine> chatLines = new LinkedList<ChatLine>();

		try {
			while (rs.next()) {
				ChatLine chatLine = new ChatLine();

				Pessoa remetente = new Pessoa();
				remetente.setPessoaId(rs.getInt("chat_line.pessoa_id"));
				chatLine.setRemetente(remetente);

				Chat chat = new Chat();
				chat.setIdChat(rs.getInt("chat_line.chat_id"));
				chatLine.setChat(chat);

				chatLine.setIdChatLine(rs.getInt("chat_line.id_chat_line"));
				chatLine.setMensagem(rs.getString("chat_line.nm_mensagem"));
				chatLine.setRegistro(rs.getDate("chat_line.dt_registro"));

				chatLines.add(chatLine);
			}

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return chatLines;
	}
}
