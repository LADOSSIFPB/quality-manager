package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

public class Edital {

	private int idEdital;
	private String arquivo;
	private int numero;
	private int ano;
	private Date inicioInscricoes;
	private Date fimInscricoes;
	private Date relatorioParcial;
	private Date relatorioFinal;
	private int vagas;
	private double bolsaDiscente;
	private double bolsaDocente;
	private char tipoEdital;
	private ProgramaInstitucional programaInstitucional;
	private Gestor gestor;
	private Date registro;

	public Edital() {
		programaInstitucional = new ProgramaInstitucional();
		gestor = new Gestor();
	}

	public Edital(String arquivo, int numero, int ano, Date inicioInscricoes,
			Date fimInscricoes, Date relatorioParcial, Date relatorioFinal,
			int vagas, double bolsaDiscente, double bolsaDocente,
			char tipoEdital, ProgramaInstitucional programaInstitucional,
			Gestor gestor) {
		setArquivo(arquivo);
		setNumero(numero);
		setAno(ano);
		setInicioInscricoes(inicioInscricoes);
		setFimInscricoes(fimInscricoes);
		setRelatorioParcial(relatorioParcial);
		setRelatorioFinal(relatorioFinal);
		setVagas(vagas);
		setBolsaDiscente(bolsaDiscente);
		setBolsaDocente(bolsaDocente);
		setTipoEdital(tipoEdital);
		setProgramaInstitucional(programaInstitucional);
		setGestor(gestor);
	}

	public int getIdEdital() {
		return idEdital;
	}

	public void setIdEdital(int idEdital) {
		this.idEdital = idEdital;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numeroAno) {
		this.numero = numeroAno;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Date getInicioInscricoes() {
		return inicioInscricoes;
	}

	public void setInicioInscricoes(Date inicioInscricoes) {
		this.inicioInscricoes = inicioInscricoes;
	}

	public Date getFimInscricoes() {
		return fimInscricoes;
	}

	public void setFimInscricoes(Date fimInscricoes) {
		this.fimInscricoes = fimInscricoes;
	}

	public Date getRelatorioParcial() {
		return relatorioParcial;
	}

	public void setRelatorioParcial(Date relatorioParcial) {
		this.relatorioParcial = relatorioParcial;
	}

	public Date getRelatorioFinal() {
		return relatorioFinal;
	}

	public void setRelatorioFinal(Date relatorioFinal) {
		this.relatorioFinal = relatorioFinal;
	}

	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}

	public double getBolsaDiscente() {
		return bolsaDiscente;
	}

	public void setBolsaDiscente(double bolsaDiscente) {
		this.bolsaDiscente = bolsaDiscente;
	}

	public double getBolsaDocente() {
		return bolsaDocente;
	}

	public void setBolsaDocente(double bolsaDocente) {
		this.bolsaDocente = bolsaDocente;
	}

	public char getTipoEdital() {
		return tipoEdital;
	}

	public void setTipoEdital(char tipoEdital) {
		this.tipoEdital = tipoEdital;
	}

	public ProgramaInstitucional getProgramaInstitucional() {
		return programaInstitucional;
	}

	public void setProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {
		this.programaInstitucional = programaInstitucional;
	}

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "Edital [idEdital=" + idEdital + ", arquivo=" + arquivo
				+ ", numero=" + numero + ", ano=" + ano + ", inicioInscricoes="
				+ inicioInscricoes + ", fimInscricoes=" + fimInscricoes
				+ ", relatorioParcial=" + relatorioParcial
				+ ", relatorioFinal=" + relatorioFinal + ", vagas=" + vagas
				+ ", bolsaDiscente=" + bolsaDiscente + ", bolsaDocente="
				+ bolsaDocente + ", tipoEdital=" + tipoEdital
				+ ", programaInstitucional=" + programaInstitucional
				+ ", gestor=" + gestor + ", registro=" + registro + "]";
	}

}