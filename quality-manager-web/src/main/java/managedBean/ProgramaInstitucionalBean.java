package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;

@ManagedBean
@ViewScoped
public class ProgramaInstitucionalBean {

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private String nomeProgramaInstitucional;

	private List<ProgramaInstitucional> programasInstitucionais;

	public void consultarProgramasInstitucionais() {

		if (this.nomeProgramaInstitucional != null
				&& !this.nomeProgramaInstitucional.trim().isEmpty()) {

			ProgramaInstitucional programaConsulta = new ProgramaInstitucional();
			programaConsulta
					.setNomeProgramaInstitucional(this.nomeProgramaInstitucional);

			this.programasInstitucionais = service
					.consultarProgramasInstitucionais(programaConsulta);
		}
	}

	/**
	 * Listar todos cursos existentes.
	 * 
	 * @return
	 */
	public void listarProgramasInstitucionais() {
		this.programasInstitucionais = service.listarProgramasInstitucionais();
	}

	public void detalharProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {

		GenericBean.resetSessionScopedBean("editarProgramaInstitucionalBean");

		EditarProgramaInstitucionalBean editarProgramaInstitucionalBean = new EditarProgramaInstitucionalBean(
				programaInstitucional);

		GenericBean.setSessionValue("editarProgramaInstitucionalBean",
				editarProgramaInstitucionalBean);

		GenericBean.sendRedirect(PathRedirect.exibirProgramaInstitucional);
	}

	public List<ProgramaInstitucional> getProgramasInstitucionais() {
		return programasInstitucionais;
	}

	public void setProgramasInstitucionais(
			List<ProgramaInstitucional> programasInstitucionais) {
		this.programasInstitucionais = programasInstitucionais;
	}

	public String getNomeProgramaInstitucional() {
		return nomeProgramaInstitucional;
	}

	public void setNomeProgramaInstitucional(String nomeProgramaInstitucional) {
		this.nomeProgramaInstitucional = nomeProgramaInstitucional;
	}
}
