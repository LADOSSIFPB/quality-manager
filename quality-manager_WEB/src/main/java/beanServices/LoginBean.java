package beanServices;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.Response;

import managedBean.GenericBean;
import managedBean.PathRedirect;
import managedBean.PessoaBean;
import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.Servidor;

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

		if (status == PathRedirect.STATUS_DISCENTE
				|| status == PathRedirect.STATUS_SERVIDOR) {

			if (status == PathRedirect.STATUS_DISCENTE) {

				setPessoa(response.readEntity(Discente.class));

				FacesContext context = FacesContext.getCurrentInstance();

				GenericBean.setSessionValue("pessoaBean", new PessoaBean(
						getPessoa()));

				try {
					externalContext.redirect(PathRedirect.indexDiscente);
				} catch (IOException e) {
					// TODO Tratar excessão caso a página não exista
					e.printStackTrace();
				}

			} else {

				setPessoa(response.readEntity(Servidor.class));

				FacesContext context = FacesContext.getCurrentInstance();

				GenericBean.setSessionValue("pessoaBean", new PessoaBean(
						getPessoa()));

				Servidor servidor = (Servidor) getPessoa();
				int tipoServidor = servidor.getCargoServidor()
						.getIdCargoServidor();

				if (tipoServidor == CargoServidor.GESTOR) {

					try {
						externalContext.redirect(PathRedirect.indexGestor);
					} catch (IOException e) {
						// TODO Tratar excessão caso a página não exista
						e.printStackTrace();
					}
				} else if (tipoServidor == CargoServidor.COORDENADOR) {

					try {
						externalContext.redirect(PathRedirect.indexCoordenador);
					} catch (IOException e) {
						// TODO Tratar excessão caso a página não exista
						e.printStackTrace();
					}
				} else if (tipoServidor == CargoServidor.PROFESSOR) {

					try {
						externalContext.redirect(PathRedirect.indexDocente);
					} catch (IOException e) {
						// TODO Tratar excessão caso a página não exista
						e.printStackTrace();
					}
				}

			}

		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!",
							"Email/Senha Incorretos."));
		}

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