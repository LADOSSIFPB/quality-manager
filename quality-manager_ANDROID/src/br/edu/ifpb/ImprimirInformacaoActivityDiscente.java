package br.edu.ifpb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import entidades.Discente;

public class ImprimirInformacaoActivityDiscente extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imprimir_informacao_discente);
		
		Intent intent = getIntent();
		Discente discente = (Discente) intent.getSerializableExtra("Discente");
		
		TextView nome = (TextView) findViewById(R.id.Nome);
		nome.setText(discente.getNomePessoa());
		
		TextView cpf = (TextView) findViewById(R.id.cpf);
		cpf.setText(discente.getCpf());
		
		TextView matricula = (TextView) findViewById(R.id.matricula);
		matricula.setText(discente.getMatricula());
		
		TextView curso = (TextView) findViewById(R.id.curso);
		curso.setText(discente.getCurso());
		
		TextView endereco = (TextView) findViewById(R.id.endereco);
		endereco.setText(discente.getEndereco());
		
		TextView cep = (TextView) findViewById(R.id.cep);
		cep.setText(discente.getCep());
		
		TextView telefone = (TextView) findViewById(R.id.telefone);
		telefone.setText(discente.getTelefone());
		
		TextView email = (TextView) findViewById(R.id.email);
		email.setText(discente.getEmail());
		
		TextView senha = (TextView) findViewById(R.id.senha);
		senha.setText(discente.getSenha());
	}
	
}