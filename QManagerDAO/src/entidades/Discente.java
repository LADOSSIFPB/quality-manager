package entidades;

/*
 TABLE `discente`
 `pessoa_id` INT NOT NULL,
 `turma_id` INT NOT NULL
 */

public class Discente extends Pessoa implements EntidadeIF {

	private int turmaId;

	public Discente(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			int instituicaoBancariaId, String operacao, String conta,
			int turmaId) {
		super(nomePessoa, cpf, matricula, endereco, cep, telefone, email,
				instituicaoBancariaId, operacao, conta);
		setTurmaId(turmaId);
	}

	public int getTurmaId() {
		return turmaId;
	}

	public void setTurmaId(int turmaId) {
		this.turmaId = turmaId;
	}

}
