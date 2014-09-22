package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;

@ManagedBean
@RequestScoped
public class ProgramaInstitucionalBean extends
		GenericBean<ProgramaInstitucional> implements beanInterface {

	private ProgramaInstitucional programaInstitucional = new ProgramaInstitucional();

	public ProgramaInstitucional getProgramaInstitucional() {
		return programaInstitucional;
	}

	public void setProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {
		this.programaInstitucional = programaInstitucional;
	}

	@Override
	public void save() {

		String message = requestClient(programaInstitucional,
				PathServices.CADASTRAR_PROGRAMA_INSTITUCIONAL);

	}

}
