package br.edu.ifpb.qmanager.entidade;

/*
 TABLE `docente`
 `pessoa_id` INT NOT NULL,
 `nm_titulacao` VARCHAR(45) NOT NULL,
 `nm_cargo` VARCHAR(45) NOT NULL,
 `nm_local_trabalho` VARCHAR(45) NOT NULL
 */

public class Docente extends Pessoa {

	private String titulacao;
	private String cargo;
	private String localTrabalho;

	public Docente() {
	}

	public Docente(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			String titulacao, String cargo, String localTrabalho,
			Usuario usuario, ContaBancaria contaBancaria) {
		super(nomePessoa, cpf, matricula, endereco, cep, telefone, email,
				usuario, contaBancaria);
		this.titulacao = titulacao;
		this.cargo = cargo;
		this.localTrabalho = localTrabalho;
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

	@Override
	public String toString() {
		return super.toString() + "-- Docente --\n\n Titulação= " + titulacao
				+ "\nCargo= " + cargo + "\nLocal de Trabalho= " + localTrabalho
				+ "\n\n--\n\n";
	}

}
