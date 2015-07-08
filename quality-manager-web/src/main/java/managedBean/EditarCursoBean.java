package managedBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.Servidor;

@ManagedBean(name = "editarCursoBean")
@SessionScoped
public class EditarCursoBean {

	private Curso curso;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private int CURSO_NAO_CADASTRADO = 0;

	public void save() {

		Response response = null;

		if (curso.getIdCurso() == CURSO_NAO_CADASTRADO) {

			PessoaBean pessoaBean = (PessoaBean) GenericBean
					.getSessionValue("pessoaBean");
			this.curso.getGestor().setPessoaId(pessoaBean.getPessoaId());
			response = service.cadastrarCurso(curso);

		} else {

			response = service.editarCurso(curso);
		}

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			GenericBean.setMessage("info.sucessoCadastroCurso",
					FacesMessage.SEVERITY_INFO);
			GenericBean.resetSessionScopedBean("editarCursoBean");

		} else {

			// Http Code: 304. Não modificado.
			Erro erro = response.readEntity(Erro.class);

			GenericBean.setMessage("erro.cadastroCurso",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public String createEdit(Curso curso) {

		if (curso == null) {
			
			// Curso ainda não criado.
			GenericBean.resetSessionScopedBean("editarCursoBean");
			GenericBean.sendRedirect(PathRedirect.cadastrarCurso);
			
		} else {

			Response response = service.consultarCurso(curso.getIdCurso());

			// Código de resposta do serviço.
			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {
				
				// Http Code: 200. Resposta para cadastro realizado com sucesso.
				Curso cursoResponse = response.readEntity(Curso.class);

				// Curso encontrado.
				this.curso = cursoResponse;

			} else {
				// Http Code: 404. Curso inexistente.
				Erro erro = response.readEntity(Erro.class);

				GenericBean.setMessage("erro.cursoInexistente",
						FacesMessage.SEVERITY_ERROR);
			}
		}

		return PathRedirect.cadastrarCurso;
	}
	
	public List<Servidor> completeCoordenador(String query) {

		Servidor servidor = new Servidor();
		servidor.setNomePessoa(query);

		List<Servidor> servidores = service.consultarServidores(servidor);

		return servidores;
	}

	public EditarCursoBean() {
		this.curso = new Curso();
	}

	public EditarCursoBean(Curso curso) {
		this.curso = curso;
	}
	
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}
