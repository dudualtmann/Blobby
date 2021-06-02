package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Autor;

/**
 * Servlet implementation class SvRCadastroLivro
 */
@WebServlet("/SvRCadastroLivro")
public class SvRCadastroLivro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Autor a = new Autor();
		String id_autor = request.getParameter("autorselect");
		if(a.findAutor(id_autor)) {
			System.out.println(a.getId()+"/"+a.getNome());
			request.getSession().setAttribute("objautor", a);
			request.setAttribute("msg", "Autor localizado com sucesso. Agora só falta colocar as informações do livro!");
			request.getRequestDispatcher("jspcadastrarlivro.jsp").forward(request, response);
		}
		else {
			request.setAttribute("msg", a.errorMsg);
			request.getRequestDispatcher("jspsearchautor.jsp").forward(request, response);
		}
		
	}

}
