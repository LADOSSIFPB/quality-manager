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
import android.widget.ImageView;
import br.edu.ifpb.R;
import br.edu.ifpb.util.SessionManager;

public class MenuGestorActivity extends Activity implements OnClickListener {

	private ImageView imageInstituicaoFinanciadora;
	private ImageView imageProgramaInstitucional;
	private ImageView imageEdital;
	private ImageView imageCurso;
	private ImageView imageOrientador;
	private ImageView imageInstituicaoBancaria;
	private Intent intent;
	private Bundle params;
	private int IdPessoa;
	private ActionBar actionBar;
	private AlertDialog alertDialog;
	private AlertDialog.Builder builderLogout;
	private SessionManager sessionManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_gestor);

		intent = getIntent();
		params = intent.getExtras();
		IdPessoa = params.getInt("Gestor");

		findViews();

		imageInstituicaoFinanciadora.setOnClickListener(this);
		imageProgramaInstitucional.setOnClickListener(this);
		imageEdital.setOnClickListener(this);
		imageCurso.setOnClickListener(this);
		imageOrientador.setOnClickListener(this);
		imageInstituicaoBancaria.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageInstituicaoFinanciadora:
			intent = new Intent(this, InstituicaoFinanciadoraActivity.class);
			params.putInt("Gestor", IdPessoa);
			intent.putExtras(params);
			startActivity(intent);
			break;
		case R.id.imageProgramaInstitucional:
			intent = new Intent(this, ProgramaInstitucionalActivity.class);
			params.putInt("Gestor", IdPessoa);
			intent.putExtras(params);
			startActivity(intent);
			break;
		case R.id.imageEdital:
			intent = new Intent(this, EditalActivity.class);
			params.putInt("Gestor", IdPessoa);
			intent.putExtras(params);
			startActivity(intent);
			break;
		case R.id.imageCurso:
			intent = new Intent(this, CursoActivity.class);
			params.putInt("Gestor", IdPessoa);
			intent.putExtras(params);
			startActivity(intent);
			break;
		case R.id.imageOrientador:
			intent = new Intent(this, OrientadorActivity.class);
			params.putInt("Gestor", IdPessoa);
			intent.putExtras(params);
			startActivity(intent);
			break;
		case R.id.imageInstituicaoBancaria:
			intent = new Intent(this, InstituicaoBancariaActivity.class);
			params.putInt("Gestor", IdPessoa);
			intent.putExtras(params);
			startActivity(intent);
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
		imageInstituicaoFinanciadora = (ImageView) findViewById(R.id.imageInstituicaoFinanciadora);
		imageProgramaInstitucional = (ImageView) findViewById(R.id.imageProgramaInstitucional);
		imageEdital = (ImageView) findViewById(R.id.imageEdital);
		imageCurso = (ImageView) findViewById(R.id.imageCurso);
		imageOrientador = (ImageView) findViewById(R.id.imageOrientador);
		imageInstituicaoBancaria = (ImageView) findViewById(R.id.imageInstituicaoBancaria);
		actionBar = getActionBar();
		actionBar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background_menu));
		builderLogout = new AlertDialog.Builder(this);
		sessionManager = new SessionManager(this);
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
