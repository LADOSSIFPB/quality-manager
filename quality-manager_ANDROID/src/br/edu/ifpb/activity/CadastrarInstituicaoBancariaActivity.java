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
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.util.Constantes;
import br.edu.ifpb.util.Mascara;
import br.edu.ifpb.util.SessionManager;
import br.edu.ifpb.util.Validação;

public class CadastrarInstituicaoBancariaActivity extends Activity implements
		OnClickListener {

	private InstituicaoBancaria instituicaoBancaria = new InstituicaoBancaria();
	private Intent intent;
	private Bundle params;
	private Servidor servidor;
	private EditText editTextNomeBanco;
	private EditText editTextCNPJ;
	private Button buttonCadastrar;
	private ActionBar actionBar;
	private AlertDialog alertDialog;
	private AlertDialog.Builder builderLogout;
	private SessionManager sessionManager;
	private int IdPessoa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_instituicao_bancaria);

		intent = getIntent();
		params = intent.getExtras();
		IdPessoa = params.getInt("Gestor");

		findViews();

		addMascaras();

		// Action do Button Cadastrar Instituição Financiadora
		buttonCadastrar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (validateAll()) {
			instituicaoBancaria.setCnpj((editTextCNPJ).getText().toString());
			instituicaoBancaria.setNomeBanco((editTextNomeBanco).getText()
					.toString());

			ParserAsyncTask<InstituicaoBancaria> parser = new ParserAsyncTask<InstituicaoBancaria>(
					instituicaoBancaria, this,
					Constantes.CADASTRAR_INSTITUICAO_BANCARIA);
			parser.execute();

			intent = new Intent(this, InstituicaoBancaria.class);
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
		editTextNomeBanco = (EditText) findViewById(R.id.editTextNomeBanco);
		editTextCNPJ = (EditText) findViewById(R.id.editTextCNPJ);
		buttonCadastrar = (Button) findViewById(R.id.buttonCadastrarCurso);
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
		if (Validação.validarCampo(editTextNomeBanco))
			if (Validação.validarCampo(editTextCNPJ))
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