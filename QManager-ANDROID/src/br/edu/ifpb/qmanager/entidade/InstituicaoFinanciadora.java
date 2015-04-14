package br.edu.ifpb.qmanager.entidade;

import java.io.Serializable;

public class InstituicaoFinanciadora implements Serializable {

	private static final long serialVersionUID = 8673767195390286482L;

	private int idInstituicaoFinanciadora;

	private String cnpj;

	private String nomeInstituicaoFinanciadora;

	private String sigla;

	private Servidor gestor;

	private String registro;

	public InstituicaoFinanciadora() {
	}

	public InstituicaoFinanciadora(String cnpj,
			String nomeInstituicaoFinanciadora, String sigla, Servidor gestor) {
		this.cnpj = cnpj;
		this.nomeInstituicaoFinanciadora = nomeInstituicaoFinanciadora;
		this.sigla = sigla;
		this.gestor = gestor;
	}

	public int getIdInstituicaoFinanciadora() {
		return idInstituicaoFinanciadora;
	}

	public void setIdInstituicaoFinanciadora(int idInstituicaoFinanciadora) {
		this.idInstituicaoFinanciadora = idInstituicaoFinanciadora;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		cnpj = cnpj.replace(".", "");
		cnpj = cnpj.replace("/", "");
		cnpj = cnpj.replace("-", "");
		this.cnpj = cnpj;
	}

	public String getNomeInstituicaoFinanciadora() {
		return nomeInstituicaoFinanciadora;
	}

	public void setNomeInstituicaoFinanciadora(
			String nomeInstituicaoFinanciadora) {
		this.nomeInstituicaoFinanciadora = nomeInstituicaoFinanciadora;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Servidor getGestor() {
		return gestor;
	}

	public void setGestor(Servidor gestor) {
		this.gestor = gestor;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}
}
