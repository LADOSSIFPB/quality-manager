package managedBean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;

@ManagedBean
@RequestScoped
public class ProgramaInstitucionalBean extends GenericBean implements
		BeanInterface, Serializable {

	private static final long serialVersionUID = -3749706911495000581L;

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

	public void save() {
		Response response = null;

		if (programaInstitucional != null
				&& programaInstitucional.getIdProgramaInstitucional() == 0) {

			PessoaBean pessoaBean = getPessoaBean(FacesContext
					.getCurrentInstance());

			programaInstitucional.getGestor().setPessoaId(
					pessoaBean.getPessoaId());

			response = service
					.cadastrarProgramaInstitucional(programaInstitucional);
		} else {
			response = service
					.editarProgramaInstitucional(programaInstitucional);
		}

		if (response != null && response.getStatus() == 200) {

			GenericBean.sendRedirect(PathRedirect.cadastroConcluido);

		} else {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
							"Orçamento insufiente"));
		}

	}

	public String createEdit(ProgramaInstitucional programaInstitucional) {

		if (programaInstitucional == null) {
			GenericBean
					.sendRedirect(PathRedirect.cadastrarProgramaInstitucional);

		} else {

			Response response = service
					.consultarProgramaInstitucional(programaInstitucional
							.getIdProgramaInstitucional());

			this.programaInstitucional = response
					.readEntity(new GenericType<ProgramaInstitucional>() {
					});

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

	public List<ProgramaInstitucional> getProgramasInstitucionais() {
		return programasInstitucionais;
	}

	public void setProgramasInstitucionais(
			List<ProgramaInstitucional> programasInstitucionais) {
		this.programasInstitucionais = programasInstitucionais;
	}

	public String detalhesProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {

		this.programaInstitucional = programaInstitucional;

		return PathRedirect.exibirProgramaInstitucional;

	}

}
