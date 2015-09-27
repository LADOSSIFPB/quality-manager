package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Area;
import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.GrandeArea;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.tipo.TipoRole;

@ManagedBean(name = "editarProjetoBean")
@SessionScoped
public class EditarProjetoBean {

	private int stepDadosProjeto = 0;

	private Projeto projeto;
	
	private List<Pessoa> pessoas;

	private List<SelectItem> editais;
	
	private List<SelectItem> grandesAreas;
	
	private List<SelectItem> areas;
	
	private List<SelectItem> campi;
	
	private boolean tenhoProtocolo = false;
	
	private boolean temCampus = false;
	
	private boolean selectGrandeArea = false;
	
	private int PROJETO_NAO_CADASTRADO = 0;
	
	private int GRANDE_AREA_NAO_SELECIONADA = 0;
	
	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);
	
	public EditarProjetoBean() {
		
		Campus campus = new Campus();		
		Edital edital = new Edital();		
		GrandeArea grandeArea = new GrandeArea();
		Area area = new Area();
		Servidor orientador = new Servidor();
		
		this.projeto = new Projeto();
		this.projeto.setCampus(campus);
		this.projeto.setEdital(edital);
		this.projeto.setGrandeArea(grandeArea);
		this.projeto.setArea(area);		
		this.projeto.setOrientador(orientador);	
		
		setCampusServidor();
	}
	
	private void setCampusServidor() {
		
		HttpServletRequest request = GenericBean.getRequest();
		boolean isServidor = request.isUserInRole(
				TipoRole.ROLE_SERVIDOR.getNome());
		
		if (isServidor) {
			
			PessoaBean pessoaBean = GenericBean.getPessoaBean();
			Servidor orientador = this.buscarServidor(pessoaBean.getPessoaId(), 
					TipoPessoa.TIPO_SERVIDOR);
			
			this.projeto.setOrientador(orientador);
			
			Campus campus = orientador.getCampus();
			this.projeto.setCampus(campus);
			
			// Inicializar lista de editais disponíveis para o Orientador
			this.getEditaisCampus();
			
			temCampus = true;
		}		
	}
	
	private Servidor buscarServidor(int pessoaId, int idTipoPessoa) {

		QManagerService serviceServidor = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		Response response = serviceServidor.consultarPessoaPorTipo(pessoaId,
				idTipoPessoa);

		Servidor servidor = response.readEntity(Servidor.class);

		return servidor;
	}

	public EditarProjetoBean(Projeto projeto) {
		this.setProjeto(projeto);
	}

	public String save() {
		
		String pageRedirect = null;

		Response response = null;

		if (this.projeto.getIdProjeto() == PROJETO_NAO_CADASTRADO) {

			PessoaBean pessoaBean = (PessoaBean) GenericBean.getPessoaBean();
			
			Servidor cadastrador = new Servidor();
			cadastrador.setPessoaId(pessoaBean.getPessoaId());
			this.projeto.setCadastrador(cadastrador);
			
			response = service.cadastrarProjeto(this.projeto);

		} else {

			response = service.editarProjeto(getProjeto());
		}

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			Projeto projetoResponse = response.readEntity(Projeto.class);
			int idProjetoResponse = projetoResponse.getIdProjeto();

			if (idProjetoResponse != PROJETO_NAO_CADASTRADO) {

				this.projeto = projetoResponse;
				
				// Remover registros anteriores da sessão.
				GenericBean.resetSessionScopedBean("editarArquivoProjetoBean");
				
				// Projeto-Arquivo na sessão. 
				EditarArquivoProjetoBean editarArquivoProjeto = 
						new EditarArquivoProjetoBean(this.projeto);				
				GenericBean.setSessionValue("editarArquivoProjetoBean", 
						editarArquivoProjeto);
				
				GenericBean.setMessage("info.sucessoCadastroProjeto",
						FacesMessage.SEVERITY_INFO);
				
				pageRedirect = PathRedirect.anexarArquivoProjeto;
			}

		} else {

			// Http Code: 304. Não modificado.
			Erro erro = response.readEntity(Erro.class);

			GenericBean.setMessage(erro.getMensagem(),
					FacesMessage.SEVERITY_ERROR);
		}
		
		return pageRedirect;
	}
	
	public void createEdit(Projeto projeto) {

		String pageRedirect = PathRedirect.cadastrarProjeto;
		
		if (projeto == null) {
			
			// Projeto ainda não criado.
			GenericBean.resetSessionScopedBean("editarProjetoBean");
			
		} else {

			Response response = service.consultarProjeto(projeto.getIdProjeto());

			// Código de resposta do serviço.
			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {
				
				// Http Code: 200. Resposta projeto encontrado com sucesso.
				Projeto projetoResponse = response.readEntity(Projeto.class);

				// Projeto encontrado.
				this.projeto = projetoResponse;

			} else {
				
				// Http Code: 404. Curso inexistente.
				Erro erro = response.readEntity(Erro.class);

				GenericBean.setMessage("erro.projetoInexistente",
						FacesMessage.SEVERITY_ERROR);
			}
		}

		GenericBean.sendRedirect(pageRedirect);
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public void getPessoaCampus(AjaxBehaviorEvent event) {	
		
		Campus campus = this.projeto.getOrientador().getCampus();
		
		if (campus != null) {
			
			// Desabilitar lista de campus.
			temCampus = true;
			
			// Projeto submetido ao campus do orientador.
			this.projeto.setCampus(campus);	
			
			// Inicializar lista de editais disponíveis para o Orientador
			this.getEditaisCampus();
			
		} else {
			
			temCampus = false;
		}		
	}

	public void getEditaisCampus() {	
		
		Campus campus = this.projeto.getOrientador().getCampus();	
		
		// Consultar edital que aceitam submissão para o campus do orientador.
		List<Edital> editaisConsulta = service.listarEditaisCampus(
				campus.getIdCampusInstitucional());
		
		if (editaisConsulta != null && !editaisConsulta.isEmpty()) {
						
			editais = GenericBean.initSelectOneItem();

			for (Edital edital : editaisConsulta) {

				SelectItem selectItem = new SelectItem();
				selectItem.setValue(edital
						.getIdEdital());
				selectItem.setLabel(edital.getNumAno() + " - " + edital.getDescricao());

				editais.add(selectItem);
			}
			
		} else {
			
			GenericBean.setMessage("Não é possível submeter para esse Edital.",
					FacesMessage.SEVERITY_ERROR);
		}	
	}
	
	public List<SelectItem> getEditais() {
		
		if (editais != null) {

			return editais;

		} else {

			List<Edital> editaisConsulta = service.listarEditais();
			
			editais = GenericBean.initSelectOneItem();

			if (!editaisConsulta.isEmpty()) {

				for (Edital edital : editaisConsulta) {

					SelectItem selectItem = new SelectItem();
					selectItem.setValue(edital
							.getIdEdital());
					selectItem.setLabel(edital.getNumAno() + " - " + edital.getDescricao());

					editais.add(selectItem);
				}
			}

			return editais;
		}
	}

	public void setEditais(List<SelectItem> editais) {
		this.editais = editais;
	}

	public List<SelectItem> getCampi() {
		
		if (campi != null) {

			return campi;

		} else {

			List<Campus> campiConsulta = service
					.listarLocais();
			
			campi = GenericBean.initSelectOneItem();
			
			if (!campiConsulta.isEmpty()) {

				for (Campus campus : campiConsulta) {

					SelectItem selectItem = new SelectItem();
					selectItem.setValue(campus.getIdCampusInstitucional());
					selectItem.setLabel(campus.getNome());

					campi.add(selectItem);
				}
			}

			return campi;
		}
	}

	public void setCampi(List<SelectItem> campus) {
		this.campi = campus;
	}

	//TODO: Chamar o serviço para recuperar participações.
	public List<Pessoa> getPessoas() {
		
		pessoas = new ArrayList<Pessoa>();
		
		if (projeto.getDiscentes() != null)
			pessoas.addAll(projeto.getDiscentes());
		if (projeto.getOrientador() != null)
			pessoas.add(projeto.getOrientador());
		if (projeto.getCoorientador() != null)
			pessoas.add(projeto.getCoorientador());
		if (projeto.getColaborador() != null)
			pessoas.add(projeto.getColaborador());
		
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public boolean isTenhoProtocolo() {
		return tenhoProtocolo;
	}

	public void setTenhoProtocolo(boolean tenhoProtocolo) {
		this.tenhoProtocolo = tenhoProtocolo;
	}

	public List<SelectItem> getGrandesAreas() {
		
		if (grandesAreas != null) {

			return grandesAreas;

		} else {

			List<GrandeArea> grandesAreasConsulta = service
					.listarGrandesAreas();

			grandesAreas = GenericBean.initSelectOneItem();

			if (!grandesAreasConsulta.isEmpty()) {

				for (GrandeArea grandeArea : grandesAreasConsulta) {

					SelectItem selectItem = new SelectItem();
					selectItem.setValue(grandeArea.getIdGrandeArea());
					selectItem.setLabel(grandeArea.getNome());

					grandesAreas.add(selectItem);
				}
			}

			return grandesAreas;
		}
	}

	public void setGrandesAreas(List<SelectItem> grandesAreas) {
		this.grandesAreas = grandesAreas;
	}

	public List<SelectItem> getAreas() {
		
		if (areas == null || areas.isEmpty()) {
			areas = GenericBean.initSelectOneItem();
		}				
		
		return areas;
	}
	

	public void setAreas(List<SelectItem> areas) {
		this.areas = areas;
	}
	
	public void mudarAreas() {
		
		GrandeArea grandeArea = projeto.getGrandeArea();

		areas = GenericBean.initSelectOneItem();

		if (grandeArea != null
				&& grandeArea.getIdGrandeArea() != GRANDE_AREA_NAO_SELECIONADA) {

			List<Area> areasConsulta = service.consultarAreasByGrandeArea(
					grandeArea.getIdGrandeArea());

			if (!areas.isEmpty()) {

				for (Area area : areasConsulta) {

					SelectItem selectItem = new SelectItem();
					selectItem.setValue(area.getIdArea());
					selectItem.setLabel(area.getNome());

					areas.add(selectItem);
				}
			}

			// Habilitar o campo.
			selectGrandeArea = true;
		}
	}
	
	/**
	 * Consultar orientador.
	 * 
	 * @param query
	 * @return
	 */
	public List<Servidor> completeOrientador(String query) {

		Servidor servidor = new Servidor();
		servidor.setNomePessoa(query);

		QManagerService serviceConsultarOrientador = ProviderServiceFactory
				.createServiceClient(QManagerService.class);
		
		List<Servidor> servidores = 
				serviceConsultarOrientador.consultarServidores(servidor);

		return servidores;
	}
	
	public boolean isSelectGrandeArea() {
		return selectGrandeArea;
	}

	public void setSelectGrandeArea(boolean selectGrandeArea) {
		this.selectGrandeArea = selectGrandeArea;
	}

	public int getStepDadosProjeto() {
		return stepDadosProjeto;
	}

	public void setStepDadosProjeto(int stepDadosProjeto) {
		this.stepDadosProjeto = stepDadosProjeto;
	}

	public boolean isTemCampus() {
		return temCampus;
	}

	public void setTemCampus(boolean temCampus) {
		this.temCampus = temCampus;
	}	
}
