package br.edu.ifpb.qmanager.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.http.HttpStatus;

import br.edu.ifpb.qmanager.dao.CampusDAO;
import br.edu.ifpb.qmanager.dao.CargoServidorDAO;
import br.edu.ifpb.qmanager.dao.CursoDAO;
import br.edu.ifpb.qmanager.dao.DepartamentoDAO;
import br.edu.ifpb.qmanager.dao.DiscenteDAO;
import br.edu.ifpb.qmanager.dao.EditalDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoBancariaDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.dao.ParticipacaoDAO;
import br.edu.ifpb.qmanager.dao.PessoaDAO;
import br.edu.ifpb.qmanager.dao.PessoaHabilitadaDAO;
import br.edu.ifpb.qmanager.dao.ProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.dao.ProjetoDAO;
import br.edu.ifpb.qmanager.dao.RecursoInstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.dao.RecursoProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.dao.ServidorDAO;
import br.edu.ifpb.qmanager.dao.TipoParticipacaoDAO;
import br.edu.ifpb.qmanager.dao.TitulacaoDAO;
import br.edu.ifpb.qmanager.dao.TurmaDAO;
import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.entidade.CodeErroQManager;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Departamento;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.MapErroQManager;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.RecursoInstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.RecursoProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoParticipacao;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.entidade.Titulacao;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
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
 * @version 1.0
 */
@Path("consultar")
public class QManagerConsultar {

	@GET
	@Path("/entidade")
	@Produces("application/json")
	public Response entidade() {

		ResponseBuilder builder = Response.status(Response.Status.OK);
		builder.expires(new Date());
		
		Object entity = new Object();
		
		builder.entity(entity);

		return builder.build();
	}

	/**
	 * Serviço que permite ao Usuário logar no sistema.
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

				if (pessoa != null) {

					builder.status(HttpStatus.SC_ACCEPTED);
					builder.entity(pessoa);

				} else {

					builder.status(HttpStatus.SC_UNAUTHORIZED);
				}

			} catch (SQLExceptionQManager qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
						.entity(erro);
			}
		} else {

			MapErroQManager mapErro = new MapErroQManager(validacao);
			builder.status(Response.Status.BAD_REQUEST).entity(
					mapErro.getErro());
		}

		return builder.build();
	}

	/**
	 * Serviço que consulta todas as Instituições Financiadoras cadastradas por
	 * nome específico.
	 * 
	 * @author Igor Barbosa
	 * @author Rhavy Maia
	 * @author Emanuel Guimarães
	 * @author Eri Jonhson
	 * @author Felipe Nascimento
	 * @author Ivanildo Terceiro
	 * @version 1.0
	 */
	@POST
	@Path("/instituicoesfinanciadoras")
	@Consumes("application/json")
	@Produces("application/json")
	public List<InstituicaoFinanciadora> consultarInstituicoesFinanciadoras(
			InstituicaoFinanciadora instituicaoFinanciadora)
			throws SQLException {

		List<InstituicaoFinanciadora> instituicoesFinanciadoras =
				new ArrayList<InstituicaoFinanciadora>();

		instituicoesFinanciadoras = InstituicaoFinanciadoraDAO.getInstance()
				.find(instituicaoFinanciadora);

		return instituicoesFinanciadoras;
	}

	/**
	 * Serviço que consulta todas as Instituições Financiadoras cadastradas.
	 * 
	 * @author Igor Barbosa
	 * @author Rhavy Maia
	 * @author Emanuel Guimarães
	 * @author Eri Jonhson
	 * @author Felipe Nascimento
	 * @author Ivanildo Terceiro
	 * @version 1.0
	 * @throws SQLExceptionQManager
	 */
	@GET
	@Path("/instituicoesfinanciadoras/listar")
	@Produces("application/json")
	public List<InstituicaoFinanciadora> listarInstituicoesFinanciadoras()
			throws SQLException {

		List<InstituicaoFinanciadora> instituicoesFinanciadoras =
				new ArrayList<InstituicaoFinanciadora>();

		instituicoesFinanciadoras = InstituicaoFinanciadoraDAO.getInstance()
				.getAll();

		return instituicoesFinanciadoras;
	}

