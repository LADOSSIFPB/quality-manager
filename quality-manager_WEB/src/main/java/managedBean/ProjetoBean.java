package managedBean;

import java.sql.SQLException;
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
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.Projeto;

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

	public String createEdit(Projeto projeto) {

		if (projeto == null) {
			GenericBean.sendRedirect(PathRedirect.cadastrarProjeto);

		} else {

			Response response = service
					.consultarProjeto(projeto.getIdProjeto());

			this.projeto = response.readEntity(new GenericType<Projeto>() {
			});

		}

		return PathRedirect.cadastrarProjeto;
	}

	@Override
	public void save() {

		if (projeto.getIdProjeto() == 0) {
			PessoaBean pessoaBean = getPessoaBean(FacesContext
					.getCurrentInstance());

			projeto.getOrientador().setPessoaId(pessoaBean.getPessoaId());

			Response message = service.cadastrarProjeto(projeto);
		} else {
			Response response = service.editarProjeto(projeto);
		}
	}

	public List<Projeto> getProjetos() {

		Pessoa orientador = new Pessoa();

		PessoaBean pessoaBean = getPessoaBean(FacesContext.getCurrentInstance());

		orientador.setPessoaId(pessoaBean.getPessoaId());

		Response response = service.consultarProjetosPessoa(orientador);

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

		List<Edital> ale = null;
		try {
			ale = service.listarEditais();
		} catch (SQLException e) {
			// TODO: verificar tratamento de erro
			e.printStackTrace();
		}

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

	public String detalhesProjeto(Projeto projeto) {

		this.projeto = projeto;
		return PathRedirect.exibirProjeto;

	}

}
