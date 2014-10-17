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
			
			pessoa = response.readEntity(Pessoa.class);
			
			FacesContext context = FacesContext.getCurrentInstance();
			// Incluindo variável na sessão.
			PessoaBean pessoaBean = new PessoaBean(pessoa);		
			context.getExternalContext().getSessionMap().put("pessoaBean", pessoaBean);		
				
			// Exemplo de recuperação de variável na sessão. 
			// Essa operação poderá ser realizada em qualquer outro Bean que desejar
			// ter acesso à PessoaBean.
			HttpSession session = (HttpSession) context.getExternalContext().getSession(false);			
			pessoaBean = (PessoaBean) session.getAttribute("pessoaBean");
			System.out.println(pessoaBean);
		} else {
			// Controlar as mensagens de erro que devem ser colocadas num objeto de sessão. 
		}

		// Na página redirecionada capturar dados específicos do tipo de usuário.
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