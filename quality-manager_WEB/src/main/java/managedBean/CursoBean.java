package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.ifpb.qmanager.entidade.Curso;

@ManagedBean
@RequestScoped
public class CursoBean extends GenericBean<Curso> implements beanInterface{

	private Curso curso = new Curso();

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public void save() {

		String message = requestClient(curso, PathServices.CADASTRAR_CURSO);
	}

}
