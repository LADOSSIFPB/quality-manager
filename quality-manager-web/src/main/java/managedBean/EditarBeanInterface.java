package managedBean;

public interface EditarBeanInterface {
	
	/**
	 * Enviar para o serviço de cadastro e edição da entidade.
	 */
	public void save();
	
	/**
	 * Enviar para o serviço de remoção da entidade.
	 * 
	 * @return pageRedirect: Página que o fluxo será redirecionado após a 
	 * solicitação de remoção.
	 */
	public String remove();
}
