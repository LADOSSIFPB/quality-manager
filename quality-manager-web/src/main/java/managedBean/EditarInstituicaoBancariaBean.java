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
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;

@ManagedBean(name = "editarInstituicaoBancariaBean")
@SessionScoped
public class EditarInstituicaoBancariaBean {

	private InstituicaoBancaria instituicaoBancaria;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private int INSTITUICAO_NAO_CADASTRADO = 0;

	public void save() {

		Response response = null;

		if (getInstituicaoBancaria().getIdInstituicaoBancaria() 
				== INSTITUICAO_NAO_CADASTRADO) {

			response = service.cadastrarInstituicaoBancaria(getInstituicaoBancaria());
		} else {

			response = service.editarInstituicaoBancaria(
					getInstituicaoBancaria());
		}

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			GenericBean.setMessage(
					"info.sucessoCadastroInstituicaoBancaria",
					FacesMessage.SEVERITY_INFO);
			GenericBean.resetSessionScopedBean("editarInstituicaoBancariaBean");

		} else {

			// Http Code: 304. Não modificado.
			Erro erro = response.readEntity(Erro.class);

			GenericBean.setMessage("erro.cadastroInstituicaoBancaria",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public void createEdit(InstituicaoBancaria instituicaoBancaria) {

		if (instituicaoBancaria == null) {
			
			GenericBean.resetSessionScopedBean("editarInstituicaoBancariaBean");
			GenericBean.sendRedirect(PathRedirect.cadastrarInstituicaoBancaria);

		} else {

			Response response = service.consultarInstituicaoBancaria(instituicaoBancaria
					.getIdInstituicaoBancaria());

			this.setInstituicaoBancaria(response
					.readEntity(new GenericType<InstituicaoBancaria>() {
					}));
		}

		GenericBean.sendRedirect(PathRedirect.cadastrarInstituicaoBancaria);
	}

	public EditarInstituicaoBancariaBean() {
		this.instituicaoBancaria = new InstituicaoBancaria();
	}

	public EditarInstituicaoBancariaBean(
			InstituicaoBancaria instituicaoFinanciadora) {
		this.setInstituicaoBancaria(instituicaoFinanciadora);
	}
	
	public InstituicaoBancaria getInstituicaoBancaria() {
		return instituicaoBancaria;
	}

	public void setInstituicaoBancaria(InstituicaoBancaria instituicaoBancaria) {
		this.instituicaoBancaria = instituicaoBancaria;
	}
}
