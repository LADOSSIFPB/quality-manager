package br.edu.ifpb.qmanager.entidade;

import java.sql.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "projeto")
public class Projeto {

	private int idProjeto;
	private String nomeProjeto;
	private Date inicioProjeto;
	private Date fimProjeto;
	private String projetoSubmetido;
	private String relatorioParcial;
	private String relatorioFinal;
	private String processo;
	private char tipoProjeto;
	private double orcamento;
	private Date registro;
	private Edital edital;

	private List<Discente> discentes;
	private Orientador orientador;
	private Orientador coorientador;

	// construtor para readById
	public Projeto() {
	}

	// construtor para creat
	public Projeto(String nomeProjeto, java.util.Date inicioProjeto,
			java.util.Date fimProjeto, String relatorioSubmetido,
			String relatorioParcial, String relatorioFinal, String processo,
			char tipoProjeto, double orcamento, Edital edital) {
		setNomeProjeto(nomeProjeto);
		setInicioProjeto(inicioProjeto);
		setFimProjeto(fimProjeto);
		setProjetoSubmetido(projetoSubmetido);
		setRelatorioParcial(relatorioParcial);
		setRelatorioFinal(relatorioFinal);
		setProcesso(processo);
		setTipoProjeto(tipoProjeto);
		setOrcamento(orcamento);
		setEdital(edital);
	}

	@XmlElement
	public List<Discente> getDiscentes() {
		return discentes;
	}

	public void setDiscentes(List<Discente> discentes) {
		this.discentes = discentes;
	}

	@XmlElement
	public Orientador getOrientador() {
		return orientador;
	}

	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
	}

	@XmlElement
	public Orientador getCoorientador() {
		return coorientador;
	}

	public void setCoorientador(Orientador coorientador) {
		this.coorientador = coorientador;
	}

	@XmlElement
	public int getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}

	@XmlElement
	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	@XmlElement
	public Date getInicioProjeto() {
		return inicioProjeto;
	}

	public void setInicioProjeto(Date inicioProjeto) {
		this.inicioProjeto = inicioProjeto;
	}

	public void setInicioProjeto(java.util.Date inicioProjeto) {
		this.inicioProjeto.setTime(inicioProjeto.getTime());
	}

	@XmlElement
	public Date getFimProjeto() {
		return fimProjeto;
	}

	public void setFimProjeto(Date fimProjeto) {
		this.fimProjeto = fimProjeto;
	}

	public void setFimProjeto(java.util.Date fimProjeto) {
		this.fimProjeto.setTime(fimProjeto.getTime());
	}

	@XmlElement
	public String getProjetoSubmetido() {
		return projetoSubmetido;
	}

	public void setProjetoSubmetido(String relatorioSubmetido) {
		this.projetoSubmetido = relatorioSubmetido;
	}

	@XmlElement
	public String getRelatorioParcial() {
		return relatorioParcial;
	}

	public void setRelatorioParcial(String relatorioParcial) {
		this.relatorioParcial = relatorioParcial;
	}

	@XmlElement
	public String getRelatorioFinal() {
		return relatorioFinal;
	}

	public void setRelatorioFinal(String relatorioFinal) {
		this.relatorioFinal = relatorioFinal;
	}

	@XmlElement
	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	@XmlElement
	public char getTipoProjeto() {
		return tipoProjeto;
	}

	public void setTipoProjeto(char tipoProjeto) {
		this.tipoProjeto = tipoProjeto;
	}

	@XmlElement
	public double getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(double orcamento) {
		this.orcamento = orcamento;
	}

	@XmlElement
	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
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
		return "Projeto [idProjeto=" + idProjeto + ", nomeProjeto="
				+ nomeProjeto + ", inicioProjeto=" + inicioProjeto
				+ ", fimProjeto=" + fimProjeto + ", projetoSubmetido="
				+ projetoSubmetido + ", relatorioParcial=" + relatorioParcial
				+ ", relatorioFinal=" + relatorioFinal + ", processo="
				+ processo + ", tipoProjeto=" + tipoProjeto + ", orcamento="
				+ orcamento + ", registro=" + registro + ", edital=" + edital
				+ ", discentes=" + discentes + ", orientador=" + orientador
				+ ", coorientador=" + coorientador + "]";
	}

}
