package br.edu.ifpb.qmanager.tipo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tipoArquivoEdital")
@XmlEnum
public enum TipoArquivoEdital implements TipoArquivoGeneric {
	
	// Tipo Arquivo de Edital
	@XmlEnumValue("1") ARQUIVO_EDITAL_INICIAL (1),
	@XmlEnumValue("2") ARQUIVO_EDITAL_RETIFICACAO (2);

	private final int id;

	TipoArquivoEdital (int id) {
		this.id = id;
	}

	@Override
	@XmlElement
	public int getId() {
		return id;
	}
}