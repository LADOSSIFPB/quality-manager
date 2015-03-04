package beanServices;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Titulacao;

@ManagedBean(eager = true)
@ApplicationScoped
public class TitulacaoAppScopeBean implements Serializable {

	private static final long serialVersionUID = 2783042425792069062L;
	
	private List<Titulacao> titulacao;

	public TitulacaoAppScopeBean() {
	}

	private void populateTitulacao() {

		if (titulacao == null || (titulacao != null && titulacao.isEmpty())) {
			
			QManagerService service = ProviderServiceFactory
					.createServiceClient(QManagerService.class);			
			// Atribui a lista de categorias.
		}
	}

	public List<Titulacao> getTitulacao() {
		populateTitulacao();
		return titulacao;
	}
}