package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Book;

/**
 * Servlet implementation class SvSearchBook
 */
@WebServlet("/SvSearchBook")
public class SvSearchBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String nomeLivro = request.getParameter("booksearch");
		
		Book b = new Book();
		List<Book> list = b.searchLivros(nomeLivro);
		if(list!=null) {
		request.setAttribute("listbooks", list);
		request.getRequestDispatcher("jspreview1.jsp").forward(request, response);
		}
		else {
			request.setAttribute("bookMsg", b.errorMsg);
			request.getRequestDispatcher("jspreview1.jsp").forward(request, response);
		}
		
	}
		
	}


