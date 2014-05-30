package br.edu.ifpb.entidades;

import java.util.Date;

//		Campos da tabela
//N_ano, Inicio_das_inscrições, Fim_das_inscrições, Ano, Prazo_do_relatório_parcial
//Prazo_do_relatório_final, Número_de_vagas, Valor_bolsa_docente, Valor Bolsa discente
//PIE_id_programa


public class EditalExtensão {
	
	private String N_ano;
	private Date InicioInscrições;
	private Date FimInscrições;
	private String Ano;
	private Date PrazoRelatórioParcial;
	private Date PrazoRelatorioFinal;
	private int NúmeroDeVagas;
	private double ValorBolsaDocente;
	private double ValorBolsaDiscente;
	private int PIE_id;
	
	
	//Construtor do edital de Extensão
	public EditalExtensão(String n_ano, Date inicioInscrições,
			Date fimInscrições, String ano, Date prazoRelatórioParcial,
			Date prazoRelatorioFinal, int númeroDeVagas, double valorBolsaDocente,
			double valorBolsaDiscente, int pie_id) {
		
		setN_ano(n_ano);
		setInicioInscrições(inicioInscrições);
		setFimInscrições(fimInscrições);
		setAno(ano);
		setPrazoRelatórioParcial(prazoRelatórioParcial);
		setPrazoRelatorioFinal(prazoRelatorioFinal);
		setNúmeroDeVagas(númeroDeVagas);
		setValorBolsaDocente(valorBolsaDocente);
		setValorBolsaDiscente(valorBolsaDiscente);
		setPIE_id(pie_id);
	}

	public String getN_ano() {
		return N_ano;
	}

	public void setN_ano(String n_ano) {
		N_ano = n_ano;
	}

	public Date getInicioInscrições() {
		return InicioInscrições;
	}

	public void setInicioInscrições(Date inicioInscrições) {
		InicioInscrições = inicioInscrições;
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

	public Date getPrazoRelatorioFinal() {
		return PrazoRelatorioFinal;
	}

	public void setPrazoRelatorioFinal(Date prazoRelatorioFinal) {
		PrazoRelatorioFinal = prazoRelatorioFinal;
	}

	public int getNúmeroDeVagas() {
		return NúmeroDeVagas;
	}

	public void setNúmeroDeVagas(int númeroDeVagas) {
		NúmeroDeVagas = númeroDeVagas;
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

	public int getPIE_id() {
		return PIE_id;
	}

	public void setPIE_id(int pIE_id) {
		PIE_id = pIE_id;
	}
	
}