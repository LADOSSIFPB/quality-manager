/**
 * 
 */
package br.edu.ifpb.entidades;

//Falta equals, toString e derivados

public class Discente {

	private String cpf;
	private String matricula;
	private String nome;
	private String Curso;
	private String Endereco;
	private String telefone;
	private String email;
	private String cep;
	
	//Construtor Discente
	public Discente(String cpf, String matricula, String nome, String curso, String endereco, String cep, String telefone, String email) {
		
		setCpf(cpf);
		setMatricula(matricula);
		setNome(nome);
		setCurso(curso);
		setEndereco(endereco);
		setTelefone(telefone);
		setEmail(email);
		setCep(cep);
		
	}
	
	public Discente(){
		this("", "", "", "", "", "", "", "");
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return Curso;
	}

	public void setCurso(String curso) {
		Curso = curso;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	
}
