package convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import managedBean.GenericBean;
import br.edu.ifpb.qmanager.entidade.Servidor;

@FacesConverter("converterServidor")
public class ConverterServidor extends GenericBean implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent componente,
			String value) {

		if (value != "0" && value != null) {

			Response response = service
					.consultarServidor(Integer.parseInt(value));
			Servidor servidor = response.readEntity(new GenericType<Servidor>() {
			});

			return servidor;
		}

		return null;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent componente,
			Object value) {

		Servidor servidor = (Servidor) value;
		
		if (servidor.getPessoaId() != 0) {

			return String.valueOf(servidor.getPessoaId());

		}
		
		return null;
	}

}