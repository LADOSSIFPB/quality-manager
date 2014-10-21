package managedBean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import service.ProviderServiceFactory;
import service.QManagerService;

public class GenericBean {

	protected QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	public PessoaBean getPessoaBean(FacesContext context) {

		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		PessoaBean pessoaBean = (PessoaBean) session.getAttribute("pessoaBean");

		return pessoaBean;

	}

}
