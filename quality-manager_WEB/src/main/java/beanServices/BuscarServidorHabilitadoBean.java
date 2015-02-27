package beanServices;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.qmanager.entidade.Servidor;

@ManagedBean(name="buscarServidorHabilitadoBean")
@ViewScoped
public class BuscarServidorHabilitadoBean {
	
	private int siape;
	
	private String nome;
	
	private List<Servidor> servidores; 
	
	public String listarServidorHabilitado() {
		
		String pageRedirect = null;
		
		System.out.println(siape);
		
		return pageRedirect;		
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Servidor> getServidores() {
		return servidores;
	}

	public void setServidores(List<Servidor> servidores) {
		this.servidores = servidores;
	}
}
