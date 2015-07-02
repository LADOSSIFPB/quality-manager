package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "recurso_instituicao_financiadora")
public class RecursoInstituicaoFinanciadora {

	private int idRecursoIF;
	private double orcamento;
	private Date validadeFinal;
	private Date validadeInicial;
	private InstituicaoFinanciadora instituicaoFinanciadora;
	private boolean recursoValido;
	private Servidor servidor;
	private Date registro;

	@XmlElement
	public int getIdRecursoIF() {
		return idRecursoIF;
	}

	public void setIdRecursoIF(int idRecursoIF) {
		this.idRecursoIF = idRecursoIF;
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
	public InstituicaoFinanciadora getInstituicaoFinanciadora() {
		return instituicaoFinanciadora;
	}

	public void setInstituicaoFinanciadora(
			InstituicaoFinanciadora instituicaoFinanciadora) {
		this.instituicaoFinanciadora = instituicaoFinanciadora;
	}

	@XmlElement
	public boolean isRecursoValido() {
		return recursoValido;
	}

	public void setRecursoValido(boolean recursoValido) {
		this.recursoValido = recursoValido;
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
		return "RecursoInstituicaoFinanciadora [idRecursoIF=" + idRecursoIF
				+ ", orcamento=" + orcamento + ", validadeFinal="
				+ validadeFinal + ", validadeInicial=" + validadeInicial
				+ ", instituicaoFinanciadora=" + instituicaoFinanciadora
				+ ", registro=" + registro + "]";
	}

}
