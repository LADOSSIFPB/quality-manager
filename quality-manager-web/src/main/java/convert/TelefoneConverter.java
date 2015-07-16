package convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("convertTelefone")
public class TelefoneConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		String telefone = value.toString();
		String mascara;

		if (telefone.length() == 9)
			mascara = "(##)#####-####";
		else
			mascara = "(##)####-####";
		
		String telefoneMascara = GenericConverter.format(mascara, telefone);

		return telefoneMascara;
	}

}
