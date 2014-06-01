package br.edu.ifpb.entidades;

import java.sql.Date;

// Campos da tabela
// N_ano, início_inscrições, fim_inscrições, ano, prazo_relatório_parcial, prazo_relatório_final, número_vagas, valor_bolsa_docente, valor_bolsa_discente, PIE_ID


public class EditalExtensão {
	
	private String NAno;
	private Date InícioInscrições;
	private Date FimInscrições;
	private String Ano;
	private Date PrazoRelatórioParcial;
	private Date PrazoRelatórioFinal;
	private int NúmeroVagas;
	private double ValorBolsaDocente;
	private double ValorBolsaDiscente;
	private int PIE_ID;
	
	//Construtor do edital de Extensão
	public EditalExtensão(String nAno, Date inícioInscrições,
			Date fimInscrições, String ano, Date prazoRelatórioParcial,
			Date prazoRelatórioFinal, int númeroVagas, double valorBolsaDocente,
			double valorBolsaDiscente, int pie_id) {
		
		setNAno(nAno);
		setInícioInscrições(inícioInscrições);
		setFimInscrições(fimInscrições);
		setAno(ano);
		setPrazoRelatórioParcial(prazoRelatórioParcial);
		setPrazoRelatórioFinal(prazoRelatórioFinal);
		setNúmeroVagas(númeroVagas);
		setValorBolsaDocente(valorBolsaDocente);
		setValorBolsaDiscente(valorBolsaDiscente);
		setPIE_ID(pie_id);
	}

	public String getNAno() {
		return NAno;
	}

	public void setNAno(String nAno) {
		NAno = nAno;
	}

	public Date getInícioInscrições() {
		return InícioInscrições;
	}

	public void setInícioInscrições(Date inícioInscrições) {
		InícioInscrições = inícioInscrições;
	}

	public Date getFimInscrições() {
		return FimInscrições;
	}

	public void setFimInscrições(Date fimInscrições) {
		FimInscrições = fimInscrições;
	}

	public String getAno() {
		return Ano;
	}

	public void setAno(String ano) {
		Ano = ano;
	}

	public Date getPrazoRelatórioParcial() {
		return PrazoRelatórioParcial;
	}

	public void setPrazoRelatórioParcial(Date prazoRelatórioParcial) {
		PrazoRelatórioParcial = prazoRelatórioParcial;
	}

	public Date getPrazoRelatórioFinal() {
		return PrazoRelatórioFinal;
	}

	public void setPrazoRelatórioFinal(Date prazoRelatórioFinal) {
		PrazoRelatórioFinal = prazoRelatórioFinal;
	}

	public int getNúmeroVagas() {
		return NúmeroVagas;
	}

	public void setNúmeroVagas(int númeroVagas) {
		NúmeroVagas = númeroVagas;
	}

	public double getValorBolsaDocente() {
		return ValorBolsaDocente;
	}

	public void setValorBolsaDocente(double valorBolsaDocente) {
		ValorBolsaDocente = valorBolsaDocente;
	}

	public double getValorBolsaDiscente() {
		return ValorBolsaDiscente;
	}

	public void setValorBolsaDiscente(double valorBolsaDiscente) {
		ValorBolsaDiscente = valorBolsaDiscente;
	}

	public int getPIE_ID() {
		return PIE_ID;
	}

	public void setPIE_ID(int pie_id) {
		PIE_ID = pie_id;
	}
	
}
