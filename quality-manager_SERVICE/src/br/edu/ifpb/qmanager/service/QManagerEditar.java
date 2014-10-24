package br.edu.ifpb.qmanager.service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.edu.ifpb.qmanager.dao.CoordenadorDAO;
import br.edu.ifpb.qmanager.dao.CursoDAO;
import br.edu.ifpb.qmanager.dao.DatabaseConnection;
import br.edu.ifpb.qmanager.dao.DiscenteDAO;
import br.edu.ifpb.qmanager.dao.EditalDAO;
import br.edu.ifpb.qmanager.dao.GestorDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoBancariaDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.dao.OrientadorDAO;
import br.edu.ifpb.qmanager.dao.ParticipacaoDAO;
import br.edu.ifpb.qmanager.dao.ProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.dao.ProjetoDAO;
import br.edu.ifpb.qmanager.dao.TurmaDAO;
import br.edu.ifpb.qmanager.entidade.Coordenador;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.Gestor;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.entidade.Partipacao;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.QManagerMapErro;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;
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

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar
				.instituicaoFinanciadora(instituicaoFinanciadora);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				banco.iniciarConexao();

				InstituicaoFinanciadoraDAO instituicaoDAO = new InstituicaoFinanciadoraDAO(
						banco);
				instituicaoDAO.update(instituicaoFinanciadora);

				builder.status(Response.Status.OK);
				builder.entity(instituicaoFinanciadora);

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			} finally {

				banco.encerrarConexao();
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/programainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.programaInstitucional(programaInstitucional);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				banco.iniciarConexao();

				ProgramaInstitucionalDAO programaInstitucionalDAO = new ProgramaInstitucionalDAO(
						banco);
				programaInstitucionalDAO.update(programaInstitucional);

				builder.status(Response.Status.OK);
				builder.entity(programaInstitucional);

			} catch (QManagerSQLException qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
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

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.edital(edital);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				banco.iniciarConexao();

				EditalDAO editalDAO = new EditalDAO(banco);
				editalDAO.update(edital);

				builder.status(Response.Status.OK);
				builder.entity(edital);

			} catch (QManagerSQLException qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
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

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.projeto(projeto);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				banco.iniciarConexao();

				ProjetoDAO projetoDAO = new ProjetoDAO(banco);
				projetoDAO.update(projeto);

				builder.status(Response.Status.OK);
				builder.entity(projeto);

			} catch (QManagerSQLException qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
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

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.discente(discente);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				banco.iniciarConexao();

				DiscenteDAO discenteDAO = new DiscenteDAO(banco);
				discenteDAO.update(discente);

				builder.status(Response.Status.OK);
				builder.entity(discente);

			} catch (QManagerSQLException qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/orientador")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarOrientador(Orientador orientador) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.orientador(orientador);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				banco.iniciarConexao();

				OrientadorDAO orientadorDAO = new OrientadorDAO(banco);
				orientadorDAO.update(orientador);

				builder.status(Response.Status.OK);
				builder.entity(orientador);

			} catch (QManagerSQLException qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/coordenador")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarCoordenador(Coordenador coordenador) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		// TODO: Fazer uma validação para Coordenador
		int validacao = 0;
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				banco.iniciarConexao();

				CoordenadorDAO coordenadorDAO = new CoordenadorDAO(banco);
				coordenadorDAO.update(coordenador);

				builder.status(Response.Status.OK);
				builder.entity(coordenador);

			} catch (QManagerSQLException qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/gestor")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarGestor(Gestor gestor) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		// TODO: Fazer uma validação para Gestor
		int validacao = 0;
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				banco.iniciarConexao();

				GestorDAO gestorDAO = new GestorDAO(banco);
				gestorDAO.update(gestor);

				builder.status(Response.Status.OK);
				builder.entity(gestor);

			} catch (QManagerSQLException qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/participacao")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarParticipacaoOrientador(Partipacao participacao) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.participacao(participacao);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				banco.iniciarConexao();

				ParticipacaoDAO participacaoDAO = new ParticipacaoDAO(banco);
				participacaoDAO.update(participacao);

				builder.status(Response.Status.OK);
				builder.entity(participacao);

			} catch (QManagerSQLException qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
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

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.instituicaoBancaria(instituicaoBancaria);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				banco.iniciarConexao();

				InstituicaoBancariaDAO instituicaoBancariaDAO = new InstituicaoBancariaDAO(
						banco);
				instituicaoBancariaDAO.update(instituicaoBancaria);

				builder.status(Response.Status.OK);
				builder.entity(instituicaoBancaria);

			} catch (QManagerSQLException qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
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

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.curso(curso);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				banco.iniciarConexao();

				CursoDAO cursoDAO = new CursoDAO(banco);
				cursoDAO.update(curso);

				builder.status(Response.Status.OK);
				builder.entity(curso);

			} catch (QManagerSQLException qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
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

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.turma(turma);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				banco.iniciarConexao();

				TurmaDAO turmaDAO = new TurmaDAO(banco);
				turmaDAO.update(turma);

				builder.status(Response.Status.OK);
				builder.entity(turma);

			} catch (QManagerSQLException qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);

			} finally {

				banco.encerrarConexao();
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

}
