package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="curso")
public class Curso {

	private int idCurso;
	private String nomeCurso;
	private Date registro;

	public Curso() {
	}

	public Curso(String nomeCurso) {
		setNomeCurso(nomeCurso);
	}

	@XmlElement
	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	@XmlElement
	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
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
		return "Curso [idCurso=" + idCurso + ", nomeCurso=" + nomeCurso
				+ ", registro=" + registro + "]";
	}

}
