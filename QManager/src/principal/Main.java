package principal;

import br.edu.ifpb.qmanager.dao.CursoDAO;
import br.edu.ifpb.qmanager.dao.DiscenteDAO;
import br.edu.ifpb.qmanager.dao.DocenteDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoDAO;
import br.edu.ifpb.qmanager.entidade.ContaBancaria;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Docente;
import br.edu.ifpb.qmanager.entidade.Instituicao;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.entidade.Usuario;

public class Main {

	private static void insertTest(Banco banco) {

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
				"12345678901", "20111004014", "Rua das Palmeiras", "12345678",
				"55559900", "erijonhson.os@gmail.com", usuarioDiscente,
				contaDiscente, turma);

		DiscenteDAO discenteDAO = new DiscenteDAO(banco);

		discenteDAO.insert(discente);
	}

	private static void getByIdTest(Banco banco) {

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

		Discente discente = discenteDAO.getById(3);

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

		//insertTest(banco);
		Usuario usuarioDiscente = new Usuario("arijonhson", "67890");
		ContaBancaria contaDiscente = new ContaBancaria(null, "44557", "001",
				"678901");
		Turma turma = new Turma(2014, "manhã", new Curso("Informática"));
		
		Discente discente = new Discente("Eri Jonhson Oliveira da Silva",
				"12345678901", "20111004014", "Rua das Palmeiras", "12345678",
				"55559900", "erijonhson.os@gmail.com", usuarioDiscente,
				contaDiscente, turma);

		DiscenteDAO discenteDAO = new DiscenteDAO(banco);

		discenteDAO.insert(discente);
		
		getByIdTest(banco);

		banco.encerrarConexao();
	}

}
