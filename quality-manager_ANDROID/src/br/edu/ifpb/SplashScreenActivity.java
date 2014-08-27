package br.edu.ifpb;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends Activity implements Runnable
{

	private static int SPLASH_TIME_OUT = 2000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		Handler handler = new Handler();
        handler.postDelayed(this, SPLASH_TIME_OUT);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		VerificaConexaoAsyncTask verifica_conexao = new VerificaConexaoAsyncTask(this);
		verifica_conexao.execute();
	}
}
