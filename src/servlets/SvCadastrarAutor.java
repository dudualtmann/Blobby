package servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import control.Autor;

/**
 * Servlet implementation class SvCadastrarAutor
 */
@WebServlet("/SvCadastrarAutor")
public class SvCadastrarAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Cadastrar Autor
		String nome = request.getParameter("autor");
		String bio = request.getParameter("bio");
		File foto = new File(request.getParameter("foto"));
		Autor a = new Autor(nome, bio, foto);
		
		if(a.cadastrarAutor()) {
			request.getSession().setAttribute("objautor", a);
			request.setAttribute("msg", "Autor cadastrado com sucesso. Agora só falta colocar as informações do livro!");
			request.getRequestDispatcher("jspcadastrarlivro.jsp").forward(request, response);
		}
		else {
			request.setAttribute("msg", "Erro ao Cadastrar Autor, tente novamente");
			request.getRequestDispatcher("jspcadastrarautor.jsp").forward(request, response);
		}
		
	}

}
