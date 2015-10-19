package br.edu.ifpb.qmanager.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import br.edu.ifpb.qmanager.dao.ProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.dao.RecursoInstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.dao.RecursoProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.dao.TipoProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.entidade.CodeErroQManager;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.MapErroQManager;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.RecursoProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.TipoProgramaInstitucional;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.validacao.Validar;

@Path("programainstitucional")
public class ProgramaInstitucionalController {
	
	/**
	 * Serviço para cadastrar Programa Institucional.
	 * 
	 * Consome:
	 * {
	 * 		"nomeProgramaInstitucional": "Programa Institucional de Bolsas de Extensão",
	 * 		"sigla": "PROBEXT",
	 * 		"instituicaoFinanciadora": {"idInstituicaoFinanciadora": 1},
	 * 		"gestor": {"pessoaId": 1}
	 * }
	 * 
	 * Produz:
	 * {
	 * 		"idProgramaInstitucional": 1,
	 * 		"nomeProgramaInstitucional": "Programa Institucional de Bolsas de Extensão",
	 * 		"sigla": "PROBEXT",
	 * 		"instituicaoFinanciadora": {"idInstituicaoFinanciadora": 1},
	 * 		"gestor": {"pessoaId": 1}
	 * }
	 * 
	 * @param JSON ProgramaInstitucional
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/cadastrar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.programaInstitucional(programaInstitucional);

		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.NOT_ACCEPTABLE).entity(
					erro.getErro());
			return builder.build();
		}

		try {

			int idProInstitucional = ProgramaInstitucionalDAO.getInstance()
					.insert(programaInstitucional);

			if (idProInstitucional != BancoUtil.IDVAZIO) {

				programaInstitucional
						.setIdProgramaInstitucional(idProInstitucional);

				builder.status(Response.Status.OK);
				builder.entity(programaInstitucional);

			} else {
				builder.status(Response.Status.NOT_ACCEPTABLE);
			}

		} catch (SQLExceptionQManager qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}
	
	/**
	 * Serviço para cadastrar Recurso para Programa Institucional.
	 * 
	 * Consome:
	 * {
	 * 		"orcamento": 150000000.12,
	 * 		"validadeInicial": "2014-03-30", // java.util.Date ou java.sql.Date
	 * 		"validadeFinal": 1427688000000,  // java.util.Date ou java.sql.Date
	 * 		"programaInstitucional": {"idProgramaInstitucional": 1},
	 * 		"recursoInstituicaoFinanciadora": {"idRecursoIF": 1}
	 * }
	 * 
	 * Produz:
	 * {
	 * 		"idRecursoPI": 1,
	 * 		"orcamento": 150000000.12,
	 * 		"validadeInicial": 1396152000000, // java.util.Date
	 * 		"validadeFinal": 1427688000000,   // java.util.Date
	 * 		"programaInstitucional": {"idProgramaInstitucional": 1},
	 * 		"recursoValido": true,
	 * 		"recursoInstituicaoFinanciadora": {"idRecursoIF": 1}
	 * }
	 * 
	 * @param JSON recurso
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/cadastrar/recursoprogramainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarRecursoPrograma(
			RecursoProgramaInstitucional recurso) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.recursoProgramaInstitucional(recurso);

		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.NOT_ACCEPTABLE).entity(
					erro.getErro());
			return builder.build();
		}

		try {

			int idRecurso = BancoUtil.IDVAZIO;

			// verificar se há orçamento válido para Programa Institucional a
			// ser cadastrado.
			int idRecursoIF = recurso.getRecursoInstituicaoFinanciadora()
					.getIdRecursoIF();

			double valorOrcamento = recurso.getOrcamento();

			boolean orcamentoDisponivel = RecursoInstituicaoFinanciadoraDAO
					.getInstance().verificaOrcamento(idRecursoIF,
							valorOrcamento);

			if (!orcamentoDisponivel) {
				MapErroQManager erro = new MapErroQManager(
						CodeErroQManager.ORCAMENTO_INSTITUICAO_FINANCIADORA_INSUFICIENTE);
				builder.status(Response.Status.CONFLICT).entity(erro.getErro());
				return builder.build();
			}

			idRecurso = RecursoProgramaInstitucionalDAO.getInstance().insert(
					recurso);

			if (idRecurso != BancoUtil.IDVAZIO) {

				recurso.setIdRecursoPI(idRecurso);

				builder.status(Response.Status.OK);
				builder.entity(recurso);

			} else {

				builder.status(Response.Status.NOT_ACCEPTABLE);
			}

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
	@Path("/consultar")
	@Consumes("application/json")
	@Produces("application/json")
	public List<ProgramaInstitucional> consultarProgramasInstitucionais(
			ProgramaInstitucional programaInstitucional) throws SQLException {

		List<ProgramaInstitucional> programasInstitucionais = 
				new ArrayList<ProgramaInstitucional>();

		programasInstitucionais = ProgramaInstitucionalDAO.getInstance().find(
				programaInstitucional);

		return programasInstitucionais;
	}
	
	@PermitAll
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<ProgramaInstitucional> listarProgramasInstitucionais()
			throws SQLException {

		List<ProgramaInstitucional> programasInstitucionais = 
				ProgramaInstitucionalDAO.getInstance().getAll();

		return programasInstitucionais;
	}
	
	@PermitAll
	@GET
	@Path("/consultar/{id}")
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
	@PermitAll
	@GET
	@Path("/recursos/listar")
	@Produces("application/json")
	public List<RecursoProgramaInstitucional> listarRecursosProgramaInstitucional()
			throws SQLException {

		List<RecursoProgramaInstitucional> recurso = new ArrayList<RecursoProgramaInstitucional>();

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
	@PermitAll
	@GET
	@Path("/consultar/recurso/{id}")
	@Produces("application/json")
	public Response consultarRecursoProgramaInstitucional(
			@PathParam("id") int idRecurso) {

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
	
	@PermitAll
	@POST
	@Path("/consultar/recursosvalidos")
	@Consumes("application/json")
	@Produces("application/json")
	public List<RecursoProgramaInstitucional> listarRecursosValidosProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) throws SQLException {

		List<RecursoProgramaInstitucional> recurso = new ArrayList<RecursoProgramaInstitucional>();

		int idProgramaInstitucional = programaInstitucional
				.getIdProgramaInstitucional();
		recurso = RecursoProgramaInstitucionalDAO.getInstance()
				.getAllByIdProgramaInstitucional(idProgramaInstitucional);

		return recurso;
	}
	
	@PermitAll
	@POST
	@Path("/consultar/tipoprogramainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public List<TipoProgramaInstitucional> consultarTipoProgramaInstitucional(
			TipoProgramaInstitucional tipoProgramaInstitucional)
			throws SQLException {

		List<TipoProgramaInstitucional> tiposProgramaInstitucional = new ArrayList<TipoProgramaInstitucional>();
		tiposProgramaInstitucional = TipoProgramaInstitucionalDAO.getInstance().find(
				tipoProgramaInstitucional);

		return tiposProgramaInstitucional;
	}

	@PermitAll
	@GET
	@Path("/consultar/tipoprogramainstitucional/listar")
	@Produces("application/json")
	public List<TipoProgramaInstitucional> listarTipoProgramaInstitucional()
			throws SQLException {

		List<TipoProgramaInstitucional> tiposProgramaInstitucional = new ArrayList<TipoProgramaInstitucional>();

		tiposProgramaInstitucional = TipoProgramaInstitucionalDAO.getInstance().getAll();

		return tiposProgramaInstitucional;
	}

	@PermitAll
	@GET
	@Path("/consultar/tipoprogramainstitucional/{idtipoprogramainstitucional}")
	@Produces("application/json")
	public Response consultarTipoProgramaInstitucional(
			@PathParam("idtipoprogramainstitucional") int idTipoProgramaInstitucional) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			TipoProgramaInstitucional tipoProgramaInstitucional = TipoProgramaInstitucionalDAO
					.getInstance().getById(idTipoProgramaInstitucional);

			if (tipoProgramaInstitucional != null) {
				// Curso encontrado
				builder.status(Response.Status.OK);
				builder.entity(tipoProgramaInstitucional);

			} else {

				// Tipo Programa Institucional não encontrado.
				builder.status(Response.Status.NOT_FOUND);
				Erro erro = new MapErroQManager(
						CodeErroQManager.TIPO_PROGRAMA_INSTITUCIONAL_INVALIDO).getErro();

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
	
	@POST
	@Path("/editar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarProgramaInstitucional(ProgramaInstitucional programaInstitucional) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.programaInstitucional(programaInstitucional);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				ProgramaInstitucionalDAO.getInstance().update(
						programaInstitucional);

				builder.status(Response.Status.OK);
				builder.entity(programaInstitucional);

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

	@POST
	@Path("/editar/recursoprogramainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarRecursoProgramaInstitucional(
			RecursoProgramaInstitucional recurso) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.recursoProgramaInstitucional(recurso);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				RecursoProgramaInstitucionalDAO.getInstance().update(recurso);

				builder.status(Response.Status.OK);
				builder.entity(recurso);

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
