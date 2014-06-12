
package br.edu.ifpb.entidades;

// Campos da tabela
// turma

//Falta equals, toString e derivados

public class Discente extends Pessoa implements Entidade {
	
	private Curso Curso;
	private String Turma;
	
	public Discente(String cpf, String matrícula, String nome, String endereço,
			String cep, String telefone, String email, Curso curso, String turma){
		super(cpf, matrícula, nome, endereço, cep, telefone, email);
		setCurso(curso);
		setTurma(turma);
	}

	public Curso getCurso() {
		return Curso;
	}

	public void setCurso(Curso curso) {
		Curso = curso;
	}

	public String getTurma() {
		return Turma;
	}

	public void setTurma(String turma) {
		Turma = turma;
	}
	
}
