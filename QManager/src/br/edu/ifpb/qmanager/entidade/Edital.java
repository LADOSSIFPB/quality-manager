package br.edu.ifpb.qmanager.entidade;

import java.sql.Date;

/*
 TABLE `edital`
 `id_edital` INT NOT NULL AUTO_INCREMENT,
 `numero_ano` VARCHAR(6) NOT NULL,
 `dt_inicio_inscricoes` DATE NOT NULL,
 `dt_fim_inscricoes` DATE NOT NULL,
 `dt_relatorio_parcial` DATE NOT NULL,
 `dt_relatorio_final` DATE NOT NULL,
 `nr_vagas` INT NOT NULL,
 `vl_bolsa_discente` DOUBLE NOT NULL,
 `vl_bolsa_docente` DOUBLE NOT NULL,
 `tp_edital` CHAR NOT NULL,
 `programa_institucional_id`
 */

public class Edital {

	private int idEdital;
	private String numeroAno;
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

	public Edital(String numeroAno, String inicioInscricoes,
			String fimInscricoes, String relatorioParcial,
			String relatorioFinal, int vagas, double bolsaDiscente,
			double bolsaDocente, String tipoEdital, int programaInstitucionalId) {
		setNumeroAno(numeroAno);
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

	public String getNumeroAno() {
		return numeroAno;
	}

	public void setNumeroAno(String numeroAno) {
		this.numeroAno = numeroAno;
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
				+ "\nNúmero do Ano= " + numeroAno + "\nInicio das Inscricoes= "
				+ inicioInscricoes + "\nFim das Inscricoes= " + fimInscricoes
				+ "\nRelatório Parcial= " + relatorioParcial
				+ "\nRelatório Final= " + relatorioFinal + "\nVagas= " + vagas
				+ "\nBolsa Discente= " + bolsaDiscente + "\nBolsa Docente= "
				+ bolsaDocente + "\nTipo Edital= " + tipoEdital
				+ "\nIdentificador do Programa Institucional= "
				+ programaInstitucionalId + "\n\n--\n\n";
	}

}
