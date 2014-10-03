package br.edu.ifpb.qmanager.entidade;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tipoPessoa")
public class TipoPessoa {

	private int idTipoPessoa;
	private String tipoPessoa;

	@XmlElement
	public int getIdTipoPessoa() {
		return idTipoPessoa;
	}

	public void setIdTipoPessoa(int idTipoPessoa) {
		this.idTipoPessoa = idTipoPessoa;
	}

	@XmlElement
	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

}
