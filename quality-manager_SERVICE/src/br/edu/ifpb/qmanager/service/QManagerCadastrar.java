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
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.entidade.Partipacao;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.QManagerErro;
import br.edu.ifpb.qmanager.entidade.Server;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;
import br.edu.ifpb.qmanager.validacao.Validar;

@Path("cadastrar")
public class QManagerCadastrar {

	@GET
	@Path("/retorneentidade")
	@Produces("application/json")
	public Response criarInstituicao() {

		ResponseBuilder builder = Response.status(Response.Status.OK);
		builder.expires(new Date());

		Curso server = new Curso("Informatica!");
		builder.entity(server);

		return builder.build();
	}

	/**
	 * Descrição do método e serviço.
	 * 
	 * @author Rhavy Maia
	 * @author Emanuel Guimarães
	 * @author Eri Jonhson
	 * @author Felipe Nascimento
	 * @author Ivanildo Terceiro
	 * @param instituicaoFinanciadora
	 * @return Response
	 */
	@POST
	@Path("/instituicaofinanciadora")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarInstituicao(
			InstituicaoFinanciadora instituicaoFinanciadora) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar
				.instituicaoFinanciadora(instituicaoFinanciadora);
		if (validacao == 0) {

			try {

				banco.iniciarConexao();

				InstituicaoFinanciadoraDAO instituicaoDAO = new InstituicaoFinanciadoraDAO(
						banco);
				int idInstituicao = instituicaoDAO
						.insert(instituicaoFinanciadora);
				instituicaoFinanciadora
						.setIdInstituicaoFinanciadora(idInstituicao);

				builder.status(Response.Status.OK);
				builder.entity(instituicaoFinanciadora);

			} catch (QManagerSQLException qme) {
				QManagerErro erro = new QManagerErro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			} finally {

				banco.encerrarConexao();
			}
		} else {
			QManagerErro erro = new QManagerErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/programainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarPI(ProgramaInstitucional programaInstitucional) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.programaInstitucional(programaInstitucional);
		if (validacao == 0) {

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
		} else {
			QManagerErro erro = new QManagerErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/edital")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarEdital(Edital edital) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.edital(edital);
		if (validacao == 0) {

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
		} else {
			QManagerErro erro = new QManagerErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/projeto")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarProjeto(Projeto projeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.projeto(projeto);
		if (validacao == 0) {

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
		} else {
			QManagerErro erro = new QManagerErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/discente")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarDiscente(Discente discente) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.discente(discente);
		if (validacao == 0) {

			try {

				banco.iniciarConexao();

				DiscenteDAO discenteDAO = new DiscenteDAO(banco);
				int idDiscente = discenteDAO.insert(discente);
				discente.setPessoaId(idDiscente);

				builder.status(Response.Status.OK);
				builder.entity(discente);

			} catch (QManagerSQLException qme) {

				QManagerErro erro = new QManagerErro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		} else {
			QManagerErro erro = new QManagerErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/orientador")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarOrientador(Orientador orientador) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		if (Validar.orientador(orientador)) {

			try {

				banco.iniciarConexao();

				OrientadorDAO orientadorDAO = new OrientadorDAO(banco);
				int idOrientador = orientadorDAO.insert(orientador);
				orientador.setPessoaId(idOrientador);

				builder.status(Response.Status.OK);
				builder.entity(orientador);

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

	@POST
	@Path("/participacao")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarParticipacaoOrientador(Partipacao participacao) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		if (Validar.participacao(participacao)) {

			try {

				banco.iniciarConexao();

				ParticipacaoDAO participacaoDAO = new ParticipacaoDAO(banco);
				int idParticipacao = participacaoDAO.insert(participacao);
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

	@POST
	@Path("/instituicaobancaria")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarInstituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		if (Validar.instituicaoBancaria(instituicaoBancaria)) {

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

	@POST
	@Path("/curso")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarCurso(Curso curso) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		if (Validar.curso(curso)) {

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

	@POST
	@Path("/turma")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarTurma(Turma turma) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		if (Validar.turma(turma)) {

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