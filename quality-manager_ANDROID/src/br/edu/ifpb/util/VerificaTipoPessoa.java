package br.edu.ifpb.util;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import br.edu.ifpb.activity.DiscenteActivity;
import br.edu.ifpb.activity.GestorActivity;
import br.edu.ifpb.activity.ProfessorActivity;
import br.edu.ifpb.conection.VerificarTipoServidorAsyncTask;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.Servidor;

public class VerificaTipoPessoa {

	private Intent intent;
	private Bundle params;
	private Pessoa pessoa;
	private Activity activity;

	public VerificaTipoPessoa(Pessoa pessoa, Activity activity) {
		this.pessoa = pessoa;
		this.activity = activity;
	}

	public Intent verificarTipoPessoa() {
		intent = new Intent();
		params = new Bundle();

		switch (pessoa.getTipoPessoa().getIdTipoPessoa()) {
		case 1:
			VerificarTipoServidorAsyncTask servidorAsyncTask = new VerificarTipoServidorAsyncTask(
					pessoa.getPessoaId());
			Servidor servidor = new Servidor();

			try {

				servidor = servidorAsyncTask.execute().get();

			} catch (InterruptedException e) {
				Toast toast = Toast.makeText(activity.getApplicationContext(),
						"ERRO: Conexão Encerrada", Toast.LENGTH_LONG);
				toast.show();
			} catch (ExecutionException e) {
				Toast toast = Toast.makeText(activity.getApplicationContext(),
						"ERRO: Conexão Encerrada", Toast.LENGTH_LONG);
				toast.show();
			}

			if (servidor != null) {
				verificarTipoServidor(servidor);
			} else {
				Toast toast = Toast.makeText(activity.getApplicationContext(),
						"Login ou Senha Inválido!", Toast.LENGTH_LONG);
				toast.show();
			}
			break;
		case 2:
			intent = new Intent(activity, DiscenteActivity.class);
			params.putInt("Discente", pessoa.getPessoaId());
			intent.putExtras(params);
			break;
		}

		return intent;
	}

	public void verificarTipoServidor(Servidor servidor) {
		switch (servidor.getCargoServidor().getIdCargoServidor()) {
		case 1:
			intent = new Intent(activity, GestorActivity.class);
			params.putInt("Gestor", servidor.getPessoaId());
			intent.putExtras(params);
			break;
		case 2:
			// this.intent = new Intent(this, CoordenadorActivity.class);
			// params.putInt("Coordenador", servidor.getPessoaId());
			// intent.putExtras(params);
			break;
		case 3:
			this.intent = new Intent(activity, ProfessorActivity.class);
			params.putInt("Professor", servidor.getPessoaId());
			intent.putExtras(params);
			break;
		case 4:
			// this.intent = new Intent(this, TecnicoActivity.class);
			// params.putInt("Tecnico", servidor.getPessoaId());
			// intent.putExtras(params);
			break;
		}
	}

}
