package br.edu.ifpb.qmanager.entidade;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = -6026714503326338815L;

	private int idRole;
	
	private String nome;

	@XmlElement
	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	@XmlElement
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Role [nome=" + nome + "]";
	}
}
