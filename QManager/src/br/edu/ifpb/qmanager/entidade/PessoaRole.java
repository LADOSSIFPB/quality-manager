package br.edu.ifpb.qmanager.entidade;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pessoaRole")
public class PessoaRole implements Serializable {

	private static final long serialVersionUID = 1111497786778355808L;

	private Pessoa pessoa;
	
	private List<Role> roles;

	@XmlElement
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@XmlElement
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}	
}
