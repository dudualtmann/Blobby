package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Book;
import control.BookListaPessoal;
import control.ListaPessoal;
import control.User;

/**
 * Servlet implementation class SvAddLista
 */
@WebServlet("/SvAddLista")
public class SvAddLista extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = (String) request.getSession().getAttribute("username");
		String rating = request.getParameter("status-select2");
		String status = request.getParameter("status-select1");
		String pagLidas = request.getParameter("pag_lidas");
		if(pagLidas.isEmpty())
			pagLidas = null;
		Book b = (Book) request.getSession().getAttribute("sessionlivro");
		User u = new User();
		if (u.findUser(username)) {
			ListaPessoal lp = u.getListaPessoal();
			BookListaPessoal bl = new BookListaPessoal(lp, b, rating, status, pagLidas);

			if (bl.verifyList(lp, b)) {
				// Fazer Update
				if(bl.updateList()) {
					
					double newRating = bl.getRating(b.getId()+"");
					System.out.println(newRating+" Novo Rating");
					b.setRating(newRating);
					if(b.updateRating()) {
					request.getSession().setAttribute("rating", newRating);	
					request.setAttribute("msg", "Lista Atualizada com Sucesso!");
					request.getRequestDispatcher("JspPerfilLivro.jsp").forward(request, response);
					}
				}
			} else {
				// Não foi achado nenhum registro que combine o Livro e a Lista daquele User.
				if (bl.errorMsg.equals("null")) {
					// O Argumento Book ou ListaPessoal era NULO.
					request.setAttribute("msg", "Erro ao Localizar o Livro ou a Lista do User.");
					request.getRequestDispatcher("JspPerfilLivro.jsp").forward(request, response);
				} else {
					// Fazer o Registro do Livro na Lista
					if (bl.addList()) {
						double newRating = bl.getRating(b.getId()+"");
						System.out.println(newRating+" Novo Rating");
						b.setRating(newRating);
						if(b.updateRating()) {
						request.getSession().setAttribute("rating", newRating);
						String members = (String) request.getSession().getAttribute("members");
						request.getSession().setAttribute("members", Integer.parseInt(members)+1);
						request.setAttribute("msg", "Livro adicionado a Lista com sucesso.");
						request.getRequestDispatcher("JspPerfilLivro.jsp").forward(request, response);
					}
					}
				}
			}

		} else {
			request.setAttribute("msg", "Não foi possível adicionar o livro a sua lista.");
			request.getRequestDispatcher("JspPerfilLivro.jsp").forward(request, response);

		}

	}

}
