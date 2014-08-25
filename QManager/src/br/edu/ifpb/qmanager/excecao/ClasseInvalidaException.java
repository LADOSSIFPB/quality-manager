package br.edu.ifpb.qmanager.excecao;

public class ClasseInvalidaException extends RuntimeException {
	
	private static final long serialVersionUID = -1290509255312012050L;

	public ClasseInvalidaException() {
		super("Classe inválida! Verifique tipo de Classe passada como parâmetro!");
	}
	
	public ClasseInvalidaException(String mensagem) {
		super(mensagem);
	}
	
}