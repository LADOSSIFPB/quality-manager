package br.edu.ifpb.qmanager.entidade;

public class RecursoInstituicaoFinanciadora {

	private int idRecursoIF;
	private double orcamento;
	private String validadeFinal;
	private String validadeInicial;
	private InstituicaoFinanciadora instituicaoFinanciadora;
	private boolean recursoValido;
	private String registro;

	public int getIdRecursoIF() {
		return idRecursoIF;
	}

	public void setIdRecursoIF(int idRecursoIF) {
		this.idRecursoIF = idRecursoIF;
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

	public InstituicaoFinanciadora getInstituicaoFinanciadora() {
		return instituicaoFinanciadora;
	}

	public void setInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
	}

	public boolean isRecursoValido() {
		return recursoValido;
	}

	public void setRecursoValido(boolean recursoValido) {
		this.recursoValido = recursoValido;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "RecursoInstituicaoFinanciadora [idRecursoIF=" + idRecursoIF
				+ ", orcamento=" + orcamento + ", validadeFinal="
				+ validadeFinal + ", validadeInicial=" + validadeInicial
				+ ", instituicaoFinanciadora=" + instituicaoFinanciadora
				+ ", registro=" + registro + "]";
	}

}
