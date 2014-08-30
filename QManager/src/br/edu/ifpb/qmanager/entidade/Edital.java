package br.edu.ifpb.qmanager.entidade;

import java.sql.Date;

public class Edital {

	private int idEdital;
	private int numero;
	private int ano;
	private String inicioInscricoes;
	private String fimInscricoes;
	private String relatorioParcial;
	private String relatorioFinal;
	private int vagas;
	private double bolsaDiscente;
	private double bolsaDocente;
	private String tipoEdital;
	private int programaInstitucionalId;

	public Edital() {
	}

	public Edital(int numero, int ano, String inicioInscricoes,
			String fimInscricoes, String relatorioParcial,
			String relatorioFinal, int vagas, double bolsaDiscente,
			double bolsaDocente, String tipoEdital, int programaInstitucionalId) {
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
		setProgramaInstitucionalId(programaInstitucionalId);
	}

	// Após informar data no formato String, converterData retornará um tipo
	// java.sql.Date,
	// pronto para ser inserido ao banco o///
	private Date converterStringEmData(String data) {

		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");

		java.util.Date dataUtil = null;

		try {
			dataUtil = f.parse(data);
		} catch (java.text.ParseException pe) {
			pe.printStackTrace();
		}

		Date dataJDBC = new java.sql.Date(dataUtil.getTime());

		return dataJDBC;

	}

	private String converterDataEmString(Date data) {
		return data.toString();
	}

	public int getIdEdital() {
		return idEdital;
	}

	public void setIdEdital(int idEdital) {
		this.idEdital = idEdital;
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
		return converterStringEmData(inicioInscricoes);
	}

	// setar quando creat
	public void setInicioInscricoes(String inicioInscricoes) {
		this.inicioInscricoes = inicioInscricoes;
	}

	// setar quando read
	public void setInicioInscricoes(Date inicioInscricoes) {
		this.inicioInscricoes = converterDataEmString(inicioInscricoes);
	}

	public Date getFimInscricoes() {
		return converterStringEmData(fimInscricoes);
	}

	public void setFimInscricoes(String fimInscricoes) {
		this.fimInscricoes = fimInscricoes;
	}

	public void setFimInscricoes(Date fimInscricoes) {
		this.fimInscricoes = converterDataEmString(fimInscricoes);
	}

	public Date getRelatorioParcial() {
		return converterStringEmData(relatorioParcial);
	}

	// setar para creat
	public void setRelatorioParcial(String relatorioParcial) {
		this.relatorioParcial = relatorioParcial;
	}

	// setar para read
	public void setRelatorioParcial(Date relatorioParcial) {
		this.relatorioParcial = converterDataEmString(relatorioParcial);
	}

	public Date getRelatorioFinal() {
		return converterStringEmData(relatorioFinal);
	}

	// setar para creat
	public void setRelatorioFinal(String relatorioFinal) {
		this.relatorioFinal = relatorioFinal;
	}

	// setar para read
	public void setRelatorioFinal(Date relatorioFinal) {
		this.relatorioParcial = converterDataEmString(relatorioFinal);
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

	public String getTipoEdital() {
		return tipoEdital;
	}

	public void setTipoEdital(String tipoEdital) {
		this.tipoEdital = tipoEdital;
	}

	public int getProgramaInstitucionalId() {
		return programaInstitucionalId;
	}

	public void setProgramaInstitucionalId(int programaInstitucionalId) {
		this.programaInstitucionalId = programaInstitucionalId;
	}

	@Override
	public String toString() {
		return "-- Edital --\n\nIdentificador do Edital= " + idEdital
				+ "\nNúmero do Edital= " + numero + "\nAno do Edital= " + ano
				+ "\nInicio das Inscricoes= " + inicioInscricoes
				+ "\nFim das Inscricoes= " + fimInscricoes
				+ "\nRelatório Parcial= " + relatorioParcial
				+ "\nRelatório Final= " + relatorioFinal + "\nVagas= " + vagas
				+ "\nBolsa Discente= " + bolsaDiscente + "\nBolsa Docente= "
				+ bolsaDocente + "\nTipo Edital= " + tipoEdital
				+ "\nIdentificador do Programa Institucional= "
				+ programaInstitucionalId + "\n\n--\n\n";
	}

}
