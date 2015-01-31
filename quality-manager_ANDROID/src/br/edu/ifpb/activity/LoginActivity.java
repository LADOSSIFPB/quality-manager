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
import br.edu.ifpb.conection.FazerLoginAsyncTask;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.util.Constantes;
import br.edu.ifpb.util.SessionManager;
import br.edu.ifpb.util.VerificaTipoPessoa;

public class LoginActivity extends Activity implements OnClickListener {

	private Intent intent;
	private EditText editTextMatriculaView;
	private EditText editTextSenhaView;
	private Button buttonLogin;
	private SessionManager sessionManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		findViews();

		buttonLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Login login = new Login();
		Pessoa pessoa = new Pessoa();

		login.setIdentificador(editTextMatriculaView.getText().toString());
		login.setSenha(editTextSenhaView.getText().toString());

		FazerLoginAsyncTask fazerLoginAsyncTask = new FazerLoginAsyncTask(login);

		try {

			pessoa = fazerLoginAsyncTask.execute().get();

		} catch (InterruptedException e) {
			Toast toast = Toast.makeText(getApplicationContext(),
					Constantes.ERROR_PROBLEMA_COMUNICACAO_SERVIDOR,
					Toast.LENGTH_LONG);
			toast.show();
		} catch (ExecutionException e) {
			Toast toast = Toast.makeText(getApplicationContext(),
					Constantes.ERROR_PROBLEMA_COMUNICACAO_SERVIDOR,
					Toast.LENGTH_LONG);
			toast.show();
		}

		if (pessoa != null) {
			VerificaTipoPessoa verificaTipoPessoa = new VerificaTipoPessoa(
					pessoa, this);
			sessionManager.createLoginSession(pessoa.getPessoaId());
			intent = verificaTipoPessoa.verificarTipoPessoa();
			startActivity(intent);
			finish();
		} else {
			Toast toast = Toast.makeText(getApplicationContext(),
					"Login ou Senha Inválido!", Toast.LENGTH_LONG);
			toast.show();
		}
	}

	public void findViews() {
		editTextMatriculaView = (EditText) findViewById(R.id.editTextMatricula);
		editTextSenhaView = (EditText) findViewById(R.id.editTextSenha);
		buttonLogin = (Button) findViewById(R.id.buttonLogin);
		sessionManager = new SessionManager(getApplicationContext());
	}

}
