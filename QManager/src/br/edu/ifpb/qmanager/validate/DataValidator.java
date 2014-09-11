package br.edu.ifpb.qmanager.validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataValidator implements QManagerValidator {

	public static final String FORMATO_DATA = "dd/MM/yyyy";
	
	public DataValidator() {}
	
	@Override
	public boolean validate(final String value) {

		boolean valido = true;

		try {
			SimpleDateFormat format = new SimpleDateFormat(FORMATO_DATA);
			format.setLenient(false);
			Date data = format.parse(value);			
		} catch (ParseException e) {
			valido = false;
		}

		return valido;
	}

}
