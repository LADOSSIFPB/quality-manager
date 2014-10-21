package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.entidade.Partipacao;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.Turma;

/**
 * Definition: Contains the services interfaces of QManager.
 * 
 * @author Rhavy Maia Guedes
 * 
 */
public interface QManagerService {
	
	/*
	 * Métodos para consulta
	 */

	/**
	 * 
	 * @param negotiation
	 * @return
	 */
	@POST
	@Path("/consultar/fazerLogin")
	@Consumes("application/json")
	@Produces("application/json")
	public Response fazerLogin(Login login);
	
	@GET
	@Path("/consultar/instituicoesfinanciadoras")
	@Produces("application/json")
	public Response consultarInstituicoes();
	
	@GET
	@Path("/consultar/programasinstitucionais")
	@Produces("application/json")
	public Response consultarProgramasInstitucionais();

	@GET
	@Path("/consultar/editais")
	@Produces("application/json")
	public Response consultarEditais();

	@POST
	@Path("/consultar/editaisprogramainstitucional")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarEditais(ProgramaInstitucional programaInstitucional);

	@GET
	@Path("/consultar/projetos")
	@Produces("application/json")
	public Response consultarProjetos();

	@POST
	@Path("/consultar/projetosprogramainstitucional")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarProjetos(
			ProgramaInstitucional programaInstitucional);

	@POST
	@Path("/consultar/projetosedital")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarProjetos(Edital edital);

	@GET
	@Path("/consultar/orientadores")
	@Produces("application/json")
	public Response consultarOrientadores();

	@GET
	@Path("/consultar/orientadoresprojeto")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarOrientadoresProjeto(Projeto projeto);

	@GET
	@Path("/consultar/discentes")
	@Produces("application/json")
	public Response consultarDiscentes();

	@GET
	@Path("/consultar/discentesprojeto")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarDiscentesProjeto(Projeto projeto);

	@GET
	@Path("/consultar/instituicoesbancarias")
	@Produces("application/json")
	public Response consultarInstituicoesBancarias();

	@GET
	@Path("/consultar/cursos")
	@Produces("application/json")
	public Response consultarCursos();
	
	/*
	 * Métodos de cadastro
	 */
	
	@POST
	@Path("/cadastrar/instituicaofinanciadora")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarInstituicao(
			InstituicaoFinanciadora instituicaoFinanciadora);
	
	@POST
	@Path("/cadastrar/programainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarProgramaInstitucional(
			ProgramaInstitucional programaInstitucional);
	
	@POST
	@Path("/cadastrar/edital")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarEdital(Edital edital);
	
	@POST
	@Path("/cadastrar/projeto")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarProjeto(Projeto projeto);
	
	@POST
	@Path("/cadastrar/discente")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarDiscente(Discente discente);
	
	@POST
	@Path("/cadastrar/orientador")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarOrientador(Orientador orientador);
	
	@POST
	@Path("/cadastrar/participacao")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarParticipacaoOrientador(Partipacao participacao);
	
	@POST
	@Path("/cadastrar/instituicaobancaria")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarInstituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria);
	
	@POST
	@Path("/cadastrar/curso")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarCurso(Curso curso);
	
	@POST
	@Path("/cadastrar/turma")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarTurma(Turma turma);
	
	@GET
	@Path("/cadastrar/servidorOnline")
	@Produces("application/json")
	public Response servidorOnline();


}