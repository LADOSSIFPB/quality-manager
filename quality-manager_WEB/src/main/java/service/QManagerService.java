package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.Coordenador;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Gestor;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.MembroProjeto;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.util.IntegerUtil;

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

	@POST
	@Path("/consultar/instituicaofinanciadora")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarInstituicao(IntegerUtil integerUtil);

	@GET
	@Path("/consultar/programasinstitucionais")
	@Produces("application/json")
	public Response consultarProgramasInstitucionais();

	@POST
	@Path("/consultar/programainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarProgramaInstitucional(IntegerUtil integerUtil);

	@GET
	@Path("/consultar/editais")
	@Produces("application/json")
	public Response consultarEditais();

	@POST
	@Path("/consultar/edital")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarEdital(IntegerUtil integerUtil);

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
	@Path("/consultar/projeto")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarProjeto(IntegerUtil integerUtil);

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

	@POST
	@Path("/consultar/projetosmembroprojeto")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarProjetosMembroProjeto(MembroProjeto membroProjeto);

	@POST
	@Path("/consultar/projetoinformacoes")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarInformacoesProjeto(Projeto projeto);

	@GET
	@Path("/consultar/servidores")
	@Produces("application/json")
	public Response consultarServidores();

	@POST
	@Path("/consultar/servidoresprojeto")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarServidoresProjeto(Projeto projeto);

	@GET
	@Path("/consultar/discentes")
	@Produces("application/json")
	public Response consultarDiscentes();

	@POST
	@Path("/consultar/discente")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarDiscente(IntegerUtil integerUtil);

	@POST
	@Path("/consultar/discentesprojeto")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarDiscentesProjeto(Projeto projeto);

	@GET
	@Path("/consultar/instituicoesbancarias")
	@Produces("application/json")
	public Response consultarInstituicoesBancarias();

	@POST
	@Path("/consultar/instituicaobancaria")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarInstituicaoBancaria(IntegerUtil integerUtil);

	@GET
	@Path("/consultar/cursos")
	@Produces("application/json")
	public Response consultarCursos();

	@POST
	@Path("/consultar/curso")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarCurso(IntegerUtil integerUtil);

	@POST
	@Path("/consultar/turmascoordenador")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarTurmasCoordenador(IntegerUtil integerUtil);

	@POST
	@Path("/consultar/projetosmembroprojeto")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarProjetos(MembroProjeto membroProjeto);
	
	@GET
	@Path("/consultar/cargos")
	@Produces("application/json")
	public Response consultarCargos();
	
	@POST
	@Path("/consultar/cargo")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarCargo(IntegerUtil integerUtil);

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
	public Response cadastrarServidor(Servidor servidor);

	@POST
	@Path("/cadastrar/participacao")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarParticipacao(Participacao participacao);

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

	/*
	 * Métodos de update
	 */

	@POST
	@Path("/editar/instituicaofinanciadora")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora);

	@POST
	@Path("/editar/programainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarProgramaInstitucional(
			ProgramaInstitucional programaInstitucional);

	@POST
	@Path("/editar/edital")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarEdital(Edital edital);

	@POST
	@Path("/editar/projeto")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarProjeto(Projeto projeto);

	@POST
	@Path("/editar/discente")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarDiscente(Discente discente);

	@POST
	@Path("/editar/orientador")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarServidor(Servidor servidor);

	@POST
	@Path("/editar/coordenador")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarCoordenador(Coordenador coordenador);

	@POST
	@Path("/editar/gestor")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarGestor(Gestor gestor);

	@POST
	@Path("/editar/participacao")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarParticipacaoOrientador(Participacao participacao);

	@POST
	@Path("/editar/instituicaobancaria")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarInstituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria);

	@POST
	@Path("/editar/curso")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarCurso(Curso curso);

	@POST
	@Path("/editar/turma")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarTurma(Turma turma);

}