package br.edu.ifpb.qmanager.entidade;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "discente")
public class Discente extends Pessoa {

	private Turma turma;

	@XmlElement
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	@Override
	public String toString() {
		return super.toString() + "Discente [turma=" + turma + "]";
	}

}
