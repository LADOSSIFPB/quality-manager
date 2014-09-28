package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class GenericBean<T, S> implements Serializable {

	public String requestCadastrar(T entidade, String path) {

		String aux = null;

		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target = client
				.target("http://localhost:8080/quality-manager_SERVICE/" + path);

		Response response = target.request().post(
				Entity.entity(entidade, "application/json"));

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		aux = response.readEntity(String.class);

		response.close();

		return aux;

	}

	// ESSE EU NÃO TESTEI, MAS ACREDITO QUE TAMBÉM DÁ ERRO!
	public List<T> requestConsultar(String path) {
		List<T> aux = null;

		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target = client
				.target("http://localhost:8080/quality-manager_SERVICE/" + path);

		Response response = target.request().get();

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		aux = (List<T>) response.getEntity();

		response.close();

		return aux;

	}

	// ESSE É O QUE DÁ ERRO
	public List<S> requestSelectConsultar(String path) {
		List<S> aux = null;

		// TODO: chamar o serviço de consulta e obter o retorno correto

		// teste que se mostrou inútil
		// Client client = ClientBuilder.newBuilder().build();
		// WebTarget target =
		// client.target("http://localhost:8080/quality-manager_SERVICE/" +
		// path);
		// Response response = target.request().get();

		// testei também com Invocation
		// Invocation.Builder invocation = target.request();
		// Response response = invocation.get();

		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client
				.target("http://localhost:8080/quality-manager_SERVICE/" + path);
		Response response = target.request().get();

		System.out.println(response.getEntity()); // aqui sempre me escreve null

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		aux = (List<S>) response.getEntity();

		response.close();

		return aux;

	}

}
