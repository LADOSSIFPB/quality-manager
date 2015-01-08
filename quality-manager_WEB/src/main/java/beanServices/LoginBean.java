package beanServices;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

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

		String pageRedirect = null;
		
		Response response = loginService(login);

		int status = response.getStatus();

		if (status == HttpStatus.SC_ACCEPTED) {

			Pessoa pessoa = response.readEntity(Pessoa.class);
			
			if (pessoa.getTipoPessoa().getIdTipoPessoa() 
					== TipoPessoa.TIPO_DISCENTE) {

				// Buscar discente.
				Discente discente = buscarDiscente(pessoa.getPessoaId(), 
						pessoa.getTipoPessoa().getIdTipoPessoa());

				GenericBean.setSessionValue("pessoaBean", new PessoaBean(
						discente));

				pageRedirect = PathRedirect.indexDiscente;

			} else if (pessoa.getTipoPessoa().getIdTipoPessoa() 
					== TipoPessoa.TIPO_SERVIDOR) {

				// Buscar servidor				
				Servidor servidor = buscarServidor(
						pessoa.getPessoaId(), 
						pessoa.getTipoPessoa().getIdTipoPessoa());

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

		} else {
			GenericBean.setMessage("erro.usuarioInvalido", 
					FacesMessage.SEVERITY_ERROR);
		}
		
		return pageRedirect;
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
	
	private Servidor buscarServidor(int pessoaId, int idTipoPessoa) {
		
		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		Response response = service.consultarPessoaPorTipo(
				pessoaId, idTipoPessoa);

		Servidor servidor = response.readEntity(Servidor.class);
		
		return servidor;		
	}
	
	private Discente buscarDiscente(int pessoaId, int idTipoPessoa) {
		
		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		Response response = service.consultarPessoaPorTipo(
				pessoaId, idTipoPessoa);

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