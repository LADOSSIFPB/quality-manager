package br.edu.ifpb.activity;

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
import br.edu.ifpb.R;
import br.edu.ifpb.conection.ParserAsyncTask;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.util.Constantes;
import br.edu.ifpb.util.Mascara;
import br.edu.ifpb.util.SessionManager;
import br.edu.ifpb.util.Valida��o;

public class CadastrarInstituicaoFinanciadoraActivity extends Activity
		implements OnClickListener {

	private InstituicaoFinanciadora instituicaoFinanciadora = new InstituicaoFinanciadora();
	private Intent intent;
	private Bundle params;
	private Servidor servidor;
	private EditText editTextCNPJ;
	private EditText editTextNomeInstituicaoFinanciadora;
	private EditText editTextSigla;
	private Button buttonCadastrar;
	private ActionBar actionBar;
	private AlertDialog alertDialog;
	private AlertDialog.Builder builderLogout;
	private SessionManager sessionManager;
	private int IdPessoa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_instituicao_financiadora);

		intent = getIntent();
		params = intent.getExtras();
		IdPessoa = params.getInt("Gestor");

		findViews();

		addMascaras();

		// Action do Button Cadastrar Institui��o Financiadora
		buttonCadastrar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (validateAll()) {
			instituicaoFinanciadora.setCnpj(Mascara.unmask((editTextCNPJ)
					.getText().toString()));
			instituicaoFinanciadora
					.setNomeInstituicaoFinanciadora((editTextNomeInstituicaoFinanciadora)
							.getText().toString());
			instituicaoFinanciadora.setSigla((editTextSigla).getText()
					.toString());
			instituicaoFinanciadora.setGestor(servidor);

			ParserAsyncTask<InstituicaoFinanciadora> parser = new ParserAsyncTask<InstituicaoFinanciadora>(
					instituicaoFinanciadora, this,
					Constantes.CADASTRAR_INSTITUICAO_FINANCIADORA);
			parser.execute();

			intent = new Intent(this, InstituicaoFinanciadoraActivity.class);
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

	public void findViews() {
		servidor = new Servidor();
		servidor.setPessoaId(params.getInt("Gestor"));
		editTextCNPJ = (EditText) findViewById(R.id.editTextCNPJ);
		editTextNomeInstituicaoFinanciadora = (EditText) findViewById(R.id.editTextNomeInstuicaoFinanciadora);
		editTextSigla = (EditText) findViewById(R.id.editTextSigla);
		buttonCadastrar = (Button) findViewById(R.id.buttonCadastrarInstituicaoFinanciadora);
		actionBar = getActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background_menu));
		builderLogout = new AlertDialog.Builder(this);
		sessionManager = new SessionManager(this);
	}

	public void addMascaras() {
		// Adicionar Mascara aos campos CNPJ
		editTextCNPJ.addTextChangedListener(Mascara.insert(
				"##.###.###/####-##", editTextCNPJ));
	}

	public boolean validateAll() {
		if (Valida��o.validarCampo(editTextCNPJ))
			if (Valida��o.validarCampo(editTextNomeInstituicaoFinanciadora))
				if (Valida��o.validarCampo(editTextSigla))
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
