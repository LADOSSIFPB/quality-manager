package br.edu.ifpb.qmanager.service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.edu.ifpb.qmanager.dao.CursoDAO;
import br.edu.ifpb.qmanager.dao.DadosBancariosDAO;
import br.edu.ifpb.qmanager.dao.DatabaseConnection;
import br.edu.ifpb.qmanager.dao.DiscenteDAO;
import br.edu.ifpb.qmanager.dao.EditalDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoBancariaDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.dao.OrientadorDAO;
import br.edu.ifpb.qmanager.dao.ParticipacaoDiscenteDAO;
import br.edu.ifpb.qmanager.dao.ParticipacaoOrientadorDAO;
import br.edu.ifpb.qmanager.dao.ProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.dao.ProjetoDAO;
import br.edu.ifpb.qmanager.dao.TurmaDAO;
import br.edu.ifpb.qmanager.dao.UsuarioDAO;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Individuo;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.QManagerErro;
import br.edu.ifpb.qmanager.entidade.Server;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

/**
 * Descrição do método e serviço.
 * 
 * @author Eri Jonhson
 * @author Emanuel Guimarães
 * @param instituicao
 * @return
 */

@Path("service")
public class QManagerService {

	@POST
	@Path("/cadastrarInstituicao")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarInstituicao(InstituicaoFinanciadora instituicao) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		if (validarInsitituicao(instituicao)) {

			try {

				banco.iniciarConexao();

				InstituicaoFinanciadoraDAO instituicaoDAO = new InstituicaoFinanciadoraDAO(
						banco);
				int idInstituicao = instituicaoDAO.insert(instituicao);
				instituicao.setIdInstituicaoFinanciadora(idInstituicao);

				builder.status(Response.Status.OK);
				builder.entity(instituicao);

			} catch (QManagerSQLException qme) {

				QManagerErro erro = new QManagerErro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		}

		return builder.build();
	}

	private boolean validarInsitituicao(InstituicaoFinanciadora instituicao) {
		// TODO Auto-generated method stub
		return true;
	}

	@POST
	@Path("/cadastrarPI")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarPI(ProgramaInstitucional programaInstitucional) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		if (validarProgramaInsitituicao(programaInstitucional)) {

			try {

				banco.iniciarConexao();

				ProgramaInstitucionalDAO pInstitucionalDAO = new ProgramaInstitucionalDAO(
						banco);
				int idProInstitucional = pInstitucionalDAO
						.insert(programaInstitucional);
				programaInstitucional
						.setIdProgramaInstitucional(idProInstitucional);

				builder.status(Response.Status.OK);
				builder.entity(programaInstitucional);

			} catch (QManagerSQLException qme) {

				QManagerErro erro = new QManagerErro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());
				System.err.println(erro.getCodigo() + " " + erro.getMensagem());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		}

