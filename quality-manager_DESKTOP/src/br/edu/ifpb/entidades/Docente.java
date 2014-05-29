package br.edu.ifpb.entidades;

// Coluna na tabela
// CPF, Matricula, Nome, Cargo, Endereco, CEP, Telefone, E_mail, Titulação, Local_de_Trabalho
// Falta equals, toString e derivados

public class Docente {
	
	private String CPF;
	private String Matricula;
	private String Nome;
	private String Cargo;
	private String Endereco;
	private String CEP;
	private String Telefone;
	private String E_mail;
	private String Titulação;
	private String Local_de_Trabalho;
	
	public Docente(String cpf, String matricula, String nome, String cargo, String endereco, String cep, String telefone, String e_mail, String titulação, String local_de_trabalho) {
		setCPF(cpf);
		setMatricula(matricula);
		setNome(nome);
		setCargo(cargo);
		setEndereco(endereco);
		setCEP(cep);
		setTelefone(telefone);
		setE_mail(e_mail);
		setTitulação(titulação);
		setLocal_de_Trabalho(local_de_trabalho);
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getMatricula() {
		return Matricula;
	}

	public void setMatricula(String matricula) {
		Matricula = matricula;
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

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
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

	public String getE_mail() {
		return E_mail;
	}

	public void setE_mail(String e_mail) {
		E_mail = e_mail;
	}

	public String getTitulação() {
		return Titulação;
	}

	public void setTitulação(String titulação) {
		Titulação = titulação;
	}

	public String getLocal_de_Trabalho() {
		return Local_de_Trabalho;
	}

	public void setLocal_de_Trabalho(String local_de_Trabalho) {
		Local_de_Trabalho = local_de_Trabalho;
	}
	
}
