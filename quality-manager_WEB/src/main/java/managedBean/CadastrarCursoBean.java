package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.ifpb.qmanager.entidade.Curso;

@ManagedBean
@RequestScoped
public class CadastrarCursoBean extends GenericBean<Curso>{
	
	private Curso curso = new Curso();

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public void save(){
		
		String message = requestClient(curso, PathServices.CADASTRAR_CURSO);
	}

}
