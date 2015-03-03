package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.TipoParticipacao;

@ManagedBean
@RequestScoped
public class ParticipacaoBean extends GenericBean implements BeanInterface {

	private Participacao participacao = new Participacao();
	private List<SelectItem> tiposParticipacoes;

	@Override
	public void save() {
		Response response = service.cadastrarParticipacao(participacao);

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

		List<TipoParticipacao> altp = service.listarTiposParticipacao();

		ArrayList<SelectItem> alsi = new ArrayList<SelectItem>();

		if (!altp.isEmpty()) {

			for (TipoParticipacao tipoParticipacao : altp) {
				SelectItem si = new SelectItem();
				si.setValue(tipoParticipacao.getIdTipoParticipacao());
				si.setLabel(tipoParticipacao.getNomeTipoParticipacao());
				alsi.add(si);
			}
		} else {
			System.err.println("Erro!");
		}

		tiposParticipacoes = alsi;

		return tiposParticipacoes;
	}

	public void setTiposParticipacoes(List<SelectItem> tiposParticipacoes) {
		this.tiposParticipacoes = tiposParticipacoes;
	}
}
