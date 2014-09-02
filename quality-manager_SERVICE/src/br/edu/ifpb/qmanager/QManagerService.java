package br.edu.ifpb.qmanager;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.edu.ifpb.entidade.QManagerErro;
import br.edu.ifpb.entidade.Server;
import br.edu.ifpb.qmanager.dao.DatabaseConnection;
import br.edu.ifpb.qmanager.dao.InstituicaoDAO;
import br.edu.ifpb.qmanager.entidade.Instituicao;
import br.edu.ifpb.qmanager.excecao.QManagerSQLException;

@Path("service")
public class QManagerService {	

	/**
	 * Descrição do método e serviço.
	 * @author Eri Jonhson
	 * @param instituicao
	 * @return
	 */
	@POST
	@Path("/cadastrarInstituicao")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarInstituicao(Instituicao instituicao) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		DatabaseConnection banco = new DatabaseConnection();
		
		if (validarInsitituicao(instituicao)) {
			
			try {			
	
				banco.iniciarConexao();
	
				InstituicaoDAO instituicaoDAO = new InstituicaoDAO(banco);
				int idInstituicao = instituicaoDAO.insert(instituicao);
				instituicao.setIdInstituicao(idInstituicao);
				
				builder.status(Response.Status.OK);
				builder.entity(instituicao);
				
			} catch (QManagerSQLException qme) {
				
				QManagerErro erro = new QManagerErro();
				erro.setCodigo(qme.getErrorCode());
				erro.setMensagem(qme.getMessage());
				
				builder.status(Response.Status.INTERNAL_SERVER_ERROR).entity(erro);
				
			} finally {
				
				banco.encerrarConexao();
			}
		}

		return builder.build();
	}

	private boolean validarInsitituicao(Instituicao instituicao) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@POST
	@Path("/cadastrarAluno")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarAluno(){
		return null;		
	}
	
	@POST
	@Path("/cadastrarProfessor")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarProfessor(){
		return null;		
	}
	
	@POST
	@Path("/cadastrarProgramaInstitucional")
	@Consumes("application/json")
	@Produces("application/json")
	public Response cadastrarProgramaInstitucional(){
		return null;		
	}

	@GET
	@Path("/servidorOnline")
	@Produces("application/json")
	public Server digaOlaServer() {

		Server server = new Server();
		server.setOnline(true);
		
		return server;
	}

}