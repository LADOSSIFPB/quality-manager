package managedBean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;

@ManagedBean
@RequestScoped
public class InstituicaoFinanciadoraBean extends
		GenericBean<InstituicaoFinanciadora> implements beanInterface {

	private InstituicaoFinanciadora instituicaoFinanciadora = new InstituicaoFinanciadora();

	public InstituicaoFinanciadora getInstituicaoFinanciadora() {
		return instituicaoFinanciadora;
	}

	public void setInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
	}

	@Override
	public void save() {

		String mensagem = requestClient(instituicaoFinanciadora,
				PathServices.CADASTRAR_INSTITUICAO);
		HttpServletResponse response = null;
	}
}
