package br.edu.ifpb.qmanager.entidade;

import java.sql.Date;

public class Participacao {

	private int idParticipacao;
	private int pessoaId;
	private int projetoId;
	private String dataInicio;
	private String dataFim;
	private int bolsista;

	public Participacao() {
	}

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
		return converterStringEmData(dataInicio);
	}

	// setar para creat
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	// setar para read
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = converterDataEmString(dataInicio);
	}

	public Date getDataFim() {
		return converterStringEmData(dataFim);
	}

	// setar para creat
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	// setar para read
	public void setDataFim(Date dataFim) {
		this.dataFim = converterDataEmString(dataFim);
	}

	public int getBolsista() {
		return bolsista;
	}

	public void setBolsista(int bolsista) {
		this.bolsista = bolsista;
	}

	@Override
	public String toString() {
		return "-- Participacao--\n\nIdentificador Participação= "
				+ idParticipacao + "\nPessoa Identificador= " + pessoaId
				+ "\nProjeto Identificador= " + projetoId + "\nData Início= "
				+ dataInicio + "\nData Fim= " + dataFim + "\nBolsista= "
				+ bolsista + "\n\n--\n\n";
	}

}
