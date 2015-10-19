package service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.edu.ifpb.qmanager.chat.Conversa;
import br.edu.ifpb.qmanager.chat.Mensagem;
import br.edu.ifpb.qmanager.entidade.Area;
import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Departamento;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.EditalCampusSubmissao;
import br.edu.ifpb.qmanager.entidade.GrandeArea;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.RecursoInstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.RecursoProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoParticipacao;
import br.edu.ifpb.qmanager.entidade.TipoProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Titulacao;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.form.FileUploadForm;
import br.edu.ifpb.qmanager.relatorios.Pizza;
import br.edu.ifpb.qmanager.relatorios.Quadro;
import br.edu.ifpb.qmanager.tipo.TipoArquivoEdital;
import br.edu.ifpb.qmanager.tipo.TipoArquivoParticipacao;
import br.edu.ifpb.qmanager.tipo.TipoArquivoProjeto;

/**
 * Definition: Contains the services interfaces of QManager.
 * 
 * @author Rhavy Maia Guedes
 * 
 */
public interface QManagerService {
	
	@GET
	@Path("/area/consultar/grandesareas/listar")
	@Produces("application/json")
	public List<GrandeArea> listarGrandesAreas();

	@GET
	@Path("/area/consultar/grandearea/{idGrandeArea}")
	@Produces("application/json")
	public List<Area> consultarAreasByGrandeArea(
			@PathParam("idGrandeArea") int idGrandeArea);

	// -----------------------------------------------------------------------

	@POST
	@Path("/campus/consultar/locais")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Campus> consultarLocais(Campus campus);

	@GET
	@Path("/campus/locais/listar")
	@Produces("application/json")
	public List<Campus> listarLocais();

	@GET
	@Path("/campus/consultar/local/{id}")
	@Produces("application/json")
	public Response consultarLocal(@PathParam("id") int idLocal);

	// -----------------------------------------------------------------------

	@POST
	@Path("/cargo/consultar")
	@Consumes("application/json")
	@Produces("application/json")
	public List<CargoServidor> consultarCargos(CargoServidor cargoServidor);

	@GET
	@Path("/cargo/listar")
	@Produces("application/json")
	public List<CargoServidor> listarCargos();

	@GET
	@Path("/cargo/consultar/{id}")
	@Produces("application/json")
	public Response consultarCargo(@PathParam("id") int idCargo);

	// -----------------------------------------------------------------------

	@POST
	@Path("/chat/cadastrar/conversa")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarConversa(Conversa conversa);

	@POST
	@Path("/chat/cadastrar/mensagem")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarMensagem(Mensagem mensagem);

	@POST
	@Path("/chat/consultar/pessoa")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Conversa> consultarConversasPorPessoa(Pessoa pessoa) throws SQLException;

	@POST
	@Path("/chat/consultar/naovizualizada/quantidade")
	@Consumes("application/json")
	@Produces("application/json")
	public int quantidadeConversasNaoVisualizadas(Pessoa pessoa) throws SQLException;

	@POST
	@Path("/chat/consultar/mensagens")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Mensagem> consultarMensagensPorConversa(Conversa conversa) throws SQLException;

	// -----------------------------------------------------------------------

