package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;

@ManagedBean
@ViewScoped
public class InstituicaoBancariaBean {

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private List<InstituicaoBancaria> instituicoesBancarias;

	private String nomeInstituicaoBancaria;

	public void consultarInstituicoesBancarias() {

		if (this.nomeInstituicaoBancaria != null
				&& !this.nomeInstituicaoBancaria.trim().isEmpty()) {

			InstituicaoBancaria instituicaoConsulta = new InstituicaoBancaria();
			instituicaoConsulta.setNomeBanco(this.nomeInstituicaoBancaria);

			this.setInstituicoesBancarias(service
					.consultarInstituicoesBancarias(instituicaoConsulta));
		}
	}

	public void listarInstituicoesBancarias() {
		this.setInstituicoesBancarias(service.listarInstituicoesBancarias());
	}

	public void detalharInstituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria) {

		GenericBean.resetSessionScopedBean("editarInstituicaoBancariaBean");

		EditarInstituicaoBancariaBean editarInstituicaoBancariaBean = new EditarInstituicaoBancariaBean(
				instituicaoBancaria);

		GenericBean.setSessionValue("editarInstituicaoBancariaBean",
				editarInstituicaoBancariaBean);

		GenericBean.sendRedirect(PathRedirect.exibirInstituicaoBancaria);
	}

	public String getNomeInstituicaoBancaria() {
		return nomeInstituicaoBancaria;
	}

	public void setNomeInstituicaoBancaria(String nomeInstituicaoBancaria) {
		this.nomeInstituicaoBancaria = nomeInstituicaoBancaria;
	}

	public List<InstituicaoBancaria> getInstituicoesBancarias() {
		return instituicoesBancarias;
	}

	public void setInstituicoesBancarias(
			List<InstituicaoBancaria> instituicoesBancarias) {
		this.instituicoesBancarias = instituicoesBancarias;
	}
}
