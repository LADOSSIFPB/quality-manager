package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;

@ManagedBean
@RequestScoped
public class DiscenteBean extends GenericBean implements
		BeanInterface {

	private Discente discente = new Discente();
	
	private List<SelectItem> instituicoesBancarias;
	
	private List<SelectItem> cursos;

	public List<SelectItem> getInstituicoesBancarias() {
		List<InstituicaoBancaria> alib = null;
		alib = service.listarInstituicoesBancarias();

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
		return service.listarDiscentes();
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
		Response response = service.cadastrarDiscente(discente);
		
		int status = response.getStatus();

		if (status == HttpStatus.SC_OK) {
			
		}
	}

	public List<SelectItem> getCursos() {
		
		List<Curso> cursosConsulta = service.listarCursos();

		cursos = new ArrayList<SelectItem>();

		if (!cursosConsulta.isEmpty()) {

			for (Curso curso : cursosConsulta) {
				SelectItem si = new SelectItem();
				si.setValue(curso.getIdCurso());
				si.setLabel(curso.getNomeCurso());
				cursos.add(si);
			}
		}
		
		return cursos;
	}

	public void setCursos(List<SelectItem> cursos) {
		this.cursos = cursos;
	}
}
