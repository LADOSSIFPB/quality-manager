package br.edu.ifpb;

import java.util.ArrayList;
import java.util.List;

import entidades.Docente;

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

public class FormularioDocenteActivity extends Activity implements OnClickListener {

	private Docente docente = new Docente();
	private Spinner cargo;
	private Spinner titulacao;
	private List<String> itens_cargo = new ArrayList<String>();
	private List<String> itens_titulacao = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_docente);

		// Opções de Cargo
		itens_cargo.add("Selecione...");
		itens_cargo.add("Professor");
		itens_cargo.add("Coordenador");

		cargo = (Spinner) findViewById(R.id.cargo);

		this.ativarSpinner(cargo, itens_cargo);

		// Opções de Titulação
		itens_titulacao.add("Selecione...");
		itens_titulacao.add("Graduado");
		itens_titulacao.add("Mestre");
		itens_titulacao.add("Doutor");

		titulacao = (Spinner) findViewById(R.id.titulacao);

		this.ativarSpinner(titulacao, itens_titulacao);

		// Adicionar Mascara aos campos CPF, CEP e Telefone
		EditText cpf = (EditText) findViewById(R.id.cpf);
		cpf.addTextChangedListener(Mascara.insert("###.###.###-##", cpf));

		EditText cep = (EditText) findViewById(R.id.cep);
		cep.addTextChangedListener(Mascara.insert("##.###-###", cep));

		EditText telefone = (EditText) findViewById(R.id.telefone);
		telefone.addTextChangedListener(Mascara.insert("(##)####-####",
				telefone));

		// Ação do Button
		Button criar_conta = (Button) findViewById(R.id.criar_conta);
		criar_conta.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		if (!Validação.verificaExistenciaErro(
				(EditText) findViewById(R.id.nome_completo),
				(EditText) findViewById(R.id.cpf),
				(EditText) findViewById(R.id.matricula),
				(EditText) findViewById(R.id.endereco),
				(EditText) findViewById(R.id.cep),
				(EditText) findViewById(R.id.telefone),
				(EditText) findViewById(R.id.email),
				(EditText) findViewById(R.id.senha),
				(EditText) findViewById(R.id.confirma_senha)))
			if (!(cargo.getSelectedItem().toString().equals("Selecione..."))
					|| (cargo.getSelectedItemPosition() != 0))
				if (!(titulacao.getSelectedItem().toString()
						.equals("Selecione..."))
						|| (titulacao.getSelectedItemPosition() != 0)) {
					docente.setNomePessoa(((EditText) findViewById(R.id.nome_completo))
							.getText().toString());
					docente.setCpf(((EditText) findViewById(R.id.cpf))
							.getText().toString());
					docente.setMatricula(((EditText) findViewById(R.id.matricula))
							.getText().toString());
					docente.setEndereco(((EditText) findViewById(R.id.endereco))
							.getText().toString());
					docente.setCep(((EditText) findViewById(R.id.cep))
							.getText().toString());
					docente.setTelefone(((EditText) findViewById(R.id.telefone))
							.getText().toString());
					docente.setEmail(((EditText) findViewById(R.id.email))
							.getText().toString());
					docente.setSenha(((EditText) findViewById(R.id.senha))
							.getText().toString());
					docente.setCargo(cargo.getSelectedItem().toString());
					docente.setTitulacao(titulacao.getSelectedItem().toString());
					Intent intent = new Intent(this,
							ImprimirInformacaoActivityDocente.class);
					intent.putExtra("Docente", docente);
					startActivity(intent);
					finish();
				} else
					Toast.makeText(getApplicationContext(),
							"Por favor, preencha o campo Titulação",
							Toast.LENGTH_LONG).show();
			else
				Toast.makeText(getApplicationContext(),
						"Por favor, preencha o campo Cargo", Toast.LENGTH_LONG)
						.show();

	}

	public void ativarSpinner(Spinner generico, List<String> itens_genericos) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itens_genericos);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		generico.setAdapter(adapter);
		generico.setSelection(0);

		generico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

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
