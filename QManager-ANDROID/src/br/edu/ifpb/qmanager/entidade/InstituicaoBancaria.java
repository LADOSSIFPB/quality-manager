package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

public class InstituicaoBancaria {

	private int idInstituicaoBancaria;
	private String nomeBanco;
	private Date registro;

	public InstituicaoBancaria() {
	}

	public InstituicaoBancaria(String nomeBanco) {
		setNomeBanco(nomeBanco);
	}

	
	public int getIdInstituicaoBancaria() {
		return idInstituicaoBancaria;
	}

	public void setIdInstituicaoBancaria(int idInstituicaoBancaria) {
		this.idInstituicaoBancaria = idInstituicaoBancaria;
	}

	
	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	
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
