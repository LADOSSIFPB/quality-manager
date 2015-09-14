package managedBean;

import java.io.IOException;
import java.util.ArrayList;
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

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.EditalCampusSubmissao;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.form.FileUploadForm;
import br.edu.ifpb.qmanager.tipo.TipoArquivo;
import br.edu.ifpb.qmanager.tipo.TipoArquivoEdital;

@ManagedBean(name = "editarEditalBean")
@SessionScoped
public class EditarEditalBean implements EditarBeanInterface {

	private Edital edital;

	private List<SelectItem> programasInstitucionais;

	private boolean tenhoNumeroAnoEdital;

	// Arquivo do Edital.
	private UploadedFile arquivoEdital;

	private List<EditalCampusSubmissao> editalCampiSubmissao;

	private int EDITAL_NAO_CADASTRADO = 0;

	private int CAMPI_SEM_PROJETO = 0;

	private QManagerService service = ProviderServiceFactory
			.createServiceClient(QManagerService.class);

	public void initEdital() {

		this.edital = new Edital();

		ProgramaInstitucional programaInstitucional = new ProgramaInstitucional();
		this.edital.setProgramaInstitucional(programaInstitucional);

		// Número e Ano do Edital
		setEditalAno();
		gerarEditalNumero();

		// Campi para submissão
		initEditalCampiSubmissao();
	}

	public EditarEditalBean() {

		initEdital();
	}

	public EditarEditalBean(Edital edital) {
		this.setEdital(edital);
	}

	private void setEditalAno() {

		Calendar agora = Calendar.getInstance();
		int ano = agora.get(Calendar.YEAR);
		this.edital.setAno(ano);
	}

	private void initEditalCampiSubmissao() {

		QManagerService serviceCampiSubmissao = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		List<Campus> campiConsulta = serviceCampiSubmissao.listarLocais();

		this.editalCampiSubmissao = new ArrayList<EditalCampusSubmissao>();

		if (!campiConsulta.isEmpty()) {

			for (Campus campus : campiConsulta) {

				EditalCampusSubmissao editalCampusSubmissao = new EditalCampusSubmissao();
				editalCampusSubmissao.setCampus(campus);

				this.editalCampiSubmissao.add(editalCampusSubmissao);
			}
		}
	}

	public void save() {

		Response response;

		try {

			if (getEdital().getIdEdital() == EDITAL_NAO_CADASTRADO) {

				PessoaBean pessoaBean = GenericBean.getPessoaBean();

				Servidor gestor = new Servidor();
				gestor.setPessoaId(pessoaBean.getPessoaId());
				this.edital.setGestor(gestor);

				// Enviar o Edital para o cadastro.
				QManagerService serviceEdital = ProviderServiceFactory
						.createServiceClient(QManagerService.class);
				response = serviceEdital.cadastrarEdital(this.edital);

				int statusCodeEdital = response.getStatus();

				if (statusCodeEdital == HttpStatus.SC_OK) {

					Edital editalResponse = response.readEntity(Edital.class);

					int idEdital = editalResponse.getIdEdital();
					this.edital.setIdEdital(idEdital);

					// TODO: Campi para submissão.
					int statusCampiSubmissao = enviarCampiSubmissao(idEdital);

					// Enviar arquivo do Edital.
					int statusCodeArquivoEdital = enviarArquivoEdital(idEdital);

					if (statusCodeArquivoEdital == HttpStatus.SC_OK) {

						GenericBean.setMessage("info.sucessoCadastroEdital",
								FacesMessage.SEVERITY_INFO);
						GenericBean.resetSessionScopedBean("editarEditalBean");

						// Detalhamento do edital inserido.
						EditalBean editalBean = new EditalBean();
						editalBean.detalharEdital(this.edital);

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

	private int enviarCampiSubmissao(int idEdital) {

		int statusCode = HttpStatus.SC_NOT_MODIFIED;

		// Campi para submissão
		List<EditalCampusSubmissao> editalCampiSubmissaoEnvio = new ArrayList<EditalCampusSubmissao>();

		// Edital
		Edital editalEnvio = new Edital();
		editalEnvio.setIdEdital(idEdital);

		for (EditalCampusSubmissao editalCampusSubmissaoEnvio : this.editalCampiSubmissao) {
			if (editalCampusSubmissaoEnvio.getQuantidadeProjeto() > CAMPI_SEM_PROJETO) {
				editalCampusSubmissaoEnvio.setEdital(editalEnvio);
				editalCampiSubmissaoEnvio.add(editalCampusSubmissaoEnvio);
			}
		}

		QManagerService serviceCampiSubmissao = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		// Código(ID) do projeto (pesquisa ou extensão) e stream do arquivo.
		Response response = serviceCampiSubmissao
				.cadastrarEditalCampiSubmissao(editalCampiSubmissaoEnvio);

		statusCode = response.getStatus();

		return statusCode;

	}

	public void resetSession(EditarEditalBean editarEditalBean) {

		GenericBean.resetSessionScopedBean("editarEditalBean");

		GenericBean.setSessionValue("editarEditalBean", editarEditalBean);
	}

	public void createEdit(Edital edital) {

		if (edital == null) {

			EditarEditalBean editarEditalBean = new EditarEditalBean();
			resetSession(editarEditalBean);
		} else {

			Response response = service.consultarEdital(edital.getIdEdital());
			this.edital = response.readEntity(Edital.class);
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

		Response response = enviarArquivoEdital(idEdital, this.arquivoEdital,
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
		response = serviceArquivo.uploadArquivoEdital(
				Integer.toString(idEdital), tipoArquivoEdital, fuf);

		return response;
	}

	public void sairEdicao() {

		GenericBean.resetSessionScopedBean("editarEditalBean");
		GenericBean.sendRedirect(PathRedirect.edital);
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

	public UploadedFile getArquivoEdital() {
		return arquivoEdital;
	}

	public void setArquivoEdital(UploadedFile arquivoEdital) {
		this.arquivoEdital = arquivoEdital;
	}

	public List<EditalCampusSubmissao> getEditalCampiSubmissao() {
		return editalCampiSubmissao;
	}

	public void setEditalCampiSubmissao(
			List<EditalCampusSubmissao> editalCampiSubmissao) {
		this.editalCampiSubmissao = editalCampiSubmissao;
	}
}
