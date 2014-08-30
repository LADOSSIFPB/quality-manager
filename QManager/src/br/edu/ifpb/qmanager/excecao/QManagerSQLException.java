package br.edu.ifpb.qmanager.excecao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QManagerSQLException extends SQLException {

	private static final long serialVersionUID = 6315776920468858333L;

	private static final Map<Integer, String> erros = new HashMap<Integer, String>();
	static {
		erros.put(1062, "Chave duplicada");
	}

	int errorCode;

	public QManagerSQLException(int errorCode) {
		super(erros.get(errorCode));
		Logger.getLogger(QManagerSQLException.class.getName()).log(
				Level.SEVERE, null, "Erro " + errorCode + ": " + erros.get(errorCode));
		setErrorCode(errorCode);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
