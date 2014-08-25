package br.edu.ifpb.qmanager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import principal.Banco;
import br.edu.ifpb.qmanager.entidade.EntidadeIF;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.excecao.ClasseInvalidaException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/*
 TABLE `instituicao_bancaria` (
 `id_instituicao_bancaria` INT NOT NULL AUTO_INCREMENT,
 `nm_banco` VARCHAR(45) NOT NULL,
 `nr_agencia` VARCHAR(6) NOT NULL
 */
//TODO: implements DAO
public class InstituicaoBancariaDAO {

	// a conexão com o banco de dados
	public Connection connection;

	public InstituicaoBancariaDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}

	public void insert(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof InstituicaoBancaria) {

			InstituicaoBancaria instituicaoBancaria = (InstituicaoBancaria) entidade;

			try {

				// Define um insert com os atributos e cada valor é representado
				// por ?
				String sql = "INSERT INTO `instituicao_bancaria` (`nm_banco`)"
						+ "VALUES (?)";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setString(1, instituicaoBancaria.getNomeBanco());

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

		if (entidade instanceof InstituicaoBancaria) {

			InstituicaoBancaria instituicaoBancaria = (InstituicaoBancaria) entidade;

			try {

				String sql = String
						.format("%s %d",
								"SELECT * FROM `instituicao_bancaria` WHERE `id_instituicao_bancaria` =",
								instituicaoBancaria.getIdInstituicaoBancaria());

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					instituicaoBancaria.setNomeBanco(rs.getString("nm_banco"));
				}

			} catch (SQLException sqle) {
				throw new RuntimeException(sqle);
			}

		} else {
			throw new ClasseInvalidaException();
		}

	}

	public void update(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof InstituicaoBancaria) {

			InstituicaoBancaria instituicaoBancaria = (InstituicaoBancaria) entidade;

			try {

				// Define update setando cada atributo e cada valor é
				// representado por ?
				String sql = "UPDATE `instituicao_bancaria` SET `nm_banco`=?"
						+ " WHERE `id_instituicao_bancaria`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setString(1, instituicaoBancaria.getNomeBanco());
				stmt.setInt(2, instituicaoBancaria.getIdInstituicaoBancaria());

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

		if (entidade instanceof InstituicaoBancaria) {

			InstituicaoBancaria instituicaoBancaria = (InstituicaoBancaria) entidade;

			try {

				// Deleta uma tupla setando o atributo de identificação com
				// valor representado por ?
				String sql = "DELETE FROM `instituicao_bancaria` WHERE `id_instituicao_bancaria`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, instituicaoBancaria.getIdInstituicaoBancaria());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();

			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}
