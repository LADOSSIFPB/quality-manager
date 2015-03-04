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
public class CampiAppScopeBean implements Serializable {

	private static final long serialVersionUID = -9135255053224066272L;
	
	private List<Campus> campi;

	public CampiAppScopeBean() {}

	private void populateCampi() {

		if (campi == null 
				|| (campi != null && campi.isEmpty())) {
			
			QManagerService service = ProviderServiceFactory
					.createServiceClient(QManagerService.class);			
			// Atribui a lista de categorias.
			this.campi = service.listarLocais();
		}
	}

	public List<Campus> getCampi() {
		populateCampi();
		return campi;
	}
}