		return builder.build();
	}

	private boolean validarProgramaInsitituicao(
			ProgramaInstitucional pInstitucional) {
		// TODO Auto-generated method stub
		return true;
	}

	@POST
	@Path("/cadastrarEdital")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarEdital(Edital edital) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		if (validarEdital(edital)) {

			try {

				banco.iniciarConexao();

				EditalDAO editalDAO = new EditalDAO(banco);
				int idEdital = editalDAO.insert(edital);
				edital.setIdEdital(idEdital);

				builder.status(Response.Status.OK);
				builder.entity(edital);

			} catch (QManagerSQLException qme) {

				QManagerErro erro = new QManagerErro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());
				System.err.println(erro.getCodigo() + " " + erro.getMensagem());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		}

		return builder.build();
	}

	private boolean validarEdital(Edital edital) {
		// TODO Auto-generated method stub
		return true;
	}

	@POST
	@Path("/cadastrarProjeto")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarProjeto(Projeto projeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		if (validarProjeto(projeto)) {

			try {

				banco.iniciarConexao();

				ProjetoDAO projetoDAO = new ProjetoDAO(banco);
				int idProjeto = projetoDAO.insert(projeto);
				projeto.setIdProjeto(idProjeto);

				builder.status(Response.Status.OK);
				builder.entity(projeto);

			} catch (QManagerSQLException qme) {

				QManagerErro erro = new QManagerErro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		}

		return builder.build();
	}

	private boolean validarProjeto(Projeto projeto) {
		// TODO Auto-generated method stub
		return true;
	}

	@POST
	@Path("/cadastrarAluno")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarAluno(Individuo<Discente> individuo) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		if (validarAluno(individuo.getIndividuo())) {

			try {

				banco.iniciarConexao();

				DiscenteDAO discenteDAO = new DiscenteDAO(banco);
				int idDiscente = discenteDAO.insert(individuo.getIndividuo());
				individuo.getIndividuo().setPessoaId(idDiscente);

				UsuarioDAO usuarioDAO = new UsuarioDAO(banco);
				individuo.getUsuario().setPessoa(individuo.getIndividuo());
				int idUsuario = usuarioDAO.insert(individuo.getUsuario());
				individuo.getUsuario().setIdUsuario(idUsuario);

				DadosBancariosDAO dadosBancariosDAO = new DadosBancariosDAO(
						banco);
				individuo.getDadosBancarios().setPessoa(
						individuo.getIndividuo());
				dadosBancariosDAO.insert(individuo.getDadosBancarios());

				builder.status(Response.Status.OK);
				builder.entity(individuo);

			} catch (QManagerSQLException qme) {

				QManagerErro erro = new QManagerErro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		}

		return builder.build();
	}

	private boolean validarAluno(Discente discente) {
		// TODO Auto-generated method stub
		return true;
	}

	@POST
	@Path("/cadastrarOrientador")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarOrientador(Individuo<Orientador> individuo) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		if (validarOrientador(individuo.getIndividuo())) {

			try {

				banco.iniciarConexao();

				OrientadorDAO orientadorDAO = new OrientadorDAO(banco);
				int idOrientador = orientadorDAO.insert(individuo
						.getIndividuo());
				individuo.getIndividuo().setPessoaId(idOrientador);

				UsuarioDAO usuarioDAO = new UsuarioDAO(banco);
				individuo.getUsuario().setPessoa(individuo.getIndividuo());
				int idUsuario = usuarioDAO.insert(individuo.getUsuario());
				individuo.getUsuario().setIdUsuario(idUsuario);

				DadosBancariosDAO dadosBancariosDAO = new DadosBancariosDAO(
						banco);
				individuo.getDadosBancarios().setPessoa(
						individuo.getIndividuo());
				dadosBancariosDAO.insert(individuo.getDadosBancarios());

				builder.status(Response.Status.OK);
				builder.entity(individuo);

			} catch (QManagerSQLException qme) {

				QManagerErro erro = new QManagerErro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		}

		return builder.build();
	}

	private boolean validarOrientador(Orientador orientador) {
		// TODO Auto-generated method stub
		return true;
	}

	@POST
	@Path("/cadastrarParticipacaoOrientador")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarParticipacaoOrientador(
			Participacao<Orientador> participacao) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		if (validarParticipacaoOrientador(participacao)) {

			try {

				banco.iniciarConexao();

				ParticipacaoOrientadorDAO participacaoOrientadorDAO = new ParticipacaoOrientadorDAO(
						banco);
				int idParticipacao = participacaoOrientadorDAO
						.insert(participacao);
				participacao.setIdParticipacao(idParticipacao);

				builder.status(Response.Status.OK);
				builder.entity(participacao);

			} catch (QManagerSQLException qme) {

				QManagerErro erro = new QManagerErro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		}

		return builder.build();
	}

	private boolean validarParticipacaoOrientador(
			Participacao<Orientador> participacao) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@POST
	@Path("/cadastrarParticipacaoDiscente")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarParticipacaoDiscente(
			Participacao<Discente> participacao) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		if (validarParticipacaoDiscente(participacao)) {

			try {

				banco.iniciarConexao();

				ParticipacaoDiscenteDAO participacaoDiscenteDAO = new ParticipacaoDiscenteDAO(
						banco);
				int idParticipacao = participacaoDiscenteDAO
						.insert(participacao);
				participacao.setIdParticipacao(idParticipacao);

				builder.status(Response.Status.OK);
				builder.entity(participacao);

			} catch (QManagerSQLException qme) {

				QManagerErro erro = new QManagerErro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		}

		return builder.build();
	}

	private boolean validarParticipacaoDiscente(
			Participacao<Discente> participacao) {
		// TODO Auto-generated method stub
		return true;
	}

	@POST
	@Path("/cadastrarIB")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarInstituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		if (validarInstituicaoBancaria(instituicaoBancaria)) {

			try {

				banco.iniciarConexao();

				InstituicaoBancariaDAO instituicaoBancariaDAO = new InstituicaoBancariaDAO(
						banco);
				int idInstituicaoBancaria = instituicaoBancariaDAO
						.insert(instituicaoBancaria);
				instituicaoBancaria
						.setIdInstituicaoBancaria(idInstituicaoBancaria);

				builder.status(Response.Status.OK);
				builder.entity(instituicaoBancaria);

			} catch (QManagerSQLException qme) {

				QManagerErro erro = new QManagerErro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		}

		return builder.build();
	}

	private boolean validarInstituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria) {
		// TODO Auto-generated method stub
		return true;
	}

	@POST
	@Path("/cadastrarCurso")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarCurso(Curso curso) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		if (validarCurso(curso)) {

			try {

				banco.iniciarConexao();

				CursoDAO cursoDAO = new CursoDAO(banco);
				int idCurso = cursoDAO.insert(curso);
				curso.setIdCurso(idCurso);

				builder.status(Response.Status.OK);
				builder.entity(curso);

			} catch (QManagerSQLException qme) {

				QManagerErro erro = new QManagerErro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		}

		return builder.build();
	}

	private boolean validarCurso(Curso curso) {
		// TODO Auto-generated method stub
		return true;
	}

	@POST
	@Path("/cadastrarTurma")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarTurma(Turma turma) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		if (validarTurma(turma)) {

			try {

				banco.iniciarConexao();

				TurmaDAO turmaDAO = new TurmaDAO(banco);
				int idTurma = turmaDAO.insert(turma);
				turma.setIdTurma(idTurma);

				builder.status(Response.Status.OK);
				builder.entity(turma);

			} catch (QManagerSQLException qme) {

				QManagerErro erro = new QManagerErro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		}

		return builder.build();
	}

	private boolean validarTurma(Turma turma) {
		// TODO Auto-generated method stub
		return true;
	}

	@GET
	@Path("/servidorOnline")
	@Produces("application/json")
	public Response servidorOnline() {

		ResponseBuilder builder = Response.status(Response.Status.OK);
        builder.expires(new Date());

        Server server = new Server();
        server.setOnline(true);
        builder.entity(server);

        return builder.build();
	}
}