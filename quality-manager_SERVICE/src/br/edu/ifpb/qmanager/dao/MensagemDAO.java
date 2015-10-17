package br.edu.ifpb.qmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifpb.qmanager.chat.Conversa;
import br.edu.ifpb.qmanager.chat.Mensagem;
import br.edu.ifpb.qmanager.chat.SituacaoMensagem;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

public class MensagemDAO implements GenericDAO<Integer, Mensagem> {

	static DBPool banco;

	private static MensagemDAO instance;

	public Connection connection;

	public static MensagemDAO getInstance() {
		banco = DBPool.getInstance();
		instance = new MensagemDAO(banco);
		return instance;
	}

	public MensagemDAO(DBPool banco) {
		this.connection = (Connection) banco.getConn();
	}

	@Override
	public int insert(Mensagem mensagem) throws SQLExceptionQManager {

		int idMensagem = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {

			String sql = String.format("%s %s (%d, %d, '%s')",
					"INSERT INTO tb_chat_line ("
						+ " chat_id, "
						+ " pessoa_id, "
						+ " nm_mensagem) ", 
						"VALUES", 
					mensagem.getConversa().getIdConversa(),
					mensagem.getRemetente().getPessoaId(),
					mensagem.getMensagem());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idMensagem = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, this.connection);
		}

		return idMensagem;
	}

	public int insertSituacaoMensagem(Mensagem mensagem, Pessoa pessoa, boolean value)
			throws SQLExceptionQManager {

		int idLineRead = BancoUtil.IDVAZIO;

		PreparedStatement stmt = null;

		try {

			String sql = String.format("%s %s (%d, %d, %d)",
					"INSERT INTO tb_chat_line_read ("
						+ " chat_line_id, "
						+ " pessoa_id, "
						+ " fl_visualizado) ", 
						"VALUES", 
					mensagem.getConversa().getIdConversa(),
					pessoa.getPessoaId(),
					value ? 1 : 0);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			idLineRead = BancoUtil.getGenerateKey(stmt);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, this.connection);
		}

		return idLineRead;
	}

	@Override
	public void update(Mensagem mensagem) throws SQLExceptionQManager {
	}

	public void updateLineRead(Mensagem mensagem, Pessoa pessoa, boolean vizualizou) throws SQLExceptionQManager {
		PreparedStatement stmt = null;

		try {

			String sql = "UPDATE tb_chat_line_read SET " 
					+ " fl_visualizado = ?, "
					+ " WHERE chat_line_id = ?"
					+ "   AND pessoa_id = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setBoolean(1, vizualizou);
			stmt.setInt(2, mensagem.getIdMensagem());
			stmt.setInt(3, pessoa.getPessoaId());

			stmt.execute();
		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, this.connection);
		}
	}

	@Override
	public int delete(Integer id) throws SQLExceptionQManager {

		PreparedStatement stmt = null;

		try {

			String sql = "DELETE FROM tb_chat_line WHERE id_chat_line = ?";

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			stmt.setInt(1, id);

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
	public List<Mensagem> getAll() throws SQLExceptionQManager {

		List<Mensagem> mensagens = null;

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
						+ " FROM tb_chat_line chat_line");

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			mensagens = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return mensagens;
	}

	@Override
	public Mensagem getById(Integer id) throws SQLExceptionQManager {

		Mensagem mensagem = null;

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
								+ " FROM tb_chat_line chat_line "
								+ " WHERE chat_line.id_chat_line =", id);

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Mensagem> chatLines = convertToList(rs);

			if (!chatLines.isEmpty())
				mensagem = chatLines.get(0);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return mensagem;
	}

	public List<Mensagem> find(Mensagem mensagem) throws SQLExceptionQManager {

		List<Mensagem> chatLines;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s '%%%s%%'", 
					"SELECT "
						+ " chat_line.id_chat_line, "
						+ " chat_line.chat_id,"
						+ " chat_line.pessoa_id, "
						+ " chat_line.nm_mensagem, "
						+ " chat_line.dt_registro "
						+ " FROM tb_chat_line chat_line"
						+ " WHERE chat_line.nm_mensagem LIKE ", 
						mensagem.getMensagem());

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

	public List<Mensagem> getMensagensByConversa(Conversa conversa) throws SQLExceptionQManager {

		List<Mensagem> mensagens = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d", 
				"SELECT "
					+ " chat_line.id_chat_line, " 
					+ " chat_line.chat_id,"
					+ " chat_line.pessoa_id, " 
					+ " chat_line.nm_mensagem, "
					+ " chat_line.dt_registro "
					+ " FROM tb_chat_line chat_line"
					+ " WHERE chat_line.chat_id = ",
					conversa.getIdConversa());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			mensagens = convertToList(rs);

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());

		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return mensagens;
	}

	public SituacaoMensagem pessoaVisualizouMensagem(Mensagem mensagem,
			Pessoa pessoa) throws SQLExceptionQManager {

		SituacaoMensagem situacao = null;

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			String sql = String.format("%s %d %s %d",
					"SELECT chat_line_read.fl_visualizado, chat_line_read.dt_registro "
						+ " FROM tb_chat_line_read chat_line_read "
						+ " WHERE chat_line_read.pessoa_id = ", pessoa.getPessoaId(),
						  "   AND chat_line_read.chat_line_id = ", mensagem.getIdMensagem());

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				boolean visualizouMensagem = rs.getBoolean("chat_line_read.fl_visualizado");
				Date data = rs.getDate("chat_line_read.dt_registro");
				situacao = new SituacaoMensagem(pessoa, visualizouMensagem, data);
			}

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		} finally {

			banco.close(stmt, rs, this.connection);
		}

		return situacao;
	}

	@Override
	public List<Mensagem> convertToList(ResultSet rs) throws SQLExceptionQManager {

		List<Mensagem> mensagens = new LinkedList<Mensagem>();

		try {
			while (rs.next()) {
				Mensagem mensagem = new Mensagem();

				Pessoa remetente = PessoaDAO.getInstance().getById(
						rs.getInt("chat_line.pessoa_id"));
				mensagem.setRemetente(remetente);

				Conversa chat = new Conversa();
				chat.setIdConversa(rs.getInt("chat_line.chat_id"));
				mensagem.setConversa(chat);

				mensagem.setIdMensagem(rs.getInt("chat_line.id_chat_line"));
				mensagem.setMensagem(rs.getString("chat_line.nm_mensagem"));
				mensagem.setRegistro(rs.getDate("chat_line.dt_registro"));

				mensagens.add(mensagem);
			}

		} catch (SQLException sqle) {

			throw new SQLExceptionQManager(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return mensagens;
	}
}
