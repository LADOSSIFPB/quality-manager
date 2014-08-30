package br.edu.ifpb.qmanager.entidade;

public class InstituicaoBancaria {

	private int idInstituicaoBancaria;
	private String nomeBanco;

	public InstituicaoBancaria() {
	}

	public InstituicaoBancaria(String nomeBanco) {
		this.nomeBanco = nomeBanco;
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

	@Override
	public String toString() {
		return "-- Instituição Bancária --\n\nIdentificador Instituição Bancária= "
				+ idInstituicaoBancaria
				+ "\nNome do Banco= "
				+ nomeBanco
				+ "\n\n--\n\n";
	}

}
