package br.edu.ifpb.entidades;

// Campos da tabela
// ID_curso, nome

public class Curso {
	
	private int IDCurso;
	private String Nome;
	
	public Curso(int iDCurso, String nome) {
		setIDCurso(iDCurso);
		setNome(nome);
	}

	public int getIDCurso() {
		return IDCurso;
	}

	public void setIDCurso(int iDCurso) {
		IDCurso = iDCurso;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}
	
}
