package br.edu.ifpb.qmanager.tipo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tipoArquivo")
@XmlEnum
public enum TipoArquivo implements Serializable {
	
	// Tipo Arquivo de Projeto
	@XmlEnumValue("1") ARQUIVO_PROJETO (1),
	@XmlEnumValue("2") ARQUIVO_EDITAL (2),
	@XmlEnumValue("3") ARQUIVO_PARTICIPACAO (3),
	@XmlEnumValue("4") ARQUIVO_PESSOA (4);	

	private final int id;

	TipoArquivo(int id) {
		this.id = id;
	}

	@XmlElement
	public int getId() {
		return id;
	}
}