package service;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Local;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.MembroProjeto;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoParticipacao;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.form.FileUploadForm;

/**
 * Definition: Contains the services interfaces of QManager.
 * 
 * @author Rhavy Maia Guedes
 * 
 */
public interface QManagerService {

	/**
	 * 
	 * @param negotiation
	 * @return
	 */

	/*
	 * Métodos para consulta
	 */

	@POST
	@Path("/fazerLogin")
	@Consumes("application/json")
	@Produces("application/json")
	public Response fazerLogin(Login login);

	@POST
	@Path("/instituicoesfinanciadoras")
	@Produces("application/json")
	public List<InstituicaoFinanciadora> consultarInstituicoesFinanciadoras(
			InstituicaoFinanciadora instituicaoFinanciadora)
			throws SQLException;

	@GET
	@Path("/instituicoesfinanciadoras/listar")
	@Produces("application/json")
	public List<InstituicaoFinanciadora> listarInstituicoesFinanciadoras()
			throws SQLException;

	@GET
	@Path("/instituicaofinanciadora/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarInstituicao(
			@PathParam("id") int idInstituicaoFinanciadora);

	@POST
	@Path("/programasinstitucionais")
	@Produces("application/json")
	public List<ProgramaInstitucional> consultarProgramasInstitucionais(
			ProgramaInstitucional programaInstitucional) throws SQLException;

	@GET
	@Path("/programasinstitucionais/listar")
	@Produces("application/json")
	public Response listarProgramasInstitucionais();

	@GET
	@Path("/programainstitucional/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarProgramaInstitucional(
			@PathParam("id") int idProgramaInstitucional);

	@POST
	@Path("/editais")
	@Produces("application/json")
	public List<Edital> consultarEditais(Edital edital) throws SQLException;

	@GET
	@Path("/editais/listar")
	@Produces("application/json")
	public List<Edital> listarEditais() throws SQLException;

