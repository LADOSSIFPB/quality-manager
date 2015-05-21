package managedBean;

import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;

import service.ProviderServiceFactory;
import service.QManagerService;
import util.FileUtil;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.form.FileUploadForm;

@ManagedBean(name = "editarArquivoProjetoBean")
@SessionScoped
public class EditarArquivoProjetoBean {

	private Projeto projeto;
	
	// Arquivo do projeto.
	private Part arquivoProjeto;
	
	private int ARQUIVO_PROJETO_NAO_CADASTRADO = 0;
	
	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);
	
	public EditarArquivoProjetoBean() {}
	
	public EditarArquivoProjetoBean(Projeto projeto) {
		this.projeto = projeto;
	}

	public String save() {
		
		String pageRedirect = null;
		
		return pageRedirect;		
	}

	public int enviarArquivoProjeto(int idProjeto) throws IOException {
		
		int idArquivoProjeto = ARQUIVO_PROJETO_NAO_CADASTRADO;
		
		FileUploadForm fuf = new FileUploadForm();

		InputStream is = this.arquivoProjeto.getInputStream();
		byte[] bytes = IOUtils.toByteArray(is);

		String nomeArquivoProjeto = FileUtil.getFileName(this.arquivoProjeto);
		fuf.setFileName(nomeArquivoProjeto);
		fuf.setData(bytes);

		// Identificação do usuário.
		PessoaBean pessoaBean = (PessoaBean) GenericBean
				.getSessionValue("pessoaBean");
		fuf.setIdPessoa(pessoaBean.getPessoaId());

		// Código(ID) do projeto (pesquisa ou extensão) e stream do arquivo.
		Response response = service
				.uploadFile(Integer.toString(idProjeto), fuf);
		int statusCode = response.getStatus();

		if (statusCode == HttpStatus.SC_OK) {
			GenericBean.setMessage("info.sucessoUploadArquivo",
					FacesMessage.SEVERITY_INFO);
		}

		return idArquivoProjeto;
	}	
	
	public String createEdit(Projeto projeto) {

		if (projeto == null) {
			// Curso ainda não criado.
			GenericBean.resetSessionScopedBean("editarProjetoBean");
			GenericBean.sendRedirect(PathRedirect.cadastrarProjeto);
		} else {

			Response response = service
					.consultarProjeto(projeto.getIdProjeto());

			// Código de resposta do serviço.
			int statusCode = response.getStatus();

			if (statusCode == HttpStatus.SC_OK) {
				// Http Code: 200. Resposta para cadastro realizado com sucesso.
				Projeto projetoResponse = response.readEntity(Projeto.class);

				// Curso encontrado.
				this.setProjeto(projetoResponse);

			} else {
				// Http Code: 404. Curso inexistente.
				Erro erro = response.readEntity(Erro.class);

				GenericBean.setMessage("erro.projetoInexistente",
						FacesMessage.SEVERITY_ERROR);
			}
		}

		return PathRedirect.cadastrarProjeto;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Part getArquivoProjeto() {
		return arquivoProjeto;
	}

	public void setArquivoProjeto(Part arquivoProjeto) {
		this.arquivoProjeto = arquivoProjeto;
	}
}
