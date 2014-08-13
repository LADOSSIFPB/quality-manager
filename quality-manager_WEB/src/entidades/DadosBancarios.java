package entidades;

/*
  TABLE `dados_bancarios` (
  `id_dados_bancarios` INT NOT NULL AUTO_INCREMENT,
  `nm_banco` INT NOT NULL,
  `nr_agencia` INT NOT NULL,
  `nr_operacao` INT NULL,
  `nr_conta` INT NOT NULL,
  `pessoa_id` INT NOT NULL
*/

public class DadosBancarios implements EntidadeIF {
	
	private int idDadosBancarios;
	private String nomeBanco;
	private int agencia;
	private int operacao;
	private int numeroConta;
	private int pessoaId;
	
	public DadosBancarios(String nomeBanco, int agencia, int operacao, int numeroConta, int pessoaId) {
		setNomeBanco(nomeBanco);
		setAgencia(agencia);
		setOperacao(operacao);
		setNumeroConta(numeroConta);
		setPessoaId(pessoaId);
	}

	public int getIdDadosBancarios() {
		return idDadosBancarios;
	}

	public void setIdDadosBancarios(int idDadosBancarios) {
		this.idDadosBancarios = idDadosBancarios;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getOperacao() {
		return operacao;
	}

	public void setOperacao(int operacao) {
		this.operacao = operacao;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public int getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}
	
}
