package br.edu.ifpb.qmanager.entidade;

public class Discente extends Pessoa {

	private Turma turma;

	public Discente() {
	}

	public Discente(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			Usuario usuario, DadosBancarios dadosBancarios, Turma turma) {

		super(nomePessoa, cpf, matricula, endereco, cep, telefone, email,
				usuario, dadosBancarios);

		setTurma(turma);
	}

	@Override
	public String toString() {
		return super.toString() + "-- Discente --\n\nAno= "
				+ turma.getAno() + "\n\n--\n\n";
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
}
