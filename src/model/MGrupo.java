package model;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import control.Grupo;
import control.User;

public class MGrupo {
	
	public String errorMsg;
	

	public boolean cadastrar(Grupo g) {
		Session session = null;
		try {

			session = HibernateUtil.abrirSession();

			session.save(g);

			session.getTransaction().commit();

			if(session!=null)
				session.close();
			// fechando a conexao
		} catch (HibernateException e) {
			if(session!=null)
				session.close();
			errorMsg = e.toString();
			return false;
		}

		return true;

	}
	
	public List<Grupo> searchGrupos(String arg) {
	   	 // Título do Grupo contem 'arg' -- usar SELECT * FROM grupos WHERE nome LIKE '%arg%'
	   	 
		Session session = null;
		List<Grupo> list = null;
		try {

			session = HibernateUtil.abrirSession();

			String sql = "SELECT g FROM Grupo g WHERE name LIKE '%" + arg + "%'";
			list = session.createQuery(sql, Grupo.class).getResultList();

			session.getTransaction().commit();

			if(session!=null)
				session.close();

			if (list == null || list.isEmpty()) {
				errorMsg = "Não Foi encontrado nenhum Grupo similar a '" + arg
						+ "', verifique se o nome foi digitado corretamente";
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
	
	public Grupo localizarGrupo(int id_grupo) {
	
		Session session = null;
		Grupo g = new Grupo();
		try {

			session = HibernateUtil.abrirSession();

			g = session.find(Grupo.class, id_grupo);

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
			errorMsg = "Grupo inválido";
			return null;

		}

		return g;
	}
	
	public boolean addUser(Grupo g, User u) {
		
		 Session session = null;
			try {
			
			session = HibernateUtil.abrirSession();
			
			Query query = session.createSQLQuery("INSERT INTO grupo_user(id_grupo, id_user) VALUES (:valor1, :valor2)");
			query.setParameter("valor1", g.getId());
			query.setParameter("valor2", u.getId());
			query.executeUpdate();
	       
	        session.getTransaction().commit();
	        
	        if(session!=null)
	        	session.close();
	        // fechando a conexao
			} catch(HibernateException e) {
				 if(session!=null)
			        	session.close();
				errorMsg = "Erro ao entrar no grupo: "+e.toString();
				return false;
			}
			
			return true;
		
	}
	
public boolean hasJoined(Grupo g, User u) {
		
			Session session = null;
			try {
				// pegando as configurações na session factory do arquivo hibernate.cfg.xml

				session = HibernateUtil.abrirSession();

				String sql = "SELECT * FROM grupo_user WHERE id_grupo = :valor1 AND id_user = :valor2";
				Query query = session.createSQLQuery(sql);

				query.setParameter("valor1", g.getId());
				query.setParameter("valor2", u.getId());
				
				query.getSingleResult();

				session.getTransaction().commit();

				if(session!=null)
					session.close();

			} catch (HibernateException e) {
				if(session!=null)
					session.close();
				errorMsg = e.toString();
				return false;
			} catch (NoResultException e) {
				if(session!=null)
					session.close();
				errorMsg = "O Usuário não faz parte do grupo";
				return false;
			}

			return true;
		
	}

public List<Long> countUsers(int id_grupo) {
	
	Session session = null;
	List<Long> list;
	try {
		// pegando as configurações na session factory do arquivo hibernate.cfg.xml

		session = HibernateUtil.abrirSession();

		String sql = "SELECT * FROM grupo_user WHERE id_grupo = "+id_grupo;

		list = (List<Long>) session.createSQLQuery(sql).getResultList();

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
		errorMsg = "Não há membros no grupo.";
		return null;
	}
	
	if(list == null) {
		return null;
	}
	
	

	return list;
	
	
	
}

public boolean leaveGroup(Grupo g, User u) {
	
	Session session = null;
	int result;
	try {
		// pegando as configurações na session factory do arquivo hibernate.cfg.xml

		session = HibernateUtil.abrirSession();

		String sql = "DELETE FROM grupo_user WHERE id_grupo = :valor1 AND id_user = :valor2";
		Query query = session.createSQLQuery(sql);

		query.setParameter("valor1", g.getId());
		query.setParameter("valor2", u.getId());
		
		result = query.executeUpdate();

		session.getTransaction().commit();

		if(session!=null)
			session.close();

	} catch (HibernateException e) {
		if(session!=null)
			session.close();
		errorMsg = e.toString();
		return false;
	} catch (NoResultException e) {
		if(session!=null)
			session.close();
		errorMsg = "O Usuário não faz parte do grupo";
		return false;
	}
	
	if(result==0) {
		errorMsg = "Não foi Possível Sair do Grupo.";
		return false;
	}

	return true;

}

public List<Grupo> ultimosAdicionados() {
	
	Session session = null;
	List<Grupo> listGrupos;
	try {
		// pegando as configurações na session factory do arquivo hibernate.cfg.xml

		session = HibernateUtil.abrirSession();

		String sql = "FROM Grupo  ORDER BY id DESC ";
		
		listGrupos = session.createQuery(sql,Grupo.class).getResultList();


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
		errorMsg = "Não Há Grupos";
		return null;
	} catch (NullPointerException e) {
		if(session!=null)
			session.close();
		errorMsg = "Não Há Grupos";
		return null;
	}
	
	if(listGrupos == null) {
		return null;
	}
	
	return listGrupos;
	
	
	
}

public List<Long> getMyList(int id_user) {
	
	Session session = null;
	List<Long> myList;
	try {
		// pegando as configurações na session factory do arquivo hibernate.cfg.xml

		session = HibernateUtil.abrirSession();

		String sql = "SELECT id_grupo FROM grupo_user WHERE id_user = "+id_user;
		
		myList = (List<Long>) session.createSQLQuery(sql).getResultList(); 


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
		errorMsg = "Não Há Grupos";
		return null;
	} catch (NullPointerException e) {
		if(session!=null)
			session.close();
		errorMsg = "Não Há Grupos";
		return null;
	}
	
	if(myList == null) {
		return null;
	}
	
	return myList;
	
	
	
}

	public boolean delGroup(Grupo g) {
		Session session = null;
		try {

			session = HibernateUtil.abrirSession();

			session.delete(g);

			session.getTransaction().commit();

			if(session!=null)
				session.close();
			// fechando a conexao
		} catch (HibernateException e) {
			if(session!=null)
				session.close();
			errorMsg = e.toString();
			return false;
		}

		return true;
	}


}
