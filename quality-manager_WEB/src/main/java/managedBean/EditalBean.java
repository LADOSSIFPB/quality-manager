package managedBean;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Edital;

@ManagedBean
@ViewScoped
public class EditalBean {

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	// CADASTRAR
	private int anoEdital;

	// CONSULTAR
	private List<Edital> editais;

	public void consultarEditais() {

		if (this.getAnoEdital() != 0) {

			Edital editalConsulta = new Edital();
			editalConsulta.setAno(this.getAnoEdital());

			try {
				this.setEditais(service.consultarEditais(editalConsulta));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Listar todos cursos existentes.
	 * 
	 * @return
	 */
	public void listarEditais() {

		try {
			this.setEditais(service.listarEditais());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String detalharEdital(Edital edital) {

		GenericBean.resetSessionScopedBean("editarEditalBean");

		EditarEditalBean editarEdital = new EditarEditalBean(edital);
		GenericBean.setSessionValue("editarEditalBean", editarEdital);

		return PathRedirect.exibirEdital;

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

}
