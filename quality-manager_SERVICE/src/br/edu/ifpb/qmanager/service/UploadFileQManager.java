package br.edu.ifpb.qmanager.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpUtils;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.edu.ifpb.qmanager.entidade.CodeErroQManager;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.FileUploadForm;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.MapErroQManager;
import br.edu.ifpb.qmanager.util.FileUtil;

/**
 * Serviço de upload de arquivo.
 * 
 * @author Rhavy
 *
 */
@Path("/arquivo")
public class UploadFileQManager {
	
	@POST
	@Path("/upload/{tipoupload}")
	@Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=UTF-8")
	@Produces("application/json")
	public Response uploadFile(@PathParam("tipoupload") String tipoUpload, 
			@MultipartForm FileUploadForm form) {

		// Tipos de uploads: projeto (pdf) e imagem (jpg e png).
		ResponseBuilder builder = Response.status(Response.Status.NOT_MODIFIED);
		builder.expires(new Date());
		
		try {
			
			System.out.println(tipoUpload);
			FileUtil.writeFile(form.getData(), form.getFileName());
			builder.status(Response.Status.OK);
			
		} catch (IOException e) {
			
			MapErroQManager me = new MapErroQManager(
					CodeErroQManager.FORMATO_ARQUIVO_INVALIDO);
			Erro erro = me.getErro();
			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}		

		return builder.build();
	}
}