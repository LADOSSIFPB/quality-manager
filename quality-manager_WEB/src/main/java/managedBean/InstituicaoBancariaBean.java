package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;

@ManagedBean
@RequestScoped
public class InstituicaoBancariaBean extends GenericBean implements
		BeanInterface {

	private InstituicaoBancaria instituicaoBancaria = new InstituicaoBancaria();
	private List<InstituicaoBancaria> instituicoesBancarias;

	public InstituicaoBancaria getInstituicaoBancaria() {
		return instituicaoBancaria;
	}

	public void setInstituicaoBancaria(InstituicaoBancaria instituicaoBancaria) {
		this.instituicaoBancaria = instituicaoBancaria;
	}

	public List<InstituicaoBancaria> getInstituicoesBancarias() {
		Response response = service.consultarInstituicoesBancarias();

		// TODO: em caso de erro, redirecionar para página de erro
		if (response.getStatus() != 200) {
			Erro qme = response.readEntity(new GenericType<Erro>() {
			});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe esse
								// campo

		}

		this.instituicoesBancarias = response
				.readEntity(new GenericType<ArrayList<InstituicaoBancaria>>() {
				});
		return instituicoesBancarias;
	}

	public void setInstituicoesBancarias(
			List<InstituicaoBancaria> instituicoesBancarias) {
		this.instituicoesBancarias = instituicoesBancarias;
	}

	@Override
	public void save() {
		
		Response message = service.cadastrarInstituicaoBancaria(instituicaoBancaria);

	}
	
	public void detalhesInstituicao(
			InstituicaoBancaria instituicaoBancaria) {

		ExibirDetalhes exibirDetalhes = new ExibirDetalhes(
				instituicaoBancaria);

		GenericBean.setSessionValue("exibirDetalhes",
				exibirDetalhes);

		exibirDetalhes.redirecionarExibirBancaria();

	}

	public void update() {

		ExibirDetalhes exibirDetalhes = (ExibirDetalhes) GenericBean
				.getSessionValue("exibirDetalhes");
		//TODO: encontrar o metódo de edição
		service.editarInstituicaoBancaria(exibirDetalhes
				.getInstituicaoBancaria());

	}

}
