package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Autor;

/**
 * Servlet implementation class SvSearchAutor
 */
@WebServlet("/SvSearchAutor")
public class SvSearchAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomeAutor = request.getParameter("autorsearch");
		System.out.println(nomeAutor+" - Nome Autor");
		Autor a = new Autor();
		List<Autor> list = a.searchAutores(nomeAutor);
		if(list!=null) {
		request.getSession().setAttribute("listautor", list);
		request.getRequestDispatcher("jspsearchautor.jsp").forward(request, response);
		}
		else {
			request.setAttribute("autorMsg", a.errorMsg);
			request.getRequestDispatcher("jspsearchautor.jsp").forward(request, response);
		}
		
	}

}
