package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Comment;
import control.Post;
import control.User;

/**
 * Servlet implementation class SvFazerComentario
 */
@WebServlet("/SvFazerComentario")
public class SvFazerComentario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Cadastrar o Comentário no Post Selecionado
		
		String content = request.getParameter("content");
		String username = (String) request.getSession().getAttribute("username");
		User u = new User();
		if(u.findUser(username)) {
			
			Post p = (Post) request.getSession().getAttribute("objpost");
			Comment c = new Comment(p, u, content);
			if(c.fazerComment()) {
				List<Comment> listComments = c.getComments(p.getId());
				request.getSession().setAttribute("listComments", listComments);
				request.getRequestDispatcher("jspposts.jsp").forward(request, response);
				
				
			}
			else {
				request.setAttribute("msg", "Erro ao Fazer Comentário: "+c.errorMsg);
				request.getRequestDispatcher("jspposts.jsp").forward(request, response);
			}
		}
		else {
			request.setAttribute("msg", "Erro ao Pegar Informações do Usuário: "+u.errorMsg);
			request.getRequestDispatcher("jspposts.jsp").forward(request, response);
		}
		
		
	}

}
