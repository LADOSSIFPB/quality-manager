package br.edu.ifpb.qmanager.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.http.HttpStatus;

import br.edu.ifpb.qmanager.dao.CargoServidorDAO;
import br.edu.ifpb.qmanager.dao.CursoDAO;
import br.edu.ifpb.qmanager.dao.DiscenteDAO;
import br.edu.ifpb.qmanager.dao.EditalDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoBancariaDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.dao.LocalDAO;
import br.edu.ifpb.qmanager.dao.ParticipacaoDAO;
import br.edu.ifpb.qmanager.dao.PessoaDAO;
import br.edu.ifpb.qmanager.dao.ProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.dao.ProjetoDAO;
import br.edu.ifpb.qmanager.dao.ServidorDAO;
import br.edu.ifpb.qmanager.dao.TipoParticipacaoDAO;
import br.edu.ifpb.qmanager.dao.TurmaDAO;
import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.entidade.CodeErroQManager;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Local;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.MapErroQManager;
import br.edu.ifpb.qmanager.entidade.MembroProjeto;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoParticipacao;
import br.edu.ifpb.qmanager.entidade.TipoPessoa;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.util.IntegerUtil;
import br.edu.ifpb.qmanager.util.PalavraUtil;
import br.edu.ifpb.qmanager.validacao.Validar;

/**
 * Classe que reune serviços de consulta ao banco de dados.
 * 
 * @author Igor Barbosa
 * @author Rhavy Maia
 * @author Emanuel Guimarães
 * @author Eri Jonhson
 * @author Felipe Nascimento
 * @author Ivanildo Terceiro
 * @version 1.0
 */
@Path("consultar")
public class QManagerConsultar {

	@GET
	@Path("/entidade")
	@Produces("application/json")
	public Response entidade() {

		ResponseBuilder builder = Response.status(Response.Status.OK);
		builder.expires(new Date());

		Login login = new Login();
		login.setIdentificador("rhavy.mg@gmail.com");
		login.setSenha("Rg123456%");

		builder.entity(login);

		return builder.build();
	}

	/**
	 * Serviço que permite ao Usuário logar no sistema.
	 * 
	 * @param login
	 * @return Usuario
	 */
	@POST
	@Path("/fazerLogin")
	@Consumes("application/json")
	@Produces("application/json")
	public Response fazerLogin(Login login) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.login(login);

