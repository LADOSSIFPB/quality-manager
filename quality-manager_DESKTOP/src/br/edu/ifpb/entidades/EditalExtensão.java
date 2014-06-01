package br.edu.ifpb.entidades;

import java.sql.Date;

//		Campos da tabela
//N_ano, Inicio_das_inscrições, Fim_das_inscrições, Ano, Prazo_do_relatório_parcial
//Prazo_do_relatório_final, Número_de_vagas, Valor_bolsa_docente, Valor Bolsa discente
//PIE_id_programa


public class EditalExtensão {
	
	private String N_ano;
	private Date ÍnicioInscrições;
	private Date FimÍnscrições;
	private String Ano;
	private Date PrazoRelatórioParcial;
	private Date PrazoRelatórioFinal;
	private int NúmeroDeVagas;
	private double ValorBolsaDocente;
	private double ValorBolsaDiscente;
	private int PIE_ID;
	
	
	//Construtor do edital de Extensão
	public EditalExtensão(String n_ano, Date ínicioinscrições,
			Date fimínscrições, String ano, Date prazorelatórioparcial,
			Date prazorelatóriofinal, int númerovagas, double valorbolsadocente,
			double valorbolsadiscente, int pie_id) {
		
		setN_ano(n_ano);
		setÍnicioInscrições(ínicioinscrições);
		setFimÍnscrições(fimínscrições);
		setAno(ano);
		setPrazoRelatórioParcial(prazorelatórioparcial);
		setPrazoRelatórioFinal(prazorelatóriofinal);
		setNúmeroDeVagas(númerovagas);
		setValorBolsaDocente(valorbolsadocente);
		setValorBolsaDiscente(valorbolsadiscente);
		setPIE_ID(pie_id);
	}


	public String getN_ano() {
		return N_ano;
	}


	public void setN_ano(String n_ano) {
		N_ano = n_ano;
	}


	public Date getÍnicioInscrições() {
		return ÍnicioInscrições;
	}


	public void setÍnicioInscrições(Date ínicioInscrições) {
		ÍnicioInscrições = ínicioInscrições;
	}


	public Date getFimÍnscrições() {
		return FimÍnscrições;
	}


	public void setFimÍnscrições(Date fimÍnscrições) {
		FimÍnscrições = fimÍnscrições;
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


	public int getPIE_ID() {
		return PIE_ID;
	}


	public void setPIE_ID(int pIE_ID) {
		PIE_ID = pIE_ID;
	}
}