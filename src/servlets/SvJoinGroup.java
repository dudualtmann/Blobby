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
 * Servlet implementation class SvJoinGroup
 */
@WebServlet("/SvJoinGroup")
public class SvJoinGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Grupo g = (Grupo) request.getSession().getAttribute("objgrupo");

		System.out.println("Nome do Grupo: " + g.getName());

		String username = (String) request.getSession().getAttribute("username");

		User u = new User();
		if (u.findUser(username)) {
			if (g.addUser(u)) {
				// Mudando o Status Para Já Participa do Grupo
				request.getSession().setAttribute("hasJoined", "yes");

				int members = 0;
				if (request.getSession().getAttribute("group-members") != null) {
					try {
						members = Integer.parseInt((String) request.getSession().getAttribute("group-members"));
						members += 1;
					} catch (ClassCastException e) {
						members = 1;
					}
				}
				request.getSession().setAttribute("group-members", members);

				request.setAttribute("msg", "Agora você faz parte do Grupo!");
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
