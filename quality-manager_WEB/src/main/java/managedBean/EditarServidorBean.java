package managedBean;

import java.sql.SQLException;
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
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
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

	public EditarServidorBean() {
		// TODO Auto-generated constructor stub
	}

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

			List<CargoServidor> alcs = null;
			try {
				alcs = service.listarCargos();
			} catch (SQLException e) {
				// TODO: verificar tratamento desse erro
				e.printStackTrace();
			}

			// TODO: tratar caso a lista estiver vazia

			ArrayList<SelectItem> alsi = new ArrayList<SelectItem>();

			if (!alcs.isEmpty()) {

				for (CargoServidor cargo : alcs) {
					SelectItem si = new SelectItem();
					si.setValue(cargo.getIdCargoServidor());
					si.setLabel(cargo.getNomeCargoServidor());
					alsi.add(si);
				}
			} else {
				System.err.println("Erro!");
			}

			this.cargos = alsi;

			return this.cargos;

		}
	}

	public void setCargos(List<SelectItem> cargos) {
		this.cargos = cargos;
	}

	public List<SelectItem> getInstituicoesBancarias() {

		if (instituicoesBancarias != null) {
			return instituicoesBancarias;
		} else {

			List<InstituicaoBancaria> alib = null;
			try {
				alib = service.listarInstituicoesBancarias();
			} catch (SQLException e) {
				// TODO: verificar tratamento desse erro
				e.printStackTrace();
			}

			// TODO: tratar caso a lista estiver vazia

			ArrayList<SelectItem> alsi = new ArrayList<SelectItem>();

			if (!alib.isEmpty()) {

				for (InstituicaoBancaria instituicao : alib) {
					SelectItem si = new SelectItem();
					si.setValue(instituicao.getIdInstituicaoBancaria());
					si.setLabel(instituicao.getNomeBanco());
					alsi.add(si);
				}
			} else {
				System.err.println("Erro!");
			}

			this.instituicoesBancarias = alsi;

			return this.instituicoesBancarias;

		}

	}

	public void setInstituicoesBancarias(List<SelectItem> instituicoesBancarias) {
		this.instituicoesBancarias = instituicoesBancarias;
	}

}
