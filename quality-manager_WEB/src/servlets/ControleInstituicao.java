package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import principal.Banco;
import dao.InstituicaoDAO;
import entidades.Instituicao;

@WebServlet("/ControleDiscente")
public class ControleInstituicao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Banco banco = new Banco();

		banco.iniciarConexao("root", "ifpbinfo");

		Instituicao instituicao = new Instituicao();

		instituicao.setCnpj(request.getParameter("nm_cnpj"));
		instituicao.setNomeInstituicao(request.getParameter("nm_instituicao"));
		instituicao.setSigla(request.getParameter("nm_sigla"));
		instituicao.setOrcamento(Double.parseDouble(request.getParameter("vl_orcamento")));

		InstituicaoDAO instituicaoDAO = new InstituicaoDAO(banco);
		
		instituicaoDAO.creat(instituicao);
		
		response.sendRedirect("paginaGestor.html");
		
		banco.encerrarConexao();

	}

}
