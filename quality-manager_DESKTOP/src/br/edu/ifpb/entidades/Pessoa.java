package br.edu.ifpb.entidades;

// Campos da tabela
// CPF, matrícula, nome, endereço, CEP, telefone, e_mail

public abstract class Pessoa {
	
	private String CPF;
	private String Matrícula;
	private String Nome;
	private String Endereço;
	private String CEP;
	private String Telefone;
	private String Email;
	
	public Pessoa(String cpf, String matrícula, String nome, String endereço,
			String cep, String telefone, String email) {
		setCPF(cpf);
		setMatrícula(matrícula);
		setNome(nome);
		setEndereço(endereço);
		setCEP(cep);
		setTelefone(telefone);
		setEmail(email);
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cpf) {
		CPF = cpf;
	}

	public String getMatrícula() {
		return Matrícula;
	}

	public void setMatrícula(String matrícula) {
		Matrícula = matrícula;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getEndereço() {
		return Endereço;
	}

	public void setEndereço(String endereço) {
		Endereço = endereço;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cep) {
		CEP = cep;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
}
