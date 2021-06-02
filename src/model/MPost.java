package model;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import control.Post;

public class MPost {
	
	public String errorMsg;
	
	public boolean fazerPost(Post p) {
		
		Session session = null;
		try {

			session = HibernateUtil.abrirSession();

			session.save(p);

			session.getTransaction().commit();

			if(session!=null)
				session.close();
			// fechando a conexao
		} catch (HibernateException e) {
			if(session!=null)
				session.close();
			errorMsg = "Erro ao fazer Post: "+e.toString();
			return false;
		}

		return true;
		
	}
	
	public List<Post> getPosts(int id_grupo) {

		Session session = null;
		List<Post> list = null;
		try {

			session = HibernateUtil.abrirSession();

			String sql = "SELECT p FROM Post p WHERE id_grupo = " + id_grupo;
			list = session.createQuery(sql, Post.class).getResultList();

			session.getTransaction().commit();

			if(session!=null)
				session.close();

			if (list == null || list.isEmpty()) {
				errorMsg = "Ainda não Foram feitos Posts nesse Grupo";
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
	
	public Post localizarPost(int id) {
		Session session = null;
		Post p = new Post();
		try {

			session = HibernateUtil.abrirSession();

			p = session.find(Post.class, id);

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
			errorMsg = "Post inválido";
			return null;

		}

		return p;

	}

}
