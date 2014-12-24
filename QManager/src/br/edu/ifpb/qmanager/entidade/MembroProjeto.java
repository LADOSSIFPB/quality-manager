package br.edu.ifpb.qmanager.entidade;

public class MembroProjeto extends Pessoa {

	public MembroProjeto() {
		super();
	}

	public MembroProjeto(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			String senha, TipoPessoa tipoPessoa, Local local,
			DadosBancarios dadosBancarios) {

		super(nomePessoa, cpf, matricula, endereco, cep, telefone, email,
				senha, tipoPessoa, local, dadosBancarios);

	}

	@Override
	public String toString() {
		return super.toString();
	}

}
