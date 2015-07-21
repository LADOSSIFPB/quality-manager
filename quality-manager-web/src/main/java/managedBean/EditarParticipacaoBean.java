package managedBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.primefaces.model.UploadedFile;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.TipoParticipacao;
import br.edu.ifpb.qmanager.form.FileUploadForm;
import br.edu.ifpb.qmanager.tipo.TipoArquivo;
import br.edu.ifpb.qmanager.tipo.TipoArquivoParticipacao;
import br.edu.ifpb.qmanager.tipo.TipoArquivoProjeto;

@ManagedBean(name = "editarParticipacaoBean")
@SessionScoped
public class EditarParticipacaoBean implements EditarBeanInterface {

	private int stepDadosProjeto = 2;
	
	private Participacao participacao;
	
	private List<Participacao> participacoes;
	
	private List<SelectItem> tiposParticipacoes;
	
	private Projeto projeto;
	
	// Arquivo não identificado do projeto.	
	private UploadedFile arquivoTermoVoluntario;
	
	// Arquivo não identificado do projeto.	
	private UploadedFile arquivoVinculoEmpregaticio;
	
	// Arquivo não identificado do projeto.	
	private UploadedFile arquivoPlanoIndividualTrabalho;
	
	// Controle da visibilidade dos paineis dos arquivos de Bolsista e Voluntário.
	private boolean panelIsVisibleBolsista = false;
	
	private boolean panelIsVisibleVoluntario = false;
	
