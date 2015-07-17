package br.edu.ifpb.qmanager.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.qmanager.entidade.Arquivo;
import br.edu.ifpb.qmanager.excecao.IOExceptionQManager;
import br.edu.ifpb.qmanager.tipo.TipoArquivo;

/**
 * Utils para manipulação de arquivos.
 * 
 * @author Rhavy
 */
public class FileUtil {

	private static Logger logger = LogManager.getLogger(FileUtil.class);
	
	public static String SERVER_PATH = "C:\\Java\\web\\uploadFile\\";

	public static String PDF_FILE = "pdf";
	
	private static Map<TipoArquivo, String> diretorios = new HashMap<TipoArquivo, String>()  {{
	    put(TipoArquivo.ARQUIVO_PROJETO, "projeto");
	    put(TipoArquivo.ARQUIVO_EDITAL, "edital");
	    put(TipoArquivo.ARQUIVO_INTEGRANTE, "integrante");
	    put(TipoArquivo.ARQUIVO_PESSOA, "pessoa");
	}};

	/**
	 * Salvar os arquivo no diretório do servidor.
	 * 
	 * @param content
	 * @param filename
	 * @throws IOException
	 */
	public static void writeFile(Arquivo arquivo)
			throws IOExceptionQManager {

		byte[] content = arquivo.getFile();
		
		String nomeArquivo = arquivo.getNomeSistemaArquivo();
		
		TipoArquivo tipoArquivo = arquivo.getTipoArquivo();
		
		String diretorioArquivo = diretorios.get(tipoArquivo) + "\\";
		
		File file = new File(SERVER_PATH + diretorioArquivo + nomeArquivo);

		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			FileOutputStream fop = new FileOutputStream(file);

			fop.write(content);
			fop.flush();
			fop.close();
			
		} catch (IOException e) {
			
			throw new IOExceptionQManager(e.getMessage());
		}
	}
	
	public static String getNomeSistemaArquivo(String idProjeto, String extension) {
		Date agora = new Date();
		String nomeSistemaArquivo = idProjeto + "-"
				+ Long.toString(agora.getTime()) + "." + FileUtil.PDF_FILE;
		return nomeSistemaArquivo;
	}
}
