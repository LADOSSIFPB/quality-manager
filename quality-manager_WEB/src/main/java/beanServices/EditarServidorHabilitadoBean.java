package beanServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifpb.qmanager.entidade.Servidor;

@ManagedBean(name="editarServidorHabilitadoBean")
@SessionScoped
public class EditarServidorHabilitadoBean {

	Servidor servidor;
	
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
}
