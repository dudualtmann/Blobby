package model;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import control.Autor;
import control.Book;

public class MAutor {

	public String errorMsg;

	public boolean cadastrar(Autor a) {

		Session session = null;
		try {

			session = HibernateUtil.abrirSession();

			session.save(a);

			session.getTransaction().commit();

			HibernateUtil.fecharSession();
			// fechando a conexao
		} catch (HibernateException e) {
			HibernateUtil.fecharSession();
			errorMsg = "Erro ao Cadastrar Autor: " + e.toString();
			return false;
		}

		return true;

	}
	
	public boolean cadastrarLivro(Autor a) {

		Session session = null;
		try {

			session = HibernateUtil.abrirSession();

			session.saveOrUpdate(a);

			session.getTransaction().commit();

			HibernateUtil.fecharSession();
			// fechando a conexao
		} catch (HibernateException e) {
			HibernateUtil.fecharSession();
			errorMsg = "Erro ao Cadastrar Livro: " + e.toString();
			return false;
		}

		return true;

	}
	
	public Autor findAutor(Autor a) {
		
		Session session = null;
		try {

			session = HibernateUtil.abrirSession();

			a = session.find(Autor.class,a.getId());
			

			session.getTransaction().commit();

			HibernateUtil.fecharSession();
			
		} catch (HibernateException e) {
			HibernateUtil.fecharSession();
			errorMsg = e.toString();
			return null;
		}
		catch(NoResultException e) {
			HibernateUtil.fecharSession();
			errorMsg = "ID do Autor inválido";
			return null;
		}
		
		if(a == null) {
			errorMsg = "Erro ao Localizar Autor";
			return null;
		}
        System.out.println(a.getNome()+"/"+a.getId());
		return a;
		
	}

	public List<Autor> searchAutores(String arg) {
		// Título do Livro contem 'arg' -- usar SELECT * FROM books WHERE nome LIKE
		// '%arg%' AND status = 'A'

		Session session = null;
		List<Autor> list = null;
		try {

			session = HibernateUtil.abrirSession();

			String sql = "SELECT a FROM Autor a WHERE nome LIKE '%" + arg + "%'";
			list = session.createQuery(sql, Autor.class).getResultList();

			session.getTransaction().commit();

			HibernateUtil.fecharSession();

			if (list == null || list.isEmpty()) {
				errorMsg = "Não Foi encontrado nenhum autor similar a '" + arg
						+ "', verifique se o nome foi digitado corretamente";
				return null;
			}
			// fechando a conexao
		} catch (HibernateException e) {
			HibernateUtil.fecharSession();
			errorMsg = e.toString();
			return null;
		}

		return list;
	}

	public List<Book> livrosAutor(int id) {

		Session session = null;
		List<Book> list = null;
		try {

			session = HibernateUtil.abrirSession();

			String sql = "SELECT a FROM Autor a WHERE id_autor = "+id;
			list = session.createQuery(sql, Book.class).getResultList();

			session.getTransaction().commit();

			HibernateUtil.fecharSession();

			if (list == null || list.isEmpty()) {
				errorMsg = "Não foram encontrados mais livros do Autor";
				return null;
			}
			// fechando a conexao
		} catch (HibernateException e) {
			HibernateUtil.fecharSession();
			errorMsg = e.toString();
			return null;
		}

		return list;

	}

}
