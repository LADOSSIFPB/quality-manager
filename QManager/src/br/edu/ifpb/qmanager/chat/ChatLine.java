package br.edu.ifpb.qmanager.chat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.edu.ifpb.qmanager.entidade.Pessoa;

public class ChatLine {

	private int idChatLine;
	private Chat chat;
	private Pessoa remetente;
	private String mensagem;
	private Date registro;
	private Map<Pessoa, Boolean> pessoas;

	public ChatLine() {
		this(0, null, "");
	}

	public ChatLine(int idChatLine, Pessoa remetente, String mensagem) {
		this.idChatLine = idChatLine;
		this.remetente = remetente;
		this.mensagem = mensagem;
		this.setPessoas(new HashMap<Pessoa, Boolean>());
	}

	public int getIdChatLine() {
		return idChatLine;
	}

	public void setIdChatLine(int idChatLine) {
		this.idChatLine = idChatLine;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public Pessoa getRemetente() {
		return remetente;
	}

	public void setRemetente(Pessoa remetente) {
		this.remetente = remetente;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Map<Pessoa, Boolean> getPessoas() {
		return pessoas;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	public void setPessoas(Map<Pessoa, Boolean> pessoas) {
		this.pessoas = pessoas;
	}

	public void setPessoaVizualizou(Pessoa pessoa, boolean vizualizou) {
		pessoas.put(pessoa, vizualizou);
	}
}
