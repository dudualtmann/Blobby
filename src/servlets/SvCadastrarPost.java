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
import control.User;

/**
 * Servlet implementation class SvCadastrarPost
 */
@WebServlet("/SvCadastrarPost")
public class SvCadastrarPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Realizar o Cadastro do Post
		
		Grupo g = (Grupo) request.getSession().getAttribute("objgrupo");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String username = (String) request.getSession().getAttribute("username");
		User u = new User();
		
		if(u.findUser(username)) {
			Post p = new Post(g, u, title, content);
			if(p.fazerPost()) {
				List<Post> listPosts = p.getPosts(g.getId());
				request.setAttribute("listPosts", listPosts);
				request.setAttribute("msg", "Post Criado com Sucesso!");
				request.getRequestDispatcher("jspdiscussoesgrupo.jsp").forward(request, response);
			}
			else {
				List<Post> listPosts = p.getPosts(g.getId());
				request.setAttribute("listPosts", listPosts);
				request.setAttribute("msg", "Erro ao Criar Post: "+p.errorMsg);
				request.getRequestDispatcher("jspdiscussoesgrupo.jsp").forward(request, response);
				
			}
		}
		else {
			Post p = new Post();
			List<Post> listPosts = p.getPosts(g.getId());
			
			request.setAttribute("listPosts", listPosts);
			request.setAttribute("msg", "Erro ao coletar as informações do usuário: "+u.errorMsg);
			request.getRequestDispatcher("jspdiscussoesgrupo.jsp").forward(request, response);
		}
		
		
	}

}
