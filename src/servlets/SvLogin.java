package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Admin;
import control.User;

/**
 * Servlet implementation class SvLogin
 */
@WebServlet("/SvLogin")
public class SvLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String senha = request.getParameter("senha");
		
		User u = new User();
		Admin a = new Admin();
		if(u.login(username, senha)) {
			// Abrir Home
			request.getSession().setAttribute("username", username);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		else if(a.login(username, senha)) {
			// Abrir Gerenciador do Admin (Comentado pois ainda não fiz a Pág de Gerenciador do Admin)
			request.getSession().setAttribute("admin", username);
			request.getRequestDispatcher("jspgerenciadoradm.jsp").forward(request, response);
			
		}
		else {
			request.setAttribute("lmsg", "Erro ao Fazer Login: "+u.errorMsg);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}
		
	}

}
