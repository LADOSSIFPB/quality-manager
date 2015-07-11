package beanServices;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.Response;

import managedBean.GenericBean;
import managedBean.PathRedirect;
import managedBean.PessoaBean;

import org.apache.http.HttpStatus;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;

@ManagedBean(name="loginBean")
@ViewScoped
public class LoginBean {

	private Login login;
	
	private Pessoa pessoa;

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

		Response response = loginService(login);

		int status = response.getStatus();

		if (status == HttpStatus.SC_ACCEPTED) {

			Pessoa pessoa = response.readEntity(Pessoa.class);

			if (pessoa.getTipoPessoa() == TipoPessoa.DISCENTE) {

				// Buscar discente.
				Discente discente = buscarDiscente(pessoa.getPessoaId());

				GenericBean.setSessionValue("pessoaBean", new PessoaBean(
						discente));

				pageRedirect = PathRedirect.indexDiscente;

			} else if (pessoa.getTipoPessoa() == TipoPessoa.SERVIDOR) {

				// Buscar servidor
				Servidor servidor = buscarServidor(pessoa.getPessoaId());

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
			
		} else {
			Erro erro = response.readEntity(Erro.class);
			
			GenericBean.setMessage(erro.getMensagem(),
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

		// Finalizando sessão para o usuário logado.
		GenericBean.invalidateSession();
		String sendRedirect = PathRedirect.index
				+ "?faces-redirect=true&includeViewParams=true";

		// Redirecionar para a página de login.
		GenericBean.sendRedirect(sendRedirect);

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

	private Servidor buscarServidor(int pessoaId) {

		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		Response response = service.consultarServidor(pessoaId);

		Servidor servidor = response.readEntity(Servidor.class);

		return servidor;
	}

	private Discente buscarDiscente(int pessoaId) {

		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		Response response = service.consultarDiscente(pessoaId);

		Discente discente = response.readEntity(Discente.class);

		return discente;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}