package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import control.ListaPessoal;

public class MListaPessoal {
	
	public String errorMsg = null;
	
	public boolean createList(ListaPessoal lp) {
		
		 Session session = null;
			try {
			session = HibernateUtil.abrirSession();
	        
	        session.saveOrUpdate(lp);
	        
	        session.getTransaction().commit();
	        
	        HibernateUtil.fecharSession();
	        // fechando a conexao
			} catch(HibernateException e) {
				HibernateUtil.fecharSession();
				errorMsg = "Erro ao cadastrar lista: "+e.toString();
				return false;
			}
			
			return true;
		
	}

}
