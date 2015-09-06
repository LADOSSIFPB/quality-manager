package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "recurso_instituicao_financiadora")
public class RecursoInstituicaoFinanciadora {

	private int idRecursoIF;
	
	private double orcamento;
	
	private double reservado;
	
	private Date validadeFinal;
	
	private Date validadeInicial;
	
	private InstituicaoFinanciadora instituicaoFinanciadora;
	
	private boolean recursoValido;
	
	private Servidor cadastrador;
	
	private Date registro;
	
	public RecursoInstituicaoFinanciadora() {
		this.cadastrador = new Servidor();
	}

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
	public Servidor getCadastrador() {
		return cadastrador;
	}

	public void setCadastrador(Servidor cadastrador) {
		this.cadastrador = cadastrador;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@XmlElement
	public double getReservado() {
		return reservado;
	}

	public void setReservado(double reservado) {
		this.reservado = reservado;
	}
}
