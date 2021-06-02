package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.ListaPessoal;
import control.User;

/**
 * Servlet implementation class SvCadastrar
 */
@WebServlet("/SvCadastrar")
public class SvCadastrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String email = request.getParameter("cemail");
		String username = request.getParameter("cuser");
		String senha = request.getParameter("cpassword");
		
		System.out.println(email+"/"+username+"/"+senha);
		
		User u = new User(email,username,senha);
		
		if(u.cadastrar()) {
			ListaPessoal lp = new ListaPessoal(u.getId()+"");
			if(u.getListaPessoal() == null) {
			lp.addList();
			u.setListaPessoal(lp);
			u.updateUser();
			}
			request.setAttribute("cmsg", "Cadastrado com Sucesso!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else {
		request.setAttribute("cmsg", "Erro ao Cadastrar: "+u.errorMsg);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		
	}

}
