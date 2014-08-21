package br.edu.ifpb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginGestorActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login2);
		
		TextView cadastrar = (TextView) findViewById(R.id.cadastrar);
		cadastrar.setVisibility(View.INVISIBLE);
		
		Button confirmar = (Button) findViewById(R.id.confirmar);
		confirmar.setOnClickListener(this); 

}

	@Override
	public void onClick(View arg0) {
		
		EditText usuarioTextView = (EditText) findViewById(R.id.nome);
		String usuario = usuarioTextView.getText().toString();
		
		EditText senhaTextView = (EditText) findViewById(R.id.senha);
		String senha = senhaTextView.getText().toString();
		
		Intent intent;
		
		if((usuario.toLowerCase().equals("marcia")) && (senha.equals("12345")))
		{	
			intent = new Intent(this,GestorActivity.class);
			startActivity(intent);
		}
		else
		{
			Toast toast = Toast.makeText(this, "Cadastro não existente!", Toast.LENGTH_SHORT);
			toast.show();			
		}	
	}
}