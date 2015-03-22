package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.TipoParticipacao;

@ManagedBean
@SessionScoped
public class ParticipacaoBean extends GenericBean implements BeanInterface {

	private Participacao participacao = new Participacao();
	private List<SelectItem> tiposParticipacoes;

	@Override
	public void save() {
		Response response = service.cadastrarParticipacao(participacao);
		
		int statusCode = response.getStatus();
		
		if (statusCode == HttpStatus.SC_OK) {

			GenericBean.setMessage("info.sucessoCadastroMembroProjeto",
					FacesMessage.SEVERITY_INFO);
			GenericBean.resetSessionScopedBean("participacaoBean");

		} else {

			// Http Code: 304. Não modificado.
			Erro erroResponse = response.readEntity(Erro.class);
			GenericBean.setMessage("erro.cadastroMembroProjeto",
					FacesMessage.SEVERITY_ERROR);
		}

	}

	public String adicionarMembroProjeto(Projeto projeto) {

		this.participacao.setProjeto(projeto);
		return PathRedirect.adicionarMembroProjeto;

	}

	public List<Pessoa> completeMembroProjeto(String query) {

		Pessoa pessoa = new Pessoa();
		pessoa.setNomePessoa(query);

		List<Pessoa> membros = service.consultarPessoas(pessoa);

		return membros;
	}

	public Participacao getParticipacao() {
		return participacao;
	}

	public void setParticipacao(Participacao participacao) {
		this.participacao = participacao;
	}

	public List<SelectItem> getTiposParticipacoes() {

		List<TipoParticipacao> tiposParticipacao = service.listarTiposParticipacao();

		this.tiposParticipacoes = new ArrayList<SelectItem>();

		for (TipoParticipacao tipoParticipacao : tiposParticipacao) {
			SelectItem selectItem = new SelectItem();
			selectItem.setValue(tipoParticipacao.getIdTipoParticipacao());
			selectItem.setLabel(tipoParticipacao.getNomeTipoParticipacao());
			
			this.tiposParticipacoes.add(selectItem);
		}

		return this.tiposParticipacoes;
	}

	public void setTiposParticipacoes(List<SelectItem> tiposParticipacoes) {
		this.tiposParticipacoes = tiposParticipacoes;
	}
}
