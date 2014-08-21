package br.edu.ifpb;

import java.util.ArrayList;
import java.util.List;

import entidades.Discente;

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

public class FormularioDiscenteActivity extends Activity implements
		OnClickListener {

	private Discente discente = new Discente();
	private Spinner curso;
	private List<String> itens_curso = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_discente);

		// Opções de Curso
		itens_curso.add("Selecione...");
		itens_curso.add("Informática");
		itens_curso.add("MSI");
		itens_curso.add("Mineração");
		itens_curso.add("Petróleo e Gás");

		curso = (Spinner) findViewById(R.id.curso);

		this.ativarSpinner(curso, itens_curso);

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
	public void onClick(View arg0) {

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
			if (!(curso.getSelectedItem().toString().equals("Selecione..."))
					|| (curso.getSelectedItemPosition() != 0)) {
				discente.setNomePessoa(((EditText) findViewById(R.id.nome_completo))
						.getText().toString());
				discente.setCpf(((EditText) findViewById(R.id.cpf)).getText()
						.toString());
				discente.setMatricula(((EditText) findViewById(R.id.matricula))
						.getText().toString());
				discente.setEndereco(((EditText) findViewById(R.id.endereco))
						.getText().toString());
				discente.setCep(((EditText) findViewById(R.id.cep)).getText()
						.toString());
				discente.setTelefone(((EditText) findViewById(R.id.telefone))
						.getText().toString());
				discente.setEmail(((EditText) findViewById(R.id.email))
						.getText().toString());
				discente.setSenha(((EditText) findViewById(R.id.senha))
						.getText().toString());
				discente.setCurso(curso.getSelectedItem().toString());
				Intent intent = new Intent(this,
						ImprimirInformacaoActivityDiscente.class);
				intent.putExtra("Discente", discente);
				startActivity(intent);
				finish();
			} else
				Toast.makeText(getApplicationContext(),
						"Por favor, preencha o campo Curso", Toast.LENGTH_LONG)
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
