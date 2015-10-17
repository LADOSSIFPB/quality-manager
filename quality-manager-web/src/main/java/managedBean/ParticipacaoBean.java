package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Projeto;

@ManagedBean
@SessionScoped
public class ParticipacaoBean {

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);
	
	private List<Participacao> participacoes;

	public void consultarParticipacao() {
	}

	/**
	 * Listar todos cursos existentes.
	 * 
	 * @return
	 */
	public void listarParticipacoes() {		
	}
	
	public void adicionarMembroProjeto(Projeto projeto) {
		
	}
}
