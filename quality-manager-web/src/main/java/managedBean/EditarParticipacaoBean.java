package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.TipoParticipacao;

@ManagedBean(name = "editarParticipacaoBean")
@SessionScoped
public class EditarParticipacaoBean implements EditarBeanInterface {

	private int stepDadosProjeto = 2;
	
	private Participacao participacao;
	
	private List<Participacao> participacoes;
	
	private List<SelectItem> tiposParticipacoes;
	
	private Projeto projeto;	
	
	private int PARTICIPACAO_NAO_CADASTRADA = 0;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);
	
	public EditarParticipacaoBean(){
		this.participacao = new Participacao();
	}
	
	public EditarParticipacaoBean(Projeto projeto) {
		this.participacao = new Participacao();
		this.projeto = projeto;
	}

	@Override
	public void save() {
		
		Response response;

		if (participacao != null 
				&& participacao.getIdParticipacao() == PARTICIPACAO_NAO_CADASTRADA) {

			// Participação no Projeto.
			this.participacao.setProjeto(projeto);
			
			response = service.cadastrarParticipacao(participacao);

			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {								
				
				// Remover registros anteriores da sessão.
				GenericBean.resetSessionScopedBean("editarParticipacaoBean");
				
				EditarParticipacaoBean editarParticipacaoBean = 
						new EditarParticipacaoBean(projeto);				
				// Reinicializar a participação.
				GenericBean.setSessionValue("participacaoBean", 
						editarParticipacaoBean);	
				
				GenericBean.setMessage("info.sucessoCadastroMembroProjeto",
						FacesMessage.SEVERITY_INFO);

			} else {

				// Http Code: 406. Não aceitável.
				Erro erroResponse = response.readEntity(Erro.class);
				GenericBean.setMessage("erro.cadastroMembroProjeto",
						FacesMessage.SEVERITY_ERROR);
			}

		} else {

			// Atualização da Participação.			
			GenericBean.sendRedirect(PathRedirect.index);
		}
	}	
	
	public void saveFinish() {
		
		Response response = service.cadastrarParticipacao(participacao);

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			GenericBean.resetSessionScopedBean("participacaoBean");
			GenericBean.sendRedirect(PathRedirect.projeto);

		} else {

			// Http Code: 304. Não modificado.
			Erro erroResponse = response.readEntity(Erro.class);
			GenericBean.setMessage("erro.cadastroMembroProjeto",
					FacesMessage.SEVERITY_ERROR);
		}
		
	}
	
	public void finish(){
		GenericBean.resetSessionScopedBean("participacaoBean");
		GenericBean.sendRedirect(PathRedirect.projeto);
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

		if (this.tiposParticipacoes == null) {
			List<TipoParticipacao> tiposParticipacao = service
					.listarTiposParticipacao();

			this.tiposParticipacoes = new ArrayList<SelectItem>();

			for (TipoParticipacao tipoParticipacao : tiposParticipacao) {
				SelectItem selectItem = new SelectItem();
				selectItem.setValue(tipoParticipacao.getIdTipoParticipacao());
				selectItem.setLabel(tipoParticipacao.getNomeTipoParticipacao());

				this.tiposParticipacoes.add(selectItem);
			}

			return this.tiposParticipacoes;
		}

		return this.tiposParticipacoes;

	}

	public void setTiposParticipacoes(List<SelectItem> tiposParticipacoes) {
		this.tiposParticipacoes = tiposParticipacoes;
	}

	public int getStepDadosProjeto() {
		return stepDadosProjeto;
	}

	public void setStepDadosProjeto(int stepDadosProjeto) {
		this.stepDadosProjeto = stepDadosProjeto;
	}

	public List<Participacao> getParticipacoes() {
		return participacoes;
	}

	public void setParticipacoes(List<Participacao> participacoes) {
		this.participacoes = participacoes;
	}

	public Projeto getProjeto() {
		return projeto;
	}
}