	/**
	 * Serviço que consulta uma Instituição Financiadora cadastrada pelo
	 * identificador.
	 * 
	 * @author Igor Barbosa
	 * @author Rhavy Maia
	 * @author Emanuel Guimarães
	 * @author Eri Jonhson
	 * @author Felipe Nascimento
	 * @author Ivanildo Terceiro
	 * @version 1.0
	 */
	@GET
	@Path("/instituicaofinanciadora/{id}")
	@Produces("application/json")
	public Response consultarInstituicao(
			@PathParam("id") int idInstituicaoFinanciadora) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			InstituicaoFinanciadora instituicoesFinanciadora = InstituicaoFinanciadoraDAO
					.getInstance().getById(idInstituicaoFinanciadora);

			builder.status(Response.Status.OK);
			builder.entity(instituicoesFinanciadora);

		} catch (SQLExceptionQManager qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	/**
	 * Serviço que consulta todos os Recursos de Instituições Financiadoras
	 * cadastrados.
	 * 
	 * @author Igor Barbosa
	 * @author Rhavy Maia
	 * @author Emanuel Guimarães
	 * @author Eri Jonhson
	 * @author Felipe Nascimento
	 * @author Ivanildo Terceiro
	 * @version 1.0
	 * @throws SQLExceptionQManager
	 */
	@GET
	@Path("/instituicaofinanciadora/recursos/listar")
	@Produces("application/json")
	public List<RecursoInstituicaoFinanciadora> listarRecursosInstituicaoFinanciadora()
			throws SQLException {

		List<RecursoInstituicaoFinanciadora> recurso = 
				new ArrayList<RecursoInstituicaoFinanciadora>();

		recurso = RecursoInstituicaoFinanciadoraDAO.getInstance().getAll();

		return recurso;
	}

	/**
	 * Serviço que consulta um Recurso de Instituição Financiadora cadastrado
	 * pelo identificador.
	 * 
	 * @author Igor Barbosa
	 * @author Rhavy Maia
	 * @author Emanuel Guimarães
	 * @author Eri Jonhson
	 * @author Felipe Nascimento
	 * @author Ivanildo Terceiro
	 * @version 1.0
	 */
	@GET
	@Path("/instituicaofinanciadora/recurso/{id}")
	@Produces("application/json")
	public Response consultarRecursoInstituicaoFinanciadora(@PathParam("id") int idRecurso) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			RecursoInstituicaoFinanciadora recurso = RecursoInstituicaoFinanciadoraDAO
					.getInstance().getById(idRecurso);

			builder.status(Response.Status.OK);
			builder.entity(recurso);

		} catch (SQLExceptionQManager qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}
	
	@GET
	@Path("/instituicaofinanciadora/recursosvalidos")
	@Produces("application/json")
	public List<RecursoInstituicaoFinanciadora> consultarRecursosValidosInstituicaoFinanciadora()
			throws SQLException {

		List<RecursoInstituicaoFinanciadora> recurso = 
				new ArrayList<RecursoInstituicaoFinanciadora>();

		recurso = RecursoInstituicaoFinanciadoraDAO.getInstance().getByOrcamentoValido();

		return recurso;
	}

	@POST
	@Path("/programasinstitucionais")
	@Consumes("application/json")
	@Produces("application/json")
	public List<ProgramaInstitucional> consultarProgramasInstitucionais(
			ProgramaInstitucional programaInstitucional) throws SQLException {

		List<ProgramaInstitucional> programasInstitucionais =
				new ArrayList<ProgramaInstitucional>();

		programasInstitucionais = ProgramaInstitucionalDAO.getInstance().find(
				programaInstitucional);

		Iterator<ProgramaInstitucional> lista = programasInstitucionais
				.iterator();

		// Recuperar instituição financiadora pra cada programa institucional.
		while (lista.hasNext()) {
			ProgramaInstitucional programaAtual = lista.next();
			
			int idInstituicaoFinanciadora = programaAtual.getInstituicaoFinanciadora()
					.getIdInstituicaoFinanciadora();
			InstituicaoFinanciadora instituicaoFinanciadora = 
					InstituicaoFinanciadoraDAO.getInstance().getById(
							idInstituicaoFinanciadora);
			
			programaAtual.setInstituicaoFinanciadora(instituicaoFinanciadora);
		}

		return programasInstitucionais;

	}

	@GET
	@Path("/programasinstitucionais/listar")
	@Produces("application/json")
	public List<ProgramaInstitucional> listarProgramasInstitucionais()
			throws SQLException {

		List<ProgramaInstitucional> programasInstitucionais = ProgramaInstitucionalDAO
				.getInstance().getAll();

		Iterator<ProgramaInstitucional> lista = programasInstitucionais
				.iterator();

		// Recuperar instituição financiadora pra cada programa institucional
		while (lista.hasNext()) {
			ProgramaInstitucional programaAtual = lista.next();
			int idInstituicaoFinanciadora = programaAtual
					.getInstituicaoFinanciadora()
					.getIdInstituicaoFinanciadora();
			InstituicaoFinanciadora instituicaoFinanciadora = InstituicaoFinanciadoraDAO
					.getInstance().getById(idInstituicaoFinanciadora);
			programaAtual.setInstituicaoFinanciadora(instituicaoFinanciadora);
		}

		return programasInstitucionais;
	}

	@GET
	@Path("/programainstitucional/{id}")
	@Produces("application/json")
	public Response consultarProgramaInstitucional(
			@PathParam("id") int idProgramaInstitucional) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			ProgramaInstitucional programaInstitucional = ProgramaInstitucionalDAO
					.getInstance().getById(idProgramaInstitucional);

			builder.status(Response.Status.OK);
			builder.entity(programaInstitucional);

		} catch (SQLExceptionQManager qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	/**
	 * Serviço que consulta todos os Recursos de Programa Institucional
	 * cadastrados.
	 * 
	 * @author Igor Barbosa
	 * @author Rhavy Maia
	 * @author Emanuel Guimarães
	 * @author Eri Jonhson
	 * @author Felipe Nascimento
	 * @author Ivanildo Terceiro
	 * @version 1.0
	 * @throws SQLExceptionQManager
	 */
	@GET
	@Path("/programainstitucional/recursos/listar")
	@Produces("application/json")
	public List<RecursoProgramaInstitucional> listarRecursosProgramaInstitucional()
			throws SQLException {

		List<RecursoProgramaInstitucional> recurso = 
				new ArrayList<RecursoProgramaInstitucional>();

		recurso = RecursoProgramaInstitucionalDAO.getInstance().getAll();

		return recurso;
	}

	/**
	 * Serviço que consulta um Recurso de Programa Institucional cadastrado pelo
	 * identificador.
	 * 
	 * @author Igor Barbosa
	 * @author Rhavy Maia
	 * @author Emanuel Guimarães
	 * @author Eri Jonhson
	 * @author Felipe Nascimento
	 * @author Ivanildo Terceiro
	 * @version 1.0
	 */
	@GET
	@Path("/programainstitucional/recurso/{id}")
	@Produces("application/json")
	public Response consultarRecursoProgramaInstitucional(@PathParam("id") int idRecurso) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			RecursoProgramaInstitucional recurso = RecursoProgramaInstitucionalDAO
					.getInstance().getById(idRecurso);

			builder.status(Response.Status.OK);
			builder.entity(recurso);

		} catch (SQLExceptionQManager qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/programainstitucional/recursosvalidos")
	@Produces("application/json")
	public List<RecursoProgramaInstitucional> listarRecursosValidosProgramaInstitucional()
			throws SQLException {

		List<RecursoProgramaInstitucional> recurso = 
				new ArrayList<RecursoProgramaInstitucional>();

		recurso = RecursoProgramaInstitucionalDAO.getInstance().getByOrcamentoValido();

		return recurso;
	}
	
	@POST
	@Path("/editais")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Edital> consultarEditais(Edital edital) throws SQLException {

		List<Edital> editais = new ArrayList<Edital>();

		editais = EditalDAO.getInstance().find(edital);

		Iterator<Edital> lista = editais.iterator();

		while (lista.hasNext()) {

			Edital editalAtual = lista.next();

			int idProgramaInstitucional = editalAtual
					.getProgramaInstitucional().getIdProgramaInstitucional();

			ProgramaInstitucional programaInstitucional = ProgramaInstitucionalDAO
					.getInstance().getById(idProgramaInstitucional);
			editalAtual.setProgramaInstitucional(programaInstitucional);
		}

		return editais;
	}

	@GET
	@Path("/editais/listar")
	@Produces("application/json")
	public List<Edital> listarEditais() throws SQLException {

		List<Edital> editais = new ArrayList<Edital>();

		editais = EditalDAO.getInstance().getAll();

		Iterator<Edital> lista = editais.iterator();

		while (lista.hasNext()) {

			Edital editalAtual = lista.next();

			int idProgramaInstitucional = editalAtual
					.getProgramaInstitucional().getIdProgramaInstitucional();
			ProgramaInstitucional programaInstitucional = ProgramaInstitucionalDAO
					.getInstance().getById(idProgramaInstitucional);

			editalAtual.setProgramaInstitucional(programaInstitucional);
		}

		return editais;
	}

	@GET
	@Path("/edital/{id}")
	@Produces("application/json")
	public Response consultarEdital(@PathParam("id") int idEdital) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Edital edital = EditalDAO.getInstance().getById(idEdital);

			builder.status(Response.Status.OK);
			builder.entity(edital);

		} catch (SQLExceptionQManager qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/editaisprogramainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarEditais(ProgramaInstitucional programaInstitucional) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.programaInstitucional(programaInstitucional);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				List<Edital> editais = EditalDAO.getInstance()
						.getByProgramaInstitucional(programaInstitucional);

				Iterator<Edital> lista = editais.iterator();

				while (lista.hasNext()) {
					Edital editalAtual = lista.next();
					editalAtual.setProgramaInstitucional(programaInstitucional);
				}

				builder.status(Response.Status.OK);
				builder.entity(editais);

			} catch (SQLExceptionQManager qme) {
				
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
			
		} else {
			
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro.getErro());
		}

		return builder.build();
	}

	@GET
	@Path("/editaisanos")
	@Produces("application/json")
	public List<Integer> consultarAnosEditais() throws SQLException {

		List<Integer> anosEditais = new ArrayList<Integer>();

		anosEditais = EditalDAO.getInstance().getAnosEditais();

		return anosEditais;
	}

	@GET
	@Path("/edital/{ano}")
	@Produces("application/json")
	public List<Edital> consultarEditalAno(@PathParam("ano") int anoEdital)
			throws SQLException {

		List<Edital> editais = new ArrayList<Edital>();

		editais = EditalDAO.getInstance().getByAno(anoEdital);

		return editais;
	}

	@POST
	@Path("/projetos")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Projeto> consultarProjetos(Projeto projeto) throws SQLException {

		List<Projeto> projetos = new ArrayList<Projeto>();

		projetos = ProjetoDAO.getInstance().find(projeto);

		return projetos;
	}

	@GET
	@Path("/projetos/listar")
	@Produces("application/json")
	public List<Projeto> listarProjetos() throws SQLException {

		List<Projeto> projetos = new ArrayList<Projeto>();

		projetos = ProjetoDAO.getInstance().getAll();

		return projetos;
	}

	@GET
	@Path("/projeto/{id}")
	@Produces("application/json")
	public Response consultarProjeto(@PathParam("id") int idProjeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Projeto projeto = ProjetoDAO.getInstance().getById(idProjeto);

			builder.status(Response.Status.OK);
			builder.entity(projeto);

		} catch (SQLExceptionQManager qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/projetosprogramainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
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

			} catch (SQLExceptionQManager qme) {
				
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
			
		} else {
			
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro.getErro());
		}

		return builder.build();
	}

	@POST
	@Path("/projetosedital")
	@Consumes("application/json")
	@Produces("application/json")
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

			} catch (SQLExceptionQManager qme) {
				
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro.getErro());
		}

		return builder.build();
	}

	@POST
	@Path("/projetospessoa")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Projeto> consultarProjetosPessoa(Pessoa pessoa)
			throws SQLExceptionQManager {

		List<Projeto> projetos = ProjetoDAO.getInstance().getByPessoa(pessoa);

		return projetos;
	}

	@POST
	@Path("/projetoinformacoes")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarInformacoesProjeto(Projeto projeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.projeto(projeto);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				Edital edital = EditalDAO.getInstance().getById(
						projeto.getEdital().getIdEdital());

				projeto.setEdital(edital);

				int idCampus = projeto.getCampus().getIdCampusInstitucional();
				Campus campus = CampusDAO.getInstance().getById(idCampus);
				projeto.setCampus(campus);

				List<Participacao> participacoes = ParticipacaoDAO
						.getInstance().getByProjeto(projeto);

				Iterator<Participacao> listaParticipacao = participacoes
						.iterator();

				List<Discente> discentes = new LinkedList<Discente>();

				while (listaParticipacao.hasNext()) {

					Participacao participacaoAtual = listaParticipacao.next();

					int tipoParticipacao = participacaoAtual
							.getTipoParticipacao().getIdTipoParticipacao();
					int idMembroProjeto = participacaoAtual.getPessoa()
							.getPessoaId();

					if (tipoParticipacao == TipoParticipacao.TIPO_ORIENTANDO) {
						Discente discente = DiscenteDAO.getInstance().getById(
								idMembroProjeto);
						discentes.add(discente);
					} else if (tipoParticipacao == TipoParticipacao.TIPO_ORIENTADOR) {
						projeto.setOrientador(ServidorDAO.getInstance()
								.getById(idMembroProjeto));
					} else if (tipoParticipacao == TipoParticipacao.TIPO_COORIENTADOR) {
						projeto.setCoorientador(ServidorDAO.getInstance()
								.getById(idMembroProjeto));
					} else if (tipoParticipacao == TipoParticipacao.TIPO_COLABORADOR) {
						projeto.setColaborador(ServidorDAO.getInstance()
								.getById(idMembroProjeto));
					}
				}

				projeto.setDiscentes(discentes);

				builder.status(Response.Status.OK);
				builder.entity(projeto);

			} catch (SQLExceptionQManager qme) {
				
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro.getErro());
		}

		return builder.build();
	}

	@POST
	@Path("/servidores")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Servidor> consultarServidores(Servidor servidor)
			throws SQLException {

		List<Servidor> servidores = new ArrayList<Servidor>();

		servidores = ServidorDAO.getInstance().find(servidor);

		return servidores;
	}

	@GET
	@Path("/servidores/listar")
	@Produces("application/json")
	public List<Servidor> listarServidores() throws SQLException {

		List<Servidor> servidores = new ArrayList<Servidor>();

		servidores = ServidorDAO.getInstance().getAll();

		return servidores;
	}

	@GET
	@Path("/servidor/{id}")
	@Produces("application/json")
	public Response consultarServidor(@PathParam("id") int idServidor) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Servidor servidor = ServidorDAO.getInstance().getById(idServidor);

			builder.status(Response.Status.OK);
			builder.entity(servidor);

		} catch (SQLExceptionQManager qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}
	
	@GET
	@Path("/orientadores/listar")
	@Produces("application/json")
	public List<Servidor> listarOrientadores() throws SQLExceptionQManager {

		List<Servidor> servidores = new ArrayList<Servidor>();

		List<Servidor> pesquisa = ServidorDAO.getInstance().getServidoresPesquisa();
		List<Servidor> extensao = ServidorDAO.getInstance().getServidoresExtensao();
		
		servidores.addAll(pesquisa);
		servidores.addAll(extensao);

		return servidores;

	}
	
	@POST
	@Path("/orientadoresprojeto")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarOrientadoresProjeto(Projeto projeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.projeto(projeto);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				List<Servidor> servidores = ServidorDAO.getInstance()
						.getByProjeto(projeto);

				builder.status(Response.Status.OK);
				builder.entity(servidores);

			} catch (SQLExceptionQManager qme) {
				
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
			
		} else {
			
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro.getErro());
		}

		return builder.build();
	}

	@GET
	@Path("/orientadorespesquisa")
	@Produces("application/json")
	public List<Servidor> consultarOrientadoresPesquisa() throws SQLExceptionQManager {

		List<Servidor> servidores = new ArrayList<Servidor>();

		servidores = ServidorDAO.getInstance().getServidoresPesquisa();

		return servidores;

	}

	@GET
	@Path("/orientadoresextensao")
	@Produces("application/json")
	public List<Servidor> consultarOrientadoresExtensao() throws SQLExceptionQManager {

		List<Servidor> servidores = new ArrayList<Servidor>();

		servidores = ServidorDAO.getInstance().getServidoresExtensao();

		return servidores;

	}

	@GET
	@Path("/orientadorespesquisa/{ano}")
	@Produces("application/json")
	public Response consultarOrientadoresPesquisa(@PathParam("ano") int ano) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Servidor> servidores = ServidorDAO.getInstance()
					.getServidoresPesquisa(ano);

			builder.status(Response.Status.OK);
			builder.entity(servidores);

		} catch (SQLExceptionQManager qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/orientadoresextensao/{ano}")
	@Produces("application/json")
	public Response consultarOrientadoresExtensao(@PathParam("ano") int ano) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Servidor> servidores = ServidorDAO.getInstance()
					.getServidoresExtensao(ano);

			builder.status(Response.Status.OK);
			builder.entity(servidores);

		} catch (SQLExceptionQManager qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/coordenadores")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Servidor> consultarCoordenadores(Servidor servidor)
			throws SQLException {

		List<Servidor> servidores = new ArrayList<Servidor>();

		servidores = ServidorDAO.getInstance().findCoordenadores(servidor);

		return servidores;
	}

	@GET
	@Path("/coordenadores/listar")
	@Produces("application/json")
	public List<Servidor> listarCoordenadores() throws SQLException {

		List<Servidor> coordenadores = new ArrayList<Servidor>();

		coordenadores = ServidorDAO.getInstance().getAllCoordenadores();

		return coordenadores;
	}

	@GET
	@Path("/coordenador/{id}")
	@Produces("application/json")
	public Response consultarCoordenador(@PathParam("id") int idCoordenador) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Servidor coordenador = ServidorDAO.getInstance()
					.getCoordenadorById(idCoordenador);

			builder.status(Response.Status.OK);
			builder.entity(coordenador);

		} catch (SQLExceptionQManager qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/gestores")
	@Produces("application/json")
	public List<Servidor> consultarGestores(Servidor servidor) throws SQLException {

		List<Servidor> gestores = new ArrayList<Servidor>();

		gestores = ServidorDAO.getInstance().findGestores(servidor);

		return gestores;
	}
	
	@GET
	@Path("/gestores/listar")
	@Produces("application/json")
	public List<Servidor> listarGestores() throws SQLException {

		List<Servidor> gestores = new ArrayList<Servidor>();

		gestores = ServidorDAO.getInstance().getAllGestores();

		return gestores;
	}

	@GET
	@Path("/gestor/{id}")
	@Produces("application/json")
	public Response consultarGestor(@PathParam("id") int idGestor) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Servidor gestor = ServidorDAO.getInstance().getCoordenadorById(
					idGestor);

			builder.status(Response.Status.OK);
			builder.entity(gestor);

		} catch (SQLExceptionQManager qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}
	
	@POST
	@Path("/servidoreshabilitados/")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Servidor> consultarServidoresHabilitados(Servidor servidor)
			throws SQLException {

		List<Servidor> servidores = new ArrayList<Servidor>();

		servidores = PessoaHabilitadaDAO.getInstance().find(servidor);

		return servidores;
	}

	@GET
	@Path("/servidorhabilitado/{siape}")
	@Produces("application/json")
	public Response buscarServidorHabilitado(@PathParam("siape") int siape) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Servidor servidorHabilitado = PessoaHabilitadaDAO.getInstance()
					.getServidorByMatricula(siape);

			if (servidorHabilitado != null) {

				builder.status(Response.Status.OK);
				builder.entity(servidorHabilitado);

			} else {

				MapErroQManager mapErro = new MapErroQManager(
						CodeErroQManager.SERVIDOR_HABILITADO_INEXISTENTE);
				builder.status(Response.Status.NOT_FOUND).entity(
						mapErro.getErro());
			}
		} catch (SQLExceptionQManager qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/discentes")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Discente> consultarDiscentes(Discente discente)
			throws SQLException {

		List<Discente> discentes = new ArrayList<Discente>();

		discentes = DiscenteDAO.getInstance().find(discente);

		return discentes;
	}

	@GET
	@Path("/discentes/listar")
	@Produces("application/json")
	public List<Discente> listarDiscentes() throws SQLException {

		List<Discente> discentes = new ArrayList<Discente>();

		discentes = DiscenteDAO.getInstance().getAll();

		return discentes;
	}

	@GET
	@Path("/discente/{id}")
	@Produces("application/json")
	public Response consultarDiscente(@PathParam("id") int idDiscente) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Discente discente = DiscenteDAO.getInstance().getById(idDiscente);

			builder.status(Response.Status.OK);
			builder.entity(discente);

		} catch (SQLExceptionQManager qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/discentesprojeto")
	@Consumes("application/json")
	@Produces("application/json")
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

			} catch (SQLExceptionQManager qme) {
				
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro.getErro());
		}

		return builder.build();
	}

	@POST
	@Path("/instituicoesbancarias")
	@Consumes("application/json")
	@Produces("application/json")
	public List<InstituicaoBancaria> consultarInstituicoesBancarias(
			InstituicaoBancaria instituicaoBancaria) throws SQLException {

		List<InstituicaoBancaria> instituicoesBancarias = new ArrayList<InstituicaoBancaria>();

		instituicoesBancarias = InstituicaoBancariaDAO.getInstance().find(
				instituicaoBancaria);

		return instituicoesBancarias;
	}

	@GET
	@Path("/instituicoesbancarias/listar")
	@Produces("application/json")
	public List<InstituicaoBancaria> listarInstituicoesBancarias()
			throws SQLException {

		List<InstituicaoBancaria> instituicoesBancarias = 
				new ArrayList<InstituicaoBancaria>();

		instituicoesBancarias = InstituicaoBancariaDAO.getInstance().getAll();

		return instituicoesBancarias;
	}

	@GET
	@Path("/instituicaobancaria/{id}")
	@Produces("application/json")
	public Response consultarInstituicaoBancaria(
			@PathParam("id") int idInstituicaoBancaria) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			InstituicaoBancaria instituicaoBancaria = InstituicaoBancariaDAO
					.getInstance().getById(idInstituicaoBancaria);

			builder.status(Response.Status.OK);
			builder.entity(instituicaoBancaria);

		} catch (SQLExceptionQManager qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/cursos")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Curso> consultarCursos(Curso curso) throws SQLException {

		List<Curso> cursos = new ArrayList<Curso>();
		cursos = CursoDAO.getInstance().find(curso);

		return cursos;
	}

	@GET
	@Path("/cursos/listar")
	@Produces("application/json")
	public List<Curso> listarCursos() throws SQLException {

		List<Curso> cursos = new ArrayList<Curso>();

		cursos = CursoDAO.getInstance().getAll();

		return cursos;
	}

	@GET
	@Path("/curso/{idcurso}")
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
						CodeErroQManager.CURSO_INEXISTENTE).getErro();

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

	@GET
	@Path("/turmascoordenador/{id}")
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
	@Path("/cargos")
	@Consumes("application/json")
	@Produces("application/json")
	public List<CargoServidor> consultarCargos(CargoServidor cargoServidor)
			throws SQLException {

		List<CargoServidor> cargosServidor = new ArrayList<CargoServidor>();

		cargosServidor = CargoServidorDAO.getInstance().find(cargoServidor);

		return cargosServidor;
	}

	@GET
	@Path("/cargos/listar")
	@Produces("application/json")
	public List<CargoServidor> listarCargos() throws SQLException {

		List<CargoServidor> cargosServidor = new ArrayList<CargoServidor>();

		cargosServidor = CargoServidorDAO.getInstance().getAll();

		return cargosServidor;
	}

	@GET
	@Path("/cargo/{id}")
	@Produces("application/json")
	public Response consultarCargo(@PathParam("id") int idCargo) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			CargoServidor cargoServidor = CargoServidorDAO.getInstance()
					.getById(idCargo);

			builder.status(Response.Status.OK);
			builder.entity(cargoServidor);

		} catch (SQLExceptionQManager qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/tiposparticipacao")
	@Consumes("application/json")
	@Produces("application/json")
	public List<TipoParticipacao> consultarTiposParticipacao(
			TipoParticipacao tipoParticipacao) throws SQLException {

		List<TipoParticipacao> tiposParticipacoes = new ArrayList<TipoParticipacao>();

		tiposParticipacoes = TipoParticipacaoDAO.getInstance().find(
				tipoParticipacao);

		return tiposParticipacoes;
	}

	@GET
	@Path("/tiposparticipacao/listar")
	@Produces("application/json")
	public List<TipoParticipacao> listarTiposParticipacao() throws SQLException {

		List<TipoParticipacao> tiposParticipacoes = new ArrayList<TipoParticipacao>();

		tiposParticipacoes = TipoParticipacaoDAO.getInstance().getAll();

		return tiposParticipacoes;
	}

	@GET
	@Path("/tipoparticipacao/{id}")
	@Produces("application/json")
	public Response consultarTipoParticipacao(
			@PathParam("id") int idTipoParticipacao) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			TipoParticipacao tipoParticipacao = TipoParticipacaoDAO
					.getInstance().getById(idTipoParticipacao);

			builder.status(Response.Status.OK);
			builder.entity(tipoParticipacao);

		} catch (SQLExceptionQManager qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/pessoas")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Pessoa> consultarPessoas(Pessoa pessoa) throws SQLException {

		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		pessoas = PessoaDAO.getInstance().find(pessoa);

		return pessoas;
	}

	@GET
	@Path("/pessoa/{id}")
	@Produces("application/json")
	public Response consultarPessoa(@PathParam("id") int idPessoa) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Pessoa pessoa = PessoaDAO.getInstance().getById(idPessoa);

			builder.status(Response.Status.OK);
			builder.entity(pessoa);

		} catch (SQLExceptionQManager qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/pessoa/{idPessoa}/{idTipoPessoa}")
	@Produces("application/json")
	public Response consultarPessoaPorTipo(@PathParam("idPessoa") int idPessoa,
			@PathParam("idTipoPessoa") int idTipoPessoa) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Pessoa pessoa = PessoaDAO.getInstance().getById(idPessoa);

			if (pessoa != null) {

				builder.status(Response.Status.OK);

				int idTipoPessoaConsulta = pessoa.getTipoPessoa()
						.getIdTipoPessoa();

				if (idTipoPessoaConsulta == TipoPessoa.TIPO_SERVIDOR
						&& idTipoPessoaConsulta == idTipoPessoa) {

					Servidor servidor = ServidorDAO.getInstance().getById(
							pessoa.getPessoaId());
					builder.entity(servidor);

				} else if (idTipoPessoaConsulta == TipoPessoa.TIPO_DISCENTE
						&& idTipoPessoaConsulta == idTipoPessoa) {

					Discente discente = DiscenteDAO.getInstance().getById(
							pessoa.getPessoaId());
					builder.entity(discente);
				}
			}

		} catch (SQLExceptionQManager qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/locais")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Campus> consultarLocais(Campus campus) throws SQLException {

		List<Campus> campi = new ArrayList<Campus>();

		campi = CampusDAO.getInstance().find(campus);

		return campi;
	}

	@GET
	@Path("/locais/listar")
	@Produces("application/json")
	public List<Campus> listarLocais() throws SQLException {

		List<Campus> campus = new ArrayList<Campus>();

		campus = CampusDAO.getInstance().getAll();

		return campus;
	}

	@GET
	@Path("/local/{id}")
	@Produces("application/json")
	public Response consultarLocal(@PathParam("id") int idLocal) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Campus campus = CampusDAO.getInstance().getById(idLocal);

			if (campus != null) {

				builder.status(Response.Status.OK);
				builder.entity(campus);

			} else {

				MapErroQManager mapErro = new MapErroQManager(
						CodeErroQManager.CAMPUS_INEXISTENTE);
				builder.status(Response.Status.NOT_FOUND).entity(
						mapErro.getErro());
			}

		} catch (SQLExceptionQManager qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/titulacao/listar")
	@Produces("application/json")
	public List<Titulacao> listarTitulacoes() throws SQLException {

		List<Titulacao> titulacoes = new ArrayList<Titulacao>();

		titulacoes = TitulacaoDAO.getInstance().getAll();

		return titulacoes;
	}

	@GET
	@Path("/departamento/listar")
	@Produces("application/json")
	public List<Departamento> listarDepartamentos() throws SQLException {

		List<Departamento> departamentos = new ArrayList<Departamento>();

		departamentos = DepartamentoDAO.getInstance().getAll();

		return departamentos;
	}
}