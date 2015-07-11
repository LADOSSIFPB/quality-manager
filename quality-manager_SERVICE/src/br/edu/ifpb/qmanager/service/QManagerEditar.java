package br.edu.ifpb.qmanager.service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.edu.ifpb.qmanager.dao.CursoDAO;
import br.edu.ifpb.qmanager.dao.DiscenteDAO;
import br.edu.ifpb.qmanager.dao.EditalDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoBancariaDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.dao.ParticipacaoDAO;
import br.edu.ifpb.qmanager.dao.ProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.dao.ProjetoDAO;
import br.edu.ifpb.qmanager.dao.RecursoInstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.dao.RecursoProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.dao.ServidorDAO;
import br.edu.ifpb.qmanager.dao.TurmaDAO;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.MapErroQManager;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.RecursoInstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.RecursoProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.validacao.Validar;

/**
 * Classe que reune serviços para editar dados das entidades.
 * 
 * @author Igor Barbosa
 * @author Rhavy Maia
 * @author Emanuel Guimarães
 * @author Eri Jonhson
 * @author Felipe Nascimento
 * @author Ivanildo Terceiro
 * @version 0.1
 */
@Path("editar")
public class QManagerEditar {

	@POST
	@Path("/instituicaofinanciadora")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.instituicaoFinanciadora(instituicaoFinanciadora);
		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager mapErro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(mapErro.getErro());
			return builder.build();
		}

		try {

			InstituicaoFinanciadoraDAO.getInstance().update(
					instituicaoFinanciadora);

			builder.status(Response.Status.OK);
			builder.entity(instituicaoFinanciadora);

		} catch (SQLExceptionQManager qme) {
			MapErroQManager mapErro = new MapErroQManager(qme.getErrorCode());
			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
					mapErro.getErro());
		}
		return builder.build();
	}

	@POST
	@Path("/recursoinstituicaofinanciadora")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarRecursoInstituicaoFinanciadora(
			RecursoInstituicaoFinanciadora recurso) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.recursoInstituicaoFinanciadora(recurso);
		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager mapErro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(mapErro.getErro());
			return builder.build();
		}

		try {

			RecursoInstituicaoFinanciadoraDAO.getInstance().update(recurso);

			builder.status(Response.Status.OK);
			builder.entity(recurso);

		} catch (SQLExceptionQManager qme) {

			MapErroQManager mapErro = new MapErroQManager(qme.getErrorCode());
			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
					mapErro.getErro());
		}

		return builder.build();
	}

	@POST
	@Path("/programainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarProgramaInstitucional(ProgramaInstitucional programaInstitucional) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.programaInstitucional(programaInstitucional);
		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager mapErro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(mapErro.getErro());
			return builder.build();
		}

		try {

			ProgramaInstitucionalDAO.getInstance()
					.update(programaInstitucional);

			builder.status(Response.Status.OK);
			builder.entity(programaInstitucional);

		} catch (SQLExceptionQManager qme) {

			MapErroQManager mapErro = new MapErroQManager(qme.getErrorCode());
			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
					mapErro.getErro());
		}

		return builder.build();
	}

	@POST
	@Path("/recursoprogramainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarRecursoProgramaInstitucional(
			RecursoProgramaInstitucional recurso) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.recursoProgramaInstitucional(recurso);
		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager mapErro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(mapErro.getErro());
			return builder.build();
		}

		try {

			RecursoProgramaInstitucionalDAO.getInstance().update(recurso);

			builder.status(Response.Status.OK);
			builder.entity(recurso);

		} catch (SQLExceptionQManager qme) {
			MapErroQManager mapErro = new MapErroQManager(qme.getErrorCode());
			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
					mapErro.getErro());
		}

		return builder.build();
	}

	@POST
	@Path("/edital")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarEdital(Edital edital) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.edital(edital);
		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager mapErro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(mapErro.getErro());
			return builder.build();
		}

		try {

			EditalDAO.getInstance().update(edital);

			builder.status(Response.Status.OK);
			builder.entity(edital);

		} catch (SQLExceptionQManager qme) {

			MapErroQManager mapErro = new MapErroQManager(qme.getErrorCode());
			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
					mapErro.getErro());
		}

		return builder.build();
	}

	@POST
	@Path("/projeto")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarProjeto(Projeto projeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.projeto(projeto);
		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager mapErro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(mapErro.getErro());
			return builder.build();
		}

		try {

			ProjetoDAO.getInstance().update(projeto);

			builder.status(Response.Status.OK);
			builder.entity(projeto);

		} catch (SQLExceptionQManager qme) {

			MapErroQManager mapErro = new MapErroQManager(qme.getErrorCode());
			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
					mapErro.getErro());
		}

		return builder.build();
	}

	@POST
	@Path("/discente")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarDiscente(Discente discente) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.discente(discente);
		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager mapErro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(mapErro.getErro());
			return builder.build();
		}

		try {

			DiscenteDAO.getInstance().update(discente);

			builder.status(Response.Status.OK);
			builder.entity(discente);

		} catch (SQLExceptionQManager qme) {

			MapErroQManager mapErro = new MapErroQManager(qme.getErrorCode());
			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
					mapErro.getErro());
		}

		return builder.build();
	}

	@POST
	@Path("/servidor")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarServidor(Servidor servidor) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.servidor(servidor);
		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager mapErro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(mapErro.getErro());
			return builder.build();
		}

		try {

			ServidorDAO.getInstance().update(servidor);

			builder.status(Response.Status.OK);
			builder.entity(servidor);

		} catch (SQLExceptionQManager qme) {

			MapErroQManager mapErro = new MapErroQManager(qme.getErrorCode());
			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
					mapErro.getErro());
		}

		return builder.build();
	}

	@POST
	@Path("/participacao")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarParticipacaoOrientador(Participacao participacao) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.participacao(participacao);
		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager mapErro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(mapErro.getErro());
			return builder.build();
		}

		try {

			ParticipacaoDAO.getInstance().update(participacao);

			builder.status(Response.Status.OK);
			builder.entity(participacao);

		} catch (SQLExceptionQManager qme) {

			MapErroQManager mapErro = new MapErroQManager(qme.getErrorCode());
			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
					mapErro.getErro());
		}

		return builder.build();
	}

	@POST
	@Path("/instituicaobancaria")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarInstituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.instituicaoBancaria(instituicaoBancaria);
		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager mapErro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(mapErro.getErro());
			return builder.build();
		}

		try {

			InstituicaoBancariaDAO.getInstance().update(instituicaoBancaria);

			builder.status(Response.Status.OK);
			builder.entity(instituicaoBancaria);

		} catch (SQLExceptionQManager qme) {

			MapErroQManager mapErro = new MapErroQManager(qme.getErrorCode());
			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
					mapErro.getErro());
		}

		return builder.build();
	}

	@POST
	@Path("/curso")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarCurso(Curso curso) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.curso(curso);

		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager mapErro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(mapErro.getErro());
			return builder.build();
		}

		try {

			CursoDAO.getInstance().update(curso);

			builder.status(Response.Status.OK);
			builder.entity(curso);

		} catch (SQLExceptionQManager qme) {

			MapErroQManager mapErro = new MapErroQManager(qme.getErrorCode());
			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
					mapErro.getErro());
		}

		return builder.build();
	}

	@POST
	@Path("/turma")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarTurma(Turma turma) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.turma(turma);
		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager mapErro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(mapErro.getErro());
			return builder.build();
		}

		try {

			TurmaDAO.getInstance().update(turma);

			builder.status(Response.Status.OK);
			builder.entity(turma);

		} catch (SQLExceptionQManager qme) {

			MapErroQManager mapErro = new MapErroQManager(qme.getErrorCode());
			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
					mapErro.getErro());
		}

		return builder.build();
	}

}
