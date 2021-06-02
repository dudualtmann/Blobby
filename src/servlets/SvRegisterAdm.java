package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Admin;

/**
 * Servlet implementation class SvRegisterAdm
 */
@WebServlet("/SvRegisterAdm")
public class SvRegisterAdm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String senha = request.getParameter("senha");
		
		
		Admin a = new Admin(email, username, senha);
		
		if(a.cadastrar()) {
			request.setAttribute("msg", "Administrador Cadastrado com Sucesso!");
			request.getRequestDispatcher("jspgerenciadoradm.jsp").forward(request, response);
		}
		else {
			request.setAttribute("msg", "Erro ao Cadastrar: "+a.errorMsg);
			request.getRequestDispatcher("jspgerenciadoradm.jsp").forward(request, response);
		}
	}

}
