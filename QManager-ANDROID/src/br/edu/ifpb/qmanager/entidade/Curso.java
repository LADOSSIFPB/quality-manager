package br.edu.ifpb.qmanager.entidade;

import java.io.Serializable;

public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idCurso;
	private String nomeCurso;
	private String registro;

	public Curso() {
	}

	public Curso(String nomeCurso) {
		setNomeCurso(nomeCurso);
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "Curso [idCurso=" + idCurso + ", nomeCurso=" + nomeCurso
				+ ", registro=" + registro + "]";
	}

}
