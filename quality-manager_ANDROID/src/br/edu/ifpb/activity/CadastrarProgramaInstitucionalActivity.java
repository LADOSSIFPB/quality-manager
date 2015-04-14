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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import br.edu.ifpb.R;
import br.edu.ifpb.conection.ParserAsyncTask;
import br.edu.ifpb.conection.PreencherSpinnerInstituicaoFinanciadoraAsyncTask;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.util.Constantes;
import br.edu.ifpb.util.SessionManager;
import br.edu.ifpb.util.Validação;

public class CadastrarProgramaInstitucionalActivity extends Activity implements
		OnClickListener {

	private Intent intent;
	private Bundle params;
	private Servidor servidor;
	private ProgramaInstitucional programaInstitucional = new ProgramaInstitucional();
	private List<InstituicaoFinanciadora> instituicoesFinanciadoras = new ArrayList<InstituicaoFinanciadora>();
	private List<String> siglaInstituicoes;
	private EditText editTextNomeProgramaInstitucional;
	private EditText editTextSigla;
	private Spinner spinnerInstituicaoFinanciadora;
	private Button buttonCadastrar;
	private ActionBar actionBar;
	private AlertDialog alertDialog;
	private AlertDialog.Builder builderLogout;
	private SessionManager sessionManager;
	private int IdPessoa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_programa_institucional);

		PreencherSpinnerInstituicaoFinanciadoraAsyncTask preencherSpinner = new PreencherSpinnerInstituicaoFinanciadoraAsyncTask();

		try {
			instituicoesFinanciadoras = preencherSpinner.execute().get();

			if (instituicoesFinanciadoras != null) {
				findViews();
				createListSigla();
				ativarSpinner(spinnerInstituicaoFinanciadora, siglaInstituicoes);
				buttonCadastrar.setOnClickListener(this);
			} else {
				Toast toast = Toast.makeText(getApplicationContext(),
						Constantes.ERROR_INSTITUICAO_FINANCIADORA_NULL,
						Toast.LENGTH_LONG);
				toast.show();
				intent = new Intent(getApplicationContext(),
						MenuGestorActivity.class);
				startActivity(intent);
				finish();
			}
		} catch (InterruptedException e) {
			Toast toast = Toast.makeText(this, "ERRO: Conexão Encerrada",
					Toast.LENGTH_LONG);
			toast.show();
			intent = new Intent(getApplicationContext(), LoginActivity.class);
			startActivity(intent);
			finish();
		} catch (ExecutionException e) {
			Toast toast = Toast.makeText(this, "ERRO: Conexão Encerrada",
					Toast.LENGTH_LONG);
			toast.show();
			intent = new Intent(getApplicationContext(), LoginActivity.class);
			startActivity(intent);
			finish();
		}
	}

	@Override
	public void onClick(View v) {
		if (validateAll()) {
			programaInstitucional
					.setNomeProgramaInstitucional((editTextNomeProgramaInstitucional)
							.getText().toString());
			programaInstitucional
					.setSigla((editTextSigla).getText().toString());
			programaInstitucional
					.setInstituicaoFinanciadora(itemSelectSpinner());
			programaInstitucional.setGestor(servidor);

			ParserAsyncTask<ProgramaInstitucional> parser = new ParserAsyncTask<ProgramaInstitucional>(
					programaInstitucional, this,
					Constantes.CADASTRAR_PROGRAMA_INSTITUCIONAL);
			parser.execute();

			intent = new Intent(this, ProgramaInstitucionalActivity.class);
			params.putInt("Gestor", IdPessoa);
			intent.putExtras(params);
			startActivity(intent);
			finish();
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

	public void ativarSpinner(Spinner generic, List<String> genericItems) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, genericItems);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		generic.setAdapter(adapter);
		generic.setSelection(0);

		generic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	public void findViews() {
		intent = getIntent();
		params = intent.getExtras();
		IdPessoa = params.getInt("Gestor");
		servidor = new Servidor();
		servidor.setPessoaId(params.getInt("Gestor"));
		siglaInstituicoes = new ArrayList<String>();
		editTextNomeProgramaInstitucional = (EditText) findViewById(R.id.editTextNomeProgramaInstitucional);
		editTextSigla = (EditText) findViewById(R.id.editTextSigla);
		spinnerInstituicaoFinanciadora = (Spinner) findViewById(R.id.spinnerInstituicaoFinanciadora);
		buttonCadastrar = (Button) findViewById(R.id.buttonCadastrarProgramaInstitucional);
		actionBar = getActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background_menu));
		builderLogout = new AlertDialog.Builder(this);
		sessionManager = new SessionManager(this);
	}

	public void createListSigla() {
		siglaInstituicoes = new ArrayList<String>();
		siglaInstituicoes.add(Constantes.MSG_INICIO_SPINNER);

		for (int i = 0; i < instituicoesFinanciadoras.size(); i++) {
			siglaInstituicoes.add(instituicoesFinanciadoras.get(i).getSigla());
		}
	}

	public boolean validateAll() {
		if (Validação.validarCampo(editTextNomeProgramaInstitucional))
			if (Validação.validarCampo(editTextSigla))
				if (Validação.validarSpinner(spinnerInstituicaoFinanciadora
						.getSelectedItem().toString(), getApplicationContext()))
					return true;
		return false;
	}

	public InstituicaoFinanciadora itemSelectSpinner() {
		InstituicaoFinanciadora instituicaoFinanciadora = new InstituicaoFinanciadora();
		for (int i = 0; i < instituicoesFinanciadoras.size(); i++) {
			if (instituicoesFinanciadoras.get(i).getSigla()
					.equals(spinnerInstituicaoFinanciadora.getSelectedItem())) {
				instituicaoFinanciadora = instituicoesFinanciadoras.get(i);
				break;
			}
		}

		return instituicaoFinanciadora;
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
