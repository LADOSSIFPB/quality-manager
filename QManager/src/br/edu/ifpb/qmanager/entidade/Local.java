package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "local")
public class Local {

	private int idLocal;
	private String nomeLocal;
	private Date registro;

	public Local() {
	}

	public Local(int idLocal, String nomeLocal) {
	}

	@XmlElement
	public int getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}

	@XmlElement
	public String getNomeLocal() {
		return nomeLocal;
	}

	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "Local [idLocal=" + idLocal + ", nomeLocal=" + nomeLocal + "]";
	}

}
