package br.edu.ifpb.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import br.edu.ifpb.R;

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
	private Toast toast;

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
			toast = Toast.makeText(this.getApplicationContext(),
					"Opção Indisponível no momento.", Toast.LENGTH_SHORT);
			toast.show();
			break;
		case R.id.imageOrientador:
			toast = Toast.makeText(this.getApplicationContext(),
					"Opção Indisponível no momento.", Toast.LENGTH_SHORT);
			toast.show();
			break;
		case R.id.imageInstituicaoBancaria:
			toast = Toast.makeText(this.getApplicationContext(),
					"Opção Indisponível no momento.", Toast.LENGTH_SHORT);
			toast.show();
			break;
		}
	}

	public void findViews() {
		imageInstituicaoFinanciadora = (ImageView) findViewById(R.id.imageInstituicaoFinanciadora);
		imageProgramaInstitucional = (ImageView) findViewById(R.id.imageProgramaInstitucional);
		imageEdital = (ImageView) findViewById(R.id.imageEdital);
		imageCurso = (ImageView) findViewById(R.id.imageCurso);
		imageOrientador = (ImageView) findViewById(R.id.imageOrientador);
		imageInstituicaoBancaria = (ImageView) findViewById(R.id.imageInstituicaoBancaria);
	}

}
