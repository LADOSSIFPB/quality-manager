package br.edu.ifpb.conection;

import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import br.edu.ifpb.activity.LoginActivity;
import br.edu.ifpb.alertdialog.SemConexaoAlertDialog;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.util.Constantes;
import br.edu.ifpb.util.SessionManager;
import br.edu.ifpb.util.VerificaTipoPessoa;

public class VerificarConexaoAsyncTask extends
		AsyncTask<Void, Integer, JSONObject> {

	private Activity activity;
	private SessionManager session;
	private ProgressBar progressBar;

	public VerificarConexaoAsyncTask(Activity activity, ProgressBar progressBar) {
		this.activity = activity;
		session = new SessionManager(this.activity);
		this.progressBar = progressBar;
	}

	@Override
	protected void onPreExecute() {
		progressBar.setVisibility(View.VISIBLE);
		super.onPreExecute();
	}

	@Override
	protected JSONObject doInBackground(Void... arg0) {
		JSONObject jsonObject = null;

		try {

			// Enviar a requisição HTTP via GET.
			HttpService httpService = new HttpService();
			HttpResponse response = httpService
					.sendGETRequest(Constantes.SERVIDOR_ONLINE);

			if (response != null
					&& response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String json = HttpUtil.entityToString(response);
				jsonObject = new JSONObject(json);
			} else {
				httpService.setUrl(Constantes.URL_INTERNA_SERVICE);
				response = httpService
						.sendGETRequest(Constantes.SERVIDOR_ONLINE);
				if (response != null
						&& response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String json = HttpUtil.entityToString(response);
					jsonObject = new JSONObject(json);
				} else {
					httpService.setUrl(Constantes.URL_LOCAL_SERVICE);
					response = httpService
							.sendGETRequest(Constantes.SERVIDOR_ONLINE);
					if (response != null
							&& response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						String json = HttpUtil.entityToString(response);
						jsonObject = new JSONObject(json);
					}
				}
			}
		} catch (JSONException e) {

			Log.e("AsyncTaskKJson", "Error parsing data " + e.toString());
		}

		return jsonObject;
	}

	@Override
	protected void onPostExecute(JSONObject result) {

		if (result != null) {
			try {
				boolean online = result.getBoolean("online");

				Log.i("AsyncTaskKJson", "Servidor conectado: " + online);

				if (online) {
					if (session.checkLogin()) {
						VerificarPessoaByIdAsyncTask pessoaByIdAsyncTask = new VerificarPessoaByIdAsyncTask(
								session.getUserDetails());
						Pessoa pessoa = new Pessoa();

						try {

							pessoa = pessoaByIdAsyncTask.execute().get();

						} catch (InterruptedException e) {
							Toast toast = Toast.makeText(
									activity.getApplicationContext(),
									Constantes.ERROR_COMUNICACAO_SERVIDOR_OFF,
									Toast.LENGTH_LONG);
							toast.show();
						} catch (ExecutionException e) {
							Toast toast = Toast.makeText(
									activity.getApplicationContext(),
									Constantes.ERROR_COMUNICACAO_SERVIDOR_OFF,
									Toast.LENGTH_LONG);
							toast.show();
						}

						if (pessoa != null) {
							VerificaTipoPessoa verificaTipoPessoa = new VerificaTipoPessoa(
									pessoa, activity);
							Intent intent = verificaTipoPessoa
									.verificarTipoPessoa();
							activity.startActivity(intent);
							activity.finish();
						} else {
							Toast toast = Toast.makeText(
									activity.getApplicationContext(),
									"Login ou Senha Inválido!",
									Toast.LENGTH_LONG);
							toast.show();
						}
					} else {
						Intent intent = new Intent(activity,
								LoginActivity.class);
						activity.startActivity(intent);
						activity.finish();
					}
				} else {
					Toast toast = Toast.makeText(
							activity.getApplicationContext(),
							Constantes.ERROR_COMUNICACAO_SERVIDOR_OFF,
							Toast.LENGTH_LONG);
					toast.show();
				}

			} catch (JSONException e) {
				Log.e("AsyncTaskKJson", "Error parsing data " + e.toString());
			}
		} else {
			SemConexaoAlertDialog semConexaoAlertDialog = new SemConexaoAlertDialog(
					this.activity);
			semConexaoAlertDialog.showAlertDialog();
		}
		progressBar.setVisibility(View.INVISIBLE);
	}
}