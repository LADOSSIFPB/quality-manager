package managedBean;

import java.io.IOException;
import java.io.InputStream;

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

@ManagedBean(name = "editarArquivoProjetoBean")
@SessionScoped
public class EditarArquivoProjetoBean {

	private Projeto projeto;
	
	// Arquivo do projeto.	
	private UploadedFile fileUpload;
	
	private int ARQUIVO_PROJETO_NAO_CADASTRADO = 0;
	
	private int TIPO_ARQUIVO_PROJETO_INICIAL = 1;
	
	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);
	
	public EditarArquivoProjetoBean() {}
	
	public EditarArquivoProjetoBean(Projeto projeto) {
		this.projeto = projeto;
	}

	public String save() {
		
		String pageRedirect = null;

		try {
			
			Response response = enviarArquivoProjeto(projeto.getIdProjeto());
		
			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {
				
				GenericBean.setMessage("info.sucessoUploadArquivo",
						FacesMessage.SEVERITY_INFO);
				
				pageRedirect = PathRedirect.adicionarMembroProjeto;
			
			} else {
				
				// Problema no envio do arquivo.
				GenericBean.setMessage("Não foi possível enviar o arquivo para o servidor.",
						FacesMessage.SEVERITY_ERROR);
			}			
			
		} catch (IOException e) {
			
			// Problema na manipulação do arquivo.
			GenericBean.setMessage("Problema ao manipular o arquivo.",
					FacesMessage.SEVERITY_ERROR);
		}
		
		return pageRedirect;		
	}

	public void addArquivoProjeto(FileUploadEvent event) {	
		
		this.fileUpload = event.getFile();
		
	}
	
	private Response enviarArquivoProjeto(int idProjeto) throws IOException {
		
		Response response = null;
		
		FileUploadForm fuf = new FileUploadForm();

		byte[] bytes = IOUtils.toByteArray(this.fileUpload.getInputstream());

		String nomeArquivoProjeto = this.fileUpload.getFileName();
		fuf.setFileName(nomeArquivoProjeto);
		fuf.setData(bytes);
		fuf.setTipoArquivo(TIPO_ARQUIVO_PROJETO_INICIAL);

		// Identificação do usuário.
		PessoaBean pessoaBean = (PessoaBean) GenericBean
				.getSessionValue("pessoaBean");
		fuf.setIdPessoa(pessoaBean.getPessoaId());

		// Código(ID) do projeto (pesquisa ou extensão) e stream do arquivo.
		response = service.uploadArquivoProjeto(Integer.toString(idProjeto),
				fuf);

		return response;
	}
	
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	public UploadedFile getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(UploadedFile fileUpload) {
		this.fileUpload = fileUpload;
	}
}
