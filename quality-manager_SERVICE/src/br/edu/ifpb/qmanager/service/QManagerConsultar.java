package br.edu.ifpb.qmanager.service;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.edu.ifpb.qmanager.dao.CargoServidorDAO;
import br.edu.ifpb.qmanager.dao.CursoDAO;
import br.edu.ifpb.qmanager.dao.DiscenteDAO;
import br.edu.ifpb.qmanager.dao.EditalDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoBancariaDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.dao.ParticipacaoDAO;
import br.edu.ifpb.qmanager.dao.PessoaDAO;
import br.edu.ifpb.qmanager.dao.ProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.dao.ProjetoDAO;
import br.edu.ifpb.qmanager.dao.ServidorDAO;
import br.edu.ifpb.qmanager.dao.TipoParticipacaoDAO;
import br.edu.ifpb.qmanager.dao.TurmaDAO;
import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.DadosBancarios;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.Gestor;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.Login;
import br.edu.ifpb.qmanager.entidade.MembroProjeto;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.QManagerMapErro;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoParticipacao;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;
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
 * @version 0.1
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

		Gestor gestor = new Gestor("Márcia", "1234567", "65432191",
				"Rua das Mandiocas", "55888000", "8388776655",
				"marcia@gmail.com", "Ma12345%", new DadosBancarios(
						new InstituicaoBancaria("Banco do Brasil"), "013",
						"07560001231"));

		InstituicaoFinanciadora instituicaoFinanciadora = new InstituicaoFinanciadora(
				"12345", "Instituto Federal", "IFPB", 1000.0, gestor);

		ProgramaInstitucional programaInstitucional = new ProgramaInstitucional(
				"PIBIC-CT", "PIB", 490.0, instituicaoFinanciadora, gestor);

		Edital edital = new Edital("C:/Users/Emanuel/Desktop/JSON.txt", 15,
				2013, java.sql.Date.valueOf("2013-01-01"),
				java.sql.Date.valueOf("2013-02-01"),
				java.sql.Date.valueOf("2013-07-01"),
				java.sql.Date.valueOf("2014-01-01"), 10, 100.0, 200.0, 'P',
				programaInstitucional, gestor);

		Projeto projeto = new Projeto("RescueBot",
				java.sql.Date.valueOf("2014-5-10"),
				java.sql.Date.valueOf("2015-5-10"), "/opt/java/rescuebot",
				"/opt/java/parcial", "/opt/java/final", "23456789098765432123",
				'P', 3000.0, null);

		builder.entity(projeto);

		return builder.build();
	}

	/**
	 * Serviço que permite ao Usuário entrar no sistema.
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

				builder.status(Response.Status.OK);
				builder.entity(pessoa);

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
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

			builder.status(Response.Status.OK);
			builder.entity(instituicoesFinanciadoras);

		} catch (QManagerSQLException qme) {
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

		} catch (QManagerSQLException qme) {
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

		} catch (QManagerSQLException qme) {
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

		} catch (QManagerSQLException qme) {
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

		} catch (QManagerSQLException qme) {
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

		} catch (QManagerSQLException qme) {
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

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
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

		} catch (QManagerSQLException qme) {
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

		} catch (QManagerSQLException qme) {
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

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
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

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
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

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
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

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
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

		} catch (QManagerSQLException qme) {
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

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
			builder.status(Response.Status.CONFLICT).entity(erro);
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

		} catch (QManagerSQLException qme) {
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

		} catch (QManagerSQLException qme) {
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

			} catch (QManagerSQLException qme) {
				Erro erro = new Erro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());

				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
						erro);
			}
		} else {
			QManagerMapErro erro = new QManagerMapErro(validacao);
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

		} catch (QManagerSQLException qme) {
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

		} catch (QManagerSQLException qme) {
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

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/cursos")
	@Produces("application/json")
	public Response consultarCursos() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<Curso> cursos = CursoDAO.getInstance().getAll();

			builder.status(Response.Status.OK);
			builder.entity(cursos);

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@POST
	@Path("/curso")
	@Consumes("application/json")
	@Produces("application/json")
	public Response consultarCurso(IntegerUtil integerUtil) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Curso curso = CursoDAO.getInstance().getById(integerUtil.getId());

			builder.status(Response.Status.OK);
			builder.entity(curso);

		} catch (QManagerSQLException qme) {
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

		} catch (QManagerSQLException qme) {
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

		} catch (QManagerSQLException qme) {
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

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

	@GET
	@Path("/tipoparticipacao")
	@Produces("application/json")
	public Response consultarTipoParticipacao() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			List<TipoParticipacao> tiposParticipacoes = TipoParticipacaoDAO
					.getInstance().getAll();

			builder.status(Response.Status.OK);
			builder.entity(tiposParticipacoes);

		} catch (QManagerSQLException qme) {
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

		} catch (QManagerSQLException qme) {
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

		} catch (QManagerSQLException qme) {
			Erro erro = new Erro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		}

		return builder.build();
	}

}