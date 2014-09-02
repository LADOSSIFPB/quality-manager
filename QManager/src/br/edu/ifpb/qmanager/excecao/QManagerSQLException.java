package br.edu.ifpb.qmanager.excecao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QManagerSQLException extends SQLException {

	private static final long serialVersionUID = 6315776920468858333L;

	private static final Map<Integer, String> erros = new HashMap<Integer, String>();
	static {
		erros.put(1062, "Chave duplicada: ");
		erros.put(1052, "Consulta com coluna ambígua: ");
		erros.put(1054, "Coluna desconhecida: ");
		erros.put(1146, "Tabela não existe: ");
		erros.put(1406, "Dado muito longo para a coluna: ");
		erros.put(1451,	"Não é possível excluir ou atualizar uma "
				+ "linha pai: uma restrição de chave estrangeira falhou");
	}
	
	private int errorCode;

	public QManagerSQLException(int errorCode, String localizedMessage) {
		
		super(parseMessage(errorCode, localizedMessage));
		
		this.errorCode = errorCode;
	}
	
	public String getMessage(){
		
		return erros.get(this.errorCode);
	}

	private static String parseMessage(int errorCode, String localizedMessage) {

		ArrayList<Integer> vet = new ArrayList<Integer>();
		ArrayList<String> pal = new ArrayList<String>();

		// descobre limites das palavras que nos interessam
		for (int i = 0; i < localizedMessage.length(); i++) {
			if (localizedMessage.charAt(i) == '\'') {
				vet.add(i);
			}
		}

		String temp = "";
		// se achou ao menos um termo destacado
		if (vet.size() > 1) {

			// recupere as palavras interessantes
			for (int i = 0; i < vet.size(); i++) {
				if (i + 1 < vet.size()) {
					pal.add(localizedMessage.substring(vet.get(i),
							vet.get(++i) + 1));
				}
			}

			for (String s : pal) {
				temp += s + " ";
			}

		}

		return erros.get(errorCode) + temp;

	}

}
