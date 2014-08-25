package br.edu.ifpb.qmanager.entidade;

public class ContaBancaria {

	private int pessoaId;
	private InstituicaoBancaria instituicaoBancaria;
	private String agencia;
	private String operacao;
	private String conta;

	public ContaBancaria(InstituicaoBancaria instituicaoBancaria,
			String agencia, String operacao, String conta) {

		this.instituicaoBancaria = instituicaoBancaria;
		this.agencia = agencia;
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

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
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

}
