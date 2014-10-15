package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Projeto;

@ManagedBean
@RequestScoped
public class ProjetoBean extends GenericBean<Projeto> implements BeanInterface {

	private Projeto projeto = new Projeto();
	private List<Projeto> projetos;

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

	public List<Projeto> getProjetos() {
		Response response = requestGetAll(PathServices.CONSULTAR_PROJETOS);

		// TODO: em caso de erro, redirecionar para página de erro
		if (response.getStatus() != 200) {
			Erro qme = response.readEntity(new GenericType<Erro>() {
			});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe esse
								// campo

		}

		this.projetos = response
				.readEntity(new GenericType<ArrayList<Projeto>>() {
				});
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

}
