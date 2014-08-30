package br.edu.ifpb.qmanager.entidade;

import java.util.Date;

public abstract class Pessoa {

	private int pessoaId;
	private String nomePessoa;
	private String cpf;
	private String matricula;
	private String endereco;
	private String cep;
	private String telefone;
	private String email;

	private Usuario usuario;

	private DadosBancarios dadosBancarios;

	private Date registro;

	public Pessoa() {
	}

	public Pessoa(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			Usuario usuario, DadosBancarios dadosBancarios) {
		this.nomePessoa = nomePessoa;
		this.cpf = cpf;
		this.matricula = matricula;
		this.endereco = endereco;
		this.cep = cep;
		this.telefone = telefone;
		this.email = email;
		this.usuario = usuario;
		this.dadosBancarios = dadosBancarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public DadosBancarios getDadosBancarios() {
		return dadosBancarios;
	}

	public void setDadosBancarios(DadosBancarios dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "-- Pessoa --\n\nPessoa Identificador= " + pessoaId
				+ "\nNome da Pessoa= " + nomePessoa + "\nCPF= " + cpf
				+ "\nMatrícula= " + matricula + "\nEndereço= " + endereco
				+ "\nCEP= " + cep + "\nTelefone= " + telefone + "\nEmail="
				+ email + "\n\n--\n\n";
	}

}
