package br.edu.ifpb.qmanager.chat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.edu.ifpb.qmanager.entidade.Pessoa;

@XmlRootElement(name = "conversa")
public class Chat {

	private int idChat;
	private String nome;
	private List<Pessoa> pessoas;
	private Date registro;

	public Chat() {
		this(0, "sem nome");
	}

	public Chat(int idChat, String nome) {
		this.idChat = idChat;
		this.nome = nome;
		this.setPessoas(new ArrayList<Pessoa>());
	}

	@XmlElement
	public int getIdChat() {
		return idChat;
	}

	public void setIdChat(int idChat) {
		this.idChat = idChat;
	}

	@XmlElement
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@XmlElement
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

}
