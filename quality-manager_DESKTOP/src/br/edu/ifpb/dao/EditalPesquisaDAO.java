package br.edu.ifpb.dao;

import java.sql.SQLException;

import br.edu.ifpb.Banco;
import br.edu.ifpb.entidades.EditalPesquisa;
import br.edu.ifpb.entidades.Entidade;
import br.edu.ifpb.exceções.ClasseInválidaException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

// Campos da tabela
// N_ano, início_inscrições, fim_inscrições, ano, prazo_relatório_parcial, prazo_relatório_final, número_vagas, valor_bolsa_docente, valor_bolsa_discente, PIP_ID

public class EditalPesquisaDAO implements DAO {
	
	public Connection connection;

	public EditalPesquisaDAO(Banco banco) {
		
		this.connection = (Connection) banco.getConnection();		
	}

	@Override
	public void creat(Entidade entidade) throws ClasseInválidaException {
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.EditalPesquisa")) {
			EditalPesquisa edital = (EditalPesquisa) entidade;
			
			// Cria um insert com os atributos e os valores sem definição, apenas
			// com a quantidade de valores a ser inseridos (representado por "?").
			String sql = "INSERT INTO edital_pesquisa (N_ano, início_inscrições, fim_inscrições,"
					+ " ano, prazo_relatório_parcial, prazo_relatório_final, número_vagas,"
					+ " valor_bolsa_docente, valor_bolsa_discente, PIP_ID)"
					+ " values (?,?,?,?,?,?,?,?,?,?)";

			try {
				// prepara para inserção
				PreparedStatement stmt = (PreparedStatement) connection
						.prepareStatement(sql);
				// seta os valores
				stmt.setString(1, edital.getNAno());
				stmt.setDate(2, edital.getInícioInscrições());
				stmt.setDate(3, edital.getFimInscrições());
				stmt.setString(4, edital.getAno());
				stmt.setDate(5, edital.getPrazoRelatórioParcial());
				stmt.setDate(6, edital.getPrazoRelatórioFinal());
				stmt.setInt(7, edital.getNúmeroVagas());
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
		else {
			throw new ClasseInválidaException();
		}
	}


	@Override
	public void read(Entidade entidade) throws ClasseInválidaException {
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.EditalPesquisa")) {
				
		}
		else {
			throw new ClasseInválidaException();
		}
	}


	@Override
	public void update(Entidade entidade) throws ClasseInválidaException {
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.EditalPesquisa")) {
			EditalPesquisa edital = (EditalPesquisa) entidade;
			
			String sql = "UPDATE edital_pesquisa SET início_inscrições=?, fim_inscrições=?, ano=?,"
					+ " prazo_relatório_parcial=?, prazo_relatório_final=?, número_vagas=?,"
					+ " valor_bolsa_docente=?, valor_bolsa_discente=?, PIE_ID=? WHERE N_ano=?";

			try {
				PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.setDate(1, edital.getInícioInscrições());
				stmt.setDate(2, edital.getFimInscrições());
				stmt.setString(3, edital.getAno());
				stmt.setDate(4, edital.getPrazoRelatórioParcial());
				stmt.setDate(5, edital.getPrazoRelatórioFinal());
				stmt.setInt(6, edital.getNúmeroVagas());
				stmt.setDouble(7, edital.getValorBolsaDocente());
				stmt.setDouble(8, edital.getValorBolsaDiscente());
				stmt.setInt(9, edital.getPIP_ID());

				stmt.execute();
				stmt.close();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		else {
			throw new ClasseInválidaException();
		}
	}


	@Override
	public void delete(Entidade entidade) throws ClasseInválidaException {
		if(entidade.getClass().equals("class br.edu.ifpb.entidades.EditalPesquisa")) {
			EditalPesquisa edital = (EditalPesquisa) entidade;
			
			String sql = "DELETE FROM edital_pesquisa WHERE N_ano=?";
			PreparedStatement stmt;
			try {
				stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.setString(1, edital.getNAno());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			throw new ClasseInválidaException();
		}
	}

}
