package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;

@ManagedBean
@ViewScoped
public class InstituicaoFinanciadoraBean implements Serializable {

	private static final long serialVersionUID = -9095367164306892357L;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);
	
	private List<InstituicaoFinanciadora> instituicoesFinanciadoras;

	private String nomeInstituicaoFinanciadora;
	
	public InstituicaoFinanciadoraBean() {
	}

	public void consultarInstituicoesFinanciadoras() {

		if (this.nomeInstituicaoFinanciadora != null
				&& !this.nomeInstituicaoFinanciadora.trim().isEmpty()) {

			InstituicaoFinanciadora instituicaoConsulta = new InstituicaoFinanciadora();
			instituicaoConsulta
					.setNomeInstituicaoFinanciadora(this.nomeInstituicaoFinanciadora);

			this.instituicoesFinanciadoras = service
					.consultarInstituicoesFinanciadoras(instituicaoConsulta);
		}
	}

	public void listarInstituicoesFinanciadoras() {
		this.instituicoesFinanciadoras = service
				.listarInstituicoesFinanciadoras();
	}

	public void detalharInstituicao(
			InstituicaoFinanciadora instituicaoFinanciadora) {

		EditarInstituicaoFinanciadoraBean editarInstituicaoFinanciadoraBean = 
				new EditarInstituicaoFinanciadoraBean(instituicaoFinanciadora);
		editarInstituicaoFinanciadoraBean.resetSession(editarInstituicaoFinanciadoraBean);

		GenericBean.sendRedirect(PathRedirect.exibirInstituicaoFinanciadora);
	}

	public List<InstituicaoFinanciadora> getInstituicoesFinanciadoras() {
		return instituicoesFinanciadoras;
	}

	public void setInstituicoesFinanciadoras(
			List<InstituicaoFinanciadora> instituicoesFinanciadoras) {
		this.instituicoesFinanciadoras = instituicoesFinanciadoras;
	}

	public String getNomeInstituicaoFinanciadora() {
		return nomeInstituicaoFinanciadora;
	}

	public void setNomeInstituicaoFinanciadora(
			String nomeInstituicaoFinanciadora) {
		this.nomeInstituicaoFinanciadora = nomeInstituicaoFinanciadora;
	}
}
