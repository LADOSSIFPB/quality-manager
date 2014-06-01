package br.edu.ifpb.entidades;

import java.sql.Date;

//		Campos da tabela
//N_ano, Inicio_das_inscrições, Fim_das_inscrições, Ano, Prazo_do_relatório_parcial
//Prazo_do_relatório_final, Número_de_vagas, Valor_bolsa_docente, Valor Bolsa discente
//PIP_id_programa


public class EditalPesquisa {
	
	private String N_ano;
	private Date ÍnicioInscrições;
	private Date FimInscrições;
	private String Ano;
	private Date PrazoRelatórioParcial;
	private Date PrazoRelatorioFinal;
	private int NúmeroDeVagas;
	private double ValorBolsaDocente;
	private double ValorBolsaDiscente;
	private int PIP_ID;
	
	
	//Construtor do edital de Pesquisa
	public EditalPesquisa(String n_ano, Date ínicioinscrições,
			Date fiminscrições, String ano, Date prazorelatórioparcial,
			Date prazorelatoriofinal, int númerovagas, double valorbolsadocente,
			double valorbolsadiscente, int pip_id) {
		
		setN_ano(n_ano);
		setÍnicioInscrições(ínicioinscrições);
		setFimInscrições(fiminscrições);
		setAno(ano);
		setPrazoRelatórioParcial(prazorelatórioparcial);
		setPrazoRelatorioFinal(prazorelatoriofinal);
		setNúmeroDeVagas(númerovagas);
		setValorBolsaDocente(valorbolsadocente);
		setValorBolsaDiscente(valorbolsadiscente);
		setPIP_ID(pip_id);
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


	public int getPIP_ID() {
		return PIP_ID;
	}


	public void setPIP_ID(int pIP_ID) {
		PIP_ID = pIP_ID;
	}

	

}
