package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.Servidor;

@ManagedBean
@RequestScoped
public class ProjetoBean extends GenericBean implements BeanInterface {

	private Projeto projeto = new Projeto();
	private List<Projeto> projetos;
	private List<SelectItem> editais;

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@Override
	public void save() {

		PessoaBean pessoaBean = getPessoaBean(FacesContext.getCurrentInstance());

		projeto.getOrientador().setPessoaId(pessoaBean.getPessoaId());

		Response message = service.cadastrarProjeto(projeto);

	}

	public List<Projeto> getProjetos() {

		Servidor orientador = new Servidor();

		PessoaBean pessoaBean = getPessoaBean(FacesContext.getCurrentInstance());

		orientador.setPessoaId(pessoaBean.getPessoaId());

		Response response = service.consultarProjetos(orientador);

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

	public List<SelectItem> getEditais() {

		Response response = service.consultarEditais();

		// TODO: em caso de erro, redirecionar para página de erro
		if (response.getStatus() != 200) {
			Erro qme = response.readEntity(new GenericType<Erro>() {
			});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe esse
								// campo

		}

		ArrayList<Edital> ale = response
				.readEntity(new GenericType<ArrayList<Edital>>() {
				});

		response.close();

		ArrayList<SelectItem> alsi = new ArrayList<SelectItem>();

		if (!ale.isEmpty()) {

			for (Edital edital : ale) {
				SelectItem si = new SelectItem();
				si.setValue(edital.getIdEdital());
				si.setLabel(edital.getNumAno());
				alsi.add(si);
			}
		} else {
			System.err.println("Erro!");
		}

		editais = alsi;

		return editais;
	}

	public void setEditais(List<SelectItem> editais) {
		this.editais = editais;
	}

	public void detalhesProjeto(Projeto projeto) {

		ExibirDetalhes exibirDetalhes = new ExibirDetalhes(projeto);

		GenericBean.setSessionValue("exibirDetalhes", exibirDetalhes);

		exibirDetalhes.redirecionarExibirProjeto();

	}

	public void update() {

		ExibirDetalhes exibirDetalhes = (ExibirDetalhes) GenericBean
				.getSessionValue("exibirDetalhes");
		// TODO: encontrar o metódo de edição
		service.editarProjeto(exibirDetalhes.getProjeto());

	}

}
