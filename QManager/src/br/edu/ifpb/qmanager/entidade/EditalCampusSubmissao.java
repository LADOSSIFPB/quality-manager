package br.edu.ifpb.qmanager.entidade;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "editalCampusSubmissao")
public class EditalCampusSubmissao implements Serializable{

	private static final long serialVersionUID = -6075173225085115331L;

	private Edital edital;
	
	private Campus campus;
	
	private int quantidadeProjeto;

	@XmlElement
	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
	}

	@XmlElement
	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	@XmlElement
	public int getQuantidadeProjeto() {
		return quantidadeProjeto;
	}

	public void setQuantidadeProjeto(int quantidadeProjeto) {
		this.quantidadeProjeto = quantidadeProjeto;
	}	
}
