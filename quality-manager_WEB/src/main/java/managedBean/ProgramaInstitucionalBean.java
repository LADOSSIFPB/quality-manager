package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.QManagerErro;

@ManagedBean
@RequestScoped
public class ProgramaInstitucionalBean extends
		GenericBean<ProgramaInstitucional> implements beanInterface {

	// CADASTRAR
	private ProgramaInstitucional programaInstitucional = new ProgramaInstitucional();
	private List<SelectItem> instituicoesFinanciadoras;

	// CONSULTAR
	private List<ProgramaInstitucional> programasInstitucionais;

	public ProgramaInstitucional getProgramaInstitucional() {
		return programaInstitucional;
	}

	public void setProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {
		this.programaInstitucional = programaInstitucional;
	}

	@Override
	public void save() {
		String message = requestCadastrar(programaInstitucional,
				PathServices.CADASTRAR_PROGRAMA_INSTITUCIONAL);
	}

	public List<SelectItem> getInstituicoesFinanciadoras() {

		Response response = requestSelectConsultar(PathServices.CONSULTAR_INSTITUICOES_FINANCIADORAS);

		// TODO: em caso de erro, redirecionar para página de erro
		if (response.getStatus() != 200) {
			QManagerErro qme = response
					.readEntity(new GenericType<QManagerErro>() {
					});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe esse
								// campo

		}

		ArrayList<InstituicaoFinanciadora> alif = response
				.readEntity(new GenericType<ArrayList<InstituicaoFinanciadora>>() {
				});

		response.close();

		ArrayList<SelectItem> alsi = new ArrayList<SelectItem>();

		if (!alif.isEmpty()) {

			for (InstituicaoFinanciadora instituicaoFinanciadora : alif) {
				SelectItem si = new SelectItem();
				si.setValue(instituicaoFinanciadora
						.getIdInstituicaoFinanciadora());
				si.setLabel(instituicaoFinanciadora.getSigla());
				alsi.add(si);
			}
		} else {
			System.err.println("Erro!");
		}

		instituicoesFinanciadoras = alsi;

		return instituicoesFinanciadoras;

	}

	public List<ProgramaInstitucional> getProgramasInstitucionais() {
		Response response = requestGetAll(PathServices.CONSULTAR_PROGRAMAS_INSTITUCIONAIS);

		// TODO: em caso de erro, redirecionar para página de erro
		if (response.getStatus() != 200) {
			QManagerErro qme = response
					.readEntity(new GenericType<QManagerErro>() {
					});

			// utilizar essa mensagem pro cliente
			qme.getMensagem();
			qme.getCodigo(); // esse código é só pra você saber que existe esse
								// campo

		}

		this.programasInstitucionais = response
				.readEntity(new GenericType<ArrayList<ProgramaInstitucional>>() {
				});

		return programasInstitucionais;
	}

	public void setProgramasInstitucionais(
			List<ProgramaInstitucional> programasInstitucionais) {
		this.programasInstitucionais = programasInstitucionais;
	}
}
