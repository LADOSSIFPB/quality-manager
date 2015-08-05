package br.edu.ifpb.qmanager.chat;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.edu.ifpb.qmanager.entidade.Pessoa;

@XmlRootElement(name = "situacao_mensagem")
public class SituacaoMensagem {

	private Pessoa pessoa;
	private boolean visualizouMensagem;
	private Date data;

	public SituacaoMensagem(Pessoa pessoa, boolean visualizouMensagem, Date data) {
		this.pessoa = pessoa;
		this.visualizouMensagem = visualizouMensagem;
		this.data = data;
	}

	@XmlElement
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@XmlElement
	public boolean isVisualizouMensagem() {
		return visualizouMensagem;
	}

	public void setVisualizouMensagem(boolean visualizouMensagem) {
		this.visualizouMensagem = visualizouMensagem;
	}

	@XmlElement
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}