package br.edu.ifpb.qmanager.tipo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tipoArquivoPessoa")
@XmlEnum
public enum TipoArquivoIntegrante {

	// Tipo Arquivo do Integrante de Projeto.
	@XmlEnumValue("1") ARQUIVO_INTEGRANTE_ (1);

	private final int id;

	TipoArquivoIntegrante(int id) {
		this.id = id;
	}

	@XmlElement
	public int getId() {
		return id;
	}
}
