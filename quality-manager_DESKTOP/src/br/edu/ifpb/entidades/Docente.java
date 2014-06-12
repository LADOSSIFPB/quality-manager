package br.edu.ifpb.entidades;

// Campos da tabela
// cargo, titulação, local_trabalho

// Falta equals, toString e derivados

public class Docente extends Pessoa implements Entidade {

	private String Cargo;
	private String Titulação;
	private String LocalTrabalho;

	public Docente(String cpf, String matrícula, String nome, String endereço,
			String cep, String telefone, String email, String cargo,
			String titulação, String localTrabalho) {
		super(cpf, matrícula, nome, endereço, cep, telefone, email);
		setCargo(cargo);
		setTitulação(titulação);
		setLocalTrabalho(localTrabalho);
	}

	public String getCargo() {
		return Cargo;
	}

	public void setCargo(String cargo) {
		Cargo = cargo;
	}

	public String getTitulação() {
		return Titulação;
	}

	public void setTitulação(String titulação) {
		Titulação = titulação;
	}

	public String getLocalTrabalho() {
		return LocalTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		LocalTrabalho = localTrabalho;
	}
	
}
