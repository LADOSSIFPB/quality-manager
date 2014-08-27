package br.edu.ifpb.qmanager;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import br.edu.ifpb.qmanager.entidade.Instituicao;


public class QManagerClient {

	// Deixar este m�todo comum para todas as passadas do Servlet pra c�, e
	// depois, mandar pra o WebService
	public static String requestClient(Instituicao instituicao) {

		// Student st = new Student("Catain", "Hook", 10, 12);

		/*
		 * Alternatively you can use this simple String to send instead of using
		 * a Student instance
		 * 
		 * String jsonString =
		 * "{\"id\":12,\"firstName\":\"Catain\",\"lastName\":\"Hook\",\"age\":10}"
		 * ;
		 */

		String aux = null;

		try {
			ResteasyClient client = new ResteasyClientBuilder().build();

			ResteasyWebTarget target = client
					.target("http://localhost:8080/quality-manager_SERVICE/service/send");

			Response response = target.request().post(
					Entity.entity(instituicao, "application/json"));

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			aux = response.readEntity(String.class);
			// System.out.println("Server response : \n");
			// System.out.println(response.readEntity(String.class))

			response.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return aux;

	}

}
