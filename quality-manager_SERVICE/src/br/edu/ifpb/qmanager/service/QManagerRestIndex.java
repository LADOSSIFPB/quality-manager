package br.edu.ifpb.qmanager.service;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.qmanager.entidade.Pessoa;

/**
 * Root of RESTful api. It provides login and logout. Also have method for
 * printing every method which is provides by RESTful api.
 * 
 * @author Rostislav Novak (Computing and Information Centre, CTU in Prague)
 * 
 */
@Path("/")
public class QManagerRestIndex {
    
	private static Logger logger = LogManager.getLogger(QManagerRestIndex.class);

    @javax.ws.rs.core.Context public static ServletContext servletContext;

    /**
     * Return html page with information about REST api. It contains methods all
     * methods provide by REST api.
     * 
     * @return HTML page which has information about all methods of REST api.
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() { 
    	// TODO Better graphics, add arguments to all methods. (limit, offset, item and so on)
        return "<html><title>QManager Service - Home</title>" +
                "<body>"
                	+ "<h1>QManager - Services </h1>"
                	+ "Server path: " + servletContext.getContextPath()
                + "</body></html> ";
    }

    /**
     * Method to login a user into REST API.
     * 
     * @param user
     *            User which will be logged in to REST API.
     * @return Returns response code OK and a token. Otherwise returns response
     *         code FORBIDDEN(403).
     */
    @POST
    @Path("/login")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response login(Pessoa pessoa) {
		return null;
	}

    /**
     * Method to logout a user from DSpace REST API. Removes the token and user from
     * TokenHolder.
     * 
     * @param headers
     *            Request header which contains the header named
     *            "rest-dspace-token" containing the token as value.
     * @return Return response OK, otherwise BAD_REQUEST, if there was a problem with
     *         logout or the token is incorrect.
     */
    @POST
    @Path("/logout")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response logout(@Context HttpHeaders headers) {
		return null;
	}

    /**
     * Method to check current status of the service and logged in user.
     * 
     * okay: true | false
     * authenticated: true | false
     * epersonEMAIL: user@example.com
     * epersonNAME: John Doe
     * @param headers
     * @return
     */
    @GET
    @Path("/status")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Status status(@Context HttpHeaders headers) throws UnsupportedEncodingException {
    	return null;
    }
}
