package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Grupo;

/**
 * Servlet implementation class SvDelGroup
 */
@WebServlet("/SvDelGroup")
public class SvDelGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Deletar o Grupo Selecionado
		String id_grupo = request.getParameter("groupselect");
		
		Grupo g = new Grupo();
		
		if(g.localizarGrupo(id_grupo)) {
			if(g.delGroup()) {
				request.setAttribute("msg", "Grupo Deletado");
				request.getRequestDispatcher("jspgerenciadoradm.jsp").forward(request, response);
			}
			else {
				request.setAttribute("msg", "Erro ao Deletar Grupo: "+g.errorMsg);
				request.getRequestDispatcher("jspgerenciadoradm.jsp").forward(request, response);
			}
		}
		else {
			request.setAttribute("msg", "Erro ao Localizar Grupo: "+g.errorMsg);
			request.getRequestDispatcher("jspdelgroup.jsp").forward(request, response);
		}
	}

}
