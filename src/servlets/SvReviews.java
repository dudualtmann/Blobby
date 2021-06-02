package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Book;
import control.Review;

/**
 * Servlet implementation class SvReviews
 */
@WebServlet("/SvReviews")
public class SvReviews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_livro = (String) request.getSession().getAttribute("id_livro");
		
		Review r = new Review();
		Book b = new Book();
		String nome;
		if(b.localizarLivro(id_livro)) {
			nome = b.getNome();
			request.setAttribute("nomelivro", nome);
		}
		
		// Pegar Todas as Reviews desse livro
		
		List<Review> list = r.getReviews(id_livro);
		if(list!=null) {
			request.setAttribute("listreviews", list);
			request.getRequestDispatcher("jspreviews.jsp").forward(request, response);
			
		}
		else {
			request.setAttribute("msg", r.errorMsg);
			request.getRequestDispatcher("jspreviews.jsp").forward(request, response);
		}
		
	}

}
