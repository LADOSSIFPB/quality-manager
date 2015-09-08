package br.edu.ifpb.qmanager.entidade;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "edital")
public class Edital {

	private int idEdital;

	private int numero;

	private int ano;

	private String numAno;

	private String titulo;

	private String descricao;

	private Date inicioInscricoes;

	private Date fimInscricoes;

	private Date inicioAvaliacao;

	private Date fimAvaliacao;

	private Date resultadoPreliminar;

	private Date inicioRecursos;

	private Date fimRecursos;

	private Date resultadoFinal;

	private Date inicioAtividades;

	private Date relatorioParcial;

	private Date relatorioFinal;
	
	private int quantidadeProjetosAprovados;
	
	private int vagasBolsistasDiscentePorProjeto;
	
	private int vagasVoluntariosPorProjeto;

	private double bolsaDiscente;

	private int vagasBolsistasDocentePorProjeto;
	
	private double bolsaDocente;

	private ProgramaInstitucional programaInstitucional;
	
	private List<Campus> campiSubmissao;

	private Servidor gestor;

	private Date registro;

	public Edital() {}

	@XmlElement
	public int getIdEdital() {
		return idEdital;
	}

	public void setIdEdital(int idEdital) {
		this.idEdital = idEdital;
	}

	@XmlElement
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numeroAno) {
		this.numero = numeroAno;
	}

	@XmlElement
	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@XmlElement
	public String getNumAno() {
		return Integer.toString(getNumero()) + "/" + Integer.toString(getAno());
	}

	@XmlElement
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@XmlElement
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@XmlElement
	public Date getInicioInscricoes() {
		return inicioInscricoes;
	}

	public void setInicioInscricoes(Date inicioInscricoes) {
		this.inicioInscricoes = inicioInscricoes;
	}

	@XmlElement
	public Date getFimInscricoes() {
		return fimInscricoes;
	}

	public void setFimInscricoes(Date fimInscricoes) {
		this.fimInscricoes = fimInscricoes;
	}

	@XmlElement
	public Date getInicioAvaliacao() {
		return inicioAvaliacao;
	}

	public void setInicioAvaliacao(Date inicioAvaliacao) {
		this.inicioAvaliacao = inicioAvaliacao;
	}

	@XmlElement
	public Date getFimAvaliacao() {
		return fimAvaliacao;
	}

	public void setFimAvaliacao(Date fimAvaliacao) {
		this.fimAvaliacao = fimAvaliacao;
	}

	@XmlElement
	public Date getResultadoPreliminar() {
		return resultadoPreliminar;
	}

	public void setResultadoPreliminar(Date resultadoPreliminar) {
		this.resultadoPreliminar = resultadoPreliminar;
	}

	@XmlElement
	public Date getInicioRecursos() {
		return inicioRecursos;
	}

	public void setInicioRecursos(Date inicioRecursos) {
		this.inicioRecursos = inicioRecursos;
	}
	
	@XmlElement
	public Date getFimRecursos() {
		return fimRecursos;
	}

	public void setFimRecursos(Date fimRecursos) {
		this.fimRecursos = fimRecursos;
	}

	@XmlElement
	public Date getResultadoFinal() {
		return resultadoFinal;
	}

	public void setResultadoFinal(Date resultadoFinal) {
		this.resultadoFinal = resultadoFinal;
	}

	@XmlElement
	public Date getInicioAtividades() {
		return inicioAtividades;
	}

	public void setInicioAtividades(Date inicioAtividades) {
		this.inicioAtividades = inicioAtividades;
	}

	@XmlElement
	public Date getRelatorioParcial() {
		return relatorioParcial;
	}

	public void setRelatorioParcial(Date relatorioParcial) {
		this.relatorioParcial = relatorioParcial;
	}

	@XmlElement
	public Date getRelatorioFinal() {
		return relatorioFinal;
	}

	public void setRelatorioFinal(Date relatorioFinal) {
		this.relatorioFinal = relatorioFinal;
	}

	@XmlElement
	public double getBolsaDiscente() {
		return bolsaDiscente;
	}

	public void setBolsaDiscente(double bolsaDiscente) {
		this.bolsaDiscente = bolsaDiscente;
	}

	@XmlElement
	public double getBolsaDocente() {
		return bolsaDocente;
	}

	public void setBolsaDocente(double bolsaDocente) {
		this.bolsaDocente = bolsaDocente;
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
	public Servidor getGestor() {
		return gestor;
	}

	public void setGestor(Servidor gestor) {
		this.gestor = gestor;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}
	
	@XmlElement
	public int getQuantidadeProjetosAprovados() {
		return quantidadeProjetosAprovados;
	}

	public void setQuantidadeProjetosAprovados(int quantidadeProjetosAprovados) {
		this.quantidadeProjetosAprovados = quantidadeProjetosAprovados;
	}

	@XmlElement
	public int getVagasBolsistasDiscentePorProjeto() {
		return vagasBolsistasDiscentePorProjeto;
	}

	public void setVagasBolsistasDiscentePorProjeto(
			int vagasBolsistasDiscentePorProjeto) {
		this.vagasBolsistasDiscentePorProjeto = vagasBolsistasDiscentePorProjeto;
	}

	@XmlElement
	public int getVagasVoluntariosPorProjeto() {
		return vagasVoluntariosPorProjeto;
	}

	public void setVagasVoluntariosPorProjeto(int vagasVoluntariosPorProjeto) {
		this.vagasVoluntariosPorProjeto = vagasVoluntariosPorProjeto;
	}

	@XmlElement
	public int getVagasBolsistasDocentePorProjeto() {
		return vagasBolsistasDocentePorProjeto;
	}

	public void setVagasBolsistasDocentePorProjeto(
			int vagasBolsistasDocentePorProjeto) {
		this.vagasBolsistasDocentePorProjeto = vagasBolsistasDocentePorProjeto;
	}

	@XmlElement
	public List<Campus> getCampiSubmissao() {
		return campiSubmissao;
	}

	public void setCampiSubmissao(List<Campus> campiSubmissao) {
		this.campiSubmissao = campiSubmissao;
	}

	@Override
	public String toString() {
		return "Edital [idEdital=" + idEdital + ", numAno=" + numAno
				+ ", titulo=" + titulo + ", descricao=" + descricao + "]";
	}		
}
