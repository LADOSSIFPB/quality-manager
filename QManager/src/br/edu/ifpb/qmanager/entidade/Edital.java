package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "edital")
public class Edital {

	private int idEdital;

	private String arquivo;

	private int numero;

	private int ano;

	private String titulo;

	private String descricao;

	private Date inicioInscricoes;

	private Date fimInscricoes;

	private Date inicioAvaliacao;

	private Date fimAvaliacao;

	private Date resultadoPreliminar;

	private Date receberRecursos;

	private Date resultadoFinal;

	private Date inicioAtividades;

	private Date relatorioParcial;

	private Date relatorioFinal;

	private int vagas;

	private double bolsaDiscente;

	private double bolsaDocente;

	private ProgramaInstitucional programaInstitucional;

	private Servidor gestor;

	private TipoEdital tipoEdital;

	private Date registro;

	private String numAno;

	public Edital() {
		programaInstitucional = new ProgramaInstitucional();
		gestor = new Servidor();
		tipoEdital = new TipoEdital();
	}

	public Edital(String arquivo, int numero, int ano, String descricao,
			Date inicioInscricoes, Date fimInscricoes, Date relatorioParcial,
			Date relatorioFinal, int vagas, double bolsaDiscente,
			double bolsaDocente, ProgramaInstitucional programaInstitucional,
			Servidor gestor) {
		setArquivo(arquivo);
		setNumero(numero);
		setAno(ano);
		setDescricao(descricao);
		setInicioInscricoes(inicioInscricoes);
		setFimInscricoes(fimInscricoes);
		setRelatorioParcial(relatorioParcial);
		setRelatorioFinal(relatorioFinal);
		setVagas(vagas);
		setBolsaDiscente(bolsaDiscente);
		setBolsaDocente(bolsaDocente);
		setProgramaInstitucional(programaInstitucional);
		setGestor(gestor);
	}

	@XmlElement
	public int getIdEdital() {
		return idEdital;
	}

	public void setIdEdital(int idEdital) {
		this.idEdital = idEdital;
	}

	@XmlElement
	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
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
	public Date getReceberRecursos() {
		return receberRecursos;
	}

	public void setReceberRecursos(Date receberRecursos) {
		this.receberRecursos = receberRecursos;
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
	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
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
	public TipoEdital getTipoEdital() {
		return tipoEdital;
	}

	public void setTipoEdital(TipoEdital tipoEdital) {
		this.tipoEdital = tipoEdital;
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
	public String getNumAno() {
		this.numAno = this.numero + "/" + this.ano;
		return this.numAno;
	}

	@Override
	public String toString() {
		return "Edital [idEdital=" + idEdital + ", arquivo=" + arquivo
				+ ", numero=" + numero + ", ano=" + ano + ", titulo=" + titulo
				+ ", descricao=" + descricao + ", inicioInscricoes="
				+ inicioInscricoes + ", fimInscricoes=" + fimInscricoes
				+ ", relatorioParcial=" + relatorioParcial
				+ ", relatorioFinal=" + relatorioFinal + ", vagas=" + vagas
				+ ", bolsaDiscente=" + bolsaDiscente + ", bolsaDocente="
				+ bolsaDocente + ", tipoEdital=" + tipoEdital
				+ ", programaInstitucional=" + programaInstitucional
				+ ", gestor=" + gestor + ", registro=" + registro + ", numAno="
				+ numAno + "]";
	}

}
