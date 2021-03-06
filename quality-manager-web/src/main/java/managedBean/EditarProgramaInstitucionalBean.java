package managedBean;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoProgramaInstitucional;

@ManagedBean(name = "editarProgramaInstitucionalBean")
@SessionScoped
public class EditarProgramaInstitucionalBean implements EditarBeanInterface {
	
	public static final String thisNomeSession = "editarProgramaInstitucionalBean";

	private ProgramaInstitucional programaInstitucional;

	private RecursoProgramaInstitucional recursoProgramaInstitucional;

	private List<RecursoProgramaInstitucional> recursosProgramasInstitucionais;

	private Map<Integer, RecursoInstituicaoFinanciadora> mapRecursosInstFinanciadora;

	private Date dataMinimaRecurso;

	private Date dataMaximaRecurso;

	private int idRecurso;

	private int PROGRAMA_INSTITUCIONAL_NAO_CADASTRADO = 0;
	private List<SelectItem> recursosInstiticaoFinanciadora;

	private List<SelectItem> instituicoesFinanciadoras;
	private List<SelectItem> tiposProgramasInstitucionais;

	public EditarProgramaInstitucionalBean() {

		this.programaInstitucional = new ProgramaInstitucional();

		InstituicaoFinanciadora instituicaoFinanciadora = new InstituicaoFinanciadora();
		this.programaInstitucional
				.setInstituicaoFinanciadora(instituicaoFinanciadora);

		TipoProgramaInstitucional tipoProgramaInstitucional = new TipoProgramaInstitucional();
		this.programaInstitucional
				.setTipoProgramaInstitucional(tipoProgramaInstitucional);

		this.recursoProgramaInstitucional = new RecursoProgramaInstitucional();

		this.setMapRecursosInstFinanciadora(new HashMap<Integer, RecursoInstituicaoFinanciadora>());
	}

	public EditarProgramaInstitucionalBean(
			ProgramaInstitucional programaInstitucional) {

		this.setProgramaInstitucional(programaInstitucional);
	}

	public EditarProgramaInstitucionalBean(
			RecursoProgramaInstitucional recursoProgramaInstitucional) {

		this.setRecursoProgramaInstitucional(recursoProgramaInstitucional);
	}

	@Override
	public void save() {
		
		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		Response response = null;

		if (getProgramaInstitucional().getIdProgramaInstitucional() == PROGRAMA_INSTITUCIONAL_NAO_CADASTRADO) {

			PessoaBean pessoaBean = (PessoaBean) GenericBean
					.getSessionValue("pessoaBean");

			// Responsável pelo cadastro.
			Servidor cadastrador = new Servidor();
			cadastrador.setPessoaId(pessoaBean.getPessoaId());
			this.programaInstitucional.setCadastrador(cadastrador);

			response = service
					.cadastrarProgramaInstitucional(this.programaInstitucional);

			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {

				GenericBean.setMessage(
						"info.sucessoCadastroProgramaInstitucional",
						FacesMessage.SEVERITY_INFO);
				GenericBean
						.resetSessionScopedBean("editarProgramaInstitucionalBean");

			} else if (statusCode == HttpStatus.SC_NOT_ACCEPTABLE) {

				// Problema com os dados enviados. Recuperar mensagem do
				// serviço.
				Erro erroResponse = response.readEntity(Erro.class);
				GenericBean.setMessage(erroResponse.getMensagem(),
						FacesMessage.SEVERITY_ERROR);

			} else {

				// Http Code: 304. Não modificado.
				GenericBean.setMessage("erro.cadastroInstituicaoFinanciadora",
						FacesMessage.SEVERITY_ERROR);
			}

		} else {

			response = service
					.editarProgramaInstitucional(getProgramaInstitucional());

			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {

				GenericBean
						.sendRedirect(PathRedirect.exibirProgramaInstitucional);

			} else if (statusCode == HttpStatus.SC_NOT_ACCEPTABLE) {

				// Problema com os dados enviados. Recuperar mensagem do
				// serviço.
				Erro erroResponse = response.readEntity(Erro.class);
				GenericBean.setMessage(erroResponse.getMensagem(),
						FacesMessage.SEVERITY_ERROR);

			} else {

				// Http Code: 304. Não modificado.
				GenericBean.setMessage("erro.cadastroInstituicaoFinanciadora",
						FacesMessage.SEVERITY_ERROR);
			}
		}
	}

