package br.edu.ifpb.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import br.edu.ifpb.qmanager.entidade.Gestor;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.util.Constantes;
import br.edu.ifpb.util.Mascara;
import br.edu.ifpb.util.Validação;

public class CadastrarProgramaInstitucionalActivity extends Activity implements
		OnClickListener {

	private Intent intent;
	private Bundle params;
	private ProgramaInstitucional programaInstitucional = new ProgramaInstitucional();
	private List<InstituicaoFinanciadora> instituicoesFinanciadoras = new ArrayList<InstituicaoFinanciadora>();
	private EditText editTextNomeProgramaInstitucional;
	private EditText editTextSigla;
	private EditText editTextOrcamento;
	private Spinner spinnerInstituicaoFinanciadora;
	private Button buttonCadastrar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_programa_institucional);

		editTextNomeProgramaInstitucional = (EditText) findViewById(R.id.editTextNomeProgramaInstitucional);
		editTextSigla = (EditText) findViewById(R.id.editTextSigla);
		editTextOrcamento = (EditText) findViewById(R.id.editTextOrcamento);
		spinnerInstituicaoFinanciadora = (Spinner) findViewById(R.id.spinnerInstituicaoFinanciadora);
		buttonCadastrar = (Button) findViewById(R.id.buttonCadastrarProgramaInstitucional);

		intent = getIntent();
		params = intent.getExtras();

		// Adicionar Mascara aos campos Orçamento
		editTextOrcamento.addTextChangedListener(Mascara
				.money(editTextOrcamento));

		PreencherSpinnerInstituicaoFinanciadoraAsyncTask preencherSpinner = new PreencherSpinnerInstituicaoFinanciadoraAsyncTask(
				this);

		try {
			instituicoesFinanciadoras = preencherSpinner.execute().get();
		} catch (InterruptedException e) {
			Toast toast = Toast.makeText(this, "ERRO: Conexão Encerrada",
					Toast.LENGTH_LONG);
		} catch (ExecutionException e) {
			Toast toast = Toast.makeText(this, "ERRO: Conexão Encerrada",
					Toast.LENGTH_LONG);
		}

		List<String> nomeInstituicao = new ArrayList<String>();

		for (int i = 0; i < instituicoesFinanciadoras.size(); i++) {
			nomeInstituicao.add(instituicoesFinanciadoras.get(i).getSigla());
		}

		ativarSpinner(spinnerInstituicaoFinanciadora, nomeInstituicao);

		// Action do Button Cadastrar Instituição Financiadora

		buttonCadastrar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (Validação.validaCampo(editTextNomeProgramaInstitucional))
			if (Validação.validaCampo(editTextSigla))
				if (Validação.validaCampo(editTextOrcamento)) {
					programaInstitucional
							.setNomeProgramaInstitucional((editTextNomeProgramaInstitucional)
									.getText().toString());
					programaInstitucional.setSigla((editTextSigla).getText()
							.toString());
					programaInstitucional.setOrcamento(Double
							.parseDouble(Mascara.unmask((editTextOrcamento)
									.getText().toString())));

					for (int i = 0; i < instituicoesFinanciadoras.size(); i++) {
						if (instituicoesFinanciadoras
								.get(i)
								.getSigla()
								.equals(spinnerInstituicaoFinanciadora
										.getSelectedItem()))
							programaInstitucional
									.setInstituicaoFinanciadora(instituicoesFinanciadoras
											.get(i));
					}

					Gestor gestor = new Gestor();
					gestor.setPessoaId(params.getInt("Gestor"));
					programaInstitucional.setGestor(gestor);

					ParserAsyncTask<ProgramaInstitucional> parser = new ParserAsyncTask<ProgramaInstitucional>(
							programaInstitucional, this,
							Constantes.CADASTRAR_PROGRAMA_INSTITUCIONAL,
							params.getInt("Gestor"));
					parser.execute();
				}
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
}
