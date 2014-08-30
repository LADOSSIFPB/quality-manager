package br.edu.ifpb.qmanager.excecao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QManagerSQLException extends SQLException {

	private static final long serialVersionUID = 6315776920468858333L;

	private static final Map<Integer, String> erros = new HashMap<Integer, String>();
	static {
		erros.put(1062, "Chave duplicada: ");
		erros.put(1052, "Consulta com coluna ambígua: ");
		erros.put(1054, "Coluna desconhecida: ");
		erros.put(1146, "Tabela não existe: ");
		erros.put(1406, "Dado muito longo para a coluna: ");
		erros.put(1451, "Não é possível excluir ou atualizar uma linha pai: uma restrição de chave estrangeira falhou: ");
	}

	public QManagerSQLException(int errorCode, String localizedMessage) {
		super(parseMessage(errorCode, localizedMessage));
		Logger.getLogger(QManagerSQLException.class.getName()).log(
				Level.SEVERE, "Erro " + errorCode);
	}

	private static String parseMessage(int errorCode, String localizedMessage) {
		
		ArrayList<Integer> vet = new ArrayList<Integer>();
		ArrayList<String> pal = new ArrayList<String>();
		
		// TODO: não tratamos o caso em que há apenas a mensagem que definimos a ser exibida
		
		// descobre limites das palavras que nos interessam
		for (int i = 0; i < localizedMessage.length(); i++) {
			if(localizedMessage.charAt(i) == '\'') {
				vet.add(i);
			}
		}
		
		String temp = "";
		
		// recuperam as palavras que nos interessam
		for (int i = 0, j; i < vet.size(); i+=2) {
			j = vet.get(i) + 1;
			pal.add(localizedMessage.substring(vet.get(i), vet.get(j)));
		}
		
		for (String s : pal) {
			temp += s;
		}
		
		temp = temp.substring(0, temp.length()-2);
		
		return erros.get(errorCode) + temp;
		
	}

}