	public void configurarDatas() {

		int idRecurso = recursoProgramaInstitucional
				.getRecursoInstituicaoFinanciadora().getIdRecursoIF();

		RecursoInstituicaoFinanciadora recurso = this.mapRecursosInstFinanciadora
				.get(idRecurso);

		if (recurso != null) {
			this.dataMinimaRecurso = recurso.getValidadeInicial();
			this.dataMaximaRecurso = recurso.getValidadeFinal();
		}

	}

	@Override
	public String remove() {
		// TODO Auto-generated method stub
		return null;
	}

	public void voltar() {
		GenericBean.sendRedirect(PathRedirect.programaInstitucional);
	}

	public void createEdit(ProgramaInstitucional programaInstitucional) {
		
		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		if (programaInstitucional == null) {

			// Edital ainda não criado.
			EditarProgramaInstitucionalBean editarProgramaInstitucionalBean = new EditarProgramaInstitucionalBean();
			resetSession(editarProgramaInstitucionalBean);

		} else {

			Response response = service
					.consultarProgramaInstitucional(programaInstitucional
							.getIdProgramaInstitucional());

			// Código de resposta do serviço.
			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {

				// Http Code: 200. Programa Institucional recuperado com
				// sucesso.
				ProgramaInstitucional programaResponse = response
						.readEntity(ProgramaInstitucional.class);

				// Edital encontrado.
				this.programaInstitucional = programaResponse;

			} else {

				// Http Code: 404. Programa Institucional inexistente.
				Erro erroResponse = response.readEntity(Erro.class);
				GenericBean.setMessage("erro.programaInstitucionalInexistente",
						FacesMessage.SEVERITY_ERROR);
			}
		}

		GenericBean.sendRedirect(PathRedirect.cadastrarProgramaInstitucional);
	}

	public void resetSession(
			EditarProgramaInstitucionalBean editarProgramaInstitucionalBean) {

		GenericBean.resetSessionScopedBean("editarProgramaInstitucionalBean");

		GenericBean.setSessionValue("editarProgramaInstitucionalBean",
				editarProgramaInstitucionalBean);
	}

	public void lancarRecurso(ProgramaInstitucional programaInstitucional) {

		RecursoProgramaInstitucional recursoProgramaInstitucional = new RecursoProgramaInstitucional();
		recursoProgramaInstitucional
				.setProgramaInstitucional(programaInstitucional);

		this.recursoProgramaInstitucional = recursoProgramaInstitucional;

		GenericBean
				.sendRedirect(PathRedirect.lancarRecursoProgramaInstitucional);
	}

