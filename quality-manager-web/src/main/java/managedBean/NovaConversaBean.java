package managedBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.chat.Conversa;
import br.edu.ifpb.qmanager.chat.Mensagem;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.Pessoa;

@ManagedBean
@ViewScoped
public class NovaConversaBean {

	QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private Conversa conversa;
	private Mensagem mensagem;

	public NovaConversaBean() {
		conversa = new Conversa();
		mensagem = new Mensagem();
	}

	public void novaConversa() {
		
		Pessoa remetente = remetente();
		
		conversa.getPessoas().add(remetente);
		
		Response reponseConversa = service.cadastrarConversa(conversa);

		int statusCode = reponseConversa.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			Conversa conversaBD = reponseConversa.readEntity(Conversa.class);

			mensagem.setConversa(conversaBD);
			mensagem.setRemetente(remetente);

			Response responseMensagem = service.cadastrarMensagem(mensagem);

			int statusMensagem = responseMensagem.getStatus();

			if (statusMensagem == HttpStatus.SC_OK) {

				GenericBean.sendRedirect(PathRedirect.mensagens);

			} else if (statusMensagem == HttpStatus.SC_NOT_ACCEPTABLE) {

				// Problema com os dados enviados. Recuperar mensagem do
				// serviço.
				Erro erroResponse = responseMensagem.readEntity(Erro.class);
				GenericBean.setMessage(erroResponse.getMensagem(),
						FacesMessage.SEVERITY_ERROR);

			} else {

				// Http Code: 304. Não modificado.
				GenericBean.setMessage("erro.cadastroMensagem",
						FacesMessage.SEVERITY_ERROR);

			}
			
		} else if (statusCode == HttpStatus.SC_NOT_ACCEPTABLE) {

			// Problema com os dados enviados. Recuperar mensagem do
			// serviço.
			Erro erroResponse = reponseConversa.readEntity(Erro.class);
			GenericBean.setMessage(erroResponse.getMensagem(),
					FacesMessage.SEVERITY_ERROR);

		} else {

			// Http Code: 304. Não modificado.
			GenericBean.setMessage("erro.cadastroConversa",
					FacesMessage.SEVERITY_ERROR);
		}

	}
	
	public Pessoa remetente(){
		
		Pessoa pessoa = (Pessoa) GenericBean.getSessionValue("pessoaBean");
		
		return pessoa;
	}
	
	public List<Pessoa> pesquisarPessoas(String query) {

		Pessoa pessoa = new Pessoa();
		pessoa.setNomePessoa(query);

		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		List<Pessoa> membros = service.consultarPessoas(pessoa);

		return membros;
	}

	public Conversa getConversa() {
		return conversa;
	}

	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

}
