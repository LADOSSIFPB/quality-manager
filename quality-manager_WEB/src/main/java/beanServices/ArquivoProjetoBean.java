package beanServices;

import java.io.IOException;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;
import javax.ws.rs.core.Response;

import managedBean.GenericBean;

import org.apache.commons.io.IOUtils;

import service.FileUploadForm;
import service.ProviderServiceFactory;
import service.QManagerService;
import util.FileUtil;

@ManagedBean
@RequestScoped
public class ArquivoProjetoBean {

	private Part arquivo;

	public void importa() {

		try {

			QManagerService service = ProviderServiceFactory
					.createServiceClient(QManagerService.class);
			FileUploadForm fuf = new FileUploadForm();

			InputStream is = this.arquivo.getInputStream();
			byte[] bytes = IOUtils.toByteArray(is);

			fuf.setFileName(FileUtil.getFileName(arquivo));
			fuf.setData(bytes);
			
			Response response = service.uploadFile("projeto", fuf);
			
			//TODO: Tratar retorno.
			System.out.println(response.getStatus());

		} catch (IOException e) {
			GenericBean.setMessage("erro.uploadArquivo", 
					FacesMessage.SEVERITY_ERROR);
		}
	}

	public Part getArquivo() {
		return arquivo;
	}

	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}	
}