package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.ifpb.qmanager.entidade.Discente;

@ManagedBean
@RequestScoped
public class DiscenteBean extends GenericBean<Discente>
		implements beanInterface {

	private Discente discente = new Discente();
	
	public Discente getDiscente() {
		return discente;
	}

	public void setDiscente(Discente discente) {
		this.discente = discente;
	}
		
	@Override
	public void save() {
		
		requestClient(discente, PathServices.CADASTRAR_DISCENTE);

	}

}