		if (validacao == Validar.VALIDACAO_OK) {

			try {

				Pessoa pessoa = PessoaDAO.getInstance().getByLogin(login);

				if (pessoa != null) {
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
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/instituicoesfinanciadoras")
	@Produces("application/json")
	public Response consultarInstituicoes() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<InstituicaoFinanciadora> instituicoesFinanciadoras = InstituicaoFinanciadoraDAO
					.getInstance().getAll();

			// for (int i = 0; i < instituicoesFinanciadoras.size(); i++) {
			// instituicoesFinanciadoras.get(i).setGestor(null);
			// }

			builder.status(Response.Status.OK);
			builder.entity(instituicoesFinanciadoras);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/instituicaofinanciadora")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarInstituicao(IntegerUtil integerUtil) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			InstituicaoFinanciadora instituicoesFinanciadora = InstituicaoFinanciadoraDAO
					.getInstance().getById(integerUtil.getId());

			builder.status(Response.Status.OK);
			builder.entity(instituicoesFinanciadora);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/programasinstitucionais")
	@Produces("application/json")
	public Response consultarProgramasInstitucionais() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<ProgramaInstitucional> programasInstitucionais = ProgramaInstitucionalDAO
					.getInstance().getAll();

			Iterator<ProgramaInstitucional> lista = programasInstitucionais
					.iterator();

			// recuperar instituição financiadora pra cada programa
			// institucional
			while (lista.hasNext()) {
				ProgramaInstitucional programaAtual = lista.next();
				int idInstituicaoFinanciadora = programaAtual
						.getInstituicaoFinanciadora()
						.getIdInstituicaoFinanciadora();
				InstituicaoFinanciadora instituicaoFinanciadora = InstituicaoFinanciadoraDAO
						.getInstance().getById(idInstituicaoFinanciadora);
				programaAtual
						.setInstituicaoFinanciadora(instituicaoFinanciadora);
			}

			builder.status(Response.Status.OK);
			builder.entity(programasInstitucionais);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/programainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarProgramaInstitucional(IntegerUtil integerUtil) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			ProgramaInstitucional programaInstitucional = ProgramaInstitucionalDAO
					.getInstance().getById(integerUtil.getId());

			builder.status(Response.Status.OK);
			builder.entity(programaInstitucional);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/editais")
	@Produces("application/json")
	public Response consultarEditais() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Edital> editais = EditalDAO.getInstance().getAll();

			Iterator<Edital> lista = editais.iterator();

			while (lista.hasNext()) {
				Edital editalAtual = lista.next();
				int idProgramaInstitucional = editalAtual
						.getProgramaInstitucional()
						.getIdProgramaInstitucional();
				ProgramaInstitucional programaInstitucional = ProgramaInstitucionalDAO
						.getInstance().getById(idProgramaInstitucional);
				editalAtual.setProgramaInstitucional(programaInstitucional);
			}

			builder.status(Response.Status.OK);
			builder.entity(editais);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/edital")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarEdital(IntegerUtil integerUtil) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Edital edital = EditalDAO.getInstance()
					.getById(integerUtil.getId());

			builder.status(Response.Status.OK);
			builder.entity(edital);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/editaisprogramainstitucional")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarEditais(ProgramaInstitucional programaInstitucional) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.programaInstitucional(programaInstitucional);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				List<Edital> editais = EditalDAO.getInstance()
						.getByProgramaInstitucional(programaInstitucional);

				Iterator<Edital> lista = editais.iterator();

				while (lista.hasNext()) {
					Edital editalAtual = lista.next();
					editalAtual.setProgramaInstitucional(programaInstitucional);
				}

				builder.status(Response.Status.OK);
				builder.entity(editais);

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

	@GET
	@Path("/projetos")
	@Produces("application/json")
	public Response consultarProjetos() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Projeto> projetos = ProjetoDAO.getInstance().getAll();

			builder.status(Response.Status.OK);
			builder.entity(projetos);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/projeto")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarProjeto(IntegerUtil integerUtil) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Projeto projeto = ProjetoDAO.getInstance().getById(
					integerUtil.getId());

			builder.status(Response.Status.OK);
			builder.entity(projeto);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/projetosprogramainstitucional")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarProjetos(
			ProgramaInstitucional programaInstitucional) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.programaInstitucional(programaInstitucional);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				List<Projeto> projetos = ProjetoDAO.getInstance()
						.getByProgramaInstitucional(programaInstitucional);

				builder.status(Response.Status.OK);
				builder.entity(projetos);

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
	@Path("/projetosedital")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarProjetos(Edital edital) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.edital(edital);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				List<Projeto> projetos = ProjetoDAO.getInstance().getByEdital(
						edital);

				builder.status(Response.Status.OK);
				builder.entity(projetos);

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
	@Path("/projetosmembroprojeto")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarProjetosMembroProjeto(MembroProjeto membroProjeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.orientador(membroProjeto);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				List<Projeto> projetos = ProjetoDAO.getInstance()
						.getByMembroProjeto(membroProjeto);

				builder.status(Response.Status.OK);
				builder.entity(projetos);

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
	@Path("/projetoinformacoes")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarInformacoesProjeto(Projeto projeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.projeto(projeto);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				Edital edital = EditalDAO.getInstance().getById(
						projeto.getEdital().getIdEdital());

				projeto.setEdital(edital);

				List<Participacao> participacoes = ParticipacaoDAO
						.getInstance().getByProjeto(projeto);

				Iterator<Participacao> listaParticipacao = participacoes
						.iterator();

				List<Discente> discentes = new LinkedList<Discente>();

				while (listaParticipacao.hasNext()) {

					Participacao participacaoAtual = listaParticipacao.next();

					int tipoParticipacao = participacaoAtual
							.getTipoParticipacao().getIdTipoParticipacao();
					int idMembroProjeto = participacaoAtual.getMembroProjeto()
							.getPessoaId();

					if (tipoParticipacao == TipoParticipacao.TIPO_ORIENTANDO) {
						Discente discente = DiscenteDAO.getInstance().getById(
								idMembroProjeto);
						discentes.add(discente);
					} else if (tipoParticipacao == TipoParticipacao.TIPO_ORIENTADOR) {
						projeto.setOrientador(ServidorDAO.getInstance()
								.getById(idMembroProjeto));
					} else if (tipoParticipacao == TipoParticipacao.TIPO_COORIENTADOR) {
						projeto.setCoorientador(ServidorDAO.getInstance()
								.getById(idMembroProjeto));
					} else if (tipoParticipacao == TipoParticipacao.TIPO_COLABORADOR) {
						projeto.setColaborador(ServidorDAO.getInstance()
								.getById(idMembroProjeto));
					}

				}

				projeto.setDiscentes(discentes);

				builder.status(Response.Status.OK);
				builder.entity(projeto);

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

	@GET
	@Path("/servidores")
	@Produces("application/json")
	public Response consultarServidores() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Servidor> servidores = ServidorDAO.getInstance().getAll();

			builder.status(Response.Status.OK);
			builder.entity(servidores);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/servidoresprojeto")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarServidoresProjeto(Projeto projeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.projeto(projeto);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				List<Servidor> servidores = ServidorDAO.getInstance()
						.getByProjeto(projeto);

				builder.status(Response.Status.OK);
				builder.entity(servidores);

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

	@GET
	@Path("/servidorespesquisa")
	@Produces("application/json")
	public Response consultarServidoresPesquisa() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Servidor> servidores = ServidorDAO.getInstance()
					.getServidoresPesquisa();

			builder.status(Response.Status.OK);
			builder.entity(servidores);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/servidoresextensao")
	@Produces("application/json")
	public Response consultarServidoresExtensao() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Servidor> servidores = ServidorDAO.getInstance()
					.getServidoresExtensao();

			builder.status(Response.Status.OK);
			builder.entity(servidores);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/servidorespesquisa/{ano}")
	@Produces("application/json")
	public Response consultarServidoresPesquisa(@PathParam("ano") int ano) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Servidor> servidores = ServidorDAO.getInstance()
					.getServidoresPesquisa(ano);

			builder.status(Response.Status.OK);
			builder.entity(servidores);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/servidoresextensao/{ano}")
	@Produces("application/json")
	public Response consultarServidoresExtensao(@PathParam("ano") int ano) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Servidor> servidores = ServidorDAO.getInstance()
					.getServidoresExtensao(ano);

			builder.status(Response.Status.OK);
			builder.entity(servidores);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/servidor")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarServidor(IntegerUtil integerUtil) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Servidor servidor = ServidorDAO.getInstance().getById(
					integerUtil.getId());

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

	@GET
	@Path("/coordenadores")
	@Produces("application/json")
	public Response consultarCoordenadores() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Servidor> coordenadores = ServidorDAO.getInstance()
					.getAllCoordenadores();

			builder.status(Response.Status.OK);
			builder.entity(coordenadores);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/coordenador")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarCoordenador(IntegerUtil integerUtil) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Servidor coordenador = ServidorDAO.getInstance()
					.getCoordenadorById(integerUtil.getId());

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

	@GET
	@Path("/gestores")
	@Produces("application/json")
	public Response consultarGestores() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Servidor> gestores = ServidorDAO.getInstance()
					.getAllGestores();

			builder.status(Response.Status.OK);
			builder.entity(gestores);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/gestor")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarGestor(IntegerUtil integerUtil) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Servidor gestor = ServidorDAO.getInstance().getCoordenadorById(
					integerUtil.getId());

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

	@GET
	@Path("/discentes")
	@Produces("application/json")
	public Response consultarDiscentes() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Discente> discentes = DiscenteDAO.getInstance().getAll();

			builder.status(Response.Status.OK);
			builder.entity(discentes);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/discente")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarDiscente(IntegerUtil integerUtil) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Discente discente = DiscenteDAO.getInstance().getById(
					integerUtil.getId());

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

