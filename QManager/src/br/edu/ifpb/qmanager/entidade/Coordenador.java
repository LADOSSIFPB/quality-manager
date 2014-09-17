package br.edu.ifpb.qmanager.entidade;

public class Coordenador extends Pessoa {

	public Coordenador() {
	}

	public Coordenador(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			String senha, DadosBancarios dadosBancarios, Turma turma) {
		super(nomePessoa, cpf, matricula, endereco, cep, telefone, email,
				senha, dadosBancarios);
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
