package managedBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.RecursoInstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Servidor;

@ManagedBean(name = "editarInstituicaoFinanciadoraBean")
@SessionScoped
public class EditarInstituicaoFinanciadoraBean {

	InstituicaoFinanciadora instituicaoFinanciadora;
	RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private int INSTITUICAO_NAO_CADASTRADA = 0;
	
	public EditarInstituicaoFinanciadoraBean() {
		this(new InstituicaoFinanciadora());
	}

	public EditarInstituicaoFinanciadoraBean(
			RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora) {
		this.recursoInstituicaoFinanciadora = recursoInstituicaoFinanciadora;
	}
	
	public EditarInstituicaoFinanciadoraBean(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
	}

	public void save() {

		Response response = null;

		if (instituicaoFinanciadora.getIdInstituicaoFinanciadora()
				== INSTITUICAO_NAO_CADASTRADA) {

			PessoaBean pessoaBean = (PessoaBean) GenericBean.getSessionValue(
					"pessoaBean");

			Servidor gestor = new Servidor();
			gestor.setPessoaId(pessoaBean.getPessoaId());
			this.instituicaoFinanciadora.setGestor(gestor);
			
			// Cadastrar uma nova Insituição Financeira.
			response = service.cadastrarInstituicao(
					this.instituicaoFinanciadora);

		} else {

			// Atualização da Insituição Financeira.
			response = service.editarInstituicaoFinanciadora(
					instituicaoFinanciadora);
		}

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			// Cadastro realizado com sucesso.
			GenericBean.setMessage("info.sucessoCadastroInstituicaoFinanciadora",
					FacesMessage.SEVERITY_INFO);
			GenericBean.resetSessionScopedBean("editarInstituicaoFinanciadoraBean");

		} else if (statusCode == HttpStatus.SC_NOT_ACCEPTABLE){
			
			// Problema com os dados enviados. Recuperar mensagem do serviço.
			Erro erroResponse = response.readEntity(Erro.class);
			GenericBean.setMessage(erroResponse.getMensagem(),
					FacesMessage.SEVERITY_ERROR);
			
		} else {

			// Http Code: 304. Não modificado.
			GenericBean.setMessage("erro.cadastroInstituicaoFinanciadora",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public String createEdit(InstituicaoFinanciadora instituicao) {

		if (instituicao == null) {
			
			GenericBean.resetSessionScopedBean(
					"editarInstituicaoFinanciadoraBean");
			GenericBean.sendRedirect(PathRedirect
					.cadastrarInstituicaoFinanciadora);

		} else {

			Response response = service.consultarInstituicao(instituicao
					.getIdInstituicaoFinanciadora());

			this.instituicaoFinanciadora = response.readEntity(
					new GenericType<InstituicaoFinanciadora>() {});

		}

		return PathRedirect.cadastrarInstituicaoFinanciadora;
	}
	
	public void lancarRecurso(){
		
		Response response = null;
		
		response = service.cadastrarRecursoInstituicao(recursoInstituicaoFinanciadora);
		
		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			// Cadastro realizado com sucesso.
			GenericBean.setMessage("info.sucessoLancamentoOrcamento",
					FacesMessage.SEVERITY_INFO);
			GenericBean.resetSessionScopedBean("editarInstituicaoFinanciadoraBean");

		} else if (statusCode == HttpStatus.SC_NOT_ACCEPTABLE){
			
			// Problema com os dados enviados. Recuperar mensagem do serviço.
			Erro erroResponse = response.readEntity(Erro.class);
			GenericBean.setMessage(erroResponse.getMensagem(),
					FacesMessage.SEVERITY_ERROR);
			
		} else {

			// Http Code: 304. Não modificado.
			GenericBean.setMessage("erro.lancamentoOrcamento",
					FacesMessage.SEVERITY_ERROR);
		}
		
		
	}

	public InstituicaoFinanciadora getInstituicaoFinanciadora() {
		return instituicaoFinanciadora;
	}

	public void setInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
	}

	public RecursoInstituicaoFinanciadora getRecursoInstituicaoFinanciadora() {
		return recursoInstituicaoFinanciadora;
	}

	public void setRecursoInstituicaoFinanciadora(
			RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora) {
		this.recursoInstituicaoFinanciadora = recursoInstituicaoFinanciadora;
	}
}
