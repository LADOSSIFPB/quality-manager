package br.edu.ifpb.conection;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import android.os.AsyncTask;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.util.Constantes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class PreencherSpinnerOrientadorAsyncTask extends
		AsyncTask<Void, Integer, List<Servidor>> {

	public PreencherSpinnerOrientadorAsyncTask() {

	}

	@Override
	protected List<Servidor> doInBackground(Void... params) {
		List<Servidor> entidades = new ArrayList<Servidor>();

		// Enviar a requisição HTTP via GET.
		HttpService httpService = new HttpService();
		HttpResponse response = httpService
				.sendGETRequest(Constantes.CONSULTAR_ORIENTADORES);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String json = HttpUtil.entityToString(response);

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

			entidades = gson.fromJson(json, new TypeToken<List<Servidor>>() {
			}.getType());
		} else {
			entidades = null;
		}

		return entidades;
	}
}