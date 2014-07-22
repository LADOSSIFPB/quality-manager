package br.edu.ifpb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login2);
		
		Button confirmar = (Button) findViewById(R.id.confirmar);
		confirmar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) 
	{
		EditText usuarioTextView = (EditText) findViewById(R.id.nome);
		String usuario = usuarioTextView.getText().toString();
		
		EditText senhaTextView = (EditText) findViewById(R.id.senha);
		String senha = senhaTextView.getText().toString();
		
		Intent intent = getIntent();
		Bundle params = intent.getExtras();
		
		if(this.testarCadastro(params.getInt("opção"), usuario, senha))
		{
			if(params.getInt("opção")==1)
				intent = new Intent(this,GestorActivity.class);
			else
			if(params.getInt("opção")==2)
				intent = new Intent(this,DocenteActivity.class);
			else
				intent = new Intent(this,DiscenteActivity.class);
			startActivity(intent);
		}
		else
		{
			Toast toast = Toast.makeText(this, "Cadastro não existente!", Toast.LENGTH_SHORT);
			toast.show();			
		}
	}
	
	public boolean testarCadastro(int i, String usuario, String senha)
	{
		switch(i)
		{
			case 1:
				if ((usuario.toLowerCase().equals("marcia")) && (senha.equals("12345")))
					return true;
				break;
			case 2:
				if ((usuario.toLowerCase().equals("rhavy")) && (senha.equals("12345")))
					return true;
				break;
			case 3:
				if ((usuario.toLowerCase().equals("emanuel")) && (senha.equals("12345")))
					return true;
				break;
		}
		return false;
	}
}
