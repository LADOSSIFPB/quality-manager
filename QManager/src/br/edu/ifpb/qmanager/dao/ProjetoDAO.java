package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import principal.Banco;
import br.edu.ifpb.qmanager.entidade.EntidadeIF;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.excecao.ClasseInvalidaException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

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
//TODO: implements DAO
public class ProjetoDAO {

	// a conexão com o banco de dados
	public Connection connection;

	public ProjetoDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}

	public void insert(EntidadeIF entidade) throws ClasseInvalidaException {

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

	public void readById(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Projeto) {

			Projeto projeto = (Projeto) entidade;

			try {

				String sql = String.format("%s %d",
						"SELECT * FROM `projeto` WHERE `id_projeto` =",
						projeto.getIdProjeto());

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					projeto.setNomeProjeto(rs.getString("nm_projeto"));
					projeto.setInicioProjeto(rs.getDate("dt_inicio_projeto"));
					projeto.setFimProjeto(rs.getDate("dt_fim_projeto"));
					projeto.setRelatorioParcial(rs.getString("relatorio_parcial"));
					projeto.setRelatorioFinal(rs.getString("relatorio_final"));
					projeto.setProcesso(rs.getInt("nr_processo"));
					projeto.setTipoProjeto(rs.getString("tp_projeto"));
					projeto.setEditalId(rs.getInt("edital_id"));
				}

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		} else {
			throw new ClasseInvalidaException();
		}

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