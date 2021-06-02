package model;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import control.Book;

public class MBook {

	public String errorMsg;

	public boolean cadastrar(Book b) {

		Session session = null;
		try {

			session = HibernateUtil.abrirSession();

			session.save(b);

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
	
	public boolean updateRating(String rating, String id) {

		Session session = null;
		try {

			session = HibernateUtil.abrirSession();

			String sql = "UPDATE Book SET rating = "+rating+" WHERE id = "+id;
			Query query = session.createQuery(sql);
			query.executeUpdate();

			session.getTransaction().commit();

			HibernateUtil.fecharSession();
			// fechando a conexao
		} catch (HibernateException e) {
			HibernateUtil.fecharSession();
			errorMsg = "Erro ao atualizar Livro: " + e.toString();
			return false;
		}

		return true;

	}

	public List<Book> searchLivros(String arg) {
		// Título do Livro contem 'arg' -- usar SELECT * FROM books WHERE nome LIKE
		// '%arg%' AND status = 'A'

		Session session = null;
		List<Book> list = null;
		try {

			session = HibernateUtil.abrirSession();

			String sql = "SELECT b FROM Book b WHERE nome LIKE '%" + arg + "%'";
			list = session.createQuery(sql, Book.class).getResultList();

			session.getTransaction().commit();

			HibernateUtil.fecharSession();

			if (list == null || list.isEmpty()) {
				errorMsg = "Não Foi encontrado nenhum livro similar a '" + arg
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

	public Book localizarLivro(int id) {
		Session session = null;
		Book b = new Book();
		try {

			session = HibernateUtil.abrirSession();

			b = session.find(Book.class, id);

			session.getTransaction().commit();

			HibernateUtil.fecharSession();

		} catch (HibernateException e) {
			HibernateUtil.fecharSession();
			errorMsg = e.toString();
			return null;

		} catch (NoResultException e) {
			HibernateUtil.fecharSession();
			errorMsg = "Usuário inválido";
			return null;

		}

		return b;

	}
	
	public List<Book> livrosAutor(int id) {

		Session session = null;
		List<Book> list = null;
		try {

			session = HibernateUtil.abrirSession();

			//String sql = "SELECT b FROM Books b WHERE autor_id = "+id;
			//list = session.createQuery(sql, Book.class).getResultList();
			String sql = "SELECT * FROM books WHERE autor_id = "+id;
			list = session.createSQLQuery(sql).addEntity(Book.class).list();

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
	
	public List<Book> getTopBooks() {
		
		Session session = null;
		List<Book> topBooks;
		try {
			// pegando as configurações na session factory do arquivo hibernate.cfg.xml

			session = HibernateUtil.abrirSession();

			String sql = "FROM Book  ORDER BY rating DESC ";
			
			topBooks = session.createQuery(sql,Book.class).getResultList();


			session.getTransaction().commit();

			if(session!=null)
				session.close();
			

		} catch (HibernateException e) {
			if(session!=null)
				session.close();
			errorMsg = e.toString();
			return null;
		} catch (NoResultException e) {
			if(session!=null)
				session.close();
			errorMsg = "Não Há Livros";
			return null;
		} catch (NullPointerException e) {
			if(session!=null)
				session.close();
			errorMsg = "Não Há Livros";
			return null;
		}
		
		if(topBooks == null) {
			return null;
		}
		
		return topBooks;
		
		
		
	}
	
public List<Book> ultimosAdicionados() {
		
		Session session = null;
		List<Book> topBooks;
		try {
			// pegando as configurações na session factory do arquivo hibernate.cfg.xml

			session = HibernateUtil.abrirSession();

			String sql = "FROM Book  ORDER BY id DESC ";
			
			topBooks = session.createQuery(sql,Book.class).getResultList();


			session.getTransaction().commit();

			if(session!=null)
				session.close();
			

		} catch (HibernateException e) {
			if(session!=null)
				session.close();
			errorMsg = e.toString();
			return null;
		} catch (NoResultException e) {
			if(session!=null)
				session.close();
			errorMsg = "Não Há Livros";
			return null;
		} catch (NullPointerException e) {
			if(session!=null)
				session.close();
			errorMsg = "Não Há Livros";
			return null;
		}
		
		if(topBooks == null) {
			return null;
		}
		
		return topBooks;
		
		
		
	}

}
