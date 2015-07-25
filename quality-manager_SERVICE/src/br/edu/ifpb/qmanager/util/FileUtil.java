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

	public static String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	public static String USER_HOME = System.getProperties().getProperty("user.home");

	public static String BASE_PATH = USER_HOME + FILE_SEPARATOR + "uploadFile" + FILE_SEPARATOR;

	public static String PROJETO_PATH = BASE_PATH + "projeto";
	public static String EDITAL_PATH = BASE_PATH + "edital";
	public static String PARTICIPACAO_PATH = BASE_PATH + "participacao";
	public static String PESSOA_PATH = BASE_PATH + "pessoa";

	public static String PDF_FILE = "pdf";

	private static Map<TipoArquivo, String> diretorios = new HashMap<TipoArquivo, String>()  {{
	    put(TipoArquivo.ARQUIVO_PROJETO, PROJETO_PATH);
	    put(TipoArquivo.ARQUIVO_EDITAL, EDITAL_PATH);
	    put(TipoArquivo.ARQUIVO_PARTICIPACAO, PARTICIPACAO_PATH);
	    put(TipoArquivo.ARQUIVO_PESSOA, PESSOA_PATH);
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

		String diretorioArquivo = diretorios.get(tipoArquivo);

		File file = new File(diretorioArquivo + FILE_SEPARATOR + nomeArquivo);

		try {
			
			logger.info("Salvando o arquivo em disco: " + file.getName());
			
			if (!file.exists()) {
				file.createNewFile();
			}

			FileOutputStream fop = new FileOutputStream(file);
			fop.write(content);
			fop.flush();
			fop.close();
			logger.info("Arquivo " + file.getName() + " salvo com sucesso.");
			
		} catch (IOException e) {
			
			logger.error("Problema ao salvar o arquivo no sistema");
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
