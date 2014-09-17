package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.ifpb.qmanager.entidade.Edital;

@ManagedBean
@RequestScoped
public class EditalBean extends GenericBean<Edital> implements beanInterface{
	
	private Edital edital = new Edital();

	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
	}
	
	@Override
	public void save(){
		
		String message = requestClient(edital, PathServices.CADASTRAR_EDITAL);
	}

}
