package managedBean;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.chat.Conversa;
import br.edu.ifpb.qmanager.chat.Mensagem;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.Pessoa;

@ManagedBean
@RequestScoped
public class ConversaBean {
	
	private static QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);
	
	private Conversa conversa;
	private List<Pessoa> pessoas;
	private Pessoa remetente;
	
	public ConversaBean() {
		this.setConversa(new Conversa());
	}
	
	public Response criarChat(Conversa conversa) {

		Response response = service.cadastrarConversa(conversa);

		return response;
	}
	
	public List<Pessoa> pesquisarPessoas(String query) {

		Pessoa pessoa = new Pessoa();
		pessoa.setNomePessoa(query);

		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		List<Pessoa> membros = service.consultarPessoas(pessoa);

		return membros;
	}
	
	public static List<Mensagem> consultarConversas(Conversa conversa) throws SQLException{
		
		List<Mensagem> mensagens = service.consultarMensagensPorConversa(conversa);
		
		return mensagens;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Pessoa getRemetente() {
		return remetente;
	}

	public void setRemetente(Pessoa remetente) {
		this.remetente = remetente;
	}

	public Conversa getConversa() {
		return conversa;
	}

	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
	}


}
