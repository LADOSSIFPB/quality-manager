package br.edu.ifpb.qmanager.entidade;

import java.io.Serializable;

public class Turma implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idTurma;
	private int periodoLetivo;
	private char turno;
	private Curso curso;
	private String registro;

	public Turma() {
		curso = new Curso();
	}

	public Turma(int periodoLetivo, char turno, Curso curso) {
		setPeriodoLetivo(periodoLetivo);
		setTurno(turno);
		setCurso(curso);
	}

	public int getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}

	public int getPeriodoLetivo() {
		return periodoLetivo;
	}

	public void setPeriodoLetivo(int periodoLetivo) {
		this.periodoLetivo = periodoLetivo;
	}

	public char getTurno() {
		return turno;
	}

	public void setTurno(char turno) {
		this.turno = turno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "Turma [idTurma=" + idTurma + ", periodoLetivo=" + periodoLetivo
				+ ", turno=" + turno + ", curso=" + curso + ", registro="
				+ registro + "]";
	}

}
