package managedBean;

import br.edu.ifpb.qmanager.entidade.Projeto;

public class CadastrarProjetoBean extends GenericBean<Projeto>{
	
	private Projeto projeto = new Projeto();

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	public void save(){
		
		String message = requestClient(this.projeto, PathServices.CADASTRAR_PROJETO);
				
	}
	
	

}
