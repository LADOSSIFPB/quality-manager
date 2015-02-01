package managedBean;

import java.sql.SQLException;
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
import br.edu.ifpb.qmanager.util.IntegerUtil;

@ManagedBean
@RequestScoped
public class EditalBean extends GenericBean implements BeanInterface {

	// CADASTRAR
	private Edital edital = new Edital();
	private List<SelectItem> programasInstitucionais;

	// CONSULTAR
	private List<Edital> editais;

	public List<SelectItem> getProgramasInstitucionais() {

		List<ProgramaInstitucional> alpi = null;
		try {
			alpi = service.listarProgramasInstitucionais();
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
		return this.editais;
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

	public String createEdit(Edital edital) {

		if (edital == null) {
			GenericBean.sendRedirect(PathRedirect.cadastrarEdital);

		} else {

			Response response = service.consultarEdital(edital.getIdEdital());

			this.edital = response.readEntity(new GenericType<Edital>() {
			});

		}

		return PathRedirect.cadastrarEdital;
	}

	@Override
	public void save() {
		if (edital.getIdEdital() == 0) {
			PessoaBean pessoaBean = getPessoaBean(FacesContext
					.getCurrentInstance());

			edital.getGestor().setPessoaId(pessoaBean.getPessoaId());

			Response message = service.cadastrarEdital(edital);
		} else {
			Response response = service.editarEdital(edital);
		}
	}

	public String detalhesEdital(Edital edital) {

		this.edital = edital;
		return PathRedirect.exibirEdital;

	}

}
