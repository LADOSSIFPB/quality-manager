package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.Response;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Projeto;

@ManagedBean(name = "projetoBean")
@ViewScoped
public class ProjetoBean implements Serializable {

	private static final long serialVersionUID = -2044005279556730534L;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private List<Projeto> projetos;
	
	private String nomeProjeto;

	public void consultarProjetos() {

		if (this.getNomeProjeto() != null
				&& !this.getNomeProjeto().trim().isEmpty()) {
			this.setProjetos(service.consultarProjetos(this.nomeProjeto));
		}
	}

	/**
	 * Listar todos cursos existentes.
	 * 
	 * @return
	 */
	public void listarProjetos() {
		
		PessoaBean pessoaBean = (PessoaBean) GenericBean
				.getSessionValue("pessoaBean");
		this.setProjetos(service.consultarProjetosPessoa(pessoaBean));
	}

	/**
	 * Detalhar o curso selecionado.
	 * 
	 * @param projeto
	 * @return
	 */
	public void detalharProjeto(Projeto projeto) {

		GenericBean.resetSessionScopedBean("editarProjetoBean");

		Response response = service.consultarInformacoesProjeto(projeto);
		projeto = response.readEntity(Projeto.class);

		EditarProjetoBean editarProjetoBean = new EditarProjetoBean(projeto);
		
		GenericBean.setSessionValue("editarProjetoBean", editarProjetoBean);

		GenericBean.sendRedirect(PathRedirect.exibirProjeto);
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}
}
