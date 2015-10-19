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
import br.edu.ifpb.qmanager.dao.InstituicaoBancariaDAO;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.MapErroQManager;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.validacao.Validar;

@Path("instituicaobancaria")
public class InstituicaoBancariaController {
	
	/**
	 * Cadastra uma Instituição Bancária.
	 * 
	 * @param InstituicaoBancaria
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/cadastrar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarInstituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.instituicaoBancaria(instituicaoBancaria);

		if (validacao == Validar.VALIDACAO_OK) {

			try {

				int idInstituicaoBancaria = InstituicaoBancariaDAO
						.getInstance().insert(instituicaoBancaria);

				if (idInstituicaoBancaria != BancoUtil.IDVAZIO) {

					instituicaoBancaria
							.setIdInstituicaoBancaria(idInstituicaoBancaria);
					builder.status(Response.Status.OK);
					builder.entity(instituicaoBancaria);

				} else {
					
					builder.status(Response.Status.NOT_ACCEPTABLE);
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
	
	@PermitAll
	@POST
	@Path("/consultar/instituicoesbancarias")
	@Consumes("application/json")
	@Produces("application/json")
	public List<InstituicaoBancaria> consultarInstituicoesBancarias(
			InstituicaoBancaria instituicaoBancaria) throws SQLException {

		List<InstituicaoBancaria> instituicoesBancarias = new ArrayList<InstituicaoBancaria>();

		instituicoesBancarias = InstituicaoBancariaDAO.getInstance().find(
				instituicaoBancaria);

		return instituicoesBancarias;
	}

	@PermitAll
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<InstituicaoBancaria> listarInstituicoesBancarias()
			throws SQLException {

		List<InstituicaoBancaria> instituicoesBancarias = new ArrayList<InstituicaoBancaria>();

		instituicoesBancarias = InstituicaoBancariaDAO.getInstance().getAll();

		return instituicoesBancarias;
	}

	@PermitAll
	@GET
	@Path("/consultar/{id}")
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
	@Path("/editar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarInstituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.instituicaoBancaria(instituicaoBancaria);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				InstituicaoBancariaDAO.getInstance()
						.update(instituicaoBancaria);

				builder.status(Response.Status.OK);
				builder.entity(instituicaoBancaria);

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
