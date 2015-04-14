package br.edu.ifpb.qmanager.entidade;

public class Curso {

	private int idCurso;
	private String nomeCurso;
	private Servidor gestor;
	private String registro;

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

	public Servidor getGestor() {
		return gestor;
	}

	public void setGestor(Servidor gestor) {
		this.gestor = gestor;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

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
