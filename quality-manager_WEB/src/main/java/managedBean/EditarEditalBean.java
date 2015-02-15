package managedBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;

@ManagedBean(name = "editarEditalBean")
@SessionScoped
public class EditarEditalBean {

	private Edital edital;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private int EDITAL_NAO_CADASTRADO = 0;

	private List<SelectItem> programasInstitucionais;

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
			response = service.cadastrarEdital(getEdital());

		} else {

			response = service.editarEdital(getEdital());
		}

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			GenericBean.setMessage("info.sucessoCadastroEdital",
					FacesMessage.SEVERITY_INFO);
			GenericBean.resetSessionScopedBean("editarEditalBean");

		} else {

			// Http Code: 304. Não modificado.
			Erro erroResponse = response.readEntity(Erro.class);

			GenericBean.setMessage("erro.cadastroEdital",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public String createEdit(Edital edital) {

		if (edital == null) {
			// Curso ainda não criado.
			GenericBean.resetSessionScopedBean("editarEditalBean");
			GenericBean.sendRedirect(PathRedirect.cadastrarEdital);
		} else {

			Response response = service.consultarEdital(edital.getIdEdital());

			// Código de resposta do serviço.
			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {
				// Http Code: 200. Resposta para cadastro realizado com sucesso.
				Edital editalResponse = response.readEntity(Edital.class);

				// Curso encontrado.
				this.edital = editalResponse;

			} else {
				// Http Code: 404. Curso inexistente.
				Erro erroResponse = response.readEntity(Erro.class);

				GenericBean.setMessage("erro.editalInexistente",
						FacesMessage.SEVERITY_ERROR);
			}
		}

		return PathRedirect.cadastrarEdital;
	}

	public List<SelectItem> getProgramasInstitucionais() {

		if (programasInstitucionais != null) {
			return programasInstitucionais;
		} else {

			List<ProgramaInstitucional> alpi = null;
			try {
				alpi = service.listarProgramasInstitucionais();
			} catch (SQLException e) {
				// TODO: verificar tratamento desse erro
				e.printStackTrace();
			}

			// TODO: tratar caso a lista estiver vazia

			ArrayList<SelectItem> alsi = new ArrayList<SelectItem>();

			if (!alpi.isEmpty()) {

				for (ProgramaInstitucional programaInstitucional : alpi) {
					SelectItem si = new SelectItem();
					si.setValue(programaInstitucional
							.getIdProgramaInstitucional());
					si.setLabel(programaInstitucional.getSigla());
					alsi.add(si);
				}
			} else {
				System.err.println("Erro!");
			}

			programasInstitucionais = alsi;

			return programasInstitucionais;

		}

	}

	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
	}

}
