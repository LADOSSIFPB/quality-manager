package br.edu.ifpb.ifopendoors.service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.ifopendoors.entity.Door;

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
@Path("door")
public class ConsultarIFOpenDoors {

	// URL e Contexto - Acesso ao Arduíno.
	private static final String APP_CONTEXT = "/door";
	
	private static final String URL_ARDUINO_SERVICE = "http://192.168.1.50:5534";
	
	private static Logger logger = LogManager.getLogger(RestIndexIFOpenDoors.class);
	
	/**
	 * Abertura da porta.
	 * 
	 * @param {'number':1}
	 * @return
	 */
	@POST
	@Path("/open")
	@Consumes("application/json")
	@Produces("application/json")
	public Response openDoor(Door door) {

		logger.info("Analisando requisição da porta");
		ResponseBuilder builder = Response.status(Response.Status.UNAUTHORIZED);
		builder.expires(new Date());
		
		logger.info("Comunicação com porta(arduíno): " + door.getNumber());
		Client client = ClientBuilder.newClient();
		Response response = client.target(URL_ARDUINO_SERVICE + APP_CONTEXT + "/open")
				.request().post(Entity.json("{'number':" + door.getNumber() + "}"));
		
		int status = response.getStatus();
		
		if (status == HttpStatus.SC_OK) {
			
			logger.info("Permissão concedida - Porta aberta");
			door.setOpen(true);
			door.setMensage("A porta está sendo aberta.");			
			builder.status(Response.Status.OK);
			
		} else {
			
			logger.info("Permissão negada - Porta aberta");		
			door.setOpen(false);
			door.setMensage("A porta não foi aberta.");
		}
		
		builder.entity(door);

		return builder.build();
	}

	@POST
	@Path("/closeDoor")
	@Produces("application/json")
	public Response closeDoor() {

		ResponseBuilder builder = Response.status(Response.Status.OK);
		builder.expires(new Date());

		Door door = new Door();
		door.setOpen(false);
		door.setMensage("Portão está sendo fechado.");
		builder.entity(door);

		return builder.build();
	}
}