package br.edu.ifpb.qmanager.form;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class ProjetoFileForm extends FileUploadForm {

	@FormParam("idProjeto")
	@PartType(MediaType.TEXT_PLAIN)
	private int idProjeto;

	public int getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}	
}
