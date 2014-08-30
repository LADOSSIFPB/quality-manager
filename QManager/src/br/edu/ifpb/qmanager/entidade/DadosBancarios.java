package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

public class DadosBancarios {

	private int pessoaId;
	private InstituicaoBancaria instituicaoBancaria;
	private String operacao;
	private String conta;
	private Date registro;

	public DadosBancarios() {
	}

	public DadosBancarios(InstituicaoBancaria instituicaoBancaria,
			String agencia, String operacao, String conta) {

		this.instituicaoBancaria = instituicaoBancaria;
		this.operacao = operacao;
		this.conta = conta;
	}

	public int getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}

	public InstituicaoBancaria getInstituicaoBancaria() {
		return instituicaoBancaria;
	}

	public void setInstituicaoBancaria(InstituicaoBancaria instituicaoBancaria) {
		this.instituicaoBancaria = instituicaoBancaria;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

}
