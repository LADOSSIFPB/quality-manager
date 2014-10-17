package beanServices;

import java.util.Enumeration;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import managedBean.PessoaBean;
import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Pessoa;

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
			pessoa = response.readEntity(Pessoa.class);
			
			//TODO: Finalizando o teste. A página será redirecionada e os dados das entidades 
			// Orientador, Discente e Coordenador serão buscados.
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("pessoa", pessoa);
			
			PessoaBean pessoaBean = new PessoaBean(pessoa);			
			
			HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
			Enumeration e = session.getAttributeNames();
			System.out.println(e);
			
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