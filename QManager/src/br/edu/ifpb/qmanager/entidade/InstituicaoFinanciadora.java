package br.edu.ifpb.qmanager.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "instituicaoFinanciadora")
public class InstituicaoFinanciadora implements Serializable {

	private static final long serialVersionUID = 8673767195390286482L;

	private int idInstituicaoFinanciadora;
	
	private String cnpj;
	
	private String nomeInstituicaoFinanciadora;
	
	private String sigla;
	
	private Servidor gestor;
	
	private Date registro;

	public InstituicaoFinanciadora() {}
	
	public InstituicaoFinanciadora(String cnpj,
			String nomeInstituicaoFinanciadora, String sigla, Servidor gestor) {		
		this.cnpj = cnpj;
		this.nomeInstituicaoFinanciadora = nomeInstituicaoFinanciadora;
		this.sigla = sigla;
		this.gestor = gestor;
	}

	@XmlElement
	public int getIdInstituicaoFinanciadora() {
		return idInstituicaoFinanciadora;
	}

	public void setIdInstituicaoFinanciadora(int idInstituicaoFinanciadora) {
		this.idInstituicaoFinanciadora = idInstituicaoFinanciadora;
	}

	@XmlElement
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		cnpj = cnpj.replace(".", "");
		cnpj = cnpj.replace("/", "");
		cnpj = cnpj.replace("-", "");
		this.cnpj = cnpj;
	}

	@XmlElement
	public String getNomeInstituicaoFinanciadora() {
		return nomeInstituicaoFinanciadora;
	}

	public void setNomeInstituicaoFinanciadora(
			String nomeInstituicaoFinanciadora) {
		this.nomeInstituicaoFinanciadora = nomeInstituicaoFinanciadora;
	}

	@XmlElement
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@XmlElement
	public Servidor getGestor() {
		return gestor;
	}

	public void setGestor(Servidor gestor) {
		this.gestor = gestor;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}
}
