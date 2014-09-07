package br.edu.ifpb.qmanager.entidade;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="orientador")
public class Orientador extends Pessoa {

	private String titulacao;
	private String cargo;
	private String localTrabalho;

	public Orientador() {
	}

	public Orientador(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			String titulacao, String cargo, String localTrabalho) {
		super(nomePessoa, cpf, matricula, endereco, cep, telefone, email);
		setTitulacao(titulacao);
		setCargo(cargo);
		setLocalTrabalho(localTrabalho);
	}

	@XmlElement
	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	@XmlElement
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@XmlElement
	public String getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	@Override
	public String toString() {
		return "Orientador [titulacao=" + titulacao + ", cargo=" + cargo
				+ ", localTrabalho=" + localTrabalho + "]";
	}

}
