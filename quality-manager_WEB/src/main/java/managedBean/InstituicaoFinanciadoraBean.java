package managedBean;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;

@ManagedBean
@ViewScoped
public class InstituicaoFinanciadoraBean {

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private List<InstituicaoFinanciadora> instituicoesFinanciadoras;

	private String nomeInstituicaoFinanciadora;

	public void consultarInstituicoesFinanciadoras() {

		if (this.getNomeInstituicaoFinanciadora() != null
				&& !this.getNomeInstituicaoFinanciadora().trim().isEmpty()) {

			InstituicaoFinanciadora instituicaoConsulta = new InstituicaoFinanciadora();
			instituicaoConsulta.setNomeInstituicaoFinanciadora(this
					.getNomeInstituicaoFinanciadora());

			try {
				this.instituicoesFinanciadoras = service.consultarInstituicoesFinanciadoras(instituicaoConsulta);
			} catch (SQLException e) {
				// TODO: verificar tratamento desse erro
				e.printStackTrace();
			}
			
		}
	}

	public void listarInstituicoesFinanciadoras() {

		try {
			this.setInstituicoesFinanciadoras(service
					.listarInstituicoesFinanciadoras());
		} catch (SQLException e) {
			// TODO: verificar tratamento desse erro
			e.printStackTrace();
		}
	}

	public String detalharInstituicao(
			InstituicaoFinanciadora instituicaoFinanciadora) {

		GenericBean.resetSessionScopedBean("editarInstituicaoFinanciadoraBean");

		EditarInstituicaoFinanciadora editarInstituicaoFinanciadora = new EditarInstituicaoFinanciadora(
				instituicaoFinanciadora);
		GenericBean.setSessionValue("editarInstituicaoFinanciadoraBean",
				editarInstituicaoFinanciadora);

		return PathRedirect.exibirInstituicaoFinanciadora;

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
