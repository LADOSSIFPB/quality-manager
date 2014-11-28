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
	private EditText editTextMatriculaView;
	private EditText editTextSenhaView;
	private Button buttonLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		findView();

		buttonLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Login login = new Login();
		Pessoa pessoa = new Pessoa();
		params = new Bundle();

		login.setIdentificador(editTextMatriculaView.getText().toString());
		login.setSenha(editTextSenhaView.getText().toString());

		ValidarLoginAsyncTask validarLoginAsyncTask = new ValidarLoginAsyncTask(
				login);

		try {

			pessoa = validarLoginAsyncTask.execute().get();

		} catch (InterruptedException e) {
			Toast toast = Toast.makeText(this, "ERRO: Conexão Encerrada",
					Toast.LENGTH_LONG);
			toast.show();
		} catch (ExecutionException e) {
			Toast toast = Toast.makeText(this, "ERRO: Conexão Encerrada",
					Toast.LENGTH_LONG);
			toast.show();
		}

		if (pessoa != null) {
			verificarTipoPessoa(pessoa);
			startActivity(intent);
		} else {
			Toast toast = Toast.makeText(getApplicationContext(),
					"Login ou Senha Inválido!", Toast.LENGTH_LONG);
			toast.show();
		}
	}

	public void findView() {
		editTextMatriculaView = (EditText) findViewById(R.id.editTextMatricula);
		editTextSenhaView = (EditText) findViewById(R.id.editTextSenha);
		buttonLogin = (Button) findViewById(R.id.buttonLogin);
	}

	public void verificarTipoPessoa(Pessoa pessoa) {
		switch (pessoa.getTipoPessoa().getIdTipoPessoa()) {
		case 1:
			this.intent = new Intent(this, LoginActivity.class);
			Toast toast = Toast.makeText(getApplicationContext(),
					"Opções de Coordenador Indisponíveis no momento!",
					Toast.LENGTH_LONG);
			toast.show();
			break;
		case 2:
			this.intent = new Intent(this, OrientadorActivity.class);
			params.putInt("Orientador", pessoa.getPessoaId());
			intent.putExtras(params);
			break;
		case 3:
			this.intent = new Intent(this, DiscenteActivity.class);
			params.putInt("Discente", pessoa.getPessoaId());
			intent.putExtras(params);
			break;
		case 4:
			intent = new Intent(this, GestorActivity.class);
			params.putInt("Gestor", pessoa.getPessoaId());
			intent.putExtras(params);
			break;
		}
	}
}
