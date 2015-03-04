package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "campus")
public class Campus {

	private int idCampusInstitucional;
	
	private String nome;
	
	private String cidade;
	
	private Date registro;

	@XmlElement
	public int getIdCampusInstitucional() {
		return idCampusInstitucional;
	}

	public void setIdCampusInstitucional(int idCampusInstitucional) {
		this.idCampusInstitucional = idCampusInstitucional;
	}

	@XmlElement
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@XmlElement
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}
}
