package br.edu.ifpb.conection;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.util.Constantes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class PreencherSpinnerEditalAsyncTask extends
		AsyncTask<Void, Integer, List<Edital>> {

	private Activity activity;

	public PreencherSpinnerEditalAsyncTask(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected List<Edital> doInBackground(Void... params) {
		List<Edital> entidades = new ArrayList<Edital>();

		// Enviar a requisição HTTP via GET.
		HttpService httpService = new HttpService();
		HttpResponse response = httpService
				.sendGETRequest(Constantes.CONSULTAR_EDITAIS);

		if (response.getStatusLine().getStatusCode() != 200) {
			Toast toast = Toast.makeText(activity, response.getStatusLine()
					.getStatusCode(), Toast.LENGTH_LONG);
			toast.show();
		}

		// Conversão do response ( resposta HTTP) para String.

		String json = HttpUtil.entityToString(response);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		entidades = gson.fromJson(json, new TypeToken<List<Edital>>() {
		}.getType());

		return entidades;
	}
}
