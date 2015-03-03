package beanServices;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.Response;

import managedBean.GenericBean;

import org.apache.http.HttpStatus;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Servidor;

@ManagedBean(name="buscarServidorHabilitadoBean")
@ViewScoped
public class BuscarServidorHabilitadoBean {
	
	private int siape;
	
	private String nomeServidor;
	
	private List<Servidor> servidores; 
	
	public String listarServidorHabilitado() {
		
		String pageRedirect = null;
		
		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		Response response = service.buscarServidorHabilitado(siape);
		
		int status = response.getStatus();

		if (status == HttpStatus.SC_OK) {
			
			Servidor servidor = response.readEntity(Servidor.class);
			
			if (!servidor.isHabilitada()) {
				servidores = new ArrayList<Servidor>();
				servidores.add(servidor);
			} else {
				GenericBean.setMessage("erro.servidorHabilitado",
						FacesMessage.SEVERITY_ERROR);
			}			
		}
		
		return pageRedirect;		
	}
	
	public void listarServidoresPorNome() {
		System.out.println("pesquisando:" + nomeServidor);		
	}
	
	public String editarServidor(Servidor servidor) {
		return null;		
	}

	public int getSiape() {
		return siape;
	}

	public void setSiape(int siape) {
		this.siape = siape;
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
