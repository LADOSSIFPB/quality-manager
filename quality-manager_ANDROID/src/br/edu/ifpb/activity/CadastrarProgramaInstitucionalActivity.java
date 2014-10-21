package br.edu.ifpb.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import br.edu.ifpb.R;
import br.edu.ifpb.conection.ParserProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.util.Mascara;
import br.edu.ifpb.util.PreencherSpinner;
import br.edu.ifpb.util.Validação;

public class CadastrarProgramaInstitucionalActivity extends Activity implements
		OnClickListener {

	private ProgramaInstitucional programaInstitucional = new ProgramaInstitucional();
	private List<InstituicaoFinanciadora> instituicoesFinanciadoras = new ArrayList<InstituicaoFinanciadora>();
	private Spinner instituicao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_programa_institucional);

		// Adicionar Mascara aos campos Orçamento
		EditText editTextOrcamento = (EditText) findViewById(R.id.editTextOrcamento);
		editTextOrcamento.addTextChangedListener(Mascara
				.money(editTextOrcamento));

		instituicao = (Spinner) findViewById(R.id.spinnerInstituicaoFinanciadora);
		PreencherSpinner preencherSpinner = new PreencherSpinner(this);
	
		try {
			instituicoesFinanciadoras = preencherSpinner.execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ativarSpinner(instituicao, instituicoesFinanciadoras);

		// Action do Button Cadastrar Instituição Financiadora
		Button buttonCadastrar = (Button) findViewById(R.id.buttonCadastrarProgramaInstitucional);
		buttonCadastrar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (Validação
				.validaCampo((EditText) findViewById(R.id.editTextNomeProgramaInstitucional)))
			if (Validação
					.validaCampo((EditText) findViewById(R.id.editTextSigla)))
				if (Validação
						.validaCampo((EditText) findViewById(R.id.editTextOrcamento))) {
					programaInstitucional
							.setNomeProgramaInstitucional(Mascara
									.unmask(((EditText) findViewById(R.id.editTextNomeProgramaInstitucional))
											.getText().toString()));
					programaInstitucional
							.setSigla(Mascara
									.unmask(((EditText) findViewById(R.id.editTextSigla))
											.getText().toString()));
					programaInstitucional
							.setOrcamento(Double.parseDouble(Mascara
									.unmask(((EditText) findViewById(R.id.editTextOrcamento))
											.getText().toString())));
					programaInstitucional
							.setInstituicaoFinanciadora((InstituicaoFinanciadora) instituicao
									.getSelectedItem());
					ParserProgramaInstitucional parser = new ParserProgramaInstitucional(
							programaInstitucional, this);
					parser.execute();
				}
	}

	public void ativarSpinner(Spinner generic,
			List<InstituicaoFinanciadora> genericItems) {
		ArrayAdapter<InstituicaoFinanciadora> adapter = new ArrayAdapter<InstituicaoFinanciadora>(
				this, android.R.layout.simple_spinner_item, genericItems);
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
