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
		daoInst.adiciona(inst);
		
		
		ProgramaInstitucionalExtensão pie = new ProgramaInstitucionalExtensão("PIBIC",
				"Programa Institucional de Bolsas de Iniciação Científica", 7);
		ProgramaInstitucionalExtensãoDAO daoPIE = new ProgramaInstitucionalExtensãoDAO(b);
		daoPIE.adiciona(pie);
		
		
		EditalExtensãoDAO daoEdt = new EditalExtensãoDAO(b);
		EditalExtensão edt = new EditalExtensão("2011.1", "12/03/2011", "25/03/2011",
				"2011", "20/09/2011", "20/02/2012", 10, 500, 100, 4);
		daoEdt.adiciona(edt);
		
		b.encerrarConexao();

	}

}
