package br.edu.ifpb.qmanager.entidade;

public class Campus {

	private int idCampusInstitucional;

	private String nome;

	private String cidade;

	private String registro;

	public int getIdCampusInstitucional() {
		return idCampusInstitucional;
	}

	public void setIdCampusInstitucional(int idCampusInstitucional) {
		this.idCampusInstitucional = idCampusInstitucional;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}
}
