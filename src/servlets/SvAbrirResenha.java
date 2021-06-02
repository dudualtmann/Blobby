package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Book;

/**
 * Servlet implementation class SvAbrirResenha
 */
@WebServlet("/SvAbrirResenha")
public class SvAbrirResenha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book b = new Book();
		String id_livro = request.getParameter("bookselect");
		if(b.localizarLivro(id_livro)) {
			System.out.println(b.getId()+"/"+b.getNome());
			request.setAttribute("objLivro", b);
			request.setAttribute("msg", "Livro localizado com sucesso. Agora só falta escrever a Resenha!");
			request.getRequestDispatcher("fazerreview.jsp").forward(request, response);
		}
		else {
			request.setAttribute("msg", b.errorMsg);
			request.getRequestDispatcher("jspreview1.jsp").forward(request, response);
		}
		
	}

}