	private int PARTICIPACAO_NAO_CADASTRADA = 0;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);
	
	public EditarParticipacaoBean(){
		this.participacao = new Participacao();
	}
	
	public EditarParticipacaoBean(Projeto projeto) {
		this.participacao = new Participacao();
		this.projeto = projeto;
	}

	@Override
	public void save() {
		
		Response response;

		try {
			if (participacao != null 
					&& participacao.getIdParticipacao() == PARTICIPACAO_NAO_CADASTRADA) {
	
				// Participação no Projeto.
				this.participacao.setProjeto(projeto);
				
				response = service.cadastrarParticipacao(participacao);
	
				int statusCode = response.getStatus();
	
				if (statusCode == HttpStatus.SC_OK) {								
					
					Participacao participacaoResponse = response.readEntity(Participacao.class);
					int idParticipacao = participacaoResponse.getIdParticipacao();
					
					
					int statusCodePlanoIndividualTrabalho =	
							enviarArquivoPlanoIndividualTrabalho(idParticipacao);
					
					int statusCodeBolsista;
					
					// Envio do arquivo do Vínculo Empregatício ou Termo de adesão do Voluntário. 
					if (participacao.isBolsista()) {
						
						statusCodeBolsista = enviarArquivoVinculoEmpregaticio(
								idParticipacao);
					} else {
						statusCodeBolsista = enviarArquivoTermoVoluntario(
								idParticipacao);
					}
					
					// Remover registros anteriores da sessão.
					GenericBean.resetSessionScopedBean("editarParticipacaoBean");
					
					EditarParticipacaoBean editarParticipacaoBean = 
							new EditarParticipacaoBean(projeto);				
					// Reinicializar a participação.
					GenericBean.setSessionValue("editarParticipacaoBean", 
							editarParticipacaoBean);	
					
					GenericBean.setMessage("info.sucessoCadastroMembroProjeto",
							FacesMessage.SEVERITY_INFO);
	
				} else {
	
					// Http Code: 406. Não aceitável.
					Erro erroResponse = response.readEntity(Erro.class);
					GenericBean.setMessage("erro.cadastroMembroProjeto",
							FacesMessage.SEVERITY_ERROR);
				}
	
			} else {
	
				// Atualização da Participação.			
				GenericBean.sendRedirect(PathRedirect.index);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void saveFinish() {
		
		Response response = service.cadastrarParticipacao(participacao);

		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {

			GenericBean.resetSessionScopedBean("editarParticipacaoBean");
			GenericBean.sendRedirect(PathRedirect.projeto);

		} else {

			// Http Code: 304. Não modificado.
			Erro erroResponse = response.readEntity(Erro.class);
			GenericBean.setMessage("erro.cadastroMembroProjeto",
					FacesMessage.SEVERITY_ERROR);
		}		
	}
	
	public void finish(){
		GenericBean.resetSessionScopedBean("participacaoBean");
		GenericBean.sendRedirect(PathRedirect.projeto);
	}
	
	public int enviarArquivoPlanoIndividualTrabalho(int idParticipacao) throws IOException {

		int statusCode = HttpStatus.SC_NOT_MODIFIED;

		Response response = enviarArquivoParticipacao(idParticipacao, 
				arquivoPlanoIndividualTrabalho, 
				TipoArquivoParticipacao.ARQUIVO_PARTICIPACAO_PLANO_INDIVIDUAL_TRABALHO);

		statusCode = response.getStatus();

		return statusCode;
	}
	
	public int enviarArquivoVinculoEmpregaticio(int idParticipacao) throws IOException {

		int statusCode = HttpStatus.SC_NOT_MODIFIED;

		Response response = enviarArquivoParticipacao(idParticipacao, 
				arquivoVinculoEmpregaticio, 
				TipoArquivoParticipacao.ARQUIVO_PARTICIPACAO_VINCULO_EMPREGATICIO);

		statusCode = response.getStatus();

		return statusCode;
	}
	
	public int enviarArquivoTermoVoluntario(int idParticipacao) throws IOException {

		int statusCode = HttpStatus.SC_NOT_MODIFIED;

		Response response = enviarArquivoParticipacao(idParticipacao, 
				arquivoTermoVoluntario, 
				TipoArquivoParticipacao.ARQUIVO_PARTICIPACAO_TERMO_VOLUNTARIO);

		statusCode = response.getStatus();

		return statusCode;
	}
	
	private Response enviarArquivoParticipacao(int idParticipacao, UploadedFile file, 
			TipoArquivoParticipacao tipoArquivoParticipacao) throws IOException {
		
		Response response = null;		

		// Conversão para array de bytes.
		byte[] bytes = IOUtils.toByteArray(file.getInputstream());

		// Nome real do arquivo
		String nomeArquivoProjeto = file.getFileName();
		
		// Identificação do usuário.
		PessoaBean pessoaBean = (PessoaBean) GenericBean
				.getSessionValue("pessoaBean");
				
		// Multi-part form
		FileUploadForm fuf = new FileUploadForm();
		fuf.setFileName(nomeArquivoProjeto);
		fuf.setData(bytes);
		fuf.setTipoArquivo(TipoArquivo.ARQUIVO_PROJETO);		
		fuf.setIdPessoa(pessoaBean.getPessoaId());

		QManagerService serviceFile = ProviderServiceFactory
				.createServiceClient(QManagerService.class);
		
		// Código(ID) do projeto (pesquisa ou extensão) e stream do arquivo.
		response = serviceFile.uploadArquivoParticipacao(Integer.toString(idParticipacao), 
				tipoArquivoParticipacao,
				fuf);

		return response;
	}
	
	
	public void changePanelState(AjaxBehaviorEvent ajaxBehaviorEvent){
		
		if (this.participacao.isBolsista()) {
			
			// Bolsista
			this.panelIsVisibleBolsista = true;
			this.panelIsVisibleVoluntario = false;
			
		} else {

			// Voluntário
			this.panelIsVisibleVoluntario = true;
			this.panelIsVisibleBolsista = false;			
		}	    
	}

	public String adicionarMembroProjeto(Projeto projeto) {

		this.participacao.setProjeto(projeto);
		return PathRedirect.adicionarMembroProjeto;
	}

	public List<Pessoa> completeMembroProjeto(String query) {

		Pessoa pessoa = new Pessoa();
		pessoa.setNomePessoa(query);

		List<Pessoa> membros = service.consultarPessoas(pessoa);

		return membros;
	}

	public Participacao getParticipacao() {
		return participacao;
	}

	public void setParticipacao(Participacao participacao) {
		this.participacao = participacao;
	}

	public List<SelectItem> getTiposParticipacoes() {

		if (this.tiposParticipacoes == null) {
			List<TipoParticipacao> tiposParticipacao = service
					.listarTiposParticipacao();

			this.tiposParticipacoes = new ArrayList<SelectItem>();

			for (TipoParticipacao tipoParticipacao : tiposParticipacao) {
				SelectItem selectItem = new SelectItem();
				selectItem.setValue(tipoParticipacao.getIdTipoParticipacao());
				selectItem.setLabel(tipoParticipacao.getNomeTipoParticipacao());

				this.tiposParticipacoes.add(selectItem);
			}

			return this.tiposParticipacoes;
		}

		return this.tiposParticipacoes;
	}

	public void setTiposParticipacoes(List<SelectItem> tiposParticipacoes) {
		this.tiposParticipacoes = tiposParticipacoes;
	}

	public int getStepDadosProjeto() {
		return stepDadosProjeto;
	}

	public void setStepDadosProjeto(int stepDadosProjeto) {
		this.stepDadosProjeto = stepDadosProjeto;
	}

	public List<Participacao> getParticipacoes() {
		return participacoes;
	}

	public void setParticipacoes(List<Participacao> participacoes) {
		this.participacoes = participacoes;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public UploadedFile getArquivoTermoVoluntario() {
		return arquivoTermoVoluntario;
	}

	public void setArquivoTermoVoluntario(UploadedFile arquivoTermoVoluntario) {
		this.arquivoTermoVoluntario = arquivoTermoVoluntario;
	}

	public UploadedFile getArquivoVinculoEmpregaticio() {
		return arquivoVinculoEmpregaticio;
	}

	public void setArquivoVinculoEmpregaticio(UploadedFile arquivoVinculoEmpregaticio) {
		this.arquivoVinculoEmpregaticio = arquivoVinculoEmpregaticio;
	}

	public UploadedFile getArquivoPlanoIndividualTrabalho() {
		return arquivoPlanoIndividualTrabalho;
	}

	public void setArquivoPlanoIndividualTrabalho(
			UploadedFile arquivoPlanoIndividualTrabalho) {
		this.arquivoPlanoIndividualTrabalho = arquivoPlanoIndividualTrabalho;
	}

	public boolean isPanelIsVisibleBolsista() {
		return panelIsVisibleBolsista;
	}

	public void setPanelIsVisibleBolsista(boolean panelIsVisibleBolsista) {
		this.panelIsVisibleBolsista = panelIsVisibleBolsista;
	}

	public boolean isPanelIsVisibleVoluntario() {
		return panelIsVisibleVoluntario;
	}

	public void setPanelIsVisibleVoluntario(boolean panelIsVisibleVoluntario) {
		this.panelIsVisibleVoluntario = panelIsVisibleVoluntario;
	}
}
