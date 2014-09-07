package br.edu.ifpb.qmanager.entidade;

import java.sql.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="dados_bancarios")
public class DadosBancarios {

	private InstituicaoBancaria instituicaoBancaria;
	private String operacao;
	private String conta;
	private Date registro;
	private Pessoa pessoa;

	public DadosBancarios() {
	}

	public DadosBancarios(InstituicaoBancaria instituicaoBancaria,
			String operacao, String conta, Pessoa pessoa) {
		setInstituicaoBancaria(instituicaoBancaria);
		setOperacao(operacao);
		setConta(conta);
		setPessoa(pessoa);
	}

	@XmlElement
	public InstituicaoBancaria getInstituicaoBancaria() {
		return instituicaoBancaria;
	}

	public void setInstituicaoBancaria(InstituicaoBancaria instituicaoBancaria) {
		this.instituicaoBancaria = instituicaoBancaria;
	}

	@XmlElement
	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	@XmlElement
	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	@XmlElement
	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	@XmlElement
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "DadosBancarios [instituicaoBancaria=" + instituicaoBancaria
				+ ", operacao=" + operacao + ", conta=" + conta + ", registro="
				+ registro + ", pessoa=" + pessoa + "]";
	}

}