	@POST
	@Path("/discentesprojeto")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarDiscentesProjeto(Projeto projeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.projeto(projeto);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				List<Discente> discentes = DiscenteDAO.getInstance()
						.getByProjeto(projeto);

				builder.status(Response.Status.OK);
				builder.entity(discentes);

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
	@Path("/discentesnome")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarDiscentesNome(PalavraUtil palavraUtil) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Discente> discentes = DiscenteDAO.getInstance().getByPalavra(
					palavraUtil);

			builder.status(Response.Status.OK);
			builder.entity(discentes);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/instituicoesbancarias")
	@Produces("application/json")
	public Response consultarInstituicoesBancarias() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<InstituicaoBancaria> instituicoesBancarias = InstituicaoBancariaDAO
					.getInstance().getAll();

			builder.status(Response.Status.OK);
			builder.entity(instituicoesBancarias);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/instituicaobancaria")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarInstituicaoBancaria(IntegerUtil integerUtil) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			InstituicaoBancaria instituicaoBancaria = InstituicaoBancariaDAO
					.getInstance().getById(integerUtil.getId());

			builder.status(Response.Status.OK);
			builder.entity(instituicaoBancaria);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/cursos")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Curso> consultarCursos(Curso curso) throws SQLException {
		
		List<Curso> cursos = new ArrayList<Curso>();
		
		cursos = CursoDAO.getInstance().find(curso);
		
		return cursos;
	}
	
