package br.edu.ifpb.qmanager.tipo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tipoArquivoPessoa")
@XmlEnum
public enum TipoArquivoParticipacao implements TipoArquivoGeneric {

	// Tipo Arquivo do Integrante de Projeto.
	@XmlEnumValue("1") ARQUIVO_PARTICIPACAO_TERMO_VOLUNTARIO (1),
	@XmlEnumValue("2") ARQUIVO_PARTICIPACAO_VINCULO_EMPREGATICIO (2),
	@XmlEnumValue("3") ARQUIVO_PARTICIPACAO_PLANO_INDIVIDUAL_TRABALHO (3);

	private final int id;

	TipoArquivoParticipacao(int id) {
		this.id = id;
	}

	@Override
	@XmlElement
	public int getId() {
		return id;
	}
}
