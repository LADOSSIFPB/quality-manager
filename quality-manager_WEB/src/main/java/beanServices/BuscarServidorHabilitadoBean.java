package beanServices;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.Response;

import managedBean.GenericBean;
import managedBean.PathRedirect;

import org.apache.http.HttpStatus;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.Servidor;

@ManagedBean(name="buscarServidorHabilitadoBean")
@ViewScoped
public class BuscarServidorHabilitadoBean {
	
	public static int TAMANHO_MINIMO = 3;
	
	private int siape;
	
	private String nomeServidor;
	
	private List<Servidor> servidores; 
	
	public String listarServidorHabilitado() {
		
		String pageRedirect = null;
		
		this.servidores = new ArrayList<Servidor>();
		
		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		Response response = service.buscarServidorHabilitado(siape);
		
		int status = response.getStatus();

		if (status == HttpStatus.SC_OK) {
			
			Servidor servidor = response.readEntity(Servidor.class);
			
			if (!servidor.isHabilitada()) {
				
				this.servidores.add(servidor);
				
			} else {
				
				GenericBean.setMessage("erro.servidorHabilitado",
						FacesMessage.SEVERITY_ERROR);
			}
			
		} else if (status == HttpStatus.SC_NOT_FOUND) {
			
			this.servidores.clear();
			
			Erro erro = response.readEntity(Erro.class);
			GenericBean.setMessage(erro.getMensagem(),
					FacesMessage.SEVERITY_ERROR);
		}
		
		return pageRedirect;		
	}
	
	public void listarServidoresPorNome() {		
		
		if (this.nomeServidor != null 
				&& !this.nomeServidor.trim().isEmpty()
				&& this.nomeServidor.length() >= TAMANHO_MINIMO) {
			
			QManagerService service = ProviderServiceFactory
					.createServiceClient(QManagerService.class);

			Servidor servidor = new Servidor();
			servidor.setNomePessoa(this.nomeServidor);
			
			this.servidores = service.consultarServidoresHabilitados(servidor);
		}
	}
	
	public String editarServidor(Servidor servidor) {
		
		GenericBean.resetSessionScopedBean("editarServidorHabilitadoBean");
		
		EditarServidorHabilitadoBean editarCursoBean = 
				new EditarServidorHabilitadoBean(servidor);
		GenericBean.setSessionValue("editarServidorHabilitadoBean", editarCursoBean);		
		
		return PathRedirect.cadastrarServidorHabilitado;	
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
