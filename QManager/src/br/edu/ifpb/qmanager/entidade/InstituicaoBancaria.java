package br.edu.ifpb.qmanager.entidade;

import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="edital")
public class InstituicaoBancaria {

	private int idInstituicaoBancaria;
	private String nomeBanco;
	private Date registro;

	public InstituicaoBancaria() {
	}

	public InstituicaoBancaria(String nomeBanco) {
		setNomeBanco(nomeBanco);
	}

	@XmlElement
	public int getIdInstituicaoBancaria() {
		return idInstituicaoBancaria;
	}

	public void setIdInstituicaoBancaria(int idInstituicaoBancaria) {
		this.idInstituicaoBancaria = idInstituicaoBancaria;
	}

	@XmlElement
	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
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
		return "InstituicaoBancaria [idInstituicaoBancaria="
				+ idInstituicaoBancaria + ", nomeBanco=" + nomeBanco
				+ ", registro=" + registro + "]";
	}
	
}
