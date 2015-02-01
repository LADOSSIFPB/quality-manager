package managedBean;

import java.sql.SQLException;
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
public class DiscenteBean extends GenericBean implements
		BeanInterface {

	private Discente discente = new Discente();
	private List<SelectItem> instituicoesBancarias;
	private List<SelectItem> cursos;

	public List<SelectItem> getInstituicoesBancarias() {
		List<InstituicaoBancaria> alib = null;
		try {
			alib = service.listarInstituicoesBancarias();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		try {
			return discentes = service.listarDiscentes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		Response message = service.cadastrarDiscente(discente);

	}

	public List<SelectItem> getCursos() {
		
		List<Curso> alc = null;
		try {
			alc = service.listarCursos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
