package managedBean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.chat.Conversa;
import br.edu.ifpb.qmanager.chat.Mensagem;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.Pessoa;

@ManagedBean
@ViewScoped
public class ChatBean implements Serializable {

	private List<Mensagem> mensagens;

	private Mensagem mensagem;

	private Conversa conversa;
	
	private List<Conversa> conversas;

	public ChatBean() throws SQLException {

		conversa = new Conversa();

		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		mensagens = null;
		conversas = new LinkedList<Conversa>();
		
		Pessoa remetente = consultarRemetente();

		this.conversas = service.consultarConversasPorPessoa(remetente);

	}

	public void sendMessage() throws SQLException {

		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		this.mensagem.setConversa(conversa);
		Response response = service.cadastrarMensagem(mensagem);

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			mensagens = new LinkedList<Mensagem>();
			this.mensagens = ConversaBean.consultarConversas(conversa);
			this.mensagem = novaMensagem();

		} else if (statusCode == HttpStatus.SC_NOT_ACCEPTABLE) {

			// Problema com os dados enviados. Recuperar mensagem do
			// serviço.
			Erro erroResponse = response.readEntity(Erro.class);
			GenericBean.setMessage(erroResponse.getMensagem(),
					FacesMessage.SEVERITY_ERROR);

		} else {

			// Http Code: 304. Não modificado.
			GenericBean.setMessage("erro.cadastroInstituicaoFinanciadora",
					FacesMessage.SEVERITY_ERROR);
		}

	}

	public void novaConversa() {

		GenericBean.sendRedirect(PathRedirect.novaConversa);
	}

	public List<Pessoa> pesquisarPessoas(String query) {

		Pessoa pessoa = new Pessoa();
		pessoa.setNomePessoa(query);

		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		List<Pessoa> membros = service.consultarPessoas(pessoa);

		return membros;
	}

	public void onRowSelect(SelectEvent event) throws SQLException {

		Conversa conversa = ((Conversa) event.getObject());
		
		mensagem = novaMensagem();
		this.conversa = conversa;

		this.mensagens = ConversaBean.consultarConversas(conversa);

	}

	public Mensagem novaMensagem() {
		
		Pessoa remetente = consultarRemetente();

		Mensagem mensagem = new Mensagem();
		mensagem.setConversa(conversa);
		mensagem.setRemetente(remetente);

		return mensagem;
	}
	
	public Pessoa consultarRemetente(){
		
		Pessoa remetente = new Pessoa();
		
		remetente = (PessoaBean) GenericBean.getSessionValue("pessoaBean");
		
		return remetente;
		
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Car Unselected",
				((Conversa) event.getObject()).getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public boolean mensagemNull() {
		if (this.mensagem != null)
			return false;
		else
			return true;
	}

	public List<Conversa> getConversas() {
		return conversas;
	}

	public void setConversas(List<Conversa> conversas) {
		this.conversas = conversas;
	}

	public Conversa getConversa() {
		return conversa;
	}

	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

}
