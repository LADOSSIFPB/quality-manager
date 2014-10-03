package br.edu.ifpb.qmanager.entidade;

public class MembroProjeto extends Pessoa implements UsuarioIF {

	public MembroProjeto() {
		super();
	}

	public MembroProjeto(String nomePessoa, String cpf, String matricula,
			String endereco, String cep, String telefone, String email,
			String senha, DadosBancarios dadosBancarios) {

		super(nomePessoa, cpf, matricula, endereco, cep, telefone, email,
				senha, dadosBancarios);

	}

	@Override
	public String toString() {
		return super.toString();
	}

}
