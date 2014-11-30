package br.edu.ifpb.qmanager.entidade;

import java.io.Serializable;

public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	private int pessoaId;
	private String nomePessoa;
	private String cpf;
	private String matricula;
	private String endereco;
	private String cep;
	private String telefone;
	private String email;
	private String senha;
	private TipoPessoa tipoPessoa;
	private String registro;

	private DadosBancarios dadosBancarios;

	public Pessoa() {
		dadosBancarios = new DadosBancarios();
		tipoPessoa = new TipoPessoa();
	}

	public Pessoa(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			String senha, DadosBancarios dadosBancarios) {
		setNomePessoa(nomePessoa);
		setCpf(cpf);
		setMatricula(matricula);
		setEndereco(endereco);
		setCep(cep);
		setTelefone(telefone);
		setEmail(email);
		setSenha(senha);
		setDadosBancarios(dadosBancarios);
	}

	public int getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		this.cpf = cpf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		cep = cep.replace(".", "");
		cep = cep.replace("-", "");
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		telefone = telefone.replace("(", "");
		telefone = telefone.replace(")", "");
		telefone = telefone.replace("-", "");
		telefone = telefone.replace(" ", "");
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public DadosBancarios getDadosBancarios() {
		return dadosBancarios;
	}

	public void setDadosBancarios(DadosBancarios dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}

	@Override
	public String toString() {
		return "Pessoa [pessoaId=" + pessoaId + ", nomePessoa=" + nomePessoa
				+ ", cpf=" + cpf + ", matricula=" + matricula + ", endereco="
				+ endereco + ", cep=" + cep + ", telefone=" + telefone
				+ ", email=" + email + ", senha=" + senha + ", registro="
				+ registro + ", dadosBancarios=" + dadosBancarios + "]";
	}

}
