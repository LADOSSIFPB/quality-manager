package managedBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Curso;
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
		// TODO Auto-generated constructor stub
	}

	public EditarProgramaInstitucionalBean(
			ProgramaInstitucional programaInstitucional) {

		this.setProgramaInstitucional(programaInstitucional);
	}

	public void save() {

		Response response = null;

		if (getProgramaInstitucional().getIdProgramaInstitucional() == PROGRAMA_INSTITUCIONAL_NAO_CADASTRADO) {

			response = service
					.cadastrarProgramaInstitucional(getProgramaInstitucional());

		} else {

			response = service
					.editarProgramaInstitucional(getProgramaInstitucional());
		}

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			GenericBean.setMessage("info.sucessoCadastroProgramaInstitucional",
					FacesMessage.SEVERITY_INFO);

		} else {

			// Http Code: 304. Não modificado.
			Erro erroResponse = response.readEntity(Erro.class);

			GenericBean.setMessage("erro.cadastroProgramaInstitucional",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public String createEdit(ProgramaInstitucional programaInstitucional) {

		if (programaInstitucional == null) {
			// Curso ainda não criado.
			GenericBean.resetSessionScopedBean("editarProgramaInstitucionalBean");
			GenericBean.sendRedirect(PathRedirect.cadastrarProgramaInstitucional);
		} else {

			Response response = service.consultarProgramaInstitucional(programaInstitucional.getIdProgramaInstitucional());

			// Código de resposta do serviço.
			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {
				// Http Code: 200. Resposta para cadastro realizado com sucesso.
				ProgramaInstitucional programaResponse = response.readEntity(ProgramaInstitucional.class);

				// Curso encontrado.
				this.programaInstitucional = programaResponse;

			} else {
				// Http Code: 404. Curso inexistente.
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

			List<InstituicaoFinanciadora> alif = null;
			try {
				alif = service.listarInstituicoesFinanciadoras();
			} catch (SQLException e) {
				// TODO: verificar tratamento desse erro
				e.printStackTrace();
			}

			// TODO: tratar caso a lista estiver vazia

			ArrayList<SelectItem> alsi = new ArrayList<SelectItem>();

			if (!alif.isEmpty()) {

				for (InstituicaoFinanciadora instituicaoFinanciadora : alif) {
					SelectItem si = new SelectItem();
					si.setValue(instituicaoFinanciadora
							.getIdInstituicaoFinanciadora());
					si.setLabel(instituicaoFinanciadora.getSigla());
					alsi.add(si);
				}
			} else {
				System.err.println("Erro!");
			}

			instituicoesFinanciadoras = alsi;

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
