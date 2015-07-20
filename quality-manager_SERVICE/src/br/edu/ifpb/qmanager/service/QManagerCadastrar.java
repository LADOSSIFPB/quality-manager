package br.edu.ifpb.qmanager.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.edu.ifpb.qmanager.dao.BancoUtil;
import br.edu.ifpb.qmanager.dao.CursoDAO;
import br.edu.ifpb.qmanager.dao.DiscenteDAO;
import br.edu.ifpb.qmanager.dao.EditalDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoBancariaDAO;
import br.edu.ifpb.qmanager.dao.InstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.dao.ParticipacaoDAO;
import br.edu.ifpb.qmanager.dao.PessoaDAO;
import br.edu.ifpb.qmanager.dao.PessoaHabilitadaDAO;
import br.edu.ifpb.qmanager.dao.ProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.dao.ProjetoDAO;
import br.edu.ifpb.qmanager.dao.RecursoInstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.dao.RecursoProgramaInstitucionalDAO;
import br.edu.ifpb.qmanager.dao.ServidorDAO;
import br.edu.ifpb.qmanager.dao.TurmaDAO;
import br.edu.ifpb.qmanager.entidade.CodeErroQManager;
import br.edu.ifpb.qmanager.entidade.Curso;
import br.edu.ifpb.qmanager.entidade.Discente;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.InstituicaoBancaria;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.MapErroQManager;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.RecursoInstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.RecursoProgramaInstitucional;
import br.edu.ifpb.qmanager.entidade.Server;
import br.edu.ifpb.qmanager.entidade.Servidor;
import br.edu.ifpb.qmanager.entidade.TipoParticipacao;
import br.edu.ifpb.qmanager.entidade.Turma;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.validacao.Validar;

/**
 * Essa classe é um dos recursos RESTFUL que contém os serviços para cadastro 
 * de entidades no sistema.
 * 
 * Os serviços para cadastro normalmente consomem e produzem pacotes de dados
 * em formato JSON.
 * 
 * @author Igor Barbosa
 * @author Rhavy Maia
 * @author Emanuel Guimarães
 * @author Eri Jonhson
 * @author Felipe Nascimento
 * @author Ivanildo Terceiro
 */
@Path("cadastrar")
public class QManagerCadastrar {

