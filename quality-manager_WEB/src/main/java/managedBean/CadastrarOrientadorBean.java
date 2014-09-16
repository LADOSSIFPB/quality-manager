package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.Individuo;
import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.entidade.Usuario;

@ManagedBean
@RequestScoped
public class CadastrarOrientadorBean extends GenericBean<Orientador>
		implements beanInterface {
	
	private Orientador orientador = new Orientador();

	public Orientador getOrientador() {
		return orientador;
	}

	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
	}

	@Override
	public void save() {

		String message = requestClient(orientador,
				PathServices.CADASTRAR_ORIENTADOR);

	}

}
