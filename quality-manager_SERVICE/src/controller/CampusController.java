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

import br.edu.ifpb.qmanager.dao.CampusDAO;
import br.edu.ifpb.qmanager.entidade.Campus;
import br.edu.ifpb.qmanager.entidade.CodeErroQManager;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.entidade.MapErroQManager;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

@Path("campus")
public class CampusController {
	
	@PermitAll
	@POST
	@Path("/consultar/locais")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Campus> consultarLocais(Campus campus) throws SQLException {

		List<Campus> campi = new ArrayList<Campus>();

		campi = CampusDAO.getInstance().find(campus);

		return campi;
	}

	@PermitAll
	@GET
	@Path("/locais/listar")
	@Produces("application/json")
	public List<Campus> listarLocais() throws SQLException {

		List<Campus> campus = new ArrayList<Campus>();

		campus = CampusDAO.getInstance().getAll();

		return campus;
	}

	@PermitAll
	@GET
	@Path("/consultar/local/{id}")
	@Produces("application/json")
	public Response consultarLocal(@PathParam("id") int idLocal) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Campus campus = CampusDAO.getInstance().getById(idLocal);

			if (campus != null) {

				builder.status(Response.Status.OK);
				builder.entity(campus);

			} else {

				MapErroQManager mapErro = new MapErroQManager(
						CodeErroQManager.CAMPUS_INVALIDO);
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

}
