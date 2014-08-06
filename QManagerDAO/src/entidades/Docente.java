package entidades;

/*
 TABLE `docente`
 `pessoa_id` INT NOT NULL,
 `nm_titulacao` VARCHAR(45) NOT NULL,
 `nm_cargo` VARCHAR(45) NOT NULL,
 `nm_local_trabalho` VARCHAR(45) NOT NULL
 */

public class Docente extends Pessoa implements EntidadeIF {

	private String titulacao;
	private String cargo;
	private String localTrabalho;

	public Docente(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			int instituicaoBancariaId, String operacao, String conta,
			String titulacao, String cargo, String localTrabalho) {
		super(nomePessoa, cpf, matricula, endereco, cep, telefone, email,
				instituicaoBancariaId, operacao, conta);
		setTitulacao(titulacao);
		setCargo(cargo);
		setLocalTrabalho(localTrabalho);
	}

	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

}
