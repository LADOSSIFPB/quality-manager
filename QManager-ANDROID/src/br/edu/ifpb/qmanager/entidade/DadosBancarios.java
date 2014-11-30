package br.edu.ifpb.qmanager.entidade;

import java.io.Serializable;

public class DadosBancarios implements Serializable {

	private static final long serialVersionUID = 1L;

	private InstituicaoBancaria instituicaoBancaria;
	private String operacao;
	private String conta;
	private String registro;

	public DadosBancarios() {
		instituicaoBancaria = new InstituicaoBancaria();
	}

	public DadosBancarios(InstituicaoBancaria instituicaoBancaria,
			String operacao, String conta) {
		setInstituicaoBancaria(instituicaoBancaria);
		setOperacao(operacao);
		setConta(conta);
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

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return "DadosBancarios [instituicaoBancaria=" + instituicaoBancaria
				+ ", operacao=" + operacao + ", conta=" + conta + ", registro="
				+ registro + "]";
	}

}
