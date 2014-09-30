package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.ifpb.qmanager.entidade.Projeto;

@ManagedBean
@RequestScoped
public class ProjetoBean extends GenericBean<Projeto> implements beanInterface {

	private Projeto projeto = new Projeto();

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@Override
	public void save() {

		String message = requestCadastrar(this.projeto,
				PathServices.CADASTRAR_PROJETO);

	}

}
