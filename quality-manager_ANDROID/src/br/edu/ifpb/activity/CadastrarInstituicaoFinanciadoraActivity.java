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
import br.edu.ifpb.qmanager.entidade.Coordenador;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.util.Constantes;
import br.edu.ifpb.util.Mascara;
import br.edu.ifpb.util.Validação;

public class CadastrarInstituicaoFinanciadoraActivity extends Activity
		implements OnClickListener {

	private InstituicaoFinanciadora instituicaoFinanciadora = new InstituicaoFinanciadora();
	private Intent intent;
	private Bundle params;
	private EditText editTextCNPJ;
	private EditText editTextNomeInstituicaoFinanciadora;
	private EditText editTextSigla;
	private EditText editTextOrcamento;
	private Button buttonCadastrar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_instituicao_financiadora);

		editTextCNPJ = (EditText) findViewById(R.id.editTextCNPJ);
		editTextNomeInstituicaoFinanciadora = (EditText) findViewById(R.id.editTextNomeInstuicaoFinanciadora);
		editTextSigla = (EditText) findViewById(R.id.editTextSigla);
		editTextOrcamento = (EditText) findViewById(R.id.editTextOrcamento);
		buttonCadastrar = (Button) findViewById(R.id.buttonCadastrarInstituicaoFinanciadora);
		
		intent = getIntent();
		params = intent.getExtras();
		
		// Adicionar Mascara aos campos CNPJ
		editTextCNPJ.addTextChangedListener(Mascara.insert(
				"##.###.###/####-##", editTextCNPJ));

		// Adicionar Mascara aos campos Orçamento
		editTextOrcamento.addTextChangedListener(Mascara
				.money(editTextOrcamento));

		// Action do Button Cadastrar Instituição Financiadora
		buttonCadastrar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (Validação.validaCampo(editTextCNPJ))
			if (Validação.validaCampo(editTextNomeInstituicaoFinanciadora))
				if (Validação.validaCampo(editTextSigla))
					if (Validação.validaCampo(editTextOrcamento)) {
						instituicaoFinanciadora.setCnpj(Mascara
								.unmask((editTextCNPJ).getText().toString()));
						instituicaoFinanciadora
								.setNomeInstituicaoFinanciadora((editTextNomeInstituicaoFinanciadora)
										.getText().toString());
						instituicaoFinanciadora.setSigla((editTextSigla)
								.getText().toString());
						instituicaoFinanciadora.setOrcamento(Double
								.parseDouble(Mascara.unmask((editTextOrcamento)
										.getText().toString())));
						Coordenador coordenador = new Coordenador();
						coordenador.setPessoaId(params.getInt("Coordenador"));
						instituicaoFinanciadora
								.setCoordenador(coordenador);
						ParserAsyncTask<InstituicaoFinanciadora> parser = new ParserAsyncTask<InstituicaoFinanciadora>(
								instituicaoFinanciadora, this,
								Constantes.CADASTRAR_INSTITUICAO_FINANCIADORA, params.getInt("Coordenador"));
						parser.execute();
					}
	}
}
