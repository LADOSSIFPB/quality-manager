package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.ifpb.qmanager.entidade.Instituicao;


@ManagedBean
@RequestScoped
public class CriarInstituicaoBean extends GenericBean<Instituicao> {

	private Instituicao instituicao = new Instituicao();

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public void save() {
				
		String mensagem = requestClient(instituicao, PathServices.CADASTRAR_INSTITUICAO);
		
	}
}
