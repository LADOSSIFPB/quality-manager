package br.edu.ifpb.qmanager.servlet;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.qmanager.util.FileUtil;

@WebListener
public class DirectoryListener implements ServletContextListener {
	
	private static Logger logger = LogManager.getLogger(DirectoryListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		logger.info("Verificando diretórios.");
		
		Path path = Paths.get(FileUtil.SERVER_PATH);
		
		if (Files.notExists(path)) {
			
			logger.warn("O diretório de arquivos não existe");
			File file = new File(path.toUri());
			file.mkdir();
			
			logger.info("Estrutura de diretório criada.");
		} else {
			
			logger.info("Estrutura de diretório já estabelecida.");
		}
	}

}
