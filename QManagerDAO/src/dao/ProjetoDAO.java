package dao;

import java.sql.SQLException;

import principal.Banco;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import entidades.EntidadeIF;
import entidades.Projeto;
import excecoes.ClasseInvalidaException;

/*
 TABLE `projeto`
 `id_projeto` INT NOT NULL AUTO_INCREMENT,
 `nm_projeto` VARCHAR(45) NOT NULL,
 `dt_inicio_projeto` DATE NOT NULL,
 `dt_fim_projeto` DATE NOT NULL,
 `relatorio_parcial` BLOB NULL,
 `relatorio_final` BLOB NULL,
 `nr_processo` INT NOT NULL, ->> rever essa questão, acho que INT é pequeno
 `tp_projeto` CHAR NOT NULL,
 `edital_id` INT NOT NULL
 */

public class ProjetoDAO implements DAO {

	// a conexão com o banco de dados
	public Connection connection;

	public ProjetoDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}

	public void creat(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Projeto) {

			Projeto projeto = (Projeto) entidade;

			try {

				// Define um insert com os atributos e cada valor é representado
				// por ?
				String sql = "INSERT INTO `projeto` (`nm_projeto`, `dt_inicio_projeto`, `dt_fim_projeto`, `relatorio_parcial`, `relatorio_final`, `nr_processo`, `tp_projeto`, `edital_id`)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setString(1, projeto.getNomeProjeto());
				stmt.setDate(2, projeto.getInicioProjeto());
				stmt.setDate(3, projeto.getFimProjeto());
				stmt.setString(4, projeto.getRelatorioParcial());
				stmt.setString(5, projeto.getRelatorioFinal());
				stmt.setInt(6, projeto.getProcesso());
				stmt.setString(7, projeto.getTipoProjeto());
				stmt.setInt(8, projeto.getEditalId());

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

	public void read(EntidadeIF entidade) throws ClasseInvalidaException {

	}

	public void update(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Projeto) {

			Projeto projeto = (Projeto) entidade;

			try {

				// Define update setando cada atributo e cada valor é
				// representado por ?
				String sql = "UPDATE `projeto` SET `nm_projeto`=?, `dt_inicio_projeto`=?, `dt_fim_projeto`=?, `relatorio_parcial`=?, `relatorio_final`=?, `nr_processo`=?, `tp_projeto`=?, `edital_id`=? "
						+ "WHERE `id_projeto`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setString(1, projeto.getNomeProjeto());
				stmt.setDate(2, projeto.getInicioProjeto());
				stmt.setDate(3, projeto.getFimProjeto());
				stmt.setString(4, projeto.getRelatorioParcial());
				stmt.setString(5, projeto.getRelatorioFinal());
				stmt.setInt(6, projeto.getProcesso());
				stmt.setString(7, projeto.getTipoProjeto());
				stmt.setInt(8, projeto.getEditalId());

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

		if (entidade instanceof Projeto) {

			Projeto projeto = (Projeto) entidade;

			try {

				// Deleta uma tupla setando o atributo de identificação com
				// valor representado por ?
				String sql = "DELETE FROM `projeto` WHERE `id_projeto`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, projeto.getIdProjeto());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}