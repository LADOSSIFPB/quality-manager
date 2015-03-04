package beanServices;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Campus;

@ManagedBean(eager = true)
@ApplicationScoped
public class CampusAppScopeBean implements Serializable {

	private static final long serialVersionUID = -2270868527399421303L;
	
	private List<Campus> campus;

	public CampusAppScopeBean() {}

	private void populateCampus() {

		if (campus == null || (campus != null && campus.isEmpty())) {
			
			QManagerService service = ProviderServiceFactory
					.createServiceClient(QManagerService.class);			
			// Atribui a lista de categorias.
		}
	}

	public List<Campus> getCampus() {
		return campus;
	}
}