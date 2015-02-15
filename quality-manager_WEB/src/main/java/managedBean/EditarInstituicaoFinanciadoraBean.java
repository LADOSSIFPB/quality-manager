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

@ManagedBean(name = "editarInstituicaoFinanciadoraBean")
@SessionScoped
public class EditarInstituicaoFinanciadoraBean {

	InstituicaoFinanciadora instituicaoFinanciadora;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private int INSTITUICAO_NAO_CADASTRADO = 0;

	public EditarInstituicaoFinanciadoraBean() {
		instituicaoFinanciadora = new InstituicaoFinanciadora();
	}

	public EditarInstituicaoFinanciadoraBean(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
	}

	public void save() {

		Response response = null;

		if (instituicaoFinanciadora.getIdInstituicaoFinanciadora() == INSTITUICAO_NAO_CADASTRADO) {

			PessoaBean pessoaBean = (PessoaBean) GenericBean.getSessionValue("pessoaBean");

			this.instituicaoFinanciadora.getGestor().setPessoaId(
					pessoaBean.getPessoaId());
			response = service.cadastrarInstituicao(this.instituicaoFinanciadora);

		} else {

			response = service
					.editarInstituicaoFinanciadora(instituicaoFinanciadora);
		}

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			GenericBean.setMessage("info.sucessoCadastroInstituicaoFinanciadora",
					FacesMessage.SEVERITY_INFO);
			GenericBean.resetSessionScopedBean("editarInstituicaoFinanciadoraBean");

		} else {

			// Http Code: 304. Não modificado.
			Erro erroResponse = response.readEntity(Erro.class);

			GenericBean.setMessage("erro.cadastroInstituicaoFinanciadora",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public String createEdit(InstituicaoFinanciadora instituicao) {

		if (instituicao == null) {
			GenericBean
					.resetSessionScopedBean("editarInstituicaoFinanciadoraBean");
			GenericBean
					.sendRedirect(PathRedirect.cadastrarInstituicaoFinanciadora);

		} else {

			Response response = service.consultarInstituicao(instituicao
					.getIdInstituicaoFinanciadora());

			this.instituicaoFinanciadora = response
					.readEntity(new GenericType<InstituicaoFinanciadora>() {
					});

		}

		return PathRedirect.cadastrarInstituicaoFinanciadora;
	}

	public InstituicaoFinanciadora getInstituicaoFinanciadora() {
		return instituicaoFinanciadora;
	}

	public void setInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
	}
}
