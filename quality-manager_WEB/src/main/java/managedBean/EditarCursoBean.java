package managedBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Erro;

@ManagedBean(name = "editarCursoBean")
@SessionScoped
public class EditarCursoBean {

	Curso curso;
	
	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);
	
	private int CURSO_NAO_CADASTRADO = 0;
	
	public EditarCursoBean() {}
	
	public EditarCursoBean(Curso curso) {
		this.curso = curso;
	}
	
	public void save() {

		Response response = null;

		if (curso.getIdCurso() == CURSO_NAO_CADASTRADO) {

			response = service.cadastrarCurso(curso);

		} else {
			
			response = service.editarCurso(curso);
		}

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			GenericBean.setMessage("info.sucessoCadastroCurso",
					FacesMessage.SEVERITY_INFO);

		} else {

			// Http Code: 304. Não modificado.
			Erro erroResponse = response.readEntity(Erro.class);

			GenericBean.setMessage("erro.cadastroCurso",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}
