package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Individuo;

@ManagedBean
@RequestScoped
public class CadastrarDiscenteBean extends GenericBean<Individuo<Discente>>
		implements beanInterface {

	private Individuo<Discente> individuo = new Individuo<Discente>();
	private Discente discente = new Discente();

	public Individuo<Discente> getIndividuo() {
		return individuo;
	}

	public void setIndividuo(Individuo<Discente> individuo) {
		this.individuo = individuo;
	}
	
	public Discente getDiscente() {
		return discente;
	}

	public void setDiscente(Discente discente) {
		this.discente = discente;
	}
		
	@Override
	public void save() {
		
		individuo.setIndividuo(discente);
		
		requestClient(individuo, PathServices.CADASTRAR_DISCENTE);

	}

}
