package br.edu.ifpb.conection;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.MembroProjeto;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.util.Constantes;

import com.google.gson.Gson;

public class ValidarLoginAsyncTask extends AsyncTask<Void, Integer, Pessoa> {

	private Login login;
	private Activity activity;

	public ValidarLoginAsyncTask(Login login, Activity activity) {
		this.login = login;
		this.activity = activity;
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

			if (response.getStatusLine().getStatusCode() != 200) {
				Toast toast = Toast.makeText(this.activity,
						"Cadastro não Existente", Toast.LENGTH_LONG);
				toast.show();
			}

			json = HttpUtil.entityToString(response);

			pessoa = gson.fromJson(json, Pessoa.class);

		} catch (JSONException e) {
			Toast toast = Toast.makeText(this.activity,
					"ERRO: Conexão Encerrada", Toast.LENGTH_LONG);
		} catch (IOException e) {
			Toast toast = Toast.makeText(this.activity,
					"ERRO: Conexão Encerrada", Toast.LENGTH_LONG);
		}

		return pessoa;
	}
}