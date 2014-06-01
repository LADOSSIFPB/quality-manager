/**
 * 
 */
package br.edu.ifpb.entidades;

//Falta equals, toString e derivados

public class Discente {

	private String cpf;
	private String Matrícula;
	private String Nome;
	private String Curso;
	private String Endereço;
	private String Telefone;
	private String Email;
	private String CEP;
	
	//Construtor Discente
	public Discente(String cpf, String matrícula, String nome, String curso,
			String endereço, String cep, String telefone, String email) {
		
		setCpf(cpf);
		setMatrícula(matrícula);
		setNome(nome);
		setCurso(curso);
		setEndereço(endereço);
		setTelefone(telefone);
		setEmail(email);
		setCEP(cep);
		
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public String getCurso() {
		return Curso;
	}

	public void setCurso(String curso) {
		Curso = curso;
	}

	public String getEndereço() {
		return Endereço;
	}

	public void setEndereço(String endereço) {
		Endereço = endereço;
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

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}
	
	
	
}
