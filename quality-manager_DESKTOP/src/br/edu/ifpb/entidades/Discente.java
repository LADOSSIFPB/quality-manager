/**
 * 
 */
package br.edu.ifpb.entidades;

// Campos da tabela
// CPF, matrÝcula, nome, curso, enderešo, CEP, telefone, e_mail

//Falta equals, toString e derivados

public class Discente {

	private String CPF;
	private String MatrÝcula;
	private String Nome;
	private String Curso;
	private String Enderešo;
	private String CEP;
	private String Telefone;
	private String Email;
	
	//Construtor Discente
	public Discente(String cpf, String matrÝcula, String nome, String curso,
			String enderešo, String cep, String telefone, String email) {
		
		setCPF(cpf);
		setMatrÝcula(matrÝcula);
		setNome(nome);
		setCurso(curso);
		setEnderešo(enderešo);
		setCEP(cep);
		setTelefone(telefone);
		setEmail(email);
		
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cpf) {
		this.CPF = cpf;
	}

	public String getMatrÝcula() {
		return MatrÝcula;
	}

	public void setMatrÝcula(String matrÝcula) {
		MatrÝcula = matrÝcula;
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

	public String getEnderešo() {
		return Enderešo;
	}

	public void setEnderešo(String enderešo) {
		Enderešo = enderešo;
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
