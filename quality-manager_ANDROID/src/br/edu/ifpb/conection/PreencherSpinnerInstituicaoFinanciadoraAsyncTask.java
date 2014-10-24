package br.edu.ifpb.conection;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.util.Constantes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class PreencherSpinnerInstituicaoFinanciadoraAsyncTask extends
		AsyncTask<Void, Integer, List<InstituicaoFinanciadora>> {

	private Activity activity;

	public PreencherSpinnerInstituicaoFinanciadoraAsyncTask(Activity activity) {
		this.activity = activity;
	}

	@Override
	protected List<InstituicaoFinanciadora> doInBackground(Void... params) {
		List<InstituicaoFinanciadora> entidades = new ArrayList<InstituicaoFinanciadora>();

		// Enviar a requisição HTTP via GET.
		HttpService httpService = new HttpService();
		HttpResponse response = httpService
				.sendGETRequest(Constantes.CONSULTAR_INSTITUICOES_FINANCIADORAS);

		if (response.getStatusLine().getStatusCode() != 200) {
			Toast toast = Toast.makeText(activity, response.getStatusLine()
					.getStatusCode(), Toast.LENGTH_LONG);
			toast.show();
		}

		// Conversão do response ( resposta HTTP) para String.

		String json = HttpUtil.entityToString(response);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		entidades = gson.fromJson(json,
				new TypeToken<List<InstituicaoFinanciadora>>() {
				}.getType());

		return entidades;
	}
}