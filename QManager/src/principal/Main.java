package principal;

import dao.CursoDAO;
import dao.DiscenteDAO;
import dao.DocenteDAO;
import dao.InstituicaoDAO;
import entidades.ContaBancaria;
import entidades.Curso;
import entidades.Discente;
import entidades.Docente;
import entidades.Instituicao;
import entidades.Turma;
import entidades.Usuario;

public class Main {

	private static void creatTest(Banco banco) {

		// testar instituicao
		// --------------------------------------------------------------------
		Instituicao instituicao = new Instituicao("01352294000214",
				"Instituto Federal da Paraíba", "IFPB", 135476.96);

		InstituicaoDAO instituicaoDAO = new InstituicaoDAO(banco);

		instituicaoDAO.insert(instituicao);

		// testar docente
		// --------------------------------------------------------------------
		Usuario usuarioDocente = new Usuario("rhavy", "12345");
		ContaBancaria contaDocente = new ContaBancaria(null, "22315", "001",
				"123456");
		Docente docente = new Docente("Rhavy Maia Guedes", "1234578900",
				"23490912309", "Rua das Laranjeiras", "58123456", "33337777",
				"rhavymg@gmail.com", "Mestre", "Professor", "Campina Grande",
				usuarioDocente, contaDocente);

		DocenteDAO docenteDAO = new DocenteDAO(banco);

		docenteDAO.insert(docente);

		// testar curso
		// --------------------------------------------------------------------
		Curso curso = new Curso("Informática");

		CursoDAO cursoDAO = new CursoDAO(banco);

		cursoDAO.insert(curso);

		// testar discente
		// --------------------------------------------------------------------
		Usuario usuarioDiscente = new Usuario("arijonhson", "67890");
		ContaBancaria contaDiscente = new ContaBancaria(null, "44557", "001",
				"678901");
		Turma turma = new Turma(2014, "manhã", new Curso("Informática"));
		turma.setIdTurma(1);
		Discente discente = new Discente("Eri Jonhson Oliveira da Silva",
				"12345678900", "20111004013", "Rua das Palmeiras", "12345678",
				"55559900", "erijonhson.os@gmail.com", usuarioDiscente,
				contaDiscente, turma);

		DiscenteDAO discenteDAO = new DiscenteDAO(banco);

		discenteDAO.insert(discente);
	}

	private static void readById(Banco banco) {

		// testar intituicao
		// --------------------------------------------------------------------
		InstituicaoDAO instituicaoDAO = new InstituicaoDAO(banco);

		Instituicao instituicao = instituicaoDAO.getById(1);

		System.out.println(instituicao);

		// testar docente
		// --------------------------------------------------------------------
		DocenteDAO docenteDAO = new DocenteDAO(banco);

		Docente docente = docenteDAO.getById(1);

		System.out.println(docente);

		// testar discente
		// --------------------------------------------------------------------
		DiscenteDAO discenteDAO = new DiscenteDAO(banco);

		Discente discente = discenteDAO.getById(1);

		System.out.println(discente);

		// testar curso
		// --------------------------------------------------------------------
		CursoDAO cursoDAO = new CursoDAO(banco);

		Curso curso = cursoDAO.getById(1);

		System.out.println(curso);

	}

	public static void main(String[] args) {

		Banco banco = new Banco();

		banco.iniciarConexao("nutrif_user", "nutr1f_us3r");

		creatTest(banco);

		readById(banco);

		banco.encerrarConexao();

	}

}
