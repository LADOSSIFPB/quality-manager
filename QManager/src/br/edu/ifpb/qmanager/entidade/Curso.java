package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "curso")
public class Curso {

	private int idCurso;
	private String nomeCurso;
	private Servidor gestor;
	private Date registro;

	private Servidor coordenador;

	public Curso() {
		coordenador = new Servidor();
		gestor = new Servidor();
	}

	public Curso(String nomeCurso, Servidor gestor, Servidor coordenador) {
		setNomeCurso(nomeCurso);
		setGestor(gestor);
		setCoordenador(coordenador);
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
	public Servidor getGestor() {
		return gestor;
	}

	public void setGestor(Servidor gestor) {
		this.gestor = gestor;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@XmlElement
	public Servidor getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Servidor coordenador) {
		this.coordenador = coordenador;
	}

	@Override
	public String toString() {
		return "Curso [idCurso=" + idCurso + ", nomeCurso=" + nomeCurso
				+ ", gestor=" + gestor + ", registro=" + registro
				+ ", coordenador=" + coordenador + "]";
	}

}
