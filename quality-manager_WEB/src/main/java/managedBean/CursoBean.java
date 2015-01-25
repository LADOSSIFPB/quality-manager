package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Curso;

@ManagedBean(name = "cursoBean")
@ViewScoped
public class CursoBean implements BeanInterface {

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);
	
	private List<Curso> cursos;	
	
	private String nomeCurso;	
	
	@Override
	public void save() {}
	
	/**
	 * Consultar os cursos existentes.
	 * 
	 * @return
	 */
	public void consultarCursos() {
		
		if (this.nomeCurso!= null && !this.nomeCurso.trim().isEmpty()) {
			
			Curso cursoConsulta = new Curso();
			cursoConsulta.setNomeCurso(this.nomeCurso);
			
			this.cursos = service.consultarCursos(cursoConsulta);
		}		
	}

	/**
	 * Listar todos cursos existentes.
	 * 
	 * @return
	 */
	public void listarCursos() {

		this.cursos = service.listarCursos();		
	}
	
	/**
	 * Detalhar o curso selecionado.
	 * 
	 * @param curso
	 * @return
	 */
	public String detalharCurso(Curso curso) {
		
		GenericBean.resetSessionScopedBean("editarCursoBean");
		
		EditarCursoBean editarCursoBean = new EditarCursoBean(curso);
		GenericBean.setSessionValue("editarCursoBean", editarCursoBean);		
		
		return PathRedirect.exibirCurso;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
}
