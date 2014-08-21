package br.edu.ifpb;

import entidades.Docente;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ImprimirInformacaoActivityDocente extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imprimir_informacao_docente);
		
		Intent intent = getIntent();
		Docente docente = (Docente) intent.getSerializableExtra("Docente");
		
		TextView nome = (TextView) findViewById(R.id.Nome);
		nome.setText(docente.getNomePessoa());
		
		TextView cpf = (TextView) findViewById(R.id.cpf);
		cpf.setText(docente.getCpf());
		
		TextView matricula = (TextView) findViewById(R.id.matricula);
		matricula.setText(docente.getMatricula());
		
		TextView cargo = (TextView) findViewById(R.id.cargo);
		cargo.setText(docente.getCargo());
		
		TextView titulacao = (TextView) findViewById(R.id.titulacao);
		titulacao.setText(docente.getTitulacao());
		
		TextView endereco = (TextView) findViewById(R.id.endereco);
		endereco.setText(docente.getEndereco());
		
		TextView cep = (TextView) findViewById(R.id.cep);
		cep.setText(docente.getCep());
		
		TextView telefone = (TextView) findViewById(R.id.telefone);
		telefone.setText(docente.getTelefone());
		
		TextView email = (TextView) findViewById(R.id.email);
		email.setText(docente.getEmail());
		
		TextView senha = (TextView) findViewById(R.id.senha);
		senha.setText(docente.getSenha());
	}
	
}
