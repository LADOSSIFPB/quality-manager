package jaas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.ProviderServiceFactory;
import service.QManagerService;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.Role;

public class CustomLoginModule implements LoginModule {

	private CallbackHandler handler;
	private Subject subject;
	private UserPrincipal userPrincipal;
	private RolePrincipal rolePrincipal;
	private List<String> userGroups;
	private Map options;
	private Map sharedState;

	// Configurable option
	private boolean debug = false;

	// Logger used to output debug information
	private Logger logger = LogManager.getLogger(CustomLoginModule.class);

	// User credentials
	private String username = null;
	private String password = null;

	private boolean isAuthenticated = false;
	private boolean commitSucceeded = false;

	/**
	 * Constructor
	 */
	public CustomLoginModule() {
		super();
	}

	/**
	 * Initializer
	 *
	 * @param subject
	 * @param callbackHandler
	 * @param sharedState
	 * @param options
	 */
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {

		// Store the handler
		this.handler = callbackHandler;

		// Subject reference holds the principals
		this.subject = subject;

		this.options = options;
		this.sharedState = sharedState;
		
		// Setup a logging class / state
		if ("true".equalsIgnoreCase((String) options.get("debug"))) {
			ConsoleHandler consoleHandler = new ConsoleHandler();
			logger.info(consoleHandler);
			debug = true;
		}
	}

	/**
	 *
	 * @return @throws LoginException
	 */
	@Override
	public boolean login() throws LoginException {

		boolean isLoged = false;
		
		// If no handler is specified throw a error
		if (handler == null) {
			throw new LoginException(
					"Error: no CallbackHandler available to recieve"
					+ " authentication information from the user");
		}

		// Declare the callbacks based on the JAAS spec
		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("login");
		callbacks[1] = new PasswordCallback("password", true);

		try {

			// Handle the callback and recieve the sent inforamation
			handler.handle(callbacks);
			username = ((NameCallback) callbacks[0]).getName();
			password = String.valueOf(((PasswordCallback) callbacks[1])
					.getPassword());

			// Debug the username / password
			if (debug) {
				logger.debug("Username: {0}", username);
				logger.debug("Password: {0}", password);
			}

			// We should never allow empty strings to be passed
			if (username == null || username.isEmpty() || password == null
					|| password.isEmpty()) {
				
				throw new LoginException("Valores vazios ou nulos não são aceitáveis");
			}

			// Verificar o usuário no serviço.
			Response response = autorizarPessoa(username, password);

			int status = response.getStatus();
			
			if (status == HttpStatus.SC_ACCEPTED) {
				
				List<Role> roles = response.readEntity(
						new GenericType<List<Role>>() {});
				
				// Assign the user roles
				userGroups = getRolesToUserGroups(roles);
				isAuthenticated = true;

				isLoged = true;
				
			} else {
				
				Erro erro = response.readEntity(Erro.class);				
				throw new LoginException(erro.getMensagem());
			}			

		} catch (LoginException | IOException | UnsupportedCallbackException exception) {
			
			throw new LoginException(exception.getMessage());
		}
		
		return isLoged;
	}

	/**
	 * Adds the username / roles to the principal
	 *
	 * @return @throws LoginException
	 */
	@Override
	public boolean commit() throws LoginException {

		if (!isAuthenticated) {
			
			return false;
			
		} else {

			userPrincipal = new UserPrincipal(username);
			subject.getPrincipals().add(userPrincipal);

			if (userGroups != null && userGroups.size() > 0) {
				for (String groupName : userGroups) {
					rolePrincipal = new RolePrincipal(groupName);
					subject.getPrincipals().add(rolePrincipal);
				}
			}

			commitSucceeded = true;

			return true;
		}
	}

	/**
	 * Terminates the logged in session on error
	 *
	 * @return @throws LoginException
	 */
	@Override
	public boolean abort() throws LoginException {
		
		if (!isAuthenticated) {
			
			return false;
			
		} else if (isAuthenticated && !commitSucceeded) {
			
			isAuthenticated = false;
			username = null;
			password = null;
			userPrincipal = null;
			
		} else {
			
			logout();
		}
		
		return true;
	}

	/**
	 * Logs the user out
	 *
	 * @return @throws LoginException
	 */
	@Override
	public boolean logout() throws LoginException {
		isAuthenticated = false;
		isAuthenticated = commitSucceeded;
		subject.getPrincipals().clear();
		return true;
	}

	/**
	 * Returns list of roles assigned to authenticated user.
	 *
	 * @return
	 */
	private List<String> getRolesToUserGroups(List<Role> roles) {

		List<String> roleList = new ArrayList<>();
		
		for (Role role: roles) {
			roleList.add(role.getNome());
		}		

		return roleList;
	}
	
	public Response autorizarPessoa(String identificador, String senha) {
		
		QManagerService service = ProviderServiceFactory
				.createServiceClient(QManagerService.class);

		Login login = new Login(identificador, senha);
		
		Response response = service.autorizarPessoa(login);

		return response;
	}
}
