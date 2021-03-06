package br.edu.ifpb.qmanager.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator implements QManagerValidator {

	private Pattern pattern;
	private Pattern patternPassword;
	private Matcher matcher;

	private static final String STRING_PATTERN = "[0-9a-zA-ZáàâãéèêíïóôõöúüçñÁÀÂÃÉÈÍÏÓÔÕÖÚÜÇÑ ,-]*";

	// Verifica se há, ao menos, um número ou caractere especial, uma letra
	// minúscula, uma letra maiúscula. O tamanho deve está entre 8 e 25
	// caracteres.
	private static final String PASSWORD_PATTERN = "(?=.*[0-9@#$%^&+=])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,25}";

	public StringValidator() {
		pattern = Pattern.compile(STRING_PATTERN);
		patternPassword = Pattern.compile(PASSWORD_PATTERN);
	}

	@Override
	public boolean validate(final String value) {
		matcher = pattern.matcher(value.trim());
		return matcher.matches();
	}

	public boolean validate(final String value, int tamanho) {
		return (validate(value) && value.length() <= tamanho);
	}

	public boolean validate(final String value, int tamanhoMenor,
			int tamanhoMaior) {
		return (validate(value) && (value.length() >= tamanhoMenor || value
				.length() <= tamanhoMaior));
	}

	public boolean validatePassword(final String senha) {
		matcher = patternPassword.matcher(senha);
		return matcher.matches();
	}

}
