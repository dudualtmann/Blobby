package model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import control.Comment;

public class MComment {
	
	public String errorMsg;
	
	public boolean fazerComment(Comment c) {
		
		Session session = null;
		try {

			session = HibernateUtil.abrirSession();

			session.save(c);

			session.getTransaction().commit();

			if(session!=null)
				session.close();
			// fechando a conexao
		} catch (HibernateException e) {
			if(session!=null)
				session.close();
			errorMsg = "Erro ao fazer Comentário: "+e.toString();
			return false;
		}

		return true;
		
	}
	
	public List<Comment> getComments(int id_post) {

		Session session = null;
		List<Comment> list = null;
		try {

			session = HibernateUtil.abrirSession();

			String sql = "SELECT c FROM Comment c WHERE id_post = " + id_post;
			list = session.createQuery(sql, Comment.class).getResultList();

			session.getTransaction().commit();

			if(session!=null)
				session.close();

			if (list == null || list.isEmpty()) {
				errorMsg = "Ainda não Foram feitos Comentários nesse Post";
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
