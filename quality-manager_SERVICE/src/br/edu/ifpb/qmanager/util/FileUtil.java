package br.edu.ifpb.qmanager.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.edu.ifpb.qmanager.excecao.IOExceptionQManager;

/**
 * Utils para manipulação de arquivos.
 * 
 * @author Rhavy
 */
public class FileUtil {

	private static String SERVER_PATH = "C:\\Java\\web\\uploadFile\\";

	public static String PDF_FILE = "pdf";

	/**
	 * Salvar os arquivo no diretório do servidor.
	 * 
	 * @param content
	 * @param filename
	 * @throws IOException
	 */
	public static void writeFile(byte[] content, String filename)
			throws IOExceptionQManager {

		File file = new File(SERVER_PATH + filename);

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
}
