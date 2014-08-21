package br.edu.ifpb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MenuAplicacaoActivity extends Activity implements OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		ImageButton gestor = (ImageButton) findViewById(R.id.imageButton1);
		ImageButton docente = (ImageButton) findViewById(R.id.imageButton2);
		ImageButton discente = (ImageButton) findViewById(R.id.imageButton3);
		
		gestor.setOnClickListener(this);
		docente.setOnClickListener(this);
		discente.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) 
	{
		Intent opção;
		Bundle params = new Bundle();
		
		switch(v.getId())
		{
			case R.id.imageButton1:
				opção = new Intent(this, LoginGestorActivity.class);
				startActivity(opção);
				break;
			case R.id.imageButton2:
				opção = new Intent(this, LoginActivity.class);
				params.putInt("opção", 2);
				opção.putExtras(params);
				startActivity(opção);
				break;
			case R.id.imageButton3:
				opção = new Intent(this, LoginActivity.class);
				params.putInt("opção", 3);
				opção.putExtras(params);
				startActivity(opção);
				break;
		}
	}

}
