package dao;

import java.sql.SQLException;

import principal.Banco;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import entidades.EntidadeIF;
import entidades.Instituicao;
import excecoes.ClasseInvalidaException;

/*
 TABLE `instituicao` 
 `id_instituicao` INT NOT NULL AUTO_INCREMENT,
 `nr_cnpj` CHAR(14) NOT NULL,
 `nm_instituicao` VARCHAR(45) NOT NULL,
 `nm_sigla` VARCHAR(10) NOT NULL,
 `vl_orcamento` DOUBLE NOT NULL
 */

public class InstituicaoDAO implements DAO {

	// a conexão com o banco de dados
	public Connection connection;

	public InstituicaoDAO(Banco banco) {
		this.connection = (Connection) banco.getConnection();
	}

	public void creat(EntidadeIF entidade) throws ClasseInvalidaException {

		if (entidade instanceof Instituicao) {

			Instituicao instituicao = (Instituicao) entidade;

			try {

				// Define um insert com os atributos e cada valor é representado
				// por ?
				String sql = "INSERT INTO `instituicao` (`nr_cnpj`, `nm_instituicao`, `nm_sigla`, `vl_orcamento`)"
						+ " VALUES (?, ?, ?, ?)";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setString(1, instituicao.getCnpj());
				stmt.setString(2, instituicao.getInstituicao());
				stmt.setString(3, instituicao.getSigla());
				stmt.setDouble(4, instituicao.getOrcamento());

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

		if (entidade.getClass().getName().equals("Instituicao")) {

			Instituicao instituicao = (Instituicao) entidade;

			try {

				// Define update setando cada atributo e cada valor é
				// representado por ?
				String sql = "UPDATE `instituicao` SET `nr_cnpj`=?, `nm_instituicao`=?, `nm_sigla`=?, `vl_orcamento`=? "
						+ "WHERE `id_instituicao`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setString(1, instituicao.getInstituicao());
				stmt.setString(2, instituicao.getSigla());
				stmt.setDouble(3, instituicao.getOrcamento());
				stmt.setInt(4, instituicao.getIdInstituicao());

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

		if (entidade.getClass().getName().equals("Instituicao")) {

			Instituicao instituicao = (Instituicao) entidade;

			try {

				// Deleta uma tupla setando o atributo de identificação com
				// valor representado por ?
				String sql = "DELETE FROM `instituicao` WHERE `id_instituicao`=?";

				// prepared statement para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, instituicao.getIdInstituicao());

				// envia para o Banco e fecha o objeto
				stmt.execute();
				stmt.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}

}
