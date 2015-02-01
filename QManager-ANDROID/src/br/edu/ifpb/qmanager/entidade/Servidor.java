package br.edu.ifpb.qmanager.entidade;

public class Servidor extends Pessoa {

	private String titulacao;
	private CargoServidor cargoServidor;

	public Servidor() {
		super();
		cargoServidor = new CargoServidor();
	}

	public Servidor(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			String senha, TipoPessoa tipoPessoa, Local localTrabalho,
			DadosBancarios dadosBancarios, String titulacao,
			CargoServidor cargoServidor) {

		super(nomePessoa, cpf, matricula, endereco, cep, telefone, email,
				senha, tipoPessoa, localTrabalho, dadosBancarios);

		setTitulacao(titulacao);
		setCargoServidor(cargoServidor);

	}

	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	public CargoServidor getCargoServidor() {
		return cargoServidor;
	}

	public void setCargoServidor(CargoServidor cargoServidor) {
		this.cargoServidor = cargoServidor;
	}

}
