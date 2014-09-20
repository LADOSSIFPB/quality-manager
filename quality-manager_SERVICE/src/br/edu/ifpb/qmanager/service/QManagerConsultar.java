package br.edu.ifpb.qmanager.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.edu.ifpb.qmanager.dao.DatabaseConnection;
import br.edu.ifpb.qmanager.dao.InstituicaoFinanciadoraDAO;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.qmanager.entidade.QManagerErro;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

/**
 * Descrição do método e serviço.
 * 
 * @author Eri Jonhson
 * @author Emanuel Guimarães
 * @param instituicao
 * @return
 */

@Path("consultar")
public class QManagerConsultar {

	@GET
	@Path("/instituicaofinanciadora")
	@Produces("application/json")
	public Response consultarInstituicao() {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();

		try {

			banco.iniciarConexao();

			InstituicaoFinanciadoraDAO instituicaoDAO = new InstituicaoFinanciadoraDAO(
					banco);
			List<InstituicaoFinanciadora> instituicoesFinanciadoras = instituicaoDAO.getAll();

			builder.status(Response.Status.OK);
			builder.entity(instituicoesFinanciadoras);

		} catch (QManagerSQLException qme) {
			QManagerErro erro = new QManagerErro();
			erro.setCodigo(qme.getErrorCode());
			erro.setMensagem(qme.getMessage());

			builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
		} finally {

			banco.encerrarConexao();
		}

		return builder.build();
	}
}