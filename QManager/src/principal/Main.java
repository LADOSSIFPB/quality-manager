package principal;

import br.edu.ifpb.qmanager.dao.CursoDAO;
import br.edu.ifpb.qmanager.dao.DatabaseConnection;
import br.edu.ifpb.qmanager.dao.DiscenteDAO;
import br.edu.ifpb.qmanager.dao.DocenteDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoBancariaDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoDAO;
import br.edu.ifpb.qmanager.dao.TurmaDAO;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Docente;
import br.edu.ifpb.qmanager.entidade.Instituicao;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.entidade.Usuario;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;
import br.edu.ifpb.qmanager.excecao.SelectVazioException;

public class Main {

	static int ind = 2; // 1, 2, 3, 4, 5, 6, 7, ...
	static int ind_pes = 3; // 1, 3, 5, 7, 9, ...

	private static void insertTest(DatabaseConnection banco) {

		try {

			// testar instituicao
			// --------------------------------------------------------------------
			Instituicao instituicao = new Instituicao("013522940000" + ind,
					"Instituto Federal da Paraíba", "IFPB", 135476.96);
			InstituicaoDAO instituicaoDAO = new InstituicaoDAO(banco);
			instituicao.setIdInstituicao(instituicaoDAO.insert(instituicao));

			// testar instituicao bancaria
			// --------------------------------------------------------------------
			InstituicaoBancaria instituicaoBancaria = new InstituicaoBancaria(
					"Banco do Brasil");
			InstituicaoBancariaDAO instituicaoBancariaDAO = new InstituicaoBancariaDAO(
					banco);
			instituicaoBancaria.setIdInstituicaoBancaria(instituicaoBancariaDAO
					.insert(instituicaoBancaria));

			// testar docente
			// --------------------------------------------------------------------
			Docente docente = new Docente("Rhavy Maia Guedes",
					"12345789" + ind, "234909123" + ind, "Rua das Laranjeiras",
					"58123456", "33337777", "rhavymg@gmail.com", "Mestre",
					"Professor", "Campina Grande",
					new Usuario("rhavy", "12345"), new DadosBancarios(
							instituicaoBancaria, "22315", "001", "123456"));
			DocenteDAO docenteDAO = new DocenteDAO(banco);
			docente.setPessoaId(docenteDAO.insert(docente));

			// testar curso
			// --------------------------------------------------------------------
			Curso curso = new Curso("Informática " + ind);
			CursoDAO cursoDAO = new CursoDAO(banco);
			curso.setIdCurso(cursoDAO.insert(curso));

			// testar turma
			// --------------------------------------------------------------------
			Turma turma = new Turma(2014, "M", curso);
			TurmaDAO turmaDAO = new TurmaDAO(banco);
			turma.setIdTurma(turmaDAO.insert(turma));

			// testar discente
			// --------------------------------------------------------------------

			Discente discente = new Discente("Eri Jonhson Oliveira da Silva",
					"123456789" + ind, "201110040" + ind, "Rua das Palmeiras",
					"12345678", "55559900", "erijonhson.os@gmail.com",
					new Usuario("Erijonhson", "67890"), new DadosBancarios(
							instituicaoBancaria, "44557", "001", "678901"),
					turma);

			DiscenteDAO discenteDAO = new DiscenteDAO(banco);
			discente.setPessoaId(discenteDAO.insert(discente));

		} catch (QManagerSQLException qme) {
			System.err.println(qme.getMessage());
		}

	}

	private static void getByIdTest(DatabaseConnection banco) {

		try {
			// testar intituicao
			// --------------------------------------------------------------------
			InstituicaoDAO instituicaoDAO = new InstituicaoDAO(banco);
			Instituicao instituicao = instituicaoDAO.getById(ind);
			System.out.println(instituicao);

			// testar instituicao bancaria
			// --------------------------------------------------------------------
			InstituicaoBancariaDAO instituicaoBancariaDAO = new InstituicaoBancariaDAO(
					banco);
			InstituicaoBancaria instituicaoBancaria = instituicaoBancariaDAO
					.getById(ind);
			System.out.println(instituicaoBancaria);

			// testar curso
			// --------------------------------------------------------------------
			CursoDAO cursoDAO = new CursoDAO(banco);
			Curso curso = cursoDAO.getById(ind);
			System.out.println(curso);

			// testar docente
			// --------------------------------------------------------------------
			DocenteDAO docenteDAO = new DocenteDAO(banco);
			Docente docente = docenteDAO.getById(ind_pes);
			System.out.println(docente);

			// testar turma
			// --------------------------------------------------------------------
			TurmaDAO turmaDAO = new TurmaDAO(banco);
			Turma turma = turmaDAO.getById(ind);
			System.out.println(turma);

			// testar discente
			// --------------------------------------------------------------------
			DiscenteDAO discenteDAO = new DiscenteDAO(banco);
			Discente discente = discenteDAO.getById(ind_pes + 1);
			System.out.println(discente);

		} catch (QManagerSQLException qme) {
			System.err.println(qme.getMessage());
		} catch (SelectVazioException sve) {
			System.err.println(sve.getMessage());
		}

	}

	private static void deleteTest(DatabaseConnection banco) {

		try {

			DiscenteDAO discenteDAO = new DiscenteDAO(banco);
			discenteDAO.delete(ind_pes + 1);
			System.out.println("Discente deletado com sucesso!");

			TurmaDAO turmaDAO = new TurmaDAO(banco);
			turmaDAO.delete(ind);
			System.out.println("Turma deletada com sucesso!");

			DocenteDAO docenteDAO = new DocenteDAO(banco);
			docenteDAO.delete(ind_pes);
			System.out.println("Docente deletado com sucesso!");

			CursoDAO cursoDAO = new CursoDAO(banco);
			cursoDAO.delete(ind);
			System.out.println("Curso deletado com sucesso!");

			InstituicaoBancariaDAO instituicaoBancariaDAO = new InstituicaoBancariaDAO(
					banco);
			instituicaoBancariaDAO.delete(ind);
			System.out.println("Instituição Bancária deletada com sucesso!");

			InstituicaoDAO instituicaoDAO = new InstituicaoDAO(banco);
			instituicaoDAO.delete(ind);
			System.out.println("Instituição deletada com sucesso!");

		} catch (QManagerSQLException qme) {
			System.err.println(qme.getMessage());
			qme.printStackTrace();
		}

	}

	public static void main(String[] args) {

		DatabaseConnection banco = new DatabaseConnection();

		try {
			banco.iniciarConexao();
		} catch (QManagerSQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		insertTest(banco);

		getByIdTest(banco);

		deleteTest(banco);

		banco.encerrarConexao();
	}

}
