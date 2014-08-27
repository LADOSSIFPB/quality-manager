package br.edu.ifpb.qmanager.entidade;

/*
 TABLE `instituicao_bancaria` (
 `id_instituicao_bancaria` INT NOT NULL AUTO_INCREMENT,
 `nm_banco` VARCHAR(45) NOT NULL,
 `nr_agencia` VARCHAR(6) NOT NULL
 */

public class InstituicaoBancaria {

	private int idInstituicaoBancaria;
	private String nomeBanco;
	
	public InstituicaoBancaria() {}
	
	public InstituicaoBancaria(String nomeBanco,
			String agencia) {
		this.nomeBanco = nomeBanco;
	}

	@Override
	public String toString() {
		return "-- Instituição Bancária --\n\nIdentificador Instituição Bancária= "
				+ idInstituicaoBancaria + "\nNome do Banco= " + nomeBanco;
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
}
