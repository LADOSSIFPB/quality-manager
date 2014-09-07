package principal;

import java.util.Date;

import br.edu.ifpb.qmanager.dao.CursoDAO;
import br.edu.ifpb.qmanager.dao.DadosBancariosDAO;
import br.edu.ifpb.qmanager.dao.DatabaseConnection;
import br.edu.ifpb.qmanager.dao.DiscenteDAO;
import br.edu.ifpb.qmanager.dao.EditalDAO;
import br.edu.ifpb.qmanager.dao.OrientadorDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoBancariaDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.dao.ParticipacaoDiscenteDAO;
import br.edu.ifpb.qmanager.dao.ParticipacaoOrientadorDAO;
import br.edu.ifpb.qmanager.dao.ProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.dao.ProjetoDAO;
import br.edu.ifpb.qmanager.dao.TurmaDAO;
import br.edu.ifpb.qmanager.dao.UsuarioDAO;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.entidade.Usuario;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;
import br.edu.ifpb.qmanager.excecao.SelectVazioException;

public class Main {

	static int ind = 1; // 1, 2, 3, 4, 5, 6, 7, ...
	static int ind_pes = 1; // 1, 3, 5, 7, 9, ...

	private static void insertTest(DatabaseConnection banco) {

		try {

			// testar instituicao
			// --------------------------------------------------------------------
			InstituicaoFinanciadora instituicao = new InstituicaoFinanciadora(
					"09876554321", "Universidade Federal de Campina Grande",
					"UFCG", 135476.96);
			InstituicaoFinanciadoraDAO instituicaoDAO = new InstituicaoFinanciadoraDAO(
					banco);
			instituicao.setIdInstituicaoFinanciadora(instituicaoDAO
					.insert(instituicao));

			// testar programa institucional
			// ---------------------------------------------------------------------
			ProgramaInstitucional programaInstitucional = new ProgramaInstitucional(
					"PIBIC-CT", "PIB", 490.0, instituicao);
			ProgramaInstitucionalDAO programaInstitucionalDAO = new ProgramaInstitucionalDAO(
					banco);
			programaInstitucional
					.setIdProgramaInstitucional(programaInstitucionalDAO
							.insert(programaInstitucional));

			// testar Edital
			// ---------------------------------------------------------------------
			Edital edital = new Edital("C:/Users/Emanuel/Desktop/JSON.txt", 15,
					2013, "2013-01-01", "2013-02-01", "2013-07-01",
					"2014-01-01", 10, 100.0, 200.0, 'P', programaInstitucional);
			EditalDAO editalDAO = new EditalDAO(banco);
			edital.setIdEdital(editalDAO.insert(edital));

			// testar Projeto
			// ---------------------------------------------------------------------
			Projeto projeto = new Projeto(
					"Robocup",
					"2013-01-01",
					"2014-01-01",
					"C:\\Users\\Emanuel\\Documents\\IFPB\\ROBOCUP\\Guia_de_Instalação.pdf",
					"C:\\Users\\Emanuel\\Documents\\IFPB\\ROBOCUP\\Guia_de_Utilização.pdf",
					"C:\\Users\\Emanuel\\Documents\\IFPB\\ROBOCUP\\Guia_de_Finalização.pdf",
					"1234567890", 'P', 3000.0, edital);
			projeto.setIdProjeto(1);
			ProjetoDAO projetoDAO = new ProjetoDAO(banco);
			projeto.setIdProjeto(projetoDAO.insert(projeto));

			// testar docente
			// --------------------------------------------------------------------
			Orientador docente = new Orientador("Rhavy Maia Guedes",
					"12345789", "234909123", "Rua das Laranjeiras", "58123456",
					"33337777", "rhavymg@gmail.com", "Mestre", "Professor",
					"Campina Grande");
			OrientadorDAO docenteDAO = new OrientadorDAO(banco);
			docente.setPessoaId(docenteDAO.insert(docente));

			// testar instituicao bancaria
			// --------------------------------------------------------------------
			InstituicaoBancaria instituicaoBancaria = new InstituicaoBancaria(
					"Banco do Brasil");
			InstituicaoBancariaDAO instituicaoBancariaDAO = new InstituicaoBancariaDAO(
					banco);
			instituicaoBancaria.setIdInstituicaoBancaria(instituicaoBancariaDAO
					.insert(instituicaoBancaria));

			// testar dados bancarios
			// --------------------------------------------------------------------
			DadosBancarios dadosBancariosOrientador = new DadosBancarios(
					instituicaoBancaria, "031", "90876523", docente);
			DadosBancariosDAO dadosBancariosOrientadorDAO = new DadosBancariosDAO(
					banco);
			dadosBancariosOrientadorDAO.insert(dadosBancariosOrientador);

			// testar usuario
			// --------------------------------------------------------------------
			Usuario usuarioOrientador = new Usuario("rhavy", "12345", docente);
			UsuarioDAO usuarioOrientadorDAO = new UsuarioDAO(banco);
			usuarioOrientador.setIdUsuario(usuarioOrientadorDAO
					.insert(usuarioOrientador));

			// testar curso
			// --------------------------------------------------------------------
			Curso curso = new Curso("Informática");
			CursoDAO cursoDAO = new CursoDAO(banco);
			curso.setIdCurso(cursoDAO.insert(curso));

			// testar turma
			// --------------------------------------------------------------------
			Turma turma = new Turma(4, 'M', curso);
			TurmaDAO turmaDAO = new TurmaDAO(banco);
			turma.setIdTurma(turmaDAO.insert(turma));

			// testar discente
			// --------------------------------------------------------------------
			Discente discente = new Discente("Eri Jonhson Oliveira da Silva",
					"123456789", "201110040", "Rua das Palmeiras", "12345678",
					"55559900", "erijonhson.os@gmail.com", turma);
			DiscenteDAO discenteDAO = new DiscenteDAO(banco);
			discente.setPessoaId(discenteDAO.insert(discente));

			// testar dados bancarios
			// --------------------------------------------------------------------
			DadosBancarios dadosBancariosDiscente = new DadosBancarios(
					instituicaoBancaria, "013", "66666666", discente);
			DadosBancariosDAO dadosBancariosDiscenteDAO = new DadosBancariosDAO(
					banco);
			dadosBancariosDiscenteDAO.insert(dadosBancariosDiscente);

			// testar usuario
			// --------------------------------------------------------------------
			Usuario usuarioDiscente = new Usuario("johw", "54321", discente);
			UsuarioDAO usuarioDiscenteDAO = new UsuarioDAO(banco);
			usuarioDiscente.setIdUsuario(usuarioDiscenteDAO
					.insert(usuarioDiscente));

			// testar participacao orientador
			// --------------------------------------------------------------------
			Participacao<Orientador> participacaoOrientador = new Participacao<Orientador>(
					docente, projeto, "2014-07-01", "2015-07-01", 200.0);
			ParticipacaoOrientadorDAO participacaoOrientadorDAO = new ParticipacaoOrientadorDAO(
					banco);
			participacaoOrientador.setIdParticipacao(participacaoOrientadorDAO
					.insert(participacaoOrientador));

			// testar participacao discente
			// --------------------------------------------------------------------
			Participacao<Discente> participacaoDiscente = new Participacao<Discente>(
					discente, projeto, "2014-07-01", "2015-07-01", 0.0);
			ParticipacaoDiscenteDAO participacaoDiscenteDAO = new ParticipacaoDiscenteDAO(
					banco);
			participacaoDiscente.setIdParticipacao(participacaoDiscenteDAO
					.insert(participacaoDiscente));

		} catch (QManagerSQLException qme) {
			System.err.println(qme.getMessage());
		}

	}

