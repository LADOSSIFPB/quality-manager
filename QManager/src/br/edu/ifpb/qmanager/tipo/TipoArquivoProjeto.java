package br.edu.ifpb.qmanager.tipo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tipoArquivoProjeto")
@XmlEnum
public enum TipoArquivoProjeto {
	
	// Tipo Arquivo de Projeto
	@XmlEnumValue("1") ARQUIVO_PROJETO_IDENTIFICADO (1),
	@XmlEnumValue("2") ARQUIVO_PROJETO_NAO_IDENTIFICADO (2),
	@XmlEnumValue("3") ARQUIVO_PROJETO_COMISSAO_ETICA (3);

	private final int id;

	TipoArquivoProjeto(int id) {
		this.id = id;
	}

	@XmlElement
	public int getId() {
		return id;
	}
}