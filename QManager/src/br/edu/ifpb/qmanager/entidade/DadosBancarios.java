package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dados_bancarios")
public class DadosBancarios {

	private int idDadosBancarios;
	private InstituicaoBancaria instituicaoBancaria;
	private String operacao;
	private String conta;
	private Date registro;

	public DadosBancarios() {
		instituicaoBancaria = new InstituicaoBancaria();
	}

	public DadosBancarios(InstituicaoBancaria instituicaoBancaria,
			String operacao, String conta) {
		setInstituicaoBancaria(instituicaoBancaria);
		setOperacao(operacao);
		setConta(conta);
	}

	@XmlElement
	public int getIdDadosBancarios() {
		return idDadosBancarios;
	}

	public void setIdDadosBancarios(int idDadosBancarios) {
		this.idDadosBancarios = idDadosBancarios;
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

	@Override
	public String toString() {
		return "DadosBancarios [instituicaoBancaria=" + instituicaoBancaria
				+ ", operacao=" + operacao + ", conta=" + conta + ", registro="
				+ registro + "]";
	}

}
