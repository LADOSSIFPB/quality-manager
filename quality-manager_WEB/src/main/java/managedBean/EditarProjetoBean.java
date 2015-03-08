package managedBean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.Projeto;

@ManagedBean(name = "editarProjetoBean")
@SessionScoped
public class EditarProjetoBean {

	private Projeto projeto;
	private List<Pessoa> pessoas;
	
	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private int PROJETO_NAO_CADASTRADO = 0;

	private List<SelectItem> editais;
	private List<SelectItem> campus;
	
	public EditarProjetoBean(Projeto projeto) {
		this.setProjeto(projeto);
	}
	
	public EditarProjetoBean(Projeto projeto, List<Pessoa> pessoas) {
		this.setProjeto(projeto);
		this.setPessoas(pessoas);
	}
	
	public EditarProjetoBean() {
		this.setProjeto(new Projeto());
		this.setPessoas(new LinkedList<Pessoa>());
	}

	public void save() {

		Response response = null;

		if (getProjeto().getIdProjeto() == PROJETO_NAO_CADASTRADO) {

			PessoaBean pessoaBean = (PessoaBean) GenericBean
					.getSessionValue("pessoaBean");
			getProjeto().getOrientador().setPessoaId(pessoaBean.getPessoaId());
			response = service.cadastrarProjeto(getProjeto());

		} else {

			response = service.editarProjeto(getProjeto());
		}

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			GenericBean.setMessage("info.sucessoCadastroProjeto",
					FacesMessage.SEVERITY_INFO);
			GenericBean.resetSessionScopedBean("editarProjetoBean");

		} else {

			// Http Code: 304. Não modificado.
			Erro erroResponse = response.readEntity(Erro.class);

			GenericBean.setMessage("erro.cadastroProjeto",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public String createEdit(Projeto projeto) {

		if (projeto == null) {
			// Curso ainda não criado.
			GenericBean.resetSessionScopedBean("editarProjetoBean");
			GenericBean.sendRedirect(PathRedirect.cadastrarProjeto);
		} else {

			Response response = service
					.consultarProjeto(projeto.getIdProjeto());

			// Código de resposta do serviço.
			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {
				// Http Code: 200. Resposta para cadastro realizado com sucesso.
				Projeto projetoResponse = response.readEntity(Projeto.class);

				// Curso encontrado.
				this.setProjeto(projetoResponse);

			} else {
				// Http Code: 404. Curso inexistente.
				Erro erroResponse = response.readEntity(Erro.class);

				GenericBean.setMessage("erro.projetoInexistente",
						FacesMessage.SEVERITY_ERROR);
			}
		}

		return PathRedirect.cadastrarProjeto;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public List<SelectItem> getEditais() {
		if (editais != null) {

			return editais;

		} else {

			List<Edital> editaisConsulta = service
					.listarEditais();

			editais = new ArrayList<SelectItem>();

			if (!editaisConsulta.isEmpty()) {

				for (Edital edital : editaisConsulta) {

					SelectItem selectItem = new SelectItem();
					selectItem.setValue(edital
							.getIdEdital());
					selectItem.setLabel(edital.getNumAno());

					editais.add(selectItem);
				}
			}

			return editais;
		}
	}

	public void setEditais(List<SelectItem> editais) {
		this.editais = editais;
	}

	public List<SelectItem> getCampus() {
		if (campus != null) {

			return campus;

		} else {

			List<Campus> campusConsulta = service
					.listarLocais();

			campus = new ArrayList<SelectItem>();

			if (!campusConsulta.isEmpty()) {

				for (Campus campi : campusConsulta) {

					SelectItem selectItem = new SelectItem();
					selectItem.setValue(campi
							.getIdCampusInstitucional());
					selectItem.setLabel(campi.getNome());

					campus.add(selectItem);
				}
			}

			return campus;
		}
	}

	public void setCampus(List<SelectItem> campus) {
		this.campus = campus;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
