package br.edu.ifpb.qmanager.tipo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tipoRole")
@XmlEnum
public enum TipoRole {
	
	// Tipo Arquivo de Projeto
	@XmlEnumValue("1") ROLE_GESTOR (1, "gestor"),
	@XmlEnumValue("2") ROLE_SERVIDOR (2, "servidor"),
	@XmlEnumValue("3") ROLE_ALUNO (3, "aluno"),
	@XmlEnumValue("4") ROLE_VISITANTE (4, "visitante");

	private final int id;
	
	private final String nome;

	TipoRole(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	@XmlElement
	public int getId() {
		return id;
	}

	@XmlElement
	public String getNome() {
		return nome;
	}
}