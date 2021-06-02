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
 * Servlet implementation class SvPerfilGrupo
 */
@WebServlet("/SvPerfilGrupo")
public class SvPerfilGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Abrir Página de Perfil Do Grupo

		String id_grupo = request.getParameter("id_grupo");
		System.out.println(id_grupo);
		Grupo g = new Grupo();
		if (g.localizarGrupo(id_grupo)) {
			// Setando as Informações do Grupo
			String username = (String) request.getSession().getAttribute("username");
			User u = new User();
			if (u.findUser(username)) {
				// Pegando a Quantidade de Membros do Grupo
				String membros = g.countUsers(g.getId()+"");
				request.getSession().setAttribute("group-members", membros);
				// Pegando as Informações do Usuário Logado
				if (g.hasJoined(u)) {
					// Verificando se o Usuário Logado ja Participa do Grupo
					request.getSession().setAttribute("hasJoined", "yes");
					request.getSession().setAttribute("objgrupo", g);
					request.getRequestDispatcher("jspperfilgrupo.jsp").forward(request, response);
				} else {
					request.getSession().setAttribute("hasJoined", "no");
					request.getSession().setAttribute("objgrupo", g);
					request.getRequestDispatcher("jspperfilgrupo.jsp").forward(request, response);
				}

			} else {
				request.setAttribute("msg", u.errorMsg);
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}

		} else {
			request.setAttribute("msg", g.errorMsg);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}

	}

}