	private static void getByIdTest(DatabaseConnection banco) {

		try {
			// testar intituicao
			// --------------------------------------------------------------------
			InstituicaoFinanciadoraDAO instituicaoDAO = new InstituicaoFinanciadoraDAO(
					banco);
			InstituicaoFinanciadora instituicao = instituicaoDAO.getById(ind);
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
			OrientadorDAO docenteDAO = new OrientadorDAO(banco);
			Orientador docente = docenteDAO.getById(ind_pes);
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

			OrientadorDAO docenteDAO = new OrientadorDAO(banco);
			docenteDAO.delete(ind_pes);
			System.out.println("Docente deletado com sucesso!");

			CursoDAO cursoDAO = new CursoDAO(banco);
			cursoDAO.delete(ind);
			System.out.println("Curso deletado com sucesso!");

			InstituicaoBancariaDAO instituicaoBancariaDAO = new InstituicaoBancariaDAO(
					banco);
			instituicaoBancariaDAO.delete(ind);
			System.out.println("Instituição Bancária deletada com sucesso!");

			InstituicaoFinanciadoraDAO instituicaoDAO = new InstituicaoFinanciadoraDAO(
					banco);
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

		// getByIdTest(banco);

		// deleteTest(banco);

		banco.encerrarConexao();
	}

}
