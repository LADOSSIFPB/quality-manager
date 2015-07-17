package br.edu.ifpb.qmanager.entidade;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.edu.ifpb.qmanager.tipo.TipoArquivoProjeto;

@XmlRootElement(name = "arquivoProjeto")
public class ArquivoProjeto {

	private int idArquivoProjeto;
	
	private Projeto projeto;
	
	private Arquivo arquivo;
	
	private TipoArquivoProjeto tipoArquivoProjeto;

	@XmlElement
	public int getIdArquivoProjeto() {
		return idArquivoProjeto;
	}

	public void setIdArquivoProjeto(int idArquivoProjeto) {
		this.idArquivoProjeto = idArquivoProjeto;
	}

	@XmlElement
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@XmlElement
	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	@XmlElement
	public TipoArquivoProjeto getTipoArquivoProjeto() {
		return tipoArquivoProjeto;
	}

	public void setTipoArquivoProjeto(TipoArquivoProjeto tipoArquivoProjeto) {
		this.tipoArquivoProjeto = tipoArquivoProjeto;
	}	
}
