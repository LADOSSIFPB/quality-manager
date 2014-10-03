package br.edu.ifpb.qmanager.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.edu.ifpb.qmanager.dao.DatabaseConnection;
import br.edu.ifpb.qmanager.dao.DiscenteDAO;
import br.edu.ifpb.qmanager.dao.EditalDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.dao.OrientadorDAO;
import br.edu.ifpb.qmanager.dao.PessoaDAO;
import br.edu.ifpb.qmanager.dao.ProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.dao.ProjetoDAO;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.QManagerMapErro;
import br.edu.ifpb.qmanager.entidade.Usuario;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;
import br.edu.ifpb.qmanager.validacao.Validar;

/**
 * Classe que reune serviços de consulta ao banco de dados.
 * 
 * @author Rhavy Maia
 * @author Emanuel Guimarães
 * @author Eri Jonhson
 * @author Felipe Nascimento
 * @author Ivanildo Terceiro
 * @version 0.1
 */
@Path("consultar")
public class QManagerConsultar {

	@GET
	@Path("/entidade")
	@Produces("application/json")
	public Response entidade() {

		ResponseBuilder builder = Response.status(Response.Status.OK);
		builder.expires(new Date());

		Edital server = new Edital("C:/Users/Emanuel/Desktop/JSON.txt", 15,
				2013, java.sql.Date.valueOf("2013-01-01"),
				java.sql.Date.valueOf("2013-02-01"),
				java.sql.Date.valueOf("2013-07-01"),
				java.sql.Date.valueOf("2014-01-01"), 10, 100.0, 200.0, 'P',
				null);
		server.setIdEdital(1);

		builder.entity(server);

		return builder.build();
	}

	/**
	 * Serviço para consultar se Usuario que deseja logar existe no sistema.
	 * 
	 * @param login
	 * @return Usuario
	 */
	@POST
	@Path("/fazerLogin")
	@Produces("application/json")
	@Consumes("application/json")
	public Response fazerLogin(Login login) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.login(login);

		if (validacao == Validar.VALIDACAO_OK) {

			try {

				banco.iniciarConexao();

				Usuario usuario = new Usuario();
				PessoaDAO pessoaDAO = new PessoaDAO(banco);

				usuario = pessoaDAO.getByLogin(login);

				builder.status(Response.Status.OK);
				builder.entity(usuario);

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
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
	@Path("/instituicoesfinanciadoras")
	@Produces("application/json")
	public Response consultarInstituicoes() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		try {

			banco.iniciarConexao();

			InstituicaoFinanciadoraDAO instituicaoDAO = new InstituicaoFinanciadoraDAO(
					banco);
			List<InstituicaoFinanciadora> instituicoesFinanciadoras = instituicaoDAO
					.getAll();

			builder.status(Response.Status.OK);
			builder.entity(instituicoesFinanciadoras);

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		} finally {

			banco.encerrarConexao();
		}

		return builder.build();
	}

	@GET
	@Path("/programasinstitucionais")
	@Produces("application/json")
	public Response consultarProgramasInstitucionais() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		try {

			banco.iniciarConexao();

			ProgramaInstitucionalDAO programaInstitucionalDAO = new ProgramaInstitucionalDAO(
					banco);
			List<ProgramaInstitucional> programasInstitucionais = programaInstitucionalDAO
					.getAll();

			builder.status(Response.Status.OK);
			builder.entity(programasInstitucionais);

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		} finally {

			banco.encerrarConexao();
		}

		return builder.build();
	}

	@GET
	@Path("/editais")
	@Produces("application/json")
	public Response consultarEditais() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		try {

			banco.iniciarConexao();

			EditalDAO editalDAO = new EditalDAO(banco);
			List<Edital> editais = editalDAO.getAll();

			builder.status(Response.Status.OK);
			builder.entity(editais);

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		} finally {

			banco.encerrarConexao();
		}

		return builder.build();
	}

	@POST
	@Path("/editaisprogramainstitucional")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarEditais(ProgramaInstitucional programaInstitucional) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.programaInstitucional(programaInstitucional);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				banco.iniciarConexao();

				EditalDAO editalDAO = new EditalDAO(banco);
				List<Edital> editais = editalDAO
						.getByProgramaInstitucional(programaInstitucional);

				builder.status(Response.Status.OK);
				builder.entity(editais);

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

	@GET
	@Path("/projetos")
	@Produces("application/json")
	public Response consultarProjetos() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		try {

			banco.iniciarConexao();

			ProjetoDAO projetoDAO = new ProjetoDAO(banco);
			List<Projeto> projetos = projetoDAO.getAll();

			builder.status(Response.Status.OK);
			builder.entity(projetos);

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		} finally {

			banco.encerrarConexao();
		}

		return builder.build();
	}

	@POST
	@Path("/projetosprogramainstitucional")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarProjetos(
			ProgramaInstitucional programaInstitucional) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.programaInstitucional(programaInstitucional);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				banco.iniciarConexao();

				ProjetoDAO projetoDAO = new ProjetoDAO(banco);
				List<Projeto> projetos = projetoDAO
						.getByProgramaInstitucional(programaInstitucional);

				builder.status(Response.Status.OK);
				builder.entity(projetos);

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
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
	@Path("/projetosedital")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarProjetos(Edital edital) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.edital(edital);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				banco.iniciarConexao();

				ProjetoDAO projetoDAO = new ProjetoDAO(banco);
				List<Projeto> projetos = projetoDAO.getByEdital(edital);

				builder.status(Response.Status.OK);
				builder.entity(projetos);

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
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
	@Path("/orientadores")
	@Produces("application/json")
	public Response consultarOrientadores() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		try {

			banco.iniciarConexao();

			OrientadorDAO orientadorDAO = new OrientadorDAO(banco);
			List<Orientador> orientadores = orientadorDAO.getAll();

			builder.status(Response.Status.OK);
			builder.entity(orientadores);

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		} finally {

			banco.encerrarConexao();
		}

		return builder.build();
	}

	@GET
	@Path("/orientadoresprojeto")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarOrientadoresProjeto(Projeto projeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.projeto(projeto);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				banco.iniciarConexao();

				OrientadorDAO orientadorDAO = new OrientadorDAO(banco);
				List<Orientador> orientadores = orientadorDAO
						.getByProjeto(projeto);

				builder.status(Response.Status.OK);
				builder.entity(orientadores);

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
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
	@Path("/discentes")
	@Produces("application/json")
	public Response consultarDiscentes() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		try {

			banco.iniciarConexao();

			DiscenteDAO discenteDAO = new DiscenteDAO(banco);
			List<Discente> discentes = discenteDAO.getAll();

			builder.status(Response.Status.OK);
			builder.entity(discentes);

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		} finally {

			banco.encerrarConexao();
		}

		return builder.build();
	}

	@GET
	@Path("/discentesprojeto")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarDiscentesProjeto(Projeto projeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		int validacao = Validar.projeto(projeto);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				banco.iniciarConexao();

				DiscenteDAO discenteDAO = new DiscenteDAO(banco);
				List<Discente> discentes = discenteDAO.getByProjeto(projeto);

				builder.status(Response.Status.OK);
				builder.entity(discentes);

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
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

}