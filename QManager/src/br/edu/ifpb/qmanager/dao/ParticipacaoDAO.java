package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class ParticipacaoDAO implements GenericDAO<Integer, Participacao> {

	// a conexão com o banco de dados
	public Connection connection;

	public ParticipacaoDAO(DatabaseConnection banco) {
		this.connection = (Connection) banco.getConnection();
	}

	@Override
	public Participacao getById(Integer id) throws QManagerSQLException {

		Participacao participacao = null;

		try {

			String sql = String
					.format("%s %d",
							"SELECT * FROM `tb_participacao` WHERE `id_participacao` =",
							id);

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(sql);

			List<Participacao> participacoes = convertToList(rs);

			participacao = participacoes.get(0);

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return participacao;

	}

	@Override
	public int insert(Participacao participacao) throws QManagerSQLException {

		int chave = 0;

		try {

			// Define um insert com os atributos e cada valor é representado
			// por ?
			String sql = String
					.format("%s %s ('%d', '%d', '%s', '%s', '%d')",
							"INSERT INTO `tb_participacao` (`pessoa_id`, `projeto_id`, `dt_inicio`, `dt_fim`, `fl_bolsista`)"
									+ "VALUES", participacao.getPessoaId(),
							participacao.getProjetoId(),
							participacao.getDataInicio(),
							participacao.getDataFim(),
							participacao.getBolsista());

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
	public void update(Participacao participacao) throws QManagerSQLException {

		try {

			// Define update setando cada atributo e cada valor é
			// representado por ?
			String sql = "UPDATE `tb_participacao` SET `pessoa_id`=?, `projeto_id`=?, `dt_inicio`=?, `dt_fim`=?, `fl_bolsista`=? "
					+ "WHERE `id_participacao`=?";

			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, participacao.getPessoaId());
			stmt.setInt(2, participacao.getProjetoId());
			stmt.setDate(3, participacao.getDataInicio());
			stmt.setDate(4, participacao.getDataFim());
			stmt.setInt(5, participacao.getBolsista());
			stmt.setInt(6, participacao.getIdParticipacao());

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
			String sql = "DELETE FROM `tb_participacao` WHERE `id_participacao`=?";

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
	public List<Participacao> findAll() throws QManagerSQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Participacao> convertToList(ResultSet rs)
			throws QManagerSQLException {

		List<Participacao> participacoes = new ArrayList<Participacao>();

		Participacao participacao = new Participacao();

		try {

			while (rs.next()) {
				participacao.setPessoaId(rs.getInt("pessoa_id"));
				participacao.setProjetoId(rs.getInt("projeto_id"));
				participacao.setDataInicio(rs.getDate("dt_inicio"));
				participacao.setDataFim(rs.getDate("dt_fim"));
				participacao.setBolsista(rs.getInt("fl_bolsista"));

				participacoes.add(participacao);

			}

		} catch (SQLException sqle) {
			throw new QManagerSQLException(sqle.getErrorCode(),
					sqle.getLocalizedMessage());
		}

		return participacoes;

	}

}
