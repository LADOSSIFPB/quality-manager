package managedBean;

import java.sql.SQLException;
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

		if (this.getNomeInstituicaoBancaria() != null
				&& !this.getNomeInstituicaoBancaria().trim().isEmpty()) {

			InstituicaoBancaria instituicaoConsulta = new InstituicaoBancaria();
			instituicaoConsulta.setNomeBanco(this
					.getNomeInstituicaoBancaria());

			try {
				this.setInstituicoesBancarias(service
						.consultarInstituicoesBancarias(instituicaoConsulta));
			} catch (SQLException e) {
				// TODO: verificar tratamento desse erro
				e.printStackTrace();
			}

		}
	}

	public void listarInstituicoesBancarias() {

		try {
			this.setInstituicoesBancarias(service
					.listarInstituicoesBancarias());
		} catch (SQLException e) {
			// TODO: verificar tratamento desse erro
			e.printStackTrace();
		}
	}

	public String detalharInstituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria) {

		GenericBean.resetSessionScopedBean("editarInstituicaoBancariaBean");

		EditarInstituicaoBancariaBean editarInstituicaoBancariaBean = new EditarInstituicaoBancariaBean(
				instituicaoBancaria);
		GenericBean.setSessionValue("editarInstituicaoBancariaBean",
				editarInstituicaoBancariaBean);

		return PathRedirect.exibirInstituicaoBancaria;

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

	public void setInstituicoesBancarias(List<InstituicaoBancaria> instituicoesBancarias) {
		this.instituicoesBancarias = instituicoesBancarias;
	}

}
