package br.edu.ifpb.conection;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import br.edu.ifpb.activity.GestorActivity;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.util.Constantes;

public class ParserProgramaInstitucional extends
		AsyncTask<Void, Integer, JSONObject> {

	private Activity activity;
	private ProgramaInstitucional programaInstitucional;
	private ProgressDialog pDialog;

	public ParserProgramaInstitucional(ProgramaInstitucional entidade,
			Activity activity) {
		this.programaInstitucional = entidade;
		this.activity = activity;
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

		try {
			jsonObject.put("instituicaoFinanciadora",
					programaInstitucional.getInstituicaoFinanciadora());
			jsonObject.put("orcamento", programaInstitucional.getOrcamento());
			jsonObject.put("sigla", programaInstitucional.getSigla());
			jsonObject.put("nomeInstituicaoFinanciadora",
					programaInstitucional.getNomeProgramaInstitucional());

			HttpService httpService = new HttpService();
			HttpResponse response = httpService.sendJsonPostRequest(
					Constantes.CADASTRAR_INSTITUICAO_FINANCIADORA, jsonObject);

			if (response.getStatusLine().getStatusCode() != 200) {
				Toast toast = Toast.makeText(activity.getApplicationContext(),
						response.getStatusLine().getStatusCode(),
						Toast.LENGTH_LONG);
				toast.show();
			}

			// Conversão do response ( resposta HTTP) para String.

			String json = HttpUtil.entityToString(response);
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
		// InstituicaoFinanciadora instituicaoFinanciadora = new
		// InstituicaoFinanciadora();
		// try {
		// instituicaoFinanciadora.setCnpj(result.getString("cnpj"));
		// instituicaoFinanciadora.setNomeInstituicaoFinanciadora(result
		// .getString("nomeInstituicaoFinanciadora"));
		// instituicaoFinanciadora.setSigla(result.getString("sigla"));
		// instituicaoFinanciadora.setOrcamento(Double.parseDouble(result
		// .getString("orcamento")));
		// } catch (JSONException e) {
		// Log.e("AsyncTaskKJson", "Error parsing data " + e.toString());
		// }
		String mensagem = "Programa Institucional Cadastrado com Sucesso!";
		Toast toast = Toast.makeText(activity.getApplicationContext(),
				mensagem, Toast.LENGTH_SHORT);
		toast.show();
		Intent intent = new Intent(activity, GestorActivity.class);
		activity.startActivity(intent);
	}
}