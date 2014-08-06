package entidades;

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

public class Projeto implements EntidadeIF {

	private int idProjeto;
	private String nomeProjeto;
	private String inicioProjeto;
	private String fimProjeto;
	private String relatorioParcial;
	private String relatorioFinal;
	private int processo;
	private String tipoProjeto;
	private int editalId;

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
	private Date converterData(String data) {

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
		return converterData(inicioProjeto);
	}

	public void setInicioProjeto(String inicioProjeto) {
		this.inicioProjeto = inicioProjeto;
	}

	public Date getFimProjeto() {
		return converterData(fimProjeto);
	}

	public void setFimProjeto(String fimProjeto) {
		this.fimProjeto = fimProjeto;
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

}
