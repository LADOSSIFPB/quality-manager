package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Projeto;

@ManagedBean
@SessionScoped
public class ParticipacaoBean implements Serializable {

	private static final long serialVersionUID = 3043463804014515052L;

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
