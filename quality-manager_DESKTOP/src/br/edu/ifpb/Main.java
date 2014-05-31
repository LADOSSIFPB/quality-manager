/**
 * 
 */
package br.edu.ifpb;

import java.sql.SQLException;

import br.edu.ifpb.dao.DocenteDAO;
import br.edu.ifpb.entidades.Docente;

/**
 * @author 
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		
		Banco b = new Banco();
		b.iniciarConexao("root", "ifpbinfo");
		
		//Discente d1 = new Discente("1234567900", "1111111111", "Sem Nome", "Nenhum", "Rua dos bosbos", "33333-333", "99999999", "semnome@nada.br");
		//objDAO.adiciona(d1); //Successful
		//objDAO.deletar(d1); //Successful o/
		
//		Discente d2 = new Discente("1234567900", "1111111111", "Lucrecia do nome lindo", "Nenhum", "Rua dos bosbos", "33333-333", "99999999", "semnome@nada.br");
//		
//		objDAO.alterar(d2);
		
		DocenteDAO objdao = new DocenteDAO(b);
		
		Docente doc = new Docente("10350625425", "1234", "EJOS o/", "Pesado", "Rua das Laranjeiras", "333333333", "99795879", "erijonhson.os@gmail.com", "PhP", "A&C");
		
		objdao.adiciona(doc);
		
		b.encerrarConexao();

	}

}