	@GET
	@Path("/cursos/listar")
	@Produces("application/json")
	public Response listarCursos() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Curso> cursos = CursoDAO.getInstance().getAll();

			builder.status(Response.Status.OK);
			builder.entity(cursos);

		} catch (SQLExceptionQManager qme) {
			
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/curso/{idcurso}")
	@Produces("application/json")
	public Response consultarCurso(@PathParam("idcurso") int idCurso) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {
			Curso curso = CursoDAO.getInstance().getById(idCurso);

			if (curso != null) {
				// Curso encontrado
				builder.status(Response.Status.OK);
				builder.entity(curso);
				
			} else {
				// Curso não encontrado.
				builder.status(Response.Status.NOT_FOUND);
				Erro erro = new MapErroQManager(
						CodeErroQManager.CURSO_INEXISTENTE).getErro();
				
				builder.entity(erro);
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
	@Path("/turmascoordenador")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarTurmasCoordenador(IntegerUtil integerUtil) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Turma> turmas = TurmaDAO.getInstance().getByCoordenador(
					integerUtil.getId());

			builder.status(Response.Status.OK);
			builder.entity(turmas);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/cargos")
	@Produces("application/json")
	public Response consultarCargos() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<CargoServidor> cargosServidor = CargoServidorDAO.getInstance()
					.getAll();

			builder.status(Response.Status.OK);
			builder.entity(cargosServidor);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/cargo")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarCargo(IntegerUtil integerUtil) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			CargoServidor cargoServidor = CargoServidorDAO.getInstance()
					.getById(integerUtil.getId());

			builder.status(Response.Status.OK);
			builder.entity(cargoServidor);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/tiposparticipacao")
	@Produces("application/json")
	public Response consultarTiposParticipacao() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<TipoParticipacao> tiposParticipacoes = TipoParticipacaoDAO
					.getInstance().getAll();

			builder.status(Response.Status.OK);
			builder.entity(tiposParticipacoes);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/tipoparticipacao")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarTipoParticipacao(IntegerUtil integerUtil) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			TipoParticipacao tipoParticipacao = TipoParticipacaoDAO
					.getInstance().getById(integerUtil.getId());

			builder.status(Response.Status.OK);
			builder.entity(tipoParticipacao);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/pessoasnome")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarPessoasNome(PalavraUtil palavraUtil) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Pessoa> pessoas = PessoaDAO.getInstance().getByPalavra(
					palavraUtil);

			builder.status(Response.Status.OK);
			builder.entity(pessoas);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/pessoa")
	@Produces("application/json")
	@Consumes("application/json")
	public Response consultarPessoa(IntegerUtil integerUtil) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Pessoa pessoa = PessoaDAO.getInstance()
					.getById(integerUtil.getId());

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

	@GET
	@Path("/pessoa/{idPessoa}/{idTipoPessoa}")
	@Produces("application/json")
	public Response consultarPessoaPorTipo(@PathParam("idPessoa") int idPessoa,
			@PathParam("idTipoPessoa") int idTipoPessoa) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Pessoa pessoa = PessoaDAO.getInstance().getById(idPessoa);

			if (pessoa != null) {

				builder.status(Response.Status.OK);
				
				int idTipoPessoaConsulta = pessoa.getTipoPessoa().getIdTipoPessoa();

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

	@GET
	@Path("/locais")
	@Produces("application/json")
	public Response consultarLocais() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Local> locais = LocalDAO.getInstance().getAll();

			builder.status(Response.Status.OK);
			builder.entity(locais);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/local")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarLocal(IntegerUtil integerUtil) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Local local = LocalDAO.getInstance().getById(integerUtil.getId());

			builder.status(Response.Status.OK);
			builder.entity(local);

		} catch (SQLExceptionQManager qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

}