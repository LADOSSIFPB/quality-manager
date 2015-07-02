package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "recursoProgramaInstitucional")
public class RecursoProgramaInstitucional {

	private int idRecursoPI;
	private double orcamento;
	private Date validadeFinal;
	private Date validadeInicial;
	private ProgramaInstitucional programaInstitucional;
	private boolean recursoValido;
	private RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora;
	private Servidor servidor;
	private Date registro;

	public RecursoProgramaInstitucional() {
		programaInstitucional = new ProgramaInstitucional();
		recursoInstituicaoFinanciadora = new RecursoInstituicaoFinanciadora();
	}

	public RecursoProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {
		this.programaInstitucional = programaInstitucional;
		recursoInstituicaoFinanciadora = new RecursoInstituicaoFinanciadora();
	}

	@XmlElement
	public int getIdRecursoPI() {
		return idRecursoPI;
	}

	public void setIdRecursoPI(int idRecursoPI) {
		this.idRecursoPI = idRecursoPI;
	}

	@XmlElement
	public double getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(double orcamento) {
		this.orcamento = orcamento;
	}

	@XmlElement
	public Date getValidadeFinal() {
		return validadeFinal;
	}

	public void setValidadeFinal(Date validadeFinal) {
		this.validadeFinal = validadeFinal;
	}

	@XmlElement
	public Date getValidadeInicial() {
		return validadeInicial;
	}

	public void setValidadeInicial(Date validadeInicial) {
		this.validadeInicial = validadeInicial;
	}

	@XmlElement
	public ProgramaInstitucional getProgramaInstitucional() {
		return programaInstitucional;
	}

	public void setProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {
		this.programaInstitucional = programaInstitucional;
	}

	@XmlElement
	public boolean isRecursoValido() {
		return recursoValido;
	}

	public void setRecursoValido(boolean recursoValido) {
		this.recursoValido = recursoValido;
	}

	@XmlElement
	public RecursoInstituicaoFinanciadora getRecursoInstituicaoFinanciadora() {
		return recursoInstituicaoFinanciadora;
	}

	public void setRecursoInstituicaoFinanciadora(
			RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora) {
		this.recursoInstituicaoFinanciadora = recursoInstituicaoFinanciadora;
	}

	@XmlElement
	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
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
