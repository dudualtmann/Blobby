package model;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import control.Admin;

public class MAdmin {
	
	public String errorMsg;
	
	public boolean cadastrar(Admin a) {
		
		Session session = null;
		try {
		
		session = HibernateUtil.abrirSession();
        
        session.save(a);
        
        session.getTransaction().commit();
        
        if(session!=null)
        	session.close();
        // fechando a conexao
		} catch(HibernateException e) {
			if(session!=null)
	        	session.close();
			errorMsg = "Erro ao Cadastrar Administrador: "+e.toString();
			return false;
		}
		
		return true;
	}
	
	public Admin login(String username, String senha) {
		// M�todo para verificar se o login (user e senha) est�o corretos.
		
		Session session = null;
		Admin a = null;
		try {
		
		session = HibernateUtil.abrirSession();
    
		
		String sql = "FROM admins WHERE username = :username AND senha = :senha";
		Query query = session.createQuery(sql);

		query.setParameter("username", username);
		query.setParameter("senha", senha);

		a = (Admin) query.getSingleResult();
		
        session.getTransaction().commit();
        
        if(session!=null)
        	session.close();
        // fechando a conexao
		} catch(HibernateException e) {
			if(session!=null)
	        	session.close();
			errorMsg = "Erro ao Cadastrar Administrador: "+e.toString();
			return null;
		} catch (NoResultException e) {
			HibernateUtil.fecharSession();
			errorMsg = "Username e/ou Senha inv�lido(s)";
			return null;
		}
		
		return a;
	}
	
	public Admin findAdm(Admin admin) {
		// M�todo para Verificar se ja existe um usu�rio com tal nome
		
		Session session = null;
		Admin a = null;
		try {

			session = HibernateUtil.abrirSession();

			String sql = "FROM admins WHERE username = :username";
			Query query = session.createQuery(sql);

			query.setParameter("username", admin.getUsername());

			a = (Admin) query.getSingleResult();

			session.getTransaction().commit();

			if(session!=null)
				session.close();
			
		} catch (HibernateException e) {
			if(session!=null)
				session.close();
			errorMsg = e.toString();
			return null;
		}
		catch(NoResultException e) {
			if(session!=null)
				session.close();
			errorMsg = "Usu�rio inv�lido";
			return null;
		}
		
		if(a == null) {
			errorMsg = "Erro ao Localizar Usu�rio";
			return null;
		}

		return a;
	}

}
