package br.edu.ifpb.qmanager.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.edu.ifpb.qmanager.dao.BancoUtil;
import br.edu.ifpb.qmanager.dao.CursoDAO;
import br.edu.ifpb.qmanager.dao.TurmaDAO;
import br.edu.ifpb.qmanager.entidade.CodeErroQManager;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.MapErroQManager;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.validacao.Validar;

@Path("curso")
public class CursoController {

	/**
	 * Cadastra um Curso.
	 * 
	 * @param Curso
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/cadastrar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarCurso(Curso curso) {
		
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.curso(curso);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				int idCurso = CursoDAO.getInstance().insert(curso);

				if (idCurso != BancoUtil.IDVAZIO) {

					// Curso inserido com sucesso.
					curso.setIdCurso(idCurso);
					builder.status(Response.Status.OK);
					builder.entity(curso);

				} else {
					
					builder.status(Response.Status.NOT_ACCEPTABLE);
				}

			} catch (SQLExceptionQManager qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}

		} else {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.NOT_ACCEPTABLE).entity(
					erro.getErro());
		}

		return builder.build();
	}
	
	/**
	 * Cadastra uma Turma.
	 * 
	 * @param Turma
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/cadastrar/turma")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarTurma(Turma turma) {
		
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.turma(turma);

		if (validacao == Validar.VALIDACAO_OK) {

			try {

				int idTurma = TurmaDAO.getInstance().insert(turma);

				if (idTurma != BancoUtil.IDVAZIO) {

					turma.setIdTurma(idTurma);

					builder.status(Response.Status.OK);
					builder.entity(turma);

				} else {
					builder.status(Response.Status.NOT_ACCEPTABLE);
				}

			} catch (SQLExceptionQManager qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			}
		} else {
			
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.NOT_ACCEPTABLE).entity(
					erro.getErro());
		}

		return builder.build();
	}
	
	@PermitAll
	@POST
	@Path("/consultar/cursos")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Curso> consultarCursos(Curso curso) throws SQLException {

		List<Curso> cursos = new ArrayList<Curso>();
		cursos = CursoDAO.getInstance().find(curso);

		return cursos;
	}

	@PermitAll
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Curso> listarCursos() throws SQLException {

		List<Curso> cursos = new ArrayList<Curso>();

		cursos = CursoDAO.getInstance().getAll();

		return cursos;
	}

	@PermitAll
	@GET
	@Path("/consultar/curso/{idcurso}")
	@Produces("application/json")
	public Response consultarCurso(@PathParam("idcurso") int idCurso) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Curso curso = CursoDAO.getInstance().getById(idCurso);

			if (curso != null) {
				// Curso encontrado
				builder.status(Response.Status.OK);
				builder.entity(curso);

			} else {

				// Curso não encontrado.
				builder.status(Response.Status.NOT_FOUND);
				Erro erro = new MapErroQManager(
						CodeErroQManager.CURSO_INVALIDO).getErro();

				builder.entity(erro);
			}

		} catch (SQLExceptionQManager qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@PermitAll
	@GET
	@Path("/consultar/turmascoordenador/{id}")
	@Produces("application/json")
	public Response consultarTurmasCoordenador(
			@PathParam("id") int idCoordenador) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Turma> turmas = TurmaDAO.getInstance().getByCoordenador(
					idCoordenador);

			builder.status(Response.Status.OK);
			builder.entity(turmas);

		} catch (SQLExceptionQManager qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}
	
	@POST
	@Path("/editar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarCurso(Curso curso) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.curso(curso);

		if (validacao == Validar.VALIDACAO_OK) {

			try {

				CursoDAO.getInstance().update(curso);

				builder.status(Response.Status.OK);
				builder.entity(curso);

			} catch (SQLExceptionQManager qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}

		} else {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}
	
	@POST
	@Path("/editar/turma")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarTurma(Turma turma) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.turma(turma);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				TurmaDAO.getInstance().update(turma);

				builder.status(Response.Status.OK);
				builder.entity(turma);

			} catch (SQLExceptionQManager qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			}
		} else {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}
}
