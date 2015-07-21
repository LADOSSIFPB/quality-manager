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

		Path serverPath = Paths.get(FileUtil.SERVER_PATH);
		Path projetoPath = Paths.get(FileUtil.PROJETO_PATH);
		Path editalPath = Paths.get(FileUtil.EDITAL_PATH);
		Path integrantePath = Paths.get(FileUtil.INTEGRANTE_PATH);
		Path pessoaPath = Paths.get(FileUtil.PESSOA_PATH);

		if (Files.notExists(serverPath)) {

			logger.warn("O diretório de arquivos não existe");

			File file = new File(serverPath.toUri());
			file.mkdir();
			logger.info("Diretório /uploadFile criado.");

			file = new File(projetoPath.toUri());
			file.mkdir();
			logger.info("Diretório /uploadFile/projeto criado.");

			file = new File(editalPath.toUri());
			file.mkdir();
			logger.info("Diretório /uploadFile/edital criado.");

			file = new File(integrantePath.toUri());
			file.mkdir();
			logger.info("Diretório /uploadFile/integrante criado.");

			file = new File(pessoaPath.toUri());
			file.mkdir();
			logger.info("Diretório /uploadFile/pessoa criado.");

			logger.info("Estrutura de diretório criada.");
		} else {
			
			logger.info("Estrutura de diretório já estabelecida.");
		}
	}

}
