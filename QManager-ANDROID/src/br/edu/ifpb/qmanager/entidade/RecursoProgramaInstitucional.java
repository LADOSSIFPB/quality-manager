package br.edu.ifpb.qmanager.entidade;

public class RecursoProgramaInstitucional {

	private int idRecursoPI;
	private double orcamento;
	private String validadeFinal;
	private String validadeInicial;
	private ProgramaInstitucional programaInstitucional;
	private boolean recursoValido;
	private RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora;
	private String registro;

	public int getIdRecursoPI() {
		return idRecursoPI;
	}

	public void setIdRecursoPI(int idRecursoPI) {
		this.idRecursoPI = idRecursoPI;
	}

	public double getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(double orcamento) {
		this.orcamento = orcamento;
	}

	public String getValidadeFinal() {
		return validadeFinal;
	}

	public void setValidadeFinal(String validadeFinal) {
		this.validadeFinal = validadeFinal;
	}

	public String getValidadeInicial() {
		return validadeInicial;
	}

	public void setValidadeInicial(String validadeInicial) {
		this.validadeInicial = validadeInicial;
	}

	public ProgramaInstitucional getProgramaInstitucional() {
		return programaInstitucional;
	}

	public void setProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {
		this.programaInstitucional = programaInstitucional;
	}

	public boolean isRecursoValido() {
		return recursoValido;
	}

	public void setRecursoValido(boolean recursoValido) {
		this.recursoValido = recursoValido;
	}

	public RecursoInstituicaoFinanciadora getRecursoInstituicaoFinanciadora() {
		return recursoInstituicaoFinanciadora;
	}

	public void setRecursoInstituicaoFinanciadora(
			RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora) {
		this.recursoInstituicaoFinanciadora = recursoInstituicaoFinanciadora;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "RecursoInstituicaoFinanciadora [idRecursoIF=" + idRecursoPI
				+ ", orcamento=" + orcamento + ", validadeFinal="
				+ validadeFinal + ", validadeInicial=" + validadeInicial
				+ ", programaInstitucional=" + programaInstitucional
				+ ", registro=" + registro + "]";
	}

}
