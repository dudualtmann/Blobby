package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Book;
import control.Grupo;
import control.User;

/**
 * Servlet implementation class SvSearch
 */
@WebServlet("/SvSearch")
public class SvSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Verificar Quais Livros, Grupos e Pessoas
		//contém o trecho que a pessoa digitou no título e redirecionar para um jsp com a página preenchida
		
		String argumento = request.getParameter("search");
		if(argumento == null || argumento.trim().isEmpty()) {
			request.setAttribute("bError", "Digite algo no Search");
			request.setAttribute("uError", "Digite algo no Search");
			request.setAttribute("gError", "Digite algo no Search");
			request.getRequestDispatcher("JspSearch.jsp").forward(request, response);
		}
		else {
		Book b = new Book();
		User u = new User();
		Grupo g = new Grupo();
		List<Book> searchLivros = b.searchLivros(argumento);
		List<User> searchUsers = u.searchUsers(argumento);
		List<Grupo> searchGrupos = g.searchGrupos(argumento);
		
		if(searchLivros==null)
			request.setAttribute("bError", b.errorMsg);
		if(searchUsers==null)
			request.setAttribute("uError", u.errorMsg);
		if(searchGrupos==null)
			request.setAttribute("gError", g.errorMsg);
		
		request.setAttribute("searchLivros", searchLivros);
		request.setAttribute("searchUsers", searchUsers);
		request.setAttribute("searchGrupos", searchGrupos);
		
		request.getRequestDispatcher("JspSearch.jsp").forward(request, response);
		}
		
	}

}
