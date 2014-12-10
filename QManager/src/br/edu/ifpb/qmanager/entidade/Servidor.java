package br.edu.ifpb.qmanager.entidade;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "servidor")
public class Servidor extends MembroProjeto {

	private String titulacao;
	private String localTrabalho;
	private CargoServidor cargoServidor;

	public Servidor() {
		super();
		cargoServidor = new CargoServidor();
	}

	public Servidor(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			String senha, DadosBancarios dadosBancarios, String titulacao,
			String localTrabalho, CargoServidor cargoServidor) {

		super(nomePessoa, cpf, matricula, endereco, cep, telefone, email,
				senha, dadosBancarios);

		setTitulacao(titulacao);
		setLocalTrabalho(localTrabalho);
		setCargoServidor(cargoServidor);

	}

	@XmlElement
	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	@XmlElement
	public String getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	@XmlElement
	public CargoServidor getCargoServidor() {
		return cargoServidor;
	}

	public void setCargoServidor(CargoServidor cargoServidor) {
		this.cargoServidor = cargoServidor;
	}

}
