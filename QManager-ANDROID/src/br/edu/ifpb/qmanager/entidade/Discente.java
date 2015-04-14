package br.edu.ifpb.qmanager.entidade;

public class Discente extends Pessoa {

	private Turma turma;

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
