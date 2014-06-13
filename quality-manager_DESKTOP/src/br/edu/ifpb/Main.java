/**
 * 
 */
package br.edu.ifpb;

import java.sql.SQLException;
import java.text.ParseException;

import br.edu.ifpb.dao.EditalExtensãoDAO;
import br.edu.ifpb.dao.InstituiçãoDAO;
import br.edu.ifpb.dao.ProgramaInstitucionalExtensãoDAO;
import br.edu.ifpb.entidades.EditalExtensão;
import br.edu.ifpb.entidades.Instituição;
import br.edu.ifpb.entidades.ProgramaInstitucionalExtensão;
import br.edu.ifpb.exceções.ClasseInválidaException;

/**
 * @author 
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws SQLException, ParseException {
		
		
		Banco b = new Banco();
		b.iniciarConexao("root", "ifpbinfo");
		
		Instituição inst = new Instituição("Conselho Nacional de Desenvolvimento Científico e Tecnológico", "CNPQ", 1000);
		InstituiçãoDAO daoInst = new InstituiçãoDAO(b);
		try {
			daoInst.creat(inst);
		} catch (ClasseInválidaException e) {
			System.out.println(e.getMessage());
		}
		
		
		ProgramaInstitucionalExtensão pie = new ProgramaInstitucionalExtensão("PIBIC",
				"Programa Institucional de Bolsas de Iniciação Científica", 7);
		ProgramaInstitucionalExtensãoDAO daoPIE = new ProgramaInstitucionalExtensãoDAO(b);
		try {
			daoPIE.creat(pie);
		} catch (ClasseInválidaException e1) {
			System.out.println(e1.getMessage());
		}
		
		
		EditalExtensãoDAO daoEdt = new EditalExtensãoDAO(b);
		EditalExtensão edt = new EditalExtensão("2011.1", "12/03/2011", "25/03/2011",
				"2011", "20/09/2011", "20/02/2012", 10, 500, 100, 4);
		try {
			daoEdt.creat(edt);
		} catch (ClasseInválidaException e2) {
			System.out.println(e2.getMessage());
		}
		
		b.encerrarConexao();

	}

}
