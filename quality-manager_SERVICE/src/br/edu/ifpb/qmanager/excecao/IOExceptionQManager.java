package br.edu.ifpb.qmanager.excecao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.qmanager.entidade.CodeErroQManager;
import br.edu.ifpb.qmanager.entidade.Erro;

public class IOExceptionQManager extends IOException {

	private static final long serialVersionUID = -4575616448229359228L;

	private Logger logger = LogManager.getLogger(IOExceptionQManager.class);
	
	private static final Map<Integer, String> erros = new HashMap<Integer, String>();
	static {
		erros.put(CodeErroQManager.PROBLEMA_MANIPULAR_ARQUIVO, 
				"Problema ao manipular o arquivo.");
	}
	
	public IOExceptionQManager(String message) {
		super(message);
		logger.error(message);
	}
	
	public Erro getErro() {
		return new Erro(CodeErroQManager.PROBLEMA_MANIPULAR_ARQUIVO, erros.get(
				CodeErroQManager.PROBLEMA_MANIPULAR_ARQUIVO));		
	}
}
