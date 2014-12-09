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
public class CursoBean extends GenericBean implements BeanInterface{

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

		Response message = service.cadastrarCurso(curso);
	}

	public List<Curso> getCursos() {
		Response response =service.consultarCursos();

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
	
	public void detalhesCurso(
			Curso curso) {

		ExibirDetalhes exibirDetalhes = new ExibirDetalhes(
				curso);

		GenericBean.setSessionValue("exibirDetalhes",
				exibirDetalhes);

		exibirDetalhes.redirecionarExibirCurso();

	}
	
	public void update() {

		ExibirDetalhes exibirDetalhes = (ExibirDetalhes) GenericBean
				.getSessionValue("exibirDetalhes");
		//TODO: encontrar o metódo de edição
		service.editarCurso(exibirDetalhes.getCurso());

	}

}
