package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import principal.Banco;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import entidades.EntidadeIF;
import entidades.Participacao;
import excecoes.ClasseInvalidaException;

/*
 TABLE `participacao`
 `id_participacao` INT NOT NULL AUTO_INCREMENT,
 `pessoa_id` INT NOT NULL,
 `projeto_id` INT NOT NULL,
 `dt_inicio` DATE NOT NULL,
 `dt_fim` DATE NULL,
 `fl_bolsista` TINYINT(1) NOT NULL --> isso tá certo?
 */

public class ParticipacaoDAO implements DAO {

	// a conexão com o banco de dados
	public Connection connection;

	public ParticipacaoDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}

	public void creat(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Participacao) {

			Participacao participacao = (Participacao) entidade;

			try {

				// Define um insert com os atributos e cada valor é representado
				// por ?
				String sql = "INSERT INTO `participacao` (`pessoa_id`, `projeto_id`, `dt_inicio`, `dt_fim`, `fl_bolsista`)"
						+ " VALUES (?, ?, ?, ?, ?)";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, participacao.getPessoaId());
				stmt.setInt(2, participacao.getProjetoId());
				stmt.setDate(3, participacao.getDataInicio());
				stmt.setDate(4, participacao.getDataFim());
				stmt.setInt(5, participacao.getBolsista());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();
			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		} else {
			throw new ClasseInvalidaException();
		}

	}

	public void readById(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Participacao) {

			Participacao participacao = (Participacao) entidade;

			try {

				String sql = String
						.format("%s %d",
								"SELECT * FROM `participacao` WHERE `id_participacao` =",
								participacao.getIdParticipacao());

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					participacao.setPessoaId(rs.getInt("pessoa_id"));
					participacao.setProjetoId(rs.getInt("projeto_id"));
					participacao.setDataInicio(rs.getDate("dt_inicio"));
					participacao.setDataFim(rs.getDate("dt_fim"));
					participacao.setBolsista(rs.getInt("fl_bolsista"));
				}

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		} else {
			throw new ClasseInvalidaException();
		}

	}

	public void update(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Participacao) {

			Participacao participacao = (Participacao) entidade;

			try {

				// Define update setando cada atributo e cada valor é
				// representado por ?
				String sql = "UPDATE `participacao` SET `pessoa_id`=?, `projeto_id`=?, `dt_inicio`=?, `dt_fim`=?, `fl_bolsista`=? "
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
				throw new RuntimeException(sqle);
			}
		} else {
			throw new ClasseInvalidaException();
		}

	}

	public void delete(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Participacao) {

			Participacao participacao = (Participacao) entidade;

			try {

				// Deleta uma tupla setando o atributo de identificação com
				// valor representado por ?
				String sql = "DELETE FROM `participacao` WHERE `id_participacao`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, participacao.getIdParticipacao());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}
