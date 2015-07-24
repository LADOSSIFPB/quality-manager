package br.edu.ifpb.qmanager.entidade;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.edu.ifpb.qmanager.tipo.TipoArquivoEdital;

@XmlRootElement(name = "arquivoEdital")
public class ArquivoEdital {

	private int idArquivoEdital;
	
	private Edital edital;
	
	private Arquivo arquivo;
	
	private TipoArquivoEdital tipoArquivoEdital;

	@XmlElement
	public int getIdArquivoEdital() {
		return idArquivoEdital;
	}

	public void setIdArquivoEdital(int idArquivoEdital) {
		this.idArquivoEdital = idArquivoEdital;
	}

	@XmlElement
	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
	}

	@XmlElement
	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	@XmlElement
	public TipoArquivoEdital getTipoArquivoEdital() {
		return tipoArquivoEdital;
	}

	public void setTipoArquivoEdital(TipoArquivoEdital tipoArquivoEdital) {
		this.tipoArquivoEdital = tipoArquivoEdital;
	}		
}
