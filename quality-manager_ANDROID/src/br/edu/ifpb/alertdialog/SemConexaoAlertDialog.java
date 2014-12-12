package br.edu.ifpb.alertdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;
import br.edu.ifpb.activity.SplashScreenActivity;
import br.edu.ifpb.util.Constantes;

public class SemConexaoAlertDialog {
	
	private Activity activity;
	
	public SemConexaoAlertDialog(Activity activity) {
		this.activity = activity;
	}
	public void showAlertDialog() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				this.activity);

		// Setting Dialog Title
		alertDialog.setTitle("Problema na conexão");

		// Setting Dialog Message
		alertDialog.setMessage("Deseja tentar novamente?");

		// Setting Icon to Dialog
		// alertDialog.setIcon(android.R.drawable.delete);

		// Setting Positive "Yes" Button
		alertDialog.setPositiveButton("Sim",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(activity,
								SplashScreenActivity.class);
						activity.startActivity(intent);
						activity.finish();
					}
				});

		// Setting Negative "NO" Button
		alertDialog.setNegativeButton("Não",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to invoke NO event
						Toast.makeText(activity, Constantes.ERROR_INTERNET_OFF,
								Toast.LENGTH_SHORT).show();
						dialog.cancel();

						activity.finish();
					}
				});

		// Showing Alert Message
		alertDialog.show();
	}
}
