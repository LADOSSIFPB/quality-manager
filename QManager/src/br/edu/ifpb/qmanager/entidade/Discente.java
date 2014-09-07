package br.edu.ifpb.qmanager.entidade;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="discente")
public class Discente extends Pessoa {

	private Turma turma;

	public Discente() {
	}

	public Discente(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			Turma turma) {

		super(nomePessoa, cpf, matricula, endereco, cep, telefone, email);

		setTurma(turma);
	}

	@XmlElement
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	@Override
	public String toString() {
		return super.toString() + "-- Discente [turma=" + turma + "]";
	}

}
