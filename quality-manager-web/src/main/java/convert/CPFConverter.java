package convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("convertCPF")
public class CPFConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		return value;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		String cpf = value.toString();

		String mascara = "###.###.###-##";

		String cpfMascara = GenericConverter.format(mascara, cpf);

		return cpfMascara;

	}

}
