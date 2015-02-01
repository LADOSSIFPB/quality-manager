package br.edu.ifpb.conection;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

import android.os.AsyncTask;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.util.Constantes;

import com.google.gson.Gson;

public class VerificarTipoServidorAsyncTask extends
		AsyncTask<Void, Integer, Servidor> {

	private int id_servidor;

	public VerificarTipoServidorAsyncTask(int id_servidor) {
		this.id_servidor = id_servidor;
	}

	@Override
	protected Servidor doInBackground(Void... arg0) {
		JSONObject jsonObject = new JSONObject();
		Servidor servidor = new Servidor();

		Gson gson = new Gson();
		String json = Integer.toString(id_servidor);

		HttpService httpService = new HttpService();
		HttpResponse response = httpService.sendGETRequest(
				Constantes.CONSULTAR_TIPO_SERVIDOR, json);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			json = HttpUtil.entityToString(response);

			servidor = gson.fromJson(json, Servidor.class);
		} else {
			servidor = null;
		}

		return servidor;
	}
}
