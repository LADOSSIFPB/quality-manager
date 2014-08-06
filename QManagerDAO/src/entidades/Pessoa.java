package entidades;

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

public abstract class Pessoa implements EntidadeIF {
	
	private int pessoaId;
	private String nomePessoa;
	private String cpf;
	private String matricula;
	private String endereco;
	private String cep;
	private String telefone;
	private String email;

	private int instituicaoBancariaId;
	private String operacao;
	private String conta;

	public Pessoa(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			int instituicaoBancariaId, String operacao, String conta) {
		setNomePessoa(nomePessoa);
		setCpf(cpf);
		setMatricula(matricula);
		setEndereco(endereco);
		setCep(cep);
		setTelefone(telefone);
		setEmail(email);

		setInstituicaoBancariaId(instituicaoBancariaId);
		setOperacao(operacao);
		setConta(conta);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getInstituicaoBancariaId() {
		return instituicaoBancariaId;
	}

	public void setInstituicaoBancariaId(int instituicaoBancariaId) {
		this.instituicaoBancariaId = instituicaoBancariaId;
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
