package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.edu.ifpb.qmanager.chat.Conversa;
import br.edu.ifpb.qmanager.chat.Mensagem;
import br.edu.ifpb.qmanager.chat.SituacaoMensagem;
import br.edu.ifpb.qmanager.dao.BancoUtil;
import br.edu.ifpb.qmanager.dao.ConversaDAO;
import br.edu.ifpb.qmanager.dao.MensagemDAO;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.MapErroQManager;
import br.edu.ifpb.qmanager.entidade.Pessoa;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;
import br.edu.ifpb.qmanager.validacao.Validar;

@Path("chat")
public class ChatController {

	/**
	 * Cadastra uma Conversa.
	 * 
	 * @param Conversa
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/cadastrar/conversa")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarConversa(Conversa conversa) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.conversa(conversa);

		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.NOT_ACCEPTABLE).entity(
					erro.getErro());
			return builder.build();
		}

		try {

			int idChat = ConversaDAO.getInstance().insert(conversa);

			if (idChat != BancoUtil.IDVAZIO) {

				conversa.setIdConversa(idChat);

				for (Pessoa pessoa : conversa.getPessoas())
					ConversaDAO.getInstance().insertPessoa(conversa, pessoa);

				builder.status(Response.Status.OK);
				builder.entity(conversa);
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
	 * Cadastra nova mensagem.
	 * 
	 * @param Mensagem
	 * @return Response
	 */
	@PermitAll
	@POST
	@Path("/cadastrar/mensagem")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarMensagem(Mensagem mensagem) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		int validacao = Validar.mensagem(mensagem);

		if (validacao != Validar.VALIDACAO_OK) {
			MapErroQManager erro = new MapErroQManager(validacao);
			builder.status(Response.Status.NOT_ACCEPTABLE).entity(
					erro.getErro());
			return builder.build();
		}

		try {

			int idChatLine = MensagemDAO.getInstance().insert(mensagem);

			if (idChatLine != BancoUtil.IDVAZIO) {

				mensagem.setIdMensagem(idChatLine);

				List<Pessoa> pessoasNaConversa = ConversaDAO.getInstance()
						.getPessoas(mensagem.getConversa());

				final boolean visualizouMensagem = true;
				final boolean naoVisualizouMensagem = false;
				for (Pessoa pessoa : pessoasNaConversa) {
					if (pessoa.equals(mensagem.getRemetente()))
						MensagemDAO.getInstance().insertSituacaoMensagem(
								mensagem, pessoa, visualizouMensagem);
					else
						MensagemDAO.getInstance().insertSituacaoMensagem(
								mensagem, pessoa, naoVisualizouMensagem);
				}

				builder.status(Response.Status.OK);
				builder.entity(mensagem);
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

	@PermitAll
	@POST
	@Path("/consultar/pessoa")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Conversa> consultarConversasPorPessoa(Pessoa pessoa)
			throws SQLException {

		List<Conversa> conversas = new ArrayList<Conversa>();

		conversas = ConversaDAO.getInstance().getByPessoa(pessoa);

		List<Pessoa> pessoas;
		for (Conversa conversa : conversas) {
			pessoas = ConversaDAO.getInstance().getPessoas(conversa);
			conversa.setPessoas(pessoas);
		}

		return conversas;
	}

	@PermitAll
	@POST
	@Path("/consultar/naovizualizada/quantidade")
	@Consumes("application/json")
	@Produces("application/json")
	public int quantidadeConversasNaoVisualizadas(Pessoa pessoa)
			throws SQLException {

		return ConversaDAO.getInstance().getQuantidadeConversasNaoVisualizadas(
				pessoa);
	}

	@PermitAll
	@POST
	@Path("/consultar/mensagens")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Mensagem> consultarMensagensPorConversa(Conversa conversa)
			throws SQLException {

		List<Mensagem> mensagens = MensagemDAO.getInstance()
				.getMensagensByConversa(conversa);

		List<Pessoa> pessoasDaConversa = ConversaDAO.getInstance().getPessoas(
				conversa);

		for (Mensagem mensagem : mensagens) {
			List<SituacaoMensagem> situacoes = new LinkedList<SituacaoMensagem>();
			for (Pessoa pessoa : pessoasDaConversa) {
				SituacaoMensagem situacao = MensagemDAO.getInstance()
						.pessoaVisualizouMensagem(mensagem, pessoa);
				situacoes.add(situacao);
			}
			mensagem.setSituacoes(situacoes);
		}

		return mensagens;
	}

}
