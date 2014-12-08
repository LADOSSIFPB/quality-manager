package managedBean;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;

@ManagedBean
@SessionScoped
public class ExibirInstituicaoFinanciadoraBean {

	private InstituicaoFinanciadora instituicaoFinanciadora;

	public ExibirInstituicaoFinanciadoraBean(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		
		setInstituicaoFinanciadora(instituicaoFinanciadora);

	}
	
	public ExibirInstituicaoFinanciadoraBean(){
		
	}

	public InstituicaoFinanciadora getInstituicaoFinanciadora() {
		return instituicaoFinanciadora;
	}

	public void setInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
	}
	
	public void redirecionarExibirInstFinan(){
		
		GenericBean.sendRedirect(PathRedirect.exibirInstituicaoFinanciadora);
		
	}

}
