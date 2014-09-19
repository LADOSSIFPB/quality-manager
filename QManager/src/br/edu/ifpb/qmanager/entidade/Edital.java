package br.edu.ifpb.qmanager.entidade;

import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "edital")
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
	private Date registro;
	private ProgramaInstitucional programaInstitucional;

	public Edital() {
	}

	public Edital(String arquivo, int numero, int ano, java.util.Date inicioInscricoes,
			java.util.Date fimInscricoes, java.util.Date relatorioParcial, java.util.Date relatorioFinal,
			int vagas, double bolsaDiscente, double bolsaDocente,
			char tipoEdital, ProgramaInstitucional programaInstitucional) {
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
	public Date getInicioInscricoes() {
		return inicioInscricoes;
	}

	public void setInicioInscricoes(Date inicioInscricoes) {
		this.inicioInscricoes = inicioInscricoes;
	}

	public void setInicioInscricoes(java.util.Date inicioInscricoes) {
		Date temp = new Date(inicioInscricoes.getTime());
		this.inicioInscricoes = temp;
	}
	
	@XmlElement
	public Date getFimInscricoes() {
		return fimInscricoes;
	}

	public void setFimInscricoes(Date fimInscricoes) {
		this.fimInscricoes = fimInscricoes;
	}
	
	public void setFimInscricoes(java.util.Date fimInscricoes) {
		Date temp = new Date(fimInscricoes.getTime());
		this.fimInscricoes = temp;
	}

	@XmlElement
	public Date getRelatorioParcial() {
		return relatorioParcial;
	}

	public void setRelatorioParcial(Date relatorioParcial) {
		this.relatorioParcial = relatorioParcial;
	}
	
	public void setRelatorioParcial(java.util.Date relatorioParcial) {
		Date temp = new Date(relatorioParcial.getTime());
		this.relatorioParcial = temp;
	}

	@XmlElement
	public Date getRelatorioFinal() {
		return relatorioFinal;
	}

	public void setRelatorioFinal(Date relatorioFinal) {
		this.relatorioFinal = relatorioFinal;
	}
	
	public void setRelatorioFinal(java.util.Date relatorioFinal) {
		Date temp = new Date(relatorioFinal.getTime());
		this.relatorioFinal = temp;
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
	public char getTipoEdital() {
		return tipoEdital;
	}

	public void setTipoEdital(char tipoEdital) {
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
				+ bolsaDocente + ", tipoEdital=" + tipoEdital + ", registro="
				+ registro + ", programaInstitucional=" + programaInstitucional
				+ "]";
	}

}
