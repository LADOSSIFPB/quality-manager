package br.edu.ifpb.qmanager.entidade;

import java.util.Date;
import java.util.LinkedList;
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
	private String nomeTipoProjeto;
	private double orcamento;
	private Date registro;
	private Edital edital;

	private List<Discente> discentes;
	private Servidor orientador;
	private Servidor coorientador;
	private Servidor colaborador;

	// construtor para readById
	public Projeto() {
		edital = new Edital();
		orientador = new Servidor();
		coorientador = new Servidor();
		// TODO: ver a questão dos discentes
		discentes = new LinkedList<Discente>();
	}

	// construtor para creat
	public Projeto(String nomeProjeto, Date inicioProjeto, Date fimProjeto,
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

	@XmlElement
	public List<Discente> getDiscentes() {
		return discentes;
	}

	public void setDiscentes(List<Discente> discentes) {
		this.discentes = discentes;
	}

	@XmlElement
	public Servidor getOrientador() {
		return orientador;
	}

	public void setOrientador(Servidor orientador) {
		this.orientador = orientador;
	}

	@XmlElement
	public Servidor getCoorientador() {
		return coorientador;
	}

	public void setCoorientador(Servidor coorientador) {
		this.coorientador = coorientador;
	}

	@XmlElement
	public Servidor getColaborador() {
		return colaborador;
	}

	public void setColaborador(Servidor colaborador) {
		this.colaborador = colaborador;
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

	@XmlElement
	public Date getFimProjeto() {
		return fimProjeto;
	}

	public void setFimProjeto(Date fimProjeto) {
		this.fimProjeto = fimProjeto;
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
	public String getNomeTipoProjeto() {
		if (getTipoProjeto() == 'P')
			return "Pesquisa";
		else
			return "Extensão";
	}

	public void setNomeTipoProjeto(String nomeTipoProjeto) {
		this.nomeTipoProjeto = nomeTipoProjeto;
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

}
