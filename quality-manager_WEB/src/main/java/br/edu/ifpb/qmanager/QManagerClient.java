package br.edu.ifpb.qmanager;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.primefaces.json.JSONObject;

import br.edu.ifpb.qmanager.entidade.Instituicao;

public class QManagerClient {

	/**
	 * Requisitar inserção de Entidade ao WebService
	 * 
	 * @author Eri Jonhson
	 * @param entidade
	 * @return JSONObject
	 * */
	public static JSONObject requestClient(Instituicao instituicao) {

		JSONObject json = null;

		ResteasyClient client = new ResteasyClientBuilder().build();

		ResteasyWebTarget target = client
				.target("http://localhost:8080/quality-manager_SERVICE/service/cadastrarInstituicao");

		// requisita inserção de entidade ao service e espera resposta
		Response response = target.request().post(
				Entity.entity(instituicao, "application/json"));

		if (response.getStatusInfo() != Response.Status.OK) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		json = (JSONObject) response.getEntity();

		response.close();

		return json;

	}

}
