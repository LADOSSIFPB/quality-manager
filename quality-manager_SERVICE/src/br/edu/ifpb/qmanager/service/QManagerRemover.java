package br.edu.ifpb.qmanager.service;

import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.edu.ifpb.qmanager.dao.ProjetoDAO;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

/**
 * Classe que reune serviços para editar dados das entidades.
 * 
 * @author Igor Barbosa
 * @author Rhavy Maia
 * @author Emanuel Guimarães
 * @author Eri Jonhson
 * @author Felipe Nascimento
 * @author Ivanildo Terceiro
 * @version 0.1
 */
@Path("remover")
public class QManagerRemover {
	
	@PermitAll
	@GET
	@Path("/projeto/{id}")
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
