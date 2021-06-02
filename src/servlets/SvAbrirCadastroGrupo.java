package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.User;

/**
 * Servlet implementation class SvAbrirCadastroGrupo
 */
@WebServlet("/SvAbrirCadastroGrupo")
public class SvAbrirCadastroGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Abrir Cadastro de Grupos
		
		String username = (String) request.getSession().getAttribute("username");
		
		User u = new User();
		if(u.findUser(username)) {
			request.getSession().setAttribute("objuser", u);
			request.getRequestDispatcher("jspcadastrargrupo.jsp").forward(request, response);
		}
		else {
			request.setAttribute("msg", "Erro ao Localizar o Usuário que Fez a Solicitação.");
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		
	}

}
