package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.qmanager.QManagerClient;
import br.edu.ifpb.qmanager.entidade.Instituicao;
import br.edu.ifpb.qmanager.util.Metodos;

@WebServlet("/ControleDiscente")
public class ControleInstituicao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Instituicao instituicao = new Instituicao();

		instituicao.setCnpj(request.getParameter("nm_cnpj"));
		instituicao.setNomeInstituicao(request.getParameter("nm_instituicao"));
		instituicao.setSigla(request.getParameter("nm_sigla"));
		instituicao.setOrcamento(Metodos.tirarMascara(request.getParameter("vl_orcamento")));
		
		String aux = QManagerClient.requestClient(instituicao);
		
		response.setContentType("text/html");
        PrintWriter writer = response.getWriter();        
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Sample Application Servlet Page</title>");
        writer.println("</head>");
        writer.println("<body bgcolor=white>");

        writer.println("<h1>Retorno do JSON</h1>");
        
        writer.println(aux);
        writer.println("Espero que funcione!!!");

        writer.println("</body>");
        writer.println("</html>");
		
		//response.sendRedirect("paginaGestor.html");

	}

}
