package managedBean;

import java.sql.SQLException;
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

	// CADASTRAR
	private String nomeProgramaInstitucional;

	// CONSULTAR
	private List<ProgramaInstitucional> programasInstitucionais;

	public void consultarProgramasInstitucionais() {

		if (this.nomeProgramaInstitucional != null && !this.nomeProgramaInstitucional.trim().isEmpty()) {

			ProgramaInstitucional programaConsulta = new ProgramaInstitucional();
			programaConsulta.setNomeProgramaInstitucional(this.nomeProgramaInstitucional);

			try {
				this.programasInstitucionais = service.consultarProgramasInstitucionais(programaConsulta);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Listar todos cursos existentes.
	 * 
	 * @return
	 */
	public void listarProgramasInstitucionais() {

		try {
			this.setProgramasInstitucionais(service.listarProgramasInstitucionais());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public String detalharProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {

		GenericBean.resetSessionScopedBean("editarProgramaInstitucionalBean");

		EditarProgramaInstitucionalBean editarProgramaInstitucionalBean = new EditarProgramaInstitucionalBean(
				programaInstitucional);
		GenericBean.setSessionValue("editarProgramaInstitucionalBean",
				editarProgramaInstitucionalBean);

		return PathRedirect.exibirProgramaInstitucional;

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
