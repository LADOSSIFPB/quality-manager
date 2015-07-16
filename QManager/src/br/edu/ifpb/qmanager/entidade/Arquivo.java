package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.edu.ifpb.qmanager.tipo.TipoArquivo;

@XmlRootElement(name = "arquivo")
public class Arquivo {

	private int idArquivo;

	private String nomeRealArquivo;

	private String nomeSistemaArquivo;

	private String extensaoArquivo;

	private Pessoa cadastradorArquivo;

	private Date registro;

	private TipoArquivo tipoArquivo;

	private byte[] file;

	@XmlElement
	public int getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(int idArquivo) {
		this.idArquivo = idArquivo;
	}

	@XmlElement
	public String getNomeRealArquivo() {
		return nomeRealArquivo;
	}

	public void setNomeRealArquivo(String nomeRealArquivo) {
		this.nomeRealArquivo = nomeRealArquivo;
	}

	@XmlElement
	public String getNomeSistemaArquivo() {
		return nomeSistemaArquivo;
	}

	public void setNomeSistemaArquivo(String nomeSistemaArquivo) {
		this.nomeSistemaArquivo = nomeSistemaArquivo;
	}

	@XmlElement
	public String getExtensaoArquivo() {
		return extensaoArquivo;
	}

	public void setExtensaoArquivo(String extensaoArquivo) {
		this.extensaoArquivo = extensaoArquivo;
	}

	@XmlElement
	public Pessoa getCadastradorArquivo() {
		return cadastradorArquivo;
	}

	public void setCadastradorArquivo(Pessoa cadastradorArquivo) {
		this.cadastradorArquivo = cadastradorArquivo;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@XmlElement
	public TipoArquivo getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(TipoArquivo tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	@XmlElement
	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

}
