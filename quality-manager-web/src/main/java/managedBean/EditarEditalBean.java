package managedBean;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.menu.MenuModel;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.form.FileUploadForm;
import br.edu.ifpb.qmanager.tipo.TipoArquivo;
import br.edu.ifpb.qmanager.tipo.TipoArquivoEdital;

@ManagedBean(name = "editarEditalBean")
@SessionScoped
public class EditarEditalBean implements EditarBeanInterface{

	private Edital edital;
	
	private List<SelectItem> programasInstitucionais;

	private boolean tenhoNumeroAnoEdital;
	
	// Arquivo do Edital.	
	private UploadedFile arquivoEdital;

	private MenuModel menuModel;

	private int EDITAL_NAO_CADASTRADO = 0;
	
	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	public EditarEditalBean() {

		setEditalAno();
		gerarEditalNumero();
	}

	public EditarEditalBean(MenuModel menuModel) {

		this.menuModel = menuModel;
		setEditalAno();
		gerarEditalNumero();
	}
	
	private void setEditalAno() {
		
		Calendar agora = Calendar.getInstance();
		int ano = agora.get(Calendar.YEAR);

		this.edital = new Edital();
		this.edital.setAno(ano);
	}

	public EditarEditalBean(Edital edital) {
		this.setEdital(edital);
		this.menuModel = BreadCrumb.detalhesEdital(true);
	}

	public void save() {

		Response response;

		try {
			
			if (getEdital().getIdEdital() == EDITAL_NAO_CADASTRADO) {

				PessoaBean pessoaBean = GenericBean.getPessoaBean();
				
				Servidor gestor = new Servidor();
				gestor.setPessoaId(pessoaBean.getPessoaId());
				this.edital.setGestor(gestor);
				
				response = service.cadastrarEdital(this.edital);

				int statusCodeEdital = response.getStatus();

				if (statusCodeEdital == HttpStatus.SC_OK) {

					Edital editalResponse = response.readEntity(Edital.class);

					int idEdital = editalResponse.getIdEdital();
					this.edital.setIdEdital(idEdital);

					int statusCodeArquivoEdital = enviarArquivoEdital(idEdital);

					if (statusCodeArquivoEdital == HttpStatus.SC_OK) {
						
						GenericBean.setMessage("info.sucessoCadastroEdital",
								FacesMessage.SEVERITY_INFO);
						GenericBean.resetSessionScopedBean("editarEditalBean");
					
					} else {
						
						// Problema no envio do arquivo.
						GenericBean.setMessage("erro.envioArquivoEdital",
								FacesMessage.SEVERITY_ERROR);
					}					

				} else {

					// Http Code: 304. Não modificado.
					Erro erroResponse = response.readEntity(Erro.class);
					GenericBean.setMessage(erroResponse.getMensagem(),
							FacesMessage.SEVERITY_ERROR);
				}

			} else {

				response = service.editarEdital(this.edital);
				GenericBean.sendRedirect(PathRedirect.exibirEdital);
			}
			
		} catch (IOException e) {

			// Problema na manipulação do arquivo.
			GenericBean.setMessage("erro.manipulacaoArquivo",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public void createEdit(Edital edital) {

		if (edital == null) {

			// Edital ainda não foi criado.
			GenericBean.resetSessionScopedBean("editarEditalBean");
			
			MenuModel menuModel = BreadCrumb.cadastrarEdital(true);
			
			EditarEditalBean editarEditalBean = new EditarEditalBean(menuModel);
			GenericBean.setSessionValue("editarEditalBean", editarEditalBean);
			
		} else {

			Response response = service.consultarEdital(edital.getIdEdital());
			this.edital = response.readEntity(Edital.class);
			this.menuModel = BreadCrumb.editarEdital(true);
		}

		GenericBean.sendRedirect(PathRedirect.cadastrarEdital);
	}

	public void gerarEditalNumero() {

		int ano = this.edital.getAno();

		if (ano != 0 && this.edital.getNumero() == 0) {

			int numero = this.service.consultarProximoNumeroEdital(ano);
			this.edital.setNumero(numero);

		} else {
			GenericBean.setMessage(
					"É necessário informar o Ano de criação do Edital",
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public List<SelectItem> getProgramasInstitucionais() {

		if (programasInstitucionais != null) {

			return programasInstitucionais;

		} else {

			List<ProgramaInstitucional> programasInstitucionaisConsulta = service
					.listarProgramasInstitucionais();

			programasInstitucionais = GenericBean.initSelectOneItem();

			if (!programasInstitucionaisConsulta.isEmpty()) {

				for (ProgramaInstitucional programaInstitucional : programasInstitucionaisConsulta) {

					SelectItem selectItem = new SelectItem();
					selectItem.setValue(programaInstitucional
							.getIdProgramaInstitucional());
					selectItem.setLabel(programaInstitucional.getSigla());

					programasInstitucionais.add(selectItem);
				}
			}

			return programasInstitucionais;
		}
	}
	
	public int enviarArquivoEdital(int idEdital) throws IOException {

		int statusCode = HttpStatus.SC_NOT_MODIFIED;

		Response response = enviarArquivoEdital(idEdital, 
				this.arquivoEdital, 
				TipoArquivoEdital.ARQUIVO_EDITAL_INICIAL);

		statusCode = response.getStatus();

		return statusCode;
	}
	
	private Response enviarArquivoEdital(int idEdital, UploadedFile file, 
			TipoArquivoEdital tipoArquivoEdital) throws IOException {
		
		Response response = null;		

		// Conversão para array de bytes.
		byte[] bytes = IOUtils.toByteArray(file.getInputstream());

		// Nome real do arquivo
		String nomeArquivoEdital = file.getFileName();
		
		// Identificação do usuário.
		PessoaBean pessoaBean = GenericBean.getPessoaBean();
				
		// Multi-part form
		FileUploadForm fuf = new FileUploadForm();
		fuf.setFileName(nomeArquivoEdital);
		fuf.setData(bytes);
		fuf.setTipoArquivo(TipoArquivo.ARQUIVO_EDITAL);		
		fuf.setIdPessoa(pessoaBean.getPessoaId());

		QManagerService serviceArquivo = ProviderServiceFactory
				.createServiceClient(QManagerService.class);
		
		// Código(ID) do projeto (pesquisa ou extensão) e stream do arquivo.
		response = serviceArquivo.uploadArquivoEdital(Integer.toString(idEdital), 
				tipoArquivoEdital,
				fuf);

		return response;
	}

	public Edital getEdital() {
		return edital;
	}

	public void setEdital(Edital edital) {
		this.edital = edital;
	}

	public boolean isTenhoNumeroAnoEdital() {
		return tenhoNumeroAnoEdital;
	}

	public void setTenhoNumeroAnoEdital(boolean tenhoNumeroAnoEdital) {
		this.tenhoNumeroAnoEdital = tenhoNumeroAnoEdital;
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public UploadedFile getArquivoEdital() {
		return arquivoEdital;
	}

	public void setArquivoEdital(UploadedFile arquivoEdital) {
		this.arquivoEdital = arquivoEdital;
	}
}
