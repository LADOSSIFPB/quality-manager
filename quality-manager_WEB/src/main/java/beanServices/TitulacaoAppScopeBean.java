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
	
	private List<Titulacao> titulacoes;

	public TitulacaoAppScopeBean() {}

	private void populateTitulacoes() {

		if (titulacoes == null 
				|| (titulacoes != null && titulacoes.isEmpty())) {
			
			QManagerService service = ProviderServiceFactory
					.createServiceClient(QManagerService.class);			
			// Atribui a lista de categorias.
			this.titulacoes = service.listarTitulacoes();
		}
	}

	public List<Titulacao> getTitulacoes() {
		populateTitulacoes();
		return titulacoes;
	}
}