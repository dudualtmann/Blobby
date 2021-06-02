package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Book;
import control.Review;
import control.User;

/**
 * Servlet implementation class SvFazerResenha
 */
@WebServlet("/SvFazerResenha")
public class SvFazerResenha extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String resenha = request.getParameter("resenha");
		String id_livro = request.getParameter("id_livro");
		System.out.println(id_livro);
		String username = (String) request.getSession().getAttribute("username");
		
		User u = new User();
		Book b = new Book();
		
		if(u.findUser(username) && b.localizarLivro(id_livro)) {
		
			Review r = new Review(u, b, resenha);
			if(r.cadastrar()) {
				request.setAttribute("msg", "Resenha Escrita com Sucesso!");
				request.setAttribute("id_livro", id_livro);
				request.getRequestDispatcher("home.jsp").forward(request, response);;
				
			}
			else {
				request.setAttribute("msg", r.errorMsg);
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
			
		}
		

}
	
}
