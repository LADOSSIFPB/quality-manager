package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;

@ManagedBean
@RequestScoped
public class DiscenteBean extends GenericBean<Discente> implements
		BeanInterface {

	private Discente discente = new Discente();
	private List<SelectItem> instituicoesBancarias;
	private List<SelectItem> cursos;

	public List<SelectItem> getInstituicoesBancarias() {
		Response response = requestSelectConsultar(PathServices.CONSULTAR_INSTITUICOES_BANCARIAS);

		// TODO: em caso de erro, redirecionar para página de erro
		if (response.getStatus() != 200) {
			Erro qme = response.readEntity(new GenericType<Erro>() {
			});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe esse
								// campo

		}

		ArrayList<InstituicaoBancaria> alib = response
				.readEntity(new GenericType<ArrayList<InstituicaoBancaria>>() {
				});

		response.close();

		ArrayList<SelectItem> alsi = new ArrayList<SelectItem>();

		if (!alib.isEmpty()) {

			for (InstituicaoBancaria instituicaoBancaria : alib) {
				SelectItem si = new SelectItem();
				si.setValue(instituicaoBancaria.getIdInstituicaoBancaria());
				si.setLabel(instituicaoBancaria.getNomeBanco());
				alsi.add(si);
			}
		} else {
			// TODO: Melhorar esse erro
			System.err.println("Erro!");
		}

		instituicoesBancarias = alsi;
		return instituicoesBancarias;
	}

	public void setInstituicoesBancarias(List<SelectItem> instituicoesBancarias) {
		this.instituicoesBancarias = instituicoesBancarias;
	}

	public List<Discente> getDiscentes() {
		Response response = requestGetAll(PathServices.CONSULTAR_DISCENTES);

		// TODO: em caso de erro, redirecionar para página de erro
		if (response.getStatus() != 200) {
			Erro qme = response.readEntity(new GenericType<Erro>() {
			});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe esse
								// campo

		}

		this.discentes = response
				.readEntity(new GenericType<ArrayList<Discente>>() {
				});
		return discentes;
	}

	public void setDiscentes(List<Discente> discentes) {
		this.discentes = discentes;
	}

	private List<Discente> discentes;

	public Discente getDiscente() {
		return discente;
	}

	public void setDiscente(Discente discente) {
		this.discente = discente;
	}

	@Override
	public void save() {

		String message = requestCadastrar(discente,
				PathServices.CADASTRAR_DISCENTE);

	}

	public List<SelectItem> getCursos() {
		Response response = requestSelectConsultar(PathServices.CONSULTAR_CURSOS);

		// TODO: em caso de erro, redirecionar para página de erro
		if (response.getStatus() != 200) {
			Erro qme = response.readEntity(new GenericType<Erro>() {
			});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe esse
								// campo

		}

		ArrayList<Curso> alc = response
				.readEntity(new GenericType<ArrayList<Curso>>() {
				});

		response.close();

		ArrayList<SelectItem> alsi = new ArrayList<SelectItem>();

		if (!alc.isEmpty()) {

			for (Curso curso : alc) {
				SelectItem si = new SelectItem();
				si.setValue(curso.getIdCurso());
				si.setLabel(curso.getNomeCurso());
				alsi.add(si);
			}
		} else {
			// TODO: Melhorar esse erro
			System.err.println("Erro!");
		}

		cursos = alsi;

		return cursos;
	}

	public void setCursos(List<SelectItem> cursos) {
		this.cursos = cursos;
	}

}
