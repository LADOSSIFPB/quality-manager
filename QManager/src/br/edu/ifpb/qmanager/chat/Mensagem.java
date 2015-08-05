package br.edu.ifpb.qmanager.chat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.edu.ifpb.qmanager.entidade.Pessoa;

@XmlRootElement(name = "mensagem")
public class Mensagem {

	private int idMensagem;
	private Conversa conversa;
	private Pessoa remetente;
	private String mensagem;
	private Date registro;
	private List<SituacaoMensagem> situacoes;

	public Mensagem() {
		this(0, null, "");
	}

	public Mensagem(int idMensagem, Pessoa remetente, String mensagem) {
		this.idMensagem = idMensagem;
		this.remetente = remetente;
		this.mensagem = mensagem;
		this.setSituacoes(new ArrayList<SituacaoMensagem>());
	}

	@XmlElement
	public int getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(int idMensagem) {
		this.idMensagem = idMensagem;
	}

	@XmlElement
	public Conversa getConversa() {
		return conversa;
	}

	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
	}

	@XmlElement
	public Pessoa getRemetente() {
		return remetente;
	}

	public void setRemetente(Pessoa remetente) {
		this.remetente = remetente;
	}

	@XmlElement
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	public List<SituacaoMensagem> getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(List<SituacaoMensagem> situacoes) {
		this.situacoes = situacoes;
	}
}
