package br.edu.ifpb.qmanager.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.http.HttpStatus;

import br.edu.ifpb.qmanager.dao.BancoUtil;
import br.edu.ifpb.qmanager.dao.DiscenteDAO;
import br.edu.ifpb.qmanager.dao.PessoaDAO;
import br.edu.ifpb.qmanager.dao.PessoaHabilitadaDAO;
import br.edu.ifpb.qmanager.dao.PessoaRoleDAO;
import br.edu.ifpb.qmanager.dao.ServidorDAO;
import br.edu.ifpb.qmanager.entidade.CodeErroQManager;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.MapErroQManager;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.Role;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.validacao.Validar;

@Path("pessoa")
public class PessoaController {
	
	/**
	 * Serviço para cadastrar Discente.
	 * 
	 * Consome:
	 * {
	 * 		"nomePessoa": "Eri Jonhson Oliveira da Silva",
	 * 		"cpf": "12345678921",
	 * 		"matricula": "20111003145",
	 * 		"endereco": "Rua Muniz Barreto de Lima, 92",
	 * 		"cep": "58487564",
	 * 		"telefone": "8399795879",
	 * 		"email": "erijonhson.os@gmail.com",
	 * 		"senha": "123456",
	 * 		"tipoPessoa": {"idTipoPessoa": 2},
	 * 		"campus": {"idCampusInstitucional": 4},
	 * 		"turma": {"idTurma": 1}
	 * }
	 * 
	 * Produz:
	 * {
	 * 		"pessoaId": 1,
	 * 		"nomePessoa": "Eri Jonhson Oliveira da Silva",
	 * 		"cpf": "12345678921",
	 * 		"matricula": "20111003145",
	 * 		"endereco": "Rua Muniz Barreto de Lima, 92",
	 * 		"cep": "58487564",
	 * 		"telefone": "8399795879",
	 * 		"email": "erijonhson.os@gmail.com",
	 * 		"senha": "123456",
	 * 		"tipoPessoa": {"idTipoPessoa": 2},
	 * 		"campus": {"idCampusInstitucional": 4},
	 * 		"turma": {"idTurma": 1},
	 * 		"habilitada": true
	 * }
	 * 
	 * @param JSON Discente
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/cadastrar/discente")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarDiscente(Discente discente) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.discente(discente);
		
		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.NOT_ACCEPTABLE).entity(
					erro.getErro());
			return builder.build();
		}

		try {

			int idDiscente = DiscenteDAO.getInstance().insert(discente);

			if (idDiscente != BancoUtil.IDVAZIO) {

				discente.setPessoaId(idDiscente);
				discente.setHabilitada(true);

				builder.status(Response.Status.OK);
				builder.entity(discente);

			} else {

				builder.status(Response.Status.NOT_ACCEPTABLE);
			}

		} catch (SQLExceptionQManager qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}
	
	/**
	 * Serviço para cadastrar Servidor.
	 * 
	 * Consome:
	 * {
	 * 		"nomePessoa": "Rhavy Maia Guedes",
	 * 		"cpf": "09876534523",
	 * 		"matricula": "32456798",
	 * 		"endereco": "Rua Capitão Domingos Cariris",
	 * 		"cep": "58432562",
	 * 		"telefone": "8396432156",
	 * 		"email": "rhavy.maia@gmail.com",
	 * 		"senha": "123456",
	 * 		"tipoPessoa": {"idTipoPessoa": 1},
	 * 		"campus": {"idCampusInstitucional": 4},
	 * 		"titulacao": {"idTitulacao": 3},
	 * 		"cargoServidor": {"idCargoServidor": 3},
	 * 		"departamento": {"idDepartamento": 19}
	 * }
	 * 
	 * Produz:
	 * {
	 * 		"pessoaId": 1,
	 * 		"nomePessoa": "Rhavy Maia Guedes",
	 * 		"cpf": "09876534523",
	 * 		"matricula": "32456798",
	 * 		"endereco": "Rua Capitão Domingos Cariris",
	 * 		"cep": "58432562",
	 * 		"telefone": "8396432156",
	 * 		"email": "rhavy.maia@gmail.com",
	 * 		"senha": "123456",
	 * 		"tipoPessoa": {"idTipoPessoa": 1},
	 * 		"campus": {"idCampusInstitucional": 4},
	 * 		"titulacao": {"idTitulacao": 3},
	 * 		"cargoServidor": {"idCargoServidor": 3},
	 * 		"departamento": {"idDepartamento": 19},
	 * 		"habilitada": true
	 * }
	 * 
	 * @param JSON Servidor
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/cadastrar/servidor")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarServidor(Servidor servidor) {
		
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.servidor(servidor);

		if (validacao == Validar.VALIDACAO_OK) {

			try {

				int idOrientador = ServidorDAO.getInstance().insert(servidor);

				if (idOrientador != BancoUtil.IDVAZIO) {

					servidor.setPessoaId(idOrientador);
					servidor.setHabilitada(true);

					builder.status(Response.Status.OK);
					builder.entity(servidor);

				} else {
					
					builder.status(Response.Status.NOT_ACCEPTABLE);
				}

			} catch (SQLExceptionQManager qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}

		} else {
			
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.NOT_ACCEPTABLE).entity(
					erro.getErro());
		}

		return builder.build();
	}
	
	/**
	 * Habilitar e cadastrar servidor.
	 * 
	 * @param JSON servidor
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/cadastrar/servidorhabilitado")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarServidorHabilitado(Servidor servidor) {
		
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());
		
		int validacao = Validar.VALIDACAO_OK; //Validar.servidor(servidor);

		if (validacao == Validar.VALIDACAO_OK) {

			try {

				// Verificar se servidor já está habilitado.
				Servidor servidorHabilitado =  PessoaHabilitadaDAO
						.getInstance().getServidorByMatricula(Integer.valueOf(
								servidor.getMatricula()));
				
				// Verificar se há CPF cadastrado para o usuário.
				boolean isCPFCadastrado = PessoaDAO.getInstance()
						.isCPFCadastrado(servidor.getCpf());				
				
				if (!servidorHabilitado.isHabilitada() && !isCPFCadastrado) {
					
					// Cadastrar servidor.
					int idServidor = ServidorDAO.getInstance().insert(servidor);

					if (idServidor != BancoUtil.IDVAZIO) {

						// Definir como habilitado o servidor;
						PessoaHabilitadaDAO.getInstance().setPessoaHabilitada(
								Integer.valueOf(servidor.getMatricula()));
						
						servidor.setPessoaId(idServidor);
						
						// Retornar servidor.
						builder.status(Response.Status.OK);
						builder.entity(servidor);

					} else {
						
						builder.status(Response.Status.NOT_ACCEPTABLE);
						// Retornar mensagem como servidor não habilitado.
					}
					
				} else {
					
					// Servidor já habilitado
					MapErroQManager erro = new MapErroQManager(
							CodeErroQManager.SERVIDOR_JA_HABILITADO);
					builder.status(Response.Status.NOT_ACCEPTABLE).entity(
							erro.getErro());
				}

			} catch (SQLExceptionQManager qme) {

				// Erro interno na manipulação dos dados.
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
			
		} else {
			
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.NOT_ACCEPTABLE).entity(
					erro.getErro());
		}
		
		return builder.build();
	}
	
	/**
	 * Serviço que permite ao Usuário logar no sistema retornando seus 
	 * papeis (roles) e chave de segurança.
	 * 
	 * @param login
	 * @return Usuario
	 */
	@PermitAll
	@POST
	@Path("/consultar/autorizacao")
	@Consumes("application/json")
	@Produces("application/json")
	public Response autorizarPessoa(Login login) {
		
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.login(login);

		if (validacao == Validar.VALIDACAO_OK) {

			try {

				// Consultar autorização de acesso.
				int idPessoa = PessoaDAO.getInstance().getIsAuthorized(login);

				if (idPessoa != BancoUtil.IDVAZIO) {

					builder.status(HttpStatus.SC_ACCEPTED);
					
					// Permissões de acesso de uma Pessoa.
					List<Role> roles = PessoaRoleDAO.getInstance()
							.getRoleByIdPessoa(idPessoa);
					
					builder.entity(roles);

				} else {

					builder.status(HttpStatus.SC_UNAUTHORIZED);
				}

			} catch (SQLExceptionQManager qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
						.entity(erro);
			}
			
		} else {

			MapErroQManager mapErro = new MapErroQManager(validacao);
			builder.status(Response.Status.BAD_REQUEST).entity(
					mapErro.getErro());
		}

		return builder.build();		
	}
	
