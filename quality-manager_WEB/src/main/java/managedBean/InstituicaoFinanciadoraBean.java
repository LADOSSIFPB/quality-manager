package managedBean;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.RecursoInstituicaoFinanciadora;

@ManagedBean
@ViewScoped
public class InstituicaoFinanciadoraBean {

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private List<InstituicaoFinanciadora> instituicoesFinanciadoras;

	private String nomeInstituicaoFinanciadora;

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

		GenericBean.resetSessionScopedBean("editarInstituicaoFinanciadoraBean");

		EditarInstituicaoFinanciadoraBean editarInstituicaoFinanciadoraBean = new EditarInstituicaoFinanciadoraBean(
				instituicaoFinanciadora);
		GenericBean.setSessionValue("editarInstituicaoFinanciadoraBean",
				editarInstituicaoFinanciadoraBean);

		GenericBean.sendRedirect(PathRedirect.exibirInstituicaoFinanciadora);
	}

	public String lancarRecurso(InstituicaoFinanciadora instituicaoFinanciadora) {

		GenericBean.resetSessionScopedBean("editarInstituicaoFinanciadoraBean");
		RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora = new RecursoInstituicaoFinanciadora();
		recursoInstituicaoFinanciadora
				.setInstituicaoFinanciadora(instituicaoFinanciadora);

		EditarInstituicaoFinanciadoraBean editarInstituicaoFinanciadoraBean = new EditarInstituicaoFinanciadoraBean(
				recursoInstituicaoFinanciadora);
		GenericBean.setSessionValue("editarInstituicaoFinanciadoraBean",
				editarInstituicaoFinanciadoraBean);

		return PathRedirect.lancarRecursoInstituicaoFinanciadora;
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
