package br.edu.ifpb.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import br.edu.ifpb.util.Validação;

public class CadastrarInstituicaoFinanciadoraActivity extends Activity
		implements OnClickListener {

	private InstituicaoFinanciadora instituicaoFinanciadora = new InstituicaoFinanciadora();
	private Intent intent;
	private Bundle params;
	private Servidor servidor;
	private EditText editTextCNPJ;
	private EditText editTextNomeInstituicaoFinanciadora;
	private EditText editTextSigla;
	private EditText editTextOrcamento;
	private Button buttonCadastrar;
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

		// Action do Button Cadastrar Instituição Financiadora
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
			instituicaoFinanciadora.setOrcamento(Double.parseDouble(Mascara
					.unmask((editTextOrcamento).getText().toString())));
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

	public void findViews() {
		servidor = new Servidor();
		servidor.setPessoaId(params.getInt("Gestor"));
		editTextCNPJ = (EditText) findViewById(R.id.editTextCNPJ);
		editTextNomeInstituicaoFinanciadora = (EditText) findViewById(R.id.editTextNomeInstuicaoFinanciadora);
		editTextSigla = (EditText) findViewById(R.id.editTextSigla);
		editTextOrcamento = (EditText) findViewById(R.id.editTextOrcamento);
		buttonCadastrar = (Button) findViewById(R.id.buttonCadastrarInstituicaoFinanciadora);
	}

	public void addMascaras() {
		// Adicionar Mascara aos campos CNPJ
		editTextCNPJ.addTextChangedListener(Mascara.insert(
				"##.###.###/####-##", editTextCNPJ));

		// Adicionar Mascara aos campos Orçamento
		editTextOrcamento.addTextChangedListener(Mascara
				.money(editTextOrcamento));
	}

	public boolean validateAll() {
		if (Validação.validarCampo(editTextCNPJ))
			if (Validação.validarCampo(editTextNomeInstituicaoFinanciadora))
				if (Validação.validarCampo(editTextSigla))
					if (Validação.validarCampo(editTextOrcamento))
						return true;
		return false;
	}
}
