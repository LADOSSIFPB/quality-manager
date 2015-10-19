package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.edu.ifpb.qmanager.dao.TitulacaoDAO;
import br.edu.ifpb.qmanager.entidade.Titulacao;

@Path("titulacao")
public class TitulacaoController {
	
	@PermitAll
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Titulacao> listarTitulacoes() throws SQLException {

		List<Titulacao> titulacoes = new ArrayList<Titulacao>();

		titulacoes = TitulacaoDAO.getInstance().getAll();

		return titulacoes;
	}

}
