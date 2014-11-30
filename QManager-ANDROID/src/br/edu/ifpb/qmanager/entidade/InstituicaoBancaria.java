package br.edu.ifpb.qmanager.entidade;

import java.io.Serializable;

public class InstituicaoBancaria implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idInstituicaoBancaria;
	private String nomeBanco;
	private String cnpj;
	private String registro;

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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "InstituicaoBancaria [idInstituicaoBancaria="
				+ idInstituicaoBancaria + ", nomeBanco=" + nomeBanco
				+ ", registro=" + registro + "]";
	}

}
