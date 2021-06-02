package model;


import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import control.Book;
import control.BookListaPessoal;
import control.ListaPessoal;

public class MBookListaPessoal {
	
	public String errorMsg = null;
	
	public boolean addList(BookListaPessoal lp) {
		
		 Session session = null;
			try {
			
			session = HibernateUtil.abrirSession();
	        
	        session.saveOrUpdate(lp);
	        
	        session.getTransaction().commit();
	        
	        HibernateUtil.fecharSession();
	        // fechando a conexao
			} catch(HibernateException e) {
				HibernateUtil.fecharSession();
				errorMsg = "Erro ao cadastrar na lista: "+e.toString();
				return false;
			}
			
			return true;
		
	}
	
	public boolean updateList(BookListaPessoal lp) {
		
		 Session session = null;
			try {
			
			session = HibernateUtil.abrirSession();
	        
	        session.update(lp);
	        
	        session.getTransaction().commit();
	        
	        HibernateUtil.fecharSession();
	        // fechando a conexao
			} catch(HibernateException e) {
				HibernateUtil.fecharSession();
				errorMsg = "Erro ao atualizar a lista: "+e.toString();
				return false;
			}
			
			return true;
		
	}
	
	public BookListaPessoal verifyList(ListaPessoal lp, Book b) {
		// Método para verificar se o usuário ja adicionou tal livro em sua lista
		Session session = null;
		BookListaPessoal lp2;
		try {
			// pegando as configurações na session factory do arquivo hibernate.cfg.xml

			session = HibernateUtil.abrirSession();

			String sql = "FROM BookListaPessoal WHERE id_book = :book AND id_booklist = :booklist";
			Query query = session.createQuery(sql);

			query.setParameter("book", b.getId());
			query.setParameter("booklist", lp.getId());

			lp2  = (BookListaPessoal) query.getSingleResult();

			session.getTransaction().commit();

			HibernateUtil.fecharSession();

		} catch (HibernateException e) {
			HibernateUtil.fecharSession();
			errorMsg = e.toString();
			return null;
		} catch (NoResultException e) {
			HibernateUtil.fecharSession();
			errorMsg = "O Usuário ainda não adicionou esse livro em sua lista.";
			return null;
		}
		
		if(lp2 == null) {
			return null;
		}

		return lp2;
	}
	
	public List<Double> getRating(int id_book) {
		
		Session session = null;
		List<Double> rating;
		try {
			// pegando as configurações na session factory do arquivo hibernate.cfg.xml

			session = HibernateUtil.abrirSession();

			String sql = "SELECT AVG(rating) FROM BookListaPessoal WHERE id_book = "+id_book;
			
			rating = session.createQuery(sql,Double.class).getResultList();


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
			errorMsg = "Não Há Notas para Esse Livro Adicionadas.";
			return null;
		} catch (NullPointerException e) {
			if(session!=null)
				session.close();
			errorMsg = "Não Há Notas para Esse Livro Adicionadas.";
			return null;
		}
		
		if(rating == null) {
			return null;
		}
		
		return rating;
		
		
		
	}
	
public List<Integer> countUsers(int id_book) {
		
		Session session = null;
		List<Integer> list;
		try {
			// pegando as configurações na session factory do arquivo hibernate.cfg.xml

			session = HibernateUtil.abrirSession();

			String sql = "SELECT pagLidas FROM BookListaPessoal WHERE id_book = "+id_book;

			list = session.createQuery(sql,Integer.class).getResultList();

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
			errorMsg = "Não Há Usuários que deram nota para esse livro.";
			return null;
		}
		
		if(list == null) {
			return null;
		}
		
		

		return list;
		
		
		
	}

public List<BookListaPessoal> getMyList(String id_booklist) {
	
	Session session = null;
	List<BookListaPessoal> myList;
	try {
		// pegando as configurações na session factory do arquivo hibernate.cfg.xml

		session = HibernateUtil.abrirSession();

		String sql = "FROM BookListaPessoal WHERE id_booklist = "+id_booklist;
		
		myList = session.createQuery(sql,BookListaPessoal.class).getResultList();


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
	
	if(myList == null) {
		return null;
	}
	
	return myList;
	
	
	
}



}
