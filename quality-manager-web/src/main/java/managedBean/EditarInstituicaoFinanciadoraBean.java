package managedBean;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.primefaces.model.menu.MenuModel;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.RecursoInstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Servidor;

@ManagedBean(name = "editarInstituicaoFinanciadoraBean")
@SessionScoped
public class EditarInstituicaoFinanciadoraBean implements EditarBeanInterface {

	InstituicaoFinanciadora instituicaoFinanciadora;

	RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora;

	private List<RecursoInstituicaoFinanciadora> recursosInstituicaoFinanciadora;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private MenuModel menuModel;

	private int INSTITUICAO_NAO_CADASTRADA = 0;

	public EditarInstituicaoFinanciadoraBean() {
		this(new InstituicaoFinanciadora());
	}

	public EditarInstituicaoFinanciadoraBean(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public EditarInstituicaoFinanciadoraBean(
			RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora) {
		this.recursoInstituicaoFinanciadora = recursoInstituicaoFinanciadora;
	}

	public EditarInstituicaoFinanciadoraBean(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
		this.menuModel = BreadCrumb.detalhesInstituicaoFinanciadora(true);
	}

	public void save() {

		Response response;

		if (instituicaoFinanciadora.getIdInstituicaoFinanciadora() == INSTITUICAO_NAO_CADASTRADA) {

			PessoaBean pessoaBean = (PessoaBean) GenericBean
					.getSessionValue("pessoaBean");

			Servidor gestor = new Servidor();
			gestor.setPessoaId(pessoaBean.getPessoaId());
			this.instituicaoFinanciadora.setCadastrador(gestor);

			// Cadastrar uma nova Insituição Financeira.
			response = service
					.cadastrarInstituicao(this.instituicaoFinanciadora);

			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {

				// Cadastro realizado com sucesso.
				GenericBean.setMessage(
						"info.sucessoCadastroInstituicaoFinanciadora",
						FacesMessage.SEVERITY_INFO);
				GenericBean
						.resetSessionScopedBean("editarInstituicaoFinanciadoraBean");

			} else if (statusCode == HttpStatus.SC_NOT_ACCEPTABLE) {

				// Problema com os dados enviados. Recuperar mensagem do
				// serviço.
				Erro erroResponse = response.readEntity(Erro.class);
				GenericBean.setMessage(erroResponse.getMensagem(),
						FacesMessage.SEVERITY_ERROR);

			} else {

				// Http Code: 304. Não modificado.
				GenericBean.setMessage("erro.cadastroInstituicaoFinanciadora",
						FacesMessage.SEVERITY_ERROR);
			}

		} else {

			// Atualização da InsTituição Financiadora.
			response = service
					.editarInstituicaoFinanciadora(instituicaoFinanciadora);
			
			this.menuModel = BreadCrumb.detalhesInstituicaoFinanciadora(true);

			GenericBean
					.sendRedirect(PathRedirect.exibirInstituicaoFinanciadora);
		}
	}

	public void createEdit(InstituicaoFinanciadora instituicao) {

		if (instituicao == null) {

			GenericBean
					.resetSessionScopedBean("editarInstituicaoFinanciadoraBean");

			MenuModel menuModel = BreadCrumb
					.cadastrarInstituicaoFinanciadora(true);
			EditarInstituicaoFinanciadoraBean editarInstituicaoFinanciadoraBean = new EditarInstituicaoFinanciadoraBean(
					menuModel);

			GenericBean.setSessionValue("editarInstituicaoFinanciadoraBean",
					editarInstituicaoFinanciadoraBean);

		} else {

			Response response = service.consultarInstituicao(instituicao
					.getIdInstituicaoFinanciadora());

			this.instituicaoFinanciadora = response
					.readEntity(new GenericType<InstituicaoFinanciadora>() {
					});
			
			this.menuModel = BreadCrumb.editarInstituicaoFinanciadora(true);

		}

		GenericBean.sendRedirect(PathRedirect.cadastrarInstituicaoFinanciadora);
	}

	public String lancarRecurso(InstituicaoFinanciadora instituicaoFinanciadora) {

		RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora = new RecursoInstituicaoFinanciadora();
		recursoInstituicaoFinanciadora
				.setInstituicaoFinanciadora(instituicaoFinanciadora);

		this.recursoInstituicaoFinanciadora = recursoInstituicaoFinanciadora;

		return PathRedirect.lancarRecursoInstituicaoFinanciadora;
	}

	public void lancarRecurso() {

		Response response = null;

		PessoaBean pessoaBean = (PessoaBean) GenericBean
				.getSessionValue("pessoaBean");
		int idPessoa = pessoaBean.getPessoaId();

		recursoInstituicaoFinanciadora.getCadastrador().setPessoaId(idPessoa);

		response = service
				.cadastrarRecursoInstituicao(recursoInstituicaoFinanciadora);

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			// Cadastro realizado com sucesso.
			GenericBean.setMessage("info.sucessoLancamentoOrcamento",
					FacesMessage.SEVERITY_INFO);
			GenericBean
					.resetSessionScopedBean("editarInstituicaoFinanciadoraBean");

		} else if (statusCode == HttpStatus.SC_NOT_ACCEPTABLE) {

			// Problema com os dados enviados. Recuperar mensagem do serviço.
			Erro erroResponse = response.readEntity(Erro.class);
			GenericBean.setMessage(erroResponse.getMensagem(),
					FacesMessage.SEVERITY_ERROR);

		} else {

			// Http Code: 304. Não modificado.
			GenericBean.setMessage("erro.lancamentoOrcamento",
					FacesMessage.SEVERITY_ERROR);
		}

	}

	public InstituicaoFinanciadora getInstituicaoFinanciadora() {
		return instituicaoFinanciadora;
	}

	public void setInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
	}

	public RecursoInstituicaoFinanciadora getRecursoInstituicaoFinanciadora() {
		return recursoInstituicaoFinanciadora;
	}

	public void setRecursoInstituicaoFinanciadora(
			RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora) {
		this.recursoInstituicaoFinanciadora = recursoInstituicaoFinanciadora;
	}

	public List<RecursoInstituicaoFinanciadora> getRecursosInstituicaoFinanciadora()
			throws SQLException {
		if (recursosInstituicaoFinanciadora == null) {
			return this.recursosInstituicaoFinanciadora = service
					.consultarRecursosInstituicaoFinanciadora(instituicaoFinanciadora);
		}

		return recursosInstituicaoFinanciadora;
	}

	public void setRecursosInstituicaoFinanciadora(
			List<RecursoInstituicaoFinanciadora> recursosInstituicaoFinanciadora) {
		this.recursosInstituicaoFinanciadora = recursosInstituicaoFinanciadora;
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}
}
