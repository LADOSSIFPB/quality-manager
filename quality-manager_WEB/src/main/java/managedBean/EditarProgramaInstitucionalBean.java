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
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.RecursoInstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.RecursoProgramaInstitucional;

@ManagedBean(name = "editarProgramaInstitucionalBean")
@SessionScoped
public class EditarProgramaInstitucionalBean {

	ProgramaInstitucional programaInstitucional;
	private RecursoProgramaInstitucional recursoProgramaInstitucional;
	
	private List<RecursoProgramaInstitucional> recursosProgramasInstitucionais;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	private int PROGRAMA_INSTITUCIONAL_NAO_CADASTRADO = 0;
	private List<SelectItem> recursosInstiticaoFinanciadora;

	private List<SelectItem> instituicoesFinanciadoras;

	public EditarProgramaInstitucionalBean() {
		this.programaInstitucional = new ProgramaInstitucional();
		this.recursoProgramaInstitucional = new RecursoProgramaInstitucional();
	}

	public EditarProgramaInstitucionalBean(
			ProgramaInstitucional programaInstitucional) {

		this.setProgramaInstitucional(programaInstitucional);
	}

	public EditarProgramaInstitucionalBean(
			RecursoProgramaInstitucional recursoProgramaInstitucional) {

		this.setRecursoProgramaInstitucional(recursoProgramaInstitucional);
	}

	public void save() {

		Response response = null;

		if (getProgramaInstitucional().getIdProgramaInstitucional() == PROGRAMA_INSTITUCIONAL_NAO_CADASTRADO) {

			PessoaBean pessoaBean = (PessoaBean) GenericBean
					.getSessionValue("pessoaBean");
			this.programaInstitucional.getGestor().setPessoaId(
					pessoaBean.getPessoaId());
			response = service
					.cadastrarProgramaInstitucional(this.programaInstitucional);
			
			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {

				GenericBean.setMessage("info.sucessoCadastroProgramaInstitucional",
						FacesMessage.SEVERITY_INFO);
				GenericBean
						.resetSessionScopedBean("editarProgramaInstitucionalBean");

			} else {

				// Http Code: 304. Não modificado.
				Erro erroResponse = response.readEntity(Erro.class);
				GenericBean.setMessage("erro.cadastroProgramaInstitucional",
						FacesMessage.SEVERITY_ERROR);
			}

		} else {

			response = service
					.editarProgramaInstitucional(getProgramaInstitucional());
			
			GenericBean.sendRedirect(PathRedirect.exibirProgramaInstitucional);
		}

		
	}

	public String createEdit(ProgramaInstitucional programaInstitucional) {

		if (programaInstitucional == null) {
			// Edital ainda não criado.
			GenericBean
					.resetSessionScopedBean("editarProgramaInstitucionalBean");
			GenericBean
					.sendRedirect(PathRedirect.cadastrarProgramaInstitucional);

		} else {

			Response response = service
					.consultarProgramaInstitucional(programaInstitucional
							.getIdProgramaInstitucional());

			// Código de resposta do serviço.
			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {
				// Http Code: 200. Resposta para cadastro realizado com sucesso.
				ProgramaInstitucional programaResponse = response
						.readEntity(ProgramaInstitucional.class);

				// Edital encontrado.
				this.programaInstitucional = programaResponse;

			} else {
				// Http Code: 404. Edital inexistente.
				Erro erroResponse = response.readEntity(Erro.class);
				GenericBean.setMessage("erro.programaInstitucionalInexistente",
						FacesMessage.SEVERITY_ERROR);
			}
		}

		return PathRedirect.cadastrarProgramaInstitucional;
	}

