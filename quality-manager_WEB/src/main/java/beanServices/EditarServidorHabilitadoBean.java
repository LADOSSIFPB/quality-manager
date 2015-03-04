package beanServices;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import managedBean.GenericBean;
import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.Departamento;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.Titulacao;

@ManagedBean(name="editarServidorHabilitadoBean")
@SessionScoped
public class EditarServidorHabilitadoBean {

	Servidor servidor;
	
	private List<SelectItem> titulacoesSelectItem;
	
	private List<SelectItem> campiSelectItem;
	
	private List<SelectItem> departamentosSelectItem;
	
	public EditarServidorHabilitadoBean(Servidor servidor) {
		this.servidor = servidor;
	}
	
	public void save() {}

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
		
		CampiAppScopeBean categoriaAppBean = (CampiAppScopeBean) GenericBean
				.getApplicationContextValue("campiAppScopeBean");

		List<Campus> campi = categoriaAppBean.getCampi();

		this.campiSelectItem = GenericBean.initSelectOneItem();

		for (Campus campus : campi) {

			this.campiSelectItem.add(
					new SelectItem(campus.getIdCampusInstitucional(),
							campus.getNome()));
		}
		
		return campiSelectItem;
	}

	public List<SelectItem> getDepartamentosSelectItem() {
		
		DepartamentosAppScopeBean categoriaAppBean = (DepartamentosAppScopeBean) GenericBean
			.getApplicationContextValue("departamentosAppScopeBean");

		List<Departamento> departamentos = categoriaAppBean.getDepartamentos();
		
		this.departamentosSelectItem = GenericBean.initSelectOneItem();
		
		for (Departamento departamento : departamentos) {
		
			this.departamentosSelectItem.add(
					new SelectItem(departamento.getIdDepartamento(),
							departamento.getNome()));
		}
		
		return departamentosSelectItem;
	}
	
	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}
}
