package model;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import control.User;

public class MUser {

	public String errorMsg;

	public boolean cadastrar(User u) {
		Session session = null;
		try {

			session = HibernateUtil.abrirSession();
			System.out.println(u.getEmail()+"/"+u.getUsername()+"/"+u.getSenha());
			System.out.println(HibernateUtil.erro);

			session.save(u);

			session.getTransaction().commit();

			if(session != null)
				session.close();
			// fechando a conexao
		} catch (HibernateException e) {
			//HibernateUtil.fecharSession();
			if(session != null)
				session.close();
			errorMsg = e.toString();
			return false;
		}

		return true;

	}

	public User login(String username, String senha) {
		// Método para verificar se o login (user e senha) estão corretos.
		Session session = null;
		// List<User> list = null;
		User u = null;
		try {
			// pegando as configurações na session factory do arquivo hibernate.cfg.xml

			session = HibernateUtil.abrirSession();

			String sql = "FROM users WHERE username = :username AND senha = :senha";
			Query query = session.createQuery(sql);

			query.setParameter("username", username);
			query.setParameter("senha", senha);

			u = (User) query.getSingleResult();

			session.getTransaction().commit();

			if(session != null)
				session.close();

		} catch (HibernateException e) {
			if(session != null)
				session.close();
			errorMsg = e.toString();
			return null;
		} catch (NoResultException e) {
			if(session != null)
				session.close();
			errorMsg = "Username e/ou Senha inválido(s)";
			return null;
		}

		return u;
	}
	
	public boolean update(User u) {
		Session session = null;
		try {

			session = HibernateUtil.abrirSession();

			session.update(u);

			session.getTransaction().commit();

			if(session != null)
				session.close();
			// fechando a conexao
		} catch (HibernateException e) {
			//HibernateUtil.fecharSession();
			if(session != null)
				session.close();
			errorMsg = e.toString();
			return false;
		}

		return true;
	}

	public User findUser(User user) {
		// Método para Verificar se ja existe um usuário com tal nome

		Session session = null;
		User u = null;
		try {

			session = HibernateUtil.abrirSession();

			String sql = "FROM users WHERE username = :username";
			Query query = session.createQuery(sql);

			query.setParameter("username", user.getUsername());

			u = (User) query.getSingleResult();

			session.getTransaction().commit();

			if(session != null)
				session.close();
			
		} catch (HibernateException e) {
			if(session != null)
				session.close();
			errorMsg = e.toString();
			return null;
		}
		catch(NoResultException e) {
			if(session != null)
				session.close();
			errorMsg = "Usuário inválido";
			return null;
		}
		
		if(u == null) {
			errorMsg = "Erro ao Localizar Usuário";
			return null;
		}

		return u;
	}

	public List<User> searchUsers(String arg) {
		// User contem 'arg' -- usar SELECT * FROM users WHERE nome LIKE '%arg%'
		Session session = null;
		List<User> list = null;

		try {
			
			
			
			session = HibernateUtil.abrirSession();
	        
	        String sql = "FROM users  WHERE username LIKE '%"+arg+"%'";
	        
	        list = session.createQuery(sql,User.class).getResultList();
	        
	        session.getTransaction().commit();
	        
	        if(session != null)
				session.close();
	        
	        if(list==null ||list.isEmpty()) {
	        	errorMsg = "Não Foi encontrado nenhum Usuário similar a '"+arg+"', verifique se o nome foi digitado corretamente";
	        	return null;
	        }
	        // fechando a conexao
			} catch( HibernateException e ) {
				if(session != null)
					session.close();
				errorMsg = e.toString();
				return null;
			}
		
		return list;

	}
	
	public boolean delUser(User u) {
		
		Session session = null;
		try {

			session = HibernateUtil.abrirSession();
	

			session.delete(u);

			session.getTransaction().commit();

			if(session != null)
				session.close();
			// fechando a conexao
		} catch (HibernateException e) {
			if(session != null)
				session.close();
			errorMsg = e.toString();
			return false;
		}

		return true;
		
	}

}
