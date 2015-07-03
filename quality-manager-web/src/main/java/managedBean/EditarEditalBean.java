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
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.TipoEdital;

@ManagedBean(name = "editarEditalBean")
@SessionScoped
public class EditarEditalBean {

	private Edital edital;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private int EDITAL_NAO_CADASTRADO = 0;

	private List<SelectItem> programasInstitucionais;
	private List<SelectItem> tiposEdital;

	public EditarEditalBean() {
		this.edital = new Edital();
	}

	public EditarEditalBean(Edital edital) {
		this.setEdital(edital);
	}

	public void save() {

		Response response = null;

		if (getEdital().getIdEdital() == EDITAL_NAO_CADASTRADO) {
			PessoaBean pessoaBean = (PessoaBean) GenericBean
					.getSessionValue("pessoaBean");
			this.edital.getGestor().setPessoaId(pessoaBean.getPessoaId());
			response = service.cadastrarEdital(this.edital);

			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {

				GenericBean.setMessage("info.sucessoCadastroEdital",
						FacesMessage.SEVERITY_INFO);
				GenericBean.resetSessionScopedBean("editarEditalBean");

			} else {

				// Http Code: 304. NÃ£o modificado.
				Erro erroResponse = response.readEntity(Erro.class);
				GenericBean.setMessage("erro.cadastroEdital",
						FacesMessage.SEVERITY_ERROR);
			}

		} else {

			response = service.editarEdital(this.edital);
			GenericBean.sendRedirect(PathRedirect.exibirEdital);

		}

	}

	public String createEdit(Edital edital) {

		if (edital == null) {

			// Edital ainda nÃ£o criado.
			GenericBean.resetSessionScopedBean("editarEditalBean");
			GenericBean.sendRedirect(PathRedirect.cadastrarEdital);

		} else {

			Response response = service.consultarEdital(edital.getIdEdital());

			this.edital = response.readEntity(new GenericType<Edital>() {
			});

		}

		return PathRedirect.cadastrarEdital;
	}

	public List<SelectItem> getProgramasInstitucionais() {

		if (programasInstitucionais != null) {

			return programasInstitucionais;

		} else {

			List<ProgramaInstitucional> programasInstitucionaisConsulta = service
					.listarProgramasInstitucionais();

			programasInstitucionais = new ArrayList<SelectItem>();

			if (!programasInstitucionaisConsulta.isEmpty()) {

				for (ProgramaInstitucional programaInstitucional : programasInstitucionaisConsulta) {

					SelectItem selectItem = new SelectItem();
					selectItem.setValue(programaInstitucional
							.getIdProgramaInstitucional());
					selectItem.setLabel(programaInstitucional.getSigla());

					programasInstitucionais.add(selectItem);
				}
			}

			return programasInstitucionais;
		}
	}

	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
	}

	public List<SelectItem> getTiposProjeto() throws SQLException {
		if (tiposEdital != null) {

			return tiposEdital;

		} else {

			List<TipoEdital> tiposProjetosConsulta = service.listarTipoEdital();

			tiposEdital = new ArrayList<SelectItem>();

			if (!tiposProjetosConsulta.isEmpty()) {

				for (TipoEdital tiposEditais : tiposProjetosConsulta) {

					SelectItem selectItem = new SelectItem();
					selectItem.setValue(tiposEditais.getIdTipoEdital());
					selectItem.setLabel(tiposEditais.getNomeTipoEdital());

					tiposEdital.add(selectItem);
				}
			}

			return tiposEdital;
		}
	}

	public void setTiposProjeto(List<SelectItem> tiposProjeto) {
		this.tiposEdital = tiposProjeto;
	}
}
