package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;

@ManagedBean
@RequestScoped
public class InstituicaoFinanciadoraBean extends GenericBean implements
		BeanInterface, Serializable {

	private InstituicaoFinanciadora instituicaoFinanciadora = new InstituicaoFinanciadora();
	private List<InstituicaoFinanciadora> instituicoesFinanciadoras;

	public InstituicaoFinanciadora getInstituicaoFinanciadora() {
		return instituicaoFinanciadora;
	}

	public void setInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
	}

	@Override
	public void save() {

		PessoaBean pessoaBean = getPessoaBean(FacesContext.getCurrentInstance());

		instituicaoFinanciadora.getGestor().setPessoaId(
				pessoaBean.getPessoaId());

		Response mensagem = service
				.cadastrarInstituicao(instituicaoFinanciadora);

	}

	public List<InstituicaoFinanciadora> getInstituicoesFinanciadoras() {

		Response response = service.consultarInstituicoes();

		// TODO: em caso de erro, redirecionar para página de erro
		if (response.getStatus() != 200) {
			Erro qme = response.readEntity(new GenericType<Erro>() {
			});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe esse
								// campo

		}

		this.instituicoesFinanciadoras = response
				.readEntity(new GenericType<ArrayList<InstituicaoFinanciadora>>() {
				});
		return instituicoesFinanciadoras;
	}

	public void setInstituicoesFinanciadoras(
			List<InstituicaoFinanciadora> instituicoesFinanciadoras) {
		this.instituicoesFinanciadoras = instituicoesFinanciadoras;
	}

	public void onRowEdit(RowEditEvent event) {
		instituicaoFinanciadora
				.setNomeInstituicaoFinanciadora(((InstituicaoFinanciadora) event
						.getObject()).getNomeInstituicaoFinanciadora());
		instituicaoFinanciadora.setSigla(((InstituicaoFinanciadora) event
				.getObject()).getSigla());
		instituicaoFinanciadora.setOrcamento(((InstituicaoFinanciadora) event
				.getObject()).getOrcamento());

		// TODO: Criar serviços de update

		FacesMessage msg = new FacesMessage("Edição concluída", String.format(
				"%d", ((InstituicaoFinanciadora) event.getObject())
						.getIdInstituicaoFinanciadora()));
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição cancelada", String.format(
				"%s%d", "Instituição ID: ", ((InstituicaoFinanciadora) event.getObject())
						.getIdInstituicaoFinanciadora()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
