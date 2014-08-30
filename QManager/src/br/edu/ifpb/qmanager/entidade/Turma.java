package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

public class Turma {

	private int idTurma;
	private int ano;
	private String turno;
	private Curso curso;
	private Date registro;

	public Turma() {
	}

	public Turma(int ano, String turno, Curso curso) {
		this.ano = ano;
		this.turno = turno;
		this.curso = curso;
	}

	public int getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "-- Turma --\n\nIdentificador da Turma= " + idTurma + "\nAno= "
				+ ano + "\nTurno= " + turno + "\nCurso= " + curso.getNomeCurso()
				+ "\n\n--\n\n";
	}

}
