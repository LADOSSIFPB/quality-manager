package br.edu.ifpb.qmanager.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
import br.edu.ifpb.qmanager.dao.CampusDAO;
import br.edu.ifpb.qmanager.dao.DiscenteDAO;
import br.edu.ifpb.qmanager.dao.EditalDAO;
import br.edu.ifpb.qmanager.dao.ParticipacaoDAO;
import br.edu.ifpb.qmanager.dao.ProjetoDAO;
import br.edu.ifpb.qmanager.dao.ServidorDAO;
import br.edu.ifpb.qmanager.dao.TipoProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.MapErroQManager;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoParticipacao;
import br.edu.ifpb.qmanager.entidade.TipoProgramaInstitucional;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.relatorios.Pizza;
import br.edu.ifpb.qmanager.relatorios.Quadro;
import br.edu.ifpb.qmanager.validacao.Validar;

@Path("projeto")
public class ProjetoController {
	
	/**
	 * Serviço para cadastrar Projeto.
	 * 
	 * Consumes:
	 * {
	 * 		"nomeProjeto": "Uma ferramenta de apoio à gestão de projetos para coordenadores de pesquisa no IFPB",
	 * 		"inicioProjeto": "2014-04-01", // java.util.Date ou java.sql.Date
	 * 		"fimProjeto": 1427857200000,   // java.util.Date ou java.sql.Date
	 * 		"processo": "1234567890123456780",
	 * 		"tipoProjeto": "P",
	 * 		"orcamento": 1500.25,
	 * 		"edital": {"idEdital": 1},
	 * 		"campus": {"idCampusInstitucional": 4},
	 * }
	 * 
	 * Produz:
	 * {
	 * 		"idProjeto": 1,
	 * 		"nomeProjeto": "Uma ferramenta de apoio à gestão de projetos para coordenadores de pesquisa no IFPB",
	 * 		"inicioProjeto": 1396321200000, // java.util.Date 
	 * 		"fimProjeto": 1427857200000,    // java.util.Date 
	 * 		"processo": "1234567890123456780",
	 * 		"tipoProjeto": "P",
	 * 		"nomeTipoProjeto": "Pesquisa",
	 * 		"orcamento": 1500.25,
	 * 		"edital": {"idEdital": 1},
	 * 		"campus": {"idCampusInstitucional": 4},
	 * }
	 * 
	 * @param JSON Projeto
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/cadastrar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarProjeto(Projeto projeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.projeto(projeto);

		if (validacao == Validar.VALIDACAO_OK) {

			try {

				int idEdital = projeto.getEdital().getIdEdital();
				Edital edital = EditalDAO.getInstance().getById(idEdital);
				
				//TODO Adicionar verificação de Edital não encontrado.
				projeto.setEdital(edital);
				
				//TODO Analisar disponibilidade de vagas no edital para o 
				// campus de submissão do orientador do projeto.
				
				int idProjeto = ProjetoDAO.getInstance().insert(projeto);

				if (idProjeto != BancoUtil.IDVAZIO) {
					
					// Identificador do Projeto.
					projeto.setIdProjeto(idProjeto);

					// Cadastrar Orientador do Projeto.
					Participacao participacaoOrientador = new Participacao();
					Servidor servidor = projeto.getOrientador();

					// Participação.
					participacaoOrientador.setPessoa(servidor);
					participacaoOrientador.setProjeto(projeto);
					participacaoOrientador.setInicioParticipacao(projeto
							.getInicioProjeto());
					participacaoOrientador.setBolsista(false);
					participacaoOrientador.setTipoParticipacao(
							new TipoParticipacao(TipoParticipacao.TIPO_ORIENTADOR));

					int idParticipacaoOrientador = ParticipacaoDAO.getInstance()
							.insert(participacaoOrientador);					

					if (idParticipacaoOrientador != BancoUtil.IDVAZIO) {
						
						participacaoOrientador.setIdParticipacao(
								idParticipacaoOrientador);
						
						builder.status(Response.Status.OK);
						builder.entity(projeto);
					}					
					
				} else {
					
					builder.status(Response.Status.NOT_MODIFIED);
					return builder.build();
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
			return builder.build();
		}

		return builder.build();
	}
	
	@PermitAll
	@GET
	@Path("/consultar/id/{id}")
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
	
	@PermitAll
	@GET
	@Path("/consultar/nome/{nome}")
	@Produces("application/json")
	public List<Projeto> consultarProjetos(@PathParam("nome") String nomeProjeto) 
			throws SQLException {

		List<Projeto> projetos = new ArrayList<Projeto>();

		projetos = ProjetoDAO.getInstance().getByNomeProjeto(nomeProjeto);

		return projetos;
	}

	@PermitAll
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Projeto> listarProjetos() throws SQLException {

		List<Projeto> projetos = new ArrayList<Projeto>();

		projetos = ProjetoDAO.getInstance().getAll();

		return projetos;
	}

	@PermitAll
	@POST
	@Path("/consultar/programainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarProjetos(
			ProgramaInstitucional programaInstitucional) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.programaInstitucional(programaInstitucional);

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

	@PermitAll
	@POST
	@Path("/consultar/edital")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarProjetos(Edital edital) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.edital(edital);

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

	@PermitAll
	@POST
	@Path("/consultar/pessoa")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Projeto> consultarProjetosPessoa(Pessoa pessoa)
			throws SQLExceptionQManager {

		List<Projeto> projetos = ProjetoDAO.getInstance().getByPessoa(pessoa);

		return projetos;
	}
	
	@PermitAll
	@POST
	@Path("/consultar/pessoas")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Projeto> consultarProjetosParticipantes(List<Pessoa> pessoas) 
			throws SQLException {

		List<Projeto> projetos = new ArrayList<Projeto>();

		projetos = ProjetoDAO.getInstance().getByPessoas(pessoas);

		return projetos;
	}

	@PermitAll
	@POST
	@Path("/consultar/projetoinformacoes")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarInformacoesProjeto(Projeto projeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.projeto(projeto);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				Edital edital = EditalDAO.getInstance().getById(
						projeto.getEdital().getIdEdital());

				projeto.setEdital(edital);

				int idCampus = projeto.getCampus().getIdCampusInstitucional();
				Campus campus = CampusDAO.getInstance().getById(idCampus);
				projeto.setCampus(campus);

				List<Participacao> participacoes = ParticipacaoDAO
						.getInstance().getByProjetoId(projeto.getIdProjeto());

				List<Discente> discentes = new ArrayList<Discente>();

				for (Participacao participacao: participacoes) {
					int tipoParticipacao = participacao
							.getTipoParticipacao().getIdTipoParticipacao();
					int idMembroProjeto = participacao.getPessoa()
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

	@PermitAll
	@GET
	@Path("/consultar/relatorio")
	@Produces("application/json")
	public Pizza relatorioQuantidadeProjetos()
			throws SQLExceptionQManager {

		Pizza pizza = new Pizza();
		List<TipoProgramaInstitucional> tiposProgramaInstitucional = TipoProgramaInstitucionalDAO
				.getInstance().getAll();
		
		// montar fatia para cada Tipo Programa Institucional
		for (TipoProgramaInstitucional tipoProgramaInstitucional : tiposProgramaInstitucional) {
			int quantidade = ProjetoDAO.getInstance().getQuantidadeProjetos(
					tipoProgramaInstitucional.getIdTipoProgramaInstitucional());
			pizza.addFatia(tipoProgramaInstitucional.getNomeTipoProgramaInstitucional(), quantidade);
		}

		return pizza;
	}
	
	@PermitAll
	@GET
	@Path("/consultar/quantidadeprojetoscampus")
	@Produces("application/json")
	public List<Quadro> relatorioQuantidadeProjetosPorCampus()
			throws SQLExceptionQManager {

		List<Quadro> quadros = new LinkedList<Quadro>();
		List<TipoProgramaInstitucional> tiposProgramaInstitucional = TipoProgramaInstitucionalDAO
				.getInstance().getAll();
		List<Campus> campi = CampusDAO.getInstance().getAll();
		
		// montar quadros para cada Tipo Programa Institucional de acordo com
		// Campus específico
		Quadro quadro;
		for (TipoProgramaInstitucional tipoProgramaInstitucional : tiposProgramaInstitucional) {
			quadro = new Quadro();
			quadro.setLabel(tipoProgramaInstitucional
					.getNomeTipoProgramaInstitucional());
			for (Campus campus : campi) {
				int quantidade = ProjetoDAO
						.getInstance()
						.getQuantidadeProjetos(
								tipoProgramaInstitucional.getIdTipoProgramaInstitucional(),
								campus.getIdCampusInstitucional());
				quadro.addBarra(campus.getNome(), quantidade);
			}
			quadros.add(quadro);
		}

		return quadros;
	}
	
	@PermitAll
	@GET
	@Path("/orientadores/listar")
	@Produces("application/json")
	public List<Servidor> listarOrientadores() throws SQLExceptionQManager {

		List<Servidor> servidores = new ArrayList<Servidor>();

		List<Servidor> pesquisa = ServidorDAO.getInstance()
				.getServidoresPesquisa();
		List<Servidor> extensao = ServidorDAO.getInstance()
				.getServidoresExtensao();

		servidores.addAll(pesquisa);
		servidores.addAll(extensao);

		return servidores;

	}

	@PermitAll
	@POST
	@Path("/consultar/orientadoresprojeto")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarOrientadoresProjeto(Projeto projeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.projeto(projeto);

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

	@PermitAll
	@GET
	@Path("/consultar/orientadorespesquisa")
	@Produces("application/json")
	public List<Servidor> consultarOrientadoresPesquisa()
			throws SQLExceptionQManager {

		List<Servidor> servidores = new ArrayList<Servidor>();

		servidores = ServidorDAO.getInstance().getServidoresPesquisa();

		return servidores;

	}

	@PermitAll
	@GET
	@Path("/consultar/orientadoresextensao")
	@Produces("application/json")
	public List<Servidor> consultarOrientadoresExtensao()
			throws SQLExceptionQManager {

		List<Servidor> servidores = new ArrayList<Servidor>();

		servidores = ServidorDAO.getInstance().getServidoresExtensao();

		return servidores;

	}

	@PermitAll
	@GET
	@Path("/consultar/orientadorespesquisa/{ano}")
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

	@PermitAll
	@GET
	@Path("/consultar/orientadoresextensao/{ano}")
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
	
	@PermitAll
	@POST
	@Path("/consultar/discentesprojeto")
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
	@Path("/editar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarProjeto(Projeto projeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.projeto(projeto);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				ProjetoDAO.getInstance().update(projeto);

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
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}
	
	@PermitAll
	@GET
	@Path("/remover/{id}")
	@Produces("application/json")
	public Response removerProjeto(
			@PathParam("id") int idProjeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			ProjetoDAO.getInstance().delete(idProjeto);
			builder.status(Response.Status.OK);

		} catch (SQLExceptionQManager qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

}
