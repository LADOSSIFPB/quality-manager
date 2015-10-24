package managedBean;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

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

	private int INSTITUICAO_NAO_CADASTRADA = 0;

	public EditarInstituicaoFinanciadoraBean() {
		this(new InstituicaoFinanciadora());
	}

	public EditarInstituicaoFinanciadoraBean(
			RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora) {
		this.recursoInstituicaoFinanciadora = recursoInstituicaoFinanciadora;
	}

	public EditarInstituicaoFinanciadoraBean(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
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

			GenericBean
					.sendRedirect(PathRedirect.exibirInstituicaoFinanciadora);
		}
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
	}
	
	public void voltar() {
		GenericBean.sendRedirect(PathRedirect.instituicaoFinanciadora);
	}
	
	public void voltarExibirInstituicao() {
		GenericBean.sendRedirect(PathRedirect.exibirInstituicaoFinanciadora);
	}

	public void resetSession(
			EditarInstituicaoFinanciadoraBean editarInstituicaoFinanciadoraBean) {

		GenericBean.resetSessionScopedBean("editarInstituicaoFinanciadoraBean");

		GenericBean.setSessionValue("editarInstituicaoFinanciadoraBean",
				editarInstituicaoFinanciadoraBean);
	}

	public void createEdit(InstituicaoFinanciadora instituicao) {

		if (instituicao == null) {

			EditarInstituicaoFinanciadoraBean editarInstituicaoFinanciadoraBean = new EditarInstituicaoFinanciadoraBean();

			resetSession(editarInstituicaoFinanciadoraBean);

		} else {

			Response response = service.consultarInstituicao(instituicao
					.getIdInstituicaoFinanciadora());

			// Código de resposta do serviço.
			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {

				// Http Code: 200. Inst. Financiadora recuperada com sucesso.
				InstituicaoFinanciadora instituicaoFinanciadoraResponse = response
						.readEntity(InstituicaoFinanciadora.class);

				// Edital encontrado.
				this.instituicaoFinanciadora = instituicaoFinanciadoraResponse;

			} else {

				// Http Code: 404. Inst. Financiadora inexistente.
				Erro erroResponse = response.readEntity(Erro.class);
				GenericBean.setMessage("erro.programaInstitucionalInexistente",
						FacesMessage.SEVERITY_ERROR);
			}
		}

		GenericBean.sendRedirect(PathRedirect.cadastrarInstituicaoFinanciadora);
	}

	public void lancarRecurso(InstituicaoFinanciadora instituicaoFinanciadora) {

		RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora = 
				new RecursoInstituicaoFinanciadora();
		recursoInstituicaoFinanciadora
				.setInstituicaoFinanciadora(instituicaoFinanciadora);

		this.recursoInstituicaoFinanciadora = recursoInstituicaoFinanciadora;

		GenericBean.sendRedirect(
				PathRedirect.lancarRecursoInstituicaoFinanciadora);
	}

	public void lancarRecurso() {

		Response response = null;

		PessoaBean pessoaBean = (PessoaBean) GenericBean
				.getSessionValue("pessoaBean");
		int idPessoa = pessoaBean.getPessoaId();

		recursoInstituicaoFinanciadora.getCadastrador().setPessoaId(idPessoa);

		response = service.cadastrarRecursoInstituicao(
				recursoInstituicaoFinanciadora);

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			// Cadastro realizado com sucesso.
			GenericBean.setMessage("info.sucessoLancamentoOrcamento",
					FacesMessage.SEVERITY_INFO);

			//TODO: Revisar código.
			RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadoraNovaSessao = 
					new RecursoInstituicaoFinanciadora();
			recursoInstituicaoFinanciadoraNovaSessao.setInstituicaoFinanciadora(
					this.recursoInstituicaoFinanciadora.getInstituicaoFinanciadora());
			EditarInstituicaoFinanciadoraBean editarInstituicaoFinanciadoraBean = 
					new EditarInstituicaoFinanciadoraBean(
					recursoInstituicaoFinanciadoraNovaSessao);

			GenericBean.resetSessionScopedBean(
					"editarInstituicaoFinanciadoraBean");
			GenericBean.setSessionValue("editarInstituicaoFinanciadoraBean", 
					editarInstituicaoFinanciadoraBean);


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
}