	public void lancarRecurso() throws SQLException {

		Response response = null;

		response = service
				.cadastrarRecursoPrograma(recursoProgramaInstitucional);

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			// Cadastro realizado com sucesso.
			GenericBean.setMessage("info.sucessoLancamentoOrcamento",
					FacesMessage.SEVERITY_INFO);
			GenericBean
					.resetSessionScopedBean("editarProgramaInstitucionalBean");

			recursoProgramaInstitucional = new RecursoProgramaInstitucional(
					recursoProgramaInstitucional.getProgramaInstitucional());
			recursosInstiticaoFinanciadora = null;
			EditarProgramaInstitucionalBean editarProgramaInstitucionalBean = new EditarProgramaInstitucionalBean(
					recursoProgramaInstitucional);
			GenericBean.setSessionValue("editarProgramaInstitucionalBean",
					editarProgramaInstitucionalBean);

		} else if (statusCode == HttpStatus.SC_NOT_ACCEPTABLE) {

			// Problema com os dados enviados. Recuperar mensagem do serviço.
			Erro erroResponse = response.readEntity(Erro.class);
			GenericBean.setMessage(erroResponse.getMensagem(),
					FacesMessage.SEVERITY_ERROR);

		} else {

			// Http Code: 304. Não modificado.
			GenericBean.setMessage("erro.lancamentoOrcamento",
					FacesMessage.SEVERITY_ERROR);
		}

	}

	public List<SelectItem> getInstituicoesFinanciadoras() {

		if (instituicoesFinanciadoras != null) {

			return instituicoesFinanciadoras;

		} else {

			List<InstituicaoFinanciadora> instituicoesFinanciadorasConsulta = service
					.listarInstituicoesFinanciadoras();

			instituicoesFinanciadoras = new ArrayList<SelectItem>();

			if (!instituicoesFinanciadorasConsulta.isEmpty()) {

				for (InstituicaoFinanciadora instituicaoFinanciadora : instituicoesFinanciadorasConsulta) {

					SelectItem selectItem = new SelectItem();
					selectItem.setValue(instituicaoFinanciadora
							.getIdInstituicaoFinanciadora());
					selectItem.setLabel(instituicaoFinanciadora.getSigla());

					instituicoesFinanciadoras.add(selectItem);
				}
			}

			return instituicoesFinanciadoras;
		}
	}

	public ProgramaInstitucional getProgramaInstitucional() {
		return programaInstitucional;
	}

	public void setProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {
		this.programaInstitucional = programaInstitucional;
	}

	public RecursoProgramaInstitucional getRecursoProgramaInstitucional() {
		return recursoProgramaInstitucional;
	}

	public void setRecursoProgramaInstitucional(
			RecursoProgramaInstitucional recursoProgramaInstitucional) {
		this.recursoProgramaInstitucional = recursoProgramaInstitucional;
	}

	public List<SelectItem> getRecursosInstiticaoFinanciadora()
			throws SQLException {
		if (recursosInstiticaoFinanciadora != null) {

			return recursosInstiticaoFinanciadora;

		} else {

			recursosInstiticaoFinanciadora = consultarRecursosInstituicao(recursoProgramaInstitucional
					.getProgramaInstitucional().getInstituicaoFinanciadora());

			return recursosInstiticaoFinanciadora;
		}
	}

	public void setRecursosInstiticaoFinanciadora(
			List<SelectItem> recursosInstiticaoFinanciadora) {
		this.recursosInstiticaoFinanciadora = recursosInstiticaoFinanciadora;
	}

	public List<SelectItem> consultarRecursosInstituicao(
			InstituicaoFinanciadora instituicao) throws SQLException {

		List<RecursoInstituicaoFinanciadora> recursosInstituicoes = service
				.consultarRecursosValidosInstituicaoFinanciadora(instituicao);

		recursosInstiticaoFinanciadora = new ArrayList<SelectItem>();

		if (!recursosInstituicoes.isEmpty()) {

			for (RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora : recursosInstituicoes) {

				SelectItem selectItem = new SelectItem();
				selectItem.setValue(recursoInstituicaoFinanciadora
						.getIdRecursoIF());
				selectItem.setLabel(String.format("%s",
						recursoInstituicaoFinanciadora.getOrcamento()));

				recursosInstiticaoFinanciadora.add(selectItem);
			}
		}

		return recursosInstiticaoFinanciadora;

	}

	public List<RecursoProgramaInstitucional> getRecursosProgramasInstitucionais() throws SQLException {
		if(recursosProgramasInstitucionais== null){
		return recursosProgramasInstitucionais =  service.listarRecursosValidosProgramaInstitucional(programaInstitucional);
		}
	
		return recursosProgramasInstitucionais;
		
	}

	public void setRecursosProgramasInstitucionais(
			List<RecursoProgramaInstitucional> recursosProgramasInstitucionais) {
		this.recursosProgramasInstitucionais = recursosProgramasInstitucionais;
	}
}
