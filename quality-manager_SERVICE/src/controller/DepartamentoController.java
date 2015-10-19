package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.edu.ifpb.qmanager.dao.DepartamentoDAO;
import br.edu.ifpb.qmanager.entidade.Departamento;

@Path("departamento")
public class DepartamentoController {
	
	@PermitAll
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Departamento> listarDepartamentos() throws SQLException {

		List<Departamento> departamentos = new ArrayList<Departamento>();

		departamentos = DepartamentoDAO.getInstance().getAll();

		return departamentos;
	}
}
