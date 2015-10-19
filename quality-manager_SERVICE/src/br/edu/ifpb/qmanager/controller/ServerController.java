package br.edu.ifpb.qmanager.controller;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.edu.ifpb.qmanager.entidade.Server;

@Path("server")
public class ServerController {

	/**
	 * Verifica se o servidor está apto pra responder.
	 * 
	 * @return Response
	 */
	@GET
	@Path("/consulta/servidorOnline")
	@Produces("application/json")
	public Response servidorOnline() {

		ResponseBuilder builder = Response.status(Response.Status.OK);
		builder.expires(new Date());

		Server server = new Server();
		server.setOnline(true);
		builder.entity(server);

		return builder.build();
	}
	
}
