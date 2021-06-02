package servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Grupo;
import control.User;

/**
 * Servlet implementation class SvCadastrarGrupo
 */
@WebServlet("/SvCadastrarGrupo")
public class SvCadastrarGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Cadastro do Grupo
		
		String nomeGrupo = request.getParameter("nomegrupo");
		User u = (User) request.getSession().getAttribute("objuser");
	    String desc = request.getParameter("desc");
	    String rules = request.getParameter("rules");
	    String category = request.getParameter("category");
	    String year = request.getParameter("ano");
	    File fotoGrupo = new File(request.getParameter("fotogrupo"));
	    
	    Grupo g = new Grupo(u, nomeGrupo, desc, rules, year, category,fotoGrupo);
	    if(g.cadastrar()) {
	    	request.setAttribute("msg", "Grupo Criado com Sucesso!");
	    	request.getRequestDispatcher("home.jsp").forward(request, response);
	    }
	    else {
	    	request.setAttribute("msg", "Erro ao Cadastrar Grupo, Tente Novamente.");
	    	request.getRequestDispatcher("home.jsp").forward(request, response);
	    }
	}

}
