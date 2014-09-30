package managedBean;

import java.io.Serializable;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class GenericBean<T> implements Serializable {

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

	// TODO: Não testado devido a falta de xhtml correspondente
	// Felipe Nascimento está pesquisando como fazer paginação.
	public Response requestGetAll(String path) {

		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target = client
				.target("http://localhost:8080/quality-manager_SERVICE/" + path);

		Response response = target.request().get();

		return response;

	}

	public Response requestSelectConsultar(String path) {

		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client
				.target("http://localhost:8080/quality-manager_SERVICE/" + path);

		Response response = target.request().get();

		return response;

	}
}
