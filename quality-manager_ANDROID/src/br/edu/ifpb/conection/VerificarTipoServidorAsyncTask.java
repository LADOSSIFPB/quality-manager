package br.edu.ifpb.conection;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.util.IntegerUtil;
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
		String json = gson.toJson(new IntegerUtil(id_servidor));

		try {

			jsonObject = new JSONObject(json);

			HttpService httpService = new HttpService();
			HttpResponse response = httpService.sendJsonPostRequest(
					Constantes.CONSULTAR_TIPO_SERVIDOR, jsonObject);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				json = HttpUtil.entityToString(response);

				servidor = gson.fromJson(json, Servidor.class);
			} else {
				servidor = null;
			}

		} catch (JSONException e) {
			servidor = null;
		} catch (IOException e) {
			servidor = null;
		}

		return servidor;
	}

}
