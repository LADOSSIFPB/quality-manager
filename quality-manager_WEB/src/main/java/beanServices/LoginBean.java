package beanServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Coordenador;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;

@ManagedBean
@SessionScoped
public class LoginBean {

	private Login login;
	private Pessoa pessoa;

	public LoginBean() {
		this.login = new Login();
	}

	public String fazerLogin() {

		String nextPage = null;

		Response response = loginService(login);

		int status = response.getStatus();

		// TODO: Dúvida se criar uma classe SessionScoped para cada usuário é
		// mais viável.
		// Ou seja, esta classe faz a busca e envia o objeto pronto para uma
		// classe SessionScoped
		if (status == Status.OK.getStatusCode()) {

			// TODO: desenrolar essa questão.
			// É importante ressaltar que, se colocarmos classecorreta.class
			// esse comando funciona.
			// Voltar Usuário talvez resolva nosso problema. O que vocês acham?
			pessoa = (Pessoa) response.readEntity(Pessoa.class);

			int tipoPessoa = pessoa.getTipoPessoa().getIdTipoPessoa();

			// Felipe, depois que eu colocar Gestor e Coordenador a gente
			// diferencia essas constantes
			if (tipoPessoa == TipoPessoa.TIPO_ORIENTADOR) {
				Orientador orientador = (Orientador) pessoa;
				nextPage = "indexOrientador";
			} else if (tipoPessoa == TipoPessoa.TIPO_DISCENTE) {
				Discente discente = (Discente) pessoa;
				nextPage = "indexDiscente";
			} else if (tipoPessoa == TipoPessoa.TIPO_COORDENADOR) {
				Coordenador coordenador = (Coordenador) pessoa;
				nextPage = "indexGestor";
			}

		}

		// TODO: Tratar redirecionamento
		return nextPage;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Response loginService(Login login) {

		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		Response response = service.fazerLogin(login);

		return response;
	}
}