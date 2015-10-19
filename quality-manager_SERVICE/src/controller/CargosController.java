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

import br.edu.ifpb.qmanager.dao.CargoServidorDAO;
import br.edu.ifpb.qmanager.entidade.CargoServidor;
import br.edu.ifpb.qmanager.entidade.Erro;
import br.edu.ifpb.qmanager.excecao.SQLExceptionQManager;

@Path("cargo")
public class CargosController {
	
	@PermitAll
	@POST
	@Path("/consultar")
	@Consumes("application/json")
	@Produces("application/json")
	public List<CargoServidor> consultarCargos(CargoServidor cargoServidor)
			throws SQLException {

		List<CargoServidor> cargosServidor = new ArrayList<CargoServidor>();

		cargosServidor = CargoServidorDAO.getInstance().find(cargoServidor);

		return cargosServidor;
	}

	@PermitAll
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<CargoServidor> listarCargos() throws SQLException {

		List<CargoServidor> cargosServidor = new ArrayList<CargoServidor>();

		cargosServidor = CargoServidorDAO.getInstance().getAll();

		return cargosServidor;
	}

	@PermitAll
	@GET
	@Path("/consultar/{id}")
	@Produces("application/json")
	public Response consultarCargo(@PathParam("id") int idCargo) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			CargoServidor cargoServidor = CargoServidorDAO.getInstance()
					.getById(idCargo);

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

}
