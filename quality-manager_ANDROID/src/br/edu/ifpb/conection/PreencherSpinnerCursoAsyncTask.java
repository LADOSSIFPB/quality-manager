package br.edu.ifpb.conection;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import android.os.AsyncTask;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.util.Constantes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class PreencherSpinnerCursoAsyncTask extends
		AsyncTask<Void, Integer, List<Curso>> {

	public PreencherSpinnerCursoAsyncTask() {

	}

	@Override
	protected List<Curso> doInBackground(Void... params) {
		List<Curso> entidades = new ArrayList<Curso>();

		// Enviar a requisição HTTP via GET.
		HttpService httpService = new HttpService();
		HttpResponse response = httpService
				.sendGETRequest(Constantes.CONSULTAR_CURSOS);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String json = HttpUtil.entityToString(response);

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

			entidades = gson.fromJson(json, new TypeToken<List<Curso>>() {
			}.getType());
		} else {
			entidades = null;
		}

		return entidades;
	}
}