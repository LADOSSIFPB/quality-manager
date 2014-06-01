package br.edu.ifpb.dao;

import java.sql.SQLException;

import br.edu.ifpb.Banco;
import br.edu.ifpb.entidades.EditalPesquisa;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/*								Dados da tabela
	N_ANO, Inicio_das_inscrições, Fim_das_escrições, Ano, Prazo_do_relatório_parcial,
	Prazo_do_relatório_final, Número_de_vagas, Valor_bolsa_docente, Valor_bolsa_discente,
	PIE_Id_Programa 
*/

public class EditalPesquisaDAO {
	
	public Connection connection;

	public EditalPesquisaDAO(Banco banco) {
		
		this.connection = (Connection) banco.getConnection();		
	}
	
	
	// Adicinando um novo Edital de Extenção ao banco
	public void adiciona(EditalPesquisa edital) {

		// Cria um insert, com os atributos, e os valores sem definição, apenas
		// com a quantidade de valores a ser inseridos (representado por "?").
		String sql = "INSERT INTO editalpesquisa (N_ano, ínicioinscrições, fiminscrições,"
				+ " ano, prazorelatórioparcial, prazorelatóriofinal, númerovagas,"
				+ " valorbolsadocente, valorbolsadiscente, PIP_ID)"
				+ " values (?,?,?,?,?,?,?,?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) connection
					.prepareStatement(sql);
			// seta os valores
			stmt.setString(1, edital.getN_ano());
			stmt.setDate(2, edital.getÍnicioInscrições());
			stmt.setDate(3, edital.getFimInscrições());
			stmt.setString(4, edital.getAno());
			stmt.setDate(5, edital.getPrazoRelatórioParcial());
			stmt.setDate(6, edital.getPrazoRelatorioFinal());
			stmt.setInt(7, edital.getNúmeroDeVagas());
			stmt.setDouble(8, edital.getValorBolsaDocente());
			stmt.setDouble(9, edital.getValorBolsaDiscente());
			stmt.setInt(10, edital.getPIP_ID());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Alterar dados do discente, a partir do CPF(chave primária).
	public void alterar(EditalPesquisa edital) {

		String sql = "UPDATE editalpesquisa SET inicioinscrições=?, fiminscrições=?, ano=?,"
				+ " prazorelatórioparcial=?, prazorelatóriofinal=?, númerovagas=?,"
				+ " valorbolsadocente=?, valorbolsadiscente=?, PIE_ID=? WHERE N_ano=?";

		try {
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setDate(1, edital.getÍnicioInscrições());
			stmt.setDate(2, edital.getFimInscrições());
			stmt.setString(3, edital.getAno());
			stmt.setDate(4, edital.getPrazoRelatórioParcial());
			stmt.setDate(5, edital.getPrazoRelatorioFinal());
			stmt.setInt(6, edital.getNúmeroDeVagas());
			stmt.setDouble(7, edital.getValorBolsaDocente());
			stmt.setDouble(8, edital.getValorBolsaDiscente());
			stmt.setInt(9, edital.getPIP_ID());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
		
		// Deletar discente a partir do CPF(chave primária)
		public void deletar(EditalPesquisa edital) {
			String sql = "DELETE FROM editalpesquisa WHERE N_ano=?";
			PreparedStatement stmt;
			try {
				stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.setString(1, edital.getN_ano());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	
	

}
