package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;

@ManagedBean
@RequestScoped
public class EditalBean extends GenericBean implements BeanInterface {

	// CADASTRAR
	private Edital edital = new Edital();
	private List<SelectItem> programasInstitucionais;

	// CONSULTAR
	private List<Edital> editais;

	public List<SelectItem> getProgramasInstitucionais() {
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

		ArrayList<ProgramaInstitucional> alpi = response
				.readEntity(new GenericType<ArrayList<ProgramaInstitucional>>() {
				});

		response.close();

		ArrayList<SelectItem> alsi = new ArrayList<SelectItem>();

		if (!alpi.isEmpty()) {

			for (ProgramaInstitucional programaInstitucional : alpi) {
				SelectItem si = new SelectItem();
				si.setValue(programaInstitucional.getIdProgramaInstitucional());
				si.setLabel(programaInstitucional.getSigla());
				alsi.add(si);
			}
		} else {
			// TODO: Melhorar esse erro
			System.err.println("Erro!");
		}

		programasInstitucionais = alsi;
		return programasInstitucionais;
	}

	public void setProgramasInstitucionais(
			List<SelectItem> programasInstitucionais) {
		this.programasInstitucionais = programasInstitucionais;
	}

	public List<Edital> getEditais() {
		Response response = service.consultarEditais();

		// TODO: em caso de erro, redirecionar para página de erro
		if (response.getStatus() != 200) {
			Erro qme = response.readEntity(new GenericType<Erro>() {
			});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe esse
								// campo

		}

		this.editais = response
				.readEntity(new GenericType<ArrayList<Edital>>() {
				});

		return editais;
	}

	public void setEditais(List<Edital> editais) {
		this.editais = editais;
	}

	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
	}

	@Override
	public void save() {
		
		PessoaBean pessoaBean = getPessoaBean(FacesContext.getCurrentInstance());

		edital.getGestor().setPessoaId(
				pessoaBean.getPessoaId());
		
		Response message = service.cadastrarEdital(edital);
	}
	
	public void detalhesEdital(
			Edital edital) {

		ExibirDetalhes exibirDetalhes = new ExibirDetalhes(
				edital);

		GenericBean.setSessionValue("exibirDetalhes",
				exibirDetalhes);

		exibirDetalhes.redirecionarExibirEdital();

	}
	
	public void update() {

		ExibirDetalhes exibirDetalhes = (ExibirDetalhes) GenericBean
				.getSessionValue("exibirDetalhes");
		//TODO: encontrar o metódo de edição
		service.editarEdital(exibirDetalhes.getEdital());

	}

}
