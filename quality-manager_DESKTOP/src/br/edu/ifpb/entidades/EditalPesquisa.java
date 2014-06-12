package br.edu.ifpb.entidades;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// Campos da tabela
// N_ano, início_inscrições, fim_inscrições, ano, prazo_relatório_parcial, prazo_relatório_final, número_vagas, valor_bolsa_docente, valor_bolsa_discente, PIP_ID


public class EditalPesquisa implements Entidade {
	
	private String NAno;
	private Date InícioInscrições;
	private Date FimInscrições;
	private String Ano;
	private Date PrazoRelatórioParcial;
	private Date PrazoRelatórioFinal;
	private int NúmeroVagas;
	private double ValorBolsaDocente;
	private double ValorBolsaDiscente;
	private int PIP_ID;
	
	
	// Construtor do Edital de Pesquisa
	public EditalPesquisa(String nAno, String inícioInscrições,
			String fimInscrições, String ano, String prazoRelatórioParcial,
			String prazoRelatorioFinal, int númeroVagas, double valorBolsaDocente,
			double valorBolsaDiscente, int pip_id) throws ParseException {
		
		setNAno(nAno);
		setInícioInscrições(recuperarDataJDBC(inícioInscrições));
		setFimInscrições(recuperarDataJDBC(fimInscrições));
		setAno(ano);
		setPrazoRelatórioParcial(recuperarDataJDBC(prazoRelatórioParcial));
		setPrazoRelatórioFinal(recuperarDataJDBC(prazoRelatorioFinal));
		setNúmeroVagas(númeroVagas);
		setValorBolsaDocente(valorBolsaDocente);
		setValorBolsaDiscente(valorBolsaDiscente);
		setPIP_ID(pip_id);
		
	}
	
	//Após informar data no formato String, ele retornara um tipo java.sql.Date,
	//Pronto para ser inserido ao banco.
	public Date recuperarDataJDBC(String data) throws ParseException{
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		
		java.util.Date dataUtil = f.parse(data);
		
		java.sql.Date dataJDBC = new java.sql.Date(dataUtil.getTime());
		return dataJDBC;
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

	public int getPIP_ID() {
		return PIP_ID;
	}

	public void setPIP_ID(int pIP_ID) {
		PIP_ID = pIP_ID;
	}
	
}
