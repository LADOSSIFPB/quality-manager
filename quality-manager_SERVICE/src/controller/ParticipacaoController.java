package controller;

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

import br.edu.ifpb.qmanager.dao.BancoUtil;
import br.edu.ifpb.qmanager.dao.ParticipacaoDAO;
import br.edu.ifpb.qmanager.dao.ProjetoDAO;
import br.edu.ifpb.qmanager.dao.TipoParticipacaoDAO;
import br.edu.ifpb.qmanager.entidade.CodeErroQManager;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.MapErroQManager;
import br.edu.ifpb.qmanager.entidade.Participacao;
import br.edu.ifpb.qmanager.entidade.Projeto;
import br.edu.ifpb.qmanager.entidade.TipoParticipacao;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.validacao.Validar;

@Path("participacao")
public class ParticipacaoController {
	
	/**
	 * Serviço para cadastrar Participação.
	 * 
	 * @param Participacao
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/cadastrar")
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
				participacao.setProjeto(projeto);

				if (projeto != null) {

					// Verificação dos intervalos das atividades da participação 
					// com as datas de inicio de atividade do Projeto.
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
							CodeErroQManager.PROJETO_INVALIDO);
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
	
	@PermitAll
	@GET
	@Path("/consultar/pesquisa/docente/quantidade")
	@Produces("application/json")
	public int quantidadeDocentesPesquisa() throws SQLException {

		int quantidade = ParticipacaoDAO.getInstance().getQuantidadeDocentesPesquisa();

		return quantidade;
	}
	
	@PermitAll
	@POST
	@Path("/consultar/tiposparticipacao")
	@Consumes("application/json")
	@Produces("application/json")
	public List<TipoParticipacao> consultarTiposParticipacao(
			TipoParticipacao tipoParticipacao) throws SQLException {

		List<TipoParticipacao> tiposParticipacoes = new ArrayList<TipoParticipacao>();

		tiposParticipacoes = TipoParticipacaoDAO.getInstance().find(
				tipoParticipacao);

		return tiposParticipacoes;
	}

	@PermitAll
	@GET
	@Path("/tiposparticipacao/listar")
	@Produces("application/json")
	public List<TipoParticipacao> listarTiposParticipacao() throws SQLException {

		List<TipoParticipacao> tiposParticipacoes = new ArrayList<TipoParticipacao>();

		tiposParticipacoes = TipoParticipacaoDAO.getInstance().getAll();

		return tiposParticipacoes;
	}

	@PermitAll
	@GET
	@Path("/consultar/tipoparticipacao/{id}")
	@Produces("application/json")
	public Response consultarTipoParticipacao(
			@PathParam("id") int idTipoParticipacao) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			TipoParticipacao tipoParticipacao = TipoParticipacaoDAO
					.getInstance().getById(idTipoParticipacao);

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
	@Path("/editar")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editarParticipacaoOrientador(Participacao participacao) {
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.VALIDACAO_OK; //Validar.participacao(participacao);
		if (validacao == Validar.VALIDACAO_OK) {

			try {

				ParticipacaoDAO.getInstance().update(participacao);

				builder.status(Response.Status.OK);
				builder.entity(participacao);

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
