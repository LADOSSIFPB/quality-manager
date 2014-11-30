package br.edu.ifpb.qmanager.entidade;

import java.io.Serializable;

public class Erro implements Serializable {

	private static final long serialVersionUID = 1L;

	private int codigo;

	private String mensagem;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
