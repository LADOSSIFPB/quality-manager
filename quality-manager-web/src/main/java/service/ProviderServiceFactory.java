package service;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import managedBean.GenericBean;
import managedBean.PessoaBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

public class ProviderServiceFactory {
	
	 private static Logger logger = LogManager.getLogger(ProviderServiceFactory.class);
	
	private static final String END_POINT = ProviderServiceFactory
			.getEndPoints().get(0);
	
	private static final String URL_SERVICE = END_POINT 
			+ "/quality-manager_SERVICE/";
	
	@Context 
	public static ServletContext servletContext;

	static {
		/*
		 * Precisa ser chamado uma única vez para registrar providers RESTEasy,
		 * scanear classes, etc
		 */
		RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
	}

	public static <T> T createServiceClient(Class<T> serviceType) {
		return createServiceClient(URL_SERVICE, serviceType);
	}

	/**
	 * A factory of clients to our REST services.
	 * 
	 * @author Rhavy Maia Guedes
	 * @author Eri Jonhson
	 */
	public static <T> T createServiceClient(String serviceUrl,
			Class<T> serviceType) {
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		
		PessoaBean pessoaBean = (PessoaBean) GenericBean.getSessionValue(
				"pessoaBean");
		
		// Usuário logado e autorizado a acessar o sistema.
		if (pessoaBean != null) {
			client.register(new AuthHeadersRequestFilter(
					String.valueOf(pessoaBean.getPessoaId()), 
					pessoaBean.getAuthorizationKey()));
		}		
		
		ResteasyWebTarget target = client.target(serviceUrl);

		return target.proxy(serviceType);
	}
	
	
	private static List<String> getEndPoints() {

		ArrayList<String> endPoints = new ArrayList<String>();

		try {

			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

			Set<ObjectName> objs = mbs.queryNames(new ObjectName(
					"*:type=Connector,*"), Query.match(Query.attr("protocol"),
					Query.value("HTTP/1.1")));

			String hostname = InetAddress.getLocalHost().getHostName();

			InetAddress[] addresses = InetAddress.getAllByName(hostname);

			
			for (Iterator<ObjectName> i = objs.iterator(); i.hasNext();) {
				ObjectName obj = i.next();
				
				String scheme = mbs.getAttribute(obj, "scheme").toString();
				
				String port = obj.getKeyProperty("port");
				
				for (InetAddress addr : addresses) {
					String host = addr.getHostAddress();
					String ep = scheme + "://" + host + ":" + port;
					endPoints.add(ep);
					
					logger.info("Conexão de serviço: " + ep);
				}
			}			
		} catch (Exception e) {
			
			logger.error("Impossível recuperar host e porta: " + e.getMessage());
		}

		return endPoints;
	}
}
