package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Grupo;
import control.User;

/**
 * Servlet implementation class SvLeaveGroup
 */
@WebServlet("/SvLeaveGroup")
public class SvLeaveGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Tirar o Usuario do Grupo Selecionado

		Grupo g = (Grupo) request.getSession().getAttribute("objgrupo");

		System.out.println("Nome do Grupo: " + g.getName());

		String username = (String) request.getSession().getAttribute("username");

		User u = new User();
		if (u.findUser(username)) {
			if (g.leaveGroup(u)) {
				// Mudando o Status Para Não Participa do Grupo
				request.getSession().setAttribute("hasJoined", "no");

				int members = 0;
				if (request.getSession().getAttribute("group-members") != null) {
					try {
					members = Integer.parseInt((String) request.getSession().getAttribute("group-members"));
					members -= 1;
					}catch(ClassCastException e) {
						members = 1;
					}
				}
				request.getSession().setAttribute("group-members", members);

				request.setAttribute("msg", "Agora você não faz mais parte do Grupo!");
				request.getRequestDispatcher("jspperfilgrupo.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", g.errorMsg);
				request.getRequestDispatcher("jspperfilgrupo.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("msg", u.errorMsg);
			request.getRequestDispatcher("jspperfilgrupo.jsp").forward(request, response);
		}

	}

}
