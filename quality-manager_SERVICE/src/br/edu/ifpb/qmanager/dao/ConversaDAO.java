package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.chat.Conversa;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class ConversaDAO implements GenericDAO<Integer, Conversa> {

	static DBPool banco;

	private static ConversaDAO instance;

	public Connection connection;

	public static ConversaDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new ConversaDAO(banco);
		return instance;
	}

	public ConversaDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Conversa conversa) throws SQLExceptionQManager {

		int idConversa = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {

			String sql = String.format("%s %s ('%s')",
					"INSERT INTO tb_chat (nm_nome) ", "VALUES", 
					conversa.getNome());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idConversa = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, this.connection);
		}

		return idConversa;
	}
	
	public int insertPessoa(Conversa conversa, Pessoa pessoa) throws SQLExceptionQManager {

		int idPessoaEmConversa = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {

			String sql = String.format("%s %s (%d, %d)",
					"INSERT INTO tb_chat_pessoas ("
						+ " chat_line_id, "
						+ " pessoa_id) ",
						"VALUES",
					conversa.getIdConversa(),
					pessoa.getPessoaId());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idPessoaEmConversa = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, this.connection);
		}

		return idPessoaEmConversa;
	}

	@Override
	public void update(Conversa conversa) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_chat"
					+ " SET nm_nome = ? "
					+ " WHERE id_chat = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setString(1, conversa.getNome());
			stmt.setInt(2, conversa.getIdConversa());

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

			String sql = "DELETE FROM tb_chat WHERE id_chat = ?";

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
	public List<Conversa> getAll() throws SQLExceptionQManager {

		List<Conversa> conversas = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s",
					"SELECT "
						+ " chat.id_chat, "
						+ " chat.nm_nome,"
						+ " chat.dt_registro" 
						+ " FROM tb_chat chat");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			conversas = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return conversas;
	}

	@Override
	public Conversa getById(Integer id) throws SQLExceptionQManager {

		Conversa conversa = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String
					.format("%s %d", 
							"SELECT "
								+ " chat.id_chat,"
								+ " chat.nm_nome," 
								+ " chat.dt_registro"
								+ " FROM tb_chat chat"
								+ " WHERE chat.id_chat =", id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Conversa> chats = convertToList(rs);

			if (!chats.isEmpty())
				conversa = chats.get(0);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return conversa;
	}

	public List<Conversa> find(Conversa chat) throws SQLExceptionQManager {

		List<Conversa> conversas;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %%%s%%", 
					"SELECT "
						+ "chat.id_chat,"
						+ " chat.nm_nome, "
						+ " chat.dt_registro"
						+ " FROM tb_chat chat"
						+ " WHERE chat.nm_nome LIKE ", 
						chat.getNome());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			conversas = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return conversas;

	}

	public List<Conversa> getByPessoa(Pessoa pessoa) throws SQLExceptionQManager {
		List<Conversa> conversas = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d",
					"SELECT "
						+ " chat.id_chat, "
						+ " chat.nm_nome,"
						+ " chat.dt_registro" 
						+ " FROM tb_chat chat INNER JOIN tb_chat_pessoas chat_pessoas"
						+ "   ON chat.id_chat = chat_pessoas.chat_id "
						+ " WHERE chat_pessoas.pessoa_id = ", 
					pessoa.getPessoaId());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			conversas = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return conversas;
	}

	public List<Pessoa> getPessoas(Conversa conversa)
			throws SQLExceptionQManager {

		List<Pessoa> pessoas = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s", "SELECT "
					+ " chat_pessoas.pessoa_id "
					+ " FROM tb_chat_pessoas chat_pessoas"
					+ " WHERE chat_pessoas.chat_id = ", conversa.getIdConversa());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				pessoas = new ArrayList<Pessoa>();
				do {
					int idPessoa = rs.getInt("chat_pessoas.pessoa_id");
					Pessoa pessoa = PessoaDAO.getInstance().getById(idPessoa);
					pessoas.add(pessoa);
				} while (rs.next());
			}

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return pessoas;
	}

	public int getQuantidadeConversasNaoVisualizadas(Pessoa pessoa) throws SQLExceptionQManager {
		
		int quantidade = 0;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d %s",
					"SELECT COUNT(*) AS quantidade"
						+ " FROM tb_chat_line_read chat_line_read "
						+ " INNER JOIN tb_chat_line chat_line"
						+ "   ON chat_line_read.chat_line_id = chat_line.id_chat_line "
						+ " INNER JOIN tb_chat_pessoas chat_pessoas"
						+ "   ON chat_pessoas.chat_id = chat_line.chat_id "
						+ " WHERE chat_line_read.fl_visualizado = TRUE"
						+ "   AND chat_pessoas.pessoa_id = ", pessoa.getPessoaId(),
						  " GROUP BY chat_pessoas.chat_id");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			if (rs.next())
				quantidade = rs.getInt("quantidade");

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return quantidade;
	}

	@Override
	public List<Conversa> convertToList(ResultSet rs) throws SQLExceptionQManager {

		List<Conversa> conversas = new LinkedList<Conversa>();

		try {
			while (rs.next()) {
				Conversa conversa = new Conversa();
				conversa.setIdConversa(rs.getInt("chat.id_chat"));
				conversa.setNome(rs.getString("chat.nm_nome"));
				conversa.setRegistro(rs.getDate("chat.dt_registro"));

				conversas.add(conversa);
			}

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return conversas;
	}

}