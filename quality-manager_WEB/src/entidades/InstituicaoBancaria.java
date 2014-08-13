package entidades;

/*
 TABLE `instituicao_bancaria` (
 `id_instituicao_bancaria` INT NOT NULL AUTO_INCREMENT,
 `nm_banco` VARCHAR(45) NOT NULL,
 `nr_agencia` VARCHAR(6) NOT NULL
 */

public class InstituicaoBancaria implements EntidadeIF {

	private int idInstituicaoBancaria;
	private String nomeBanco;
	private String agencia;

	// construtor para read
	public InstituicaoBancaria(int idInstituicaoBancaria) {
		setIdInstituicaoBancaria(idInstituicaoBancaria);
	}
	
	// construtor para creat
	public InstituicaoBancaria(String nomeBanco,
			String agencia) {
		setNomeBanco(nomeBanco);
		setAgencia(agencia);
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

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	@Override
	public String toString() {
		return "-- Instituição Bancária --\n\nIdentificador Instituição Bancária= "
				+ idInstituicaoBancaria + "\nNome do Banco= " + nomeBanco
				+ "\nAgência= " + agencia + "\n\n--\n\n";
	}

}
