package managedBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;

@ManagedBean
@RequestScoped
public class ProgramaInstitucionalBean extends
		GenericBean<ProgramaInstitucional, InstituicaoFinanciadora> implements
		beanInterface {

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

		List<InstituicaoFinanciadora> lif = requestSelectConsultar(PathServices.CONSULTAR_INSTITUICAO);

		System.out.println(lif); // aqui me escreve vazia

		ArrayList<SelectItem> alsi = new ArrayList<SelectItem>();

		/* TODO: colocar instituições financiadoras no Select
		if (!lif.isEmpty()) {
			InstituicaoFinanciadora instituicaoFinanciadora;
			SelectItem si = new SelectItem();
			Iterator<InstituicaoFinanciadora> it = lif.iterator();

			while (it.hasNext()) {
				instituicaoFinanciadora = (InstituicaoFinanciadora) it.next();
				si.setValue(instituicaoFinanciadora
						.getIdInstituicaoFinanciadora());
				si.setLabel(instituicaoFinanciadora.getSigla());
				alsi.add(si);
			}
		} else {
			System.err.println("Erro!");
		}*/

		instituicoesFinanciadoras = alsi;
		
		return instituicoesFinanciadoras;

	}
}