	public void lancarRecurso() throws SQLException {
		
		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		Response response = null;

		PessoaBean pessoaBean = (PessoaBean) GenericBean
				.getSessionValue("pessoaBean");
		int idPessoa = pessoaBean.getPessoaId();

		this.recursoProgramaInstitucional.getCadastrador()
				.setPessoaId(idPessoa);

		response = service
				.cadastrarRecursoPrograma(recursoProgramaInstitucional);

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {
			
			limparRecurso();
			
			// Cadastro realizado com sucesso.
			GenericBean.setMessage("info.sucessoLancamentoOrcamento",
					FacesMessage.SEVERITY_INFO);

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
	
	public void limparRecurso(){
		
		Servidor cadadastrador = recursoProgramaInstitucional
				.getCadastrador();
		
		RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora = recursoProgramaInstitucional
				.getRecursoInstituicaoFinanciadora();
		
		this.recursoProgramaInstitucional = new RecursoProgramaInstitucional();
		
		this.recursoProgramaInstitucional.setCadastrador(cadadastrador);
		this.recursoProgramaInstitucional.setProgramaInstitucional(programaInstitucional);
		this.recursosInstiticaoFinanciadora = null;
		this.dataMaximaRecurso = null;
		this.dataMinimaRecurso = null;
		
	}

	public List<SelectItem> getInstituicoesFinanciadoras() {
		
		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		if (instituicoesFinanciadoras != null) {

			return instituicoesFinanciadoras;

		} else {

			List<InstituicaoFinanciadora> instituicoesFinanciadorasConsulta = service
					.listarInstituicoesFinanciadoras();

			instituicoesFinanciadoras = GenericBean.initSelectOneItem();

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
		
		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		List<RecursoInstituicaoFinanciadora> recursosInstituicoes = service
				.consultarRecursosValidosInstituicaoFinanciadora(instituicao);

		this.setMapRecursosInstFinanciadora(new HashMap<Integer, RecursoInstituicaoFinanciadora>());

		recursosInstiticaoFinanciadora = GenericBean.initSelectOneItem();

		if (!recursosInstituicoes.isEmpty()) {

			int chaveRecursoIF;

			for (RecursoInstituicaoFinanciadora recursoInstituicaoFinanciadora : recursosInstituicoes) {

				chaveRecursoIF = recursoInstituicaoFinanciadora
						.getIdRecursoIF();

				SelectItem selectItem = new SelectItem();
				selectItem.setValue(chaveRecursoIF);
				selectItem.setLabel(GenericBean
						.formatMonetaryNumber(recursoInstituicaoFinanciadora
								.getOrcamento()));

				recursosInstiticaoFinanciadora.add(selectItem);
				getMapRecursosInstFinanciadora().put(chaveRecursoIF,
						recursoInstituicaoFinanciadora);
			}
		}

		return recursosInstiticaoFinanciadora;

	}

	public List<RecursoProgramaInstitucional> getRecursosProgramasInstitucionais()
			throws SQLException {
		
		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		if (recursosProgramasInstitucionais == null) {
			return recursosProgramasInstitucionais = service
					.listarRecursosValidosProgramaInstitucional(programaInstitucional);
		}

		return recursosProgramasInstitucionais;
	}

	public void setRecursosProgramasInstitucionais(
			List<RecursoProgramaInstitucional> recursosProgramasInstitucionais) {
		this.recursosProgramasInstitucionais = recursosProgramasInstitucionais;
	}

	public List<SelectItem> getTiposProgramasInstitucionais()
			throws SQLException {
		
		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);
		
		if (tiposProgramasInstitucionais != null) {

			return tiposProgramasInstitucionais;

		} else {

			List<TipoProgramaInstitucional> tiposProgramasConsulta = service
					.listarTipoProgramaInstitucional();

			tiposProgramasInstitucionais = GenericBean.initSelectOneItem();

			if (!tiposProgramasConsulta.isEmpty()) {

				for (TipoProgramaInstitucional tipoProgramaInstitucional : tiposProgramasConsulta) {

					SelectItem selectItem = new SelectItem();
					selectItem.setValue(tipoProgramaInstitucional
							.getIdTipoProgramaInstitucional());
					selectItem.setLabel(tipoProgramaInstitucional
							.getNomeTipoProgramaInstitucional());

					tiposProgramasInstitucionais.add(selectItem);
				}
			}

			return tiposProgramasInstitucionais;
		}
	}

	public void setTiposProgramasInstitucionais(
			List<SelectItem> tiposProgramasInstitucionais) {
		this.tiposProgramasInstitucionais = tiposProgramasInstitucionais;
	}

	public Map<Integer, RecursoInstituicaoFinanciadora> getMapRecursosInstFinanciadora() {
		return mapRecursosInstFinanciadora;
	}

	public void setMapRecursosInstFinanciadora(
			Map<Integer, RecursoInstituicaoFinanciadora> mapRecursosInstFinanciadora) {
		this.mapRecursosInstFinanciadora = mapRecursosInstFinanciadora;
	}

	public Date getDataMinimaRecurso() {
		return dataMinimaRecurso;
	}

	public void setDataMinimaRecurso(Date dataMinimaRecurso) {
		this.dataMinimaRecurso = dataMinimaRecurso;
	}

	public Date getDataMaximaRecurso() {
		return dataMaximaRecurso;
	}

	public void setDataMaximaRecurso(Date dataMaximaRecurso) {
		this.dataMaximaRecurso = dataMaximaRecurso;
	}

	public int getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(int idRecurso) {
		this.idRecurso = idRecurso;
	}
}
