package managedBean;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;

@ManagedBean
@RequestScoped
public class InstituicaoBancariaBean extends GenericBean implements
		BeanInterface {

	private InstituicaoBancaria instituicaoBancaria = new InstituicaoBancaria();
	private List<InstituicaoBancaria> instituicoesBancarias;

	public InstituicaoBancaria getInstituicaoBancaria() {
		return instituicaoBancaria;
	}

	public void setInstituicaoBancaria(InstituicaoBancaria instituicaoBancaria) {
		this.instituicaoBancaria = instituicaoBancaria;
	}

	public List<InstituicaoBancaria> getInstituicoesBancarias() {

		try {
			this.instituicoesBancarias = service.listarInstituicoesBancarias();
		} catch (SQLException e) {
			// TODO: verificar tratamento de erro.
			e.printStackTrace();
		}

		return instituicoesBancarias;

	}

	public void setInstituicoesBancarias(
			List<InstituicaoBancaria> instituicoesBancarias) {
		this.instituicoesBancarias = instituicoesBancarias;
	}

	public String createEdit(InstituicaoBancaria instituicaoBancaria) {

		if (instituicaoBancaria == null) {
			GenericBean.sendRedirect(PathRedirect.cadastrarInstituicaoBancaria);

		} else {

			Response response = service
					.consultarInstituicaoBancaria(instituicaoBancaria
							.getIdInstituicaoBancaria());

			this.instituicaoBancaria = response
					.readEntity(new GenericType<InstituicaoBancaria>() {
					});

		}

		return PathRedirect.cadastrarInstituicaoBancaria;
	}

	@Override
	public void save() {

		if (instituicaoBancaria.getIdInstituicaoBancaria() == 0) {
			Response message = service
					.cadastrarInstituicaoBancaria(instituicaoBancaria);
		} else {
			Response response = service
					.editarInstituicaoBancaria(instituicaoBancaria);
		}
	}

	public String detalhesInstituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria) {

		this.instituicaoBancaria = instituicaoBancaria;
		return PathRedirect.exibirInstituicaoBancaria;

	}

}
