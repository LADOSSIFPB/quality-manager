package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Orientador;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;

@ManagedBean
@SessionScoped
public class ExibirDetalhes {

	private InstituicaoFinanciadora instituicaoFinanciadora;
	private ProgramaInstitucional programaInstitucional;
	private Edital edital;
	private Curso curso;
	private Orientador orientador;
	private InstituicaoBancaria instituicaoBancaria;
	private Projeto projeto;

	public ExibirDetalhes() {

	}

	public ExibirDetalhes(InstituicaoFinanciadora instituicaoFinanciadora) {

		setInstituicaoFinanciadora(instituicaoFinanciadora);

	}

	public ExibirDetalhes(ProgramaInstitucional programaInstitucional) {

		setProgramaInstitucional(programaInstitucional);
	}

	public ExibirDetalhes(Edital edital) {

		setEdital(edital);
	}

	public ExibirDetalhes(Curso curso) {

		setCurso(curso);
	}

	public ExibirDetalhes(Orientador orientador) {

		setOrientador(orientador);
	}
	
	public ExibirDetalhes(InstituicaoBancaria instituicaoBancaria) {

		setInstituicaoBancaria(instituicaoBancaria);
	}
	
	public ExibirDetalhes(Projeto projeto) {

		setProjeto(projeto);
	}
	
	public InstituicaoFinanciadora getInstituicaoFinanciadora() {
		return instituicaoFinanciadora;
	}

	public void setInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
	}

	public void redirecionarExibirInstFinan() {

		GenericBean.sendRedirect(PathRedirect.exibirInstituicaoFinanciadora);

	}

	public void redirecionarExibirProgramaInstitucional() {

		GenericBean.sendRedirect(PathRedirect.exibirProgramaInstitucional);

	}

	public void redirecionarExibirEdital() {

		GenericBean.sendRedirect(PathRedirect.exibirEdital);

	}
	
	public void redirecionarExibirCurso() {

		GenericBean.sendRedirect(PathRedirect.exibirCurso);

	}
	
	public void redirecionarExibirOrientador() {

		GenericBean.sendRedirect(PathRedirect.exibirOrientador);

	}
	
	public void redirecionarExibirBancaria() {

		GenericBean.sendRedirect(PathRedirect.exibirInstituicaoBancaria);

	}
	
	public void redirecionarExibirProjeto() {

		GenericBean.sendRedirect(PathRedirect.exibirProjeto);

	}

	public ProgramaInstitucional getProgramaInstitucional() {
		return programaInstitucional;
	}

	public void setProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {
		this.programaInstitucional = programaInstitucional;
	}

	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Orientador getOrientador() {
		return orientador;
	}

	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
	}

	public InstituicaoBancaria getInstituicaoBancaria() {
		return instituicaoBancaria;
	}

	public void setInstituicaoBancaria(InstituicaoBancaria instituicaoBancaria) {
		this.instituicaoBancaria = instituicaoBancaria;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

}
