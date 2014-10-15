package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.Login;

/**
 * Definition: Contains the services interfaces of QManager.
 * 
 * @author Rhavy Maia Guedes
 * 
 */
public interface QManagerService {

	/**
	 * 
	 * @param negotiation
	 * @return
	 */
	@POST
	@Path("/consultar/fazerLogin")
	@Consumes("application/json")
	@Produces("application/json")
	public Response fazerLogin(Login login);

}