	@GET
	@Path("/edital/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarEdital(@PathParam("id") int idEdital);

	@POST
	@Path("/editaisprogramainstitucional")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarEditais(ProgramaInstitucional programaInstitucional);

	@POST
	@Path("/projetos")
	@Produces("application/json")
	public List<Projeto> consultarProjetos(Projeto projeto) throws SQLException;

	@GET
	@Path("/projetos/listar")
	@Produces("application/json")
	public List<Projeto> listarProjetos() throws SQLException;

	@GET
	@Path("/projeto/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarProjeto(@PathParam("id") int idProjeto);

	@POST
	@Path("/projetosprogramainstitucional")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarProjetos(
			ProgramaInstitucional programaInstitucional);

	@POST
	@Path("/projetosedital")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarProjetos(Edital edital);

	@POST
	@Path("/projetosmembroprojeto")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarProjetosMembroProjeto(MembroProjeto membroProjeto);

	@POST
	@Path("/projetoinformacoes")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarInformacoesProjeto(Projeto projeto);

	@POST
	@Path("/servidores")
	@Produces("application/json")
	public List<Servidor> consultarServidores(Servidor servidor)
			throws SQLException;

	@GET
	@Path("/servidores/listar")
	@Produces("application/json")
	public List<Servidor> listarServidores() throws SQLException;

	@POST
	@Path("/servidoresprojeto")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarServidoresProjeto(Projeto projeto);

	@GET
	@Path("/servidorespesquisa")
	@Produces("application/json")
	public Response consultarServidoresPesquisa();

	@GET
	@Path("/servidoresextensao")
	@Produces("application/json")
	public Response consultarServidoresExtensao();

	@GET
	@Path("/servidorespesquisa/{ano}")
	@Produces("application/json")
	public Response consultarServidoresPesquisa(@PathParam("ano") int ano);

	@GET
	@Path("/servidoresextensao/{ano}")
	@Produces("application/json")
	public Response consultarServidoresExtensao(@PathParam("ano") int ano);

	@GET
	@Path("/servidor/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarServidor(@PathParam("id") int idServidor);

	@GET
	@Path("/coordenadores")
	@Produces("application/json")
	public List<Servidor> consultarCoordenadores() throws SQLException;

	@GET
	@Path("/coordenador/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarCoordenador(@PathParam("id") int idCoordenador);

	@GET
	@Path("/gestores")
	@Produces("application/json")
	public List<Servidor> consultarGestores() throws SQLException;

	@GET
	@Path("/gestor/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarGestor(@PathParam("id") int idGestor);

	@GET
	@Path("/discentes")
	@Produces("application/json")
	public List<Discente> consultarDiscentes(Discente discente)
			throws SQLException;

	@POST
	@Path("/discentes/listar")
	@Produces("application/json")
	public List<Discente> listarDiscentes() throws SQLException;

	@POST
	@Path("/discente/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarDiscente(@PathParam("id") int idDiscente);

	@POST
	@Path("/discentesprojeto")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarDiscentesProjeto(Projeto projeto);

	@POST
	@Path("/instituicoesbancarias")
	@Produces("application/json")
	public List<InstituicaoBancaria> consultarInstituicoesBancarias(
			InstituicaoBancaria instituicaoBancaria) throws SQLException;

	@GET
	@Path("/instituicoesbancarias/listar")
	@Produces("application/json")
	public List<InstituicaoBancaria> listarInstituicoesBancarias()
			throws SQLException;

	@GET
	@Path("/instituicaobancaria/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarInstituicaoBancaria(
			@PathParam("id") int idInstituicaoBancaria);

	@POST
	@Path("/cursos")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Curso> consultarCursos(Curso curso) throws SQLException;

	@GET
	@Path("/cursos/listar")
	@Produces("application/json")
	public List<Curso> listarCursos() throws SQLException;

	@GET
	@Path("/curso/{idcurso}")
	@Produces("application/json")
	public Response consultarCurso(@PathParam("idcurso") int idCurso);

	@POST
	@Path("/turmascoordenador/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarTurmasCoordenador(
			@PathParam("id") int idCoordenador);

	@POST
	@Path("/cargos")
	@Produces("application/json")
	public List<CargoServidor> consultarCargos(CargoServidor cargoServidor)
			throws SQLException;

	@GET
	@Path("/cargos/listar")
	@Produces("application/json")
	public List<CargoServidor> consultarCargos() throws SQLException;

	@GET
	@Path("/cargo/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarCargo(@PathParam("id") int idCargo);

	@POST
	@Path("/tiposparticipacao")
	@Produces("application/json")
	public List<TipoParticipacao> consultarTiposParticipacao(
			TipoParticipacao tipoParticipacao) throws SQLException;

	@POST
	@Path("/tipoparticipacao/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarTipoParticipacao(
			@PathParam("id") int idTipoParticipacao);

	@POST
	@Path("/pessoas")
	@Produces("application/json")
	@Consumes("application/json")
	public List<Pessoa> consultarPessoas(Pessoa pessoa) throws SQLException;

	@POST
	@Path("/pessoa/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarPessoa(@PathParam("id") int idPessoa);

	@GET
	@Path("/pessoa/{idPessoa}/{idTipoPessoa}")
	@Produces("application/json")
	public Response consultarPessoaPorTipo(@PathParam("idPessoa") int idPessoa,
			@PathParam("idTipoPessoa") int idTipoPessoa);

	@POST
	@Path("/locais")
	@Produces("application/json")
	public List<Local> consultarLocais(Local local) throws SQLException;

	@GET
	@Path("/locais/listar")
	@Produces("application/json")
	public List<Local> listarLocais() throws SQLException;

	@POST
	@Path("/local/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarLocal(@PathParam("id") int idLocal);

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
	public Response editarCoordenador(Servidor coordenador);

	@POST
	@Path("/editar/gestor")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarGestor(Servidor gestor);

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

	@POST
	@Path("/arquivo/upload/projeto/{idprojeto}")
	@Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=UTF-8")
	@Produces("application/json")
	public Response uploadFile(@PathParam("idprojeto") String idProjeto,
			@MultipartForm FileUploadForm form);
}