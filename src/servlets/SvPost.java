package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Comment;
import control.Grupo;
import control.Post;

/**
 * Servlet implementation class SvPost
 */
@WebServlet("/SvPost")
public class SvPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Abrir o Post Selecionado
		
		String id_post = request.getParameter("id_post");
		System.out.println(id_post+" <-- Id que veio do HTML do Post");
		Post p = new Post();
		Comment c = new Comment();
		
		if(p.localizarPost(id_post)) {
			List<Comment> listComments = c.getComments(p.getId());
			request.getSession().setAttribute("listComments", listComments);
			request.getSession().setAttribute("objpost", p);
			request.getRequestDispatcher("jspposts.jsp").forward(request, response);
		}
		else {
			Grupo g = (Grupo) request.getSession().getAttribute("objgrupo");
			List<Post> listPosts = p.getPosts(g.getId());
			
			request.setAttribute("listPosts", listPosts);
			
			request.getRequestDispatcher("jspdiscussoesgrupo.jsp").forward(request, response);
		}
	}

}
