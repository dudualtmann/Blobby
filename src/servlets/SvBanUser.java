package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.User;

/**
 * Servlet implementation class SvBanUser
 */
@WebServlet("/SvBanUser")
public class SvBanUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Banir Usuário
		
		User u = new User();
		String username = request.getParameter("username");
		
		if(u.findUser(username)) {
			
			if(u.delUser()) {
				request.setAttribute("msg", "Usuário Banido.");
				request.getRequestDispatcher("jspgerenciadoradm.jsp").forward(request, response);
			}
			else {
				request.setAttribute("msg", "Erro ao Banir: "+u.errorMsg);
				request.getRequestDispatcher("jspgerenciadoradm.jsp").forward(request, response);
			}
			
		}
		else {
			request.setAttribute("msg", "Erro ao Localizar Usuário: "+u.errorMsg);
			request.getRequestDispatcher("jspbanuser.jsp").forward(request, response);
		}
		
	}

}
