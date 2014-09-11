package br.edu.ifpb.qmanager.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator implements QManagerValidator{


	private Pattern pattern;
	private Matcher matcher;

	private static final String STRING_PATTERN = "[a-zA-Z]*";

	public StringValidator() {
		pattern = Pattern.compile(STRING_PATTERN);
	}
	
	@Override
	public boolean validate(final String value) {
		matcher = pattern.matcher(value.trim());
		return matcher.matches();
	}
	
	public boolean validate(final String value, int tamanho) {
		return (validate(value) 
				&& value.length() <= tamanho);
	}
	
	public boolean validate(final String value, int tamanhoMenor, 
			int tamanhoMaior) {
		return (validate(value) 
				&& (value.length() >= tamanhoMenor 
					|| value.length() <= tamanhoMaior));
	}
}
