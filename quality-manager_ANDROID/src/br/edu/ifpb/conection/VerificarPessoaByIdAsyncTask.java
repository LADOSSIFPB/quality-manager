package br.edu.ifpb.conection;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.util.IntegerUtil;
import br.edu.ifpb.util.Constantes;

import com.google.gson.Gson;

public class VerificarPessoaByIdAsyncTask extends
		AsyncTask<Void, Integer, Pessoa> {

	private int id_servidor;

	public VerificarPessoaByIdAsyncTask(int id_servidor) {
		this.id_servidor = id_servidor;
	}

	@Override
	protected Pessoa doInBackground(Void... arg0) {
		JSONObject jsonObject = new JSONObject();
		Pessoa pessoa = new Pessoa();

		Gson gson = new Gson();
		String json = gson.toJson(new IntegerUtil(id_servidor));

		try {

			jsonObject = new JSONObject(json);

			HttpService httpService = new HttpService();
			HttpResponse response = httpService.sendJsonPostRequest(
					Constantes.CONSULTAR_PESSOA_BY_ID, jsonObject);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				json = HttpUtil.entityToString(response);

				pessoa = gson.fromJson(json, Pessoa.class);
			} else {
				pessoa = null;
			}

		} catch (JSONException e) {
			pessoa = null;
		} catch (IOException e) {
			pessoa = null;
		}

		return pessoa;
	}
}
