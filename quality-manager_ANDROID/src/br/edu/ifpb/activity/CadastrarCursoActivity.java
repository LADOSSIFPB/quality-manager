package br.edu.ifpb.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import br.edu.ifpb.R;
import br.edu.ifpb.conection.ParserAsyncTask;
import br.edu.ifpb.conection.VerificarCoordenadoresAsyncTask;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.util.Constantes;
import br.edu.ifpb.util.SessionManager;
import br.edu.ifpb.util.Validação;

public class CadastrarCursoActivity extends Activity implements OnClickListener {

	private Curso curso = new Curso();
	private Intent intent;
	private Bundle params;
	private Servidor servidor;
	private Servidor coordenador;
	private List<Servidor> coordenadores;
	private EditText editTextNomeCurso;
	private EditText editTextCoordenador;
	private ImageView imageSearch;
	private Button buttonCadastrar;
	private AlertDialog levelDialog;
	private AlertDialog.Builder builder;
	private CharSequence[] nomeCoordenadores;
	private ActionBar actionBar;
	private AlertDialog alertDialog;
	private AlertDialog.Builder builderLogout;
	private SessionManager sessionManager;
	private int IdPessoa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_curso);

		intent = getIntent();
		params = intent.getExtras();
		IdPessoa = params.getInt("Gestor");

		findViews();

		// Action do Button Cadastrar Curso
		buttonCadastrar.setOnClickListener(this);
		imageSearch.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonCadastrarCurso:
			if (validateAll()) {
				curso.setNomeCurso((editTextNomeCurso).getText().toString());
				curso.setCoordenador(coordenador);
				curso.setGestor(servidor);

				ParserAsyncTask<Curso> parser = new ParserAsyncTask<Curso>(
						curso, this, Constantes.CADASTRAR_CURSO);
				parser.execute();

				intent = new Intent(this, CursoActivity.class);
				params.putInt("Gestor", IdPessoa);
				intent.putExtras(params);
				startActivity(intent);
				finish();
			}
			break;
		case R.id.imageSearch:
			coordenador.setNomePessoa((editTextCoordenador).getText()
					.toString());

			VerificarCoordenadoresAsyncTask verificar = new VerificarCoordenadoresAsyncTask(
					coordenador);

			try {

				coordenadores = verificar.execute().get();

				createListCoordenador();

				builder.setTitle("Coordenadores");
				builder.setSingleChoiceItems(nomeCoordenadores, -1,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int item) {
								for (int i = 0; i < coordenadores.size(); i++) {
									if (i == item) {
										coordenador = coordenadores.get(i);
										editTextCoordenador.setText(coordenador
												.getNomePessoa());
									}
								}
								levelDialog.dismiss();
							}
						});
				levelDialog = builder.create();
				levelDialog.show();

			} catch (InterruptedException e) {
				Toast toast = Toast.makeText(getApplicationContext(),
						Constantes.ERROR_PROBLEMA_COMUNICACAO_SERVIDOR,
						Toast.LENGTH_SHORT);
				toast.show();
				Intent intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
				finish();
			} catch (ExecutionException e) {
				Toast toast = Toast.makeText(getApplicationContext(),
						Constantes.ERROR_PROBLEMA_COMUNICACAO_SERVIDOR,
						Toast.LENGTH_SHORT);
				toast.show();
				Intent intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return (true);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.logout:
			alertDialogLogout();
			break;
		}
		return (true);
	}

	public void findViews() {
		servidor = new Servidor();
		servidor.setPessoaId(params.getInt("Gestor"));
		imageSearch = (ImageView) findViewById(R.id.imageSearch);
		editTextNomeCurso = (EditText) findViewById(R.id.editTextCurso);
		editTextCoordenador = (EditText) findViewById(R.id.editTextCoordenador);
		buttonCadastrar = (Button) findViewById(R.id.buttonCadastrarCurso);
		coordenador = new Servidor();
		coordenadores = new ArrayList<Servidor>();
		builder = new AlertDialog.Builder(this);
		actionBar = getActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background_menu));
		builderLogout = new AlertDialog.Builder(this);
		sessionManager = new SessionManager(this);
	}

	public void createListCoordenador() {
		List<String> names = new ArrayList<String>();

		for (int i = 0; i < coordenadores.size(); i++) {
			names.add(coordenadores.get(i).getNomePessoa());
		}

		nomeCoordenadores = names.toArray(new CharSequence[names.size()]);
	}

	public boolean validateAll() {
		if (Validação.validarCampo(editTextNomeCurso))
			if (Validação.validarCampo(editTextCoordenador))
				return true;
		return false;
	}

	public void alertDialogLogout() {
		builderLogout.setTitle("Sair");
		builderLogout.setMessage("Deseja sair agora?");
		builderLogout.setPositiveButton("Sair",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						sessionManager.logoutUser();
					}
				});
		builderLogout.setNegativeButton("Cancelar",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		alertDialog = builderLogout.create();
		alertDialog.show();
	}

}
