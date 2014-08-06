package entidades;

import java.sql.Date;

/*
 TABLE `participacao`
 `id_participacao` INT NOT NULL AUTO_INCREMENT,
 `pessoa_id` INT NOT NULL,
 `projeto_id` INT NOT NULL,
 `dt_inicio` DATE NOT NULL,
 `dt_fim` DATE NULL,
 `fl_bolsista` TINYINT(1) NOT NULL --> isso tá certo?
 */

public class Participacao implements EntidadeIF {

	private int idParticipacao;
	private int pessoaId;
	private int projetoId;
	private String dataInicio;
	private String dataFim;
	private int bolsista;

	public Participacao(int pessoaId, int projetoId, String dataInicio,
			String dataFim, int bolsista) {
		setPessoaId(pessoaId);
		setProjetoId(projetoId);
		setDataInicio(dataInicio);
		setDataFim(dataFim);
		setBolsista(bolsista);
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

	public int getIdParticipacao() {
		return idParticipacao;
	}

	public void setIdParticipacao(int idParticipacao) {
		this.idParticipacao = idParticipacao;
	}

	public int getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}

	public int getProjetoId() {
		return projetoId;
	}

	public void setProjetoId(int projetoId) {
		this.projetoId = projetoId;
	}

	public Date getDataInicio() {
		return converterData(dataInicio);
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return converterData(dataFim);
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public int getBolsista() {
		return bolsista;
	}

	public void setBolsista(int bolsista) {
		this.bolsista = bolsista;
	}

}
