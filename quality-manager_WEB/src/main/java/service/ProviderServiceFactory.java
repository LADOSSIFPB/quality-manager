package service;

import org.apache.commons.httpclient.HttpClient;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

public class ProviderServiceFactory {

	private static final String URL_SERVICE = 
		"http://localhost:8080/quality-manager_SERVICE/";
	
	static {
		/*
		 * Precisa ser chamado uma �nica vez para registrar providers RESTEasy,
		 * scanear classes, etc
		 */
		RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
	}

	public static <T> T createServiceClient(Class<T> serviceType){
		return createServiceClient(URL_SERVICE, serviceType);
	}
	
	/**
	 * A factory of clients to our REST services.
	 * 
	 * @author Rhavy Maia Guedes
	 */
	public static <T> T createServiceClient(String serviceUrl,
			Class<T> serviceType) {

		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("UTF-8");

		return ProxyFactory.create(serviceType, serviceUrl);
	}
}
