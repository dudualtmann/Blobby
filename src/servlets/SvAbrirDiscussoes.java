package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Grupo;
import control.Post;

/**
 * Servlet implementation class SvAbrirDiscussoes
 */
@WebServlet("/SvAbrirDiscussoes")
public class SvAbrirDiscussoes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Abrir a Parte de Discussões do Grupo Selecionado
		
		Post p = new Post();
		Grupo g = (Grupo) request.getSession().getAttribute("objgrupo");
		
		List<Post> listPosts = p.getPosts(g.getId());
		
		request.setAttribute("listPosts", listPosts);
		
		request.getRequestDispatcher("jspdiscussoesgrupo.jsp").forward(request, response);
		
	}

}
