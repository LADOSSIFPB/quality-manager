package br.edu.ifpb.qmanager.entidade;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "titulacao")
public class Titulacao {

	private int idTitulacao;
	
	private String nome;

	@XmlElement
	public int getIdTitulacao() {
		return idTitulacao;
	}

	public void setIdTitulacao(int idTitulacao) {
		this.idTitulacao = idTitulacao;
	}

	@XmlElement
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
	
}
