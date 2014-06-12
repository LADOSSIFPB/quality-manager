package br.edu.ifpb.exceções;

public class ClasseInválidaException extends Exception {
	
	private static final long serialVersionUID = -1290509255312012050L;

	public ClasseInválidaException() {
		super("Classe inválida! Verifique tipo de Classe passada como parâmetro!");
	}
	
	public ClasseInválidaException(String mensagem) {
		super(mensagem);
	}
	
}
