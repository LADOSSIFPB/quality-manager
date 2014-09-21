package principal;

import java.sql.Date;
import java.util.List;

import br.edu.ifpb.qmanager.dao.CursoDAO;
import br.edu.ifpb.qmanager.dao.DatabaseConnection;
import br.edu.ifpb.qmanager.dao.DiscenteDAO;
import br.edu.ifpb.qmanager.dao.EditalDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoBancariaDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.dao.OrientadorDAO;
import br.edu.ifpb.qmanager.dao.ParticipacaoDAO;
import br.edu.ifpb.qmanager.dao.ProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.dao.ProjetoDAO;
import br.edu.ifpb.qmanager.dao.TurmaDAO;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.entidade.Partipacao;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

public class Main {

	static int ind = 2; // 1, 2, 3, 4, 5, 6, 7, ...
	static int ind_pes = 3; // 1, 3, 5, 7, 9, ...

	private static void insertTest(DatabaseConnection banco) {

		try {

			// testar instituicao
			// --------------------------------------------------------------------
			InstituicaoFinanciadora instituicao = new InstituicaoFinanciadora(
					"09876554321" + ind,
					"Universidade Federal de Campina Grande", "UFCG", 135476.96);
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
			int idProgramaInstitucional = programaInstitucionalDAO
					.insert(programaInstitucional);
			programaInstitucional
					.setIdProgramaInstitucional(idProgramaInstitucional);

			// testar Edital
			// ---------------------------------------------------------------------
			Edital edital = new Edital("C:/Users/Emanuel/Desktop/JSON.txt",
					15 + ind, 2013, Date.valueOf("2013-01-01"),
					Date.valueOf("2013-02-01"), Date.valueOf("2013-07-01"),
					Date.valueOf("2014-01-01"), 10, 100.0, 200.0, 'P',
					programaInstitucional);
			EditalDAO editalDAO = new EditalDAO(banco);
			edital.setIdEdital(editalDAO.insert(edital));

			// testar Projeto
			// ---------------------------------------------------------------------
			Projeto projeto = new Projeto(
					"Robocup",
					java.sql.Date.valueOf("2013-01-01"),
					java.sql.Date.valueOf("2014-01-01"),
					"C:\\Users\\Emanuel\\Documents\\IFPB\\ROBOCUP\\Guia_de_Instalação.pdf",
					"C:\\Users\\Emanuel\\Documents\\IFPB\\ROBOCUP\\Guia_de_Utilização.pdf",
					"C:\\Users\\Emanuel\\Documents\\IFPB\\ROBOCUP\\Guia_de_Finalização.pdf",
					"1234567890", 'P', 3000.0, edital);
			ProjetoDAO projetoDAO = new ProjetoDAO(banco);
			projeto.setIdProjeto(projetoDAO.insert(projeto));

			projeto = new Projeto(
					"Estudo de caso de Servidores em RESTEasy utilizando o aplicativo quality-manager_SERVICE",
					java.sql.Date.valueOf("2013-01-01"),
					java.sql.Date.valueOf("2014-01-01"),
					"/home/ejos/Documentos/QManager/Estudo de caso de Servidores em RESTEasy.pdf",
					"/home/ejos/Documentos/QManager/Estudo de caso de Servidores em RESTEasy - Parcial.pdf",
					"/home/ejos/Documentos/QManager/Estudo de caso de Servidores em RESTEasy - Final.pdf",
					"1234567890", 'P', 3000.0, edital);
			projeto.setIdProjeto(projetoDAO.insert(projeto));

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
			Orientador docente = new Orientador("Rhavy Maia Guedes", "12345789"
					+ ind_pes, "23490" + ind, "Rua das Laranjeiras",
					"58123456", "33337777", "rhavymg@gmail.com", "1234",
					new DadosBancarios(instituicaoBancaria, "031", "90876523"),
					"Mestre", "Professor", "Campina Grande");
			OrientadorDAO docenteDAO = new OrientadorDAO(banco);
			docente.setPessoaId(docenteDAO.insert(docente));

			// testar curso
			// --------------------------------------------------------------------
			Curso curso = new Curso("Informática" + ind);
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
					"1234567" + ind_pes, "201110040" + ind,
					"Rua das Palmeiras", "12345678", "55559900",
					"erijonhson.os@gmail.com", "0099", new DadosBancarios(
							instituicaoBancaria, "013", "66666666"), turma);
			DiscenteDAO discenteDAO = new DiscenteDAO(banco);
			discente.setPessoaId(discenteDAO.insert(discente));

			// testar participacao orientador
			// --------------------------------------------------------------------
			Partipacao participacaoOrientador = new Partipacao(projeto,
					docente, Date.valueOf("2014-07-01"),
					Date.valueOf("2015-07-01"), 200.0);
			ParticipacaoDAO participacaoOrientadorDAO = new ParticipacaoDAO(
					banco);
			int idParticipacaoOrientador = participacaoOrientadorDAO
					.insert(participacaoOrientador);
			participacaoOrientador.setIdParticipacao(idParticipacaoOrientador);

		} catch (QManagerSQLException qme) {
			System.err.println(qme.getMessage());
		}

	}

	private static void getAllTest(DatabaseConnection banco) {
		try {

			// testar instituicao
			// --------------------------------------------------------------------
			InstituicaoFinanciadoraDAO instituicaoDAO = new InstituicaoFinanciadoraDAO(
					banco);
			List<InstituicaoFinanciadora> instituicoesFinanciadoras = instituicaoDAO
					.getAll();
			for (int i = 0; i < instituicoesFinanciadoras.size(); i++) {
				System.out.println(instituicoesFinanciadoras.get(i));
			}

			// testar programa institucional
			// ---------------------------------------------------------------------
			ProgramaInstitucionalDAO programaInstitucionalDAO = new ProgramaInstitucionalDAO(
					banco);
			List<ProgramaInstitucional> programasInstitucionais = programaInstitucionalDAO
					.getAll();
			for (int i = 0; i < programasInstitucionais.size(); i++) {
				System.out.println(programasInstitucionais.get(i));
			}

			// testar Edital
			// ---------------------------------------------------------------------
			EditalDAO editalDAO = new EditalDAO(banco);
			List<Edital> editais = editalDAO.getAll();
			for (int i = 0; i < editais.size(); i++) {
				System.out.println(editais.get(i));
			}

			// testar Projeto
			// ---------------------------------------------------------------------
			ProjetoDAO projetoDAO = new ProjetoDAO(banco);
			List<Projeto> projetos = projetoDAO.getAll();
			for (int i = 0; i < projetos.size(); i++) {
				System.out.println(projetos.get(i));
			}

			// testar instituicao bancaria
			// --------------------------------------------------------------------
			InstituicaoBancariaDAO instituicaoBancariaDAO = new InstituicaoBancariaDAO(
					banco);
			List<InstituicaoBancaria> instituicoesBancarias = instituicaoBancariaDAO
					.getAll();
			for (int i = 0; i < instituicoesBancarias.size(); i++) {
				System.out.println(instituicoesBancarias.get(i));
			}

			// testar docente
			// --------------------------------------------------------------------
			OrientadorDAO docenteDAO = new OrientadorDAO(banco);
			List<Orientador> orientadores = docenteDAO.getAll();
			for (int i = 0; i < orientadores.size(); i++) {
				System.out.println(orientadores.get(i));
			}

			// testar curso
			// --------------------------------------------------------------------
			CursoDAO cursoDAO = new CursoDAO(banco);
			List<Curso> cursos = cursoDAO.getAll();
			for (int i = 0; i < cursos.size(); i++) {
				System.out.println(cursos.get(i));
			}

			// testar turma
			// --------------------------------------------------------------------
			TurmaDAO turmaDAO = new TurmaDAO(banco);
			List<Turma> turmas = turmaDAO.getAll();
			for (int i = 0; i < turmas.size(); i++) {
				System.out.println(turmas.get(i));
			}

			// testar discente
			// --------------------------------------------------------------------
			DiscenteDAO discenteDAO = new DiscenteDAO(banco);
			List<Discente> discentes = discenteDAO.getAll();
			for (int i = 0; i < discentes.size(); i++) {
				System.out.println(discentes.get(i));
			}

			// testar participacao orientador
			// --------------------------------------------------------------------
			ParticipacaoDAO participacaoOrientadorDAO = new ParticipacaoDAO(
					banco);
			List<Partipacao> participacoes = participacaoOrientadorDAO.getAll();
			for (int i = 0; i < participacoes.size(); i++) {
				System.out.println(participacoes.get(i));
			}

		} catch (QManagerSQLException qme) {
			System.err.println(qme.getMessage());
		}
	}

	private static void getByIdTest(DatabaseConnection banco) {

		try {
			// testar instituicao
			// --------------------------------------------------------------------
			InstituicaoFinanciadoraDAO instituicaoDAO = new InstituicaoFinanciadoraDAO(
					banco);
			InstituicaoFinanciadora instituicaoFinanciadora = instituicaoDAO
					.getById(ind);
			System.out.println(instituicaoFinanciadora);

			// testar programa institucional
			// ---------------------------------------------------------------------
			ProgramaInstitucionalDAO programaInstitucionalDAO = new ProgramaInstitucionalDAO(
					banco);
			ProgramaInstitucional programaInstitucional = programaInstitucionalDAO
					.getById(ind);
			System.out.println(programaInstitucional);

			// testar Edital
			// ---------------------------------------------------------------------
			EditalDAO editalDAO = new EditalDAO(banco);
			Edital edital = editalDAO.getById(ind);
			System.out.println(edital);

			// testar Projeto
			// ---------------------------------------------------------------------
			ProjetoDAO projetoDAO = new ProjetoDAO(banco);
			Projeto projeto = projetoDAO.getById(ind);
			System.out.println(projeto);

			// testar instituicao bancaria
			// --------------------------------------------------------------------
			InstituicaoBancariaDAO instituicaoBancariaDAO = new InstituicaoBancariaDAO(
					banco);
			InstituicaoBancaria instituicaoBancaria = instituicaoBancariaDAO
					.getById(ind);
			System.out.println(instituicaoBancaria);

			// testar docente
			// --------------------------------------------------------------------
			OrientadorDAO docenteDAO = new OrientadorDAO(banco);
			Orientador orientador = docenteDAO.getById(ind_pes);
			System.out.println(orientador);

			// testar curso
			// --------------------------------------------------------------------
			CursoDAO cursoDAO = new CursoDAO(banco);
			Curso curso = cursoDAO.getById(ind);
			System.out.println(curso);

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

			// testar participacao orientador
			// --------------------------------------------------------------------
			ParticipacaoDAO participacaoOrientadorDAO = new ParticipacaoDAO(
					banco);
			Partipacao participacao = participacaoOrientadorDAO.getById(ind);
			System.out.println(participacao);

		} catch (QManagerSQLException qme) {
			System.err.println(qme.getMessage());
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
		} catch (QManagerSQLException qme) {
			qme.printStackTrace();
		}

		try {

			Edital edital = new Edital();
			edital.setIdEdital(1);

			// testar Projeto
			// ---------------------------------------------------------------------

			Projeto projeto = new Projeto(
					"Robocup",
					java.sql.Date.valueOf("2013-01-01"),
					java.sql.Date.valueOf("2014-01-01"),
					"C:\\Users\\Emanuel\\Documents\\IFPB\\ROBOCUP\\Guia_de_Instalação.pdf",
					"C:\\Users\\Emanuel\\Documents\\IFPB\\ROBOCUP\\Guia_de_Utilização.pdf",
					"C:\\Users\\Emanuel\\Documents\\IFPB\\ROBOCUP\\Guia_de_Finalização.pdf",
					"1234567890", 'P', 3000.0, edital);
			ProjetoDAO projetoDAO = new ProjetoDAO(banco);
			projeto.setIdProjeto(projetoDAO.insert(projeto));

			projeto = new Projeto(
					"Estudo de caso de Servidores em RESTEasy utilizando o aplicativo quality-manager_SERVICE",
					java.sql.Date.valueOf("2013-01-01"),
					java.sql.Date.valueOf("2014-01-01"),
					"/home/ejos/Documentos/QManager/Estudo de caso de Servidores em RESTEasy.pdf",
					"/home/ejos/Documentos/QManager/Estudo de caso de Servidores em RESTEasy - Parcial.pdf",
					"/home/ejos/Documentos/QManager/Estudo de caso de Servidores em RESTEasy - Final.pdf",
					"1234567890", 'P', 3000.0, edital);
			projeto.setIdProjeto(projetoDAO.insert(projeto));

		} catch (QManagerSQLException e) {
			e.printStackTrace();
		}

		/*
		 * insertTest(banco); // funcionando com sucesso!!
		 * 
		 * getByIdTest(banco); //funcionando com sucesso!!
		 * 
		 * getAllTest(banco); //funcionando com sucesso!!
		 */
		// deleteTest(banco);

		banco.encerrarConexao();

	}
}
