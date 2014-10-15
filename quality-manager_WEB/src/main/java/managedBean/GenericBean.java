package managedBean;

import java.io.Serializable;

import javax.ws.rs.core.Response;

public class GenericBean<T> implements Serializable {

	public String requestCadastrar(T entidade, String path) {

		String aux = null;

		

		return aux;

	}

	// TODO: Não testado devido a falta de xhtml correspondente
	// Felipe Nascimento está pesquisando como fazer paginação.
	public Response requestGetAll(String path) {

		return null;

	}

	public Response requestSelectConsultar(String path) {

		return null;

	}
}
