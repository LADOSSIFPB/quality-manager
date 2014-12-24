package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.MembroProjeto;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.TipoParticipacao;
import br.edu.ifpb.qmanager.util.PalavraUtil;

@ManagedBean
@RequestScoped
public class ParticipacaoBean extends GenericBean implements BeanInterface {

	private Participacao participacao = new Participacao();
	private List<SelectItem> tiposParticipacoes;

	@Override
	public void save() {
		System.out.println();
		Response response = service.cadastrarParticipacao(participacao);

	}

	public String adicionarMembroProjeto(Projeto projeto) {

		this.participacao.setProjeto(projeto);
		return PathRedirect.adicionarMembroProjeto;

	}

	public ArrayList<MembroProjeto> completeMembroProjeto(String query) {

		PalavraUtil palavraUtil = new PalavraUtil(query);
		Response response = service.consultarPessoasNome(palavraUtil);

		ArrayList<MembroProjeto> membros = response
				.readEntity(new GenericType<ArrayList<MembroProjeto>>() {
				});

		return membros;
	}

	public Participacao getParticipacao() {
		return participacao;
	}

	public void setParticipacao(Participacao participacao) {
		this.participacao = participacao;
	}

	public List<SelectItem> getTiposParticipacoes() {
		Response response = service.consultarTiposParticipacao();

		// TODO: em caso de erro, redirecionar para página de erro
		if (response.getStatus() != 200) {
			Erro qme = response.readEntity(new GenericType<Erro>() {
			});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe
								// esse
								// campo

		}

		ArrayList<TipoParticipacao> altp = response
				.readEntity(new GenericType<ArrayList<TipoParticipacao>>() {
				});

		response.close();

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
