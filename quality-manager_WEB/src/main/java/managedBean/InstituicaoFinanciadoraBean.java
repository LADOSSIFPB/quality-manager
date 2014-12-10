package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.util.IntegerUtil;

@ManagedBean
@RequestScoped
public class InstituicaoFinanciadoraBean extends GenericBean implements
		BeanInterface, Serializable {

	private static final long serialVersionUID = -3749706911495000581L;

	private InstituicaoFinanciadora instituicaoFinanciadora = new InstituicaoFinanciadora();

	private List<InstituicaoFinanciadora> instituicoesFinanciadoras;

	public InstituicaoFinanciadora getInstituicaoFinanciadora() {
		return instituicaoFinanciadora;
	}

	public void setInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
	}

	public String createEdit(InstituicaoFinanciadora instituicao) {

		if (instituicao == null) {
			GenericBean
					.sendRedirect(PathRedirect.cadastrarInstituicaoFinanciadora);

		} else {

			IntegerUtil integerUtil = new IntegerUtil(
					instituicao.getIdInstituicaoFinanciadora());

			Response response = service.consultarInstituicao(integerUtil);

			this.instituicaoFinanciadora = response
					.readEntity(new GenericType<InstituicaoFinanciadora>() {
					});

		}

		return PathRedirect.cadastrarInstituicaoFinanciadora;
	}

	@Override
	public void save() {

		if (instituicaoFinanciadora.getIdInstituicaoFinanciadora() == 0) {
			PessoaBean pessoaBean = getPessoaBean(FacesContext
					.getCurrentInstance());

			instituicaoFinanciadora.getGestor().setPessoaId(
					pessoaBean.getPessoaId());

			Response mensagem = service
					.cadastrarInstituicao(instituicaoFinanciadora);
		} else {
			Response mensagem = service
					.editarInstituicaoFinanciadora(instituicaoFinanciadora);
		}
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

	public String detalhesInstituicao(
			InstituicaoFinanciadora instituicaoFinanciadora) {

		this.instituicaoFinanciadora = instituicaoFinanciadora;

		return PathRedirect.exibirInstituicaoFinanciadora;

	}
}
