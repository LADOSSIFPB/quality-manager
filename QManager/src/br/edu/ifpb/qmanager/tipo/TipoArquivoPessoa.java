package br.edu.ifpb.qmanager.tipo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tipoArquivoPessoa")
@XmlEnum
public enum TipoArquivoPessoa {

	// Tipo Arquivo de Pessoa.
	@XmlEnumValue("1") ARQUIVO_PESSOA_FOTO (1);

	private final int id;

	TipoArquivoPessoa(int id) {
		this.id = id;
	}

	@XmlElement
	public int getId() {
		return id;
	}
}
