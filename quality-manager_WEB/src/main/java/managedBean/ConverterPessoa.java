package managedBean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.Pessoa;

@FacesConverter("converterMembro")
public class ConverterPessoa extends GenericBean implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent componente,
			String value) {

		Response response = service.consultarPessoa(Integer.parseInt(value));
		Pessoa pessoa = response.readEntity(new GenericType<Pessoa>() {
		});

		return pessoa;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent componente,
			Object value) {

		Pessoa membroProjeto = (Pessoa) value;
		return String.valueOf(membroProjeto.getPessoaId());

	}

}
