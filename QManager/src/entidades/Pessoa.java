package entidades;

import java.util.Date;

/*
 TABLE `pessoa` (
 `id_pessoa` INT NOT NULL AUTO_INCREMENT,
 `nm_pessoa` VARCHAR(45) NOT NULL,
 `nr_cpf` CHAR(11) NOT NULL,
 `nr_matricula` VARCHAR(20) NOT NULL,
 `nm_endereco` VARCHAR(45) NOT NULL,
 `nm_cep` CHAR(8) NOT NULL,
 `nm_telefone` VARCHAR(15) NOT NULL,
 `nm_email` VARCHAR(45) NOT NULL
 */

/*
 TABLE `dados_bancarios` (
 `pessoa_id` INT NOT NULL,
 `instituicao_bancaria_id` INT NOT NULL,
 `nr_operacao` INT,
 `nr_conta` INT NOT NULL
 */

public abstract class Pessoa {

	private int pessoaId;
	private Usuario usuario;
	private String nomePessoa;
	private String cpf;
	private String matricula;
	private String endereco;
	private String cep;
	private String telefone;
	private String email;

	private ContaBancaria contaBancaria;

	private Date registro;

	public Pessoa() {
	}

	public Pessoa(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			Usuario usuario, ContaBancaria contaBancaria) {
		this.nomePessoa = nomePessoa;
		this.cpf = cpf;
		this.matricula = matricula;
		this.endereco = endereco;
		this.cep = cep;
		this.telefone = telefone;
		this.email = email;
		this.usuario = usuario;
		this.contaBancaria = contaBancaria;
	}

	@Override
	public String toString() {
		return "-- Pessoa --\n\nPessoa Identificador= " + pessoaId
				+ "\nNome da Pessoa= " + nomePessoa + "\nCPF= " + cpf
				+ "\nMatrícula= " + matricula + "\nEndereço= " + endereco
				+ "\nCEP= " + cep + "\nTelefone= " + telefone + "\nEmail="
				+ email;
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

	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
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
}
