package convert;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import managedBean.GenericBean;
import beanServices.TitulacaoAppScopeBean;
import br.edu.ifpb.qmanager.entidade.Titulacao;

@FacesConverter("convert.TitulacaoConverter")
public class TitulacaoConverter implements Converter {

	public static String EMPYT = "0";
	
	@Override
	public Titulacao getAsObject(FacesContext context, UIComponent component,
			String value) {

		Titulacao titulacaoSelect = null;

		try {
			
			TitulacaoAppScopeBean regiaoAppBean = (TitulacaoAppScopeBean) GenericBean
					.getApplicationContextValue("titulacaoAppScopeBean");
			
			List<Titulacao> titulacoes = regiaoAppBean.getTitulacoes();

			int id = Integer.parseInt(value);
			
			for (Titulacao titulacao : titulacoes) {
				
				if (titulacao.getIdTitulacao() == id) {
					titulacaoSelect = titulacao;
				}
			}		

		} catch (Throwable ex) {
			
			ResourceBundle bundle = ResourceBundle.getBundle("messages");
			FacesMessage msg = new FacesMessage(
					bundle.getString("erro.GeneralServerErro"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			
			throw new ConverterException(msg);
		}
		
		return titulacaoSelect;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent componente,
			Object value) {
		
		String idTitulacao = null;
        
		try {
			
			Titulacao titulacao = (Titulacao) value;			
            idTitulacao = Integer.toString(titulacao.getIdTitulacao());
            
		} catch (NullPointerException e) {
        	
			idTitulacao = EMPYT;
			
        } catch(Throwable ex) {
        	
            ResourceBundle bundle = ResourceBundle.getBundle("messages");
            FacesMessage msg = new FacesMessage(bundle.getString("erro.GeneralServerErro"));            
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            
            throw new ConverterException(msg);
        }
		
        return idTitulacao;
	}
}
