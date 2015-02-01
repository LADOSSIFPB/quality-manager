package br.edu.ifpb.conection;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

import android.os.AsyncTask;
import br.edu.ifpb.qmanager.entidade.Pessoa;
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
		String json = Integer.toString(this.id_servidor);

		// jsonObject = new JSONObject(json);

		HttpService httpService = new HttpService();
		HttpResponse response = httpService.sendGETRequest(
				Constantes.CONSULTAR_PESSOA_BY_ID, json);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			json = HttpUtil.entityToString(response);

			pessoa = gson.fromJson(json, Pessoa.class);
		} else {
			pessoa = null;
		}

		return pessoa;
	}
}
