package beanServices;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import service.ProviderServiceFactory;
import service.QManagerService;
import managedBean.GenericBean;
import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.entidade.Departamento;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.Titulacao;

@ManagedBean(name="editarServidorHabilitadoBean")
@SessionScoped
public class EditarServidorHabilitadoBean {

	private Servidor servidor;
	
	private String repassword;
	
	private List<SelectItem> titulacoesSelectItem;
	
	private List<SelectItem> campiSelectItem;
	
	private List<SelectItem> departamentosSelectItem;
	
	private List<SelectItem> cargosServidorSelectItem;
	
	public EditarServidorHabilitadoBean(Servidor servidor) {
		this.servidor = servidor;
	}
	
	public void save() {
		
		boolean repasswordCheck = checkRePassword();
		
		if (repasswordCheck) {
			
			QManagerService service = ProviderServiceFactory
					.createServiceClient(QManagerService.class);

			Response response = service.cadastrarServidorHabilitado(servidor);
			
			int status = response.getStatus();

			if (status == HttpStatus.SC_OK) {
				
			} else if (status == HttpStatus.SC_NOT_MODIFIED) {
				
			} else {
				
			}
			
		} else {			
			
			GenericBean.setMessage("erro.senhaRedigitadaInvalida",
					FacesMessage.SEVERITY_ERROR);
		}		
	}

	private boolean checkRePassword() {		
		return repassword.equals(servidor.getSenha());
	}

	public List<SelectItem> getTitulacoesSelectItem() {
		
		TitulacaoAppScopeBean categoriaAppBean = (TitulacaoAppScopeBean) GenericBean
				.getApplicationContextValue("titulacaoAppScopeBean");

		List<Titulacao> titulacoes = categoriaAppBean.getTitulacoes();

		this.titulacoesSelectItem = GenericBean.initSelectOneItem();

		for (Titulacao titulacao : titulacoes) {

			this.titulacoesSelectItem.add(
					new SelectItem(titulacao.getIdTitulacao(), titulacao.getNome()));
		}

		return this.titulacoesSelectItem;
	}

	public List<SelectItem> getCampiSelectItem() {
		
		CampiAppScopeBean campiAppBean = (CampiAppScopeBean) GenericBean
				.getApplicationContextValue("campiAppScopeBean");

		List<Campus> campi = campiAppBean.getCampi();

		this.campiSelectItem = GenericBean.initSelectOneItem();

		for (Campus campus : campi) {

			this.campiSelectItem.add(
					new SelectItem(campus.getIdCampusInstitucional(),
							campus.getNome()));
		}
		
		return campiSelectItem;
	}

	public List<SelectItem> getDepartamentosSelectItem() {
		
		DepartamentosAppScopeBean departamentosAppBean = (DepartamentosAppScopeBean) GenericBean
			.getApplicationContextValue("departamentosAppScopeBean");

		List<Departamento> departamentos = departamentosAppBean.getDepartamentos();
		
		this.departamentosSelectItem = GenericBean.initSelectOneItem();
		
		for (Departamento departamento : departamentos) {
		
			this.departamentosSelectItem.add(
					new SelectItem(departamento.getIdDepartamento(),
							departamento.getNome()));
		}
		
		return this.departamentosSelectItem;
	}
	
	public List<SelectItem> getCargosServidorSelectItem() {
		
		CargosServidorAppScopeBean cargosServidorAppBean = (CargosServidorAppScopeBean) GenericBean
				.getApplicationContextValue("cargosServidorAppScopeBean");

		List<CargoServidor> cargosServidor = cargosServidorAppBean.getCargosServidor();
		
		this.cargosServidorSelectItem = GenericBean.initSelectOneItem();
		
		for (CargoServidor cargoServidor : cargosServidor) {
		
			this.cargosServidorSelectItem.add(
					new SelectItem(cargoServidor.getIdCargoServidor(),
							cargoServidor.getNomeCargoServidor()));
			}
		
		
		return this.cargosServidorSelectItem;
	}
	
	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
}
