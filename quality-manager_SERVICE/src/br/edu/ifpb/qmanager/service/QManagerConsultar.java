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

import br.edu.ifpb.qmanager.dao.CursoDAO;
import br.edu.ifpb.qmanager.dao.DiscenteDAO;
import br.edu.ifpb.qmanager.dao.EditalDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoBancariaDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.dao.OrientadorDAO;
import br.edu.ifpb.qmanager.dao.PessoaDAO;
import br.edu.ifpb.qmanager.dao.ProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.dao.ProjetoDAO;
import br.edu.ifpb.qmanager.dao.TurmaDAO;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.Gestor;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.MembroProjeto;
import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.QManagerMapErro;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;
import br.edu.ifpb.qmanager.validacao.Validar;

/**
 * Classe que reune serviços de consulta ao banco de dados.
 * 
 * @author Igor Barbosa
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

		Login login = new Login();
		login.setIdentificador("rhavy.mg@gmail.com");
		login.setSenha("Rg123456%");

		Gestor gestor = new Gestor("Márcia", "1234567", "65432191",
				"Rua das Mandiocas", "55888000", "8388776655",
				"marcia@gmail.com", "Ma12345%", new DadosBancarios(
						new InstituicaoBancaria("Banco do Brasil"), "013",
						"07560001231"));

		InstituicaoFinanciadora instituicaoFinanciadora = new InstituicaoFinanciadora(
				"12345", "Instituto Federal", "IFPB", 1000.0, gestor);

		ProgramaInstitucional programaInstitucional = new ProgramaInstitucional(
				"PIBIC-CT", "PIB", 490.0, instituicaoFinanciadora, gestor);

		Edital edital = new Edital("C:/Users/Emanuel/Desktop/JSON.txt", 15,
				2013, java.sql.Date.valueOf("2013-01-01"),
				java.sql.Date.valueOf("2013-02-01"),
				java.sql.Date.valueOf("2013-07-01"),
				java.sql.Date.valueOf("2014-01-01"), 10, 100.0, 200.0, 'P',
				programaInstitucional, gestor);

		builder.entity(edital);

		return builder.build();
	}

	/**
	 * Serviço que permite ao Usuário entrar no sistema.
	 * 
	 * @param login
	 * @return Usuario
	 */
	@POST
	@Path("/fazerLogin")
	@Consumes("application/json")
	@Produces("application/json")
	public Response fazerLogin(Login login) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.login(login);

		if (validacao == Validar.VALIDACAO_OK) {

			try {

				Pessoa pessoa = PessoaDAO.getInstance().getByLogin(login);

				builder.status(Response.Status.OK);
				builder.entity(pessoa);

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/instituicoesfinanciadoras")
	@Produces("application/json")
	public Response consultarInstituicoes() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<InstituicaoFinanciadora> instituicoesFinanciadoras = InstituicaoFinanciadoraDAO
					.getInstance().getAll();

			builder.status(Response.Status.OK);
			builder.entity(instituicoesFinanciadoras);

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/programasinstitucionais")
	@Produces("application/json")
	public Response consultarProgramasInstitucionais() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<ProgramaInstitucional> programasInstitucionais = ProgramaInstitucionalDAO
					.getInstance().getAll();

			builder.status(Response.Status.OK);
			builder.entity(programasInstitucionais);

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/editais")
	@Produces("application/json")
	public Response consultarEditais() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Edital> editais = EditalDAO.getInstance().getAll();

			builder.status(Response.Status.OK);
			builder.entity(editais);

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
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

		int validacao = Validar.programaInstitucional(programaInstitucional);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				List<Edital> editais = EditalDAO.getInstance()
						.getByProgramaInstitucional(programaInstitucional);

				builder.status(Response.Status.OK);
				builder.entity(editais);

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
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

		try {

			List<Projeto> projetos = ProjetoDAO.getInstance().getAll();

			builder.status(Response.Status.OK);
			builder.entity(projetos);

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
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

		int validacao = Validar.programaInstitucional(programaInstitucional);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				List<Projeto> projetos = ProjetoDAO.getInstance()
						.getByProgramaInstitucional(programaInstitucional);

				builder.status(Response.Status.OK);
				builder.entity(projetos);

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
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

		int validacao = Validar.edital(edital);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				List<Projeto> projetos = ProjetoDAO.getInstance().getByEdital(
						edital);

				builder.status(Response.Status.OK);
				builder.entity(projetos);

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/projetosmembroprojeto")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarProjetos(MembroProjeto membroProjeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.orientador(membroProjeto);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				List<Projeto> projetos = ProjetoDAO.getInstance()
						.getByMembroProjeto(membroProjeto);

				builder.status(Response.Status.OK);
				builder.entity(projetos);

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/orientadores")
	@Produces("application/json")
	public Response consultarOrientadores() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Orientador> orientadores = OrientadorDAO.getInstance()
					.getAll();

			builder.status(Response.Status.OK);
			builder.entity(orientadores);

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
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

		int validacao = Validar.projeto(projeto);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				List<Orientador> orientadores = OrientadorDAO.getInstance()
						.getByProjeto(projeto);

				builder.status(Response.Status.OK);
				builder.entity(orientadores);

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/discentes")
	@Produces("application/json")
	public Response consultarDiscentes() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Discente> discentes = DiscenteDAO.getInstance().getAll();

			builder.status(Response.Status.OK);
			builder.entity(discentes);

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
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

		int validacao = Validar.projeto(projeto);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				List<Discente> discentes = DiscenteDAO.getInstance()
						.getByProjeto(projeto);

				builder.status(Response.Status.OK);
				builder.entity(discentes);

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/instituicoesbancarias")
	@Produces("application/json")
	public Response consultarInstituicoesBancarias() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<InstituicaoBancaria> instituicoesBancarias = InstituicaoBancariaDAO
					.getInstance().getAll();

			builder.status(Response.Status.OK);
			builder.entity(instituicoesBancarias);

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/cursos")
	@Produces("application/json")
	public Response consultarCursos() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Curso> cursos = CursoDAO.getInstance().getAll();

			builder.status(Response.Status.OK);
			builder.entity(cursos);

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/turmascoordenador")
	@Produces("application/json")
	public Response consultarTurmasCoordenador(int id) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Turma> turmas = TurmaDAO.getInstance().getByCoordenador(id);

			builder.status(Response.Status.OK);
			builder.entity(turmas);

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

}