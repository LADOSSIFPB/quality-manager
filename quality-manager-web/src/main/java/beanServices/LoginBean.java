package beanServices;

import java.io.Serializable;
import java.security.Principal;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import managedBean.GenericBean;
import managedBean.PathRedirect;
import managedBean.PessoaBean;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.ProviderServiceFactory;
import service.QManagerService;
import util.CookieHelper;
import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;

@ManagedBean(name="loginBean")
@ViewScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 5758816338721773827L;

	private Login login;
	
	private Pessoa pessoa;
	
	private boolean manterLogin;
	
	private static final Logger logger = LogManager
			.getLogger(LoginBean.class);

	public LoginBean() {
		this.login = new Login();
	}

	/**
	 * Login do usuário.
	 * 
	 * @return
	 */
	public String fazerLogin() {

		String pageRedirect = null;
		
		// Get the current servlet request from the facesContext
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) 
				ctx.getExternalContext().getRequest();

		try {			
	
			// Do login from the container (will call login module)
			request.login(login.getIdentificador(), login.getSenha());
			Principal principal = request.getUserPrincipal();
		
			Response response = loginService(login);
	
			int status = response.getStatus();
			
			if (status == HttpStatus.SC_ACCEPTED) {
	
				Pessoa pessoa = response.readEntity(Pessoa.class);
				String authorizationKey = pessoa.getAuthorizationKey();
				
				if (manterLogin) {
			        String uuid = UUID.randomUUID().toString();
			        CookieHelper.setCookie("login", uuid, CookieHelper.SECONDS_PER_YEAR);
			    }
	
				if (pessoa.getTipoPessoa().getIdTipoPessoa() 
						== TipoPessoa.TIPO_DISCENTE) {
	
					// Buscar discente.
					Discente discente = buscarDiscente(pessoa.getPessoaId(), pessoa
							.getTipoPessoa().getIdTipoPessoa());
					discente.setAuthorizationKey(authorizationKey);
	
					GenericBean.setSessionValue("pessoaBean", new PessoaBean(
							discente));
	
					pageRedirect = PathRedirect.indexDiscente;
	
				} else if (pessoa.getTipoPessoa().getIdTipoPessoa() 
						== TipoPessoa.TIPO_SERVIDOR) {
	
					// Buscar servidor
					Servidor servidor = buscarServidor(pessoa.getPessoaId(), pessoa
							.getTipoPessoa().getIdTipoPessoa());
					servidor.setAuthorizationKey(authorizationKey);
	
					GenericBean.setSessionValue("pessoaBean", new PessoaBean(
							servidor));
	
					int tipoServidor = servidor.getCargoServidor()
							.getIdCargoServidor();
	
					// Redirecionar para a página do servidor.
					if (tipoServidor == CargoServidor.GESTOR) {
	
						pageRedirect = PathRedirect.indexGestor;
	
					} else if (tipoServidor == CargoServidor.COORDENADOR) {
	
						pageRedirect = PathRedirect.indexCoordenador;
	
					} else if (tipoServidor == CargoServidor.PROFESSOR) {
	
						pageRedirect = PathRedirect.indexDocente;
					}
				}
	
				GenericBean.sendRedirect(pageRedirect);
				
			} 
		
		} catch (ServletException se) {
			
			GenericBean.setMessage("erro.usuarioInvalido",
					FacesMessage.SEVERITY_ERROR);
		}
		
		return pageRedirect;
	}

	/**
	 * Encerrar login(sessão) do usuário.s
	 * 
	 * @return
	 */
	public void logout() {

		String sendRedirect = PathRedirect.index
				+ "?faces-redirect=true&includeViewParams=true";

		// Get the current servlet request from the facesContext
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) ctx
				.getExternalContext().getRequest();

		try {
			
			request.logout();

			// Finalizando sessão para o usuário logado.
			GenericBean.invalidateSession();

			CookieHelper cookieHelper = new CookieHelper();
			cookieHelper.eraseCookie();

		} catch (ServletException e) {
			
			logger.error(e);
			
		} finally {
			
			// Redirecionar para a página de login.
			GenericBean.sendRedirect(sendRedirect);
		}
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

		Response response = service.logarPessoa(login);

		return response;
	}

	private Servidor buscarServidor(int pessoaId, int idTipoPessoa) {

		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		Response response = service.consultarPessoaPorTipo(pessoaId,
				idTipoPessoa);

		Servidor servidor = response.readEntity(Servidor.class);

		return servidor;
	}

	private Discente buscarDiscente(int pessoaId, int idTipoPessoa) {

		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		Response response = service.consultarPessoaPorTipo(pessoaId,
				idTipoPessoa);

		Discente discente = response.readEntity(Discente.class);

		return discente;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public boolean isManterLogin() {
		return manterLogin;
	}

	public void setManterLogin(boolean manterLogin) {
		this.manterLogin = manterLogin;
	}
}