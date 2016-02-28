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

import br.edu.ifpb.qmanager.entidade.Orientacao;
import br.edu.ifpb.qmanager.dao.OrientacaoDAO;
import br.edu.ifpb.qmanager.dao.BancoUtil;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.entidade.Erro;

@Path("orientacao")
public class OrientacaoController {
	/**
	 * Serviço para cadastrar Orientação.
	 * 
	 * @param Orientacao
	 * @return Response
	 */

	// Adicionar Validação

	@PermitAll
	@POST
	@Path("/cadastrar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarOrientacao(Orientacao orientacao) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {
			int idOrientacao = OrientacaoDAO.getInstance().insert(orientacao);
			if (idOrientacao != BancoUtil.IDVAZIO) {
				// Orientacao inserido com sucesso.
				orientacao.setIdOrientacao(idOrientacao);
				builder.status(Response.Status.OK);
				builder.entity(orientacao);
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
	@Path("/consultar/orientacao")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Orientacao> consultarOrientacoes(Orientacao orientacao) throws SQLException {

		List<Orientacao> orientacoes = new ArrayList<Orientacao>();

		orientacoes = OrientacaoDAO.getInstance().find(orientacao);
		return orientacoes;
	}

	@PermitAll
	@GET
	@Path("/consultar/orientacao/{id}")
	@Produces("application/json")
	public List<Orientacao> consultarOrientacao(@PathParam("id") int idDiscente) throws SQLException {

		List<Orientacao> orientacoes = new ArrayList<Orientacao>();

		orientacoes = OrientacaoDAO.getInstance().getByDiscente(idDiscente);

		return orientacoes;
	}

	@PermitAll
	@GET
	@Path("/orientacao/listar")
	@Produces("application/json")
	public List<Orientacao> listarOrientacoes() throws SQLException {

		List<Orientacao> orientacoes = new ArrayList<Orientacao>();

		orientacoes = OrientacaoDAO.getInstance().getAll();

		return orientacoes;
	}
}