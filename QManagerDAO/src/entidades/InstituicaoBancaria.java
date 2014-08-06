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

}
