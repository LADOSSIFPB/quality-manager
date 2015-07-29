package br.edu.ifpb.qmanager.entidade;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.edu.ifpb.qmanager.tipo.TipoArquivoParticipacao;

@XmlRootElement(name = "arquivoParticipacao")
public class ArquivoParticipacao {

	private int idArquivoParticipacao;
	
	private Participacao participacao;
	
	private Arquivo arquivo;
	
	private TipoArquivoParticipacao tipoArquivoParticipacao;

	@XmlElement
	public int getIdArquivoParticipacao() {
		return idArquivoParticipacao;
	}

	public void setIdArquivoParticipacao(int idArquivoParticipacao) {
		this.idArquivoParticipacao = idArquivoParticipacao;
	}

	@XmlElement
	public Participacao getParticipacao() {
		return participacao;
	}

	public void setParticipacao(Participacao participacao) {
		this.participacao = participacao;
	}

	@XmlElement
	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	@XmlElement
	public TipoArquivoParticipacao getTipoArquivoParticipacao() {
		return tipoArquivoParticipacao;
	}

	public void setTipoArquivoParticipacao(
			TipoArquivoParticipacao tipoArquivoParticipacao) {
		this.tipoArquivoParticipacao = tipoArquivoParticipacao;
	}
}
