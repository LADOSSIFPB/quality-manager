package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.Individuo;
import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.entidade.Usuario;

@ManagedBean
@RequestScoped
public class CadastrarOrientadorBean extends GenericBean<Individuo<Orientador>>
		implements beanInterface {

	private Individuo<Orientador> individuo = new Individuo<Orientador>();
	private Orientador orientador = new Orientador();

	public Orientador getOrientador() {
		return orientador;
	}

	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
	}

	public Individuo<Orientador> getIndividuo() {
		return individuo;
	}

	public void setIndividuo(Individuo<Orientador> individuo) {
		this.individuo = individuo;
	}

	@Override
	public void save() {
		
		individuo.setIndividuo(orientador);

		String message = requestClient(individuo,
				PathServices.CADASTRAR_ORIENTADOR);

	}

}
