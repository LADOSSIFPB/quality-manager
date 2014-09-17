package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;

@ManagedBean
@RequestScoped
public class InstituicaoFinanciadoraBean extends
		GenericBean<InstituicaoFinanciadora> implements beanInterface, Serializable {

	private InstituicaoFinanciadora instituicaoFinanciadora = new InstituicaoFinanciadora();
	private List<InstituicaoFinanciadora> instituicoes;
	
	public InstituicaoFinanciadora getInstituicaoFinanciadora() {
		return instituicaoFinanciadora;
	}

	public void setInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
	}
	
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
	
	@Override
	public void save() {

		String mensagem = requestClient(instituicaoFinanciadora,
				PathServices.CADASTRAR_INSTITUICAO);

	}
}
