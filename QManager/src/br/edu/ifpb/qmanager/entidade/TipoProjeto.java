package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TipoProjeto {

	private int idTipoProjeto;
	private String nomeProjeto;
	private Date registro;

	public TipoProjeto() {
	}

	public TipoProjeto(int idTipoProjeto, String nomeProjeto) {
		this.idTipoProjeto = idTipoProjeto;
		this.nomeProjeto = nomeProjeto;
	}

	@XmlElement
	public int getIdTipoProjeto() {
		return idTipoProjeto;
	}

	public void setIdTipoProjeto(int idTipoProjeto) {
		this.idTipoProjeto = idTipoProjeto;
	}

	@XmlElement
	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
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
		return "TipoProjeto [idTipoProjeto=" + idTipoProjeto + ", nomeProjeto="
				+ nomeProjeto + "]";
	}

}
