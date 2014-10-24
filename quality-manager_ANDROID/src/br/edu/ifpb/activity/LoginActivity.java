package br.edu.ifpb.activity;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.edu.ifpb.R;
import br.edu.ifpb.conection.ValidarLoginAsyncTask;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Pessoa;

public class LoginActivity extends Activity implements OnClickListener {

	private Intent intent;
	private Bundle params;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
		buttonLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Login login = new Login();
		Pessoa pessoa = new Pessoa();
		params = new Bundle();

		EditText editTextMatriculaView = (EditText) findViewById(R.id.editTextMatricula);
		login.setIdentificador(editTextMatriculaView.getText().toString());

		EditText editTextSenhaView = (EditText) findViewById(R.id.editTextSenha);
		login.setSenha(editTextSenhaView.getText().toString());

		ValidarLoginAsyncTask validarLoginAsyncTask = new ValidarLoginAsyncTask(
				login, this);

		try {

			pessoa = validarLoginAsyncTask.execute().get();

		} catch (InterruptedException e) {
			Toast toast = Toast.makeText(this, "ERRO: Conexão Encerrada",
					Toast.LENGTH_LONG);
		} catch (ExecutionException e) {
			Toast toast = Toast.makeText(this, "ERRO: Conexão Encerrada",
					Toast.LENGTH_LONG);
		}

		if (pessoa.getTipoPessoa().getIdTipoPessoa() == 4) {
			this.intent = new Intent(this, GestorActivity.class);
			params.putInt("Gestor", pessoa.getPessoaId());
			intent.putExtras(params);
		} else if (pessoa.getTipoPessoa().getIdTipoPessoa() == 2) {
			this.intent.putExtra("Orientador", pessoa.getPessoaId());
			params.putInt("Orientador", pessoa.getPessoaId());
			intent.putExtras(params);
		} else if (pessoa.getTipoPessoa().getIdTipoPessoa() == 3) {
			this.intent.putExtra("Discente", pessoa.getPessoaId());
			params.putInt("Discente", pessoa.getPessoaId());
			intent.putExtras(params);
		}

		startActivity(intent);
	}
}
