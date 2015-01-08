package br.edu.ifpb.qmanager.service;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.edu.ifpb.qmanager.entidade.FileUploadForm;
import br.edu.ifpb.qmanager.util.FileUtil;

/**
 * Serviço de upload de arquivo.
 * 
 * @author Rhavy
 *
 */
@Path("/arquivo")
public class UploadFileQManager {

	private static String PATH = "C:\\Java\\web\\uploadFile\\";
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@MultipartForm FileUploadForm form) {

		try {
			System.out.println(form.getFileName());
			FileUtil.writeFile(form.getData(), form.getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Done");
		return Response
				.status(200)
				.entity("uploadFile is called, Uploaded file name : "
						+ form.getFileName()).build();

	}
}