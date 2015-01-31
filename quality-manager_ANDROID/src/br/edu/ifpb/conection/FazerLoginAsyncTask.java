package br.edu.ifpb.conection;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.util.Constantes;

import com.google.gson.Gson;

public class FazerLoginAsyncTask extends AsyncTask<Void, Integer, Pessoa> {

	private Login login;

	public FazerLoginAsyncTask(Login login) {
		this.login = login;
	}

	@Override
	protected Pessoa doInBackground(Void... params) {
		JSONObject jsonObject = new JSONObject();
		Pessoa pessoa = new Pessoa();

		Gson gson = new Gson();
		String json = gson.toJson(this.login);

		try {

			jsonObject = new JSONObject(json);

			HttpService httpService = new HttpService();
			HttpResponse response = httpService.sendJsonPostRequest(
					Constantes.CONSULTAR_LOGIN, jsonObject);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_ACCEPTED) {
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