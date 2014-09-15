package managedBean;

import br.edu.ifpb.qmanager.entidade.Projeto;

public class CadastrarProjetoBean extends GenericBean<Projeto> implements
		beanInterface {

	private Projeto projeto = new Projeto();

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	@Override
	public void save() {

		String message = requestClient(this.projeto,
				PathServices.CADASTRAR_PROJETO);

	}

}
