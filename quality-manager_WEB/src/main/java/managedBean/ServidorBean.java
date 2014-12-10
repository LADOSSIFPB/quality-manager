package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Servidor;

@ManagedBean
@RequestScoped
public class ServidorBean extends GenericBean implements BeanInterface {

	private Servidor servidor = new Servidor();
	private List<SelectItem> instituicoesBancarias;
	private List<SelectItem> cargos;

	private List<Servidor> servidores;

	public List<Servidor> getServidores() {
		Response response = service.consultarServidores();

		// TODO: em caso de erro, redirecionar para página de erro
		if (response.getStatus() != 200) {
			Erro qme = response.readEntity(new GenericType<Erro>() {
			});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe esse
								// campo

		}

		this.servidores = response
				.readEntity(new GenericType<ArrayList<Servidor>>() {
				});

		return servidores;
	}

	public void setservidores(List<Servidor> servidores) {
		this.servidores = servidores;
	}

	public void setInstituicoesBancarias(List<SelectItem> instituicoesBancarias) {
		this.instituicoesBancarias = instituicoesBancarias;
	}

	public List<SelectItem> getInstituicoesBancarias() {
		Response response = service.consultarInstituicoesBancarias();

		// TODO: em caso de erro, redirecionar para página de erro
		if (response.getStatus() != 200) {
			Erro qme = response.readEntity(new GenericType<Erro>() {
			});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe esse
								// campo

		}

		ArrayList<InstituicaoBancaria> alib = response
				.readEntity(new GenericType<ArrayList<InstituicaoBancaria>>() {
				});

		response.close();

		ArrayList<SelectItem> alsi = new ArrayList<SelectItem>();

		if (!alib.isEmpty()) {

			for (InstituicaoBancaria instituicaoBancaria : alib) {
				SelectItem si = new SelectItem();
				si.setValue(instituicaoBancaria.getIdInstituicaoBancaria());
				si.setLabel(instituicaoBancaria.getNomeBanco());
				alsi.add(si);
			}
		} else {
			// TODO: Melhorar esse erro
			System.err.println("Erro!");
		}

		instituicoesBancarias = alsi;
		return instituicoesBancarias;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	@Override
	public void save() {

		Response message = service.cadastrarServidor(servidor);

	}

	public String detalhesServidor(Servidor servidor) {

		this.servidor = servidor;
		return PathRedirect.exibirServidor;

	}

	public List<SelectItem> getCargos() {
		Response response = service.consultarCargos();

		// TODO: em caso de erro, redirecionar para página de erro
		if (response.getStatus() != 200) {
			Erro qme = response.readEntity(new GenericType<Erro>() {
			});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe esse
								// campo

		}

		ArrayList<CargoServidor> alc = response
				.readEntity(new GenericType<ArrayList<CargoServidor>>() {
				});

		response.close();

		ArrayList<SelectItem> alsi = new ArrayList<SelectItem>();

		if (!alc.isEmpty()) {

			for (CargoServidor cargo : alc) {
				SelectItem si = new SelectItem();
				si.setValue(cargo
						.getIdCargoServidor());
				si.setLabel(cargo.getCargoServidor());
				alsi.add(si);
			}
		} else {
			System.err.println("Erro!");
		}

		cargos = alsi;

		return cargos;
	}

	public void setCargos(List<SelectItem> cargos) {
		this.cargos = cargos;
	}

}
