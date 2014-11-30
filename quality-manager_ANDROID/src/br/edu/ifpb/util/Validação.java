package br.edu.ifpb.util;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class Validação {

	public static boolean validarCampo(EditText campo) {
		if ((campo.getText().toString().trim().equals(""))
				|| (campo.getText().toString().equals(null))) {
			campo.setError(Constantes.MSG_PREENCHER_CAMPO);
			campo.setFocusable(true);
			campo.requestFocus();
			return false;
		}
		return true;
	}

	public static boolean validarSpinner(String campo, Context activity) {
		if ((campo).equals(Constantes.MSG_INICIO_SPINNER)) {
			Toast toast = Toast.makeText(activity,
					Constantes.MSG_PREENCHER_INSTITUICAO_FINANCIADORA,
					Toast.LENGTH_LONG);
			toast.show();
			return false;
		}
		return true;
	}

	public static boolean validaSenha(EditText senha1, EditText senha2) {
		if (!(senha1.getText().toString().equals(senha2.getText().toString()))) {
			senha2.setError("As senhas não correspondem");
			senha2.setFocusable(true);
			senha2.requestFocus();
			return false;
		}
		return true;
	}

	public static boolean validaEmail(EditText email) {
		if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
				email.getText().toString()).matches()) {
			email.setError("E-mail inválido");
			email.setFocusable(true);
			email.requestFocus();
			return false;
		}
		return true;
	}

}
