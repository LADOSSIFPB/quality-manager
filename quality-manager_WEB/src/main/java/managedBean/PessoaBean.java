package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifpb.qmanager.entidade.Pessoa;

@ManagedBean(name="pessoaBean")
@SessionScoped
public class PessoaBean extends Pessoa{
	
	public PessoaBean() {}
	
	public PessoaBean(Pessoa pessoa) {
		super.setPessoaId(pessoa.getPessoaId());
		super.setNomePessoa(pessoa.getNomePessoa());
		super.setCpf(pessoa.getCpf());
		super.setMatricula(pessoa.getMatricula());
		super.setCep(pessoa.getCep());
		super.setEndereco(pessoa.getEndereco());
		super.setTelefone(pessoa.getTelefone());
		super.setTipoPessoa(pessoa.getTipoPessoa());
	}
}
