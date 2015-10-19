package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.edu.ifpb.qmanager.dao.AreaDAO;
import br.edu.ifpb.qmanager.dao.GrandeAreaDAO;
import br.edu.ifpb.qmanager.entidade.Area;
import br.edu.ifpb.qmanager.entidade.GrandeArea;

@Path("area")
public class AreaController {
	
	@PermitAll
	@GET
	@Path("/consultar/grandesareas/listar")
	@Produces("application/json")
	public List<GrandeArea> listarGrandesAreas() throws SQLException {

		List<GrandeArea> grandesAreas = new ArrayList<GrandeArea>();

		grandesAreas = GrandeAreaDAO.getInstance().getAll();

		return grandesAreas;
	}

	@PermitAll
	@GET
	@Path("/consultar/grandearea/{idGrandeArea}")
	@Produces("application/json")
	public List<Area> consultarAreasByGrandeArea(
			@PathParam("idGrandeArea") int idGrandeArea) throws SQLException {

		List<Area> areas = new ArrayList<Area>();

		areas = AreaDAO.getInstance().getAreaByGrandeArea(idGrandeArea);

		return areas;
	}

}
