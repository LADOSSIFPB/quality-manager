package beanServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean {
	
	private boolean remember;
	private Login login;
	private Usuario user;
	
	public LoginBean() {
		this.login = new Login();
	}

	public String fazerLogin() {

		String nextPage = null;
		
		Response response = loginService(user);
		
		int status = response.getStatus();		
		
		if (status == Status.OK.getStatusCode()) {
			Usuario entity = (Usuario) response.getEntity();
		}

		return nextPage;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	public Response loginService(Usuario usuario) {

		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);		
		
		Response response = service.fazerLogin(usuario);

		return response;
	}	
}
