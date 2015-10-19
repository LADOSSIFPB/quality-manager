package controller;

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
import br.edu.ifpb.qmanager.dao.InstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.dao.RecursoInstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.entidade.CodeErroQManager;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.MapErroQManager;
import br.edu.ifpb.qmanager.entidade.RecursoInstituicaoFinanciadora;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.validacao.Validar;

@Path("instituicaofinanciadora")
public class InstituicaoFinanciadoraController {
	
	/**
	 * Serviço para cadastrar Instituição Financiadora.
	 * 
	 * Consome:
	 * {
	 * 		"cnpj": "10783898000175",
	 * 		"nomeInstituicaoFinanciadora": "Instituto Federal de Educação, Ciência e Tecnologia da Paraíba",
	 * 		"sigla": "IFPB",
	 * 		"cadastrador": {"pessoaId": 1}
	 * }
	 * 
	 * Produz:
	 * {
	 * 		"idInstituicaoFinanciadora": 1,
	 * 		"cnpj": "10783898000175",
	 * 		"nomeInstituicaoFinanciadora": "Instituto Federal de Educação, Ciência e Tecnologia da Paraíba",
	 * 		"sigla": "IFPB",
	 * 		"cadastrador": {"pessoaId": 1}
	 * }
	 * 
	 * @param JSON instituicaoFinanciadora
	 * @return Response
	 */	
	@PermitAll
	@POST
	@Path("/cadastrar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarInstituicaoFinaciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.instituicaoFinanciadora(instituicaoFinanciadora);

		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.NOT_ACCEPTABLE).entity(
					erro.getErro());
			return builder.build();
		}

		try {
			// Verificar se o cnpj já foi cadastrado.
			boolean isCNPJCadastrado = InstituicaoFinanciadoraDAO.getInstance()
					.isCNPJCadastrado(instituicaoFinanciadora.getCnpj());

			if (isCNPJCadastrado) {
				MapErroQManager erro = new MapErroQManager(
						CodeErroQManager.INSTITUICAO_FINANCIADORA_JA_CADASTRADA);
				builder.status(Response.Status.NOT_ACCEPTABLE).entity(
						erro.getErro());
				return builder.build();
			}

			int idInstituicaoFinanciadora = InstituicaoFinanciadoraDAO
					.getInstance().insert(instituicaoFinanciadora);

			if (idInstituicaoFinanciadora != BancoUtil.IDVAZIO) {

				instituicaoFinanciadora
						.setIdInstituicaoFinanciadora(idInstituicaoFinanciadora);

				builder.status(Response.Status.OK);
				builder.entity(instituicaoFinanciadora);

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
	 * Serviço para cadastrar Recurso para Instituição Financiadora.
	 * 
	 * TODO: Perguntar para a cliente:
	 *  - No caso em que Recurso da Instituição Financiadora perde a validade, 
	 * o que acontece com os Recursos de Programas Institucionais a ele 
	 * associados?
	 * 
	 * Consome:
	 * {
	 * 		"orcamento": 5234.68,
	 * 		"validadeInicial": "2014-03-26", // java.util.Date ou java.sql.Date
	 * 		"validadeFinal": 1427342400000,  // java.util.Date ou java.sql.Date
	 * 		"instituicaoFinanciadora": {"idInstituicaoFinanciadora": 1}
	 * }
	 * 
	 * Produz:
	 * {
	 * 		"idRecursoIF": 1,
	 * 		"orcamento": 5234.68,
	 * 		"validadeInicial": 1395806400000, // java.util.Date
	 * 		"validadeFinal": 1427342400000,   // java.util.Date
	 * 		"instituicaoFinanciadora": {"idInstituicaoFinanciadora": 1},
	 * 		"recursoValido": true
	 * }
	 * 
	 * @param JSON recurso
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/cadastrar/recursoinstituicaofinanciadora")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarRecursoInstituicao(
			RecursoInstituicaoFinanciadora recurso) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.recursoInstituicaoFinanciadora(recurso);

		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.NOT_ACCEPTABLE).entity(
					erro.getErro());
			return builder.build();
		}

		try {

			int idRecurso = RecursoInstituicaoFinanciadoraDAO.getInstance().insert(recurso);

			if (idRecurso != BancoUtil.IDVAZIO) {

				recurso.setIdRecursoIF(idRecurso);

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
	@PermitAll
	@POST
	@Path("/consultar/instituicoesfinanciadoras")
	@Consumes("application/json")
	@Produces("application/json")
	public List<InstituicaoFinanciadora> consultarInstituicoesFinanciadoras(
			InstituicaoFinanciadora instituicaoFinanciadora)
			throws SQLException {

		List<InstituicaoFinanciadora> instituicoesFinanciadoras = new ArrayList<InstituicaoFinanciadora>();

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
	@PermitAll
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<InstituicaoFinanciadora> listarInstituicoesFinanciadoras()
			throws SQLException {

		List<InstituicaoFinanciadora> instituicoesFinanciadoras = new ArrayList<InstituicaoFinanciadora>();

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
	@PermitAll
	@GET
	@Path("/consultar/{id}")
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
	@PermitAll
	@GET
	@Path("/consultar/recursos/listar")
	@Produces("application/json")
	public List<RecursoInstituicaoFinanciadora> listarRecursosInstituicaoFinanciadora()
			throws SQLException {

		List<RecursoInstituicaoFinanciadora> recurso = new ArrayList<RecursoInstituicaoFinanciadora>();

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
	@PermitAll
	@GET
	@Path("/consultar/recurso/{id}")
	@Produces("application/json")
	public Response consultarRecursoInstituicaoFinanciadora(
			@PathParam("id") int idRecurso) {

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
	
	@PermitAll
	@POST
	@Path("/consultar/recursosvalidos")
	@Consumes("application/json")
	@Produces("application/json")
	public List<RecursoInstituicaoFinanciadora> consultarRecursosValidosInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora)
			throws SQLException {

		List<RecursoInstituicaoFinanciadora> recurso = new ArrayList<RecursoInstituicaoFinanciadora>();

		recurso = RecursoInstituicaoFinanciadoraDAO.getInstance()
				.getByOrcamentoValido(instituicaoFinanciadora);

		return recurso;
	}
	
	@PermitAll
	@POST
	@Path("/consultar/recursos")
	@Consumes("application/json")
	@Produces("application/json")
	public List<RecursoInstituicaoFinanciadora> consultarRecursosInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora)
			throws SQLException {

		List<RecursoInstituicaoFinanciadora> recurso = new ArrayList<RecursoInstituicaoFinanciadora>();

		recurso = RecursoInstituicaoFinanciadoraDAO.getInstance()
				.getAllByInstituicaoFinanciadora(instituicaoFinanciadora);

		return recurso;
	}
	
	@POST
	@Path("/editar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.instituicaoFinanciadora(instituicaoFinanciadora);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				InstituicaoFinanciadoraDAO.getInstance().update(
						instituicaoFinanciadora);

				builder.status(Response.Status.OK);
				builder.entity(instituicaoFinanciadora);

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
	@Path("/editar/recursoinstituicaofinanciadora")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarRecursoInstituicaoFinanciadora(
			RecursoInstituicaoFinanciadora recurso) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.recursoInstituicaoFinanciadora(recurso);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				RecursoInstituicaoFinanciadoraDAO.getInstance().update(recurso);

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
