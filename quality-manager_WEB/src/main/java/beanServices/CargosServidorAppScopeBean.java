package beanServices;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.CargoServidor;

@ManagedBean(eager = true)
@ApplicationScoped
public class CargosServidorAppScopeBean implements Serializable {
	
	private static final long serialVersionUID = 6995253265560456938L;
	
	private List<CargoServidor> cargosServidor;

	public CargosServidorAppScopeBean() {}

	private void populateCargosServidor() {

		if (cargosServidor == null 
				|| (cargosServidor != null && cargosServidor.isEmpty())) {
			
			QManagerService service = ProviderServiceFactory
					.createServiceClient(QManagerService.class);			
			
			// Atribui a lista de cargos para servidores.
			cargosServidor = service.listarCargos();			
		}
	}

	public List<CargoServidor> getCargosServidor() {
		populateCargosServidor();
		return cargosServidor;
	}
}