	/**
	 * Serviço para cadastrar Instituição Financiadora.
	 * 
	 * Consome:
	 * {
	 * 		"cnpj": "10783898000175",
	 * 		"nomeInstituicaoFinanciadora": "Instituto Federal de Educação, Ciência e Tecnologia da Paraíba",
	 * 		"sigla": "IFPB",
	 * 		"gestor": {"pessoaId": 1}
	 * }
	 * 
	 * Produz:
	 * {
	 * 		"idInstituicaoFinanciadora": 1,
	 * 		"cnpj": "10783898000175",
	 * 		"nomeInstituicaoFinanciadora": "Instituto Federal de Educação, Ciência e Tecnologia da Paraíba",
	 * 		"sigla": "IFPB",
	 * 		"gestor": {"pessoaId": 1}
	 * }
	 * 
	 * @param JSON instituicaoFinanciadora
	 * @return Response
	 */
	@POST
	@Path("/instituicaofinanciadora")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarInstituicao(
			InstituicaoFinanciadora instituicaoFinanciadora) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.instituicaoFinanciadora(instituicaoFinanciadora);

		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.NOT_ACCEPTABLE).entity(
					erro.getErro());
			return builder.build();
		}

		try {
			// Verificar se o cnpj já foi cadastrado.
			boolean isCNPJCadastrado = InstituicaoFinanciadoraDAO.getInstance()
					.isCNPJCadastrado(instituicaoFinanciadora.getCnpj());

			if (isCNPJCadastrado) {
				MapErroQManager erro = new MapErroQManager(
						CodeErroQManager.INSTITUICAO_FINANCIADORA_JA_CADASTRADA);
				builder.status(Response.Status.NOT_ACCEPTABLE).entity(
						erro.getErro());
				return builder.build();
			}

			int idInstituicaoFinanciadora = InstituicaoFinanciadoraDAO
					.getInstance().insert(instituicaoFinanciadora);

			if (idInstituicaoFinanciadora != BancoUtil.IDVAZIO) {

				instituicaoFinanciadora
						.setIdInstituicaoFinanciadora(idInstituicaoFinanciadora);

				builder.status(Response.Status.OK);
				builder.entity(instituicaoFinanciadora);

			} else {
				builder.status(Response.Status.NOT_ACCEPTABLE);
				// TODO: Inserir mensagem de erro.
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
	 * Serviço para cadastrar Recurso para Instituição Financiadora.
	 * 
	 * TODO: Perguntar para a cliente:
	 *  - No caso em que Recurso da Instituição Financiadora perde a validade, 
	 * o que acontece com os Recursos de Programas Institucionais a ele 
	 * associados?
	 * 
	 * Consome:
	 * {
	 * 		"orcamento": 5234.68,
	 * 		"validadeInicial": "2014-03-26", // java.util.Date ou java.sql.Date
	 * 		"validadeFinal": 1427342400000,  // java.util.Date ou java.sql.Date
	 * 		"instituicaoFinanciadora": {"idInstituicaoFinanciadora": 1}
	 * }
	 * 
	 * Produz:
	 * {
	 * 		"idRecursoIF": 1,
	 * 		"orcamento": 5234.68,
	 * 		"validadeInicial": 1395806400000, // java.util.Date
	 * 		"validadeFinal": 1427342400000,   // java.util.Date
	 * 		"instituicaoFinanciadora": {"idInstituicaoFinanciadora": 1},
	 * 		"recursoValido": true
	 * }
	 * 
	 * @param JSON recurso
	 * @return Response
	 */
	@POST
	@Path("/recursoinstituicaofinanciadora")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarRecursoInstituicao(
			RecursoInstituicaoFinanciadora recurso) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.recursoInstituicaoFinanciadora(recurso);

		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.NOT_ACCEPTABLE).entity(
					erro.getErro());
			return builder.build();
		}

		try {

			int idRecurso = RecursoInstituicaoFinanciadoraDAO.getInstance()
					.insert(recurso);

			if (idRecurso != BancoUtil.IDVAZIO) {

				recurso.setIdRecursoIF(idRecurso);

				builder.status(Response.Status.OK);
				builder.entity(recurso);

			} else {
				builder.status(Response.Status.NOT_ACCEPTABLE);
				// TODO: Inserir mensagem de erro.
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
	 * Serviço para cadastrar Programa Institucional.
	 * 
	 * Consome:
	 * {
	 * 		"nomeProgramaInstitucional": "Programa Institucional de Bolsas de Extensão",
	 * 		"sigla": "PROBEXT",
	 * 		"instituicaoFinanciadora": {"idInstituicaoFinanciadora": 1},
	 * 		"gestor": {"pessoaId": 1}
	 * }
	 * 
	 * Produz:
	 * {
	 * 		"idProgramaInstitucional": 1,
	 * 		"nomeProgramaInstitucional": "Programa Institucional de Bolsas de Extensão",
	 * 		"sigla": "PROBEXT",
	 * 		"instituicaoFinanciadora": {"idInstituicaoFinanciadora": 1},
	 * 		"gestor": {"pessoaId": 1}
	 * }
	 * 
	 * @param JSON ProgramaInstitucional
	 * @return Response
	 */
	@POST
	@Path("/programainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarProgramaInstitucional(
			ProgramaInstitucional programaInstitucional) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.programaInstitucional(programaInstitucional);

		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.NOT_ACCEPTABLE).entity(
					erro.getErro());
			return builder.build();
		}

		try {

			int idProInstitucional = ProgramaInstitucionalDAO.getInstance()
					.insert(programaInstitucional);

			if (idProInstitucional != BancoUtil.IDVAZIO) {

				programaInstitucional
						.setIdProgramaInstitucional(idProInstitucional);

				builder.status(Response.Status.OK);
				builder.entity(programaInstitucional);

			} else {
				builder.status(Response.Status.NOT_ACCEPTABLE);
				// TODO: Inserir mensagem de erro.
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
	 * Serviço para cadastrar Recurso para Programa Institucional.
	 * 
	 * Consome:
	 * {
	 * 		"orcamento": 150000000.12,
	 * 		"validadeInicial": "2014-03-30", // java.util.Date ou java.sql.Date
	 * 		"validadeFinal": 1427688000000,  // java.util.Date ou java.sql.Date
	 * 		"programaInstitucional": {"idProgramaInstitucional": 1},
	 * 		"recursoInstituicaoFinanciadora": {"idRecursoIF": 1}
	 * }
	 * 
	 * Produz:
	 * {
	 * 		"idRecursoPI": 1,
	 * 		"orcamento": 150000000.12,
	 * 		"validadeInicial": 1396152000000, // java.util.Date
	 * 		"validadeFinal": 1427688000000,   // java.util.Date
	 * 		"programaInstitucional": {"idProgramaInstitucional": 1},
	 * 		"recursoValido": true,
	 * 		"recursoInstituicaoFinanciadora": {"idRecursoIF": 1}
	 * }
	 * 
	 * @param JSON recurso
	 * @return Response
	 */
	@POST
	@Path("/recursoprogramainstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarRecursoPrograma(
			RecursoProgramaInstitucional recurso) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.recursoProgramaInstitucional(recurso);

		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.NOT_ACCEPTABLE).entity(
					erro.getErro());
			return builder.build();
		}

		try {

			int idRecurso = BancoUtil.IDVAZIO;

			// verificar se há orçamento válido para Programa Institucional a
			// ser cadastrado.
			int idRecursoIF = recurso.getRecursoInstituicaoFinanciadora()
					.getIdRecursoIF();

			double valorOrcamento = recurso.getOrcamento();

			boolean orcamentoDisponivel = RecursoInstituicaoFinanciadoraDAO
					.getInstance().verificaOrcamento(idRecursoIF,
							valorOrcamento);

			if (!orcamentoDisponivel) {
				MapErroQManager erro = new MapErroQManager(
						CodeErroQManager.ORCAMENTO_IF_INSUFICIENTE);
				builder.status(Response.Status.CONFLICT).entity(erro.getErro());
				return builder.build();
			}

			idRecurso = RecursoProgramaInstitucionalDAO.getInstance().insert(
					recurso);

			if (idRecurso != BancoUtil.IDVAZIO) {

				recurso.setIdRecursoPI(idRecurso);

				builder.status(Response.Status.OK);
				builder.entity(recurso);

			} else {

				builder.status(Response.Status.NOT_ACCEPTABLE);
				// TODO: Inserir mensagem de erro.
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
	 * Serviço para cadastrar Edital.
	 * 
	 * @param JSON Edital
	 * @return Response
	 */
	@POST
	@Path("/edital")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarEdital(Edital edital) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.edital(edital);

		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.NOT_ACCEPTABLE).entity(
					erro.getErro());
			return builder.build();
		}

		try {

			// verificar se há orçamento válido para Edital a ser cadastrado.
			ProgramaInstitucional programaInstitucional = ProgramaInstitucionalDAO
					.getInstance().getById(
							edital.getProgramaInstitucional().getIdProgramaInstitucional());
			List<RecursoProgramaInstitucional> listaRecursos = RecursoProgramaInstitucionalDAO
					.getInstance().getAllByProgramaInstitucional(
							programaInstitucional);

			if ((listaRecursos == null) && (edital.getBolsaDiscente() > 0.0)
					&& (edital.getBolsaDocente() > 0.0)) {
				MapErroQManager erro = new MapErroQManager(
						CodeErroQManager.ORCAMENTO_PI_INSUFICIENTE);
				builder.status(Response.Status.NOT_ACCEPTABLE).entity(
						erro.getErro());
				return builder.build();
			}

			double orcamentoDisponivel = 0.0;
			for (int i = 0; i < listaRecursos.size(); i++)
				orcamentoDisponivel += listaRecursos.get(i).getOrcamento();

			// TODO: verificar se essa função calcula valor do orçamento
			// adequadamente
			double valorOrcamento = (edital.getBolsaDiscente() * edital.getVagasBolsistasDiscentePorProjeto()) 
					+ (edital.getBolsaDocente() * edital.getVagasBolsistasDocentePorProjeto());

			boolean temOrcamentoDisponivel = (orcamentoDisponivel - valorOrcamento) >= 0.0 ? true
					: false;

			if (!temOrcamentoDisponivel) {
				MapErroQManager erro = new MapErroQManager(
						CodeErroQManager.ORCAMENTO_PI_INSUFICIENTE);
				builder.status(Response.Status.CONFLICT).entity(erro.getErro());
				return builder.build();
			}
			
			int idEdital = EditalDAO.getInstance().insert(edital);

			if (idEdital != BancoUtil.IDVAZIO) {

				edital.setIdEdital(idEdital);

				builder.status(Response.Status.OK);
				builder.entity(edital);

			} else {
				builder.status(Response.Status.NOT_ACCEPTABLE);
				// TODO: Inserir mensagem de erro.
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
	 * Serviço para cadastrar Projeto.
	 * 
	 * Consumes:
	 * {
	 * 		"nomeProjeto": "Uma ferramenta de apoio à gestão de projetos para coordenadores de pesquisa no IFPB",
	 * 		"inicioProjeto": "2014-04-01", // java.util.Date ou java.sql.Date
	 * 		"fimProjeto": 1427857200000,   // java.util.Date ou java.sql.Date
	 * 		"processo": "1234567890123456780",
	 * 		"tipoProjeto": "P",
	 * 		"orcamento": 1500.25,
	 * 		"edital": {"idEdital": 1},
	 * 		"campus": {"idCampusInstitucional": 4},
	 * }
	 * 
	 * Produz:
	 * {
	 * 		"idProjeto": 1,
	 * 		"nomeProjeto": "Uma ferramenta de apoio à gestão de projetos para coordenadores de pesquisa no IFPB",
	 * 		"inicioProjeto": 1396321200000, // java.util.Date 
	 * 		"fimProjeto": 1427857200000,    // java.util.Date 
	 * 		"processo": "1234567890123456780",
	 * 		"tipoProjeto": "P",
	 * 		"nomeTipoProjeto": "Pesquisa",
	 * 		"orcamento": 1500.25,
	 * 		"edital": {"idEdital": 1},
	 * 		"campus": {"idCampusInstitucional": 4},
	 * }
	 * 
	 * @param JSON Projeto
	 * @return Response
	 */
	@POST
	@Path("/projeto")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarProjeto(Projeto projeto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.projeto(projeto);

		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.NOT_ACCEPTABLE).entity(
					erro.getErro());
			return builder.build();
		}

		try {

			int idEdital = projeto.getEdital().getIdEdital();
			Edital edital = EditalDAO.getInstance().getById(idEdital);
			projeto.setEdital(edital);

			projeto.setInicioProjeto(edital.getInicioAtividades());
			long seisMeses = 15778463000L;
			projeto.setFimProjeto(new java.sql.Date(edital
					.getInicioAtividades().getTime() + seisMeses));

			int idProjeto = ProjetoDAO.getInstance().insert(projeto);

			if (idProjeto == BancoUtil.IDVAZIO) {
				builder.status(Response.Status.NOT_ACCEPTABLE);
				// TODO: Inserir mensagem de erro.
				return builder.build();
			}

			projeto.setIdProjeto(idProjeto);

			// Cadastrar orientador do projeto
			Participacao participacaoOrientador = new Participacao();
			Servidor servidor = projeto.getOrientador();

			// Participação
			participacaoOrientador.setPessoa(servidor);
			participacaoOrientador.setProjeto(projeto);
			participacaoOrientador.setInicioParticipacao(projeto
					.getInicioProjeto());
			participacaoOrientador.setValorBolsa(0.0);
			participacaoOrientador.setTipoParticipacao(new TipoParticipacao(
					TipoParticipacao.TIPO_ORIENTADOR));

			ParticipacaoDAO.getInstance().insert(participacaoOrientador);

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
	@POST
	@Path("/discente")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarDiscente(Discente discente) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; // Validar.discente(discente);
		
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
				// TODO: Inserir mensagem de erro.
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
	@POST
	@Path("/servidor")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarServidor(Servidor servidor) {
		
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.servidor(servidor);

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
					// TODO: Inserir mensagem de erro.
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
	@POST
	@Path("/servidorhabilitado")
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
	 * Serviço para cadastrar Participação.
	 * 
	 * @param Participacao
	 * @return Response
	 */
	@POST
	@Path("/participacao")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarParticipacao(Participacao participacao) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.participacao(participacao);

		try {

			// Validar Edital.
			if (validacao == Validar.VALIDACAO_OK) {

				// Pesquisar Projeto.
				Projeto projeto = ProjetoDAO.getInstance().getById(
						participacao.getProjeto().getIdProjeto());

				// Pesquisar Edital.
				Edital edital = EditalDAO.getInstance().getById(
						projeto.getEdital().getIdEdital());
				
				// Participação com Projeto e Edital.
				projeto.setEdital(edital);
				participacao.setProjeto(projeto);

				if (edital != null) {

					// Verificação dos intervalos das atividades da participação 
					// com as datas de inicio de atividade do Edital.
					validacao = Validar.participacaoEdital(participacao);

					if (validacao == Validar.VALIDACAO_OK) {

						// Inserir Participação.
						int idParticipacao = ParticipacaoDAO.getInstance()
								.insert(participacao);

						if (idParticipacao != BancoUtil.IDVAZIO) {

							participacao.setIdParticipacao(idParticipacao);

							// Participação aceita.
							builder.status(Response.Status.OK);
							builder.entity(participacao);

						} else {
							
							// Participação não aceita.
							builder.status(Response.Status.NOT_ACCEPTABLE);
						}
					
					} else {

						MapErroQManager mapErro = new MapErroQManager(validacao);
						builder.status(Response.Status.NOT_ACCEPTABLE).entity(
								mapErro.getErro());
					}

				} else {

					MapErroQManager mapErro = new MapErroQManager(
							CodeErroQManager.EDITAL_ASSOCIADO_INVALIDO);
					builder.status(Response.Status.NOT_ACCEPTABLE).entity(
							mapErro.getErro());
				}

			} else {

				MapErroQManager mapErro = new MapErroQManager(validacao);
				builder.status(Response.Status.NOT_ACCEPTABLE).entity(
						mapErro.getErro());
			}

		} catch (SQLExceptionQManager qme) {

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
					qme.getErro());
		}

		return builder.build();
	}

	/**
	 * Cadastra uma Instituição Bancária.
	 * 
	 * @param InstituicaoBancaria
	 * @return Response
	 */
	@POST
	@Path("/instituicaobancaria")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarInstituicaoBancaria(
			InstituicaoBancaria instituicaoBancaria) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.instituicaoBancaria(instituicaoBancaria);

		if (validacao == Validar.VALIDACAO_OK) {

			try {

				int idInstituicaoBancaria = InstituicaoBancariaDAO
						.getInstance().insert(instituicaoBancaria);

				if (idInstituicaoBancaria != BancoUtil.IDVAZIO) {

					instituicaoBancaria
							.setIdInstituicaoBancaria(idInstituicaoBancaria);
					builder.status(Response.Status.OK);
					builder.entity(instituicaoBancaria);

				} else {
					
					builder.status(Response.Status.NOT_ACCEPTABLE);
					// TODO: Inserir mensagem de erro.
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
	 * Cadastra um Curso.
	 * 
	 * @param Curso
	 * @return Response
	 */
	@POST
	@Path("/curso")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarCurso(Curso curso) {
		
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.curso(curso);

		if (validacao == Validar.VALIDACAO_OK) {
			try {

				int idCurso = CursoDAO.getInstance().insert(curso);

				if (idCurso != BancoUtil.IDVAZIO) {

					// Curso inserido com sucesso.
					curso.setIdCurso(idCurso);
					builder.status(Response.Status.OK);
					builder.entity(curso);

				} else {
					
					builder.status(Response.Status.NOT_ACCEPTABLE);
					// TODO: Inserir mensagem de erro.
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
	 * Cadastra uma Turma.
	 * 
	 * @param Turma
	 * @return Response
	 */
	@POST
	@Path("/turma")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarTurma(Turma turma) {
		
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.turma(turma);

		if (validacao == Validar.VALIDACAO_OK) {

			try {

				int idTurma = TurmaDAO.getInstance().insert(turma);

				if (idTurma != BancoUtil.IDVAZIO) {

					turma.setIdTurma(idTurma);

					builder.status(Response.Status.OK);
					builder.entity(turma);

				} else {
					builder.status(Response.Status.NOT_ACCEPTABLE);
					// TODO: Inserir mensagem de erro.
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
	 * Verifica se o servidor está apto pra responder.
	 * 
	 * @return Response
	 */
	@GET
	@Path("/servidorOnline")
	@Produces("application/json")
	public Response servidorOnline() {

		ResponseBuilder builder = Response.status(Response.Status.OK);
		builder.expires(new Date());

		Server server = new Server();
		server.setOnline(true);
		builder.entity(server);

		return builder.build();
	}
}