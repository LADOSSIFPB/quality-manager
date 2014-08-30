package br.edu.ifpb.qmanager.excecao;

public class SelectVazioException extends Exception {

	private static final long serialVersionUID = 1L;

	public SelectVazioException(String message) {
		super("Informação " + message + " não existente!");
	}

}
