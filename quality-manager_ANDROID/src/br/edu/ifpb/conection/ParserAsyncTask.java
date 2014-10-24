package br.edu.ifpb.conection;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import br.edu.ifpb.activity.GestorActivity;

import com.google.gson.Gson;

public class ParserAsyncTask<E> extends AsyncTask<Void, Integer, JSONObject> {

	private Activity activity;
	private E entidade;
	private String path;
	private int IdCoordenador;
	private ProgressDialog pDialog;

	public ParserAsyncTask(E entidade, Activity activity, String path,
			int IdCoordenador) {
		this.entidade = entidade;
		this.activity = activity;
		this.path = path;
		this.IdCoordenador = IdCoordenador;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(activity);
		pDialog.setMessage("Cadastrando...");
		pDialog.setCancelable(false);
		pDialog.setIndeterminate(true);
		pDialog.show();
	}

	@Override
	protected JSONObject doInBackground(Void... params) {
		JSONObject jsonObject = new JSONObject();
		Gson gson = new Gson();
		String json = gson.toJson(this.entidade);

		try {
			jsonObject = new JSONObject(json);

			HttpService httpService = new HttpService();
			HttpResponse response = httpService.sendJsonPostRequest(this.path,
					jsonObject);

			if (response.getStatusLine().getStatusCode() != 200) {
				Toast toast = Toast.makeText(activity, "Cadastro inválido",
						Toast.LENGTH_LONG);
				toast.show();
			}

			// Conversão do response ( resposta HTTP) para String.

			json = HttpUtil.entityToString(response);
			jsonObject = new JSONObject(json);

		} catch (JSONException e) {
			Log.e("AsyncTaskKJson", "Error parsing data " + e.toString());
		} catch (IOException e2) {
			Log.e("AsyncTaskKJson", "Error parsing data " + e2.toString());
		}

		return jsonObject;
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		pDialog.dismiss();
		String mensagem = "Operação realizada com Sucesso!";
		Toast toast = Toast.makeText(activity.getApplicationContext(),
				mensagem, Toast.LENGTH_SHORT);
		toast.show();
		Intent intent = new Intent(activity, GestorActivity.class);
		Bundle params = new Bundle();
		params.putInt("Gestor", IdCoordenador);
		intent.putExtras(params);
		activity.startActivity(intent);
	}
}
