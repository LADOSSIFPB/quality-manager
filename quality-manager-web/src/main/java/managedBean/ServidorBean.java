package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Servidor;

@ManagedBean
@ViewScoped
public class ServidorBean {

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private String nomeServidor;
	
	private List<Servidor> servidores;	

	public void consultarServidor() {

		if (this.getNomeServidor() != null
				&& !this.getNomeServidor().trim().isEmpty()) {

			Servidor servidorConsulta = new Servidor();
			servidorConsulta.setNomePessoa(this
					.getNomeServidor());
			
			this.servidores = service.consultarServidores(servidorConsulta);
		}
	}

	public void listarServidores() {
		this.servidores = service.listarServidores();
	}

	public void detalharServidor(
			Servidor servidor) {

		GenericBean.resetSessionScopedBean("editarServidorBean");

		EditarServidorBean editarServidorBean = new EditarServidorBean(
				servidor);
		GenericBean.setSessionValue("editarServidorBean",
				editarServidorBean);

		GenericBean.sendRedirect(PathRedirect.exibirServidor);
	}

	public List<Servidor> getServidores() {
		return servidores;
	}

	public void setServidores(List<Servidor> servidores) {
		this.servidores = servidores;
	}

	public String getNomeServidor() {
		return nomeServidor;
	}

	public void setNomeServidor(String nomeServidor) {
		this.nomeServidor = nomeServidor;
	}
}
