package br.edu.ifpb.qmanager.tipo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tipoRole")
@XmlEnum
public enum TipoRole {
	
	// Tipo Arquivo de Projeto
	@XmlEnumValue("1") ROLE_GESTOR (1),
	@XmlEnumValue("2") ROLE_SERVIDOR (2),
	@XmlEnumValue("3") ROLE_ALUNO (3),
	@XmlEnumValue("4") ROLE_VISITANTE (4);

	private final int id;

	TipoRole(int id) {
		this.id = id;
	}

	@XmlElement
	public int getId() {
		return id;
	}
}