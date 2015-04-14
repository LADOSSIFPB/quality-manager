package br.edu.ifpb.conection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.util.Constantes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class VerificarCoordenadoresAsyncTask extends
		AsyncTask<Void, Integer, List<Servidor>> {

	private Servidor coordenador;

	public VerificarCoordenadoresAsyncTask(Servidor coordenador) {
		this.coordenador = coordenador;
	}

	@Override
	protected List<Servidor> doInBackground(Void... params) {
		JSONObject jsonObject = new JSONObject();
		List<Servidor> entidades = new ArrayList<Servidor>();

		Gson gson = new Gson();
		String json = gson.toJson(this.coordenador);

		try {

			jsonObject = new JSONObject(json);

			// Enviar a requisição HTTP via GET.
			HttpService httpService = new HttpService();
			HttpResponse response = httpService.sendJsonPostRequest(
					Constantes.CONSULTAR_COORDENADORES, jsonObject);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				json = HttpUtil.entityToString(response);

				gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

				entidades = gson.fromJson(json,
						new TypeToken<List<Servidor>>() {
						}.getType());
			} else {
				entidades = null;
			}
		} catch (JSONException e) {
			entidades = null;
		} catch (IOException e) {
			entidades = null;
		}

		return entidades;
	}

}
