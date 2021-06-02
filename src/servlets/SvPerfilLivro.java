package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Book;
import control.BookListaPessoal;

/**
 * Servlet implementation class SvPerfilLivro
 */
@WebServlet("/SvPerfilLivro")
public class SvPerfilLivro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id_livro = request.getParameter("id_livro");
		System.out.println(id_livro);
		Book b = new Book();
		if (b.localizarLivro(id_livro)) {
			request.getSession().setAttribute("sessionlivro", b);
			List<Book> list = b.livrosAutor(b.getAutor().getId());
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getNome());
			}
			request.getSession().setAttribute("maislivros", list);
			BookListaPessoal bl = new BookListaPessoal();
			double rating = bl.getRating(b.getId() + "");
			System.out.println(bl.errorMsg);
			String count = bl.countUsers(b.getId() + "");
			System.out.println(bl.errorMsg);
			Book b2 = new Book();
			List<Book> topBooks = b2.getTopBooks();
			String classificacao = "ND";
			int id_book = Integer.parseInt(id_livro);
			for(int i = 0;i < topBooks.size(); i++) {
				if(topBooks.get(i).getId() == id_book ) {
					classificacao = (i+1)+"";
				}
			}
			request.getSession().setAttribute("classificacao", classificacao);
			request.getSession().setAttribute("rating", rating);
			request.getSession().setAttribute("members", count);
			request.getRequestDispatcher("JspPerfilLivro.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", b.errorMsg);
			request.getRequestDispatcher("JspPerfilLivro.jsp").forward(request, response);
		}

	}

}
