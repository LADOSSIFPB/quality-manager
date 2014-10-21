package br.edu.ifpb.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import br.edu.ifpb.conection.HttpService;
import br.edu.ifpb.conection.HttpUtil;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;

import com.google.gson.Gson;

public class PreencherSpinner extends
		AsyncTask<Void, Integer, List<InstituicaoFinanciadora>> {

	private Activity activity;

	public PreencherSpinner(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected List<InstituicaoFinanciadora> doInBackground(Void... params) {
		JSONArray jsonObjects = new JSONArray();
		List<InstituicaoFinanciadora> entidades = new ArrayList<InstituicaoFinanciadora>();

		// Enviar a requisição HTTP via GET.
		HttpService httpService = new HttpService();
		HttpResponse response = httpService
				.sendGETRequest(Constantes.CONSULTAR_INSTITUICOES_FINANCIADORAS);

		if (response.getStatusLine().getStatusCode() != 200) {
			Toast toast = Toast
					.makeText(activity, response
							.getStatusLine().getStatusCode(), Toast.LENGTH_LONG);
			toast.show();
		}

		// Conversão do response ( resposta HTTP) para String.

		String json = HttpUtil.entityToString(response);

		try {

			jsonObjects = new JSONArray(json);

			Gson gson = new Gson();

			try {

				for (int i = 0; i < jsonObjects.length(); i++) {
					json = gson.toJson(jsonObjects.getJSONObject(i));
					entidades.add(gson.fromJson(json,
							InstituicaoFinanciadora.class));
				}

			} catch (JSONException jse) {
				Log.e("AsyncTaskKJson", "Error parsing data " + jse.toString());
			}

		} catch (JSONException e) {

			Log.e("AsyncTaskKJson", "Error parsing data " + e.toString());
		}
		return null;
	}
}