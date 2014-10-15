package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Erro;

@ManagedBean
@RequestScoped
public class CursoBean extends GenericBean<Curso> implements BeanInterface{

	private Curso curso = new Curso();
	private List<Curso> cursos;

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public void save() {

		String message = requestCadastrar(curso, PathServices.CADASTRAR_CURSO);
	}

	public List<Curso> getCursos() {
		Response response = requestGetAll(PathServices.CONSULTAR_CURSOS);

		if (response.getStatus() != 200) {
			Erro qme = response.readEntity(new GenericType<Erro>() {
			});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe esse
								// campo

		}

		this.cursos = response
				.readEntity(new GenericType<ArrayList<Curso>>() {
				});
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

}
