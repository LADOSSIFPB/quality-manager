package br.edu.ifpb.qmanager.entidade;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario {

	UsuarioIF usuario;
	
	@XmlElement
	public UsuarioIF getUsuario() {
		return usuario;
	}
	
	public void setUsuario(UsuarioIF usuario) {
		this.usuario = usuario;
	}

}
