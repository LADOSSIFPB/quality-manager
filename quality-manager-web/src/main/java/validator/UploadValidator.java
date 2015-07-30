package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.model.NativeUploadedFile;

import br.edu.ifpb.qmanager.util.StringUtil;
import managedBean.GenericBean;

@FacesValidator("uploadValidator")
public class UploadValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object object) throws ValidatorException {

		FileUpload fileUpload = (FileUpload) component;
		
		NativeUploadedFile nuf = (NativeUploadedFile) object;
		
		boolean required = fileUpload.isRequired();
		
		if (required
				&& nuf.getFileName().trim().equals(StringUtil.STRING_VAZIO)){
			throw new ValidatorException(GenericBean.newBundledFacesMessage(
					FacesMessage.SEVERITY_ERROR, "Sumário",
					fileUpload.getLabel() + ": Informe o arquivo para o anexo.",
					fileUpload));			
		}
	}
}