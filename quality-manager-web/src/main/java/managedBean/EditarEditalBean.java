package managedBean;

import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.primefaces.model.menu.MenuModel;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;

@ManagedBean(name = "editarEditalBean")
@SessionScoped
public class EditarEditalBean implements EditarBeanInterface{

	private Edital edital;
	
	private List<SelectItem> programasInstitucionais;

	private boolean tenhoNumeroAnoEdital;	

	private MenuModel menuModel;

	private int EDITAL_NAO_CADASTRADO = 0;
	
	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	public EditarEditalBean() {

		setEditalAno();
	}

	public EditarEditalBean(MenuModel menuModel) {

		this.menuModel = menuModel;
		setEditalAno();		
	}
	
	private void setEditalAno() {
		
		Calendar agora = Calendar.getInstance();
		int ano = agora.get(Calendar.YEAR);

		this.edital = new Edital();
		this.edital.setAno(ano);
	}

	public EditarEditalBean(Edital edital) {
		this.setEdital(edital);
		this.menuModel = BreadCrumb.detalhesEdital(true);
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

				Edital editalResponse = response.readEntity(Edital.class);

				this.edital.setIdEdital(editalResponse.getIdEdital());

				GenericBean.setMessage("info.sucessoCadastroEdital",
						FacesMessage.SEVERITY_INFO);
				GenericBean.resetSessionScopedBean("editarEditalBean");

			} else {

				// Http Code: 304. Não modificado.
				Erro erroResponse = response.readEntity(Erro.class);
				GenericBean.setMessage(erroResponse.getMensagem(),
						FacesMessage.SEVERITY_ERROR);
			}

		} else {

			response = service.editarEdital(this.edital);
			GenericBean.sendRedirect(PathRedirect.exibirEdital);
		}
	}

	public void createEdit(Edital edital) {

		if (edital == null) {

			// Edital ainda não foi criado.
			GenericBean.resetSessionScopedBean("editarEditalBean");
			
			MenuModel menuModel = BreadCrumb.cadastrarEdital(true);
			
			EditarEditalBean editarEditalBean = new EditarEditalBean(menuModel);
			GenericBean.setSessionValue("editarEditalBean", editarEditalBean);
			
		} else {

			Response response = service.consultarEdital(edital.getIdEdital());
			this.edital = response.readEntity(Edital.class);
			this.menuModel = BreadCrumb.editarEdital(true);
		}

		GenericBean.sendRedirect(PathRedirect.cadastrarEdital);
	}

	public void mudarTipoEdital() {

		int ano = this.edital.getAno();

		if (ano != 0 && this.edital.getNumero() == 0) {

			int numero = this.service.consultarProximoNumeroEdital(ano);
			this.edital.setNumero(numero);

		} else {
			GenericBean.setMessage(
					"É necessário informar o Ano de criação do Edital",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public List<SelectItem> getProgramasInstitucionais() {

		if (programasInstitucionais != null) {

			return programasInstitucionais;

		} else {

			List<ProgramaInstitucional> programasInstitucionaisConsulta = service
					.listarProgramasInstitucionais();

			programasInstitucionais = GenericBean.initSelectOneItem();

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

	public boolean isTenhoNumeroAnoEdital() {
		return tenhoNumeroAnoEdital;
	}

	public void setTenhoNumeroAnoEdital(boolean tenhoNumeroAnoEdital) {
		this.tenhoNumeroAnoEdital = tenhoNumeroAnoEdital;
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}
}
