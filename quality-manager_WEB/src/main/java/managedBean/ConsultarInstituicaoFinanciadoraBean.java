package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;

@ManagedBean
@RequestScoped
public class ConsultarInstituicaoFinanciadoraBean implements Serializable {
	
	private List<InstituicaoFinanciadora> instituicoes;

	public List<InstituicaoFinanciadora> getInstituicoes() {
		return instituicoes;
	}

	public void setInstituicoes(List<InstituicaoFinanciadora> instituicoes) {
		this.instituicoes = instituicoes;
	}

	@PostConstruct
	public void init(){
		
		//instituicoes = service.CreateInstituicoes();
		
	}


}