	/**
	 * Serviço que permite consultar o Usuário logado no sistema.
	 * 
	 * @param login
	 * @return Usuario
	 */
	@PermitAll
	@POST
	@Path("/consultar/login")
	@Consumes("application/json")
	@Produces("application/json")
	public Response logarPessoa(Login login) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.login(login);

		if (validacao == Validar.VALIDACAO_OK) {

			try {

				Pessoa pessoa = PessoaDAO.getInstance().getByLogin(login);

				if (pessoa != null) {

					// Gerar chave de autenticação.
					int idAuthorizationKey = PessoaDAO.getInstance()
							.insertAuthorizationKey(pessoa);
					
					if (idAuthorizationKey != BancoUtil.IDVAZIO) {
						
						// Recuperar a Chave de autorização de acesso aos serviços.
						String authorizationKey = PessoaDAO.getInstance()
								.getAuthorizationKeyById(idAuthorizationKey);						
						pessoa.setAuthorizationKey(authorizationKey);
					}
					
					builder.status(HttpStatus.SC_ACCEPTED);
					builder.entity(pessoa);

				} else {

					builder.status(HttpStatus.SC_UNAUTHORIZED);
				}

			} catch (SQLExceptionQManager qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
						.entity(erro);
			}
		} else {

			MapErroQManager mapErro = new MapErroQManager(validacao);
			builder.status(Response.Status.BAD_REQUEST).entity(
					mapErro.getErro());
		}

