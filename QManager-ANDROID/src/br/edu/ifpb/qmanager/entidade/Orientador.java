package br.edu.ifpb.qmanager.entidade;

import java.io.Serializable;

public class Orientador extends MembroProjeto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String titulacao;
	private String cargo;
	private String localTrabalho;

	public Orientador() {
		super();
	}

	public Orientador(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			String senha, DadosBancarios dadosBancarios, String titulacao,
			String cargo, String localTrabalho) {

		super(nomePessoa, cpf, matricula, endereco, cep, telefone, email,
				senha, dadosBancarios);

		setTitulacao(titulacao);
		setCargo(cargo);
		setLocalTrabalho(localTrabalho);

	}

	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	@Override
	public String toString() {
		return super.toString() + "Orientador [titulacao=" + titulacao
				+ ", cargo=" + cargo + ", localTrabalho=" + localTrabalho + "]";
	}

}
