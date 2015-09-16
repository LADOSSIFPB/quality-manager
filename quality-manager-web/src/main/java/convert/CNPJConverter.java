package convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("convertCNPJ")
public class CNPJConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		String cnpj = value.toString();

		String mascara = "##.###-###/####-##";

		String cnpjMascara = GenericConverter.format(mascara, cnpj);

		return cnpjMascara;
	}

}
