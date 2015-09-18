package convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import managedBean.GenericBean;
import br.edu.ifpb.qmanager.entidade.Pessoa;

@FacesConverter("converterPessoa")
public class ConverterPessoa extends GenericBean implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent componente,
			String value) {

		if (value != "0" && value != null) {

			Response response = service
					.consultarPessoa(Integer.parseInt(value));
			Pessoa pessoa = response.readEntity(new GenericType<Pessoa>() {
			});

			return pessoa;
		}

		return null;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent componente,
			Object value) {

		Pessoa membroProjeto = (Pessoa) value;
		
		if (membroProjeto.getPessoaId() != 0) {

			return String.valueOf(membroProjeto.getPessoaId());

		}
		
		return null;
	}

}
