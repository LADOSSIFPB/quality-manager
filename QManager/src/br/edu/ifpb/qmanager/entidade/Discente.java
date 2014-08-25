package br.edu.ifpb.qmanager.entidade;

/*
 TABLE `discente`
 `pessoa_id` INT NOT NULL,
 `turma_id` INT NOT NULL
 */
public class Discente extends Pessoa {

	private Turma turma;

	public Discente() {
	}

	public Discente(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			Usuario usuario, ContaBancaria contaBancaria, Turma turma) {

		super(nomePessoa, cpf, matricula, endereco, cep, telefone, email,
				usuario, contaBancaria);

		setTurma(turma);
	}

	@Override
	public String toString() {
		return super.toString() + "-- Discente --\n\nTurma Identificador= "
				+ turma + "\n\n--\n\n";
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
}
