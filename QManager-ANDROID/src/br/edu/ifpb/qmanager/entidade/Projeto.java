package br.edu.ifpb.qmanager.entidade;

import java.util.LinkedList;
import java.util.List;

public class Projeto {

	private int idProjeto;

	private String nomeProjeto;

	private String inicioProjeto;

	private String fimProjeto;

	private String projetoSubmetido;

	private String relatorioParcial;

	private String relatorioFinal;

	private String processo;

	private char tipoProjeto;

	private String nomeTipoProjeto;

	private double orcamento;

	private Edital edital;

	private Campus campus;

	private String registro;

	private List<Discente> discentes;

	private Servidor orientador;

	private Servidor coorientador;

	private Servidor colaborador;

	// construtor para readById
	public Projeto() {
		edital = new Edital();
		campus = new Campus();
		orientador = new Servidor();
		coorientador = new Servidor();
		// TODO: ver a questÃ£o dos discentes
		discentes = new LinkedList<Discente>();
	}

	// construtor para creat
	public Projeto(String nomeProjeto, String inicioProjeto, String fimProjeto,
			String relatorioSubmetido, String relatorioParcial,
			String relatorioFinal, String processo, char tipoProjeto,
			double orcamento, Edital edital) {
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

	public List<Discente> getDiscentes() {
		return discentes;
	}

	public void setDiscentes(List<Discente> discentes) {
		this.discentes = discentes;
	}

	public Servidor getOrientador() {
		return orientador;
	}

	public void setOrientador(Servidor orientador) {
		this.orientador = orientador;
	}

	public Servidor getCoorientador() {
		return coorientador;
	}

	public void setCoorientador(Servidor coorientador) {
		this.coorientador = coorientador;
	}

	public Servidor getColaborador() {
		return colaborador;
	}

	public void setColaborador(Servidor colaborador) {
		this.colaborador = colaborador;
	}

	public int getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	public String getInicioProjeto() {
		return inicioProjeto;
	}

	public void setInicioProjeto(String inicioProjeto) {
		this.inicioProjeto = inicioProjeto;
	}

	public String getFimProjeto() {
		return fimProjeto;
	}

	public void setFimProjeto(String fimProjeto) {
		this.fimProjeto = fimProjeto;
	}

	public String getProjetoSubmetido() {
		return projetoSubmetido;
	}

	public void setProjetoSubmetido(String relatorioSubmetido) {
		this.projetoSubmetido = relatorioSubmetido;
	}

	public String getRelatorioParcial() {
		return relatorioParcial;
	}

	public void setRelatorioParcial(String relatorioParcial) {
		this.relatorioParcial = relatorioParcial;
	}

	public String getRelatorioFinal() {
		return relatorioFinal;
	}

	public void setRelatorioFinal(String relatorioFinal) {
		this.relatorioFinal = relatorioFinal;
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public char getTipoProjeto() {
		return tipoProjeto;
	}

	public void setTipoProjeto(char tipoProjeto) {
		this.tipoProjeto = tipoProjeto;
	}

	public String getNomeTipoProjeto() {
		if (getTipoProjeto() == 'P')
			return "Pesquisa";
		else
			return "Extensão";
	}

	public void setNomeTipoProjeto(String nomeTipoProjeto) {
		this.nomeTipoProjeto = nomeTipoProjeto;
	}

	public double getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(double orcamento) {
		this.orcamento = orcamento;
	}

	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	@Override
	public String toString() {
		return "Projeto [idProjeto=" + idProjeto + ", nomeProjeto="
				+ nomeProjeto + ", inicioProjeto=" + inicioProjeto
				+ ", fimProjeto=" + fimProjeto + ", projetoSubmetido="
				+ projetoSubmetido + ", relatorioParcial=" + relatorioParcial
				+ ", relatorioFinal=" + relatorioFinal + ", processo="
				+ processo + ", tipoProjeto=" + tipoProjeto
				+ ", nomeTipoProjeto=" + nomeTipoProjeto + ", orcamento="
				+ orcamento + ", edital=" + edital + ", campus=" + campus
				+ ", registro=" + registro + ", discentes=" + discentes
				+ ", orientador=" + orientador + ", coorientador="
				+ coorientador + ", colaborador=" + colaborador + "]";
	}
}
