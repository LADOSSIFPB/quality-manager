package managedBean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.form.FileUploadForm;
import br.edu.ifpb.qmanager.tipo.TipoArquivo;
import br.edu.ifpb.qmanager.tipo.TipoArquivoProjeto;

@ManagedBean(name = "editarArquivoProjetoBean")
@SessionScoped
public class EditarArquivoProjetoBean implements Serializable {
	
	private static final long serialVersionUID = 7259324634920828811L;

	private int stepDadosProjeto = 1;

	private Projeto projeto;
	
	// Arquivo identificado do projeto.	
	private UploadedFile arquivoProjetoIdentificado;
	
	// Arquivo não identificado do projeto.	
	private UploadedFile arquivoProjetoNaoIdentificado;
	
	// Arquivo da Aprovação da Comissão de Ética.	
	private UploadedFile arquivoComissaoEtica;
	
	public EditarArquivoProjetoBean() {}
	
	public EditarArquivoProjetoBean(Projeto projeto) {
		this.projeto = projeto;
	}

	public String save() {
		
		String pageRedirect = null;

		try {
		
			int statusCodeProjetoIdentificado = enviarArquivoProjetoIndentificado();
			
			int statusCodeProjetoNaoIdentificado = enviarArquivoProjetoNaoIndentificado();

			if (statusCodeProjetoIdentificado == HttpStatus.SC_OK 
					&& statusCodeProjetoNaoIdentificado == HttpStatus.SC_OK) {
				
				// Remover registros anteriores da sessão.
				GenericBean.resetSessionScopedBean("editarParticipacaoBean");
				
				EditarParticipacaoBean editarParticipacaoBean = 
						new EditarParticipacaoBean(projeto);
				GenericBean.setSessionValue("editarParticipacaoBean", 
						editarParticipacaoBean);
				
				GenericBean.setMessage("info.sucessoUploadArquivo",
						FacesMessage.SEVERITY_INFO);
				
				pageRedirect = PathRedirect.adicionarMembroProjeto;
			
			} else if (statusCodeProjetoIdentificado 
					== HttpStatus.SC_NOT_MODIFIED) {
				
				// Problema no envio do arquivo.
				GenericBean.setMessage("erro.envioArquivoProjetoIdentificado",
						FacesMessage.SEVERITY_ERROR);
				
			} else if (statusCodeProjetoNaoIdentificado 
					== HttpStatus.SC_NOT_MODIFIED) {
				
				// Problema no envio do arquivo.
				GenericBean.setMessage("erro.envioArquivoProjetoNaoIdentificado",
						FacesMessage.SEVERITY_ERROR);
			}			
			
		} catch (IOException e) {
			
			// Problema na manipulação do arquivo.
			GenericBean.setMessage( "erro.manipulacaoArquivo",
					FacesMessage.SEVERITY_ERROR);
		}
		
		return pageRedirect;		
	}
	
	public int enviarArquivoProjetoIndentificado() throws IOException {

		int statusCode = HttpStatus.SC_NOT_MODIFIED;

		Response response = enviarArquivoProjeto(projeto.getIdProjeto(), 
				arquivoProjetoIdentificado, 
				TipoArquivoProjeto.ARQUIVO_PROJETO_IDENTIFICADO);

		statusCode = response.getStatus();

		return statusCode;
	}
	
	public int enviarArquivoProjetoNaoIndentificado() throws IOException {

		int statusCode = HttpStatus.SC_NOT_MODIFIED;

		Response response = enviarArquivoProjeto(projeto.getIdProjeto(), 
				arquivoProjetoNaoIdentificado, 
				TipoArquivoProjeto.ARQUIVO_PROJETO_NAO_IDENTIFICADO);

		statusCode = response.getStatus();

		return statusCode;
	}
	
	private Response enviarArquivoProjeto(int idProjeto, UploadedFile file, 
			TipoArquivoProjeto tipoArquivoProjeto) throws IOException {
		
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

		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);
		
		// Código(ID) do projeto (pesquisa ou extensão) e stream do arquivo.
		response = service.uploadArquivoProjeto(Integer.toString(idProjeto), 
				tipoArquivoProjeto,
				fuf);

		return response;
	}
	
	public void addArquivoProjeto(FileUploadEvent event) {	
		
		this.arquivoProjetoIdentificado = event.getFile();		
	}
	
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	public UploadedFile getArquivoProjetoIdentificado() {
		return arquivoProjetoIdentificado;
	}

	public void setArquivoProjetoIdentificado(
			UploadedFile arquivoProjetoIdentificado) {
		this.arquivoProjetoIdentificado = arquivoProjetoIdentificado;
	}

	public UploadedFile getArquivoProjetoNaoIdentificado() {
		return arquivoProjetoNaoIdentificado;
	}

	public void setArquivoProjetoNaoIdentificado(
			UploadedFile arquivoProjetoNaoIdentificado) {
		this.arquivoProjetoNaoIdentificado = arquivoProjetoNaoIdentificado;
	}
	
	public int getStepDadosProjeto() {
		return stepDadosProjeto;
	}

	public void setStepDadosProjeto(int stepDadosProjeto) {
		this.stepDadosProjeto = stepDadosProjeto;
	}

	public UploadedFile getArquivoComissaoEtica() {
		return arquivoComissaoEtica;
	}

	public void setArquivoComissaoEtica(UploadedFile arquivoComissaoEtica) {
		this.arquivoComissaoEtica = arquivoComissaoEtica;
	}
}
