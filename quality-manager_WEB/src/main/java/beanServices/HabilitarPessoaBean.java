package beanServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.Servidor;

@ManagedBean(name="loginBean")
@ViewScoped
public class HabilitarPessoaBean {
	
	private Pessoa pessoaHabilitada;
	
	private Servidor servidor;
	
	public String listarPessoaHabilitada() {
		return null;		
	}
	
	public String cadastrarServidor() {
		return null;		
	}
}
