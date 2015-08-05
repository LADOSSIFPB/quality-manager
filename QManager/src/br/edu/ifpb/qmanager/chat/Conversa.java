package br.edu.ifpb.qmanager.chat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.edu.ifpb.qmanager.entidade.Pessoa;

@XmlRootElement(name = "conversa")
public class Conversa {

	private int idConversa;
	private String nome;
	private List<Pessoa> pessoas;
	private Date registro;

	public Conversa() {
		this(0, "sem nome");
	}

	public Conversa(int idConversa, String nome) {
		this.idConversa = idConversa;
		this.nome = nome;
		this.setPessoas(new ArrayList<Pessoa>());
	}

	@XmlElement
	public int getIdConversa() {
		return idConversa;
	}

	public void setIdConversa(int idConversa) {
		this.idConversa = idConversa;
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
