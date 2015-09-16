package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.Projeto;

@ManagedBean(name = "gestorProjetoBean")
@ViewScoped
public class GestorProjetoBean implements Serializable {

	private static final long serialVersionUID = -3804463268093227211L;
	
	private Projeto projeto;
	
	private List<Projeto> projetos;
	
	private List<Pessoa> participantes;	
	
	/**
	 * Consultar lista de Participantes.
	 * 
	 * @param query
	 * @return
	 */
	public List<Pessoa> completeParticipantes(String query) {

		Pessoa pessoa = new Pessoa();
		pessoa.setNomePessoa(query);

		QManagerService serviceConsultarPessoa = ProviderServiceFactory
				.createServiceClient(QManagerService.class);
		
		List<Pessoa> pessoas = serviceConsultarPessoa.consultarPessoas(pessoa);

		return pessoas;
	}
	
	public void consultarProjetos() {
		
		if (!participantes.isEmpty()) {
			
			QManagerService serviceConsultarPessoas = ProviderServiceFactory
					.createServiceClient(QManagerService.class);
			
			this.projetos = serviceConsultarPessoas.
					consultarProjetosParticipantes(this.participantes);
		}
	}
	
	public void listarProjetos() {
		System.out.println("Listar projetos");
	}


	public Projeto getProjeto() {
		return projeto;
	}


	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}


	public List<Projeto> getProjetos() {
		return projetos;
	}


	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}


	public List<Pessoa> getParticipantes() {
		return participantes;
	}


	public void setParticipantes(List<Pessoa> participantes) {
		this.participantes = participantes;
	}
}
