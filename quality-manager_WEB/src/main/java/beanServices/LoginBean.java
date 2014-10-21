package beanServices;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import managedBean.PathRedirect;
import managedBean.PessoaBean;
import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Login;
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

	public void fazerLogin() {

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();

		Response response = loginService(login);

		int status = response.getStatus();

		// TODO: Dúvida se criar uma classe SessionScoped para cada usuário é
		// mais viável.
		// Ou seja, esta classe faz a busca e envia o objeto pronto para uma
		// classe SessionScoped
		if (status == Status.OK.getStatusCode()) {

			setPessoa(response.readEntity(Pessoa.class));

			FacesContext context = FacesContext.getCurrentInstance();
			// Incluindo variável na sessão.
			PessoaBean pessoaBean = new PessoaBean(getPessoa());
			context.getExternalContext().getSessionMap()
					.put("pessoaBean", pessoaBean);

			// Exemplo de recuperação de variável na sessão.
			// Essa operação poderá ser realizada em qualquer outro Bean que
			// desejar
			// ter acesso à PessoaBean.
			HttpSession session = (HttpSession) context.getExternalContext()
					.getSession(false);
			pessoaBean = (PessoaBean) session.getAttribute("pessoaBean");

			if (getPessoa().getTipoPessoa().getIdTipoPessoa() == TipoPessoa.TIPO_ORIENTADOR) {
				try {
					externalContext.redirect(PathRedirect.indexOrientador);
				} catch (IOException e) {
					// TODO Tratar excessão caso a página não exista
					e.printStackTrace();
				}
			}

			else if (getPessoa().getTipoPessoa().getIdTipoPessoa() == TipoPessoa.TIPO_DISCENTE) {
				try {
					externalContext.redirect(PathRedirect.indexDiscente);
				} catch (IOException e) {
					// TODO Tratar excessão caso a página não exista
					e.printStackTrace();
				}
			}

			else if (getPessoa().getTipoPessoa().getIdTipoPessoa() == TipoPessoa.TIPO_GESTOR) {
				try {
					externalContext.redirect(PathRedirect.indexGestor);
				} catch (IOException e) {
					// TODO Tratar excessão caso a página não exista
					e.printStackTrace();
				}
			}

			else if (getPessoa().getTipoPessoa().getIdTipoPessoa() == TipoPessoa.TIPO_COORDENADOR) {
				try {
					externalContext.redirect(PathRedirect.indexGestor);
				} catch (IOException e) {
					// TODO Tratar excessão caso a página não exista
					e.printStackTrace();
				}
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
							"Email/Senha Incorretos."));
		}

		// Na página redirecionada capturar dados específicos do tipo de
		// usuário.
	}
	
	public void logout() {
		
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();

		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		
		try {
			externalContext.redirect(PathRedirect.index);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		Response response = service.fazerLogin(login);

		return response;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}