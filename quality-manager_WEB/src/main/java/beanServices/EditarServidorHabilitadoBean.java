package beanServices;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import managedBean.GenericBean;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.Titulacao;

@ManagedBean(name="editarServidorHabilitadoBean")
@SessionScoped
public class EditarServidorHabilitadoBean {

	Servidor servidor;
	
	private List<SelectItem> titulacoesSelectItem;
	
	public EditarServidorHabilitadoBean(Servidor servidor) {
		this.servidor = servidor;
	}
	
	public void save() {}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	public List<SelectItem> getTitulacoesSelectItem() {
		
		TitulacaoAppScopeBean categoriaAppBean = (TitulacaoAppScopeBean) GenericBean
				.getApplicationContextValue("titulacaoAppScopeBean");

		List<Titulacao> titulacoes = categoriaAppBean.getTitulacoes();

		this.titulacoesSelectItem = GenericBean.initSelectOneItem();

		for (Titulacao titulacao : titulacoes) {

			this.titulacoesSelectItem.add(new SelectItem(titulacao,
					titulacao.getNome()));
		}

		return this.titulacoesSelectItem;
	}
}
