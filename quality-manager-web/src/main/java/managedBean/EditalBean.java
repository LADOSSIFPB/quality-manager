package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.menu.MenuModel;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Edital;

@ManagedBean
@ViewScoped
public class EditalBean {

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);
	
	private MenuModel menuModel;
	
	private int anoEdital;

	private List<Edital> editais;
	
	public EditalBean() {
	this.menuModel = BreadCrumb.edital(true);
	}

	public void consultarEditais() {

		if (this.getAnoEdital() != 0) {

			Edital editalConsulta = new Edital();
			editalConsulta.setAno(this.getAnoEdital());
			
			this.setEditais(service.consultarEditais(editalConsulta));
		}
	}

	/**
	 * Listar todos cursos existentes.
	 * 
	 * @return
	 */
	public void listarEditais() {
		this.editais = service.listarEditais();
	}

	public void detalharEdital(Edital edital) {

		GenericBean.resetSessionScopedBean("editarEditalBean");

		EditarEditalBean editarEdital = new EditarEditalBean(edital);
		GenericBean.setSessionValue("editarEditalBean", editarEdital);

		GenericBean.sendRedirect(PathRedirect.exibirEdital);
	}

	public List<Edital> getEditais() {
		return editais;
	}

	public void setEditais(List<Edital> editais) {
		this.editais = editais;
	}

	public int getAnoEdital() {
		return anoEdital;
	}

	public void setAnoEdital(int anoEdital) {
		this.anoEdital = anoEdital;
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}
}
