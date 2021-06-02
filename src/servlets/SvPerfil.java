package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.BookListaPessoal;
import control.Grupo;
import control.User;

/**
 * Servlet implementation class SvPerfil
 */
@WebServlet("/SvPerfil")
public class SvPerfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getSession().getAttribute("username");
		
		User u = new User();
		if(u.findUser(username)) {
			BookListaPessoal bl = new BookListaPessoal();
			List<BookListaPessoal> myList = bl.getMyList(u.getListaPessoal().getId()+"");
			Grupo g = new Grupo();
			List<Long> groupIds = g.getMyList(u.getId());
			List<Grupo> myGroupList = new ArrayList<Grupo>();
			
			if(groupIds != null) {
			for(int i = 0; i < groupIds.size(); i++) {
				Grupo g2 = new Grupo();
				if(g2.localizarGrupo(groupIds.get(i)+"")) {
					myGroupList.add(g2);
					System.out.println(g2.getName() +"//"+i);
				}
			}
			}
			
			request.setAttribute("myGroupList", myGroupList);
			request.setAttribute("user", username);
			request.setAttribute("myList", myList);
			request.getRequestDispatcher("jspmylist.jsp").forward(request, response);
		}
		else {
		request.setAttribute("msg", "Erro ao Localizar o Perfil do Usuário, tente novamente");
		request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		
	}

}
