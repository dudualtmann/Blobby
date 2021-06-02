package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Grupo;

/**
 * Servlet implementation class SvSearchGroup
 */
@WebServlet("/SvSearchGroup")
public class SvSearchGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Procurar Grupos que contenha tal argumento no nome
		
        String nomeGrupo = request.getParameter("groupsearch");
		
		Grupo g = new Grupo();
		List<Grupo> list = g.searchGrupos(nomeGrupo);
		if(list!=null) {
		request.getSession().setAttribute("listgrupos", list);
		request.getRequestDispatcher("jspdelgroup.jsp").forward(request, response);
		}
		else {
			request.setAttribute("msg", g.errorMsg);
			request.getRequestDispatcher("jspdelgroup.jsp").forward(request, response);
		}
	}

}
