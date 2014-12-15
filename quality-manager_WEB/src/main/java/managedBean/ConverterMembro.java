package managedBean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.MembroProjeto;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.util.IntegerUtil;

@FacesConverter("converterMembro")
public class ConverterMembro extends GenericBean implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent componente,
			String value) {

		IntegerUtil integerUtil = new IntegerUtil(Integer.parseInt(value));
		Response response = service.consultarPessoa(integerUtil);
		MembroProjeto membroProjeto = response
				.readEntity(new GenericType<MembroProjeto>() {
				});

		return membroProjeto;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componente,
			Object value) {
		MembroProjeto membroProjeto = (MembroProjeto) value;
		return String.valueOf(membroProjeto.getPessoaId());
	}

}
