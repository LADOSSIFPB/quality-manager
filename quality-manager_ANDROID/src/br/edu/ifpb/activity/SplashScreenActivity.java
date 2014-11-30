package br.edu.ifpb.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import br.edu.ifpb.R;
import br.edu.ifpb.conection.VerificarConexaoAsyncTask;

public class SplashScreenActivity extends Activity implements Runnable {

	private static int SPLASH_TIME_OUT = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		Handler handler = new Handler();
		handler.postDelayed(this, SPLASH_TIME_OUT);
	}

	@Override
	public void run() {
		VerificarConexaoAsyncTask verifica_conexao = new VerificarConexaoAsyncTask(
				this);
		verifica_conexao.execute();
	}
}