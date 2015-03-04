package beanServices;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Departamento;

@ManagedBean(eager = true)
@ApplicationScoped
public class DepartamentosAppScopeBean implements Serializable {

	private static final long serialVersionUID = -9135255053224066272L;
	
	private List<Departamento> departamentos;

	public DepartamentosAppScopeBean() {}

	private void populateDepartamentos() {

		if (departamentos == null 
				|| (departamentos != null && departamentos.isEmpty())) {
			
			QManagerService service = ProviderServiceFactory
					.createServiceClient(QManagerService.class);			
			// Atribui a lista de categorias.
			this.departamentos = service.listarDepartamentos();
		}
	}

	public List<Departamento> getDepartamentos() {
		populateDepartamentos();
		return departamentos;
	}
}