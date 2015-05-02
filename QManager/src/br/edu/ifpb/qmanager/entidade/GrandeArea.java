package br.edu.ifpb.qmanager.entidade;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "grandeArea")
public class GrandeArea {

	private int idGrandeArea;
	
	private String codigo;
	
	private String nome;

	@XmlElement
	public int getIdGrandeArea() {
		return idGrandeArea;
	}

	public void setIdGrandeArea(int idGrandeArea) {
		this.idGrandeArea = idGrandeArea;
	}

	@XmlElement
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@XmlElement
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public String toString() {
		return " Grande Área [ id:" + idGrandeArea + ", nome:" + nome + "]";
	}	
}
