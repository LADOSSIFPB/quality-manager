package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;

@ManagedBean(name = "editarEditalBean")
@SessionScoped
public class EditarEditalBean {

	private Edital edital;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private int EDITAL_NAO_CADASTRADO = 0;

	private List<SelectItem> programasInstitucionais;

	public EditarEditalBean() {
		this.edital = new Edital();
	}

	public EditarEditalBean(Edital edital) {
		this.setEdital(edital);
	}

	public void save() {

		Response response = null;

		if (getEdital().getIdEdital() == EDITAL_NAO_CADASTRADO) {
			PessoaBean pessoaBean = (PessoaBean) GenericBean
					.getSessionValue("pessoaBean");
			this.edital.getGestor().setPessoaId(pessoaBean.getPessoaId());
			response = service.cadastrarEdital(this.edital);

		} else {

			response = service.editarEdital(this.edital);
		}

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			GenericBean.setMessage("info.sucessoCadastroEdital",
					FacesMessage.SEVERITY_INFO);
			GenericBean.resetSessionScopedBean("editarEditalBean");

		} else {

			// Http Code: 304. Não modificado.
			Erro erroResponse = response.readEntity(Erro.class);
			GenericBean.setMessage("erro.cadastroEdital",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public String createEdit(Edital edital) {

		if (edital == null) {
			
			// Edital ainda não criado.
			GenericBean.resetSessionScopedBean("editarEditalBean");
			GenericBean.sendRedirect(PathRedirect.cadastrarEdital);
		
		} else {

			Response response = service.consultarEdital(edital.getIdEdital());

			// Código de resposta do serviço.
			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {
				// Http Code: 200. Resposta para cadastro realizado com sucesso.
				Edital editalResponse = response.readEntity(Edital.class);

				// Curso encontrado.
				this.edital = editalResponse;

			} else {
				// Http Code: 404. Edital inexistente.
				Erro erroResponse = response.readEntity(Erro.class);

				GenericBean.setMessage("erro.editalInexistente",
						FacesMessage.SEVERITY_ERROR);
			}
		}

		return PathRedirect.cadastrarEdital;
	}

	public List<SelectItem> getProgramasInstitucionais() {

		if (programasInstitucionais != null) {
			
			return programasInstitucionais;
		
		} else {

			List<ProgramaInstitucional> programasInstitucionaisConsulta = 
					service.listarProgramasInstitucionais();
			
			programasInstitucionais = new ArrayList<SelectItem>();

			if (!programasInstitucionaisConsulta.isEmpty()) {

				for (ProgramaInstitucional programaInstitucional : 
					programasInstitucionaisConsulta) {
					
					SelectItem selectItem = new SelectItem();
					selectItem.setValue(programaInstitucional
							.getIdProgramaInstitucional());
					selectItem.setLabel(programaInstitucional.getSigla());
					
					programasInstitucionais.add(selectItem);
				}
			}

			return programasInstitucionais;
		}
	}

	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
	}
}
