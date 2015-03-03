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
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;

@ManagedBean(name = "editarProgramaInstitucionalBean")
@SessionScoped
public class EditarProgramaInstitucionalBean {

	ProgramaInstitucional programaInstitucional;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private int PROGRAMA_INSTITUCIONAL_NAO_CADASTRADO = 0;
	
	private List<SelectItem> instituicoesFinanciadoras;

	public EditarProgramaInstitucionalBean() {
		this.programaInstitucional = new ProgramaInstitucional();
	}

	public EditarProgramaInstitucionalBean(
			ProgramaInstitucional programaInstitucional) {

		this.setProgramaInstitucional(programaInstitucional);
	}

	public void save() {

		Response response = null;

		if (getProgramaInstitucional().getIdProgramaInstitucional() == 
				PROGRAMA_INSTITUCIONAL_NAO_CADASTRADO) {
			
			PessoaBean pessoaBean = (PessoaBean) GenericBean.getSessionValue(
					"pessoaBean");
			this.programaInstitucional.getGestor().setPessoaId(
					pessoaBean.getPessoaId());
			response = service.cadastrarProgramaInstitucional(
					this.programaInstitucional);

		} else {

			response = service
					.editarProgramaInstitucional(getProgramaInstitucional());
		}

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			GenericBean.setMessage("info.sucessoCadastroProgramaInstitucional",
					FacesMessage.SEVERITY_INFO);
			GenericBean.resetSessionScopedBean("editarProgramaInstitucionalBean");

		} else {

			// Http Code: 304. Não modificado.
			Erro erroResponse = response.readEntity(Erro.class);
			GenericBean.setMessage("erro.cadastroProgramaInstitucional",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public String createEdit(ProgramaInstitucional programaInstitucional) {

		if (programaInstitucional == null) {
			// Edital ainda não criado.
			GenericBean.resetSessionScopedBean("editarProgramaInstitucionalBean");
			GenericBean.sendRedirect(PathRedirect.cadastrarProgramaInstitucional);
			
		} else {

			Response response = service.consultarProgramaInstitucional(
					programaInstitucional.getIdProgramaInstitucional());

			// Código de resposta do serviço.
			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {
				// Http Code: 200. Resposta para cadastro realizado com sucesso.
				ProgramaInstitucional programaResponse = response.readEntity(ProgramaInstitucional.class);

				// Edital encontrado.
				this.programaInstitucional = programaResponse;

			} else {
				// Http Code: 404. Edital inexistente.
				Erro erroResponse = response.readEntity(Erro.class);
				GenericBean.setMessage("erro.programaInstitucionalInexistente",
						FacesMessage.SEVERITY_ERROR);
			}
		}

		return PathRedirect.cadastrarProgramaInstitucional;
	}
	
	public List<SelectItem> getInstituicoesFinanciadoras() {

		if (instituicoesFinanciadoras != null) {
			
			return instituicoesFinanciadoras;
		
		} else {

			List<InstituicaoFinanciadora> instituicoesFinanciadorasConsulta = 
					service.listarInstituicoesFinanciadoras();

			instituicoesFinanciadoras = new ArrayList<SelectItem>();

			if (!instituicoesFinanciadorasConsulta.isEmpty()) {

				for (InstituicaoFinanciadora instituicaoFinanciadora :
					instituicoesFinanciadorasConsulta) {
					
					SelectItem selectItem = new SelectItem();
					selectItem.setValue(instituicaoFinanciadora
							.getIdInstituicaoFinanciadora());
					selectItem.setLabel(instituicaoFinanciadora.getSigla());
					
					instituicoesFinanciadoras.add(selectItem);
				}
			}

			return instituicoesFinanciadoras;
		}
	}

	public ProgramaInstitucional getProgramaInstitucional() {
		return programaInstitucional;
	}

	public void setProgramaInstitucional(ProgramaInstitucional programaInstitucional) {
		this.programaInstitucional = programaInstitucional;
	}
}
