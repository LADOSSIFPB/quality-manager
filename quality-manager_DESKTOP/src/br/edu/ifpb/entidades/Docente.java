package br.edu.ifpb.entidades;

// 		Campos na tabela
// CPF, Matricula, Nome, Cargo, Endereco, CEP, Telefone, E_mail, Titulação, Local_de_Trabalho

// Falta equals, toString e derivados

public class Docente {
	
	private String CPF;
	private String Matrícula;
	private String Nome;
	private String Cargo;
	private String Endereço;
	private String CEP;
	private String Telefone;
	private String Email;
	private String Titulação;
	private String LocalTrabalho;
	
	public Docente(String cpf, String matrícula, String nome, String cargo,
			String endereço, String cep, String telefone, String email,
			String titulação, String localtrabalho) {
		
		setCPF(cpf);
		setMatrícula(matrícula);
		setNome(nome);
		setCargo(cargo);
		setEndereço(endereço);
		setCEP(cep);
		setTelefone(telefone);
		setEmail(email);
		setTitulação(titulação);
		setLocalTrabalho(localtrabalho);
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
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

	public String getCargo() {
		return Cargo;
	}

	public void setCargo(String cargo) {
		Cargo = cargo;
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

	public void setCEP(String cEP) {
		CEP = cEP;
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
