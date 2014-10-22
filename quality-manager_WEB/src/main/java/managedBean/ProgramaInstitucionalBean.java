package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;

@ManagedBean
@RequestScoped
public class ProgramaInstitucionalBean extends GenericBean implements
		BeanInterface {

	// CADASTRAR
	private ProgramaInstitucional programaInstitucional = new ProgramaInstitucional();
	private List<SelectItem> instituicoesFinanciadoras;

	// CONSULTAR
	private List<ProgramaInstitucional> programasInstitucionais;

	public ProgramaInstitucional getProgramaInstitucional() {
		return programaInstitucional;
	}

	public void setProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {
		this.programaInstitucional = programaInstitucional;
	}

	@Override
	public void save() {

		PessoaBean pessoaBean = getPessoaBean(FacesContext.getCurrentInstance());

		programaInstitucional.getGestor().setPessoaId(
				pessoaBean.getPessoaId());

		Response message = service
				.cadastrarProgramaInstitucional(programaInstitucional);
	}

	public List<SelectItem> getInstituicoesFinanciadoras() {

		Response response = service.consultarInstituicoes();

		// TODO: em caso de erro, redirecionar para página de erro
		if (response.getStatus() != 200) {
			Erro qme = response.readEntity(new GenericType<Erro>() {
			});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe esse
								// campo

		}

		ArrayList<InstituicaoFinanciadora> alif = response
				.readEntity(new GenericType<ArrayList<InstituicaoFinanciadora>>() {
				});

		response.close();

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

	public List<ProgramaInstitucional> getProgramasInstitucionais() {
		Response response = service.consultarProgramasInstitucionais();

		// TODO: em caso de erro, redirecionar para página de erro
		if (response.getStatus() != 200) {
			Erro qme = response.readEntity(new GenericType<Erro>() {
			});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe esse
								// campo

		}

		this.programasInstitucionais = response
				.readEntity(new GenericType<ArrayList<ProgramaInstitucional>>() {
				});

		return programasInstitucionais;
	}

	public void setProgramasInstitucionais(
			List<ProgramaInstitucional> programasInstitucionais) {
		this.programasInstitucionais = programasInstitucionais;
	}
}
