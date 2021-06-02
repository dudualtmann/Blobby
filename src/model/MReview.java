package model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import control.Review;

public class MReview {
	
	public String errorMsg = null;
	
	public boolean cadastrar(Review r) {
		Session session = null;
		try {

			session = HibernateUtil.abrirSession();

			session.save(r);

			session.getTransaction().commit();

			if(session!=null)
				session.close();
			// fechando a conexao
		} catch (HibernateException e) {
			if(session!=null)
				session.close();
			errorMsg = "Erro ao Cadastrar Review: " + e.toString();
			return false;
		}

		return true;
	}
	
	public List<Review> getReviews(int id_book) {
		
		Session session = null;
		List<Review> list = null;
		try {

			session = HibernateUtil.abrirSession();

			String sql = "SELECT r FROM Review r WHERE id_book = "+id_book;
			list = session.createQuery(sql, Review.class).getResultList();

			session.getTransaction().commit();

			if(session!=null)
				session.close();

			if (list == null || list.isEmpty()) {
				errorMsg = "Não foram encontradas resenhas desse livro. Ajude e seja o primeiro a escrever uma!";
				return null;
			}
			// fechando a conexao
		} catch (HibernateException e) {
			if(session!=null)
				session.close();
			errorMsg = e.toString();
			return null;
		}

		return list;
		
	}

}
