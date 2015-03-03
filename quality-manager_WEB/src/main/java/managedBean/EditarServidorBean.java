package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.Servidor;

@ManagedBean
@SessionScoped
public class EditarServidorBean {

	private Servidor servidor;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private int SERVIDOR_NAO_CADASTRADO = 0;

	private List<SelectItem> cargos;
	
	private List<SelectItem> instituicoesBancarias;

	public EditarServidorBean() {}

	public EditarServidorBean(Servidor servidor) {
		this.setServidor(servidor);
	}

	public void save() {

		Response response = null;

		if (getServidor().getPessoaId() == SERVIDOR_NAO_CADASTRADO) {

			response = service.cadastrarServidor(getServidor());

		} else {

			response = service.editarServidor(getServidor());
		}

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			GenericBean.setMessage("info.sucessoCadastroServidor",
					FacesMessage.SEVERITY_INFO);

		} else {

			// Http Code: 304. Não modificado.
			Erro erroResponse = response.readEntity(Erro.class);
			GenericBean.setMessage("erro.cadastroServidor",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public String createEdit(Servidor servidor) {

		Response response = service.consultarServidor(servidor.getPessoaId());

		// Código de resposta do serviço.
		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {
			// Http Code: 200. Resposta para cadastro realizado com sucesso.
			Servidor servidorResponse = response.readEntity(Servidor.class);

			// Curso encontrado.
			this.setServidor(servidorResponse);

		} else {
			// Http Code: 404. Curso inexistente.
			Erro erroResponse = response.readEntity(Erro.class);
			GenericBean.setMessage("erro.servidorInexistente",
					FacesMessage.SEVERITY_ERROR);
		}

		return PathRedirect.editarServidor;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	public List<SelectItem> getCargos() {

		if (cargos != null) {
			
			return cargos;
			
		} else {

			List<CargoServidor> cargosConsulta = service.listarCargos();

			cargos = new ArrayList<SelectItem>();

			if (!cargosConsulta.isEmpty()) {
				
				for (CargoServidor cargo : cargosConsulta) {
					
					SelectItem selectItem = new SelectItem();
					selectItem.setValue(cargo.getIdCargoServidor());
					selectItem.setLabel(cargo.getNomeCargoServidor());
					
					cargos.add(selectItem);
				}
			}

			return cargos;
		}
	}

	public void setCargos(List<SelectItem> cargos) {
		this.cargos = cargos;
	}

	public List<SelectItem> getInstituicoesBancarias() {

		if (instituicoesBancarias != null) {
			
			return instituicoesBancarias;
			
		} else {

			List<InstituicaoBancaria> instituicoesBancariasConsulta = 
					service.listarInstituicoesBancarias();

			instituicoesBancarias = new ArrayList<SelectItem>();

			if (!instituicoesBancariasConsulta.isEmpty()) {

				for (InstituicaoBancaria instituicao : 
					instituicoesBancariasConsulta) {
					
					SelectItem selectItem = new SelectItem();
					selectItem.setValue(instituicao.getIdInstituicaoBancaria());
					selectItem.setLabel(instituicao.getNomeBanco());
					
					instituicoesBancarias.add(selectItem);
				}
			}

			return instituicoesBancarias;
		}
	}

	public void setInstituicoesBancarias(List<SelectItem> instituicoesBancarias) {
		this.instituicoesBancarias = instituicoesBancarias;
	}
}
