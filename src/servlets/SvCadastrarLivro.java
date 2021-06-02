package servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import control.Admin;
import control.Autor;
import control.Book;
import control.User;

/**
 * Servlet implementation class SvCadastrarLivro
 */
@WebServlet("/SvCadastrarLivro")
public class SvCadastrarLivro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = (String) request.getSession().getAttribute("username");
		//String username = "paulineo800";
		String nomelivro = request.getParameter("nomelivro");
		String sinopse = request.getParameter("sinopse");
		String editora = request.getParameter("editora");
		String npages = request.getParameter("npages");
		String gprincipal = request.getParameter("gprincipal");
		String ano = request.getParameter("ano");
		File fotocapa = new File(request.getParameter("fotocapa"));
		String rating = null;
		
		
		Autor at1 = (Autor) request.getSession().getAttribute("objautor");
		
		User u = new User();
		Admin a = new Admin();
		System.out.println("aqui 1");
		Autor at = new Autor(at1.getNome(), at1.getBiografia(), at1.getFoto());
		at.setId(at1.getId());
		
			
			if(u.findUser(username)) {
				System.out.println("aqui finduser");
				Book b = new Book(nomelivro, sinopse, npages, gprincipal, editora, rating, ano, fotocapa,at);
				// Em um estágio mais avançado do site esse status será "R" para usuários comuns, pois apenas os AMDs poderão de fato introduzir o
				// livro no site
				b.setStatus("A");
				
				if(b.cadastrarLivro()) {
					System.out.println("aqui cadatsrarlivro user");
					// Abrir Jsp avisando que a requisição de adicionar aquele livro foi encaminhada para os Adms
					request.setAttribute("msg", "A Requisição do livro foi mandada com sucesso para os administradores!");
					request.getRequestDispatcher("jspcadastrarlivro.jsp").forward(request, response);
				}
				else {
					//Abrir JSP com Mensagem de Erro
					request.setAttribute("msg", "Erro: "+b.errorMsg);
					request.getRequestDispatcher("jspcadastrarlivro.jsp").forward(request, response);
				}
				
			}
			else if(a.findAdm(username)) {
				System.out.println("aqui findadm");
				Book b = new Book(nomelivro, sinopse, npages, gprincipal, editora, rating, ano, fotocapa,at);
				b.setStatus("A");
				
				if(b.cadastrarLivro()) {
				// Abrir Jsp Avisando que o livro foi cadastrado com sucesso ou não
					System.out.println("aqui cadastrar adm");
					request.setAttribute("msg", "Livro cadastrado com sucesso!");
					request.getRequestDispatcher("jspcadastrarlivro.jsp").forward(request, response);
				}
				else {
					//Abrir JSP com Mensagem de Erro
					request.setAttribute("msg", "Erro: "+a.errorMsg);
					request.getRequestDispatcher("jspcadastrarlivro.jsp").forward(request, response);
				}
			}
		
		
		
	}

}
