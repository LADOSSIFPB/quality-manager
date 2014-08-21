package br.edu.ifpb;

import android.widget.EditText;

public class Validação {

	public static boolean validaCampo(EditText campo) {
		if ((campo.getText().toString().trim().equals(""))
				|| (campo.getText().toString().equals(null))) {
			campo.setError("Por favor, preencha esse campo.");
			campo.setFocusable(true);
			campo.requestFocus();
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
	
	public static boolean verificaExistenciaErro(EditText nome_completo,
			EditText cpf, EditText matricula, EditText endereco, EditText cep,
			EditText telefone, EditText email, EditText senha,
			EditText confirma_senha) {
		if ((validaCampo(nome_completo)) && (validaCampo(cpf))
				&& (validaCampo(matricula)) && (validaCampo(endereco))
				&& (validaCampo(cep)) && (validaCampo(telefone))
				&& (validaCampo(email)) && (validaCampo(senha))
				&& (validaCampo(confirma_senha)) && (validaEmail(email))
				&& (validaSenha(senha, confirma_senha)))
			return false;
		return true;
	}

}
