package br.edu.ifpb.qmanager.chat;

import java.util.Date;

public class Chat {

	private int idChat;
	private String nome;
	private Date registro;

	public Chat() {
		this(0, "sem nome");
	}

	public Chat(int idChat, String nome) {
		this.idChat = idChat;
		this.nome = nome;
	}

	public int getIdChat() {
		return idChat;
	}

	public void setIdChat(int idChat) {
		this.idChat = idChat;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

}