	@POST
	@Path("/curso/cadastrar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarCurso(Curso curso);

	@POST
	@Path("/curso/cadastrar/turma")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarTurma(Turma turma);

	@POST
	@Path("/curso/consultar/cursos")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Curso> consultarCursos(Curso curso);

	@GET
	@Path("/curso/listar")
	@Produces("application/json")
	public List<Curso> listarCursos();

	@GET
	@Path("/curso/consultar/curso/{idcurso}")
	@Produces("application/json")
	public Response consultarCurso(@PathParam("idcurso") int idCurso);

	@GET
	@Path("/curso/consultar/turmascoordenador/{id}")
	@Produces("application/json")
	public Response consultarTurmasCoordenador(
			@PathParam("id") int idCoordenador);

	@POST
	@Path("/curso/editar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarCurso(Curso curso);

	@POST
	@Path("/curso/editar/turma")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarTurma(Turma turma);

	// -------------------------------------------------------------------

	@GET
	@Path("/departamento/listar")
	@Produces("application/json")
	public List<Departamento> listarDepartamentos();

	// -------------------------------------------------------------------

	@POST
	@Path("/edital/cadastrar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarEdital(Edital edital);

	@POST
	@Path("/edital/cadastrar/editalcampisubmissao")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarEditalCampiSubmissao(
			List<EditalCampusSubmissao> editalCampiSubmissao);

	@POST
	@Path("/edital/consultar")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Edital> consultarEditais(Edital edital);

	@GET
	@Path("/edital/listar")
	@Produces("application/json")
	public List<Edital> listarEditais();

	@GET
	@Path("/edital/consultar/{id}")
	@Produces("application/json")
	public Response consultarEdital(@PathParam("id") int idEdital);

	@POST
	@Path("/edital/consultar/editaisprogramainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarEditais(ProgramaInstitucional programaInstitucional);

	@GET
	@Path("/edital/consultar/editaisanos")
	@Produces("application/json")
	public List<Integer> consultarAnosEditais();

	@GET
	@Path("/edital/consultar/ano/{ano}")
	@Produces("application/json")
	public List<Edital> consultarEditalAno(@PathParam("ano") int anoEdital);

	@GET
	@Path("/edital/consultar/proximonumero/{ano}")
	@Produces("application/json")
	public int consultarProximoNumeroEdital(@PathParam("ano") int anoEdital);

	@PermitAll
	@GET
	@Path("/edital/consultar/campus/{idCampus}")
	@Produces("application/json")
	public List<Edital> listarEditaisCampus(@PathParam("idCampus") int idCampus);

	@POST
	@Path("/edital/editar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarEdital(Edital edital);

	// --------------------------------------------------------------------------

	@POST
	@Path("/instituicaobancaria/cadastrar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarInstituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria);

	@POST
	@Path("/instituicaobancaria/consultar/instituicoesbancarias")
	@Consumes("application/json")
	@Produces("application/json")
	public List<InstituicaoBancaria> consultarInstituicoesBancarias(
			InstituicaoBancaria instituicaoBancaria);

	@GET
	@Path("/instituicaobancaria/listar")
	@Produces("application/json")
	public List<InstituicaoBancaria> listarInstituicoesBancarias();

	@GET
	@Path("/instituicaobancaria/consultar/{id}")
	@Produces("application/json")
	public Response consultarInstituicaoBancaria(
			@PathParam("id") int idInstituicaoBancaria);

	@POST
	@Path("/instituicaobancaria/editar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarInstituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria);

	// -------------------------------------------------------------------

	@POST
	@Path("/instituicaofinanciadora/cadastrar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarInstituicao(
			InstituicaoFinanciadora instituicaoFinanciadora);

	@POST
	@Path("/instituicaofinanciadora/cadastrar/recursoinstituicaofinanciadora")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarRecursoInstituicao(
			RecursoInstituicaoFinanciadora recurso);

	@POST
	@Path("/instituicaofinanciadora/consultar/instituicoesfinanciadoras")
	@Consumes("application/json")
	@Produces("application/json")
	public List<InstituicaoFinanciadora> consultarInstituicoesFinanciadoras(
			InstituicaoFinanciadora instituicaoFinanciadora);

	@GET
	@Path("/instituicaofinanciadora/listar")
	@Produces("application/json")
	public List<InstituicaoFinanciadora> listarInstituicoesFinanciadoras();

	@GET
	@Path("/instituicaofinanciadora/consultar/{id}")
	@Produces("application/json")
	public Response consultarInstituicao(
			@PathParam("id") int idInstituicaoFinanciadora);

	@GET
	@Path("/instituicaofinanciadora/consultar/recursos/listar")
	@Produces("application/json")
	public List<RecursoInstituicaoFinanciadora> listarRecursosInstituicaoFinanciadora();

	@GET
	@Path("/instituicaofinanciadora/consultar/recurso/{id}")
	@Produces("application/json")
	public Response consultarRecursoInstituicaoFinanciadora(
			@PathParam("id") int idRecurso);

	@POST
	@Path("/instituicaofinanciadora/consultar/recursosvalidos")
	@Consumes("application/json")
	@Produces("application/json")
	public List<RecursoInstituicaoFinanciadora> consultarRecursosValidosInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) throws SQLException;

	@POST
	@Path("/instituicaofinanciadora/consultar/recursos")
	@Consumes("application/json")
	@Produces("application/json")
	public List<RecursoInstituicaoFinanciadora> consultarRecursosInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) throws SQLException;

	@POST
	@Path("/instituicaofinanciadora/editar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora);

	// -------------------------------------------------------------------

	@POST
	@Path("/participacao/cadastrar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarParticipacao(Participacao participacao);

	@POST
	@Path("/participacao/consultar/tiposparticipacao")
	@Consumes("application/json")
	@Produces("application/json")
	public List<TipoParticipacao> consultarTiposParticipacao(
			TipoParticipacao tipoParticipacao);

	@GET
	@Path("/participacao/tiposparticipacao/listar")
	@Produces("application/json")
	public List<TipoParticipacao> listarTiposParticipacao();

	@GET
	@Path("/participacao/consultar/tipoparticipacao/{id}")
	@Produces("application/json")
	public Response consultarTipoParticipacao(
			@PathParam("id") int idTipoParticipacao);

	@POST
	@Path("/participacao/editar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarParticipacaoOrientador(Participacao participacao);

	// --------------------------------------------------------------------------------------

	@POST
	@Path("/pessoa/cadastrar/discente")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarDiscente(Discente discente);

	@POST
	@Path("/pessoa/cadastrar/servidor")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarServidor(Servidor servidor);

	@POST
	@Path("/pessoa/cadastrar/servidorhabilitado")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarServidorHabilitado(Servidor servidor);

	@POST
	@Path("/pessoa/consultar/autorizacao")
	@Consumes("application/json")
	@Produces("application/json")
	public Response autorizarPessoa(Login login);

	@POST
	@Path("/pessoa/consultar/login")
	@Consumes("application/json")
	@Produces("application/json")
	public Response logarPessoa(Login login);

	@POST
	@Path("/pessoa/consultar/servidores")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Servidor> consultarServidores(Servidor servidor);

	@GET
	@Path("/pessoa/servidores/listar")
	@Produces("application/json")
	public List<Servidor> listarServidores();

	@GET
	@Path("/pessoa/consultar/servidor/{id}")
	@Produces("application/json")
	public Response consultarServidor(@PathParam("id") int idServidor);

	@GET
	@Path("/pessoa/consultar/coordenador/{id}")
	@Produces("application/json")
	public Response consultarCoordenador(@PathParam("id") int idCoordenador);

	@GET
	@Path("/pessoa/consultar/gestor/{id}")
	@Produces("application/json")
	public Response consultarGestor(@PathParam("id") int idGestor);

	@POST
	@Path("/pessoa/consultar/servidoreshabilitados/")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Servidor> consultarServidoresHabilitados(Servidor servidor);

	@GET
	@Path("/pessoa/consultar/servidorhabilitado/{siape}")
	@Produces("application/json")
	public Response buscarServidorHabilitado(@PathParam("siape") int siape);

	@POST
	@Path("/pessoa/consultar/discentes")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Discente> consultarDiscentes(Discente discente);

	@GET
	@Path("/pessoa/discentes/listar")
	@Produces("application/json")
	public List<Discente> listarDiscentes();

	@GET
	@Path("/pessoa/consultar/discente/{id}")
	@Produces("application/json")
	public Response consultarDiscente(@PathParam("id") int idDiscente);

	@POST
	@Path("/pessoa/consultar")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Pessoa> consultarPessoas(Pessoa pessoa);

	@GET
	@Path("/pessoa/consultar/{id}")
	@Produces("application/json")
	public Response consultarPessoa(@PathParam("id") int idPessoa);

	@GET
	@Path("/pessoa/consultar/{idPessoa}/{idTipoPessoa}")
	@Produces("application/json")
	public Response consultarPessoaPorTipo(@PathParam("idPessoa") int idPessoa,
			@PathParam("idTipoPessoa") int idTipoPessoa);

	@POST
	@Path("/pessoa/editar/discente")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarDiscente(Discente discente);

	@POST
	@Path("/pessoa/editar/servidor")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarServidor(Servidor servidor);

	// ----------------------------------------------------------------------------

	@POST
	@Path("/programainstitucional/cadastrar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarProgramaInstitucional(
			ProgramaInstitucional programaInstitucional);

	@POST
	@Path("/programainstitucional/cadastrar/recursoprogramainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarRecursoPrograma(
			RecursoProgramaInstitucional recurso);

	@POST
	@Path("/programainstitucional/consultar")
	@Consumes("application/json")
	@Produces("application/json")
	public List<ProgramaInstitucional> consultarProgramasInstitucionais(
			ProgramaInstitucional programaInstitucional);

	@GET
	@Path("/programainstitucional/listar")
	@Produces("application/json")
	public List<ProgramaInstitucional> listarProgramasInstitucionais();

	@GET
	@Path("/programainstitucional/consultar/{id}")
	@Produces("application/json")
	public Response consultarProgramaInstitucional(
			@PathParam("id") int idProgramaInstitucional);

	@POST
	@Path("/programainstitucional/consultar/recursosvalidos")
	@Consumes("application/json")
	@Produces("application/json")
	public List<RecursoProgramaInstitucional> listarRecursosValidosProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) throws SQLException;

	@POST
	@Path("/programainstitucional/consultar/tipoprogramainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public List<TipoProgramaInstitucional> consultarTipoProgramaInstitucional(
			TipoProgramaInstitucional tipoProgramaInstitucional) throws SQLException;

	@GET
	@Path("/programainstitucional/consultar/tipoprogramainstitucional/listar")
	@Produces("application/json")
	public List<TipoProgramaInstitucional> listarTipoProgramaInstitucional() throws SQLException;

	@GET
	@Path("/programainstitucional/consultar/tipoprogramainstitucional/{idtipoprogramainstitucional}")
	@Produces("application/json")
	public Response consultarTipoProgramaInstitucional(
			@PathParam("idtipoprogramainstitucional") int idTipoProgramaInstitucional);

	@POST
	@Path("/programainstitucional/editar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarProgramaInstitucional(
			ProgramaInstitucional programaInstitucional);

	// ---------------------------------------------------------------------------------------------

	@POST
	@Path("/projeto/cadastrar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarProjeto(Projeto projeto);

	@GET
	@Path("/projeto/consultar/id/{id}")
	@Produces("application/json")
	public Response consultarProjeto(@PathParam("id") int idProjeto);

	@GET
	@Path("/projeto/consultar/nome/{nome}")
	@Produces("application/json")
	public List<Projeto> consultarProjetos(@PathParam("nome") String nomeProjeto);

	@GET
	@Path("/projeto/listar")
	@Produces("application/json")
	public List<Projeto> listarProjetos();

	@POST
	@Path("/projeto/consultar/programainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarProjetos(
			ProgramaInstitucional programaInstitucional);

	@POST
	@Path("/projeto/consultar/edital")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarProjetos(Edital edital);

	@POST
	@Path("/projeto/consultar/pessoa")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Projeto> consultarProjetosPessoa(Pessoa pessoa);

	@POST
	@Path("/projeto/consultar/pessoas")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Projeto> consultarProjetosParticipantes(List<Pessoa> pessoas);

	@POST
	@Path("/projeto/consultar/projetoinformacoes")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarInformacoesProjeto(Projeto projeto);

	@GET
	@Path("/projeto/consultar/relatorio")
	@Produces("application/json")
	public Pizza relatorioQuantidadeProjetos();

	@GET
	@Path("/projeto/consultar/quantidadeprojetoscampus")
	@Produces("application/json")
	public List<Quadro> relatorioQuantidadeProjetosPorCampus();

	@POST
	@Path("/projeto/consultar/discentesprojeto")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarDiscentesProjeto(Projeto projeto);

	@POST
	@Path("/projeto/editar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarProjeto(Projeto projeto);

	// --------------------------------------------------------------------------------

	@GET
	@Path("/server/consulta/servidorOnline")
	@Produces("application/json")
	public Response servidorOnline();

	// --------------------------------------------------------------------------------

	@GET
	@Path("/titulacao/listar")
	@Produces("application/json")
	public List<Titulacao> listarTitulacoes();

	// ---------------------------------------------------------------------------------

	@POST
	@Path("/arquivo/upload/projeto/{idprojeto}/{tipoarquivoprojeto}")
	@Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=UTF-8")
	@Produces("application/json")
	public Response uploadArquivoProjeto(
			@PathParam("idprojeto") String idProjeto,
			@PathParam("tipoarquivoprojeto") TipoArquivoProjeto tipoArquivoProjeto,
			@MultipartForm FileUploadForm form);

	@POST
	@Path("/arquivo/upload/edital/{idedital}/{tipoarquivoedital}")
	@Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=UTF-8")
	@Produces("application/json")
	public Response uploadArquivoEdital(
			@PathParam("idedital") String idEdital,
			@PathParam("tipoarquivoedital") TipoArquivoEdital tipoArquivoEdital,
			@MultipartForm FileUploadForm form);
	
	@POST
	@Path("/arquivo/upload/participacao/{idparticipacao}/{tipoarquivoparticipacao}")
	@Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=UTF-8")
	@Produces("application/json")
	public Response uploadArquivoParticipacao(
			@PathParam("idparticipacao") String idParticipacao,
			@PathParam("tipoarquivoparticipacao") TipoArquivoParticipacao tipoArquivoParticipacao,
			@MultipartForm FileUploadForm form);

}