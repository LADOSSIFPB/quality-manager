package br.edu.ifpb.qmanager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.edu.ifpb.qmanager.entidade.Pessoa;

@Path("entidade")
public class EntidadeController {
	
	@PermitAll
	@GET
	@Path("/consultar")
	@Produces("application/json")
	public Response entidade() {

		ResponseBuilder builder = Response.status(Response.Status.OK);
		builder.expires(new Date());


		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		Pessoa pessoa1 = new Pessoa();
		pessoa1.setPessoaId(2);
		pessoas.add(pessoa1);

		Pessoa pessoa2 = new Pessoa();
		pessoa2.setPessoaId(3);
		pessoas.add(pessoa2);
		
		builder.entity(pessoas);

		return builder.build();
	}

}
