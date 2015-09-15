package managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Edital;

@ManagedBean
@ViewScoped
public class EditalBean implements Serializable {

	private static final long serialVersionUID = 1897358608599533505L;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);
	
	private String descricao;
	
	private int numero;
	
	private int ano;
	
	private List<Edital> editais;
	
	public EditalBean() {
	}

	public void consultarEditais() {

		Edital editalConsulta = new Edital();
		editalConsulta.setDescricao(this.descricao);
		editalConsulta.setNumero(this.numero);
		editalConsulta.setAno(this.ano);
		
		this.setEditais(service.consultarEditais(editalConsulta));
		
	}
	
	/**
	 * Listar todos cursos existentes.
	 * 
	 * @return
	 */
	public void listarEditais() {
		this.editais = service.listarEditais();
	}

	public void detalharEdital(Edital edital) {

		GenericBean.resetSessionScopedBean("editarEditalBean");

		EditarEditalBean editarEdital = new EditarEditalBean(edital);
		GenericBean.setSessionValue("editarEditalBean", editarEdital);

		GenericBean.sendRedirect(PathRedirect.exibirEdital);
	}

	public List<Edital> getEditais() {
		return editais;
	}

	public void setEditais(List<Edital> editais) {
		this.editais = editais;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
}
