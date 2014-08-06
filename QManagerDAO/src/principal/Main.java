package principal;

import dao.CursoDAO;
import dao.DiscenteDAO;
import dao.DocenteDAO;
import dao.ParticipacaoDAO;
import dao.TurmaDAO;
import entidades.Curso;
import entidades.Discente;
import entidades.Docente;
import entidades.Participacao;
import entidades.Turma;

public class Main {

	private static void creatTest(Banco banco) {

		// testar instituicao
		// --------------------------------------------------------------------

		/*Instituicao instituicao = new Instituicao("91352294000212",
				"Instituto Federal da Paraíba", "IFPB", 135476.96);

		InstituicaoDAO instituicaoDAO = new InstituicaoDAO(banco);

		instituicaoDAO.creat(instituicao);*/

		// testar programa_institucional
		// --------------------------------------------------------------------

		/*ProgramaInstitucional programaInstitucional = new ProgramaInstitucional("Programa Institucional de Bolsas", "PIB", 1);

		ProgramaInstitucionalDAO programaInstitucionalDAO = new ProgramaInstitucionalDAO(
				banco);

		programaInstitucionalDAO.creat(programaInstitucional);*/

		// testar edital
		// --------------------------------------------------------------------

		/*Edital edital = new Edital("25/20", "2014-8-11", "2015-8-11",
				"2015-2-11", "2015-8-11", 20, 10, 10, "E", 1);

		EditalDAO editalDAO = new EditalDAO(banco);

		editalDAO.creat(edital);*/

		// testar projeto
		// --------------------------------------------------------------------

		/*Projeto projeto = new Projeto("Robocup", "2013-5-26", "2014-9-12",
				"2013-10-26", "2014-9-20", 123, "P", 1);

		ProjetoDAO projetoDAO = new ProjetoDAO(banco);

		projetoDAO.creat(projeto);*/
		
		// testar instituicao_bancaria
		// --------------------------------------------------------------------
		
		/*InstituicaoBancaria instituicaoBancaria = new InstituicaoBancaria("Caixa Econômica Federal", "1234");
		
		InstituicaoBancariaDAO instituicaoBancariaDAO = new InstituicaoBancariaDAO(banco);
		
		instituicaoBancariaDAO.creat(instituicaoBancaria);
		
		instituicaoBancaria = new InstituicaoBancaria("Banco do Brasil", "7896");
		
		instituicaoBancariaDAO.creat(instituicaoBancaria);*/
		
		// testar docente
		// --------------------------------------------------------------------

		Docente docente = new Docente("Rhavy Maia Guedes", "1234578900",
				"23490912309", "Rua das Laranjeiras", "58123456", "33337777",
				"rhavymg@eu.com", 1, "5", "123", "PhD", "Professor Dedicação Exclusiva",
				"Lab. de Redes Convergentes");

		DocenteDAO docenteDAO = new DocenteDAO(banco);

		docenteDAO.creat(docente);

		// testar curso
		// --------------------------------------------------------------------

		Curso curso = new Curso("Informática");

		CursoDAO cursoDAO = new CursoDAO(banco);

		cursoDAO.creat(curso);

		// testar turma
		// --------------------------------------------------------------------

		Turma turma = new Turma(4, "M", 1);

		TurmaDAO turmaDAO = new TurmaDAO(banco);

		turmaDAO.creat(turma);

		// testar discente
		// --------------------------------------------------------------------

		Discente discente = new Discente("Eri Jonhson Oliveira da Silva", "12345678900", "20111004013", "Rua das Palmeiras", "12345678", "55559900", "erijonhson.os@eu.com", 1, "13", "458909", 1);

		DiscenteDAO discenteDAO = new DiscenteDAO(banco);

		discenteDAO.creat(discente);
		
		// testar participacao
		// --------------------------------------------------------------------
		
		Participacao participacao = new Participacao(1, 1, "2014-8-11", "2015-8-11", 1);
		
		ParticipacaoDAO participacaoDAO = new ParticipacaoDAO(banco);
		
		participacaoDAO.creat(participacao);
		
	}

	public static void main(String[] args) {

		Banco banco = new Banco();

		banco.iniciarConexao("root", "ifpbinfo");

		creatTest(banco);

		banco.encerrarConexao();

	}

}