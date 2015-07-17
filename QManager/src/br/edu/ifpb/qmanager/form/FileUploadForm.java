package br.edu.ifpb.qmanager.form;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import br.edu.ifpb.qmanager.tipo.TipoArquivo;

public class FileUploadForm {
	
	@FormParam("uploadedFile")
	@PartType(MediaType.APPLICATION_OCTET_STREAM)
	private byte[] data;
	
	@FormParam("fileName")
	@PartType(MediaType.TEXT_PLAIN +";charset=UTF-8")
	private String fileName;
	
	@FormParam("idPessoa")
	@PartType(MediaType.TEXT_PLAIN)
	private int idPessoa;
	
	@FormParam("tipoArquivo")
	@PartType(MediaType.TEXT_PLAIN)
	private TipoArquivo tipoArquivo;
	
	public FileUploadForm() {}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public TipoArquivo getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(TipoArquivo tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}
}