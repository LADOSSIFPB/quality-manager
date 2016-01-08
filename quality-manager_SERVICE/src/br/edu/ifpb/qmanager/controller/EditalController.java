package br.edu.ifpb.qmanager.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import br.edu.ifpb.qmanager.dao.EditalCampusSubmissaoDAO;
import br.edu.ifpb.qmanager.dao.EditalDAO;
import br.edu.ifpb.qmanager.dao.RecursoProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.entidade.CodeErroQManager;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.EditalCampusSubmissao;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.MapErroQManager;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.RecursoProgramaInstitucional;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.validacao.Validar;

@Path("edital")
public class EditalController {

	/**
	 * Serviço para cadastrar Edital.
	 * 
	 * @param JSON
	 *            Edital
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/cadastrar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarEdital(Edital edital) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		// Validação para o cadastro do Edital.
		int validacao = Validar.edital(edital);

		if (validacao == Validar.VALIDACAO_OK) {

			try {

				// Verificação de orçamento disponível no Programa Institucional.
				boolean temOrcamentoDisponivel = temOrcamentoDisponivelEdital(edital);

				if (temOrcamentoDisponivel) {

					int idEdital = EditalDAO.getInstance().insert(edital);

					if (idEdital != BancoUtil.IDVAZIO) {

						edital.setIdEdital(idEdital);
						builder.status(Response.Status.OK);
						builder.entity(edital);
					}

				} else {

					MapErroQManager erro = new MapErroQManager(
							CodeErroQManager.ORCAMENTO_PROGRAMA_INSTITUCIONAL_INSUFICIENTE);
					builder.status(Response.Status.CONFLICT).entity(
							erro.getErro());
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
	 * Cadastrar os campi autorizados a submeterem ao Edital.
	 * 
	 * @param editalCampiSubmissao
	 * @throws SQLExceptionQManager
	 */
	@PermitAll
	@POST
	@Path("/cadastrar/editalcampisubmissao")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarEditalCampiSubmissao(
			List<EditalCampusSubmissao> editalCampiSubmissao)
			throws SQLExceptionQManager {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		if (editalCampiSubmissao.size() > 0) {

			List<EditalCampusSubmissao> editalCampiResponse = 
					new ArrayList<EditalCampusSubmissao>();

			for (EditalCampusSubmissao editalCampusSubmissao : editalCampiSubmissao) {

				// Validação do Campus para o Edital.
				int validacao = Validar.editalCampiSubmissao(
						editalCampusSubmissao);

				if (validacao == Validar.VALIDACAO_OK) {

					int idEditalCampusSubmissao = EditalCampusSubmissaoDAO
							.getInstance().insert(editalCampusSubmissao);

					editalCampusSubmissao
							.setIdEditalCampusSubmissao(idEditalCampusSubmissao);
					editalCampiResponse.add(editalCampusSubmissao);

					// Response: Sucesso
					builder.status(Response.Status.OK);
					builder.entity(editalCampiResponse);

				} else {

					// Problema na validação.
					MapErroQManager erro = new MapErroQManager(validacao);
					builder.status(Response.Status.NOT_ACCEPTABLE).entity(
							erro.getErro());

					// Interronper laço.
					break;
				}
			}
		}

		return builder.build();
	}

	private boolean temOrcamentoDisponivelEdital(Edital edital)
			throws SQLExceptionQManager {

		boolean orcamentoDisponivel = true;

		// Verificar se há orçamento do Programa Institucional válido para
		// Edital.
		int idProgramaInstitucional = edital.getProgramaInstitucional()
				.getIdProgramaInstitucional();

		List<RecursoProgramaInstitucional> resursos = RecursoProgramaInstitucionalDAO
				.getInstance().getAllByIdProgramaInstitucional(
						idProgramaInstitucional);

		if ((resursos == null) && (edital.getBolsaDiscente() > 0.0)
				&& (edital.getBolsaDocente() > 0.0)) {

			orcamentoDisponivel = false;

		} else {

			double valorOrcamentoTotal = 0;

			for (RecursoProgramaInstitucional recurso : resursos) {
				valorOrcamentoTotal += recurso.getOrcamento();
			}

			double valorOrcamentoPorEdital = calcularGastoPorEdital(edital);

			orcamentoDisponivel = (valorOrcamentoTotal - valorOrcamentoPorEdital) >= 0.0 ? true
					: false;
		}

		return orcamentoDisponivel;
	}

	private double calcularGastoPorEdital(Edital edital) {

		double valorGastoPorEdital;
		int quantidadeProjetosAprovados = edital
				.getQuantidadeProjetosAprovados();
		double bolsaDiscente = edital.getBolsaDiscente();
		int vagasBolsistasDiscentePorProjeto = edital
				.getVagasBolsistasDiscentePorProjeto();
		double bolsaDocente = edital.getBolsaDocente();
		int vagasBolsistasDocentePorProjeto = edital
				.getVagasBolsistasDocentePorProjeto();

		valorGastoPorEdital = quantidadeProjetosAprovados
				* ((bolsaDiscente * vagasBolsistasDiscentePorProjeto) + (bolsaDocente * vagasBolsistasDocentePorProjeto));

		return valorGastoPorEdital;
	}
	
	@PermitAll
	@POST
	@Path("/consultar")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Edital> consultarEditais(Edital edital) throws SQLException {

		List<Edital> editais = new ArrayList<Edital>();

		EditalDAO dao = EditalDAO.getInstance();
		editais = dao.find(edital);

		return editais;
	}
	
	@PermitAll
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Edital> listarEditais() throws SQLException {

		List<Edital> editais = new ArrayList<Edital>();

		editais = EditalDAO.getInstance().getAll();

		return editais;
	}
	
	@PermitAll
	@GET
	@Path("/consultar/{id}")
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
	
	@PermitAll
	@POST
	@Path("/consultar/editaisprogramainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarEditais(ProgramaInstitucional programaInstitucional) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //TODO: Validar.programaInstitucional(programaInstitucional);

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
	
	@PermitAll
	@GET
	@Path("/consultar/editaisanos")
	@Produces("application/json")
	public List<Integer> consultarAnosEditais() throws SQLException {

		List<Integer> anosEditais = new ArrayList<Integer>();

		anosEditais = EditalDAO.getInstance().getAnosEditais();

		return anosEditais;
	}
	
	@PermitAll
	@GET
	@Path("/consultar/ano/{ano}")
	@Produces("application/json")
	public List<Edital> consultarEditalAno(@PathParam("ano") int anoEdital)
			throws SQLException {

		List<Edital> editais = new ArrayList<Edital>();

		editais = EditalDAO.getInstance().getByAno(anoEdital);

		return editais;
	}
	
	@PermitAll
	@GET
	@Path("/consultar/proximonumero/{ano}")
	@Produces("application/json")
	public int consultarProximoNumeroEdital(@PathParam("ano") int anoEdital)
			throws SQLException {

		int proximoNumero = EditalDAO.getInstance().getProximoNumero(anoEdital);

		return proximoNumero;
	}
	
	@PermitAll
	@GET
	@Path("/consultar/campus/{idCampus}")
	@Produces("application/json")
	public List<Edital> listarEditaisCampus(@PathParam("idCampus") int idCampus) 
			throws SQLException {
		
		List<Edital> editais = new ArrayList<Edital>();
		
		editais = EditalDAO.getInstance().getEditaisByCampus(idCampus);

		return editais;		
	}
	
	@POST
	@Path("/editar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarEdital(Edital edital) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //TODO: Validar.edital(edital);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				EditalDAO.getInstance().update(edital);

				builder.status(Response.Status.OK);
				builder.entity(edital);

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
