package br.edu.ifpb.conection;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import android.os.AsyncTask;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.util.Constantes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class PreencherSpinnerEditalAsyncTask extends
		AsyncTask<Void, Integer, List<Edital>> {

	public PreencherSpinnerEditalAsyncTask() {

	}

	@Override
	protected List<Edital> doInBackground(Void... params) {
		List<Edital> entidades = new ArrayList<Edital>();

		// Enviar a requisição HTTP via GET.
		HttpService httpService = new HttpService();
		HttpResponse response = httpService
				.sendGETRequest(Constantes.CONSULTAR_EDITAIS);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String json = HttpUtil.entityToString(response);

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

			entidades = gson.fromJson(json, new TypeToken<List<Edital>>() {
			}.getType());
		} else {
			entidades = null;
		}

		return entidades;
	}
}
