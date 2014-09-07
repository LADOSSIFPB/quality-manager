package br.edu.ifpb.qmanager.entidade;

import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="turma")
public class Turma {

	private int idTurma;
	private int ano;
	private char turno;
	private Curso curso;
	private Date registro;

	public Turma() {
	}

	public Turma(int ano, char turno, Curso curso) {
		setAno(ano);
		setTurno(turno);
		setCurso(curso);
	}

	@XmlElement
	public int getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}

	@XmlElement
	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@XmlElement
	public char getTurno() {
		return turno;
	}

	public void setTurno(char turno) {
		this.turno = turno;
	}

	@XmlElement
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "Turma [idTurma=" + idTurma + ", ano=" + ano + ", turno="
				+ turno + ", curso=" + curso + ", registro=" + registro + "]";
	}

}
