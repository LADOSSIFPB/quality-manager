package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;

@ManagedBean
@RequestScoped
public class ProgramaInstitucionalBean extends
		GenericBean<ProgramaInstitucional> implements beanInterface {

	private ProgramaInstitucional programaInstitucional = new ProgramaInstitucional();

	private List<SelectItem> instituicoesFinanciadoras;

	public ProgramaInstitucional getProgramaInstitucional() {
		return programaInstitucional;
	}

	public void setProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {
		this.programaInstitucional = programaInstitucional;
	}

	@Override
	public void save() {
		String message = requestCadastrar(programaInstitucional,
				PathServices.CADASTRAR_PROGRAMA_INSTITUCIONAL);
	}

	public List<SelectItem> getInstituicoesFinanciadoras() {

		Response response = requestSelectConsultar(PathServices.CONSULTAR_INSTITUICAO);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		ArrayList<InstituicaoFinanciadora> alif = response
				.readEntity(new GenericType<ArrayList<InstituicaoFinanciadora>>() {
				});

		response.close();

		ArrayList<SelectItem> alsi = new ArrayList<SelectItem>();

		if (!alif.isEmpty()) {
			InstituicaoFinanciadora instituicaoFinanciadora = null;
			SelectItem si = new SelectItem();

			for (int i = 0; i < alif.size(); i++) {
				instituicaoFinanciadora = alif.get(i);
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
