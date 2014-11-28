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
import br.edu.ifpb.conection.PreencherSpinnerProgramaInstitucionalAsyncTask;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Gestor;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.util.Constantes;
import br.edu.ifpb.util.Mascara;
import br.edu.ifpb.util.Validação;

public class CadastrarEditalActivity extends Activity implements
		OnClickListener {

	private Intent intent;
	private Bundle params;
	private Edital edital = new Edital();
	private List<ProgramaInstitucional> programasInstitucionais = new ArrayList<ProgramaInstitucional>();
	private Spinner spinnerProgramaInstitucional;
	private EditText editTextNumeroEdital;
	private EditText editTextAno;
	private EditText editTextInicioInscricoes;
	private EditText editTextFimInscricoes;
	private EditText editTextPrazoRelatorioParcial;
	private EditText editTextPrazoRelatorioFinal;
	private EditText editTextNumeroVagas;
	private EditText editTextBolsaOrientador;
	private EditText editTextBolsaDiscente;
	private Spinner spinnerTipoEdital;
	private Button buttonCadastrarEdital;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_edital);

		editTextInicioInscricoes = (EditText) findViewById(R.id.editTextInicioInscricoes);
		editTextFimInscricoes = (EditText) findViewById(R.id.editTextFimInscricoes);
		editTextPrazoRelatorioParcial = (EditText) findViewById(R.id.editTextPrazoRelatorioParcial);
		editTextPrazoRelatorioFinal = (EditText) findViewById(R.id.editTextPrazoRelatorioFinal);

		editTextNumeroEdital = (EditText) findViewById(R.id.editTextNumeroEdital);
		editTextAno = (EditText) findViewById(R.id.editTextAno);
		editTextNumeroVagas = (EditText) findViewById(R.id.editTextNumeroVagas);
		editTextBolsaOrientador = (EditText) findViewById(R.id.editTextBolsaOrientador);
		editTextBolsaDiscente = (EditText) findViewById(R.id.editTextBolsaDiscente);
		spinnerProgramaInstitucional = (Spinner) findViewById(R.id.spinnerProgramaInstitucional);
		spinnerTipoEdital = (Spinner) findViewById(R.id.spinnerTipoEdital);
		buttonCadastrarEdital = (Button) findViewById(R.id.buttonCadastrarEdital);

		intent = getIntent();
		params = intent.getExtras();

		// Adicionar Mascara aos campos Inicio Inscrições
		editTextInicioInscricoes.addTextChangedListener(Mascara.insert(
				"####-##-##", editTextInicioInscricoes));

		// Adicionar Mascara aos campos Fim Inscrições
		editTextFimInscricoes.addTextChangedListener(Mascara.insert(
				"####-##-##", editTextFimInscricoes));

		// Adicionar Mascara aos campos Prazo Relatorio Parcial
		editTextPrazoRelatorioParcial.addTextChangedListener(Mascara.insert(
				"####-##-##", editTextPrazoRelatorioParcial));

		// Adicionar Mascara aos campos Prazo Relatorio Final
		editTextPrazoRelatorioFinal.addTextChangedListener(Mascara.insert(
				"####-##-##", editTextPrazoRelatorioFinal));

		// Adicionar Mascara aos campos Bolsa Orientador
		editTextBolsaOrientador.addTextChangedListener(Mascara
				.money(editTextBolsaOrientador));

		// Adicionar Mascara aos campos Bolsa Discente
		editTextBolsaDiscente.addTextChangedListener(Mascara
				.money(editTextBolsaDiscente));

		PreencherSpinnerProgramaInstitucionalAsyncTask preencherSpinner = new PreencherSpinnerProgramaInstitucionalAsyncTask(
				this);

		try {
			programasInstitucionais = preencherSpinner.execute().get();
		} catch (InterruptedException e) {
			Toast toast = Toast.makeText(this, "ERRO: Conexão Encerrada",
					Toast.LENGTH_LONG);
		} catch (ExecutionException e) {
			Toast toast = Toast.makeText(this, "ERRO: Conexão Encerrada",
					Toast.LENGTH_LONG);
		}

		List<String> nomeProgramaInstitucional = new ArrayList<String>();

		for (int i = 0; i < programasInstitucionais.size(); i++) {
			nomeProgramaInstitucional.add(programasInstitucionais.get(i)
					.getSigla());
		}

		ativarSpinner(spinnerProgramaInstitucional, nomeProgramaInstitucional);

		List<String> tipoEdital = new ArrayList<String>();
		tipoEdital.add("Pesquisa");
		tipoEdital.add("Extensão");

		ativarSpinner(spinnerTipoEdital, tipoEdital);

		// Action do Button Cadastrar Instituição Financiadora

		buttonCadastrarEdital.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (Validação.validaCampo(editTextNumeroEdital))
			if (Validação.validaCampo(editTextAno))
				if (Validação.validaCampo(editTextInicioInscricoes))
					if (Validação.validaCampo(editTextFimInscricoes))
						if (Validação
								.validaCampo(editTextPrazoRelatorioParcial))
							if (Validação
									.validaCampo(editTextPrazoRelatorioFinal))
								if (Validação.validaCampo(editTextNumeroVagas))
									if (Validação
											.validaCampo(editTextBolsaOrientador))
										if (Validação
												.validaCampo(editTextBolsaDiscente)) {
											edital.setNumero(Integer
													.parseInt((editTextNumeroEdital)
															.getText()
															.toString()));
											edital.setAno(Integer
													.parseInt((editTextAno)
															.getText()
															.toString()));
											edital.setInicioInscricoes((editTextInicioInscricoes)
															.getText()
															.toString());
											edital.setFimInscricoes((editTextFimInscricoes)
															.getText()
															.toString());
											edital.setRelatorioParcial((editTextPrazoRelatorioParcial)
															.getText()
															.toString());
											edital.setRelatorioFinal((editTextPrazoRelatorioFinal)
															.getText()
															.toString());
											edital.setNumero(Integer
													.parseInt((editTextNumeroVagas)
															.getText()
															.toString()));
											edital.setBolsaDocente(Double
													.parseDouble(Mascara.unmask((editTextBolsaOrientador)
															.getText()
															.toString())));
											edital.setBolsaDiscente(Double
													.parseDouble(Mascara.unmask((editTextBolsaDiscente)
															.getText()
															.toString())));

											for (int i = 0; i < programasInstitucionais
													.size(); i++) {
												if (programasInstitucionais
														.get(i)
														.getSigla()
														.equals(spinnerProgramaInstitucional
																.getSelectedItem()))
													edital.setProgramaInstitucional(programasInstitucionais
															.get(i));
											}

											edital.setTipoEdital((spinnerTipoEdital
													.getSelectedItem())
													.toString().charAt(0));

											Gestor gestor = new Gestor();
											gestor.setPessoaId(params
													.getInt("Gestor"));
											edital.setGestor(gestor);

											ParserAsyncTask<Edital> parser = new ParserAsyncTask<Edital>(
													edital,
													this,
													Constantes.CADASTRAR_EDITAL,
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
