package br.edu.ifpb.qmanager.entidade;

import java.sql.Date;

/* 
 TABLE `projeto`
 `id_projeto` INT NOT NULL AUTO_INCREMENT,
 `nm_projeto` VARCHAR(45) NOT NULL,
 `dt_inicio_projeto` DATE NOT NULL,
 `dt_fim_projeto` DATE NOT NULL,
 `relatorio_parcial` BLOB NULL,
 `relatorio_final` BLOB NULL,
 `nr_processo` INT NOT NULL, ->> rever essa questão, acho que INT é pequeno
 `tp_projeto` CHAR NOT NULL,
 `edital_id` INT NOT NULL
 */

public class Projeto {

	private int idProjeto;
	private String nomeProjeto;
	private String inicioProjeto;
	private String fimProjeto;
	private String relatorioParcial;
	private String relatorioFinal;
	private int processo;
	private String tipoProjeto;
	private int editalId;

	// construtor para readById
	public Projeto() {}

	// construtor para creat
	public Projeto(String nomeProjeto, String inicioProjeto, String fimProjeto,
			String relatorioParcial, String relatorioFinal, int processo,
			String tipoProjeto, int editalId) {
		setNomeProjeto(nomeProjeto);
		setInicioProjeto(inicioProjeto);
		setFimProjeto(fimProjeto);
		setRelatorioParcial(relatorioParcial);
		setRelatorioFinal(relatorioFinal);
		setProcesso(processo);
		setTipoProjeto(tipoProjeto);
		setEditalId(editalId);
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

	public int getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
	}

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	public Date getInicioProjeto() {
		return converterStringEmData(inicioProjeto);
	}

	// setar quando creat
	public void setInicioProjeto(String inicioProjeto) {
		this.inicioProjeto = inicioProjeto;
	}

	// setar quando creat
	public void setInicioProjeto(Date inicioProjeto) {
		this.inicioProjeto = converterDataEmString(inicioProjeto);
	}

	public Date getFimProjeto() {
		return converterStringEmData(fimProjeto);
	}

	// setar quando creat
	public void setFimProjeto(String fimProjeto) {
		this.fimProjeto = fimProjeto;
	}

	// setar quando read
	public void setFimProjeto(Date fimProjeto) {
		this.fimProjeto = converterDataEmString(fimProjeto);
	}

	public String getRelatorioParcial() {
		return relatorioParcial;
	}

	public void setRelatorioParcial(String relatorioParcial) {
		this.relatorioParcial = relatorioParcial;
	}

	public String getRelatorioFinal() {
		return relatorioFinal;
	}

	public void setRelatorioFinal(String relatorioFinal) {
		this.relatorioFinal = relatorioFinal;
	}

	public int getProcesso() {
		return processo;
	}

	public void setProcesso(int processo) {
		this.processo = processo;
	}

	public String getTipoProjeto() {
		return tipoProjeto;
	}

	public void setTipoProjeto(String tipoProjeto) {
		this.tipoProjeto = tipoProjeto;
	}

	public int getEditalId() {
		return editalId;
	}

	public void setEditalId(int editalId) {
		this.editalId = editalId;
	}

	@Override
	public String toString() {
		return "-- Projeto --\n\n Identificador do Projeto= " + idProjeto
				+ "\nNome do Projeto= " + nomeProjeto + "\nInício do Projeto= "
				+ inicioProjeto + "\nFim do Projeto= " + fimProjeto
				+ "\nRelatório Parcial= " + relatorioParcial
				+ "\nRelatório Final= " + relatorioFinal + "\nProcesso= "
				+ processo + "\nTipo do Projeto= " + tipoProjeto
				+ "\nIdentificador do Edital= " + editalId + "\n\n--\n\n";
	}

}
