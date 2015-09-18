package messages_pt;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import managedBean.GenericBean;

@ManagedBean
@RequestScoped
public class MessageProperties {
	
	private static final String instituicaoFinanciadora = "name.instituicao_financiadora";
	private static final String nomeInstituicaoFinanciadora = "name.nome_instituicao_financiadora";
	
	private static final String sigla = "name.sigla";
	private static final String orcamento = "name.orcamento";
	private static final String cnpj = "name.cnpj";
	
	private static final String opcoes = "name.opcoes";

	private static final String detalhes = "option.detalhes";
	private static final String novo = "option.novo";
	private static final String buscar = "option.buscar";
	private static final String listarTodos = "option.listar_todos";
	
	public String getNovo() {
		return GenericBean.message(novo);
	}

	public String getBuscar() {
		return GenericBean.message(buscar);
	}

	public String getListartodos() {
		return GenericBean.message(listarTodos);
	}

	public String getInstituicaofinanciadora() {
		return GenericBean.message(instituicaoFinanciadora);
	}

	public String getNomeinstituicaofinanciadora() {
		return GenericBean.message(nomeInstituicaoFinanciadora);
	}

	public String getOrcamento() {
		return GenericBean.message(orcamento);
	}

	public String getSigla() {
		return GenericBean.message(sigla);
	}

	public String getCnpj() {
		return GenericBean.message(cnpj);
	}

	public String getOpcoes() {
		return GenericBean.message(opcoes);
	}

	public String getDetalhes() {
		return GenericBean.message(detalhes);
	}

}
