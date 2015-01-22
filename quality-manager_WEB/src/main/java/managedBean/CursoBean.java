package managedBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Erro;

@ManagedBean
@RequestScoped
public class CursoBean extends GenericBean implements BeanInterface {

	private int CURSO_NAO_CADASTRADO = 0;
	
	private Curso curso = new Curso();
	private List<Curso> cursos;

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String createEdit(Curso curso) {

		if (curso == null) {
			// Curso ainda não criado.
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
				Erro erroResponse = response.readEntity(Erro.class);
				
				GenericBean.setMessage("erro.cursoInexistente", 
						FacesMessage.SEVERITY_ERROR);
			}			
		}

		return PathRedirect.cadastrarCurso;
	}

	@Override
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

	public List<Curso> getCursos() {
		
		Response response = service.consultarCursos();
		
		int statusCode = response.getStatus();
		
		if (statusCode == HttpStatus.SC_OK) {
			
			this.cursos = response.readEntity(List.class);
			
		} else {
			
			Erro erroResponse = response.readEntity(Erro.class);
			
			GenericBean.setMessage("erro.listagemCursos", 
					FacesMessage.SEVERITY_ERROR);			
		}
		
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public String detalhesCurso(Curso curso) {

		this.curso = curso;
		return PathRedirect.exibirCurso;
	}
}