		return builder.build();
	}
	
	@PermitAll
	@POST
	@Path("/consultar/servidores")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Servidor> consultarServidores(Servidor servidor)
			throws SQLException {

		List<Servidor> servidores = new ArrayList<Servidor>();

		servidores = ServidorDAO.getInstance().find(servidor);

		return servidores;
	}

	@PermitAll
	@GET
	@Path("/servidores/listar")
	@Produces("application/json")
	public List<Servidor> listarServidores() throws SQLException {

		List<Servidor> servidores = new ArrayList<Servidor>();

		servidores = ServidorDAO.getInstance().getAll();

		return servidores;
	}

	@PermitAll
	@GET
	@Path("/consultar/servidor/{id}")
	@Produces("application/json")
	public Response consultarServidor(@PathParam("id") int idServidor) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Servidor servidor = ServidorDAO.getInstance().getById(idServidor);

			builder.status(Response.Status.OK);
			builder.entity(servidor);

		} catch (SQLExceptionQManager qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}
	
	@PermitAll
	@POST
	@Path("/consultar/coordenadores")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Servidor> consultarCoordenadores(Servidor servidor)
			throws SQLException {

		List<Servidor> servidores = new ArrayList<Servidor>();

		servidores = ServidorDAO.getInstance().findCoordenadores(servidor);

		return servidores;
	}

	@PermitAll
	@GET
	@Path("/coordenadores/listar")
	@Produces("application/json")
	public List<Servidor> listarCoordenadores() throws SQLException {

		List<Servidor> coordenadores = new ArrayList<Servidor>();

		coordenadores = ServidorDAO.getInstance().getAllCoordenadores();

		return coordenadores;
	}

	@PermitAll
	@GET
	@Path("/consultar/coordenador/{id}")
	@Produces("application/json")
	public Response consultarCoordenador(@PathParam("id") int idCoordenador) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Servidor coordenador = ServidorDAO.getInstance()
					.getCoordenadorById(idCoordenador);

			builder.status(Response.Status.OK);
			builder.entity(coordenador);

		} catch (SQLExceptionQManager qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@PermitAll
	@POST
	@Path("/consultar/gestores")
	@Produces("application/json")
	public List<Servidor> consultarGestores(Servidor servidor)
			throws SQLException {

		List<Servidor> gestores = new ArrayList<Servidor>();

		gestores = ServidorDAO.getInstance().findGestores(servidor);

		return gestores;
	}

	@PermitAll
	@GET
	@Path("/gestores/listar")
	@Produces("application/json")
	public List<Servidor> listarGestores() throws SQLException {

		List<Servidor> gestores = new ArrayList<Servidor>();

		gestores = ServidorDAO.getInstance().getAllGestores();

		return gestores;
	}

	@PermitAll
	@GET
	@Path("/consultar/gestor/{id}")
	@Produces("application/json")
	public Response consultarGestor(@PathParam("id") int idGestor) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Servidor gestor = ServidorDAO.getInstance().getCoordenadorById(
					idGestor);

			builder.status(Response.Status.OK);
			builder.entity(gestor);

		} catch (SQLExceptionQManager qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@PermitAll
	@POST
	@Path("/consultar/servidoreshabilitados/")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Servidor> consultarServidoresHabilitados(Servidor servidor)
			throws SQLException {

		List<Servidor> servidores = new ArrayList<Servidor>();

		servidores = PessoaHabilitadaDAO.getInstance().find(servidor);

		return servidores;
	}

	@PermitAll
	@GET
	@Path("/consultar/servidorhabilitado/{siape}")
	@Produces("application/json")
	public Response buscarServidorHabilitado(@PathParam("siape") int siape) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Servidor servidorHabilitado = PessoaHabilitadaDAO.getInstance()
					.getServidorByMatricula(siape);

			if (servidorHabilitado != null) {

				builder.status(Response.Status.OK);
				builder.entity(servidorHabilitado);

			} else {

				MapErroQManager mapErro = new MapErroQManager(
						CodeErroQManager.SERVIDOR_HABILITADO_INEXISTENTE);
				builder.status(Response.Status.NOT_FOUND).entity(
						mapErro.getErro());
			}
		} catch (SQLExceptionQManager qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@PermitAll
	@POST
	@Path("/consultar/discentes")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Discente> consultarDiscentes(Discente discente)
			throws SQLException {

		List<Discente> discentes = new ArrayList<Discente>();

		discentes = DiscenteDAO.getInstance().find(discente);

		return discentes;
	}

	@PermitAll
	@GET
	@Path("/discentes/listar")
	@Produces("application/json")
	public List<Discente> listarDiscentes() throws SQLException {

		List<Discente> discentes = new ArrayList<Discente>();

		discentes = DiscenteDAO.getInstance().getAll();

		return discentes;
	}

	@PermitAll
	@GET
	@Path("/consultar/discente/{id}")
	@Produces("application/json")
	public Response consultarDiscente(@PathParam("id") int idDiscente) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Discente discente = DiscenteDAO.getInstance().getById(idDiscente);

			builder.status(Response.Status.OK);
			builder.entity(discente);

		} catch (SQLExceptionQManager qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}
	
	@PermitAll
	@POST
	@Path("/consultar")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Pessoa> consultarPessoas(Pessoa pessoa) throws SQLException {

		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		pessoas = PessoaDAO.getInstance().find(pessoa);

		return pessoas;
	}

	@PermitAll
	@GET
	@Path("/consultar/{id}")
	@Produces("application/json")
	public Response consultarPessoa(@PathParam("id") int idPessoa) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Pessoa pessoa = PessoaDAO.getInstance().getById(idPessoa);

			builder.status(Response.Status.OK);
			builder.entity(pessoa);

		} catch (SQLExceptionQManager qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@PermitAll
	@GET
	@Path("/consultar/{idPessoa}/{idTipoPessoa}")
	@Produces("application/json")
	public Response consultarPessoaPorTipo(@PathParam("idPessoa") int idPessoa,
			@PathParam("idTipoPessoa") int idTipoPessoa) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Pessoa pessoa = PessoaDAO.getInstance().getById(idPessoa);

			if (pessoa != null) {

				builder.status(Response.Status.OK);

				int idTipoPessoaConsulta = pessoa.getTipoPessoa()
						.getIdTipoPessoa();

				if (idTipoPessoaConsulta == TipoPessoa.TIPO_SERVIDOR
						&& idTipoPessoaConsulta == idTipoPessoa) {

					Servidor servidor = ServidorDAO.getInstance().getById(
							pessoa.getPessoaId());
					builder.entity(servidor);

				} else if (idTipoPessoaConsulta == TipoPessoa.TIPO_DISCENTE
						&& idTipoPessoaConsulta == idTipoPessoa) {

					Discente discente = DiscenteDAO.getInstance().getById(
							pessoa.getPessoaId());
					builder.entity(discente);
				}
			}

		} catch (SQLExceptionQManager qme) {

			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}
	
	@POST
	@Path("/editar/discente")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarDiscente(Discente discente) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.discente(discente);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				DiscenteDAO.getInstance().update(discente);

				builder.status(Response.Status.OK);
				builder.entity(discente);

			} catch (SQLExceptionQManager qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/editar/servidor")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarServidor(Servidor servidor) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.servidor(servidor);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				ServidorDAO.getInstance().update(servidor);

				builder.status(Response.Status.OK);
				builder.entity(servidor);

			} catch (SQLExceptionQManager qme) {

				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

}
