package br.edu.ifpb.qmanager.servlet;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
		logger.info("Base:" + FileUtil.BASE_PATH);
		
		List<Path> paths = new ArrayList<Path>();
		
		Path basePath = Paths.get(FileUtil.BASE_PATH);
		paths.add(basePath);
		
		logger.info("Base path: " + basePath);
		
		Path projetoPath = Paths.get(FileUtil.PROJETO_PATH);
		paths.add(projetoPath);
		
		Path editalPath = Paths.get(FileUtil.EDITAL_PATH);
		paths.add(editalPath);
		
		Path integrantePath = Paths.get(FileUtil.PARTICIPACAO_PATH);
		paths.add(integrantePath);
		
		Path pessoaPath = Paths.get(FileUtil.PESSOA_PATH);
		paths.add(pessoaPath);
		
		if (Files.notExists(basePath)) {
			
			logger.warn("Os diretórios de arquivos não existem!");
			
			for (Path path: paths) {
				
				File file = new File(path.toUri());
				file.mkdir();
				
				logger.info("Diretório " + path.getFileName() + " criado.");
			}
			
		} else {
			
			logger.info("Estrutura de diretório já estabelecida.");
		}
	}

}
