package br.edu.ifpb.qmanager.servlet;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edu.ifpb.qmanager.dao.ReleaseDAO;

@WebServlet(value="/ReleaseConn.do", loadOnStartup=1)
public class ReleaseConn extends HttpServlet {

	private static final long serialVersionUID = 6468000434064659763L;
	
	private static Logger logger = LogManager.getLogger(ReleaseConn.class);

	public void init() throws ServletException {

		logger.info("Iniciando o ReleaseConn");
		
		ScheduledExecutorService executor = Executors
				.newSingleThreadScheduledExecutor();

		Runnable periodicTask = new Runnable() {
			public void run() {				
				Calendar cal = Calendar.getInstance();
		    	cal.getTime();
		    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		    	
		    	try {
					int count = ReleaseDAO.getInstance().count();
					logger.info("O valor do contador é: " + count);
					
				} catch (SQLException e) {
					logger.error("Problema na conexão");
					logger.error(e);
				}
		    	
		    	logger.info("Executando a cada 5 minutos: "
		    			+ sdf.format(cal.getTime()));
			}
		};

		executor.scheduleAtFixedRate(periodicTask, 0, 5, TimeUnit.MINUTES);
	}
}
