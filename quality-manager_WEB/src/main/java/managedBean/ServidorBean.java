package managedBean;

import java.sql.SQLException;
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

	private List<Servidor> servidores;

	private String nomeServidor;

	public void consultarServidor() {

		if (this.getNomeServidor() != null
				&& !this.getNomeServidor().trim().isEmpty()) {

			Servidor servidorConsulta = new Servidor();
			servidorConsulta.setNomePessoa(this
					.getNomeServidor());

			try {
				this.setServidores(service
						.consultarServidores(servidorConsulta));
			} catch (SQLException e) {
				// TODO: verificar tratamento desse erro
				e.printStackTrace();
			}

		}
	}

	public void listarServidores() {

		try {
			this.setServidores(service
					.listarServidores());
		} catch (SQLException e) {
			// TODO: verificar tratamento desse erro
			e.printStackTrace();
		}
	}

	public String detalharServidor(
			Servidor servidor) {

		GenericBean.resetSessionScopedBean("editarServidorBean");

		EditarServidorBean editarServidorBean = new EditarServidorBean(
				servidor);
		GenericBean.setSessionValue("editarServidorBean",
				editarServidorBean);

		return PathRedirect.exibirServidor;

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
