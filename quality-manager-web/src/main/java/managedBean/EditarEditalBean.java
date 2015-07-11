package managedBean;

import java.sql.SQLException;
import java.util.Calendar;
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
import br.edu.ifpb.qmanager.entidade.TipoProgramaInstitucional;

@ManagedBean(name = "editarEditalBean")
@SessionScoped
public class EditarEditalBean {

	private Edital edital;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private int EDITAL_NAO_CADASTRADO = 0;

	private List<SelectItem> programasInstitucionais;
	
	private List<SelectItem> tiposEditais;
	
	private boolean tenhoNumeroAnoEdital;

	public EditarEditalBean() {
		
		this.edital = new Edital();		
		
		Calendar agora = Calendar.getInstance(); 
		int ano = agora.get(Calendar.YEAR);
		
		this.edital.setAno(ano);
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

	public String createEdit(Edital edital) {

		if (edital == null) {

			// Edital ainda não foi criado.
			GenericBean.resetSessionScopedBean("editarEditalBean");
			GenericBean.sendRedirect(PathRedirect.cadastrarEdital);

		} else {

			Response response = service.consultarEdital(edital.getIdEdital());
			this.edital = response.readEntity(Edital.class);
		}

		return PathRedirect.cadastrarEdital;
	}
	
	public void mudarTipoEdital() {
		
		int ano = this.edital.getAno();
		
		if (ano != 0 
				&& this.edital.getNumero() == 0) {
			
			int numero = this.service.consultarProximoNumeroEdital(ano);
			this.edital.setNumero(numero);
			
		} else {
			GenericBean.setMessage("É necessário informar o Ano de criação do Edital",
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

	public List<SelectItem> getTiposEditais() throws SQLException {
		if (tiposEditais != null) {

			return tiposEditais;

		} else {

			tiposEditais = GenericBean.initSelectOneItem();

			for (TipoProgramaInstitucional tipoPI : TipoProgramaInstitucional
					.values()) {

				SelectItem selectItem = new SelectItem();
				selectItem.setValue(tipoPI);
				selectItem.setLabel(tipoPI.toString());

				tiposEditais.add(selectItem);
			}

			return tiposEditais;
		}
	}

	public void setTiposEditais(List<SelectItem> tiposProjeto) {
		this.tiposEditais = tiposProjeto;
	}

	public boolean isTenhoNumeroAnoEdital() {
		return tenhoNumeroAnoEdital;
	}

	public void setTenhoNumeroAnoEdital(boolean tenhoNumeroAnoEdital) {
		this.tenhoNumeroAnoEdital = tenhoNumeroAnoEdital;
	}
}
